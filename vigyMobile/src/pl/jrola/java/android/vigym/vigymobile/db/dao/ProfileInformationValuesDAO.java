package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.AddProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.DeleteProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.GetProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UpdateProfileInformationValueException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationValueTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;

public interface ProfileInformationValuesDAO {

	void addProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO)
			throws AddProfileInformationValueException;

	List<TransferObject> getProfileInformationValueList(Long id)
			throws GetProfileInformationValueException;

	boolean deleteProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO)
			throws DeleteProfileInformationValueException;

	boolean deleteProfileInformationValue(Long id)
			throws DeleteProfileInformationValueException;

	void updateProfileInformationValue(
			ProfileInformationValueTransferObject profileInformationValueTO) throws UpdateProfileInformationValueException;

	boolean deleteProfileInformationValueByProfileInformation(Long id) throws DeleteProfileInformationValueException;

	ProfileInformationValueTransferObject getLatestProfileInformationValueByDate(Long id) throws GetProfileInformationValueException;
	
	UnitTransferObject getUnit(Long id) throws UnitGetException;
	
	Double getProgress(Long id) throws GetProfileInformationValueException;
	
}