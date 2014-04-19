package pl.jrola.java.android.vigym.vigymobile.db.dao;

import pl.jrola.java.android.vigym.vigymobile.utils.DatabaseHelper;

public abstract class DAOFactory {

	public static UsersDAO createUsersDAO (DatabaseHelper databaseHelper) {
		return new UsersDAO(databaseHelper);
	}
	
}
