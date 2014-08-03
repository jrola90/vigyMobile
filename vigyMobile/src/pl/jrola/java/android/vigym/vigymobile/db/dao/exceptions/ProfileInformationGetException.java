package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class ProfileInformationGetException extends Exception {

	private static final long serialVersionUID = 3206096853940966894L;

	public ProfileInformationGetException(String msg) {
		super(msg);
	}
	
	public ProfileInformationGetException(Exception e) {
		super(e);
	}
	
}
