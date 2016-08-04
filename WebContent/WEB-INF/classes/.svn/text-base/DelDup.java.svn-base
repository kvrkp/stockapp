import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class DelDup {
	String infile = "C:/temp/nfsae/a2.txt";
	String outfile1 = "C:/temp/nfsae/out_kept.txt";
	String outfile2 = "C:/temp/nfsae/out_deleted.txt";
	String outfile3 = "C:/temp/nfsae/out_sql.txt";
	String deleteSql = "delete from scbbth.acct_txn where id = ";
	
	public static void main(String args[]) {
		System.out.println("Starting program");		
		DelDup deldup = new DelDup();
		int end = 999999;
		if (args.length > 0)
			end = Integer.parseInt(args[0]);
		deldup.delDupLines(end);
		System.out.println("Ending program");
	}
	
	public void delDupLines(int end) {
		String line = "";
		String comma = ",";
		String prevApproverSeqNo = "";
		int row = 0;
		int deleterow = 0;
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw1 = null;
		FileWriter fw2 = null;
		FileWriter fw3 = null;
		try {
			fr = new FileReader(infile);
			br = new BufferedReader(fr);
			fw1 = new FileWriter(outfile1);
			fw2 = new FileWriter(outfile2);
			fw3 = new FileWriter(outfile3);
			while ((line = br.readLine()) != null) {
				row++;
				if (row == 1) continue; //header
				String[] tokens = line.split(comma);
				String approverSeqNo = tokens[0];
				String id = tokens[1];
				if (approverSeqNo.equals(prevApproverSeqNo)) {
					//This is to be deleted
					deleterow++;
					fw2.write(line + "\n");
					String deleteLine = deleteSql + id + ";\n";
					fw3.write(deleteLine);
					if (deleterow % 100 == 0) 
						fw3.write("commit;\n");
				} else {
					fw1.write(line + "\n");
				}
				prevApproverSeqNo = approverSeqNo;
				if (row % 100 == 0)
					System.out.println( row + " lines done");
				if (row > end) break;
			}
			fw3.write("commit;\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
				fw1.close();
				fw2.close();
				fw3.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
