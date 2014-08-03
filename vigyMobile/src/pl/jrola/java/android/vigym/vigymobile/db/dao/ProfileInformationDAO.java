package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationAddException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationDeleteException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationGetListException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.ProfileInformationUpdateException;
import pl.jrola.java.android.vigym.vigymobile.db.to.ProfileInformationTransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;

public interface ProfileInformationDAO {
	
	public void addProfileInformation(ProfileInformationTransferObject profileInformationTransferObject) throws ProfileInformationAddException;
	public TransferObject getProfileInformation(Long id) throws ProfileInformationGetException;
	public List<TransferObject> getProfileInformationList(Long userId) throws ProfileInformationGetListException;
	public boolean deleteProfileInformation(ProfileInformationTransferObject profileInformationTransferObject) throws ProfileInformationDeleteException;
	public void updateProfileInformation(
			ProfileInformationTransferObject profileInformationTransferObject) throws ProfileInformationUpdateException;
	
}
