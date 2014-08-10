package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class GetTrainingListException extends Exception {

	private static final long serialVersionUID = 3191866281808161434L;

	public GetTrainingListException(String msg) {
		super(msg);
	}
	
	public GetTrainingListException(Exception e) {
		super(e);
	}
}
