package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class GetExerciseListException extends Exception {

	private static final long serialVersionUID = -756597240897783156L;

	public GetExerciseListException(String msg) {
		super(msg);
	}

	public GetExerciseListException(Exception e) {
		super(e);
	}

}
