package pl.jrola.java.android.vigym.vigymobile.activities.profilemanagement;

import java.text.DecimalFormat;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;
import android.content.Context;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PropertyValueTextView extends TextView {

	private Double value;
	private Double progress;
	private UnitTransferObject unit;
	private List<ProfileInformationValueTransferObject> profileInformationValueList;

	public PropertyValueTextView(Context context) {
		super(context);
		this.setLayoutParams(new LinearLayout.LayoutParams(0,
				LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		this.setPadding(40, 10, 40, 0);
		this.setTextAppearance(context, android.R.style.TextAppearance_Medium);
	}

	public PropertyValueTextView(Context context, Double value,
			Double progress, UnitTransferObject unit) {
		this(context);
		this.value = value;
		this.progress = progress;
		this.unit = unit;

		this.updateValues();
	}

	public PropertyValueTextView(Context context,
			List<ProfileInformationValueTransferObject> pivto) {
		this(context, 0.0, 0.0, null);
		this.profileInformationValueList = pivto;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
		this.updateValues();
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
		this.updateValues();
	}

	public UnitTransferObject getUnit() {
		return unit;
	}

	public void setUnit(UnitTransferObject unit) {
		this.unit = unit;
		this.updateValues();
	}

	private void updateValues() {

		String color = "";

		if (this.progress != null) {
			if (this.progress < 0) {
				color = "FF0000";
			} else if (this.progress > 0) {
				color = "00FF00";
			} else
				color = "0000FF";
		} else {
			color = "0000FF";
		}

		if (this.progress == null)
			this.progress = 0.0;

		if (this.unit == null)
			this.unit = new UnitTransferObject(0L, "", "", "");

		String text = "<font color=#000>" + this.value
				+ " (</font><font color=#" + color + ">"
				+ new DecimalFormat("#.00").format(this.progress)
				+ "</font><font color=#000>) " + this.unit.getShortName()
				+ "</font>";

		this.setText(Html.fromHtml(text));
	}
}