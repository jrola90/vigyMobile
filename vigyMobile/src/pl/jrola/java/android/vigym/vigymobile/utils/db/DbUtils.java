package pl.jrola.java.android.vigym.vigymobile.utils.db;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;

public abstract class DbUtils {

	public static final boolean DEBUG = true;
	public static final String DB_NAME = "vigyMobileDB.db";
	public static final int DB_VERSION = 1;

	public static final String DB_TABLE_USERS = "users";
	public static final String DB_COLUMN_USERS_USER_ID = "user_id";
	public static final String DB_COLUMN_USERS_USER_MAIL = "user_mail";
	public static final String DB_COLUMN_USERS_USER_PASSWORD = "user_password";
	public static final String DB_COLUMN_USERS_USER_NICKNAME = "user_nickname";

	public static final String DB_TABLE_PROF_INFO = "profile_information";
	public static final String DB_COLUMN_PROF_INFO_ID = "prof_info_id";
	public static final String DB_COLUMN_PROF_INFO_NAME = "prof_info_name";
	public static final String DB_COLUMN_PROF_INFO_DESC = "prof_info_desc";
	public static final String DB_COLUMN_PROF_INFO_USER_ID = "prof_info_user_id";

	public static final String DB_TABLE_PROF_VALUES = "profile_information_values";
	public static final String DB_COLUMN_PROF_INFO_VAL_ID = "prof_info_val_id";
	public static final String DB_COLUMN_PROF_INFO_VAL_DATE = "prof_info_val_date";
	public static final String DB_COLUMN_PROF_INFO_VAL_VALUE = "prof_info_val_value";
	public static final String DB_COLUMN_PROF_INFO_VAL_UNIT_ID = "prof_info_val_unit_id";
	public static final String DB_COLUMN_PROF_INFO_VAL_INFO_ID = "prof_info_val_info_id";

	public static final String DB_TABLE_UNITS = "units";
	public static final String DB_COLUMN_UNIT_ID = "unit_id";
	public static final String DB_COLUMN_UNIT_NAME = "unit_name";
	public static final String DB_COLUMN_UNIT_DESC = "unit_desc";
	public static final String DB_COLUMN_UNIT_SHORT_NAME = "unit_short_name";

	public static final String DB_TABLE_EXERCISES = "exercises";
	public static final String DB_COLUMN_EXERCISE_ID = "exercise_id";
	public static final String DB_COLUMN_EXERCISE_NAME = "exercise_name";
	public static final String DB_COLUMN_EXERCISE_DESC = "exercise_desc";

	public static final String DB_TABLE_TRAININGS = "trainings";
	public static final String DB_COLUMN_TRAINING_ID = "training_id";
	public static final String DB_COLUMN_TRAINING_COUNT = "training_count";
	public static final String DB_COLUMN_TRAINING_VALUE = "training_value";
	public static final String DB_COLUMN_TRAINING_DATE = "training_date";
	public static final String DB_COLUMN_TRAINING_EXERCISE_ID = "training_exercise_id";

	public static final String PK = "PRIMARY KEY";
	public static final String FK = "FOREIGN KEY";
	public static final String REF = "REFERENCES";

