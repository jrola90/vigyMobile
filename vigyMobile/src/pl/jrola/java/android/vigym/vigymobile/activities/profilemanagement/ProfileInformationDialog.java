package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator.PropertyNameValidator;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.validator.ValidatorList;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.widget.TextView;

public abstract class ProfileInformationDialog extends VigymDialog implements
		VigymForm {

	protected TextView propertyNameTextView;
	protected TextView propertyDescTextView;

	protected VigymAsyncTask<Void, Void, String> asyncTask;

	protected ValidatorList validator;

	public ProfileInformationDialog(Activity parentActivity,
			VigymAsyncTask<Void, Void, String> asyncTask) {
		super(parentActivity);

		propertyNameTextView = (TextView) dialogView
				.findViewById(R.id.property_name);
		propertyDescTextView = (TextView) dialogView
				.findViewById(R.id.property_desc);

		this.asyncTask = asyncTask;
		
		this.validator = new ValidatorList(getContext());
		this.validator.addValidator(new PropertyNameValidator(this.propertyNameTextView));
		
	}

	@Override
	public int getLayout() {
		return R.layout.activity_property_dialog;
	}

	@Override
	public int getTitle() {
		return R.string.add_property;
	}

	@Override
	protected int getPositiveButtonText() {
		return R.string.add;
	}

	@Override
	protected int getNegativeButtonText() {
		return R.string.cancel;
	}

	@Override
	protected int getNeutralButtonText() {
		return 0;
	}

	@Override
	protected OnClickListener getNeutralButtonListener() {
		return null;
	}

	@Override
	public void clearForm() {
		propertyNameTextView.setText("");
		propertyDescTextView.setText("");
	}

	@Override
	public void initForm(TransferObject transferObject) {
		ProfileInformationTransferObject pito = (ProfileInformationTransferObject) transferObject;
		this.propertyNameTextView.setText(pito.getName());
		this.propertyDescTextView.setText(pito.getDesc());
	}

	@Override
	public void clearFormErrors() {
		this.propertyNameTextView.setError(null);
		this.propertyDescTextView.setError(null);
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				clearForm();
				clearFormErrors();
				hide();
			}
		};
	}

	@Override
	public boolean validate() {
		return validator.validate();
	}
	
	@Override
	public void refresh() {
		
	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		
	}

	@Override
	protected boolean[] getMultiCheckedItems() {
		return null;
	}

	@Override
	protected CharSequence[] getMultiChoiceItems() {
		return null;
	}

	@Override
	protected OnMultiChoiceClickListener getOnMultiChoiceClickListener() {
		return null;
	}
	
	@Override
	protected OnClickListener getOnSingleChoiceClickListener() {
		return null;
	}

	@Override
	protected int getCheckedItem() {
		return 0;
	}
}