package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class UpdateTrainingException extends Exception {

	private static final long serialVersionUID = 2832321173323793573L;

	public UpdateTrainingException(String msg) {
		super(msg);
	}
	
	public UpdateTrainingException(Exception e) {
		super(e);
	}
	
}
