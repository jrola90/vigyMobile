package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class AddUserException extends Exception {

	private static final long serialVersionUID = 4352045546660033415L;

	public AddUserException(String msg) {
		super(msg);
	}
	
	public AddUserException(Exception e) {
		super(e);
	}
}
