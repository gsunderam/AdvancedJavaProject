package examples;

import static log.Logger.stdout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * @author A159991 (G Sunderam)
 *
 * An utility file to convert simulator input text files having identical dates to a similar file containing ALL unequal
 * TMS timestamps. We need this for SNF testing as TMS_TIMESTAMP has a unique index.  
 */
public class GenerateSNFTextfile {
	private static final String TMS_TIMESTAMP_FORMAT = "yyyy-dd-MM HH:mm:ss";
	private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
	private static final int AMT = 98;
	private static final String DEFAULT_DIR = "C:/NOSCAN/falconProject/simulator/messageTools/";
	private static final String DEFAULT_FILE = "Stress500.txt";
	
	@SuppressWarnings("hiding")
	public static void main(String[] args) {
		if (args.length < 2)  {
			usage("/Your/path/", "yourFile.txt", "[beginYear] ", "[outputFileName] ", "[date/hour/min]");
			System.exit(0);
		}
		
		String path = null; String inputFileName = null; String outputFileName = null; int beginYear = 2010;
		int incrementField = Calendar.HOUR;
		
		//Read path
		if (args.length > 0) path = args[0];
		//Assign a default path if null
		if (path == null) {
			path = DEFAULT_DIR;
		}
		
		//Get input file name
		if (args.length > 1) inputFileName = args[1];
		if (inputFileName == null) {
			inputFileName = DEFAULT_FILE;
		}
		
		//Get Start year if specified
		if (args.length > 2) { 
			try {
				beginYear = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {}
		}
		
		//Read output file name if specified
		if (args.length > 3) outputFileName = args[3];
		if (outputFileName == null) {
			outputFileName = inputFileName.substring(0, inputFileName.indexOf('.')) + "SNF.txt";
		}
		
		//Get calendar increment field. How the date time should be incremented is specified by this
		if (args.length > 4) { 
			String calField = args[4];
			if (calField != null) {
				incrementField = (calField.equalsIgnoreCase("date") ? Calendar.DATE : (calField.equalsIgnoreCase("hour") ? Calendar.HOUR : 
					(calField.equalsIgnoreCase("min") ? Calendar.MINUTE : incrementField)));
			}
		}
		
		//create input file
		File file = new File(path + inputFileName);
		
		//Create date format
		SimpleDateFormat sdf = new SimpleDateFormat(TMS_TIMESTAMP_FORMAT);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, beginYear); //These three can be set to anything you need.
		c.set(Calendar.DATE, 1);
		c.set(Calendar.MONTH, 2);

		//create file references
		FileReader fr = null; PrintWriter pw = null; Scanner fileScanner = null;
		
		//read and write
		try {
			fr = new FileReader(file);
			fileScanner = new Scanner(fr);
			fileScanner.useDelimiter("\\n");
			pw = new PrintWriter(path + outputFileName);
			stdout("File getting created. Please wait...");
			//Iterate through the scanner
			while(fileScanner.hasNext()) {
				String line = fileScanner.next();
				line = line.replaceAll(DATE_REGEX, sdf.format(c.getTime()));
				pw.write(line);
				pw.flush();
				c.add(incrementField, 1); //Increment the hour by 1 to get unique TMS time stamps
			}
			stdout("Done!");
			stdout("Please check the generated file: " + path + outputFileName + " for accuracy");
			
		} catch(FileNotFoundException fne) {
			usage(path, inputFileName);
		} catch (IOException e) {
			stdout("IO Exception " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (fileScanner != null) fileScanner.close();
			if (pw != null) pw.close();
		}
	}
	
	private static void usage(String path, String file) {
		stdout(appendHyphen(AMT));
		stdout("Please make sure that an input file exists in your path ex: " + path + file + " ?");
		stdout("You must specify BOTH filePath and the fileName as in (1)\n");
		stdout("(1) snf C:/your/path/dir/ yourInputFile.txt");
		stdout(appendHyphen(AMT));
	}

	private static void usage(String... inputs) {
		stdout(appendHyphen(38));stdout("READ ME");stdout(appendHyphen(38));
		stdout("Please make sure that an input file exists in your path ex: " + inputs[0] + inputs[1] + " ?");
		stdout("Otherwise ask John, Uma or G Sunderam for one");
		stdout("You must specify BOTH filePath and the fileName as in (1). Optional parameters are beginYear, outputFileName and\n calendar increment Field\n");
		stdout("(1) snf C:/your/path/dir/ yourInputFile.txt " + inputs[2] + inputs[3] + inputs[4] + "\n");
		stdout("The fifth optional param if specifed must be EXACTLY \"hour\", \"date\" OR \"min\". This says HOW you want the timestamps\n to be incremented\n");
		stdout("example: snf C:/NOSCAN/falconProject/simulator/messageTools/ Test4.txt [2007] [myOutputfile.txt] [hour]");
		stdout(appendHyphen(AMT));
	}
	
	private static String appendHyphen(int amt) {
		final StringBuilder hyphen = new StringBuilder(100);
		for (int i = 0;i < amt;i++) hyphen.append("-");
		return hyphen.toString();
	}

}
