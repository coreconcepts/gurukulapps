package com.follett.connector.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

public class YamlParser {

	public static void main(String args[]) {
		YamlParser yParser = new YamlParser();
		yParser.parse();
	}

	private void parse() {
		Yaml yaml = new Yaml();
		Map<String, String> yamlMap = new HashMap<String, String>();

		try {
			InputStream input = new FileInputStream(
					new File(
							"C://Aditya//myworkspace//QAFileUpload//connectordata//src//main//java//com//follett//connector//util//configuration.yaml"));
			Object obj = yaml.load(input);
			System.out.println(obj);
			Map<String, Object> myparentHashMap = (Map<String, Object>) obj;
			Iterator<?> iterator = myparentHashMap.entrySet().iterator();
			while (iterator.hasNext()) {
			    Entry myEntry = (Entry) iterator.next();
			    System.out.println(myEntry.getKey() + " : ");
			    HashMap<String, String> childHashMaps = ((HashMap<String, String>) myEntry.getValue());
			    System.out.println("{");
			    for (Map.Entry<String, String> childHashMap : childHashMaps.entrySet()) {
			        System.out.print(childHashMap.getKey() + " : ");
			        System.out.println(childHashMap.getValue());
			    }
			    System.out.println("}");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}



