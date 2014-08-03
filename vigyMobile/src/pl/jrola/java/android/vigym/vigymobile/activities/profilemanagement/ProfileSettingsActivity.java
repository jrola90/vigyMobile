package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.dao.DAOFactory;
import pl.jrola.java.android.vigym.vigymobile.db.dao.UsersDAO;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymFragment;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfileSettingsActivity extends VigymFragment {

	private EditText currentPasswordEditText;
	private EditText newPasswordEditText;
	private EditText confirmNewPasswordEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View V = inflater.inflate(R.layout.activity_profile_settings,
				container, false);

		return V;
	}

	public void onChangePasswordButtonOnClick(View view) {

		this.currentPasswordEditText = (EditText) getActivity().findViewById(
				R.id.profile_settings_current_password);
		this.newPasswordEditText = (EditText) getActivity().findViewById(
				R.id.profile_settings_new_password);
		this.confirmNewPasswordEditText = (EditText) getActivity()
				.findViewById(R.id.profile_settings_confirm_new_password);

		String currentPassword = currentPasswordEditText.getText().toString()
				.trim();
		String newPassword = newPasswordEditText.getText().toString().trim();
		String confirmPassword = confirmNewPasswordEditText.getText()
				.toString().trim();

		if (LoggedUserSingleton.getUserTransferObject().verifyPassword(
				currentPassword) == false) {
			this.currentPasswordEditText
					.setError(getText(R.string.current_password_error));
			return;
		}

		if (newPassword.equals(confirmPassword) == false) {
			this.confirmNewPasswordEditText
					.setError(getText(R.string.confirm_password_error));
			return;
		}

		UsersDAO userDAO = DAOFactory.createUsersDAO(databaseHelper);
		String passHash = Utils.generateSHA1Hash(newPassword);
		if (userDAO.changePassword(LoggedUserSingleton.getUserTransferObject()
				.getUser_id(), passHash)) {
			LoggedUserSingleton.getUserTransferObject().setUser_password(
					passHash);
			Utils.showToast(getActivity().getApplicationContext(),
					getText(R.string.change_password_success).toString());
		} else
			Utils.showToast(getActivity().getApplicationContext(),
					getText(R.string.change_password_error).toString());

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		// TODO Auto-generated method stub
		
	}
}