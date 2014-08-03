package pl.jrola.java.android.vigym.vigymobile.db.to;

import java.util.Date;

import android.database.Cursor;

import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;

public class ProfileInformationValueTransferObject extends TransferObject {

	private Long prof_info_val_id;
	private Date prof_info_val_date;
	private ProfileInformationTransferObject prof_info_val_info_id;
	private Double prof_info_val_value;
	private UnitTransferObject prof_info_val_unit_id;

	public ProfileInformationValueTransferObject() {
		super();
	}

	public ProfileInformationValueTransferObject(Long id, Date date,
			ProfileInformationTransferObject pito, Double value,
			UnitTransferObject unitTO) {
		super();
		this.prof_info_val_id = id;
		this.prof_info_val_date = date;
		this.prof_info_val_info_id = pito;
		this.prof_info_val_value = value;
		this.prof_info_val_unit_id = unitTO;
	}

	public ProfileInformationValueTransferObject(Cursor cursor) {
		int columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_ID);
		this.prof_info_val_id = cursor.getLong(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE);
		this.prof_info_val_date = Utils.convertStringToDate(cursor.getString(columnIndex), Utils.DATE_FORMAT);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE);
		this.prof_info_val_value = cursor.getDouble(columnIndex);

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID);
		this.prof_info_val_info_id = new ProfileInformationTransferObject();
		this.prof_info_val_info_id.setId(cursor.getLong(columnIndex));

		columnIndex = cursor.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID);
		this.prof_info_val_unit_id = new UnitTransferObject();
		this.prof_info_val_unit_id.setId(cursor.getLong(columnIndex));		
	}

	public Long getId() {
		return prof_info_val_id;
	}

	public void setId(Long prof_values_id) {
		this.prof_info_val_id = prof_values_id;
	}

	public Date getDate() {
		return prof_info_val_date;
	}
	
	public String getDateAsString() {
		return Utils.convertDateToString(prof_info_val_date, Utils.DATE_FORMAT);
	}

	public void setDate(Date prof_values_date) {
		this.prof_info_val_date = prof_values_date;
	}

	public ProfileInformationTransferObject getProfileInformationTransferObject() {
		return prof_info_val_info_id;
	}

	public void setProfileInformationTransferObject(
			ProfileInformationTransferObject profileInformationTransferObject) {
		this.prof_info_val_info_id = profileInformationTransferObject;
	}

	public Double getValue() {
		return prof_info_val_value;
	}

	public void setValue(Double value) {
		this.prof_info_val_value = value;
	}

	public UnitTransferObject getUnitTransferObject() {
		return prof_info_val_unit_id;
	}

	public void setUnitTransferObject(UnitTransferObject unitTransferObject) {
		this.prof_info_val_unit_id = unitTransferObject;
	}

	@Override
	public String toString() {
		return prof_info_val_value.toString();
	}
	
	
}