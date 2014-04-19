package pl.jrola.java.android.vigym.vigymobile.test.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import pl.jrola.java.android.vigym.vigymobile.utils.DBUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.DbDataOptions;
import pl.jrola.java.android.vigym.vigymobile.utils.DbDataTypes;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.WhereClauseHelper;
import android.test.ActivityTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

public class DBUtilsTest extends ActivityTestCase {

	@SmallTest
	public void testGenerateCreateStatement() throws Exception {

		String generatedStatement = DBUtils.generateCreateStatement(
				DBUtils.DB_USERS, new LinkedHashMap<String, String>() {
					{
						put(DBUtils.DB_USERS_USER_ID, DbDataTypes.INTEGER + " " + DbDataOptions.PRIMARY_KEY + " " + DbDataOptions.AUTOINCREMENT);
						put(DBUtils.DB_USERS_USER_MAIL, DbDataTypes.TEXT + "");
						put(DBUtils.DB_USERS_USER_PASSWORD, DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
						put(DBUtils.DB_USERS_USER_NICKNAME, DbDataTypes.TEXT + " " + DbDataOptions.NOT_NULL);
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
		assertEquals("prop1 = ? OR prop2 = ? OR prop3 = ?", whereClauseHelper.getWhereStatement());
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
		assertEquals("upper(prop1) = upper(?) OR upper(prop2) = upper(?)", whereClauseHelper.getWhereStatement());
		assertEquals(whereClauseHelper.getWhereStatementValues()[0], "2");
		assertEquals(whereClauseHelper.getWhereStatementValues()[1], "test");
	}
	
}