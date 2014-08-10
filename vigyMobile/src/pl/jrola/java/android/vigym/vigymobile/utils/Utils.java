package pl.jrola.java.android.vigym.vigymobile.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAOImpl;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.exceptions.DatabaseHelperInitialiseException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public abstract class Utils {

	public static final String DATE_SEP = "/";
	public static final String DATE_FORMAT = "yyyy/MM/dd";

	public static String PROFILE_FILE_EXTENSION = ".*xml";

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
		string = new StringBuilder(string).replace(index,
				index + value.length(), "").toString();
		return string;
	}

	public static boolean isEmailVacant(String email, DbHelper databaseHelper)
			throws DatabaseHelperInitialiseException {

		UsersDAOImpl userDAO = new UsersDAOImpl(databaseHelper);

		UserTransferObject searchedTransferObject = new UserTransferObject();
		searchedTransferObject.setUser_mail(email);

		List<UserTransferObject> userTransferObjectList = userDAO
				.getUsers(searchedTransferObject);

		if (userTransferObjectList.size() == 0)
			return true;
		else
			return false;
	}

	public static void showToast(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.show();
	}

	public static Intent closeApp() {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		return intent;
	}

	public static void logError(Exception e) {
		Log.e("[vigym]", e.getMessage(), e);
	}

	public static Date convertStringToDate(String dateString, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		if (dateString != null) {
			try {
				return sdf.parse(dateString);
			} catch (ParseException e) {
				return new Date();
			}
		}
		return new Date();
	}

	public static String convertDateToString(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

}