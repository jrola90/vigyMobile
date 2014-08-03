package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.util.Collection;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.AddProfileInformationAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.GetProfileInformationListAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymFragment;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ProfileBioFragmentActivity extends VigymFragment {

	private List<TransferObject> profileInformatonList;
	private VigymDialog propertyNameDialog;
	private LinearLayout propertyItemsLinearLayout;
	private VigymAsyncTask<Void, Void, String> getProfileInformationListAsyncTask;
	private VigymDialog profileInformationValueDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_profile_bio, container,
				false);

		this.propertyItemsLinearLayout = (LinearLayout) v
				.findViewById(R.id.activity_profile_bio_lin_lay);

		this.refresh();
		this.profileInformationValueDialog = new ProfileInformationValueDialog(
				getActivity());

		return v;
	}

	public void onAddPropertyOnClick(View view) {
		AddProfileInformationAsyncTask adpiTask = new AddProfileInformationAsyncTask(
				(VigymUIComponent) getActivity(), loadingSpinner);
		this.propertyNameDialog = new ProfileInformationAddDialog(
				getActivity(), adpiTask);
		this.propertyNameDialog.show();
	}

	@Override
	public void refresh(Collection<TransferObject> data) {
		this.removeAllPropertyItems();
		this.profileInformatonList = (List<TransferObject>) data;
		this.addPropertyItems(this.profileInformatonList);
	}

	@Override
	public void refresh() {
		this.getProfileInformationListAsyncTask = new GetProfileInformationListAsyncTask(
				this, loadingSpinner,
				LoggedUserSingleton.getUserTransferObject());

		this.loadingSpinner.start();

		this.getProfileInformationListAsyncTask.execute();
	}

	private void addPropertyItems(List<TransferObject> profileInformatonList) {

		for (TransferObject pito : profileInformatonList) {
			this.propertyItemsLinearLayout.addView(new BioPropertyLinearLayout(
					getActivity().getApplicationContext(),
					(ProfileInformationTransferObject) pito, null,
					(VigymUIComponent) getActivity(),
					this.profileInformationValueDialog));
		}
	}

	private void removeAllPropertyItems() {
		this.propertyItemsLinearLayout.removeAllViews();
	}

	public void showTimePickerDialog(View v) {
		((ProfileInformationValueDialog)profileInformationValueDialog).showTimePickerDialog(v);
	}

	public void addInformationValue(View v) {
		((ProfileInformationValueDialog)profileInformationValueDialog).addInformationValue(v);
	}

	public void modifyInformationValue(View v) {
		((ProfileInformationValueDialog)profileInformationValueDialog).modifyInformationValue(v);
	}

	public void deleteInformationValue(View v) {
		((ProfileInformationValueDialog)profileInformationValueDialog).deleteInformationValue(v);
	}
}