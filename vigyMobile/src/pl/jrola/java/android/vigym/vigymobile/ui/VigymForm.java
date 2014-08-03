package pl.jrola.java.android.vigym.vigymobile.ui;

import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;

public interface VigymForm {
	void clearForm();
	void clearFormErrors();
	boolean validate();
	TransferObject submitForm();
	void initForm(TransferObject transferObject);
}