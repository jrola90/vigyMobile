package pl.jrola.java.android.vigym.vigymobile.activities.training.validators;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;
import pl.jrola.java.android.vigym.vigymobile.validator.Validator;
import android.view.View;

public class MultipleSelectionValidator extends Validator {

	private List<TrainingTransferObject> selectedItems;

	public MultipleSelectionValidator(View uiComponent,
			List<TrainingTransferObject> selectedItems) {
		super(uiComponent);
		this.selectedItems = selectedItems;
	}

	@Override
	public boolean validate() {
		if (this.selectedItems.size() == 0) {
			this.errorMessageList.add(uiComponent.getContext().getString(
					R.string.error_no_item_selected));
			return false;
		}
		return true;
	}

}