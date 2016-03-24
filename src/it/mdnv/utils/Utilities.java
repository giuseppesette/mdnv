package it.mdnv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Utilities {

	public static Date getSysdate_DDMMYYYY() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(Constants.DATE_DDMMYYYY);
		Date today = new Date();
		String oggi = format.format(today);
		return format.parse(oggi);
	}
	
	public static String getNomeLavoro(int sequence, String nomeLavoro){
		
		// Generazione del Codice Fornitore
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		// - Comporre il codice come: 2014_001_<Nome Lavoro>_R
		return yearInString + "_" + String.format("%03d", sequence) + "_" + nomeLavoro + "_R";
		
	} // END getNomeLavoro
	
	/**
	public static String getNomeLavoro(int idFolderLavoro, String nomeLavoro){
		
		// Generazione del Codice Fornitore
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		// - Comporre il codice come: 2014_001_<Nome Lavoro>_R
		return yearInString + "_" + String.format("%03d", idFolderLavoro) + "_" + nomeLavoro + "_R";
		
	} // END getNomeLavoro
	**/
	
	public static String getCodiceComposto(String prefix, int idFornitore){
		
		// Generazione del Codice Fornitore
		String nextId = String.format("%03d", idFornitore);
		System.out.println("[Utilities][getCodFornitore] - nextId padded: " + nextId);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		// - Comporre il codice come: CLI_001_2014
		return prefix + "_" + nextId + "_" + yearInString;
		
	} // END getCodFornitore
	/***
	private String getCodCliente(int idCliente){
		
		// Generazione del Codice Cliente
		String nextId = String.format("%03d", idCliente);
	//System.out.println("[ClientiMB][getCodCliente] - nextId padded: " + nextId);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		// - Comporre il codice come: CLI_001_2014
		return "CLI_" + nextId + "_" + yearInString;
		
	} // END getCodCliente
	****/
	
	
    /**
     * Ordinamento:
     * http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
     * http://stackoverflow.com/questions/780541/how-to-sort-a-hashmap-in-java
     * http://stackoverflow.com/questions/2889777/difference-between-hashmap-linkedhashmap-and-treemap
     *    HashMap makes absolutely no guarantees about the iteration order. 
     *           It can (and will) even change completely when new elements are added.
     *    TreeMap will iterate according to the "natural ordering" of the keys according 
     *           to their compareTo() method (or an externally supplied Comparator). 
     *           Additionally, it implements the SortedMap interface, which contains 
     *           methods that depend on this sort order.
     *    LinkedHashMap will iterate in the order in which the entries were put into the map
     */
    /* http://beginnersbook.com/2013/12/how-to-sort-hashmap-in-java-by-keys-and-values/ */
    /**
     *  
     * 
     * 
     * Adeguata per l'utilizzo di Map<String, String>
     * @param Map<String, String> map
     * @return Map<String, String>
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> sortByValues(Map<String, String> map) {
		// http://stackoverflow.com/questions/780541/how-to-sort-a-hashmap-in-java
		// risposta 7
		SortedSet<String> keys = new TreeSet<String>(map.keySet());
		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		Map<String, String> sortedHashMap = new LinkedHashMap();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			//Map.Entry entry = (Map.Entry) it.next();
			//sortedHashMap.put((String)entry.getKey(), (String)entry.getValue());
			String k = (String)it.next();
			sortedHashMap.put(k, map.get(k));
		}

		return sortedHashMap;
	}
} // end class
