package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.validator;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class PropertyNameValidator extends Validator {

	public PropertyNameValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {

		try {
			TextView propertyNameTextView = (TextView) uiComponent;
			
			if (propertyNameTextView != null) {
				String propertyName = propertyNameTextView.getText().toString().trim();
				if (TextUtils.isEmpty(propertyName)) {
					propertyNameTextView.setError(uiComponent.getContext().getString(R.string.field_cannot_be_empty));
					return false;
				} else
					return true;
					
			} 
			
		} catch (ClassCastException cce) {
			Utils.logError(cce);
		}

		return false;
	}

}
