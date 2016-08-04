/*
 * ====================================================================
 * Copyright (c) 2004-2011 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */
package com.pdr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashMap;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SvnRead {
    static String sep = ",";
    static HashMap revisionMap = new HashMap();
	final static String propFile = "C:/usr/workspace/stockapp/WebContent/WEB-INF/classes/com/pdr/SvnReadUserNames.properties";

    public static void main(String[] args) {
        String url = "";
        String name = "";
        String password = "";
        String repositoryPath = "";
        setupLibrary();
        HashMap userMap = readPropertyFile();
        GregorianCalendar gc = new GregorianCalendar();
        
        System.out.println("Moddate" + sep + "ModTime" + sep + "Url" + sep + "FileType" + sep + "Filename" + sep + "Userid" + sep + 
        		"Username" + sep + "Revision" + sep + "Month" + sep + "LogComments");
        System.out.println("***** Started on " + gc.getTime());
        if (args != null) {
            url = (args.length >= 1) ? args[0] : url;
            name = (args.length >= 2) ? args[1] : name;
            password = (args.length >= 3) ? args[2] : password;
        }
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            String repositoryPathTemp = repository.getLocation().getPath();
            String repTokens[] = repositoryPathTemp.split("/");
            repositoryPath = repTokens[repTokens.length-1];
        } catch (SVNException svne) {
            System.err
                    .println("error while creating an SVNRepository for location '"
                            + url + "': " + svne.getMessage());
            System.exit(1);
        }

        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
        repository.setAuthenticationManager(authManager);

        try {
            SVNNodeKind nodeKind = repository.checkPath("", -1);
            if (nodeKind == SVNNodeKind.NONE) {
                System.err.println("There is no entry at '" + url + "'.");
                System.exit(1);
            } else if (nodeKind == SVNNodeKind.FILE) {
                System.err.println("The entry at '" + url + "' is a file while a directory was expected.");
                System.exit(1);
            }
            listEntries(repository, "", repositoryPath, userMap);
        } catch (SVNException svne) {
            System.err.println("error while listing entries: "
                    + svne.getMessage());
            System.exit(1);
        }
        /*
         * Gets the latest revision number of the repository
         */
        long latestRevision = -1;
        try {
            latestRevision = repository.getLatestRevision();
        } catch (SVNException svne) {
            System.err
                    .println("error while fetching the latest repository revision: "
                            + svne.getMessage());
            System.exit(1);
        }
        System.out.println("***** Ended on " + gc.getTime());
        System.exit(0);
    }

    /*
     * Initializes the library to work with a repository via 
     * different protocols.
     */
    private static void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }

    public static void listEntries(SVNRepository repository, String path, String repositoryPath, HashMap userMap)
            throws SVNException {
    	String filetype = "";
        Collection logEntries = null;
        String logMessage = "";
        
        Collection entries = repository.getDir(path, -1, null,
                (Collection) null);
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            SVNDirEntry entry = (SVNDirEntry) iterator.next();
            
            GregorianCalendar gc = new GregorianCalendar(); 
            gc.setTime(entry.getDate());
            
            int hour = gc.get(Calendar.HOUR);
            int minute = gc.get(Calendar.MINUTE);
            int second = gc.get(Calendar.SECOND);
            
            String shour = (hour < 10 ? "0" + hour : "" + hour);            
            String sminute = (minute < 10 ? "0" + minute : "" + minute);
            String ssecond = (second < 10 ? "0" + second : "" + second);
            
            String mtime = shour + ":" + sminute + ":" + ssecond;
            
            int month = gc.get(Calendar.MONTH)+1;
            String smonth = (month < 10 ? "0" + month : "" + month);
            String mth = gc.get(Calendar.YEAR) + "-" + smonth;
            
            int dayofmonth = gc.get(Calendar.DAY_OF_MONTH);
            String sdayofmonth = (dayofmonth < 10 ? "0" + dayofmonth : "" + dayofmonth);
            String mdate = gc.get(Calendar.YEAR) + "-" + smonth + "-" + sdayofmonth;
            
            if (path != null)
            	path = path.replace(sep, "-");
            filetype = "File";
            if (entry.getKind() == SVNNodeKind.DIR) {
            	filetype = "Dir ";
            } else {
            	if (revisionMap.containsKey(entry.getRevision())) {
            		logMessage = (String) revisionMap.get(entry.getRevision());
            	} else {
	                try {
		                logEntries = repository.log(new String[] {""}, null,
		                		entry.getRevision(), entry.getRevision(), true, true);
		                for (Iterator logentries = logEntries.iterator(); logentries.hasNext();) {
		                    SVNLogEntry logEntry = (SVNLogEntry) logentries.next();
		                    logMessage = logEntry.getMessage();
		                    if (logMessage != null) {
		                    	logMessage = logMessage.replaceAll(sep, " ");
		                    	logMessage = logMessage.replaceAll("\n", " ");
		                    	if (logMessage.length() > 100)
		                    		logMessage = logMessage.substring(0, 99);
		                    }
		                    revisionMap.put(entry.getRevision(), logMessage);
		                    break;
		                }
	                } catch (Exception e) { //Some revisions do not have logs
	                	//System.out.println("Exception:" + e.getMessage());
	                }
            	}
            	String psid = entry.getAuthor();
            	String fullname = (String) userMap.get(psid);
            	if (fullname == null)
            		fullname = psid;
            	
                System.out.println(mdate + sep + mtime + sep + repositoryPath + sep + filetype + sep + "/" + (path.equals("") ? "" : path + "/")
	                    + entry.getName() + sep + entry.getAuthor() + sep + fullname
	                    + sep + entry.getRevision() + sep + mth + sep + logMessage
	                    );
                
            }
            /*
             * Checking up if the entry is a directory.
             */
            if (entry.getKind() == SVNNodeKind.DIR) {
                listEntries(repository, (path.equals("")) ? entry.getName()
                        : path + "/" + entry.getName(), repositoryPath, userMap);
            }
        }
    }
    
	private static HashMap readPropertyFile() {
		String[] includeFile = null;
		String line = "";
		HashMap map = new HashMap();
		try {
			FileReader fr = new FileReader(propFile);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#")) continue;
				String[] tokens = line.split(",");
				if (tokens.length <= 1) continue;
				tokens[1] = tokens[1].replace(" ","");
				if (tokens[1].length() == 0) continue;
				
				String psid = tokens[0];
				String fullname = tokens[1];
				map.put(psid, fullname);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return map;
	}
}