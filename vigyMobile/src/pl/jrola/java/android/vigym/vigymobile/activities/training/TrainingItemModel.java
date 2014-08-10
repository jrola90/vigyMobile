package pl.jrola.java.android.vigym.vigymobile.activities.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.to.TrainingTransferObject;

public class TrainingItemModel {

	private List<TrainingTransferObject> trainingList = new ArrayList<TrainingTransferObject>();

	private Double maxTotalValue = Double.valueOf(0);
	private Double avgTotalValue = Double.valueOf(0);

	private Double maxSingleValue = Double.valueOf(0);
	private Double avgSingleValue = Double.valueOf(0);

	private TrainingTransferObject lastTrainingItem;

	public void addItem(TrainingTransferObject tto) {
		trainingList.add(tto);
	}

	public void calcValues() {
		this.maxTotalValue = this.calcMaxTotalValue();
		this.maxSingleValue = this.calcMaxSingleValue();

		this.avgTotalValue = this.calcAvgTotalValue();
		this.avgSingleValue = this.calcAvgSingleValue();

		this.lastTrainingItem = this.getLastTrainingItem();
	}

	private TrainingTransferObject getLastTrainingItem() {

		TrainingTransferObject lastTraining = Collections.max(
				this.trainingList, new Comparator<TrainingTransferObject>() {
					@Override
					public int compare(TrainingTransferObject lhs,
							TrainingTransferObject rhs) {

						int cmp = lhs.getDate().compareTo(rhs.getDate());

						if (cmp == 0) {
							return lhs.getId().compareTo(rhs.getId());
						}

						return cmp;
					}
				});

		return lastTraining;
	}

	private Double calcAvgSingleValue() {
		Double avgSingle = 0.0;
		for (TrainingTransferObject tto : this.trainingList) {
			avgSingle += tto.getValue();
		}
		return avgSingle / this.trainingList.size();
	}

	private Double calcAvgTotalValue() {
		Double avgTotal = 0.0;
		for (TrainingTransferObject tto : this.trainingList) {
			avgTotal += tto.getTotalValue();
		}
		return avgTotal / this.trainingList.size();
	}

	private Double calcMaxSingleValue() {
		TrainingTransferObject trainingTO = Collections.max(this.trainingList);
		return trainingTO.getValue();
	}

	private Double calcMaxTotalValue() {
		TrainingTransferObject trainingTO = Collections.max(this.trainingList);
		return trainingTO.getTotalValue();
	}

	public Double getMaxTotalValue() {
		return maxTotalValue;
	}

	public void setMaxTotalValue(Double maxTotalValue) {
		this.maxTotalValue = maxTotalValue;
	}

	public Double getAvgTotalValue() {
		return avgTotalValue;
	}

	public void setAvgTotalValue(Double avgTotalValue) {
		this.avgTotalValue = avgTotalValue;
	}

	public Double getMaxSingleValue() {
		return maxSingleValue;
	}

	public void setMaxSingleValue(Double maxSingleValue) {
		this.maxSingleValue = maxSingleValue;
	}

	public Double getAvgSingleValue() {
		return avgSingleValue;
	}

	public void setAvgSingleValue(Double avgSingleValue) {
		this.avgSingleValue = avgSingleValue;
	}

	public void setLastTrainingItem(TrainingTransferObject lastTrainingItem) {
		this.lastTrainingItem = lastTrainingItem;
	}

	public TrainingTransferObject getLastTraining() {
		return this.lastTrainingItem;
	}

	public List<TrainingTransferObject> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(List<TrainingTransferObject> trainingList) {
		this.trainingList = trainingList;
	}

}