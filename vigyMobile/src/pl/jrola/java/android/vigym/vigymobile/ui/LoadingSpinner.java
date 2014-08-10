package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

public class LoadingSpinner {

	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	private TextView messageTextView;
	
	private String message = "";
	
	public LoadingSpinner(Activity activity) {
		this(activity, "");
		dialog.setCancelable(false);
	}
	
	public LoadingSpinner(Activity activity, int resId) {
		this(activity, activity.getString(resId));
	}
	
	public LoadingSpinner(Activity activity, String message) {
		super();
		this.message = message;
		this.builder = new AlertDialog.Builder(activity);
		
		View builderView = activity.getLayoutInflater().inflate(
				R.layout.activity_progress_spinner, null);
		this.builder.setView(builderView);

		messageTextView = (TextView) builderView.findViewById(R.id.login_status_message);
		messageTextView.setText(message);
		
		this.dialog = builder.create();
	}
	
	public void start() {
		this.dialog.setCancelable(false);
		this.dialog.show();
	}
	
	public void stop() {
		this.dialog.hide();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.messageTextView.setText(message);
	}
}