package pl.jrola.java.android.vigym.vigymobile.db.to;

import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

/**
 * Transfer object for user.
 * 
 */
public class UserTransferObject extends TransferObject {

	private Long user_id;
	private String user_mail;
	private String user_password;
	private String user_nickname;

	public UserTransferObject() {
		super();
	}

	public UserTransferObject(Long userId, String userMail,
			String userPassword, String userNickname) {
		super();
		this.user_id = userId;
		this.user_mail = userMail;
		this.user_password = userPassword;
		this.user_nickname = userNickname;
	}

	public UserTransferObject(Cursor cursor) {
		super();

		int columnIndexUserId = cursor.getColumnIndex(DbUtils.DB_COLUMN_USERS_USER_ID);
		this.user_id = cursor.getLong(columnIndexUserId);

		int columnIndexUserMain = cursor.getColumnIndex(DbUtils.DB_COLUMN_USERS_USER_MAIL);
		this.user_mail = cursor.getString(columnIndexUserMain);

		int columnIndexUserNickname = cursor.getColumnIndex(DbUtils.DB_COLUMN_USERS_USER_NICKNAME);
		this.user_nickname = cursor.getString(columnIndexUserNickname);

		int columnIndexUserPassword = cursor.getColumnIndex(DbUtils.DB_COLUMN_USERS_USER_PASSWORD);
		this.user_password = cursor.getString(columnIndexUserPassword);
	}

	public Long getId() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public boolean verifyPassword(String password) {

		if (this.user_password.equals(Utils.generateSHA1Hash(password)))
			return true;

		return false;
	}

}