package com.pdr;

import java.io.*;
import java.util.*;


public class FolderRead {
	
	public static void main(String args[]) {
		//Read property file
		FolderRead pr = new FolderRead();
		
		String currentDir = ".";
		if (args.length > 0) {
			currentDir = args[0];
		}
		System.out.println(FolderBean.printHeader());
		pr.process(currentDir);
		
	}
	
	private void process(String currentDir) {
		File f = new File(currentDir);		
		if (f.isDirectory()) {
			if ((f.getName().equals(".svn")) || (f.getName().equals(".git")))
				return;
			String[] files = f.list(buildFileNameFilter());		
			for (int i = 0; i<files.length; i++) {
				process(currentDir + "/" + files[i]);				
			}
		} else { //It is a file
			print(f);
		}
	}
		
	/* This returns everything right now */
	private FilenameFilter buildFileNameFilter() {
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return true;
			}
		};		
		return textFilter;
	}
	
	private void print(File f) {
		String filetype = "File:";
		try {
			String fullname = f.getCanonicalPath();
			String filename = f.getName();			
			String dirname = fullname.substring(0, fullname.length()-filename.length()-1);
			if (fullname != null) {
				fullname = fullname.replaceAll(",", "-");
				filename = filename.replaceAll(",", "-");
				dirname = dirname.replaceAll(",", "-");
			}
			long lastM = f.lastModified();
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(lastM);
			
			
			int year = gc.get(Calendar.YEAR);			
			int mth = gc.get(Calendar.MONTH)+1;
			String mthStr = (mth < 10 ? "0" + mth : "" + mth);			
			int day = gc.get(Calendar.DAY_OF_MONTH);
			String dayStr = (day < 10 ? "0" + day : "" + day);
			String lastModifiedDate = year + "-" + mthStr + "-" + dayStr;
			
			long len = f.length();
			String[] files = f.list();
			int filecnt = 0;
			if (files != null) {
				filecnt = files.length;
			}
			if (f.isDirectory()) {
				filetype="Dir :";
				return; //we are not printing directories
			}
			FolderBean pdr = new FolderBean(dirname, filename, lastModifiedDate, len, filecnt, filetype); 
			System.out.println(pdr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
