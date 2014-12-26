package com.guru.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSlf4j {
	public static final Logger logger = LoggerFactory.getLogger(TestSlf4j.class);

		 public static void main(String args[]) {

		  String name = "lordofthejars";

		  logger.info("Hello from Bar.");


		  logger.debug("In bar my name is {}.", name);

		 }

		}


