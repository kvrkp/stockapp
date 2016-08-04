package com.pdr;

import java.util.*; 
import java.io.*; 
import org.apache.pdfbox.util.*; 

public class PdfMerge {

	 public static void main(String[] args) throws Exception {
	  // Get the byte streams from any source (maintain order)
	  System.out.println("PDFMerge started");
	  List<InputStream> sourcePDFs = new ArrayList<InputStream>();
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 01 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 02 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 03 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 04 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 05 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 06 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 07 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 08 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 09 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 10 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 11 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 12 - Firebirds Report.pdf")));
	  sourcePDFs.add(new FileInputStream(new File("C:/myjob/projects/BMW_ID/sprints_analysis/Sprint 13 - Firebirds Report.pdf")));

	  // initialize the Merger utility and add pdfs to be merged
	  PDFMergerUtility mergerUtility = new PDFMergerUtility();
	  mergerUtility.addSources(sourcePDFs);
	  // set the destination pdf name and merge input pdfs
	  mergerUtility.setDestinationFileName("c:/temp/bmwid_sprints.pdf");
	  mergerUtility.mergeDocuments();
	  System.out.println("PDFMerge ended");
	 }
	}
