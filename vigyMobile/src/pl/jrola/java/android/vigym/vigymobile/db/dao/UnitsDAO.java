package pl.jrola.java.android.vigym.vigymobile.db.dao;

import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitGetException;
import pl.jrola.java.android.vigym.vigymobile.db.dao.exceptions.UnitListGetException;
import pl.jrola.java.android.vigym.vigymobile.db.to.TransferObject;
import pl.jrola.java.android.vigym.vigymobile.db.to.UnitTransferObject;

public interface UnitsDAO {

	List<TransferObject> getUnitList() throws UnitListGetException;

	TransferObject getUnit(Long unitId) throws UnitGetException;
	
}