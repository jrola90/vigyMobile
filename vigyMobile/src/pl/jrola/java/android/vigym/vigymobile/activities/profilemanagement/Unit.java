package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

public enum Unit {
	
	NO_UNIT(""),
	PERCENT("%");
	
	private String unit;
	
	private Unit(String unit) {
		this.unit = unit;
	}
	
	public String toString() {
		return unit;
	}
	
}