	/**
	 * Generate create statement.
	 * 
	 * @param tableName
	 * @param map
	 * @return
	 */
	public static String generateCreateStatement(String tableName,
			LinkedHashMap<String, String> map) {
		String ret = "CREATE TABLE " + tableName + " (";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			ret += entry.getKey() + " " + entry.getValue() + ", ";
		}
		ret += ")";
		ret = Utils.removeLastOccuranceInString(ret, ",");
		return ret;
	}

	/**
	 * Gets absolute path to database.
	 * 
	 * @return
	 */
	public static String getDbPath() {

		if (DEBUG == true) {
			return Environment.getExternalStorageDirectory() + File.separator
					+ "DB" + File.separator + DB_NAME;
		} else {
			return DB_NAME;
		}
	}

	public static String getCreateTableUsersStatement() {
		return DbUtils.generateCreateStatement(DbUtils.DB_TABLE_USERS,
				new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = 8572934663485817276L;

					{
						put(DbUtils.DB_COLUMN_USERS_USER_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.PRIMARY_KEY + " "
										+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_USERS_USER_MAIL, DbDataTypes.TEXT
								+ "");
						put(DbUtils.DB_COLUMN_USERS_USER_PASSWORD,
								DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_USERS_USER_NICKNAME,
								DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
					}
				});
	}

	public static String getCreateTableProfileInformationStatement() {
		final DbConstraints constraintFK = DbConstraints.FOREIGN_KEY;
		constraintFK.setConstraint(DbConstraints.FOREIGN_KEY);
		constraintFK.setParentTable(DbUtils.DB_TABLE_USERS);
		constraintFK.setColumnParentTable(DbUtils.DB_COLUMN_USERS_USER_ID);
		constraintFK.setColumnChildTable(DbUtils.DB_COLUMN_PROF_INFO_USER_ID);

		return DbUtils.generateCreateStatement(DbUtils.DB_TABLE_PROF_INFO,
				new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = -7578713567218804600L;

					{
						put(DbUtils.DB_COLUMN_PROF_INFO_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.PRIMARY_KEY + " "
								+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_PROF_INFO_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_DESC, DbDataTypes.TEXT
								+ "");
						put(DbUtils.DB_COLUMN_PROF_INFO_USER_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put(" ", constraintFK.toString());
					}
				});
	}

	public static String getCreateTableProfileInformationValuesStatement() {

		final DbConstraints constraintFK = DbConstraints.FOREIGN_KEY;
		// constraintFK.setConstraint(DbConstraints.FOREIGN_KEY);
		constraintFK.setParentTable(DbUtils.DB_TABLE_PROF_INFO);
		constraintFK
				.setColumnChildTable(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID);
		constraintFK.setColumnParentTable(DbUtils.DB_COLUMN_PROF_INFO_ID);
		final String fk = constraintFK.toString();

		final DbConstraints constraintFK2 = DbConstraints.PRIMARY_KEY;
		// constraintFK2.setConstraint(DbConstraints.FOREIGN_KEY);
		constraintFK2.setParentTable(DbUtils.DB_TABLE_PROF_VALUES);
		constraintFK2.setColumnChildTable(DbUtils.DB_COLUMN_UNIT_ID);
		constraintFK2
				.setColumnParentTable(DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID);
		final String fk2 = constraintFK.toString();

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_PROF_VALUES,
				new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = -4149335393516774279L;

					{
						put(DbUtils.DB_COLUMN_PROF_INFO_VAL_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.PRIMARY_KEY + " "
										+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE,
								DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE,
								DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put(" ", fk);
						put("  ", fk2);
					}
				});

		return generatedStatement;

	}

	public static String getCreateTableUnitsStatement() {

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_UNITS, new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = 3719050435059773077L;

					{
						put(DbUtils.DB_COLUMN_UNIT_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.PRIMARY_KEY + " "
								+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_UNIT_NAME, DbDataTypes.TEXT + " "
								+ DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_UNIT_DESC, DbDataTypes.TEXT + "");
						put(DbUtils.DB_COLUMN_UNIT_SHORT_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
					}
				});

		return generatedStatement;
	}

	public static List<ContentValues> getInsertUnitsStatement(Context context) {
		List<ContentValues> cvList = new ArrayList<ContentValues>();

		String[] unitArray = context.getResources().getStringArray(
				R.array.units);
		for (String unit : unitArray) {
			String[] splitted = getValuesFromString(unit, ";");
			ContentValues weight = new ContentValues();
			weight.put(DbUtils.DB_COLUMN_UNIT_NAME, splitted[0]);
			weight.put(DbUtils.DB_COLUMN_UNIT_DESC, splitted[1]);
			weight.put(DbUtils.DB_COLUMN_UNIT_SHORT_NAME, splitted[2]);
			cvList.add(weight);
		}

		return cvList;
	}

	public static List<ContentValues> getInsertExercisesStatement(
			Context context) {
		List<ContentValues> cvList = new ArrayList<ContentValues>();

		String[] exercisesArray = context.getResources().getStringArray(
				R.array.exercises);
		for (String exercise : exercisesArray) {
			String[] splitted = getValuesFromString(exercise, ";");
			ContentValues weight = new ContentValues();
			weight.put(DbUtils.DB_COLUMN_EXERCISE_NAME, splitted[0]);
			weight.put(DbUtils.DB_COLUMN_EXERCISE_DESC, splitted[1]);
			cvList.add(weight);
		}

		return cvList;
	}

	public static String getCreateTableExercisesStatement() {
		String stmt = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_EXERCISES,
				new LinkedHashMap<String, String>() {

					private static final long serialVersionUID = 2975992523090140330L;

					{
						put(DbUtils.DB_COLUMN_EXERCISE_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.PRIMARY_KEY + " "
								+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_EXERCISE_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_EXERCISE_DESC, DbDataTypes.TEXT
								+ "");
					}
				});

		return stmt;
	}

	public static String getCreateTableTrainingStatement() {
		final DbConstraints constraintFK = DbConstraints.FOREIGN_KEY;
		constraintFK.setParentTable(DbUtils.DB_TABLE_EXERCISES);
		constraintFK
				.setColumnChildTable(DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID);
		constraintFK.setColumnParentTable(DbUtils.DB_COLUMN_EXERCISE_ID);
		final String fk = constraintFK.toString();

		String stmt = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_TRAININGS,
				new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = 114098001845612144L;

					{
						put(DbUtils.DB_COLUMN_TRAINING_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.PRIMARY_KEY + " "
								+ DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_TRAINING_DATE, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_TRAINING_VALUE, DbDataTypes.REAL
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_TRAINING_COUNT,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put(" ", fk);
					}
				});

		return stmt;
	}

	public static String[] getValuesFromString(String line, String separator) {
		return line.split(separator, -1);
	}
}