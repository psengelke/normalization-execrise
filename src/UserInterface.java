import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class UserInterface  implements ActionListener {

	private JTextArea jtAreaOutput;
	private ParserController pc;
	private JFilePicker fp;
	
	public UserInterface(ParserController pc) {
		this.pc = pc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = fp.getSelectedFilePath();
		if (filePath.isEmpty())
		{
			jtAreaOutput.setText("");
			jtAreaOutput.setText("Please select a file to parse");
		}
		else
		{
			LinkedList<String> result = pc.parseFile(filePath);
			jtAreaOutput.setText("");
			String output = "";
			for (int k = 0; k < result.size(); ++k)
				output = output + result.get(k) + "\n";
			jtAreaOutput.setText(output);
		}
	}
	
	public void initUi() {
		JFrame frame = new JFrame();
		JButton parseButton = new JButton("Parse File");
		parseButton.setBounds(100,130, 100, 40);
		parseButton.addActionListener(this);
		
		fp = new JFilePicker("Choose a file to parse: ", "Browse");
		fp.setBounds(70, 50, 600, 50);
		fp.setMode(1);
		
		
		jtAreaOutput = new JTextArea(5, 20);
		jtAreaOutput.setCaretPosition(jtAreaOutput.getDocument()
				.getLength());
		jtAreaOutput.setEditable(false);
		jtAreaOutput.setBounds(100, 200, 600, 200);
		
		frame.add(jtAreaOutput);
		frame.add(fp);
		frame.add(parseButton);
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
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
