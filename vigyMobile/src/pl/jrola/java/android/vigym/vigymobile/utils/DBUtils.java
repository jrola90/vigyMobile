package pl.jrola.java.android.vigym.vigymobile.utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import android.os.Environment;

public abstract class DBUtils {
	
	public static final boolean DEBUG = true;
	public static final String DB_NAME = "vigyMobileDB.db";
	public static final int DB_VERSION = 1;
	
	public static final String DB_USERS = "users";
	public static final String DB_USERS_USER_ID = "user_id";
	public static final String DB_USERS_USER_MAIL = "user_mail";
	public static final String DB_USERS_USER_PASSWORD = "user_password";
	public static final String DB_USERS_USER_NICKNAME = "user_nickname";
	
	/**
	 * Generate create statement.
	 * @param tableName
	 * @param map
	 * @return
	 */
	public static String generateCreateStatement(String tableName, LinkedHashMap<String, String> map) {
		String ret = "CREATE TABLE " + tableName + " (";
		for(Map.Entry<String, String> entry : map.entrySet()) {
			ret += entry.getKey() + " " + entry.getValue() + ", ";
		}
		ret += ")";
		ret = Utils.removeLastOccuranceInString(ret, ",");
		return ret;
	}

	/**
	 * Gets absolute path to database.
	 * @return
	 */
	public static String getDbPath() {
		
		if (DEBUG == true) {
			return Environment.getExternalStorageDirectory()
					+ File.separator + "DB" + File.separator + DB_NAME;
		} else {
			return DB_NAME;
		}
	}
	
}