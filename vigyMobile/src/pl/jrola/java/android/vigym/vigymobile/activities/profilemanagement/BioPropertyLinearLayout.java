package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.DeleteProfileInformationAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.tasks.UpdateProfileInformationAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.tasks.VigymAsyncTask;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymConfirmDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymUIComponent;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BioPropertyLinearLayout extends LinearLayout {

	private LinearLayout titleLinearLayout;
	private TextView propertyNameTextView;
	private View separatorView;
	private LinearLayout propertyValueLinearLayout;
	private PropertyValueTextView propertyValueTextView;
	private TextView propertyDescTextView;

	private ImageButton editPropertyImageButton;
	private ImageButton deletePropertyImageButton;
	private ImageButton addPropertyValueImageButton;

	private ProfileInformationTransferObject profileInformationTransferObject;
	private List<ProfileInformationValueTransferObject> profileInformationValueList;

	private VigymUIComponent parent;
	private VigymDialog profileInformationValueDialog;

	public BioPropertyLinearLayout(Context context,
			ProfileInformationTransferObject pito,
			List<ProfileInformationValueTransferObject> pivtoList,
			VigymUIComponent parent, VigymDialog profileInformationValueDialog) {
		super(context);

		this.profileInformationValueDialog = profileInformationValueDialog;

		this.profileInformationTransferObject = pito;
		this.profileInformationValueList = pivtoList;

		this.setOrientation(LinearLayout.VERTICAL);

		this.titleLinearLayout = new LinearLayout(context);
		this.titleLinearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.titleLinearLayout.setPadding(0, 0, 40, 0);

		this.editPropertyImageButton = new ImageButton(context);
		this.editPropertyImageButton.setLayoutParams(new LayoutParams(70, 70));
		this.editPropertyImageButton
				.setImageResource(R.drawable.ic_edit);

		this.deletePropertyImageButton = new ImageButton(context);
		this.deletePropertyImageButton
				.setLayoutParams(new LayoutParams(70, 70));
		this.deletePropertyImageButton
				.setImageResource(R.drawable.ic_delete);

		this.propertyNameTextView = new TextView(context);
		this.propertyNameTextView.setLayoutParams(new LayoutParams(0,
				LayoutParams.WRAP_CONTENT, 1.0f));
		this.propertyNameTextView.setPadding(40, 40, 40, 2);
		this.propertyNameTextView.setAllCaps(true);
		this.propertyNameTextView.setTypeface(
				this.propertyNameTextView.getTypeface(), Typeface.BOLD);
		this.propertyNameTextView.setText(pito.getName());
		this.propertyNameTextView.setTextColor(Color.BLACK);

		this.separatorView = new View(context);
		this.separatorView.setBackgroundColor(Color.LTGRAY);
		this.separatorView.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, 4));
		((ViewGroup.MarginLayoutParams) this.separatorView.getLayoutParams())
				.setMargins(30, 0, 30, 0);

		this.addPropertyValueImageButton = new ImageButton(context);
		this.addPropertyValueImageButton.setLayoutParams(new LayoutParams(70,
				70));
		this.addPropertyValueImageButton
				.setImageResource(R.drawable.ic_add);

		this.propertyValueLinearLayout = new LinearLayout(context);
		this.propertyValueLinearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.propertyValueLinearLayout.setPadding(0, 0, 40, 0);

		// this.propertyValueTextView = new PropertyValueTextView(context,
		// pivtoList);
		this.propertyValueTextView = new PropertyValueTextView(context,
				profileInformationTransferObject
						.getLatestProfileInformationValue().getValue(),
				profileInformationTransferObject.getProgress(),
				profileInformationTransferObject
						.getLatestProfileInformationValue()
						.getUnitTransferObject());

		this.propertyDescTextView = new TextView(context);
		this.propertyDescTextView.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.propertyDescTextView.setPadding(40, 0, 40, 0);
		this.propertyDescTextView.setTextAppearance(context,
				android.R.style.TextAppearance_Small);
		this.propertyDescTextView.setTypeface(
				this.propertyNameTextView.getTypeface(), Typeface.ITALIC);
		this.propertyDescTextView.setText(profileInformationTransferObject
				.getLatestProfileInformationValue().getDateAsString());

		this.titleLinearLayout.addView(propertyNameTextView);
		this.titleLinearLayout.addView(editPropertyImageButton);
		this.titleLinearLayout.addView(deletePropertyImageButton);

		this.propertyValueLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		this.propertyValueLinearLayout.addView(propertyValueTextView);
		this.propertyValueLinearLayout.addView(addPropertyValueImageButton);

		this.addView(titleLinearLayout);
		this.addView(separatorView);
		this.addView(propertyValueLinearLayout);
		this.addView(propertyDescTextView);

		this.parent = parent;

		this.editPropertyImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				VigymAsyncTask<Void, Void, String> asyncTask = new UpdateProfileInformationAsyncTask(
						BioPropertyLinearLayout.this.parent,
						BioPropertyLinearLayout.this.parent.getLoadingSpinner(),
						profileInformationTransferObject);

				ProfileInformationUpdateDialog profileInformationUpdateDialog = new ProfileInformationUpdateDialog(
						(Activity) BioPropertyLinearLayout.this.parent,
						asyncTask, profileInformationTransferObject);

				profileInformationUpdateDialog.show();

			}
		});

		this.deletePropertyImageButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						final VigymConfirmDialog confirmDialog = new VigymConfirmDialog(
								(Activity) BioPropertyLinearLayout.this.parent,
								null, null);

						confirmDialog
								.setOnClickListener(new android.view.View.OnClickListener() {

									@Override
									public void onClick(View arg0) {
										BioPropertyLinearLayout.this.parent
												.getLoadingSpinner().start();
										VigymAsyncTask<Void, Void, String> deleteProfileInformationAsyncTask = new DeleteProfileInformationAsyncTask(
												BioPropertyLinearLayout.this.parent,
												BioPropertyLinearLayout.this.parent
														.getLoadingSpinner(),
												profileInformationTransferObject,
												confirmDialog);
										deleteProfileInformationAsyncTask
												.execute();
									}
								});

						confirmDialog.show();

					}
				});

		this.addPropertyValueImageButton
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						((ProfileInformationValueDialog) BioPropertyLinearLayout.this.profileInformationValueDialog)
								.setProfileInformationTO(profileInformationTransferObject);
						((ProfileInformationValueDialog) BioPropertyLinearLayout.this.profileInformationValueDialog)
								.show(profileInformationTransferObject);

					}
				});

	}
}