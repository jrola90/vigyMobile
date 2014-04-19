package pl.jrola.java.android.vigym.vigymobile.utils;

import java.util.LinkedHashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

public class DatabaseHelper extends SQLiteOpenHelper {

	private SQLiteDatabase db;

	public DatabaseHelper(Context context) {

		super(context, DBUtils.getDbPath(), null, DBUtils.DB_VERSION);

	}

	public long insert(String table, String nullColumnHack, ContentValues values) {
		return db.insert(table, nullColumnHack, values);
	}

	public int delete(String table, String whereClause, String[] whereArgs) {

		return db.delete(table, whereClause, whereArgs);
	}

	public Cursor query(boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit,
			CancellationSignal cancellationSignal) {
		return db.query(distinct, table, columns, selection, selectionArgs, groupBy,
				having, orderBy, limit, cancellationSignal);
	}

	public DatabaseHelper open() {
		try {
			db = this.getWritableDatabase();
		} catch (SQLException e) {
			return null;
		}
		return this;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBUtils.generateCreateStatement(DBUtils.DB_USERS,
				new LinkedHashMap<String, String>() {
					{
						put(DBUtils.DB_USERS_USER_ID, DbDataTypes.INTEGER + " "
								+ DbDataOptions.AUTOINCREMENT);
						put(DBUtils.DB_USERS_USER_MAIL, DbDataTypes.TEXT + "");
						put(DBUtils.DB_USERS_USER_PASSWORD, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DBUtils.DB_USERS_USER_NICKNAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
					}
				}));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void execSQL(String sql) {
		db.execSQL(sql);
	}
}