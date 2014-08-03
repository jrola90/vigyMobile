package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class UpdateProfileInformationValueException extends Exception {

	private static final long serialVersionUID = 5838064927010859999L;

	public UpdateProfileInformationValueException(String msg) {
		super(msg);
	}
	
	public UpdateProfileInformationValueException(Exception e) {
		super(e);
	}
}
