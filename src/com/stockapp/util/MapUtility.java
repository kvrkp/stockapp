package com.stockapp.util;

/**
 * This class provides some helper methods for Maps
 * 
 * author: natamah
 * @version $Id: MapUtility.java,v 1.23 2009/02/06 20:31:43 natamah Exp $
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MapUtility {
	final static String quote = "'";
	final static String comma = ",";
	
	public static void printMap(Map r) {
		Iterator i = r.entrySet().iterator();
		while (i.hasNext()) {
			System.out.println((String) i.next().toString());
		}
	}
	
	/*
	 * This method copies all elements in map to newmap that are in set s
	 */
	public static Map pruneMap(Map map, Set s) {
		HashMap newMap = new HashMap();
		if (s == null)
			return newMap;
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (s.contains(key)) {
				Object obj = map.get(key);
				newMap.put(key, obj);
			}
		}
		return newMap;
	}
	
	public static Map pruneMap(Map map, Map s) {
		HashMap newMap = new HashMap();
		if (s == null)
			return newMap;
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (s.containsKey(key)) {
				Object obj = map.get(key);
				newMap.put(key, obj);
			}
		}
		return newMap;
	}
	
	public static String MapToString(Map map) {
		StringBuffer sb = new StringBuffer();
		Set set = map.keySet();
		Iterator iter = set.iterator();
		int i=0;
		while (iter.hasNext()) {
			i++;
			if (i > 1)
				sb.append("&");
			String key = (String) iter.next();
			Object value = map.get(key);
			if (value == null)
				value = "";
			if (value instanceof String[]) {
				String[] str = (String[]) value;
				sb.append(key + "=" + str[0]);
			} else if (value instanceof HashSet) {
				sb.append(key + "=" + SetToCsv((HashSet)value));
			} else {
				sb.append(key + "=" + value.toString());
			}
		}
		return sb.toString();
	}

	public static String CollectionToString(Collection coll, String sep) {
		StringBuffer sb = new StringBuffer();
		Iterator iter = coll.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			sb.append(obj.toString() + sep);
		}
		return sb.toString();
	}
	
	public static String ResultSetToTable(ArrayList list, boolean clob) {
		//return 200 rows at most
		return ResultSetToTable(list, "200", clob);
	}
	
	public static String ResultSetToTable(ArrayList list, String count, boolean clob) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbheader = new StringBuffer();
		StringBuffer sbdetail = new StringBuffer();
		
		if (list.size() == 0) {
			sbheader.append("<TD>No rows</TD>");
		}
		
		if (list.size() == 1) { //It contains a result set object
			Object obj = list.get(0);
			if (obj instanceof java.sql.ResultSet) {
				java.sql.ResultSet rs = (java.sql.ResultSet) obj; 
				return ResultSetToTable(rs, count, clob);
			}
		}
		return "";
		
	}
	
	
	public static String ResultSetToTable(java.sql.ResultSet rs, String count, boolean clob) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sbheader = new StringBuffer();
		StringBuffer sbdetail = new StringBuffer();
		int row = 0;
		int colcount = 0;
		int countnum = Integer.parseInt(count);
		ArrayList colTypeList = new ArrayList();
		java.sql.ResultSetMetaData metadata = null;
		try {
			while (rs.next()) {
				if (row == 0) {
					metadata = rs.getMetaData();
					colcount = metadata.getColumnCount();
					sbheader.append("<TR>");
					for (int i=0; i<colcount; i++) {
						sbheader.append("<TD>" + metadata.getColumnName(i+1) + "</TD>");
						String colType = metadata.getColumnClassName(i+1);
						String colName = metadata.getColumnName(i+1);
						colTypeList.add(colType);
					}
					sbheader.append("</TR>");
				}
				sbdetail.append("<TR>");
				for (int i=0; i<colcount; i++) {
					if (colTypeList.get(i).equals("oracle.sql.CLOB")) { 	
						if (clob) {
							sbdetail.append("<TD>" + getValueFromClob(rs.getClob(i+1)) + "</TD>");
						} else {
							sbdetail.append("<TD>" + getValueFromClob(rs.getClob(i+1), 20) + "</TD>");
						}
					}
					else {
						if (clob) {
							sbdetail.append("<TD>" + rs.getString(i+1) + "</TD>");
						} else {
							String s = rs.getString(i+1);
							String colName = metadata.getColumnName(i+1);
							if (s == null)
								s = "null";
							else if (s.length() > 30)
								s = s.substring(0, 30) + "...";
							sbdetail.append("<TD>" + s + "</TD>");
						}
					}
				}
				sbdetail.append("</TR>");
				row++;
				if (row >= countnum)
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append("<TABLE CLASS=\"sortable\" BORDER=1><TR>" + sbheader + "</TR>" + sbdetail + "</TABLE>");
		return sb.toString();
	}
	
	public static String SetToCsv(HashSet set) {
		Iterator iter = set.iterator();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		String s = "";
		while (iter.hasNext()) {
			Object obj = iter.next();
			
			if (obj instanceof String)
				s = (String) obj;
			if (obj instanceof Integer)
				s = ((Integer) obj).toString();
			
			if (s.indexOf(quote) < 0)
				sb.append(quote + s.trim() + quote);
			else
				sb.append(s.trim());
			if (i > 0)
				sb.append(comma);
			i++;
		}
		return sb.toString();
	}

	public static HashSet CsvToSet(String s) {
		HashSet set = new HashSet(); 
		String[] tokens = s.split(",");
		for (int i=0; i<tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}
	
    public static String getValueFromClob(java.sql.Clob myclob) throws SQLException {
    	return getValueFromClob(myclob, 1000); 
    }    
    
    public static String getFullValueFromClob(java.sql.Clob myclob) throws SQLException {
    	return getValueFromClob(myclob, 20000); 
    }    
    
    public static String getValueFromClob(java.sql.Clob myclob, int count) throws SQLException {
    	if (myclob == null)
    		return null;
		java.io.Reader reader = myclob.getCharacterStream();
		StringBuffer sb = new StringBuffer();
		char[] b = new char[1];
		
		try {
			int i=0;
			while (reader.read(b) > 0) {
				i++;
				if (i> count)
					break;
				sb.append(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sb.toString();
    }    
}
