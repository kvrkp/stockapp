package com.pdr;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class PdrRead {
	final static String propFile = "C:/usr/workspace/stockapp/build/com/pdr/PdrRead.properties";
	
	public static void main(String args[]) {
		//Read property file
		PdrRead pr = new PdrRead();
		PdrBean pdrb = new PdrBean();
		
		PdrPropertyBean pdrprop = pr.readPropertyFile();
		
		System.out.println(pdrb.printHeader(pdrprop));
		PdrParamBean bean = new PdrParamBean();
		
		switch (args.length) {
		case 0:
			bean.setCurrentDir(".");
			bean.setFromDate(null);
			break;
		case 1:
			bean.setCurrentDir(args[0]);
			bean.setFromDate(null);
			break;
		case 2:
			bean.setCurrentDir(args[0]);
			GregorianCalendar gc = new GregorianCalendar();
			int year = Integer.parseInt(args[1].substring(0,4));
			int month = Integer.parseInt(args[1].substring(4,6));
			int day = Integer.parseInt(args[1].substring(6,8));
			gc.set(year, month-1, day);
			bean.setFromDate(gc.getTime());
			break;			
		}
		pr.process(bean, pdrprop);
		System.out.println(pdrb.printFooter(pdrprop));
	}
	
	private PdrPropertyBean readPropertyFile() {
		String outputType = "csv";
		String[] excludeDir = null;
		String[] excludeFile = null;
		String[] includeDir = null;
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
				
				String firstItem = tokens[0];
				if (firstItem.equals("Output")) {
					outputType = tokens[1].trim();
				} else if (firstItem.equals("Drive")) {
					String drive = tokens[2].trim();
					String sharepointFolder = tokens[3].trim();
					map.put(drive, sharepointFolder);					
				} else if (firstItem.equals("ExcludeDir")) {
					excludeDir = tokens[1].split(" ");
				} else if (firstItem.equals("ExcludeFile")) {
					excludeFile = tokens[1].split(" ");					
				} else if (firstItem.equals("IncludeDir")) {
					includeDir = tokens[1].split(" ");
				} else if (firstItem.equals("IncludeFile")) {
					includeFile = tokens[1].split(" ");					
				}			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdrPropertyBean bean = new PdrPropertyBean();
		bean.setOutputType(outputType);
		bean.setSharePointFolder(map);
		bean.setExcludeDir(excludeDir);
		bean.setExcludeFile(excludeFile);
		bean.setIncludeDir(includeDir);
		bean.setIncludeFile(includeFile);
		
		return bean;
	}
	private void process(PdrParamBean bean, PdrPropertyBean pdrprop) {
		File f = new File(bean.getCurrentDir());		
		if (f.isDirectory()) {
			print(f, bean, pdrprop);
			//String[] files = f.list();
			String[] files = f.list(buildFileNameFilter(pdrprop));		
			if (files == null)
				return;
			for (int i = 0; i<files.length; i++) {
				PdrParamBean bean2 = new PdrParamBean(bean.getCurrentDir() + "/" + files[i], bean.getFromDate());
				process(bean2, pdrprop);				
			}
		} else { //It is a file
			print(f, bean, pdrprop);
		}
	}
		
	private FilenameFilter buildFileNameFilter(final PdrPropertyBean pdrprop) {
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (pdrprop.getExcludeDir() != null) {
					//System.out.println("Mahesh 1:" + name);
					for (int i=0; i<pdrprop.getExcludeDir().length; i++) {
						if (lowercaseName.equals(pdrprop.getExcludeDir()[i])) {
							return false;
						}
					}
				}
				if (pdrprop.getExcludeFile() != null) {
					//System.out.println("Mahesh 2:" + name);
					for (int i=0; i<pdrprop.getExcludeFile().length; i++) {
						if (lowercaseName.endsWith(pdrprop.getExcludeFile()[i])) {
							return false;
						}
					}
				}
				/* This has some problem
				if (pdrprop.getIncludeDir() != null) {
					System.out.println("Mahesh 3:" + name + "," + dir.getName());
					for (int i=0; i<pdrprop.getIncludeDir().length; i++) {
						if (dir.isDirectory() && (! lowercaseName.equals(pdrprop.getIncludeDir()[i]))) {
							return false;
						} 
					}
				}
				*/
				if (pdrprop.getIncludeFile() != null) {
					//System.out.println("Mahesh 4:" + name);
					for (int i=0; i<pdrprop.getIncludeFile().length; i++) {
						if (! lowercaseName.endsWith(pdrprop.getIncludeFile()[i])) {
							return false;
						} 
					}
				}
				
				return true;
			}
		};		
		return textFilter;
	}
	
	private void print(File f, PdrParamBean bean, PdrPropertyBean pdrprop) {
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
			
			//check for last modified date
			if ((bean.getFromDate() != null) && (bean.getFromDate().compareTo(gc.getTime()) >= 0)) {
				return;				
			}

			String lastModifiedDate = gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH)+1) + "-" + gc.get(Calendar.DAY_OF_MONTH);
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
			PdrBean pdr = new PdrBean(dirname, filename, lastModifiedDate, len, filecnt, filetype, pdrprop); 
			System.out.println(pdr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
