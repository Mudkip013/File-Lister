import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class MainWindow implements ActionListener{
	
	public MainWindow(){
		Logger.logln("Initializing Window!");
		Init();
		Logger.logln("Done!");
		
		
		
	}
	private JFrame frame;
	private int width = 800, height = 600;
	private boolean isResizable = false;
	
	private JPanel panel;
	private JButton submitButton;
	private static JTextField pathField;
	private JLabel folderListsLabel;
	private JButton createListButton;
	private static JComboBox<String> FileTypeDropDown;
	
	private FileCreator fileCreator;
	
	private void Init() {
		Logger.logln("Declaring FileTypeDropDown...");
		FileTypeDropDown = new JComboBox<>(new String[] {FileTypeConstants.NONE.name(), FileTypeConstants.CSV.name(), FileTypeConstants.TSV.name(), FileTypeConstants.LIST.name()});
		
		Logger.logln("Creating Frame...");
		frame = new JFrame("FileLister");
		frame.setResizable(isResizable);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		Logger.logln("Creating Panel...");
		panel = new JPanel();
		frame.add(panel);
		
		Logger.logln("Creating TextField...");
		pathField = new JTextField(16);
		pathField.setText("Enter folder path");
		
		Logger.logln("Creating Submit button...");
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setBounds(50, 50, 50, 25);
		
		Logger.logln("Creating Create List button...");
		createListButton = new JButton("Create List");
		createListButton.addActionListener(fileCreator = new FileCreator());
		createListButton.setBounds(50, 50, 50, 25);
		
		
		
		Logger.logln("Creating label...");
		folderListsLabel = new JLabel("Folders in Directory: ");
		
		Logger.logln("Adding components to panel");
		panel.add(pathField);
		panel.add(submitButton);
		panel.add(FileTypeDropDown);
		panel.add(createListButton);
		panel.add(folderListsLabel);
		
		Logger.logln("Finalizing frame");
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("Beginning Frame Termination!");
				Terminate();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Logger.logln("Submit Button ActionPerformed!");
		Logger.logln(getPath());
		
		folderListsLabel.setText(Arrays.toString(Main.readFolders(MainWindow.getPath())));
		
	}
	
	public static String getPath() {
		return pathField.getText();
	}
	public static int GetFileTypeInt() {
		return FileTypeConstants.valueOf((String) FileTypeDropDown.getSelectedItem()).ordinal();
	}
	public static String GetFileTypeString() {
		return (String) FileTypeDropDown.getSelectedItem();
	}
	
	private void Terminate() {
		if(Logger.CloseLogger()) {
			System.out.println("Logger closed with no errors.");
		} else {
			System.out.println("An error occured while closing logger!");
		}
		frame.dispose();
		System.exit(0);
	}
	
	
	
	
}
