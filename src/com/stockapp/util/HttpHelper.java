package com.stockapp.util;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.util.Enumeration;

/**
 * This class provides some helper methods for Http protocol
 * 
 * author: natamah
 * @version $Id: HttpHelper.java,v 1.34 2009/02/23 20:51:01 natamah Exp $
 */

public class HttpHelper {	
	public static String getRootUrl(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		String contextPath = req.getContextPath();		
		int pos = url.indexOf(contextPath);
		String rootUrl = url.substring(0, pos+contextPath.length());		
		return rootUrl;
	}
	
	public static String getRootUrlWithoutContext(HttpServletRequest req) {
		String url = req.getRequestURL().toString();
		String contextPath = req.getContextPath();		
		int pos = url.indexOf(contextPath);
		String rootUrlWithoutContext = url.substring(0, pos);		
		return rootUrlWithoutContext;
	}
	
	public static String getContext(HttpServletRequest req) {
		String contextPath = req.getContextPath();		
		return contextPath;
	}
	
	public static String getFullUrl(HttpServletRequest req) {
		return req.getRequestURL() + "?" + getParams(req);
	}
	
	public static boolean isLocalHost(HttpServletRequest req) {
		if (req.getRequestURL().toString().startsWith("http://localhost"))
			return true;
		return false;
	}
	
	public static boolean isLocalHost() {
		String userName = System.getProperty("user.name");
		if ((userName != null) && (! userName.startsWith("dw")))
			return true;
		return false;
	}
	
	public static String urlEncode(String url) {
		String s = "";		
		try {
			s = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public static String urlDecode(String url) {
		String s = "";		
		try {
			s = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static String getMainPartofUrl(String url) {
		int pos = url.indexOf("?");
		return url.substring(0, pos);
	}
	
	public static HashMap getMapFromParams(String str) {
		HashMap map = new HashMap();
		String tokens[] = str.split("&");
		String param = "";
		int tokencnt = tokens.length;
		for (int i=0; i<tokencnt; i++) {
			param = tokens[i];
			String token2[] = param.split("=");
			if (token2.length == 1)
				map.put(token2[0], "");
			else
				map.put(token2[0], token2[1]);
		}
		return map;
	}

	public static String getParamsFromMap(HashMap map) {
		StringBuffer sb = new StringBuffer();
		Set set = map.keySet();
		Iterator iter = set.iterator();
		int i=0;
		while (iter.hasNext()) {
			if (i > 0)
				sb.append("&");
			String key = (String) iter.next();
			String value = (String) map.get(key);
			sb.append(key + "=" + value);
			i++;
		}
		return sb.toString();
	}
	
	public static String getIP(HttpServletRequest req) {
		return req.getRemoteAddr();
	}
	
	public static String getParams(HttpServletRequest req) {
		Map map = req.getParameterMap();
		return MapUtility.MapToString(map);
	}
	
	public static String removeParam(String url, String param) {
		StringBuffer sb = new StringBuffer();
		if (url.indexOf(param) >= 0) {
			int pos = url.indexOf(param);
			sb.append(url.substring(0, pos-1));
			int pos2 = url.indexOf("&", pos+1);
			if (pos2 >= 0) {
				sb.append("&" + url.substring(pos2+1));
			}
		} else {
			sb.append(url);
		}
		String s = sb.toString();
		return s;
	}
	
	public static String removeParamFromUrl(String url, String param) {
		StringBuffer sb = new StringBuffer();
		if (url.indexOf(param) >= 0) {
			int pos = url.indexOf(param);
			int pos2 = url.indexOf("&", pos+1);
			if (pos2 >= 0) {
				sb.append(url.substring(0, pos));
				sb.append(url.substring(pos2+1));
			}
			else
			{
				sb.append(url.substring(0, pos-1));
			}
		} else {
			sb.append(url);
		}
		String s = sb.toString();
		return s;
	}
	
	public static String getAttributes(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		Enumeration enumNames = req.getAttributeNames();
		while (enumNames.hasMoreElements()) {
			String attrname = (String) enumNames.nextElement();
			Object attrvalue = req.getAttribute(attrname);
			sb.append(attrname);
			sb.append("=");
			if (attrvalue != null)
				sb.append(attrvalue.toString());
			else
				sb.append("null");
			sb.append(",");
		}
		return sb.toString();
	}

	public static String getHeaders(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		Enumeration enumNames = req.getHeaderNames();
		while (enumNames.hasMoreElements()) {
			String attrname = (String) enumNames.nextElement();
			Object attrvalue = req.getAttribute(attrname);
			sb.append(attrname);
			sb.append("=");
			if (attrvalue != null)
				sb.append(attrvalue.toString());
			else
				sb.append("null");
			sb.append(",");
		}
		return sb.toString();
	}
	
}
