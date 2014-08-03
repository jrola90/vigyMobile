package pl.jrola.java.android.vigym.vigymobile.activities.login.tasks;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.usermain.UserMainActivity;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAOImpl;
import pl.jrola.java.android.vigym.vigymobile.db.to.UserTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.LoadingSpinner;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import android.app.Activity;
import android.content.Intent;

/**
 * Represents an asynchronous login/registration task used to authenticate the
 * user.
 */
public class LoginAsyncTask extends VigymAsyncTask<Void, Void, String> {

	public LoginAsyncTask(VigymUIComponent activity,
			LoadingSpinner loadingSpinner) {
		super(activity, loadingSpinner);
	}

	@Override
	protected String doInBackground(Void... params) {

		try {
			// creates a new user object and adds it to database
			UsersDAOImpl userDAO = new UsersDAOImpl(
					this.activity.getDatabaseHelper());
			UserTransferObject userTransferObject = (UserTransferObject) ((VigymForm) activity)
					.submitForm();

			List<UserTransferObject> userTransferObjectList = userDAO.getUsers(
					userTransferObject, DbConditionals.AND);

			if (userTransferObjectList.size() == 1)
				LoggedUserSingleton
						.setUserTransferObject(userTransferObjectList.get(0));
			else
				return getActivity().getContext().getString(
						R.string.error_incorrect_password);

			return null;

		} catch (Exception e) {
			return  getActivity().getContext().getString(R.string.error_login) + " : " + e.getMessage();
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
			Utils.showToast(this.activity.getContext(), success);
		}

	}
}