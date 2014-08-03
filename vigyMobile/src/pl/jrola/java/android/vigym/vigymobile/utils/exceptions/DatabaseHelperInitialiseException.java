package pl.jrola.java.android.vigym.vigymobile.utils.exceptions;

public class DatabaseHelperInitialiseException extends Exception {
	public DatabaseHelperInitialiseException(String msg) {
		super(msg);
	}
	
	public DatabaseHelperInitialiseException(Exception e) {
		super(e);
	}
}
