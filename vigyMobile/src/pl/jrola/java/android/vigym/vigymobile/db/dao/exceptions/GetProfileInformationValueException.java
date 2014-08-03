package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class GetProfileInformationValueException extends Exception {

	private static final long serialVersionUID = -3829190843008183424L;

	public GetProfileInformationValueException(String msg) {
		super(msg);
	}
	
	public GetProfileInformationValueException(Exception e) {
		super(e);
	}	
	
}
