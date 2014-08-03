package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class ProfileInformationGetListException extends Exception {

	private static final long serialVersionUID = 3238056695141872246L;

	public ProfileInformationGetListException(String msg) {
		super(msg);
	}
	
	public ProfileInformationGetListException(Exception e) {
		super(e);
	}
}
