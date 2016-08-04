<%@ page import="java.util.*" %>
<html>

<head>
<title>System Properties</title>
</head>
<body bgcolor='#ffffff'>

<h1>Memory</h1>
<%
out.println("Total memory = " + Runtime.getRuntime().totalMemory()/1024/1024 + " MB <BR>");
out.println("Free memory = " + Runtime.getRuntime().freeMemory()/1024/1024 + " MB <BR>");
%>
<h1>System Properties v1</h1>
<%
    /**
     * Dump the system properties
     */
    java.util.Enumeration e=null;
    try {
        e= System.getProperties().propertyNames();
    } catch (SecurityException se) {
    }
    ArrayList list = new ArrayList();
    if(e!=null) {
        for (;e.hasMoreElements();) {
            String key = (String) e.nextElement();
            String s = key + "=" + System.getProperty(key)+"<BR>";
            list.add(s);
            //out.println(s);
        }
    } else {
        out.write("sysPropError");
    }
    Collections.sort(list);
    Iterator iter = list.iterator();
    while (iter.hasNext()) {
    	String s = (String) iter.next();
    	out.println(s);
    }
%>
</body>
</html>