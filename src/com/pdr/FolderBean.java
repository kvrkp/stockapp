package com.pdr;

public class FolderBean {
	String dirname;
	String filename;
	String modifiedDate;
	long length;
	int filecnt;
	String filetype;
	
	public FolderBean() {
		
	}
	public FolderBean(String dirname, String filename, String modifiedDate,	long length, 
			int filecnt, String filetype) {
		super();
		this.dirname = dirname;
		this.filename = filename;
		this.modifiedDate = modifiedDate;
		this.length = length;
		this.filecnt = filecnt;
		this.filetype=filetype;
	}
	
	public String getExtension() {
		String extension = "";
		//Get the extension
		if (filename != null) {
			int extpos = filename.lastIndexOf(".");
			if (extpos >= 0) {
				extension = filename.substring(extpos+1);
			}
		}
		return extension;
	}
	
	public String toString() {
		return (dirname + "," + filename + "," + getExtension() + "," + modifiedDate + "," + length);
	}
	
	public static String printHeader() {
		return ("Dir,File,Ext,Mod Date,Length");		
	}
	public String printFooter(PdrPropertyBean pdrprop) {
		return ("-------------------------------------------------------");
	}
}
