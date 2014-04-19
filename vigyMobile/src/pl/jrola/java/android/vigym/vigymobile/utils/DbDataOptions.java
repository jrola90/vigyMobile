package pl.jrola.java.android.vigym.vigymobile.utils;

public enum DbDataOptions {
	
	AUTOINCREMENT("AUTOINCREMENT"),
	NOT_NULL("NOT NULL"),
	PRIMARY_KEY("PRIMARY KEY");
	
	DbDataOptions(String value) {
		this.value = value;
	}
	
	private String value;

	@Override
	public String toString() {
		return value;
	}
	
}
