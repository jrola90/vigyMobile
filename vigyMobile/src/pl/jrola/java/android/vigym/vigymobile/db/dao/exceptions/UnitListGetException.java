package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class UnitListGetException extends Exception {

	private static final long serialVersionUID = 6897311248233700787L;

	public UnitListGetException(String msg) {
		super(msg);
	}
	
	public UnitListGetException(Exception e) {
		super(e);
	}
}
