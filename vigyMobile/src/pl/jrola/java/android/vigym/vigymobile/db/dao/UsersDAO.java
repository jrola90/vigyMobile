package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.DBUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.DatabaseHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.WhereClauseHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class UsersDAO implements UsersDAOInterface {

	private DatabaseHelper db;

	public UsersDAO(DatabaseHelper db) {
		this.db = db;
	}

	@Override
	public UserTransferObject addUser(UserTransferObject userTransferObject) {

		ContentValues contentValues = new ContentValues();
		contentValues.put(DBUtils.DB_USERS_USER_NICKNAME,
				userTransferObject.getUser_nickname());
		contentValues.put(DBUtils.DB_USERS_USER_PASSWORD,
				Utils.generateSHA1Hash(userTransferObject.getUser_password()));
		contentValues.put(DBUtils.DB_USERS_USER_MAIL,
				userTransferObject.getUser_mail());
		try {
			db.insert(DBUtils.DB_USERS, null, contentValues);
		} catch (Exception e) {
			Log.e(getClass().getName(), e.getMessage());
			return null;
		}
			
		return userTransferObject;
	}

	@Override
	public boolean deleteUser(Long id) {

		String whereClause = DBUtils.DB_USERS_USER_ID + "= ?";
		if (db.delete(DBUtils.DB_USERS, whereClause,
				new String[] { Long.toString(id) }) == 1)
			return true;
		return false;
	}

	@Override
	public boolean deleteUser(UserTransferObject userTransferObject) {

		// TODO:
		return false;
	}

	@Override
	public UserTransferObject getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserTransferObject> getUsers(UserTransferObject userTransferObject) {

		List<UserTransferObject> userTransferObjectList = new ArrayList<UserTransferObject>();
		Cursor cursor = null;
		try {
			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setDbConditional(DbConditionals.OR);
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setWhereStatementFromMap(userTransferObject
					.getPropertiesMap());
			cursor = db.query(true, DBUtils.DB_USERS, null, whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues(), null, null, null, null, null);
		} catch (IllegalAccessException e) {
			Log.e(getClass().getName(), e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.e(getClass().getName(), e.getMessage());
		}

		while(cursor.moveToNext()) 
			userTransferObjectList.add(new UserTransferObject(cursor));
		
		return userTransferObjectList;
	}

	@Override
	public UserTransferObject updateUser(UserTransferObject userTransferObject) {
		// TODO Auto-generated method stub
		return null;
	}
}