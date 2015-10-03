import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * 
 * Handles the {@link JavaSourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {

	private enum extension {
		java, c;
	}

	private extension determineExtension(String path) {
		String[] splitPath=path.split(Pattern.quote("."));

		if (splitPath.length!=0) {
			if (splitPath[splitPath.length-1].equals("java"))
				return extension.java;
			else
				if (splitPath[splitPath.length-1].equals("c") || splitPath[splitPath.length-1].equals("h"))
					return extension.c;
		}
		return null;
	}

	private String makeStandardFile(String path){

		Random rng=new Random();
		Integer rand=rng.nextInt(500);
		String newpath=path+rand;
		/*if (determineExtension(path)==extension.java)
			command="AStyle.exe --style=java < "+path+" > "+newpath;
		else
			if (determineExtension(path)==extension.c)
				command="AStyle.exe --style=allman < "+path+" > "+newpath;
			else
				System.out.println("Unrecognized extension");*/

		StringBuffer output=new StringBuffer();
		ProcessBuilder builder = null;
		Process p;

		try {
			//				p=Runtime.getRuntime().exec(command);
			//				System.out.println(command);
			if (determineExtension(path)==extension.java)
				builder=new ProcessBuilder("AStyle.exe", "--style=java");
			else
				if (determineExtension(path)==extension.c)
					builder=new ProcessBuilder("AStyle.exe", "--style=c");
			if (builder!=null) {
				builder.redirectInput(new File(path));
				builder.redirectOutput(new File(newpath));
				p=builder.start();
				p.waitFor();

				BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line="";
				while ((line=reader.readLine())!=null)
					output.append(line+"\n");
				System.out.println(output.toString());

				return newpath;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates a report of the contents of a source code file.
	 * @param path The file path of the source code file.
	 * @return A report in the form of a {@link LinkedList}.
	 */
	public LinkedList<String> parseFile(String path){

		String temp = makeStandardFile(path);
		LinkedList<String> file = SourceFileReader.readSourceFile(temp);
		LinkedList<String> report = new LinkedList<>();
		
		SourceFileParser parser = (determineExtension(path) == extension.java)?new JavaSourceFileParser(): new CSourceFileParser();
		
		report.add("Number of lines of code: "+parser.countCodeLines(file));
		report.add("Number of statements: "+parser.countStatements(file));
		report.add("Number of classes: "+parser.countClasses(file));
		report.add("Number of functions: "+parser.countFunctions(file));
		report.add("Average number of statements / class: "+parser.calcAverageStatementsPerClass(file));
		report.add("Average number of statements per function: "+parser.calcAverageStatementsPerFunction(file));
		report.add("Lines of comments: "+parser.countComments(file));

		return report;
	}

	/**
	 * Entry point.
	 * @param args
	 */
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		ParserController pc = new ParserController();
		MyActionListener listener = pc.new MyActionListener();
		JButton parseButton = new JButton("Parse File");
		parseButton.setBounds(120,150, 100, 40);
		parseButton.addActionListener(listener);
		
		JFilePicker fp = pc.new JFilePicker("Choose a file to parse: ", "Browse");
		fp.setBounds(100, 50, 600, 100);
		fp.setMode(1);
		
		frame.add(fp);
		frame.add(parseButton);
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public class MyActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
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
