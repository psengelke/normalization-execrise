import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * User Interface class to capture user input and provide parse results
 * @author Izak
 *
 */

public class UserInterface  implements ActionListener {

	private JTextArea jtAreaOutput;
	private ParserController pc;
	private JFilePicker fp;
	
	/**
	 * Constructor
	 * @param pc An instance of ParserController. Used to calculate output
	 */
	public UserInterface(ParserController pc) {
		this.pc = pc;
	}
	
	/**
	 * Event handling function. Inoked when "Parse" button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = fp.getSelectedFilePath();
		if (filePath.isEmpty())	// No file path supplied
		{
			jtAreaOutput.setText("");
			jtAreaOutput.setText("Please select a file to parse");
		}
		else	// Get result from ParserController pc and output
		{
			LinkedList<String> result = pc.parseFile(filePath);
			jtAreaOutput.setText("");
			String output = "";
			for (int k = 0; k < result.size(); ++k)
				output = output + result.get(k) + "\n";
			jtAreaOutput.setText(output);
		}
	}
	
	/**
	 * Initiate User Interface components
	 */
	public void initUi() {
		JFrame frame = new JFrame();
		JButton parseButton = new JButton("Parse File");
		parseButton.setBounds(100,130, 100, 40);
		parseButton.addActionListener(this);
		
		// Initiate file picker
		fp = new JFilePicker("Choose a file to parse: ", "Browse");
		fp.setBounds(70, 50, 600, 50);
		fp.setMode(1);
		
		// Initiate output textArea
		jtAreaOutput = new JTextArea(5, 20);
		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
				.getLength());
		jtAreaOutput.setEditable(false);
		jtAreaOutput.setBounds(100, 200, 600, 300);
		
		// Add components
		frame.add(jtAreaOutput);
		frame.add(fp);
		frame.add(parseButton);
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Class to handle file path selection
	 * @author Izak
	 *
	 */
	public class JFilePicker extends JPanel {
	    private String textFieldLabel;
	    private String buttonLabel;
	     
	    private JLabel label;
	    private JTextField textField;
	    private JButton button;
	     
	    private JFileChooser fileChooser;
	     
	    private int mode;
	    public static final int MODE_OPEN = 1;
	    public static final int MODE_SAVE = 2;
	     
	    public JFilePicker(String textFieldLabel, String buttonLabel) {
	        this.textFieldLabel = textFieldLabel;
	        this.buttonLabel = buttonLabel;
	         
	        fileChooser = new JFileChooser();
	         
	        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	 
	        // creates the GUI
	        label = new JLabel(textFieldLabel);
	         
	        textField = new JTextField(30);
	        button = new JButton(buttonLabel);
	         
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	                buttonActionPerformed(evt);            
	            }
	        });
	         
	        add(label);
	        add(textField);
	        add(button);
	         
	    }
	    
	    /**
	     * Called on ActionEvent when "Browse button clicked"
	     * @param evt
	     */
	    private void buttonActionPerformed(ActionEvent evt) {
	    	if (mode == MODE_OPEN) {
	            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
	        } else if (mode == MODE_SAVE) {
	            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
	                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
	        }
	    }
	 
	    /**
	     * Used to add a filter to the filpicker
	     * @param extension	for files to filter
	     * @param description	of files with extension
	     */
	    public void addFileTypeFilter(String extension, String description) {
	        FileTypeFilter filter = new FileTypeFilter(extension, description);
	        fileChooser.addChoosableFileFilter(filter);
	    }
	     
	    public void setMode(int mode) {
	        this.mode = mode;
	    }
	     
	    public String getSelectedFilePath() {
	        return textField.getText();
	    }
	     
	    public JFileChooser getFileChooser() {
	        return this.fileChooser;
	    }
	}
	
	/**
	 * class used to provide a filter to FilePicker
	 * @author Izak
	 *
	 */
	public class FileTypeFilter extends FileFilter {
		 
	    private String extension;
	    private String description;
	     
	    public FileTypeFilter(String extension, String description) {
	        this.extension = extension;
	        this.description = description;
	    }
	     
	    @Override
	    public boolean accept(File file) {
	        if (file.isDirectory()) {
	            return true;
	        }
	        return file.getName().toLowerCase().endsWith(extension);
	    }
	     
	    public String getDescription() {
	        return description + String.format(" (*%s)", extension);
	    }

		
	}
}
