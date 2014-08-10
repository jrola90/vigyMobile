package pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions;

public class DeleteTrainingException extends Exception {

	private static final long serialVersionUID = -9207282902243092605L;

	public DeleteTrainingException(String msg) {
		super(msg);
	}

	public DeleteTrainingException(Exception e) {
		super(e);
	}
	
}
