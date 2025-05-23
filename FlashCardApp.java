import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.foreign.AddressLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FlashCardApp{
	 JTextArea question ;
	 JTextArea answer ;
	 ArrayList<FlashCard> listcard;
	 JFrame frame;
	
	
	public FlashCardApp() {
		frame =new JFrame("Flash Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		question= new JTextArea(5,15);
		answer=new JTextArea(5,15);
		
		listcard =new ArrayList<FlashCard>();
		JPanel mainPanel = new JPanel();
		JButton nextButton =new JButton("Next Card");
		JLabel qLabel=new JLabel("Question");
		JLabel aLabel=new JLabel("Answer");
		JMenuBar menuBar=new JMenuBar();
		JMenu menu= new JMenu("File");
		JMenuItem newMenuItem= new JMenuItem("New");
		JMenuItem saveMenuItem=new JMenuItem("Save");
		menu.add(newMenuItem);
		menu.add(saveMenuItem);
		menuBar.add(menu);
		
		newMenuItem.addActionListener(new fileActionListner());
		saveMenuItem.addActionListener(new saveActionListner());
		
		
		frame.setLayout(new GridLayout(1, 1));
		frame.setJMenuBar(menuBar);
		
		mainPanel.add(qLabel);
		mainPanel.add(question);
		mainPanel.add(aLabel);
		mainPanel.add(answer);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new buttonActionListner());
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
		

		
		
	}
	public static void main(String[] args) {
		new FlashCardApp();
		
	}
class buttonActionListner implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		  FlashCard card = new FlashCard(question.getText(), answer.getText());
		  listcard.add(card);
		  clearCard();
		  
		  
		
	}

	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
		
	}
	
}
class fileActionListner implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
class saveActionListner implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		FlashCard card = new FlashCard(question.getText(), answer.getText());
		  listcard.add(card);
		  JFileChooser filesave = new JFileChooser();
		  filesave.showSaveDialog(frame);
		  saveFile(filesave.getSelectedFile());
	
		
	}

	private void saveFile(File selectedFile) {
		try {
			BufferedWriter writer =new BufferedWriter(new FileWriter(selectedFile));
			Iterator<FlashCard> cardIterator= listcard.iterator();
		    while(cardIterator.hasNext()) {
		    	FlashCard card = cardIterator.next();
		    	writer.write(card.getQuestion()+"/");
		    	writer.write(card.getAnswer()+"\n");
		    	
				
			}
		    writer.close();
		} catch (Exception e) {
			System.out.println("Couldn't write into file ");
			e.printStackTrace();
			
		}
		
	}
	
}
}