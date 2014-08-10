package pl.jrola.java.android.vigym.vigymobile.ui;

import java.util.Calendar;

import pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement.ProfileInformationValueDialog;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerFragmentDialog extends VigymFragmentDialog implements DatePickerDialog.OnDateSetListener {

	private VigymUIComponent parentActivity;
	private VigymForm parentForm;
	private TextView dateTextView;
	
	public DatePickerFragmentDialog(VigymUIComponent parentActivity, VigymForm parentForm, TextView dateTextView) {
		super();
		this.parentActivity = parentActivity;
		this.parentForm = parentForm;
		this.dateTextView = dateTextView;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		StringBuilder date = new StringBuilder();
		date.append(year);
		date.append(Utils.DATE_SEP);
		date.append(monthOfYear + 1);
		date.append(Utils.DATE_SEP);
		date.append(dayOfMonth);
		
		if (this.dateTextView != null) {
			dateTextView.setText(date.toString());
		}
		
	}
}