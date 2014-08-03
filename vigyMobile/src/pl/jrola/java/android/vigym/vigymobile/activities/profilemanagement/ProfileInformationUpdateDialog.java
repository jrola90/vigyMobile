package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.UpdateProfileInformationAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.Button;

public class ProfileInformationUpdateDialog extends ProfileInformationDialog {

	private ProfileInformationTransferObject profileInformationTO;

	public ProfileInformationUpdateDialog(Activity parentActivity,
			VigymAsyncTask<Void, Void, String> asyncTask,
			ProfileInformationTransferObject pito) {
		super(parentActivity, asyncTask);

		this.profileInformationTO = pito;

		this.initForm(this.profileInformationTO);

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

						ProfileInformationTransferObject pito = (ProfileInformationTransferObject) submitForm();

						((UpdateProfileInformationAsyncTask) ProfileInformationUpdateDialog.this.asyncTask)
								.setProfileInformationTransferObject(pito);
						ProfileInformationUpdateDialog.this.asyncTask.execute();
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
	protected OnClickListener getNegativeButtonListener() {
		return new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {

			}
		};
	}

	@Override
	public TransferObject submitForm() {
		String name = this.propertyNameTextView.getText().toString().trim();
		String desc = this.propertyDescTextView.getText().toString().trim();

		this.profileInformationTO.setName(name);
		this.profileInformationTO.setDesc(desc);

		return this.profileInformationTO;
	}

	@Override
	protected int getMessage() {
		return 0;
	}
}