import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.foreign.AddressLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
public class FlashCardPlayer {
	JTextArea displayArea;
	JLabel question ;
	ArrayList<FlashCard> listcard ;
	Iterator cardIterator;
	FlashCard currentCard;
	JButton answButton;
	int currentindex;
	JFrame frame;
	boolean showans ;
	public FlashCardPlayer() {
		frame =new JFrame("Flash Card Player");
		displayArea = new JTextArea(10,20);
		question= new JLabel("Question");
		answButton= new JButton("Show Answer");
		answButton.addActionListener(new answerActionlistner());
		JPanel mainPanel= new JPanel();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu= new JMenu("File");
		JMenuItem loadcardset = new JMenuItem("load Card Set");
		loadcardset.addActionListener(new loadActionListner());
		menu.add(loadcardset);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.setLayout(new GridLayout(1, 1));
		mainPanel.add(question);
		mainPanel.add(displayArea);
		mainPanel.add(answButton);
		frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
				
		
	}

	public static void main(String[] args) {
		new FlashCardPlayer();
		

	}
	class answerActionlistner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(showans) {
				displayArea.setText(currentCard.getAnswer());
				answButton.setText("next Card");
				showans=false;
				
			}else {
				if(cardIterator.hasNext()) {
					shownextcard();
					
				}else {
					displayArea.setText("thats last Card");
					answButton.setEnabled(false);
				}
			}
			
		}
		
	}
	class loadActionListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser selectFile = new JFileChooser();
			selectFile.showOpenDialog(frame);
			loadFile(selectFile.getSelectedFile());
			
			
		}
}

		public void loadFile(File selectedFile) {
			listcard = new ArrayList<FlashCard>();
			try {
				BufferedReader reader =new BufferedReader(new FileReader(selectedFile));
				String line = null;
				while ((line=reader.readLine())!=null) {
					makecard(line);
					
				}
				
			} catch (Exception e) {
				System.out.println("couldnt read the File");
				e.printStackTrace();
			}
			cardIterator =listcard.iterator();
			shownextcard();
			
		}

		public void shownextcard() {
			currentCard= (FlashCard) cardIterator.next();
			displayArea.setText(currentCard.getQuestion());
			answButton.setText("show Answer");
			showans=true;
			
			
			
		}

		private void makecard(String linetoparse) {
			String[] result = linetoparse.split("/");
			FlashCard card = new FlashCard(result[0], result[1]);
			listcard.add(card);
			
			
			
			
		}
		

}
