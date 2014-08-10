package pl.jrola.java.android.vigym.vigymobile.db.to;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class ExerciseTransferObject extends TransferObject {

	private Long exercise_id;
	private String exercise_name;
	private String exercise_desc;

	public ExerciseTransferObject() {
		super();
	}

	public ExerciseTransferObject(Long exercise_id, String exercise_name,
			String exercise_desc) {
		super();
		this.exercise_id = exercise_id;
		this.exercise_name = exercise_name;
		this.exercise_desc = exercise_desc;
	}

	public ExerciseTransferObject(Cursor cursor) {
		int columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_EXERCISE_ID);
		this.exercise_id = cursor.getLong(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_EXERCISE_NAME);
		this.exercise_name = cursor.getString(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_EXERCISE_DESC);
		this.exercise_desc = cursor.getString(columnIndex);
	}

	public Long getId() {
		return exercise_id;
	}

	public void setId(Long exercise_id) {
		this.exercise_id = exercise_id;
	}

	public String getName() {
		return exercise_name;
	}

	public void setName(String exercise_name) {
		this.exercise_name = exercise_name;
	}

	public String getDesc() {
		return exercise_desc;
	}

	public void setDesc(String exercise_desc) {
		this.exercise_desc = exercise_desc;
	}

	@Override
	public int hashCode() {
		int hashCode = this.exercise_id.intValue();
		return hashCode;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof ExerciseTransferObject
				&& ((ExerciseTransferObject) o).exercise_id
						.equals(this.exercise_id)
				&& ((ExerciseTransferObject) o).exercise_name
						.equals(this.exercise_name)
				&& ((ExerciseTransferObject) o).exercise_desc
						.equals(this.exercise_desc)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return exercise_name;
	}

}