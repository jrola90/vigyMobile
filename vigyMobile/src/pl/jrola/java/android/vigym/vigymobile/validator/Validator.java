package pl.jrola.java.android.vigym.vigymobile.validator;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAOImpl;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;
import android.view.View;

public abstract class Validator implements Validable {
	
	protected View uiComponent;
	protected Validator nextValidator;
	protected List<String> errorMessageList = new ArrayList<String>();
	
	public List<String> getErrorMessageList() {
		return errorMessageList;
	}
	
	public String getErrorMessages() {
		StringBuilder sb = new StringBuilder();
		for(String item : errorMessageList) {
			sb.append(item);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
	
	public Validator(View uiComponent) {
		this.uiComponent = uiComponent;
	}

	public Validator getNextValidator() {
		return nextValidator;
	}

	public void setNextValidator(Validator nextValidator) {
		this.nextValidator = nextValidator;
	}

	protected boolean isEmailValid(String email) {
		if (email == null)
			return false;
		else
			return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	protected boolean isUsernameVacant(String username, DbHelper databaseHelper)
			throws DatabaseHelperInitialiseException {

		UsersDAOImpl userDAO = new UsersDAOImpl(databaseHelper);

		UserTransferObject searchedTransferObject = new UserTransferObject();
		searchedTransferObject.setUser_nickname(username);

		List<UserTransferObject> userTransferObjectList = userDAO
				.getUsers(searchedTransferObject);

		if (userTransferObjectList.size() == 0)
			return true;
		else
			return false;
	}
	
	public abstract boolean validate();

	public void clearErrorMessage() {
		errorMessageList.clear();
	}
}