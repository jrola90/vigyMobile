package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class ProfileInformationUpdateException extends Exception {
	private static final long serialVersionUID = -9071749605404081160L;

	public ProfileInformationUpdateException(String msg) {
		super(msg);
	}
	
	public ProfileInformationUpdateException(Exception e) {
		super(e);
	}
}
