package pl.jrola.java.android.vigym.vigymobile.validator;

import pl.jrola.java.android.vigym.vigymobile.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class SpinnerValidator extends Validator {

	public SpinnerValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		
		Spinner unitSpinner = (Spinner) uiComponent;
		
		if (unitSpinner != null) {
			if (unitSpinner.getSelectedItemPosition() != AdapterView.INVALID_POSITION) {
				return true;
			} else {
				this.errorMessageList.add(unitSpinner.getContext().getString(R.string.error_no_item_selected));
			}
		}
		
		return false;
	}

}
