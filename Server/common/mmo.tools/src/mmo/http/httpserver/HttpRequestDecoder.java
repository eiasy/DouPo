package mmo.http.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.tools.config.ProjectCofigs;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public class HttpRequestDecoder extends MessageDecoderAdapter {
	private static final byte[] CONTENT_LENGTH = new String("Content-Length:").getBytes();
	static String               defaultEncoding;
	private CharsetDecoder      decoder;

	public CharsetDecoder getDecoder() {
		return decoder;
	}

	public void setEncoder(CharsetDecoder decoder) {
		this.decoder = decoder;
	}

	public HttpRequestDecoder() {
		decoder = Charset.forName(defaultEncoding).newDecoder();
	}

	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		try {
			return messageComplete(in) ? MessageDecoderResult.OK : MessageDecoderResult.NEED_DATA;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return MessageDecoderResult.NOT_OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		HttpRequestMessage m = decodeBody(in);

		// Return NEED_DATA if the body is not fully read.
		if (m == null) {
			return MessageDecoderResult.NEED_DATA;
		}

		out.write(m);

		return MessageDecoderResult.OK;

	}

	/*
	 * 判断HTTP请求是否完整，若格式有错误直接抛出异常
	 */
	private boolean messageComplete(IoBuffer in) {
		int last = in.remaining() - 1;
		if (in.remaining() < 4) {
			return false;
		}

		// to speed up things we check if the Http request is a GET or POST
		if (in.get(0) == (byte) 'G' && in.get(1) == (byte) 'E' && in.get(2) == (byte) 'T') {
			// Http GET request therefore the last 4 bytes should be 0x0D 0x0A
			// 0x0D 0x0A
			return in.get(last) == (byte) 0x0A && in.get(last - 1) == (byte) 0x0D && in.get(last - 2) == (byte) 0x0A
			        && in.get(last - 3) == (byte) 0x0D;
		} else if (in.get(0) == (byte) 'P' && in.get(1) == (byte) 'O' && in.get(2) == (byte) 'S' && in.get(3) == (byte) 'T') {
			// Http POST request
			// first the position of the 0x0D 0x0A 0x0D 0x0A bytes
			int eoh = -1;
			for (int i = last; i > 2; i--) {
				if (in.get(i) == (byte) 0x0A && in.get(i - 1) == (byte) 0x0D && in.get(i - 2) == (byte) 0x0A && in.get(i - 3) == (byte) 0x0D) {
					eoh = i + 1;
					break;
				}
			}
			if (eoh == -1) {
				return false;
			}
			for (int i = 0; i < last; i++) {
				boolean found = false;
				for (int j = 0; j < CONTENT_LENGTH.length; j++) {
					if (in.get(i + j) != CONTENT_LENGTH[j]) {
						found = false;
						break;
					}
					found = true;
				}
				if (found) {
					// retrieve value from this position till next 0x0D 0x0A
					StringBuilder contentLength = new StringBuilder();
					for (int j = i + CONTENT_LENGTH.length; j < last; j++) {
						if (in.get(j) == 0x0D) {
							break;
						}
						contentLength.append(new String(new byte[] { in.get(j) }));
					}
					// if content-length worth of data has been received then
					// the message is complete
					return Integer.parseInt(contentLength.toString().trim()) + eoh == in.remaining();
				}
			}
		}

		// the message is not complete and we need more data
		return false;

	}

	private HttpRequestMessage decodeBody(IoBuffer in) {
		HttpRequestMessage request = new HttpRequestMessage();
		try {
			parseRequest(request, new StringReader(in.getString(decoder)));
			return request;
		} catch (CharacterCodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private Map<String, String[]> parseRequest(HttpRequestMessage request, StringReader is) {
		Map<String, String[]> hearders = new HashMap<String, String[]>();
		List<String> parameterNames = new ArrayList<String>();
		BufferedReader rdr = new BufferedReader(is);

		try {
			String line = rdr.readLine();
			String[] url = line.split(" ");
			if (url.length < 3) {
				return hearders;
			}

			hearders.put("URI", new String[] { line });
			hearders.put("Method", new String[] { url[0].toUpperCase() });
			hearders.put("Context", new String[] { url[1].substring(1) });
			hearders.put("Protocol", new String[] { url[2] });
			while ((line = rdr.readLine()) != null && line.length() > 0) {
				String[] tokens = line.split(": ");
				hearders.put(tokens[0], new String[] { tokens[1] });
			}
			if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
				LoggerError.warn("REQUEST TYPE:" + url[0]);
			}
			String postLine = null;

			if (url[0].equalsIgnoreCase("POST")) {
				int len = Integer.parseInt(hearders.get("Content-Length")[0]);
				char[] buf = new char[len];
				int readLength = rdr.read(buf);
				if (readLength == len) {
					line = String.copyValueOf(buf);
					if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
						LoggerError.warn("POST CONTENT:" + line + "   len=" + len + ",readLength=" + readLength);
					}
					line = URLDecoder.decode(line, HttpRequestDecoder.defaultEncoding);
				} else {
					line = String.copyValueOf(buf);
					if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
						LoggerError.warn("POST CONTENT:" + line + "   len=" + len + ",readLength=" + readLength);
					}
					line = URLDecoder.decode(line, HttpRequestDecoder.defaultEncoding);
				}
				if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
					LoggerError.warn("POST CONTENT DECODE:" + line + "   len=" + len + ",readLength=" + readLength);
				}

				int idx = url[1].indexOf('?');
				if (idx != -1) {
					hearders.put("Context", new String[] { url[1].substring(1, idx) });
					postLine = url[1].substring(idx + 1);
					if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
						LoggerError.warn("POST GET CONTENT:" + postLine);
					}
					postLine = URLDecoder.decode(postLine, HttpRequestDecoder.defaultEncoding);
					if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
						LoggerError.warn("POST GET CONTENT DECODE:" + postLine);
					}
				} else {
					postLine = null;
				}

			} else if (url[0].equalsIgnoreCase("GET")) {
				int idx = url[1].indexOf('?');
				if (idx != -1) {
					hearders.put("Context", new String[] { url[1].substring(1, idx) });
					line = url[1].substring(idx + 1);
					if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
						LoggerError.warn("GET CONTENT:" + line);
					}
					line = URLDecoder.decode(line, HttpRequestDecoder.defaultEncoding);
				} else {
					line = null;
				}
				if ("1".equals(ProjectCofigs.getParameter("LOGGER_HTTP"))) {
					LoggerError.warn("GET CONTENT DECODE:" + line);
				}
			}
			if (line != null) {
				String[] match = line.split("\\&");
				for (String element : match) {
					String[] params = new String[1];
					int firstIndex = element.indexOf("=");
					if (firstIndex == -1) {
						if (!"".equals(element.trim())) {
							hearders.put("@".concat(element), new String[] {});
							parameterNames.add(element);
						}
					} else if (firstIndex == 0) {
						hearders.put("@".concat(element.substring(0, firstIndex)),
						        new String[] { element.substring(firstIndex + 1, element.length()) });
						parameterNames.add(element);
					} else {
						String k = element.substring(0, firstIndex);
						String v = element.substring(firstIndex + 1, element.length());
						String name = "@".concat(k);
						if (hearders.containsKey(name)) {
							params = hearders.get(name);
							String[] tmp = new String[params.length + 1];
							for (int j = 0; j < params.length; j++) {
								tmp[j] = params[j];
							}
							params = null;
							params = tmp;
						}
						params[params.length - 1] = v.trim();
						hearders.put(name, params);
						parameterNames.add(k);
					}
				}
			}

			if (postLine != null) {
				String[] match = postLine.split("\\&");
				for (String element : match) {
					String[] params = new String[1];
					int firstIndex = element.indexOf("=");
					if (firstIndex == -1) {
						hearders.put("@".concat(element), new String[] {});
						parameterNames.add(element);
					} else if (firstIndex == 0) {
						hearders.put("@".concat(element.substring(0, firstIndex)),
						        new String[] { element.substring(firstIndex + 1, element.length()) });
						parameterNames.add(element);
					} else {
						String k = element.substring(0, firstIndex);
						String v = element.substring(firstIndex + 1, element.length());
						String name = "@".concat(k);
						if (hearders.containsKey(name)) {
							params = hearders.get(name);
							String[] tmp = new String[params.length + 1];
							for (int j = 0; j < params.length; j++) {
								tmp[j] = params[j];
							}
							params = null;
							params = tmp;
						}
						params[params.length - 1] = v.trim();
						hearders.put(name, params);
						parameterNames.add(k);
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		request.setHeaders(hearders, parameterNames);
		return hearders;
	}

}
