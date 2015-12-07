package mmo.module.admin;

import java.sql.Date;

public class GameDataVersion {
		public static String ID          = "id";
		public static String USERID      = "userid";
		public static String TABLENAME   = "tablename";
		public static String NAMENOTE    = "namenote";
		public static String CATE        = "cate";
		public static String DATAVERSION = "dataversion";
		public static String ATIME       = "atime";
		public static String NOTE        = "note";
		public static String STATE       = "state";

		private int          id;
		private String       userId;
		private String       tableName;
		private String       nameNote;
		private byte         cate;
		private int          dataVersion;
		private Date         atime;
		private String       note;
		private int          state;

		public final int getId() {
				return id;
		}

		public final void setId(int id) {
				this.id = id;
		}

		public final String getUserId() {
				return userId;
		}

		public final void setUserId(String userId) {
				this.userId = userId;
		}

		public final String getTableName() {
				return tableName;
		}

		public final void setTableName(String tableName) {
				this.tableName = tableName;
		}

		public final String getNameNote() {
				return nameNote;
		}

		public final void setNameNote(String nameNote) {
				this.nameNote = nameNote;
		}

		public final byte getCate() {
				return cate;
		}

		public final void setCate(byte cate) {
				this.cate = cate;
		}

		public final int getDataVersion() {
				return dataVersion;
		}

		public final void setDataVersion(int dataVersion) {
				this.dataVersion = dataVersion;
		}

		public final Date getAtime() {
				return atime;
		}

		public final void setAtime(Date atime) {
				this.atime = atime;
		}

		public final String getNote() {
				return note;
		}

		public final void setNote(String note) {
				this.note = note;
		}

		public final int getState() {
				return state;
		}

		public final void setState(int state) {
				this.state = state;
		}
}
