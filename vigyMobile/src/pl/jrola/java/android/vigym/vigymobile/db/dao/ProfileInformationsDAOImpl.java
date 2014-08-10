package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.LoggedUserSingleton;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationAddException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationDeleteException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationGetListException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationUpdateException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.utils.Utils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbConditionals;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbHelper;
import pl.jrola.java.android.vigym.vigymobile.utils.db.DbUtils;
import pl.jrola.java.android.vigym.vigymobile.utils.db.WhereClauseHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class ProfileInformationsDAOImpl implements ProfileInformationsDAO {

	private DbHelper db;

	public ProfileInformationsDAOImpl(DbHelper databaseHelper) {
		this.db = databaseHelper;
	}

	@Override
	public void addProfileInformation(
			ProfileInformationTransferObject profileInformationTransferObject)
			throws ProfileInformationAddException {

		ContentValues toValues = new ContentValues();
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_NAME,
				profileInformationTransferObject.getName());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_DESC,
				profileInformationTransferObject.getDesc());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_USER_ID, LoggedUserSingleton
				.getUserTransferObject().getId());

		try {
			db.insert(DbUtils.DB_TABLE_PROF_INFO, null, toValues);
		} catch (Exception e) {
			Utils.logError(e);
			throw new ProfileInformationAddException(e.getMessage());
		}
	}

	@Override
	public List<TransferObject> getProfileInformationList(Long userId)
			throws ProfileInformationGetListException {

		List<TransferObject> profileInformationList = new ArrayList<TransferObject>();
		Cursor cursor = null;

		WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
		whereClauseHelper.setCaseSensitive(false);
		whereClauseHelper.setWhereStatement(DbUtils.DB_COLUMN_PROF_INFO_USER_ID
				+ "=" + "?");
		whereClauseHelper.setWhereStatementValues(new String[] { userId
				.toString() });

		try {
			cursor = db.query(true, DbUtils.DB_TABLE_PROF_INFO, null,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues(), null, null,
					null, null, null);

			while (cursor.moveToNext()) {

				ProfileInformationTransferObject pito = new ProfileInformationTransferObject(
						cursor);

				ProfileInformationValuesDAO profileInformationValuesDAO = DAOFactory
						.createProfileInformationValuesDAO(db);
				Double progress = profileInformationValuesDAO.getProgress(pito
						.getId());
				ProfileInformationValueTransferObject pivto = profileInformationValuesDAO
						.getLatestProfileInformationValueByDate(pito.getId());

				pito.setProgress(progress);
				pito.setLatestProfileInformationValue(pivto);
				profileInformationList.add(pito);
			}

		} catch (Exception e) {
			throw new ProfileInformationGetListException(e);
		}

		return profileInformationList;
	}

	@Override
	public boolean deleteProfileInformation(
			ProfileInformationTransferObject profileInformationTransferObject)
			throws ProfileInformationDeleteException {

		try {

			WhereClauseHelper whereClauseHelper = new WhereClauseHelper();
			whereClauseHelper.setCaseSensitive(false);
			whereClauseHelper.setWhereStatement(DbUtils.DB_COLUMN_PROF_INFO_ID
					+ "=?");
			whereClauseHelper
					.setWhereStatementValues(new String[] { profileInformationTransferObject
							.getId().toString() });

			int ret = db.delete(DbUtils.DB_TABLE_PROF_INFO,
					whereClauseHelper.getWhereStatement(),
					whereClauseHelper.getWhereStatementValues());

			if (ret == 1) {

				ProfileInformationValuesDAO profileInformationValuesDAO = DAOFactory
						.createProfileInformationValuesDAO(db);
				profileInformationValuesDAO
						.deleteProfileInformationValueByProfileInformation(profileInformationTransferObject
								.getId());

				return true;
			}

			return false;

		} catch (Exception e) {
			Utils.logError(e);
			throw new ProfileInformationDeleteException(e);
		}
	}

	@Override
	public void updateProfileInformation(
			ProfileInformationTransferObject profileInformationTransferObject)
			throws ProfileInformationUpdateException {

		ContentValues toValues = new ContentValues();
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_NAME,
				profileInformationTransferObject.getName());
		toValues.put(DbUtils.DB_COLUMN_PROF_INFO_DESC,
				profileInformationTransferObject.getDesc());

		try {
			db.update(DbUtils.DB_TABLE_PROF_INFO, toValues,
					DbUtils.DB_COLUMN_PROF_INFO_ID + "= ?",
					new String[] { profileInformationTransferObject.getId()
							.toString() });
			// db.insert(DbUtils.DB_PROF_INFO, null, toValues);
		} catch (Exception e) {
			Utils.logError(e);
			throw new ProfileInformationUpdateException(e.getMessage());
		}

	}

	@Override
	public TransferObject getProfileInformation(Long id)
			throws ProfileInformationGetException {
		try {
			Cursor cursor = db.query(false, DbUtils.DB_TABLE_PROF_INFO, null,
					DbUtils.DB_COLUMN_PROF_INFO_ID + "=?",
					new String[] { id.toString() }, null, null, null, 1, null);
			while (cursor.moveToNext())
				return new ProfileInformationTransferObject(cursor);
			return null;
		} catch (Exception e) {
			Utils.logError(e);
			throw new ProfileInformationGetException(e);
		}
	}
}