package pl.jrola.java.android.vigym.vigymobile.activities.usermain;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.activities.main.MainActivity;
import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.ProfileManagementActivity;
import pl.jrola.java.android.vigym.vigymobile.activities.training.TrainingActivity;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_main);
	}

	public void onTrainingButtonClick(View view) {
		Intent intent = new Intent(this, TrainingActivity.class);
		startActivity(intent);		
	}
	
	public void onProfileManagementButtonClick(View view) {
		Intent intent = new Intent(this, ProfileManagementActivity.class);
		startActivity(intent);
	}

	public void logoutButtonOnClick(View view) {
		LoggedUserSingleton.setUserTransferObject(null);
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}
	
	public void exitButtonOnClick(View view) {
		startActivity(Utils.closeApp());
	}
}