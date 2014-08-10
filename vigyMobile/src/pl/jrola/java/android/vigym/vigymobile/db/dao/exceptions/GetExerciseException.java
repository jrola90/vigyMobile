package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class GetExerciseException extends Exception {

	private static final long serialVersionUID = -6186727452101914435L;

	public GetExerciseException(String msg) {
		super(msg);
	}
	
	public GetExerciseException(Exception e) {
		super(e);
	}
	
}
