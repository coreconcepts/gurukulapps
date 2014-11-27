package com.follett.connector.util;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author adchaudhary Sample start file: store=9948
 *         campus=9AE60CEB-8A4C-4890-8EFA-0EE60C4DB500 campusName=includED_9948
 *         term=SummerUC1 2014 termName=SummerUC1 2014
 */

public class DataBlock {

	@Size(min=1, message = "Store is required.")
	private String store = "9948";
	@NotBlank(message = "Campus is required")
	private String campus="9AE60CEB-8A4C-4890-8EFA-0EE60C4DB500";
	@NotBlank(message = "Campus Name is required.")
	private String campusName="includED_9948";
	@NotBlank(message = "Campus Name is required.")
	private String term="SummerUC1 2014";
	@NotBlank(message = "Term Name is required.")
	private String termName="SummerUC1 2014";
	
	MultipartFile dataFile;
	// Not direct user input
	String fileName;

	public DataBlock() {
		if (dataFile != null) {
			this.setFileName(dataFile.getOriginalFilename());
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String storeId) {
		this.store = storeId;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campusId) {
		this.campus = campusId.toUpperCase();
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public MultipartFile getDataFile() {
		return dataFile;
	}

	public void setDataFile(MultipartFile dataFile) {
		this.dataFile = dataFile;
	}

	public void setProductImageLocation(String fileName) {
		// TODO Auto-generated method stub

	}

}
