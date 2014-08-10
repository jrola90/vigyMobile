package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.DeleteProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.WhereClauseHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class ProfileInformationValuesDAOImpl implements
		ProfileInformationValuesDAO {

	private DbHelper dbHelper;

	public ProfileInformationValuesDAOImpl(DbHelper databaseHelper) {
		this.dbHelper = databaseHelper;
	}

	@Override
	public void addProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO)
			throws AddProfileInformationValueException {

		ContentValues toValues = new ContentValues();
		/*
		 * toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_ID,
		 * profileInformationValueTO.getId());
		 */
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE,
				profileInformationValueTO.getValue());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE,
				profileInformationValueTO.getDateAsString());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID,
				profileInformationValueTO.getUnitTransferObject().getId());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID,
				profileInformationValueTO.getProfileInformationTransferObject()
						.getId());

		try {
			dbHelper.insert(DbUtils.DB_TABLE_PROF_VALUES, null, toValues);
		} catch (Exception e) {
			Utils.logError(e);
			throw new AddProfileInformationValueException(e);
		}

	}

	@Override
	public List<TransferObject> getProfileInformationValueList(Long id)
			throws GetProfileInformationValueException {
		List<TransferObject> pivList = new ArrayList<TransferObject>();

		Cursor cursor = null;

		WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
		whereClauseHelper.setCaseSensitive(false);
		whereClauseHelper
				.setWhereStatement(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID
						+ "=" + "?");
		whereClauseHelper
				.setWhereStatementValues(new String[] { id.toString() });

		try {
			cursor = dbHelper.query(true, DbUtils.DB_TABLE_PROF_VALUES, null,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues(), null, null,
					null, null, null);

			while (cursor.moveToNext()) {
				ProfileInformationValueTransferObject pivto = new ProfileInformationValueTransferObject(
						cursor);

				Long unitId = pivto.getUnitTransferObject().getId();
				UnitsDAO unitsDAO = DAOFactory.createUnitsDAO(dbHelper);
				UnitTransferObject unitTransferObject = (UnitTransferObject) unitsDAO
						.getUnit(unitId);
				pivto.setUnitTransferObject(unitTransferObject);

				Long profileInformationId = pivto
						.getProfileInformationTransferObject().getId();
				ProfileInformationsDAO profileInformationDAO = DAOFactory
						.createProfileInformationsDAO(dbHelper);
				ProfileInformationTransferObject profileInformationTO = (ProfileInformationTransferObject) profileInformationDAO
						.getProfileInformation(profileInformationId);
				pivto.setProfileInformationTransferObject(profileInformationTO);

				pivList.add(pivto);
			}

		} catch (Exception e) {
			throw new GetProfileInformationValueException(e);
		}

		return pivList;
	}

	@Override
	public boolean deleteProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO)
			throws DeleteProfileInformationValueException {
		return deleteProfileInformationValue(profileInformationValueTO.getId());
	}

	@Override
	public boolean deleteProfileInformationValue(Long id)
			throws DeleteProfileInformationValueException {

		try {

			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setDbConditional(DbConditionals.AND);
			whereClauseHelper
					.setWhereStatement(DbUtils.DB_COLUMN_PROF_INFO_VAL_ID
							+ "=?");
			whereClauseHelper.setWhereStatementValues(new String[] { id
					.toString() });

			int ret = dbHelper.delete(DbUtils.DB_TABLE_PROF_VALUES,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues());

			if (ret == 1) {
				return true;
			}

			return false;

		} catch (Exception e) {
			Utils.logError(e);
			throw new DeleteProfileInformationValueException(e);
		}
	}

	@Override
	public void updateProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO)
			throws UpdateProfileInformationValueException {

		ContentValues toValues = new ContentValues();
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE,
				profileInformationValueTO.getValue());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE,
				profileInformationValueTO.getDateAsString());

		try {
			dbHelper.update(
					DbUtils.DB_TABLE_PROF_VALUES,
					toValues,
					DbUtils.DB_COLUMN_PROF_INFO_VAL_ID + "= ?",
					new String[] { profileInformationValueTO.getId().toString() });
		} catch (Exception e) {
			Utils.logError(e);
			throw new UpdateProfileInformationValueException(e);
		}
	}

	@Override
	public boolean deleteProfileInformationValueByProfileInformation(Long id)
			throws DeleteProfileInformationValueException {
		try {

			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setDbConditional(DbConditionals.AND);
			whereClauseHelper
					.setWhereStatement(DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID
							+ "=?");
			whereClauseHelper.setWhereStatementValues(new String[] { id
					.toString() });

			dbHelper.delete(DbUtils.DB_TABLE_PROF_VALUES,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues());

			return true;

		} catch (Exception e) {
			Utils.logError(e);
			throw new DeleteProfileInformationValueException(e);
		}
	}

	@Override
	public ProfileInformationValueTransferObject getLatestProfileInformationValueByDate(
			Long id) throws GetProfileInformationValueException {
		try {
			Cursor cursor = dbHelper
					.query(false,
							DbUtils.DB_TABLE_PROF_VALUES,
							new String[] {
									"MAX("
											+ DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE
											+ ")",
									DbUtils.DB_COLUMN_PROF_INFO_VAL_ID,
									DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE,
									DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID,
									DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID,
									DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE },
							DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID + "=?",
							new String[] { id.toString() }, null, null, null,
							1, null);
			while (cursor.moveToNext()) {

				int unitIndex = cursor
						.getColumnIndex(DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID);
				ProfileInformationValueTransferObject pivto = new ProfileInformationValueTransferObject(
						cursor);
				UnitsDAO unitsDAO = DAOFactory.createUnitsDAO(dbHelper);
				UnitTransferObject unitTransferObject = (UnitTransferObject) unitsDAO
						.getUnit(cursor.getLong(unitIndex));
				pivto.setUnitTransferObject(unitTransferObject);

				return pivto;
			}
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new GetProfileInformationValueException(e);
		}
	}

	@Override
	public UnitTransferObject getUnit(Long id) throws UnitGetException {

		try {
			Cursor cursor = dbHelper.query(false, DbUtils.DB_TABLE_PROF_VALUES,
					null, DbUtils.DB_COLUMN_PROF_INFO_VAL_UNIT_ID + "=?",
					new String[] { id.toString() }, null, null, null, 1, null);
			while (cursor.moveToNext())
				return new UnitTransferObject(cursor);
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new UnitGetException(e);
		}

	}

	@Override
	public Double getProgress(Long id)
			throws GetProfileInformationValueException {
		Double latestValue = this.getLatestValue(id);
		Double firstValue = this.getFirstValue(id);

		return latestValue - firstValue;
	}

	private Double getLatestValue(Long id)
			throws GetProfileInformationValueException {
		try {
			Cursor cursor = dbHelper
					.query(false,
							DbUtils.DB_TABLE_PROF_VALUES,
							new String[] {
									"MAX("
											+ DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE
											+ ")",
									DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE },
							DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID + "=?",
							new String[] { id.toString() }, null, null, null,
							1, null);
			while (cursor.moveToNext()) {
				return cursor.getDouble(1);
			}
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new GetProfileInformationValueException(e);
		}
	}

	private Double getFirstValue(Long id)
			throws GetProfileInformationValueException {
		try {
			Cursor cursor = dbHelper
					.query(false,
							DbUtils.DB_TABLE_PROF_VALUES,
							new String[] {
									"MIN("
											+ DbUtils.DB_COLUMN_PROF_INFO_VAL_DATE
											+ ")",
									DbUtils.DB_COLUMN_PROF_INFO_VAL_VALUE },
							DbUtils.DB_COLUMN_PROF_INFO_VAL_INFO_ID + "=?",
							new String[] { id.toString() }, null, null, null,
							1, null);
			while (cursor.moveToNext()) {
				return cursor.getDouble(1);
			}
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new GetProfileInformationValueException(e);
		}
	}

}