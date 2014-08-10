package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class AddTrainingException extends Exception {

	private static final long serialVersionUID = -9097393475039101015L;

	public AddTrainingException(String msg) {
		super(msg);
	}
	
	public AddTrainingException(Exception e) {
		super(e);
	}
	
}