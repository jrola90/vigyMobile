package pl.jrola.java.android.vigym.vigymobile.utils.db;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

public class DbHelper extends SQLiteOpenHelper {

	private SQLiteDatabase db;
	private Context context;

	public DbHelper(Context context) {
		super(context, DbUtils.getDbPath(), null, DbUtils.DB_VERSION);
		this.context = context;
	}

	private boolean initDatabaseHelper()
			throws DatabaseHelperInitialiseException {

		try {
			if (this.db == null) {
				this.db = open();
			}
		} catch (Exception e) {
			Utils.logError(e);
			throw new DatabaseHelperInitialiseException(e);
		}

		return true;
	}

	public void releaseDatabaseHelper() {
		if (this.db != null) {
			this.db.close();
			this.db = null;
		}
	}

	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) throws DatabaseHelperInitialiseException {
		if (initDatabaseHelper() == true)
			return db.update(table, values, whereClause, whereArgs);
		else
			return 0;
	}

	public long insert(String table, String nullColumnHack, ContentValues values)
			throws DatabaseHelperInitialiseException {
		if (initDatabaseHelper() == true)
			return db.insert(table, nullColumnHack, values);
		else
			return 0;
	}

	public int delete(String table, String whereClause, String[] whereArgs)
			throws DatabaseHelperInitialiseException {
		if (initDatabaseHelper() == true)
			return db.delete(table, whereClause, whereArgs);
		else
			return 0;
	}

	public Cursor query(boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, Integer limit,
			CancellationSignal cancellationSignal)
			throws DatabaseHelperInitialiseException {
		if (initDatabaseHelper() == true)
			return db
					.query(distinct, table, columns, selection, selectionArgs,
							groupBy, having, orderBy, limit == null ? null
									: limit.toString(), cancellationSignal);
		else
			return null;
	}

	private SQLiteDatabase open() {
		try {
			db = this.getWritableDatabase();
		} catch (SQLException e) {
			Utils.logError(e);
			return null;
		}
		return db;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DbUtils.getCreateTableUsersStatement());
			db.execSQL(DbUtils.getCreateTableProfileInformationStatement());
			db.execSQL(DbUtils
					.getCreateTableProfileInformationValuesStatement());
			db.execSQL(DbUtils.getCreateTableUnitsStatement());
			db.execSQL(DbUtils.getCreateTableExercisesStatement());
			db.execSQL(DbUtils.getCreateTableTrainingStatement());

			execInsert(db, DbUtils.getInsertUnitsStatement(context),
					DbUtils.DB_TABLE_UNITS);
			execInsert(db, DbUtils.getInsertExercisesStatement(context),
					DbUtils.DB_TABLE_EXERCISES);
		} catch (Exception e) {
			Utils.logError(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void execSQL(String sql) throws DatabaseHelperInitialiseException {
		if (initDatabaseHelper() == true)
			db.execSQL(sql);
	}

	private void execInsert(SQLiteDatabase db,
			List<ContentValues> contentValueList, String tableName)
			throws DatabaseHelperInitialiseException {
		if (contentValueList != null) {
			for (ContentValues cv : contentValueList) {
				db.insert(tableName, null, cv);
			}
		}
	}
}