package pl.jrola.java.android.vigym.vigymobile.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAO;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import android.app.Activity;
import android.app.AlertDialog;

public abstract class Utils {

	public static String PROFILE_FILE_EXTENSION = ".*xml";
	
	public static boolean isPasswordValid(String password) {

		return true;
	}

	public static boolean isUsernameVacant(String username,
			DatabaseHelper databaseHelper) {

		UsersDAO userDAO = new UsersDAO(databaseHelper);

		UserTransferObject searchedTransferObject = new UserTransferObject();
		searchedTransferObject.setUser_nickname(username);

		List<UserTransferObject> userTransferObjectList = userDAO
				.getUsers(searchedTransferObject);

		if (userTransferObjectList.size() == 0)
			return true;
		else
			return false;
	}

	public static boolean isEmailValid(String email) {
		if (email == null)
			return false;
		else
			return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	public static void showErrorMessage(String msg, Activity activity) {
		AlertDialog.Builder alertMessageBox = new AlertDialog.Builder(activity);
		alertMessageBox.setMessage(msg);
		alertMessageBox.setTitle(R.string.error);
		alertMessageBox.setPositiveButton(R.string.ok, null);
		alertMessageBox.setCancelable(true);
		alertMessageBox.create().show();
	}
	
	public static void showInfoMessage(String msg, Activity activity) {
		AlertDialog.Builder alertMessageBox = new AlertDialog.Builder(activity);
		alertMessageBox.setMessage(msg);
		alertMessageBox.setTitle(R.string.info);
		alertMessageBox.setPositiveButton(R.string.ok, null);
		alertMessageBox.setCancelable(true);
		alertMessageBox.create().show();	
	}

	public static String generateSHA1Hash(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(message.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String removeLastOccuranceInString(String string, String value) {
		int index = string.lastIndexOf(value);
		string = new StringBuilder(string).replace(index, index + value.length(),"").toString();
		return string;
	}

	public static boolean isEmailVacant(String email,
			DatabaseHelper databaseHelper) {
		
		UsersDAO userDAO = new UsersDAO(databaseHelper);

		UserTransferObject searchedTransferObject = new UserTransferObject();
		searchedTransferObject.setUser_mail(email);

		List<UserTransferObject> userTransferObjectList = userDAO
				.getUsers(searchedTransferObject);

		if (userTransferObjectList.size() == 0)
			return true;
		else
			return false;	
	}
}