package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class ProfileInformationDeleteException extends Exception {

	private static final long serialVersionUID = -7357727215093702293L;

	public ProfileInformationDeleteException(Exception e) {
		super(e);
	}

	public ProfileInformationDeleteException(String msg) {
		super(msg);
	}
}
