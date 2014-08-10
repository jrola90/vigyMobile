package pl.jrola.java.android.vigym.vigymobile.activities.training.validators;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.view.View;

public class SingleSelectionValidator extends Validator {

	private Integer selectedItem;

	public SingleSelectionValidator(View uiComponent, Integer selectedItem) {
		super(uiComponent);
		this.selectedItem = selectedItem;
	}

	@Override
	public boolean validate() {
		if (this.selectedItem == null) {
			this.errorMessageList.add(uiComponent.getContext().getString(
					R.string.error_no_item_selected));
			return false;
		}
		return true;
	}
}
