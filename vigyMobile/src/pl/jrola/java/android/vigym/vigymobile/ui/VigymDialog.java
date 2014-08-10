package pl.jrola.java.android.vigym.vigymobile.ui;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.LayoutInflater;
import android.view.View;

public abstract class VigymDialog implements VigymUIComponent {

	protected AlertDialog.Builder dialogBuilder;
	protected AlertDialog dialog;
	protected Activity parentActivity;
	protected View dialogView;
	protected LoadingSpinner loadingSpinner;
	protected DbHelper databaseHelper;
	private boolean lateInit = false;
	
	public VigymDialog(Activity parentActivity) {

		this.parentActivity = parentActivity;
		this.dialogBuilder = new AlertDialog.Builder(this.parentActivity);
		this.loadingSpinner = new LoadingSpinner(parentActivity);

		if (getLayout() != 0) {
			LayoutInflater inflater = this.parentActivity.getLayoutInflater();
			dialogView = inflater.inflate(getLayout(), null);
		}

		dialogBuilder.setView(dialogView);

		if (getPositiveButtonText() != 0)
			dialogBuilder.setPositiveButton(getPositiveButtonText(),
					getPositiveButtonListener());

		if (getNegativeButtonText() != 0)
			dialogBuilder.setNegativeButton(getNegativeButtonText(),
					getNegativeButtonListener());

		if (getNeutralButtonText() != 0)
			dialogBuilder.setNeutralButton(getNeutralButtonText(),
					getNeutralButtonListener());

		if (getTitle() != 0)
			dialogBuilder.setTitle(this.parentActivity.getText(getTitle()));

		if (getMessage() != 0)
			this.dialogBuilder.setMessage(this.parentActivity
					.getText(getMessage()));

		if (getOnSingleChoiceClickListener() != null)
			this.dialogBuilder.setSingleChoiceItems(getMultiChoiceItems(),
					getCheckedItem(), getOnSingleChoiceClickListener());

		if (getOnMultiChoiceClickListener() != null)
			this.dialogBuilder.setMultiChoiceItems(getMultiChoiceItems(),
					getMultiCheckedItems(), getOnMultiChoiceClickListener());

		dialog = dialogBuilder.create();
		
		this.databaseHelper = new DbHelper(this.parentActivity);
	}

	public VigymDialog(Activity parentActivity, boolean lateInit) {
		this(parentActivity);
		this.lateInit = lateInit;
	}
	
	public void releaseDatabaseHelper() {
		this.databaseHelper.releaseDatabaseHelper();
	}

	protected abstract int getLayout();

	protected abstract int getTitle();

	protected abstract int getPositiveButtonText();

	protected abstract DialogInterface.OnClickListener getPositiveButtonListener();

	protected abstract int getNegativeButtonText();

	protected abstract DialogInterface.OnClickListener getNegativeButtonListener();

	protected abstract int getNeutralButtonText();

	protected abstract DialogInterface.OnClickListener getNeutralButtonListener();

	protected abstract int getMessage();

	protected abstract OnClickListener getOnSingleChoiceClickListener();

	protected abstract int getCheckedItem();

	protected abstract boolean[] getMultiCheckedItems();

	protected abstract CharSequence[] getMultiChoiceItems();

	protected abstract OnMultiChoiceClickListener getOnMultiChoiceClickListener();

	public void show() {

		if (this.lateInit)
			dialog = dialogBuilder.create();

		dialog.show();
	}

	public void hide() {
		dialog.hide();
	}

	@Override
	public LoadingSpinner getLoadingSpinner() {
		return this.loadingSpinner;
	}

	@Override
	public DbHelper getDatabaseHelper() {
		return this.databaseHelper;
	}

	@Override
	public String getStringRes(int resId) {
		return parentActivity.getString(resId);
	}

	public Context getContext() {
		return parentActivity.getApplicationContext();
	}

	@Override
	public void refresh() {

	}

	@Override
	public void refresh(Collection<TransferObject> to) {

	}
}