import java.io.*;
public class Main {

	private static MainWindow window;
	
	public static void main(String[] args) {
		Logger.logln("Program Started!");
		FileCreator.CreateExportFolder();
		
		window = new MainWindow();
		

	}
	
	public static String[] readFolders(String path) {
		Logger.logln("Beginning folder read.");
		File file = new File(path);
		String[] directories = file.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		Logger.logln("Done!");
		return directories;
	}
	
	

}
