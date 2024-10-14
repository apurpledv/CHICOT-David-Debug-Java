package com.hemebiotech.analytics;

import java.util.Map;

public interface ISymptomWriter {
	/**
	 * Writes every symptom found along with their number of occurences
	 * 
	 * @param a list containing each time: <name of symptom>, <number of occurences>
	 */
	public void writeSymptoms(Map<String, Integer> symptoms);
}

