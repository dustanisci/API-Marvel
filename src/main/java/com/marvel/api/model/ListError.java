package com.marvel.api.model;

import java.util.ArrayList;
import java.util.List;

public class ListError {
	private List<String> errorList = new ArrayList<String>();

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(String error) {
		this.errorList.add(error);
	}

}
