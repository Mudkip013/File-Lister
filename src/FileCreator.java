import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class FileCreator implements ActionListener{
	String[] directories;
	String[] editedDirectories;
	
	private int selectedFileType;
	public FileCreator() {
		Logger.logln("FileCreator initialized.");
		
	}
	
	private final static String EXPORT_PATH = "Exports"; 
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Logger.logln("Create File ActionPerformed!");
		Logger.logln("Reading folders in directory...");
		directories = Main.readFolders(MainWindow.getPath());
		Logger.logln("Done!");
		
		Logger.logln("Editting directories for requested type...");
		editedDirectories = EditTypes(MainWindow.GetFileTypeInt());
		Logger.logln("Done!");
		
		CreateListFile(editedDirectories);
		
	}
	
	public String[] EditTypes(int fileType) {
		Logger.logln("Editing Directories...");
		String[] _editedDirectories = new String[directories.length];
		Logger.logln("_editedDirectories Created");
		Logger.logln("Converting directories");
		for(int i = 0; i < directories.length - 1; i++) {
			switch (fileType) {
			case 1:
				_editedDirectories[i] = directories[i] + ", ";
				break;
			default:
				_editedDirectories[i] = directories[i];
				break;
			}
		} 
		Logger.logln("Finished converting most of directories!");
		_editedDirectories[directories.length - 1] = directories[directories.length - 1];
		Logger.logln("Done!");
		selectedFileType = fileType;
		return _editedDirectories;
	}
	public boolean CreateListFile(String[] _directories) {
		Logger.logln("Creating File...");
		String fileName = EXPORT_PATH + "\\Directories list[" + MainWindow.GetFileTypeString() + "]" + new SimpleDateFormat("[yyyy-MM-dd_HH-mm-ss]").format(Calendar.getInstance().getTime()) + ".txt";
		try {
			File exportFile = new File(fileName);
			if(!exportFile.createNewFile()) {
				throw new IOException(); 
			}
		} catch (IOException e) {
			Logger.logln("Error creating file!: " + e);
			return false;
		}
		try {
			Logger.logln("Adding directories to file...");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			
			switch (selectedFileType) {
			case 0:
				writer.write(Arrays.toString(_directories));
				break;
			case 2:
				for (String string : _directories) {
					writer.write(string);
					writer.write("\t");
				}
				break;
			case 3:
				for (String string : _directories) {
					writer.write(string);
					writer.newLine();
				}
				break;
			default:
				for (String string : _directories) {
					writer.write(string);
				}
				break;
			}
			
			//writer.write(Arrays.toString(_directories));
			writer.close();
			Logger.logln("Done!");
			return true;
		} catch (IOException e) {
			Logger.logln("Error adding directories!: " + e);
			return false;
		}
	}

	public static void CreateExportFolder() {
		
		Logger.logln("Creating export folder...");
		File dir = new File(EXPORT_PATH);
		if(!dir.exists()) {
			if(dir.mkdir()) {
				Logger.logln("Directory created Successfully.");
			}
			else {
				Logger.logln("Directory failed to be created!");
			}
		}
		else {
			Logger.logln("Directory already exits!");
		}
	}
}
