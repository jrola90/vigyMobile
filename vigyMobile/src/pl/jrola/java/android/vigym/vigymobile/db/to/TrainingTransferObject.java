package pl.jrola.java.android.vigym.vigymobile.db.to;

import java.util.Date;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class TrainingTransferObject extends TransferObject implements
		Comparable<TrainingTransferObject> {

	private Long training_id;
	private Integer training_count;
	private Double training_value;
	private Date training_date;
	private ExerciseTransferObject training_exercise_id;
	private UserTransferObject training_user_id;

	public TrainingTransferObject() {
		super();
	}

	public TrainingTransferObject(Long training_id, Integer training_count,
			Double training_value, Date training_date,
			ExerciseTransferObject training_exercise_id) {
		super();
		this.training_id = training_id;
		this.training_count = training_count;
		this.training_value = training_value;
		this.training_date = training_date;
		this.training_exercise_id = training_exercise_id;
	}

	public TrainingTransferObject(Cursor cursor) {
		int columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_TRAINING_ID);
		this.training_id = cursor.getLong(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_TRAINING_COUNT);
		this.training_count = cursor.getInt(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_TRAINING_VALUE);
		this.training_value = cursor.getDouble(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_TRAINING_DATE);
		this.training_date = Utils.convertStringToDate(
				cursor.getString(columnIndex), Utils.DATE_FORMAT);

		columnIndex = cursor
				.getColumnIndex(DbUtils.DB_COLUMN_TRAINING_EXERCISE_ID);
		this.training_exercise_id = new ExerciseTransferObject();
		this.training_exercise_id.setId(cursor.getLong(columnIndex));

		this.training_user_id = LoggedUserSingleton.getUserTransferObject();
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

	public Double getValue() {
		return training_value;
	}

	public void setValue(Double training_value) {
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

	public UserTransferObject getUserId() {
		return training_user_id;
	}

	public void setUserId(UserTransferObject training_user_id) {
		this.training_user_id = training_user_id;
	}

	public String getDateAsString() {
		return Utils.convertDateToString(training_date, Utils.DATE_FORMAT);
	}

	public Double getTotalValue() {
		if (this.training_count != null && this.training_value != null)
			return this.training_count * this.training_value;
		return 0.0;
	}

	@Override
	public String toString() {
		return getDateAsString() + " - [" + training_count + " x "
				+ training_value + "]";
	}

	@Override
	public int compareTo(TrainingTransferObject anotherTO) {
		if (this.training_value == anotherTO.training_value)
			return 0;
		else {
			if (this.training_value > anotherTO.training_value)
				return 1;
			else
				return -1;
		}
	}

	@Override
	public int hashCode() {
		return this.training_id.intValue();
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof TrainingTransferObject
				&& ((TrainingTransferObject) o).training_id
						.equals(this.training_id)
				&& ((TrainingTransferObject) o).training_count
						.equals(this.training_count)
				&& ((TrainingTransferObject) o).training_value
						.equals(this.training_value)
				&& ((TrainingTransferObject) o).training_date
						.equals(this.training_date))
			return true;

		return false;
	}
}