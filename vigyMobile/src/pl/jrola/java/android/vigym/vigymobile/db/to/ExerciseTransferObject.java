package pl.jrola.java.android.vigym.vigymobile.db.to;

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
}