package pl.jrola.java.android.vigym.vigymobile.utils;

import java.util.Map;

/**
 * Class which represents where clause.
 */
public class WhereClauseHelper {

	private String whereStatement = "";
	private String[] whereStatementValues;
	private DbConditionals dbConditional;
	private boolean caseSensitive = true;

	public WhereClauseHelper() {

	}

	public WhereClauseHelper(String whereStatement,
			String[] whereStatementValues) {
		super();
		this.whereStatement = whereStatement;
		this.whereStatementValues = whereStatementValues;
	}

	public String getWhereStatement() {
		return whereStatement;
	}

	public void setWhereStatement(String whereStatement) {
		this.whereStatement = whereStatement;
	}

	public String[] getWhereStatementValues() {
		return whereStatementValues;
	}

	public void setWhereStatementValues(String[] whereStatementValues) {
		this.whereStatementValues = whereStatementValues;
	}

	public DbConditionals getDbConditional() {
		return dbConditional;
	}

	public void setDbConditional(DbConditionals dbConditional) {
		this.dbConditional = dbConditional;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void setWhereStatementFromMap(Map<String, String> map) {

		int mapSize = map.size();
		this.whereStatementValues = new String[mapSize];
		int i = 0;

		if (caseSensitive == true) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				this.whereStatement += entry.getKey() + " = ? "
						+ this.dbConditional + " ";
				this.whereStatementValues[i] = entry.getValue();
				i++;
			}
		} else {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				this.whereStatement += "upper(" + entry.getKey() + ") = upper(?) "
						+ this.dbConditional + " ";
				this.whereStatementValues[i] = entry.getValue();
				i++;
			}
		}

		this.whereStatement = Utils.removeLastOccuranceInString(
				this.whereStatement, " " + this.dbConditional + " ");

	}

}