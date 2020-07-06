import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logger {
	final static String LOGS_PATH = "Logs";
	static String fileName = LOGS_PATH + "\\Log" + new SimpleDateFormat("[yyyy-MM-dd_HH-mm-ss]").format(Calendar.getInstance().getTime()) + ".txt";
	static File exportFile = new File(fileName);
	static BufferedWriter writer;
	
	
	
	public static void log(String message){
		message = new SimpleDateFormat("[yyyy-MM-dd_HH-mm-ss]").format(Calendar.getInstance().getTime()) + message;
		
		if(!exportFile.exists()) {
			CreateFile();
		}
		
		System.out.print(message);
		try {
			writer.write(message);
			
		} catch (IOException e) {
			System.out.println("Error! Trying to write to Log file: " + e);
			e.printStackTrace();
		}
		
	}
	
	public static void logln(String message) {
		message = new SimpleDateFormat("[yyyy-MM-dd_HH-mm-ss]").format(Calendar.getInstance().getTime()) + message;
		
		if(!exportFile.exists()) {
			CreateFile();
		}
		System.out.println(message);
		try {
			writer.write(message);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error! Trying to write to Log file: " + e);
			e.printStackTrace();
		}
		
	}
	
	private static void CreateFile() {
		CreateLogFolder();
		
		try {
			if(exportFile.exists()) {
				return;
			}
			
			writer = new BufferedWriter(new FileWriter(fileName));
			if(!exportFile.createNewFile()) {
				throw new IOException(); 
			}
		} catch (IOException e) {
			System.out.println("Error! Trying to create Log file: " + e);
			e.printStackTrace();
		}
	}
	
	private static void CreateLogFolder() {
		
		File dir = new File(LOGS_PATH);
		
		if(!dir.exists()) {
			if(dir.mkdir()) {
				System.out.println("Directory created Successfully.");
			}
			else {
				System.out.println("Directory failed to be created!");
			}
		}
		else {
			System.out.println("Directory already exits!");
		}
	}
	
	public static boolean CloseLogger() {
		try {
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
