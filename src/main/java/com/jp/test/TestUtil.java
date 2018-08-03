package com.jp.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jp.test.Instruction.InstructionType;

/**
 * @author Zulhizam
 * 
 *         Utility class for this test.
 * 
 */
public class TestUtil {

	/**
	 * Method to verify the date is in a weekend
	 * 
	 * @param date 
	 * @param currency 
	 * @return true for weekend, false for weekday
	 */
	public boolean isWeekend(Date date, String currency) {
		Calendar cal = Calendar.getInstance();
		
		// Week day starts Monday
		int first = 2;
		
		//Week day starts Sunday
		if (currency == "SAR" || currency == "AED") {
			first = 1;
		}
		cal.setTime(date);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int diff = dayOfWeek - first;

		switch (first) {
		case 2:
			if ((diff == 5) || (diff < 0)) {
				return true;
			}
			break;
		case 1:
			if (diff > 4) {
				return true;
			}
			break;
		}
		return false;
	}

	
	/**
	 * Parse a 2 dimensional array of data into HashMap for sorting and calculation
	 * 
	 * @param arr 2-dimensional array containing Data for instructions.  
	 * @param key The key for the returned HashMap
	 * @return HashMap containing Instructions grouped according to key
	 */
	public HashMap<Object, List<Instruction>> getHash(String[][] arr, String key) {
		HashMap<Object, List<Instruction>> h = new HashMap<>();
		Date settleDate = new Date();

		for (int i = 0; i < arr.length; i++) {
			Instruction instruct = new Instruction();
			Entity entity = new Entity();
			entity.setCode(arr[i][0]);
			entity.setName(arr[i][0]);
			instruct.setEntity(entity);

			if (arr[i][1].trim() == "B")
				instruct.setType(InstructionType.BUY);
			else
				instruct.setType(InstructionType.SELL);

			instruct.setAgreedFx(Double.parseDouble(arr[i][2]));
			instruct.setCurrency(arr[i][3]);

			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			try {
				Date iDate = sdf.parse(arr[i][4]);
				Date sDate = sdf.parse(arr[i][5]);
				Calendar c = Calendar.getInstance();

				c.setTime(sDate);
				settleDate = c.getTime();
				
				// Check the settlement date for weekends transfer 
				// Move forward a day if it is a weekend day
				while (isWeekend(settleDate, arr[i][3])) {
					c.add(Calendar.DATE, 1);
					settleDate = c.getTime();				
				}

				instruct.setSettlementDate(settleDate);
				instruct.setInstructionDate(iDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			instruct.setUnits(Integer.parseInt(arr[i][6]));
			instruct.setUnitPrice(Double.parseDouble(arr[i][7]));

			addToList(h, key, instruct);

		}
		return h;
	}

	/**
	 * Adding Instruction to the Hashmap according to mapKey
	 * 
	 * @param hash - input HashMap
	 * @param mapKey - Key for the HashMap
	 * @param inst - Instruction to be added into the HashMap
	 */
	public synchronized void addToList(HashMap<Object, List<Instruction>> hash, String mapKey, Instruction inst) {

		Object key = null;
		if (mapKey == "E") {
			key = inst.getEntity().getName();
		}
		if (mapKey == "D") {
			key = inst.getSettlementDate();
		}
		List<Instruction> instList = hash.get(key);
		// if list does not exist create it
		if (instList == null) {
			instList = new ArrayList<Instruction>();
			instList.add(inst);
			hash.put(key, instList);
		} else {
			instList.add(inst);
		}
	}

	/**
	 * Print in console the report according to type
	 * 
	 * 
	 * @param hash HashMap to be printed
	 * @param type Type of report generated
	 */
	public void printReportUSD(HashMap<Object, List<Instruction>> hash, String type) {
		HashMap<Object, Double> outHash = new HashMap<>();  // HashMap for sum of BUY instruction
		HashMap<Object, Double> inHash = new HashMap<>();   // HashMap for sum of SELL instruction
		for (Object key : hash.keySet()) {
			List<Instruction> l = hash.get(key);
			Iterator<Instruction> it = l.iterator();
			// System.out.println("key ===> " + key);
			double usdIn = 0, usdOut = 0;
			while (it.hasNext()) {
				Instruction instruct = it.next();
				if (instruct.getType() == InstructionType.BUY)
					usdOut += instruct.getAgreedFx() * instruct.getUnits() * instruct.getUnitPrice();
				else
					usdIn += instruct.getAgreedFx() * instruct.getUnits() * instruct.getUnitPrice();
			}
			// System.out.println(key + " : in >> " + usdIn + " : out >> " + usdOut);
			inHash.put(key, Double.valueOf(usdIn));
			outHash.put(key, Double.valueOf(usdOut));
		}
		System.out.println(type + " Incoming Rank (USD)");
		System.out.println("==========================\n");
		printSorted(inHash);
		System.out.println("\n" + type + " Outgoing Rank (USD)");
		System.out.println("==========================\n");
		printSorted(outHash);
		System.out.println("--------------------------\n\n\n");
	}

	/**
	 * Print out HashMap by rank based on values
	 * 
	 * @param hash Input hash to be sorted and display
	 */
	public void printSorted(HashMap<Object, Double> hash) {
		Map<Object, Double> map = sortByValues(hash);
		Set<Entry<Object, Double>> set2 = map.entrySet();
		Iterator<Entry<Object, Double>> iterator2 = set2.iterator();
		int idx = 0;
		while (iterator2.hasNext()) {
			Entry<Object, Double> me2 = iterator2.next();
			if (me2.getKey().getClass() == Date.class) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
				String formattedDate = sdf.format((Date) me2.getKey());
				System.out.print(++idx + ". " + formattedDate + ": ");
			} else
				System.out.print(++idx + ". " + me2.getKey() + ": ");
			System.out.println(me2.getValue());
		}
	}

	/**
	 * Sort HashMap according to values. Reverse the list to achieve rank. 
	 * @param map Hashmap to be sorted
	 * @return Sorted HashMap
	 */
	private HashMap<Object, Double> sortByValues(HashMap<Object, Double> map) {
		List<Entry<Object, Double>> list = new LinkedList<Entry<Object, Double>>(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator<Entry<Object, Double>>() {
			@Override
			public int compare(Entry<Object, Double> o1, Entry<Object, Double> o2) {
				if (o1.getValue() == o2.getValue())
					return 0;
				if (o1.getValue() > o2.getValue())
					return 1;
				else
					return -1;
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap<Object, Double> sortedHashMap = new LinkedHashMap<Object, Double>();
		List<Entry<Object, Double>> listDesc = new LinkedList<Entry<Object, Double>>(list);
		Collections.reverse(listDesc);
		for (Iterator<Entry<Object, Double>> it = listDesc.iterator(); it.hasNext();) {
			Map.Entry<Object, Double> entry = it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

}
