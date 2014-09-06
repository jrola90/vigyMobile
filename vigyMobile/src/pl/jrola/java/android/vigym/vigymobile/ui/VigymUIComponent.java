package pl.jrola.java.android.vigym.vigymobile.ui;

import java.util.Collection;

import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import android.content.Context;

public interface VigymUIComponent {
	void refresh();
	void refresh(Collection<TransferObject> to);
	LoadingSpinner getLoadingSpinner();
	DbHelper getDatabaseHelper();
	String getStringRes(int resId);
	Context getContext();
}