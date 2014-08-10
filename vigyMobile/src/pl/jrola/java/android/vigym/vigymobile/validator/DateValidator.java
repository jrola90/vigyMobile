package pl.jrola.java.android.vigym.vigymobile.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import pl.jrola.java.android.vigym.vigymobile.R;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.view.View;
import android.widget.EditText;

public class DateValidator extends Validator {

	public DateValidator(View uiComponent) {
		super(uiComponent);
	}

	@Override
	public boolean validate() {
		EditText dateEditText = (EditText) uiComponent;

		if (dateEditText != null) {

			String dateString = dateEditText.getText().toString();
			SimpleDateFormat sdf = new SimpleDateFormat(Utils.DATE_FORMAT);

			try {
				sdf.parse(dateString);
				return true;
			} catch (ParseException e) {
				dateEditText.setError(dateEditText.getContext().getString(
						R.string.error_incorrect_date_format));
			}
		}

		return false;
	}
}