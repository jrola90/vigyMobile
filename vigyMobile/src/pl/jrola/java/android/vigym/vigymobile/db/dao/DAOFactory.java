package pl.jrola.java.android.vigym.vigymobile.db.dao;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;

public abstract class DAOFactory {

	public static UsersDAO createUsersDAO (DbHelper databaseHelper) {
		return new UsersDAOImpl(databaseHelper);
	}
	
	public static ProfileInformationsDAO createProfileInformationsDAO (DbHelper databaseHelper) {
		return new ProfileInformationsDAOImpl(databaseHelper);
	}	
	
	public static ProfileInformationValuesDAO createProfileInformationValuesDAO(DbHelper databaseHelper) {
		return new ProfileInformationValuesDAOImpl(databaseHelper);
	}
	
	public static UnitsDAO createUnitsDAO(DbHelper databaseHelper) {
		return new UnitsDAOImpl(databaseHelper);
	}
	
	public static ExercisesDAO createExercisesDAO(DbHelper databaseHelper) {
		return new ExercisesDAOImpl(databaseHelper);
	}
	
	public static TrainingsDAO createTrainingsDAO(DbHelper databaseHelper) {
		return new TrainingsDAOImpl(databaseHelper);
	}
}