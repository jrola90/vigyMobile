package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Button;

public class VigymConfirmDialog extends VigymDialog {

	private android.view.View.OnClickListener positiveOnClickListener;
	private android.view.View.OnClickListener negativeOnClickListener;

	public VigymConfirmDialog(Activity parentActivity,
			android.view.View.OnClickListener positiveOnClickListener,
			android.view.View.OnClickListener negativeOnClickListener) {
		super(parentActivity);

		this.positiveOnClickListener = positiveOnClickListener;
		this.negativeOnClickListener = negativeOnClickListener;

		this.setOnClickListener(positiveOnClickListener);

	}

	public void setOnClickListener(android.view.View.OnClickListener positiveOnClickListener) {
		this.positiveOnClickListener = positiveOnClickListener;
		
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(DialogInterface dialog) {

				Button b = VigymConfirmDialog.this.dialog
						.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(VigymConfirmDialog.this.positiveOnClickListener);
			}
		});
	}
	
	@Override
	protected int getLayout() {
		return 0;
	}

	@Override
	protected int getTitle() {
		return R.string.confirm;
	}

	@Override
	protected int getPositiveButtonText() {
		return R.string.yes;
	}

	@Override
	protected OnClickListener getPositiveButtonListener() {
		return null;
	}

	@Override
	protected int getNegativeButtonText() {
		return R.string.no;
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return null;
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
		return R.string.q_are_you_sure;
	}
}