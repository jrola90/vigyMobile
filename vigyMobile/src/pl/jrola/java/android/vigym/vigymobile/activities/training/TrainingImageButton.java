package pl.jrola.java.android.vigym.vigymobile.activities.training;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class TrainingImageButton extends ImageButton {

	protected TrainingItemModel trainingsItemModel;
	
	public TrainingImageButton(Context context) {
		super(context);
	}

	public TrainingImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public TrainingImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}	
	
	public TrainingItemModel getTrainingsItemModel() {
		return trainingsItemModel;
	}

	public void setTrainingsItemModel(TrainingItemModel trainingsItemModel) {
		this.trainingsItemModel = trainingsItemModel;
	}
}