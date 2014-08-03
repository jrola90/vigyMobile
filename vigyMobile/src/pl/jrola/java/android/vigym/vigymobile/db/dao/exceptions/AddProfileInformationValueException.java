package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class AddProfileInformationValueException extends Exception {

	private static final long serialVersionUID = -2748698239965939840L;

	public AddProfileInformationValueException(String msg) {
		super(msg);
	}

	public AddProfileInformationValueException(Exception e) {
		super(e);
	}
}