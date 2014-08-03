package pl.jrola.java.android.vigym.vigymobile.activities.training;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.ui.VigymDialog;

public abstract class TrainingDialog extends VigymDialog {

	public TrainingDialog(Activity parentActivity) {
		super(parentActivity);
	}

	@Override
	protected int getLayout() {
		return R.layout.dialog_training;
	}

	@Override
	protected int getTitle() {
		return R.string.add_training_item;
	}

	@Override
	protected int getPositiveButtonText() {
		return R.string.ok;
	}

	@Override
	protected OnClickListener getPositiveButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		};
	}

	@Override
	protected int getNegativeButtonText() {
		return R.string.cancel;
	}

	@Override
	protected OnClickListener getNegativeButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

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
}