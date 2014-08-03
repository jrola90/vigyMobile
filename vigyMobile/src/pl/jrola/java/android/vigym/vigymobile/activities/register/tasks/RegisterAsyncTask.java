package pl.jrola.java.android.vigym.vigymobile.activities.register.tasks;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.usermain.UserMainActivity;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAOImpl;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddUserException;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.app.Activity;
import android.content.Intent;

/**
 * Represents an asynchronous login/registration task used to authenticate the
 * user.
 */
public class RegisterAsyncTask extends VigymAsyncTask<Void, Void, String> {

	public RegisterAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner) {
		super(activity, loadingSpinner);
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			UsersDAOImpl userDAO = new UsersDAOImpl(activity.getDatabaseHelper());
			UserTransferObject userTransferObject = (UserTransferObject) ((VigymForm) activity)
					.submitForm();
			userTransferObject = userDAO.addUser(userTransferObject);
			LoggedUserSingleton.setUserTransferObject(userTransferObject);
			return null;

		} catch (AddUserException e) {
			return e.getMessage();
		}
	}

	@Override
	protected void onPostExecute(final String success) {

		super.onPostExecute(success);
		
		if (success == null) {
			((VigymForm) activity).clearForm();

			// move to another activity
			Intent intent = new Intent((Activity) activity,
					UserMainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.activity.getContext().startActivity(intent);

		} else {
			Utils.showToast(this.activity.getContext(), this.activity.getStringRes(R.string.error_create_profile) + ": " + success);
		}

	}
}