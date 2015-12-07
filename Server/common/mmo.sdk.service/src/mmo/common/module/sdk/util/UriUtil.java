package mmo.common.module.sdk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UriUtil {
	/** Unicode replacement character: \\uFFFD. */
	private static final byte[] REPLACEMENT      = { (byte) 0xFF, (byte) 0xFD };
	/** Index of a component which was not found. */
	private final static int    NOT_FOUND        = -1;

	/** Placeholder value for an index which hasn't been calculated yet. */
	private final static int    NOT_CALCULATED   = -2;

	/**
	 * Error message presented when a user tries to treat an opaque URI as hierarchical.
	 */
	private static final String NOT_HIERARCHICAL = "This isn't a hierarchical URI.";
	private static final char[] HEX_DIGITS       = "0123456789ABCDEF".toCharArray();
	/** Default encoding. */
	private static final String DEFAULT_ENCODING = "UTF-8";

	public static String decode(String s) {
		/*
		 * Compared to java.net.URLEncoderDecoder.decode(), this method decodes a chunk at a time instead of one
		 * character at a time, and it doesn't throw exceptions. It also only allocates memory when necessary--if
		 * there's nothing to decode, this method won't do much.
		 */

		if (s == null) {
			return null;
		}

		// Lazily-initialized buffers.
		StringBuilder decoded = null;
		ByteArrayOutputStream out = null;

		int oldLength = s.length();

		// This loop alternates between copying over normal characters and
		// escaping in chunks. This results in fewer method calls and
		// allocations than decoding one character at a time.
		int current = 0;
		while (current < oldLength) {
			// Start in "copying" mode where we copy over normal characters.

			// Find the next escape sequence.
			int nextEscape = s.indexOf('%', current);

			if (nextEscape == NOT_FOUND) {
				if (decoded == null) {
					// We didn't actually decode anything.
					return s;
				} else {
					// Append the remainder and return the decoded string.
					decoded.append(s, current, oldLength);
					return decoded.toString();
				}
			}

			// Prepare buffers.
			if (decoded == null) {
				// Looks like we're going to need the buffers...
				// We know the new string will be shorter. Using the old length
				// may overshoot a bit, but it will save us from resizing the
				// buffer.
				decoded = new StringBuilder(oldLength);
				out = new ByteArrayOutputStream(4);
			} else {
				// Clear decoding buffer.
				out.reset();
			}

			// Append characters leading up to the escape.
			if (nextEscape > current) {
				decoded.append(s, current, nextEscape);

				current = nextEscape;
			} else {
				// assert current == nextEscape
			}

			// Switch to "decoding" mode where we decode a string of escape
			// sequences.

			// Decode and append escape sequences. Escape sequences look like
			// "%ab" where % is literal and a and b are hex digits.
			try {
				do {
					if (current + 2 >= oldLength) {
						// Truncated escape sequence.
						out.write(REPLACEMENT);
					} else {
						int a = Character.digit(s.charAt(current + 1), 16);
						int b = Character.digit(s.charAt(current + 2), 16);

						if (a == -1 || b == -1) {
							// Non hex digits.
							out.write(REPLACEMENT);
						} else {
							// Combine the hex digits into one byte and write.
							out.write((a << 4) + b);
						}
					}

					// Move passed the escape sequence.
					current += 3;
				} while (current < oldLength && s.charAt(current) == '%');

				// Decode UTF-8 bytes into a string and append it.
				decoded.append(out.toString(DEFAULT_ENCODING));
			} catch (UnsupportedEncodingException e) {
				throw new AssertionError(e);
			} catch (IOException e) {
				throw new AssertionError(e);
			}
		}

		// If we don't have a buffer, we didn't have to decode anything.
		return decoded == null ? s : decoded.toString();
	}

	/**
	 * Encodes characters in the given string as '%'-escaped octets using the UTF-8 scheme. Leaves letters ("A-Z",
	 * "a-z"), numbers ("0-9"), and unreserved characters ("_-!.~'()*") intact. Encodes all other characters.
	 * 
	 * @param s
	 *            string to encode
	 * @return an encoded version of s suitable for use as a URI component, or null if s is null
	 */
	public static String encode(String s) {
		return encode(s, null);
	}

	public static String encode(String s, String allow) {
		if (s == null) {
			return null;
		}

		// Lazily-initialized buffers.
		StringBuilder encoded = null;

		int oldLength = s.length();

		// This loop alternates between copying over allowed characters and
		// encoding in chunks. This results in fewer method calls and
		// allocations than encoding one character at a time.
		int current = 0;
		while (current < oldLength) {
			// Start in "copying" mode where we copy over allowed chars.

			// Find the next character which needs to be encoded.
			int nextToEncode = current;
			while (nextToEncode < oldLength && isAllowed(s.charAt(nextToEncode), allow)) {
				nextToEncode++;
			}

			// If there's nothing more to encode...
			if (nextToEncode == oldLength) {
				if (current == 0) {
					// We didn't need to encode anything!
					return s;
				} else {
					// Presumably, we've already done some encoding.
					encoded.append(s, current, oldLength);
					return encoded.toString();
				}
			}

			if (encoded == null) {
				encoded = new StringBuilder();
			}

			if (nextToEncode > current) {
				// Append allowed characters leading up to this point.
				encoded.append(s, current, nextToEncode);
			} else {
				// assert nextToEncode == current
			}

			// Switch to "encoding" mode.

			// Find the next allowed character.
			current = nextToEncode;
			int nextAllowed = current + 1;
			while (nextAllowed < oldLength && !isAllowed(s.charAt(nextAllowed), allow)) {
				nextAllowed++;
			}

			// Convert the substring to bytes and encode the bytes as
			// '%'-escaped octets.
			String toEncode = s.substring(current, nextAllowed);
			try {
				byte[] bytes = toEncode.getBytes(DEFAULT_ENCODING);
				int bytesLength = bytes.length;
				for (int i = 0; i < bytesLength; i++) {
					encoded.append('%');
					encoded.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4]);
					encoded.append(HEX_DIGITS[bytes[i] & 0xf]);
				}
			} catch (UnsupportedEncodingException e) {
				throw new AssertionError(e);
			}

			current = nextAllowed;
		}

		// Encoded could still be null at this point if s is empty.
		return encoded == null ? s : encoded.toString();
	}

	private static boolean isAllowed(char c, String allow) {
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || "_-!.~'()*".indexOf(c) != NOT_FOUND
		        || (allow != null && allow.indexOf(c) != NOT_FOUND);
	}

}
