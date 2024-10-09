package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

public class AnalyticsCounter {
	private static ISymptomReader reader = null;
	private static ISymptomWriter writer = null;

	// AnalyticsCounter - CONSTRUCTOR -> initialize the 'reader' (reads through the file and retrieve every symptoms' names) and 'writer' (writes the final output, 
	// that being the number of each symptom found, in a file) objects.
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) { 
		this.reader = reader;
		this.writer = writer;
	}
	
	// getSymptoms -> retrieve symptoms from the file and returns a list containing each of them.
	public List<String> getSymptoms() { 
		return reader.GetSymptoms();
	}
	
	// countSymptoms -> counts the number of occurences of each symptom found.
	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> symptomsCounter = new HashMap<String, Integer>();
		for (String symptomName : symptoms) {
			if (symptomsCounter.containsKey(symptomName)) {
				symptomsCounter.put(symptomName, symptomsCounter.get(symptomName) + 1);
			} else {
				symptomsCounter.put(symptomName, 1);
			}
		}
		return symptomsCounter;
	}
	
	// sortSymptoms -> sort the symptoms alphabetically
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) { 
		Map<String, Integer> sortedSymtomsList = new TreeMap<>(symptoms);
		return sortedSymtomsList;
	}
	
	// writeSymptoms -> writes the final output ("<symptom name> : <number of occurences>")in a file.
	public void writeSymptoms(Map<String, Integer> symptoms) { 
		writer.writeSymptoms(symptoms);
	}
	
	public static void main(String args[]) throws Exception {
		/*
		// first get input
		BufferedReader reader = new BufferedReader (new FileReader("../symptoms.txt"));
		String line = reader.readLine();

		int i = 0;
		int headCount = 0;	// counts headaches
		while (line != null) {
			i++;
			System.out.println("symptom from file: " + line);
			if (line.equals("headache")) {
				headacheCount++;
			}
			else if (line.equals("rash")) {
				rashCount++;
			}
			else if (line.contains("pupils")) {
				pupilCount++;
			}

			line = reader.readLine();	// get another symptom
		}
		
		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + headacheCount + "\n");
		writer.write("rash: " + rashCount + "\n");
		writer.write("dialated pupils: " + pupilCount + "\n");
		writer.close();*/
	}
}
