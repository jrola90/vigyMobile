package pl.jrola.java.android.vigym.vigymobile;

import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;

public class LoggedUserSingleton {

	private static LoggedUserSingleton INSTANCE;
	
	public static LoggedUserSingleton getInstance() {
		
		if (INSTANCE == null)
			INSTANCE = new LoggedUserSingleton();
			
		return INSTANCE;
	}
	
	private LoggedUserSingleton() {
		
	}

	private static UserTransferObject userTransferObject;

	public static UserTransferObject getUserTransferObject() {
		getInstance();
		return userTransferObject;
	}

	public static void setUserTransferObject(UserTransferObject userTransferObject) {
		getInstance();
		LoggedUserSingleton.userTransferObject = userTransferObject;
	}
}