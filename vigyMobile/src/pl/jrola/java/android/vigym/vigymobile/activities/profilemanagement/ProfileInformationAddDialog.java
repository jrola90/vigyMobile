package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.AddProfileInformationAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.Button;

public class ProfileInformationAddDialog extends ProfileInformationDialog {

	public ProfileInformationAddDialog(Activity parentActivity,
			VigymAsyncTask<Void, Void, String> asyncTask) {
		super(parentActivity, asyncTask);

		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(DialogInterface dialogu) {

				Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

						if (validate() == false)
							return;

						String propertyName = "";
						String propertyDesc = "";

						propertyName = propertyNameTextView.getText()
								.toString();
						propertyDesc = propertyDescTextView.getText()
								.toString();

						ProfileInformationTransferObject pito = new ProfileInformationTransferObject(
								propertyName, propertyDesc, LoggedUserSingleton
										.getUserTransferObject().getUser_id());

						((AddProfileInformationAsyncTask) ProfileInformationAddDialog.this.asyncTask)
								.setProfileInformationTransferObject(pito);
						ProfileInformationAddDialog.this.asyncTask.execute();
						dialog.dismiss();
					}
				});
			}
		});
	}

	@Override
	protected OnClickListener getPositiveButtonListener() {
		return null;
	}
	
	@Override
	public TransferObject submitForm() {
		String name = this.propertyNameTextView.getText().toString().trim();
		String desc = this.propertyDescTextView.getText().toString().trim();

		ProfileInformationTransferObject pito = new ProfileInformationTransferObject();
		pito.setName(name);
		pito.setDesc(desc);

		return pito;
	}

	@Override
	protected int getMessage() {
		return 0;
	}
}