package pl.jrola.java.android.vigym.vigymobile.db.to;

import java.util.Date;

public class TrainingTransferObject extends TransferObject {

	private Long training_id;
	private Integer training_count;
	private Integer training_value;
	private Date training_date;
	private ExerciseTransferObject training_exercise_id;

	public TrainingTransferObject() {
		super();
	}

	public TrainingTransferObject(Long training_id, Integer training_count,
			Integer training_value, Date training_date,
			ExerciseTransferObject training_exercise_id) {
		super();
		this.training_id = training_id;
		this.training_count = training_count;
		this.training_value = training_value;
		this.training_date = training_date;
		this.training_exercise_id = training_exercise_id;
	}

	public Long getId() {
		return training_id;
	}

	public void setId(Long training_id) {
		this.training_id = training_id;
	}

	public Integer getCount() {
		return training_count;
	}

	public void setCount(Integer training_count) {
		this.training_count = training_count;
	}

	public Integer getValue() {
		return training_value;
	}

	public void setValue(Integer training_value) {
		this.training_value = training_value;
	}

	public Date getDate() {
		return training_date;
	}

	public void setDate(Date training_date) {
		this.training_date = training_date;
	}

	public ExerciseTransferObject getExerciseId() {
		return training_exercise_id;
	}

	public void setExerciseId(ExerciseTransferObject training_exercise_id) {
		this.training_exercise_id = training_exercise_id;
	}
}