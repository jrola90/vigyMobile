package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class DeleteProfileInformationValueException extends Exception {

	private static final long serialVersionUID = 5537258613174548596L;

	public DeleteProfileInformationValueException(String msg) {
		super(msg);
	}
	
	public DeleteProfileInformationValueException(Exception e) {
		super(e);
	}	
}
