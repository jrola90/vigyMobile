package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class UnitValidator extends Validator {

	public UnitValidator(View uiComponent) {
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
