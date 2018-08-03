package com.jp.test;

import java.util.HashMap;
import java.util.List;
/**
 * App for sorting Instructions
 *
 */
public class App {

	public static void main(String[] args) {
		
		String[][] data = { { "foo", "B", "0.50", "SGP", "01 Jan 2016", "02 Jan 2016", "200", "100.25" }, { "foo", "S", "0.50", "SGP", "01 Jan 2016", "02 Jan 2016", "200", "100.25" },
				{ "bar", "S", "0.22", "AED", "05 Jan 2016", "07 Jan 2016", "450", "150.5" },{ "bar", "B", "0.18", "SAR", "05 Jan 2016", "07 Jan 2016", "250", "10.5" },
				{ "bar", "S", "0.50", "SGD", "05 Feb 2016", "07 Feb 2016", "300", "120.5" }, { "sef", "B", "0.22", "JPY",  "01 Jan 2016", "02 Jan 2016", "50", "250" },
				{ "sef", "S", "0.22", "JPY", "11 Jun 2016", "12 Jun 2016", "5000", "250" }};
		
		// Aggregate the data according to requirement in HashMap 
		// HashMap for Instructions with date as the key
		HashMap<Object, List<Instruction>> dailyHash = new HashMap<>();
		// HashMap for Instructions with entity as key
		HashMap<Object, List<Instruction>> entityHash = new HashMap<>(); 
		
		TestUtil util = new TestUtil();
		dailyHash = util.getHash(data, "D");
		entityHash = util.getHash(data, "E");
		
		util.printReportUSD(entityHash,"Entity");
		util.printReportUSD(dailyHash, "Daily");
	}
	
}
