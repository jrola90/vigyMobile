package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddUserException;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.db.WhereClauseHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class UsersDAOImpl implements UsersDAO {

	private DbHelper db;

	public UsersDAOImpl(DbHelper db) {
		this.db = db;
	}

	@Override
	public UserTransferObject addUser(UserTransferObject userTransferObject) throws AddUserException {

		ContentValues contentValues = new ContentValues();
		contentValues.put(DbUtils.DB_COLUMN_USERS_USER_NICKNAME,
				userTransferObject.getUser_nickname());
		contentValues.put(DbUtils.DB_COLUMN_USERS_USER_PASSWORD,
				userTransferObject.getUser_password());
		contentValues.put(DbUtils.DB_COLUMN_USERS_USER_MAIL,
				userTransferObject.getUser_mail());
		try {
			db.insert(DbUtils.DB_TABLE_USERS, null, contentValues);
		} catch (Exception e) {
			Utils.logError(e);
			throw new AddUserException(e);
		}

		try {
			userTransferObject = this.getUsers(userTransferObject).get(0);
		} catch (DatabaseHelperInitialiseException e) {
			Utils.logError(e);
			throw new AddUserException(e);
		}
		
		
		return userTransferObject;
	}

	@Override
	public boolean deleteUser(Long id) throws DatabaseHelperInitialiseException {

		String whereClause = DbUtils.DB_COLUMN_USERS_USER_ID + "= ?";
		if (db.delete(DbUtils.DB_TABLE_USERS, whereClause,
				new String[] { Long.toString(id) }) == 1)
			return true;
		return false;
	}

	@Override
	public List<UserTransferObject> getUsers(
			UserTransferObject userTransferObject, DbConditionals dbCond) throws DatabaseHelperInitialiseException {

		List<UserTransferObject> userTransferObjectList = new ArrayList<UserTransferObject>();
		Cursor cursor = null;
		try {
			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setDbConditional(dbCond);
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setWhereStatementFromMap(userTransferObject
					.getPropertiesMap());
			cursor = db.query(true, DbUtils.DB_TABLE_USERS, null,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues(), null, null,
					null, null, null);
		} catch (IllegalAccessException e) {
			Log.e(getClass().getName(), e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.e(getClass().getName(), e.getMessage());
		}

		while (cursor.moveToNext())
			userTransferObjectList.add(new UserTransferObject(cursor));

		return userTransferObjectList;
	}

	public List<UserTransferObject> getUsers(
			UserTransferObject userTransferObject) throws DatabaseHelperInitialiseException {
		return getUsers(userTransferObject, DbConditionals.AND);
	}

	@Override
	public UserTransferObject updateUser(UserTransferObject userTransferObject) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean changePassword(long user_id, String newPassword) {
		try {

			UserTransferObject uto = new UserTransferObject();
			uto.setUser_id(user_id);

			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setDbConditional(DbConditionals.AND);
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setWhereStatementFromMap(uto.getPropertiesMap());

			ContentValues cv = new ContentValues();
			cv.put(DbUtils.DB_COLUMN_USERS_USER_PASSWORD, newPassword);

			int ret = db.update(DbUtils.DB_TABLE_USERS, cv,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues());

			if (ret == 1)
				return true;
			else
				return false;

		} catch (Exception e) {
			Log.e(getClass().getName(), e.getMessage());
			return false;
		}
	}
}