package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;

/**
 * Data access for User interface.
 *
 */
public interface UsersDAOInterface {

	/**
	 * Adds an user.
	 * @param userTransferObject user to be added 
	 * @return added user
	 */
	UserTransferObject addUser(UserTransferObject userTransferObject);
	
	/**
	 * Deletes an user.
	 * @param id id of the user to be deleted
	 * @return true if operation succeed, otherwise false 
	 */
	boolean deleteUser(Long id);
	
	/**
	 * Deletes an user.
	 * @param userTransferObject user to be deleted
	 * @return true if operation succeed, otherwise false
	 */
	boolean deleteUser(UserTransferObject userTransferObject);
	
	/**
	 * Gets an user.
	 * @param id user id
	 * @return User object
	 */
	UserTransferObject getUser(Long id);
	
	/**
	 * Gets an user.
	 * @param userTransferObject
	 * @return
	 */
	List<UserTransferObject> getUsers(UserTransferObject userTransferObject);
	
	/**
	 * Updates an user.
	 * @param userTransferObject
	 * @return
	 */
	UserTransferObject updateUser(UserTransferObject userTransferObject);
	
}
