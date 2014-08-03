package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymActivity;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymFragment;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymTabListener;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

public class ProfileManagementActivity extends VigymActivity {

	private ActionBar.Tab accountTab;
	private ActionBar.Tab personalTab;
	
	private VigymFragment accountFragment = new ProfileSettingsActivity();
	private VigymFragment personalFragment = new ProfileBioFragmentActivity();
    
	public void showTimePickerDialog(View v) {
		((ProfileBioFragmentActivity)personalFragment).showTimePickerDialog(v);
	}
	
	public void addInformationValue(View v) {
		((ProfileBioFragmentActivity)personalFragment).addInformationValue(v);
	}
	
	public void modifyInformationValue(View v) {
		((ProfileBioFragmentActivity)personalFragment).modifyInformationValue(v);
	}
	
	public void deleteInformationValue(View v) {
		((ProfileBioFragmentActivity)personalFragment).deleteInformationValue(v);
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        accountTab = actionBar.newTab().setText(getText(R.string.title_activity_profile_settings));
        personalTab = actionBar.newTab().setText(getText(R.string.title_activity_profile_bio));
        
        accountTab.setTabListener(new VigymTabListener(accountFragment));
        personalTab.setTabListener(new VigymTabListener(personalFragment));
        
        actionBar.addTab(accountTab);
        actionBar.addTab(personalTab);
    }

	@Override
	public void refresh() {
		this.accountFragment.refresh();
		this.personalFragment.refresh();
	}
	
	public void onChangePasswordButtonOnClick(View view) {
		ProfileSettingsActivity settingsFragment = (ProfileSettingsActivity) getFragmentManager()
				.findFragmentById(R.id.fragment_container);
		settingsFragment.onChangePasswordButtonOnClick(view);
	}

	public void onAddPropertyOnClick(View view) {

		ProfileBioFragmentActivity bioFragment = (ProfileBioFragmentActivity) getFragmentManager()
				.findFragmentById(R.id.fragment_container);
		bioFragment.onAddPropertyOnClick(view);
	}

	@Override
	public void refresh(Collection<TransferObject> to) {
		
	}
}