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
	private ISymptomReader reader = null;
	private ISymptomWriter writer = null;

	// AnalyticsCounter - CONSTRUCTOR -> initializes the 'reader' (reads through the file and retrieve every symptoms' names) and 'writer' (writes the final output, 
	// that being the number of each symptom found, in a file) objects.
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) { 
		this.reader = reader;
		this.writer = writer;
	}
	
	// getSymptoms -> retrieves all the symptoms from the file.
	// > returns a list containing each of them.
	public List<String> getSymptoms() { 
		return reader.getSymptoms();
	}
	
	// countSymptoms -> counts the number of occurences of each symptom found.
	// > if it already exists, simply do '+1' to the number of occurences of that symptom
	// > otherwise add a new entry to the map with "<name_of_symptom>, <1> (first one found)"
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
	
	// sortSymptoms -> sorts the symptoms alphabetically (using TreeMap)
	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) { 
		Map<String, Integer> sortedSymtomsList = new TreeMap<>(symptoms);
		return sortedSymtomsList;
	}
	
	// writeSymptoms -> writes the final output ("<symptom name> : <number of occurences>") in a file.
	public void writeSymptoms(Map<String, Integer> symptoms) { 
		writer.writeSymptoms(symptoms);
	}
	
	public static void main(String args[]) {
		ISymptomReader reader = new ReadSymptomDataFromFile("../symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile("result.out");
		
		// first, we create a new AnalyticsCounter object (containing all the methods we'll need).
		AnalyticsCounter analyticsObj = new AnalyticsCounter(reader, writer);
		
		// then we retreive all the symptoms from the file '../symptoms.txt'.	
		List<String> symptomsList = new ArrayList<String>();
		
		// we check to see if there has been an error while reading the file (aka if it doesn't exist)
		// > if so, stop processing with an error message.
		symptomsList = analyticsObj.getSymptoms();

		if (!symptomsList.isEmpty()) {
			// next, we count the number of occurences of each symptom.
			Map<String, Integer> symptomsMap = analyticsObj.countSymptoms(symptomsList);
			
			// after that we sort the symptoms alphabetically.
			Map<String, Integer> sortedSymptomsMap = analyticsObj.sortSymptoms(symptomsMap);
			
			// finally, we write each symptom along with its number of occurences in a file ('result.out').
			analyticsObj.writeSymptoms(sortedSymptomsMap);
			
			System.out.println("Symptoms listed and sorted successfully.");
		}
	}
}
