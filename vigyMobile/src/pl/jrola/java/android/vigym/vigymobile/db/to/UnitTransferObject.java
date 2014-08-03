package pl.jrola.java.android.vigym.vigymobile.db.to;

import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class UnitTransferObject extends TransferObject {

	private Long unit_id;
	private String unit_name;
	private String unit_desc;
	private String unit_short_name;

	public UnitTransferObject() {
		super();
	}

	public UnitTransferObject(Long id, String name, String desc,
			String shortName) {
		super();
		this.unit_id = id;
		this.unit_name = name;
		this.unit_desc = desc;
		this.unit_short_name = shortName;
	}

	public UnitTransferObject(Cursor cursor) {
		int indexId = cursor.getColumnIndex(DbUtils.DB_COLUMN_UNIT_ID);
		this.unit_id = cursor.getLong(indexId);

		int indexName = cursor.getColumnIndex(DbUtils.DB_COLUMN_UNIT_NAME);
		this.unit_name = cursor.getString(indexName);

		int indexDesc = cursor.getColumnIndex(DbUtils.DB_COLUMN_UNIT_DESC);
		this.unit_desc = cursor.getString(indexDesc);

		int shortNameIndex = cursor
				.getColumnIndex(DbUtils.DB_COLUMN_UNIT_SHORT_NAME);
		this.unit_short_name = cursor.getString(shortNameIndex);
	}

	public Long getId() {
		return unit_id;
	}

	public void setId(Long id) {
		this.unit_id = id;
	}

	public String getName() {
		return unit_name;
	}

	public void setName(String name) {
		this.unit_name = name;
	}

	public String getDesc() {
		return unit_desc;
	}

	public void setDesc(String desc) {
		this.unit_desc = desc;
	}

	public String getShortName() {
		return unit_short_name;
	}

	public void setShortName(String shortName) {
		this.unit_short_name = shortName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.unit_name);
		sb.append(" (");
		sb.append(this.unit_short_name);
		sb.append(")");

		return sb.toString();
	}

}