package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class UnitGetException extends Exception {

	private static final long serialVersionUID = 4906832458235936254L;

	public UnitGetException(String msg) {
		super(msg);
	}
	
	public UnitGetException(Exception e) {
		super(e);
	}
}
