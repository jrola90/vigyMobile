package pl.jrola.java.android.vigym.vigymobile.db.dao;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;

public abstract class DAOFactory {

	public static UsersDAO createUsersDAO (DbHelper databaseHelper) {
		return new UsersDAOImpl(databaseHelper);
	}
	
	public static ProfileInformationDAO createProfileInformationDAO (DbHelper databaseHelper) {
		return new ProfileInformationDAOImpl(databaseHelper);
	}	
	
	public static ProfileInformationValuesDAO createProfileInformationValuesDAO(DbHelper databaseHelper) {
		return new ProfileInformationValuesDAOImpl(databaseHelper);
	}
	
	public static UnitsDAO createUnitsDAO(DbHelper databaseHelper) {
		return new UnitsDAOImpl(databaseHelper);
	}
}