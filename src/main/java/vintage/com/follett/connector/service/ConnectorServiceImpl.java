package com.follett.connector.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.follett.connector.util.DataBlock;
import com.follett.connector.util.StatusBean;

@Component
public class ConnectorServiceImpl implements ConnectorService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ConnectorServiceImpl.class);
	
	@Value("${datafiledestination}")
	private String dataFileDestination;
	@Value("${startfiledestination}")
	private String startFileDestination;
	@Value("${datafilesource}")
	private String dataFileSource;
	@Value("${startfilesource}")
	private String startFileSource;
	@Value("${startfilename}")
	private String startFileName;
	@Value("${filecheckinterval}")
	private int fileCheckInterval;
	@Value("${status.success.filedeleted}")
	private int statusSucFileDel;

	
	/**
	 * Move files to server locations.
	 * Keep checking every 30 seconds to find if files have been taken out.
	 * Report back if files are gone.
	 * If files are not gone after 5 minutes, report back an error.
	 * 
	 * Sample start file: store=9948
 *    campus=9AE60CEB-8A4C-4890-8EFA-0EE60C4DB500 campusName=includED_9948
 *         term=SummerUC1 2014 termName=SummerUC1 2014
	 */
	
	private final String const_store		="store";
	private final String const_campus		="campus";
	private final String const_campusName	="campusName";
	private final String const_term			="term";
	private final String const_termName		="termName";
	
	
	public StatusBean uploadData(DataBlock dataBlock) {
		StatusBean uploadStatus = new StatusBean();
		// Create start file
		PrintWriter writer;
		try {
			writer = new PrintWriter(startFileSource+ "/"+startFileName, "UTF-8");
		
		writer.println(const_store+":"+dataBlock.getStore());
		writer.println(const_campus+":"+dataBlock.getCampus());
		writer.println(const_campusName+":"+dataBlock.getCampusName());
		writer.println(const_term+":"+dataBlock.getTerm());
		writer.print(const_termName+":"+dataBlock.getTermName());
		writer.close();
		
		
		// Copy data file
		uploadStatus = copyFile(dataBlock.getFileName(),dataFileSource, dataFileDestination); 
		if(uploadStatus.getErrorCode() >=0){
			// Copy start file
			uploadStatus = copyFile(startFileName, startFileSource, startFileDestination);
			
			
			
			if(uploadStatus.getErrorCode()>=0){
				System.out.println("Check -1 ");
				// Check if files picked up.
				//
				uploadStatus = this.checkFilePresence();
				System.out.println("Check -2 ");
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uploadStatus;
	}
	
	private StatusBean checkFilePresence(){
		StatusBean statusBean = new StatusBean();
		int counter  = 5; 
		try {
			Thread.sleep(10000);
			for(;counter>0;counter--){
				if(!new File(startFileDestination+ "/" + startFileName).exists()){
					statusBean.setErrorCode(statusSucFileDel);
					break;
				}
				Thread.sleep(15000);
        		 
        	}
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return statusBean;
	}
	
	private StatusBean copyFile(String fileName, String source, String dest){
		try{	
			FileUtils.copyFile(new File(source + "/"+ fileName), new File(dest + "/"+ fileName));
		}
		catch( IOException exception){
			exception.printStackTrace();
			StatusBean errorBean = new StatusBean();
			errorBean.setErrorCode(-1);
			errorBean.setErrorMessage(exception.getMessage());
			return errorBean;
	}
	return new StatusBean();

}


}
