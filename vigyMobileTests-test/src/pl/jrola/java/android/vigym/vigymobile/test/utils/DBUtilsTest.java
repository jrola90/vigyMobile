package pl.jrola.java.android.vigym.vigymobile.test.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConstraints;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbDataOptions;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbDataTypes;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.WhereClauseHelper;
import android.content.Context;
import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class DBUtilsTest extends ActivityTestCase {

	@SmallTest
	public void testGenerateCreateStatement() throws Exception {

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_USERS, new LinkedHashMap<String, String>() {
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

		assertEquals(
				"CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_mail TEXT, user_password TEXT NOT NULL, user_nickname TEXT NOT NULL )",
				generatedStatement);

	}

	@SmallTest
	public void testRemoveLastOccuranceInString() throws Exception {

		String st = " , przecinek,";
		st = Utils.removeLastOccuranceInString(st, ",");
		assertEquals(" , przecinek", st);

		String st2 = "WHERE test = ? AND test2 = ? AND ";
		st2 = Utils.removeLastOccuranceInString(st2, " AND ");
		assertEquals("WHERE test = ? AND test2 = ?", st2);

	}

	@SmallTest
	public void testGenerateWhereClause() throws Exception {

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("property", "val1");

		WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
		whereClauseHelper.setDbConditional(DbConditionals.OR);
		whereClauseHelper.setWhereStatementFromMap(map);
		assertEquals("property = ?", whereClauseHelper.getWhereStatement());
		assertEquals(whereClauseHelper.getWhereStatementValues()[0], "val1");

		whereClauseHelper = new WhereClauseHelper();
		whereClauseHelper.setDbConditional(DbConditionals.OR);
		map.clear();
		map.put("prop1", "2");
		map.put("prop2", "test");
		map.put("prop3", "a12");
		whereClauseHelper.setWhereStatementFromMap(map);
		assertEquals("prop1 = ? OR prop2 = ? OR prop3 = ?",
				whereClauseHelper.getWhereStatement());
		assertEquals(whereClauseHelper.getWhereStatementValues()[0], "2");
		assertEquals(whereClauseHelper.getWhereStatementValues()[1], "test");
		assertEquals(whereClauseHelper.getWhereStatementValues()[2], "a12");

		whereClauseHelper = new WhereClauseHelper();
		whereClauseHelper.setDbConditional(DbConditionals.OR);
		whereClauseHelper.setCaseSensitive(false);
		map.clear();
		map.put("prop1", "2");
		map.put("prop2", "test");
		whereClauseHelper.setWhereStatementFromMap(map);
		assertEquals("upper(prop1) = upper(?) OR upper(prop2) = upper(?)",
				whereClauseHelper.getWhereStatement());
		assertEquals(whereClauseHelper.getWhereStatementValues()[0], "2");
		assertEquals(whereClauseHelper.getWhereStatementValues()[1], "test");
	}

	@SmallTest
	public void testGenerateCreateStatementWithPrimaryKey() throws Exception {

		final DbConstraints constraint = DbConstraints.PRIMARY_KEY;
		constraint.setConstraint(DbConstraints.PRIMARY_KEY);
		constraint.setColumnParentTable(DbUtils.DB_COLUMN_USERS_USER_ID);

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_USERS, new LinkedHashMap<String, String>() {
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
						put("", constraint.toString());
					}
				});

		assertEquals(
				"CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, user_mail TEXT, user_password TEXT NOT NULL, user_nickname TEXT NOT NULL,  PRIMARY KEY(user_id) )",
				generatedStatement);
	}

	@SmallTest
	public void testGenerateCreateStatementWithForeignKey() throws Exception {
		final DbConstraints constraint = DbConstraints.FOREIGN_KEY;
		constraint.setConstraint(DbConstraints.FOREIGN_KEY);
		constraint.setParentTable(DbUtils.DB_TABLE_USERS);
		constraint.setColumnParentTable(DbUtils.DB_COLUMN_USERS_USER_ID);
		constraint.setColumnChildTable(DbUtils.DB_COLUMN_PROF_INFO_USER_ID);

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_PROF_INFO,
				new LinkedHashMap<String, String>() {
					{
						put(DbUtils.DB_COLUMN_PROF_INFO_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_PROF_INFO_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_DESC, DbDataTypes.TEXT
								+ "");
						put(DbUtils.DB_COLUMN_PROF_INFO_USER_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put("", constraint.toString());
					}
				});

		assertEquals(
				"CREATE TABLE profile_information (prof_info_id INTEGER AUTOINCREMENT, prof_info_name TEXT NOT NULL, prof_info_desc TEXT, prof_info_user_id INTEGER NOT NULL,  FOREIGN KEY(prof_info_user_id) REFERENCES users(user_id) )",
				generatedStatement);
	}

	@SmallTest
	public void testGenerateCreateStatementWithPrimaryAndForeignKeys()
			throws Exception {

		final DbConstraints constraintPK = DbConstraints.PRIMARY_KEY;
		constraintPK.setConstraint(DbConstraints.PRIMARY_KEY);
		constraintPK.setColumnParentTable(DbUtils.DB_COLUMN_PROF_INFO_ID);

		final DbConstraints constraintFK = DbConstraints.FOREIGN_KEY;
		constraintFK.setConstraint(DbConstraints.FOREIGN_KEY);
		constraintFK.setParentTable(DbUtils.DB_TABLE_USERS);
		constraintFK.setColumnParentTable(DbUtils.DB_COLUMN_USERS_USER_ID);
		constraintFK.setColumnChildTable(DbUtils.DB_COLUMN_PROF_INFO_USER_ID);

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_PROF_INFO,
				new LinkedHashMap<String, String>() {
					{
						put(DbUtils.DB_COLUMN_PROF_INFO_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_PROF_INFO_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_PROF_INFO_DESC, DbDataTypes.TEXT
								+ "");
						put(DbUtils.DB_COLUMN_PROF_INFO_USER_ID,
								DbDataTypes.INTEGER + " "
										+ DbDataOptions.NOT_NULL);
						put("", constraintPK.toString());
						put(" ", constraintFK.toString());
					}
				});

		assertEquals(
				"CREATE TABLE profile_information (prof_info_id INTEGER AUTOINCREMENT, prof_info_name TEXT NOT NULL, prof_info_desc TEXT, prof_info_user_id INTEGER NOT NULL,  PRIMARY KEY(prof_info_id),   FOREIGN KEY(prof_info_user_id) REFERENCES users(user_id) )",
				generatedStatement);

	}

	@SmallTest
	public void testGenerateCreateStatementForUnitsTable() throws Exception {

		final DbConstraints constraintPK = DbConstraints.PRIMARY_KEY;
		constraintPK.setConstraint(DbConstraints.PRIMARY_KEY);
		constraintPK.setColumnParentTable(DbUtils.DB_COLUMN_UNIT_ID);

		String generatedStatement = DbUtils.generateCreateStatement(
				DbUtils.DB_TABLE_UNITS, new LinkedHashMap<String, String>() {
					{
						put(DbUtils.DB_COLUMN_UNIT_ID, DbDataTypes.INTEGER
								+ " " + DbDataOptions.AUTOINCREMENT);
						put(DbUtils.DB_COLUMN_UNIT_NAME, DbDataTypes.TEXT + " "
								+ DbDataOptions.NOT_NULL);
						put(DbUtils.DB_COLUMN_UNIT_DESC, DbDataTypes.TEXT + "");
						put(DbUtils.DB_COLUMN_UNIT_SHORT_NAME, DbDataTypes.TEXT
								+ " " + DbDataOptions.NOT_NULL);
						put("", constraintPK.toString());
					}
				});

		assertEquals(
				"CREATE TABLE units (unit_id INTEGER AUTOINCREMENT, unit_name TEXT NOT NULL, unit_desc TEXT, unit_short_name TEXT NOT NULL,  PRIMARY KEY(unit_id) )",
				generatedStatement);
	}

	@SmallTest
	public void testSplitStringArray() throws Exception {

		String testString = "D³ugoœæ;D³ugoœæ;cm";
		String testString2 = ";;";
		String testString3 = "D³ugoœæ;D³ugoœæ;";
		String testString4 = ";D³ugoœæ;cm";

		assertEquals(
				Arrays.toString(new String[] { "D³ugoœæ", "D³ugoœæ", "cm" }),
				Arrays.toString(DbUtils.getValuesFromString(testString, ";")));

		assertEquals(Arrays.toString(new String[] { "", "", "" }),
				Arrays.toString(DbUtils.getValuesFromString(testString2, ";")));

		assertEquals(
				Arrays.toString(new String[] { "D³ugoœæ", "D³ugoœæ", "" }),
				Arrays.toString(DbUtils.getValuesFromString(testString3, ";")));

		assertEquals(Arrays.toString(new String[] { "", "D³ugoœæ", "cm" }),
				Arrays.toString(DbUtils.getValuesFromString(testString4, ";")));

	}
}