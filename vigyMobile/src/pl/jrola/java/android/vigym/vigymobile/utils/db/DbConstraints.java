package pl.jrola.java.android.vigym.vigymobile.utils.db;

public enum DbConstraints {

	PRIMARY_KEY, FOREIGN_KEY;

	private DbConstraints constraint;
	private String columnChildTable;
	private String parentTable;
	private String columnParentTable;

	public String getColumnChildTable() {
		return columnChildTable;
	}

	public void setColumnChildTable(String columnChildTable) {
		this.columnChildTable = columnChildTable;
	}

	public String getParentTable() {
		return parentTable;
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	public String getColumnParentTable() {
		return columnParentTable;
	}

	public void setColumnParentTable(String columnParentTable) {
		this.columnParentTable = columnParentTable;
	}

	public DbConstraints getConstraint() {
		return constraint;
	}

	public void setConstraint(DbConstraints constraint) {
		this.constraint = constraint;
	}

	public String toString() {

		if (constraint == DbConstraints.PRIMARY_KEY) {
			return DbUtils.PK + "(" + columnParentTable + ")";
		} else if (constraint == DbConstraints.FOREIGN_KEY) {
			return DbUtils.FK + "(" + columnChildTable + ") " + DbUtils.REF
					+ " " + parentTable + "(" + columnParentTable + ")";
		}

		else
			return null;
	}

}
