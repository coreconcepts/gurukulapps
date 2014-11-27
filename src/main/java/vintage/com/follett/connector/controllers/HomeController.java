package com.follett.connector.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.follett.connector.service.ConnectorService;
import com.follett.connector.util.DataBlock;
import com.follett.connector.util.StatusBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private ConnectorService connectorService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws SQLException 
	 */
	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws SQLException {
		com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//***************
		/*
		String sql  = "insert into gurukul.user(userId, role, location, firstname, lastname, password, securityphrase) "
				+ " values(?,?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, new Object[] { "aditya.d","branchmanager", 3, "aditya", "chaudhary", "pass1234", "thisissecure"});
	    
			*/			
		
		//**************
		
		
		

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String uploadfile(Locale locale, Model model, DataBlock dataBlock) {
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute(dataBlock);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "datainput";
	}

	@RequestMapping(value = "/startfile", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addProduct(ModelMap model,
			@RequestParam("dataFile") MultipartFile file,
			@Valid DataBlock dataBlock, BindingResult result) {
		if (result.hasErrors()) {

			return "datainput";
		}
		String fileName = "C:/Aditya/test/" + file.getOriginalFilename();

		if (file.isEmpty()) {
			result.addError(new ObjectError("Data_File_Empty",
					"Either no file has been chosen or file is empty"));
			return "datainput";
			
		} else if (!file.isEmpty()) {
			try {

				// BufferedOutputStream bos = null;
				// FileOutputStream fos = null;
				// fos = new FileOutputStream(fileName);
				// bos = new BufferedOutputStream(fos);
				// bos.write(b);

				byte[] bytes = file.getBytes();

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(fileName)));
				stream.write(bytes);
				stream.close();
				dataBlock.setProductImageLocation(fileName);

			} catch (IOException e) {
				result.addError(new ObjectError("Failed_to_upload_data_file.",
						"Failed to upload data file. Please try again."));
				return "datainput";
			}
		}
		model.addAttribute(dataBlock);
		return "dataupload";
	}

	/**
	 * Handle AJAX requests for data uploads Sends back results in a JSON.
	 * 
	 * @param model
	 * @param file
	 * @param dataBlock
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/dataupload", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody StatusBean uploadData(
			@RequestParam(value = "store", required = false) String store,
			@RequestParam(value = "campus", required = false) String campus,
			@RequestParam(value = "campusName", required = false) String campusName,
			@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "termName", required = false) String termName,
			@RequestParam(value = "fileName", required = false) String fileName,
			ModelMap model, @Valid DataBlock dataBlock, BindingResult result) {

		return connectorService.uploadData(dataBlock);
		/*} else if (statusBean.getErrorCode() < 0) {
			result.addError(new ObjectError("Error", statusBean.getErrorCode()
					+ ""));
			return "datainput";
		}
		return "";
		*/

	}
	
	@RequestMapping(value = { "/tabfileupload"}, method = RequestMethod.GET)
	public String tabUploadLink(Locale locale, Model model) {
		logger.info("Welcome tabuploadlink! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "tabuploadlink";
	}
	
	
	@RequestMapping(value = "/recipe", method = RequestMethod.GET)
	public String displayAddRecipe(Locale locale, Model model, DataBlock dataBlock, HttpServletRequest request) {
		request.getParameterValues("component_1");
		
		logger.info("Recipe Add! The client locale is {}.", locale);
		model.addAttribute(dataBlock);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "recipe/recipe-add";
	}

	

}
