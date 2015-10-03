import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * Handles the {@link SourceFileParser} and {@link SourceFileReader} and interacts with the UI.
 * 
 * @author Paul
 *
 */
public class ParserController {
	
	private String makeStandardFile(String path){
		
		
		
		return null;
	}
	
	public LinkedList<String> parseFile(String path){
		
		String temp = makeStandardFile(path);
		
		LinkedList<String> file = SourceFileReader.readSourceFile(temp);
		
		return null;
	}
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		MyActionListener listener = new MyActionListener();
		JButton parseButton = new JButton("parse");
		parseButton.setBounds(100,150, 80, 30);
		parseButton.addActionListener(listener);
		
		frame.setSize(400, 500);
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
}
