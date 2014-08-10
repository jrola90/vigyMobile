package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitListGetException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import android.database.Cursor;

public class UnitsDAOImpl implements UnitsDAO {

	private DbHelper databaseHelper;

	public UnitsDAOImpl(DbHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	@Override
	public List<TransferObject> getUnitList() throws UnitListGetException {

		List<TransferObject> unitList = new ArrayList<TransferObject>();

		Cursor cursor = null;

		try {
			cursor = databaseHelper.query(true, DbUtils.DB_TABLE_UNITS, null,
					null, null, null, null, null, null, null);

			while (cursor.moveToNext()) {
				unitList.add(new UnitTransferObject(cursor));
			}
		} catch (Exception e) {
			throw new UnitListGetException(e);
		}

		return unitList;
	}

	@Override
	public TransferObject getUnit(Long unitId) throws UnitGetException {

		try {
			Cursor cursor = databaseHelper.query(false, DbUtils.DB_TABLE_UNITS,
					null, DbUtils.DB_COLUMN_UNIT_ID + "=?",
					new String[] { unitId.toString() }, null, null,
					DbUtils.DB_COLUMN_UNIT_ID, 1, null);
			while (cursor.moveToNext())
				return new UnitTransferObject(cursor);
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new UnitGetException(e);
		}
	}

}
