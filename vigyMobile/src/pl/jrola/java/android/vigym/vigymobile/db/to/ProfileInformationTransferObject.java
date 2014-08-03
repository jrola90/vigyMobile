package pl.jrola.java.android.vigym.vigymobile.db.to;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class ProfileInformationTransferObject extends TransferObject {

	private Long prof_info_id;
	private String prof_info_name;
	private String prof_info_desc;
	private Long prof_info_user_id;

	private Double progress;
	private ProfileInformationValueTransferObject latestProfileInformationValue;

	public ProfileInformationTransferObject() {

	}

	public ProfileInformationTransferObject(Long id,
			String name, String desc, Long userId) {
		super();
		this.prof_info_id = id;
		this.prof_info_name = name;
		this.prof_info_desc = desc;
		this.prof_info_user_id = userId;
	}

	public ProfileInformationTransferObject(String name,
			String desc, Long userId) {
		this(null, name, desc, userId);
	}

	public ProfileInformationTransferObject(Cursor cursor) {

		int columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_ID);
		this.prof_info_id = cursor.getLong(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_NAME);
		this.prof_info_name = cursor.getString(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_DESC);
		this.prof_info_desc = cursor.getString(columnIndex);

		columnIndex = cursor
				.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_USER_ID);
		this.prof_info_user_id = cursor.getLong(columnIndex);
	}

	public Long getId() {
		return prof_info_id;
	}

	public void setId(Long id) {
		this.prof_info_id = id;
	}

	public String getName() {
		return prof_info_name;
	}

	public void setName(String name) {
		this.prof_info_name = name;
	}

	public String getDesc() {
		return prof_info_desc;
	}

	public void setDesc(String desc) {
		this.prof_info_desc = desc;
	}

	public Long getUserId() {
		return prof_info_user_id;
	}

	public void setUserId(Long userId) {
		this.prof_info_user_id = userId;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public ProfileInformationValueTransferObject getLatestProfileInformationValue() {
		return latestProfileInformationValue;
	}

	public void setLatestProfileInformationValue(
			ProfileInformationValueTransferObject latestProfileInformationValue) {
		this.latestProfileInformationValue = latestProfileInformationValue;
	}
}