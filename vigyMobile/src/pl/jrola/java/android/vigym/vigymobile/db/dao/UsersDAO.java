package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddUserException;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;

/**
 * Data access for User interface.
 *
 */
public interface UsersDAO {

	/**
	 * Adds an user.
	 * Important: this function does not set field user_id.
	 * @param userTransferObject user to be added 
	 * @return added user
	 * @throws AddUserException 
	 */
	UserTransferObject addUser(UserTransferObject userTransferObject) throws AddUserException;
	
	/**
	 * Deletes an user.
	 * @param id id of the user to be deleted
	 * @return true if operation succeed, otherwise false 
	 * @throws DatabaseHelperInitialiseException 
	 */
	boolean deleteUser(Long id) throws DatabaseHelperInitialiseException;
	
	/**
	 * Gets an user.
	 * @param userTransferObject
	 * @return
	 * @throws DatabaseHelperInitialiseException 
	 */
	List<UserTransferObject> getUsers(UserTransferObject userTransferObject) throws DatabaseHelperInitialiseException;
	
	/**
	 * Updates an user.
	 * @param userTransferObject
	 * @return
	 */
	UserTransferObject updateUser(UserTransferObject userTransferObject);

	List<UserTransferObject> getUsers(UserTransferObject userTransferObject,
			DbConditionals dbCond) throws DatabaseHelperInitialiseException;
	
	boolean changePassword(long user_id, String newPassword);
	
}
