package pl.jrola.java.android.vigym.vigymobile.validator;

import java.util.ArrayList;
import java.util.List;

import pl.jrola.java.android.vigym.vigymobile.utils.Utils;

import android.content.Context;

public class ValidatorList implements Validable {

	private List<Validator> list = new ArrayList<Validator>();
	private Validator firstValiadator;
	private String errorMessage = "";
	private Context context;
	
	public ValidatorList(Context context) {
		super();
		this.context = context;
	}

	public void addValidator(Validator validator) {
		
		if (this.list.size() == 0)
			this.addFirstValidator(validator);
		else
			this.addAnotherValidator(validator);
		
		this.list.add(validator);
	}
	
	private void addFirstValidator(Validator validator) {
		this.firstValiadator = validator;
	}

	private void addAnotherValidator(Validator validator) {
		int n = this.list.size();
		Validator lastValidator = this.list.get(n - 1);
		lastValidator.nextValidator = validator;
	}

	public void removeValidator(Validator validator) {
		this.list.remove(validator);
		if (this.list.size() == 0)
			this.firstValiadator = null;
	}
	
	public void removeAll() {
		this.firstValiadator = null;
		this.list.clear();
	}

	@Override
	public boolean validate() {
		
		Validator tmpValidator = firstValiadator;
		
		while((tmpValidator) != null) {
			if (tmpValidator.validate() == false) {
				errorMessage = tmpValidator.getErrorMessages().trim();
				tmpValidator.clearErrorMessage();
				if (errorMessage.equals("") == false) {
					Utils.showToast(context, errorMessage);
				}
				return false;
			}
			tmpValidator = tmpValidator.nextValidator;
		}
		
		return true;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}