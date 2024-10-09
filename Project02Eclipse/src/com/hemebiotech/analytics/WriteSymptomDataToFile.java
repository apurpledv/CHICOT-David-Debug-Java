package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WriteSymptomDataToFile implements ISymptomWriter {
	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public WriteSymptomDataToFile (String filepath) {
		this.filepath = filepath;
	}
	
	public void writeSymptoms(Map<String, Integer> symptoms) {
		try {
			FileWriter writer = new FileWriter (filepath);
			for (Map.Entry<String, Integer> eachSymptom : symptoms.entrySet()) {
				writer.write(eachSymptom.getKey() + " : " + eachSymptom.getValue() + "\n");
			}
			 writer.close();		
		} catch (Exception e) {
			System.out.println("Error! Cannot write within the file named '" + filepath + "'.");
		}
	}
	
	
}