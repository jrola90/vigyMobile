package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;

public class VigymTabListener implements ActionBar.TabListener {
	Fragment fragment;
	
	public VigymTabListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}
	
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}
}