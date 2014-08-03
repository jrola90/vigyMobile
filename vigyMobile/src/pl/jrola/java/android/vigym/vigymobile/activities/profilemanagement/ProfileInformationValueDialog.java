package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.AddProfileInformationValuesAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.DeleteProfileInformationValueAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.GetProfileInformationValuesAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.UpdateProfileInformationValueAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator.DateValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator.UnitValidator;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator.ValueValidator;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.DatePickerFragmentDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymConfirmDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymForm;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.validator.ValidatorList;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileInformationValueDialog extends VigymDialog implements
		VigymForm {

	private ValidatorList validator;
	private VigymAsyncTask<Void, Void, String> getProfileInformationValuesAsyncTask;
	private VigymAsyncTask<Void, Void, String> addProfileInformationValuesAsyncTask;

	private TextView valueTextView;
	private Spinner unitSpinner;
	private TextView dateTextView;
	private ListView valueList;

	private ProfileInformationTransferObject profileInformationTO;
	private ProfileInformationValueTransferObject profileInformationValueTO;
	private ProfileInformationValueTransferObject selectedProfileInformationValueTO;
	private List<ProfileInformationValueTransferObject> profileInformationValueList = new ArrayList<ProfileInformationValueTransferObject>();

	public ProfileInformationValueDialog(Activity parentActivity) {
		super(parentActivity);
		this.profileInformationValueTO = new ProfileInformationValueTransferObject();

		this.valueTextView = (TextView) this.dialogView
				.findViewById(R.id.valueEditText);
		this.unitSpinner = (Spinner) this.dialogView
				.findViewById(R.id.unitSpinner);
		this.dateTextView = (TextView) this.dialogView
				.findViewById(R.id.dateEditText);
		this.valueList = (ListView) this.dialogView
				.findViewById(R.id.valueList);

		this.valueList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedProfileInformationValueTO = (ProfileInformationValueTransferObject) valueList
						.getAdapter().getItem(position);
				initForm(selectedProfileInformationValueTO);
			}
		});

		validator = new ValidatorList(parentActivity);
		validator.addValidator(new ValueValidator(this.valueTextView));
		validator.addValidator(new UnitValidator(this.unitSpinner));
		validator.addValidator(new DateValidator(this.dateTextView));
	}

	@Override
	protected int getLayout() {
		return R.layout.dialog_profile_information_value;
	}

	@Override
	protected int getTitle() {
		return R.string.add_value;
	}

	@Override
	protected int getPositiveButtonText() {
		return 0;
	}

	@Override
	protected OnClickListener getPositiveButtonListener() {
		return null;
	}

	@Override
	protected int getNegativeButtonText() {
		return R.string.close;
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				((VigymUIComponent)parentActivity).refresh();
			}
		};
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
	protected int getMessage() {
		return 0;
	}

	@Override
	public void clearForm() {
		this.valueTextView.setText("");
		this.dateTextView.setText("");
		this.unitSpinner.setEnabled(true);
		this.unitSpinner.setSelection(0);
		this.valueList.setAdapter(null);
	}

	@Override
	public void clearFormErrors() {
		this.valueTextView.setError(null);
		this.dateTextView.setError(null);
	}

	@Override
	public boolean validate() {
		return validator.validate();
	}

	@Override
	public TransferObject submitForm() {

		ProfileInformationValueTransferObject pivto = new ProfileInformationValueTransferObject();

		Double value = Double.parseDouble(this.valueTextView.getText()
				.toString().trim());
		UnitTransferObject unitTO = (UnitTransferObject) this.unitSpinner
				.getSelectedItem();
		String dateString = this.dateTextView.getText().toString().trim();
		Date date = Utils.convertStringToDate(dateString, Utils.DATE_FORMAT);

		if (this.selectedProfileInformationValueTO != null)
			pivto.setId(this.selectedProfileInformationValueTO.getId());
		pivto.setValue(value);
		pivto.setDate(date);
		pivto.setProfileInformationTransferObject(this.profileInformationTO);
		pivto.setUnitTransferObject(unitTO);

		return pivto;
	}

	@Override
	public void initForm(TransferObject transferObject) {
		this.profileInformationValueTO = (ProfileInformationValueTransferObject) transferObject;

		this.valueTextView.setText(this.profileInformationValueTO.getValue()
				.toString());
		this.setDateTextView(this.profileInformationValueTO.getDate());

	}

	private void setDateTextView(Date date) {
		SimpleDateFormat df = new SimpleDateFormat(Utils.DATE_FORMAT);
		String formattedDate = df.format(date);
		this.setDateTextView(formattedDate);
	}

	public void setDateTextView(String date) {
		this.dateTextView.setText(date);
	}

	@Override
	public void refresh() {
		this.clearForm();
		this.clearFormErrors();
		this.initValueList();
	}

	private void initValueList() {
		// this.valueList.removeAllViews();

		this.loadingSpinner.start();
		this.getProfileInformationValuesAsyncTask = new GetProfileInformationValuesAsyncTask(
				this, loadingSpinner, this.profileInformationTO);
		this.getProfileInformationValuesAsyncTask.execute();
	}

	@Override
	public void refresh(Collection<TransferObject> toList) {
		int size = toList.size();
		if (size > 0) {
			Object[] objectArray = toList.toArray();
			if (objectArray[0] instanceof UnitTransferObject) {
				UnitTransferObject[] unitTOArray = Arrays.copyOf(objectArray,
						objectArray.length, UnitTransferObject[].class);
				this.refresh(unitTOArray);
			} else if (objectArray[0] instanceof ProfileInformationValueTransferObject) {
				ProfileInformationValueTransferObject[] profileInformationValueTOArray = Arrays
						.copyOf(objectArray, objectArray.length,
								ProfileInformationValueTransferObject[].class);
				this.refresh(profileInformationValueTOArray);
			}
		}
	}

	private void refresh(
			ProfileInformationValueTransferObject[] profileInformationValueTOArray) {
		this.profileInformationValueList = Arrays
				.asList(profileInformationValueTOArray);

		int size = this.profileInformationValueList.size();

		if (size > 0) {
			this.unitSpinner.setSelection(this.profileInformationValueList
					.get(0).getUnitTransferObject().getId().intValue() - 1);
			this.unitSpinner.setEnabled(false);
		}

		ArrayAdapter<ProfileInformationValueTransferObject> dataAdapter = new ArrayAdapter<ProfileInformationValueTransferObject>(
				this.parentActivity,
				R.layout.profile_information_value_list_view_item, R.id.label,
				this.profileInformationValueList);
		this.valueList.setAdapter(dataAdapter);
	}

	private void refresh(UnitTransferObject[] toList) {
		if (this.unitSpinner != null) {
			ArrayAdapter<UnitTransferObject> dataAdapter = new ArrayAdapter<UnitTransferObject>(
					this.parentActivity, android.R.layout.simple_spinner_item,
					toList);
			this.unitSpinner.setAdapter(dataAdapter);
		}
	}

	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragmentDialog(
				(VigymUIComponent) parentActivity, (VigymForm) this);
		newFragment.show(parentActivity.getFragmentManager(), "datePicker");
	}

	public void addInformationValue(View v) {

		if (validate() == false)
			return;

		this.profileInformationValueTO = (ProfileInformationValueTransferObject) this
				.submitForm();

		this.loadingSpinner.start();
		this.addProfileInformationValuesAsyncTask = new AddProfileInformationValuesAsyncTask(
				this, loadingSpinner, this.profileInformationValueTO);
		this.addProfileInformationValuesAsyncTask.execute();

	}

	public void modifyInformationValue(View v) {
		if (validate() == false)
			return;

		if (selectedProfileInformationValueTO != null) {
			loadingSpinner.start();
			UpdateProfileInformationValueAsyncTask updateProfileInformationValueAsyncTask = new UpdateProfileInformationValueAsyncTask(
					(VigymUIComponent) parentActivity, loadingSpinner,
					(ProfileInformationValueTransferObject) submitForm(),
					ProfileInformationValueDialog.this);
			updateProfileInformationValueAsyncTask.execute();

		} else
			Utils.showToast(parentActivity,
					getStringRes(R.string.error_no_item_selected));
	}

	public void deleteInformationValue(View v) {

		if (this.selectedProfileInformationValueTO != null) {

			final VigymConfirmDialog confirmDialog = new VigymConfirmDialog(
					(Activity) this.parentActivity, null, null);

			confirmDialog
					.setOnClickListener(new android.view.View.OnClickListener() {

						@Override
						public void onClick(View arg0) {

							if (selectedProfileInformationValueTO != null) {
								loadingSpinner.start();
								DeleteProfileInformationValueAsyncTask deleteProfileInformationValueAsyncTask = new DeleteProfileInformationValueAsyncTask(
										(VigymUIComponent) parentActivity,
										loadingSpinner,
										selectedProfileInformationValueTO,
										ProfileInformationValueDialog.this,
										confirmDialog);
								deleteProfileInformationValueAsyncTask
										.execute();
							} else
								Utils.showToast(
										parentActivity,
										getStringRes(R.string.error_no_item_selected));

						}
					});

			confirmDialog.show();
		} else {
			Utils.showToast(parentActivity,
					parentActivity.getString(R.string.error_no_item_selected));
		}
	}

	public ProfileInformationValueTransferObject getProfileInformationValueTO() {
		return profileInformationValueTO;
	}

	public void setProfileInformationValueTO(
			ProfileInformationValueTransferObject profileInformationValueTO) {
		this.profileInformationValueTO = profileInformationValueTO;
	}

	public ProfileInformationTransferObject getProfileInformationTO() {
		return profileInformationTO;
	}

	public void setProfileInformationTO(
			ProfileInformationTransferObject profileInformationTO) {
		this.profileInformationTO = profileInformationTO;
	}

	public void show(ProfileInformationTransferObject profileInformationTO) {
		this.profileInformationTO = profileInformationTO;
		this.clearForm();
		this.clearFormErrors();
		this.initValueList();
		super.show();
	}

	public void setSelectedProfileInformationValueTO(
			ProfileInformationValueTransferObject selectedProfileInformationValueTO) {
		this.selectedProfileInformationValueTO = selectedProfileInformationValueTO;
	}
}