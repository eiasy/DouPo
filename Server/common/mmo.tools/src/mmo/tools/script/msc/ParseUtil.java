package mmo.tools.script.msc;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

public class ParseUtil {
		public ParseUtil() {
		}

		static Hashtable tableReplacedTexts = new Hashtable();

		public static void addReplacedText(String from, String to) {
				tableReplacedTexts.put(from, to);
		}

		static String modifyPath(String path) {
				Enumeration anEnum = tableReplacedTexts.keys();
				while (anEnum.hasMoreElements()) {
						String from = (String) anEnum.nextElement();
						String to = (String) tableReplacedTexts.get(from);

						path = path.replaceAll(from, to);
				}

				return path;
		}

		public static String getCurrentDir() {
				return new File("").getAbsolutePath();
		}

		public static String getFileDir(String filename) {
				return new File(filename).getParent();
		}

		/**
		 * translate a filename into absolute full path filename
		 * 
		 * @param filename
		 *            String filename will be translated. if filename is already
		 *            a full path, nothing to do. if filename is not a full
		 *            path, it will be view as a path relative to current dir.
		 * @return String
		 */
		public static String getFullPathFilename(String filename) {
				return modifyPath(new File(filename).getAbsolutePath());
				// File file = new File ( filename );
				// if ( file.isAbsolute() ) return filename;
				// else return file.getAbsolutePath();
		}

		/**
		 * translate a filename into absolute full path filename
		 * 
		 * @param filename
		 *            String filename will be translated. if filename is already
		 *            a full path, nothing to do. if filename is not a full
		 *            path, it will be view as a path relative to the path
		 *            specifed with relativeTo.
		 * @param relativeTo
		 *            String must be absolute (path or file)
		 * @return String
		 */
		public static String getFullPathFilename(String filename, String relativeTo) throws Exception {
				if (!new File(relativeTo).isAbsolute())
						throw new Exception("relativeTo is not absolute");

				if (new File(filename).isAbsolute())
						return modifyPath(new File(filename).getCanonicalPath());
				else if (new File(relativeTo).isFile())
						return modifyPath(new File(getFileDir(relativeTo), filename).getCanonicalPath());
				else // if ( new File ( relativeTo ).isDirectory() )
				{
						return modifyPath(new File(relativeTo, filename).getCanonicalPath());
				}
		}

		public static String getRelativePathFilename(String filename, String relativeTo) throws Exception {
				relativeTo = new File(relativeTo).getCanonicalPath();
				filename = getFullPathFilename(filename, relativeTo);

				int slash_char = '/';
				if (relativeTo.indexOf(slash_char) < 0)
						slash_char = '\\';

				if (!new File(relativeTo).isFile())
						relativeTo = relativeTo + (char) slash_char;

				if (!new File(filename).isFile())
						filename = filename + (char) slash_char;

				int prev_slash_pos = 0;
				int curr_slash_pos;
				do {
						curr_slash_pos = relativeTo.indexOf(slash_char, prev_slash_pos + 1);
						if (curr_slash_pos > 0
						                && relativeTo.regionMatches(true, prev_slash_pos, filename, prev_slash_pos,
						                                curr_slash_pos + 1 - prev_slash_pos)) {
								prev_slash_pos = curr_slash_pos;
						} else
								break;
				} while (true);

				int common_path_length = prev_slash_pos;
				int gen_gaps = 0;
				do {
						curr_slash_pos = relativeTo.indexOf(slash_char, prev_slash_pos + 1);
						if (curr_slash_pos > 0) {
								++gen_gaps;
								prev_slash_pos = curr_slash_pos;
						} else
								break;
				} while (true);

				StringBuffer rel_path = new StringBuffer(255);

				if (gen_gaps == 0 && common_path_length == filename.length()) {
						rel_path.append('.');
						rel_path.append((char) slash_char);
				} else {
						while (--gen_gaps >= 0) {
								rel_path.append('.');
								rel_path.append('.');
								rel_path.append((char) slash_char);
						}

						rel_path.append(filename.substring(common_path_length + 1));
				}

				return modifyPath(rel_path.toString());
		}

		public static String getRelativePathFilename(String filename) throws Exception {
				return getRelativePathFilename(filename, getCurrentdir());
		}

		static Stack _currentDirStack = new Stack();

		public static void pushCurrentDir(String dir) {
				_currentDirStack.push(dir);
		}

		public static String getCurrentdir() {
				return (String) _currentDirStack.peek();
		}

		public static String popCurrentDir() {
				return (String) _currentDirStack.pop();
		}

}
