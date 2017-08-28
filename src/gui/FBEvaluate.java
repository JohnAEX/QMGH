package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import auswertung.Fragebogenauswertung;
import guiModules.FBSSubmissionModul;
import umfrage.Frage;
import umfrage.Fragebogen;
import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Solver;

import javax.swing.JTabbedPane;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author Jonathan Grenda
 *
 */
public class FBEvaluate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private JPanel contentPane;
	private static Fragebogen currentFB;
	private static Fragebogenauswertung currentAuswertung;
	static ArrayList<Frage> fragenList = new ArrayList<Frage>();
	static ArrayList<Integer> antwortenSolver = new ArrayList<Integer>();
	Iterator<Frage> fragenIt = fragenList.iterator();
	private static int fragenZahl = 0;
	/**
	 * Launch the application.
	 */
	public static void mainRunEvaluate() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FBEvaluate frame = new FBEvaluate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void evaluateFB(Fragebogenauswertung choosenAus){
		currentAuswertung = choosenAus;
		currentFB = choosenAus.getSourceFragebogen();
		mainRunEvaluate();
		fragenList = currentFB.getFragen();
	}
	/**
	 * Create the frame.
	 */
	public FBEvaluate() {
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		setTitle("QuestionMark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel introPanel = new JPanel();
		introPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Beschreibung", null, introPanel, null);
		
		JLabel lbl_1 = new JLabel("Expose:");
		lbl_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("Titel:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblTitle = new JLabel("New label");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//Title_Set
		lblTitle.setText(currentFB.getTitel());
		border = BorderFactory.createLineBorder(Color.BLACK);
		lblTitle.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel lblExpose = new JLabel("New label");
		lblExpose.setVerticalAlignment(SwingConstants.TOP);
		lblExpose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//Expose_Set
		lblExpose.setText("<html>" + currentFB.getExposee() + "</html>");
		border = BorderFactory.createLineBorder(Color.BLACK);
		lblExpose.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));		
		
		GroupLayout gl_introPanel = new GroupLayout(introPanel);
		gl_introPanel.setHorizontalGroup(
			gl_introPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_introPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(lbl_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_introPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblExpose, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_introPanel.setVerticalGroup(
			gl_introPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_introPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(lblTitle))
					.addGap(33)
					.addGroup(gl_introPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_1)
						.addComponent(lblExpose, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(171, Short.MAX_VALUE))
		);
		introPanel.setLayout(gl_introPanel);
		
		JPanel currentQuestion = new JPanel();
		
		tabbedPane.addTab("Frage " + (fragenZahl + 1), null, currentQuestion, null);
		
		currentQuestion.setLayout(new CardLayout(0, 0));
		
		JPanel jaNeinPanel = new JPanel();
		jaNeinPanel.setBackground(Color.WHITE);
		currentQuestion.add(jaNeinPanel, "jaNeinChoice");
		
		JLabel lbl_3 = new JLabel("Frage:");
		lbl_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblFrage = new JLabel("<html>" + "" + "</html>");
		lblFrage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrage.setVerticalAlignment(SwingConstants.TOP);
		border = BorderFactory.createLineBorder(Color.BLACK);
		lblFrage.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		
		JLabel lblAntwort = new JLabel("Antwort:");
		lblAntwort.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JRadioButton rdbtnJa = new JRadioButton("Ja");
		rdbtnJa.setBackground(Color.WHITE);
		rdbtnJa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JRadioButton rdbtnNein = new JRadioButton("Nein");
		rdbtnNein.setBackground(Color.WHITE);
		rdbtnNein.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNein);
		group.add(rdbtnJa);
		
		JButton btnNext = new JButton("N\u00E4chste Frage");

		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_jaNeinPanel = new GroupLayout(jaNeinPanel);
		gl_jaNeinPanel.setHorizontalGroup(
			gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jaNeinPanel.createSequentialGroup()
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jaNeinPanel.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jaNeinPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAntwort, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addGap(47)
									.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnNein, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnJa, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_jaNeinPanel.createSequentialGroup()
									.addComponent(lbl_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addGap(15)
									.addComponent(lblFrage, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_jaNeinPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_jaNeinPanel.setVerticalGroup(
			gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jaNeinPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_3)
						.addComponent(lblFrage, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAntwort)
						.addComponent(rdbtnJa))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnNein)
					.addGap(100)
					.addComponent(btnNext)
					.addGap(176))
		);
		jaNeinPanel.setLayout(gl_jaNeinPanel);
		
		JPanel singlePanel = new JPanel();
		singlePanel.setBackground(Color.WHITE);
		currentQuestion.add(singlePanel, "singleChoice");
		
		JLabel label_2 = new JLabel("Frage:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblFrageSingle = new JLabel("<html>" + "" + "</html>");
		lblFrageSingle.setVerticalAlignment(SwingConstants.TOP);
		lblFrageSingle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		border = BorderFactory.createLineBorder(Color.BLACK);
		lblFrageSingle.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel lblAntwort_1 = new JLabel("Antwort:");
		lblAntwort_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNext2 = new JButton("N\u00E4chste Frage");
		
		btnNext2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_singlePanel = new GroupLayout(singlePanel);
		gl_singlePanel.setHorizontalGroup(
			gl_singlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_singlePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNext2, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_singlePanel.createSequentialGroup()
							.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAntwort_1)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblFrageSingle, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_singlePanel.setVerticalGroup(
			gl_singlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_singlePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(lblFrageSingle, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_singlePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAntwort_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addComponent(btnNext2)
					.addContainerGap(176, Short.MAX_VALUE))
		);
		singlePanel.setLayout(gl_singlePanel);
		
		JPanel multiPanel = new JPanel();
		multiPanel.setBackground(Color.WHITE);
		currentQuestion.add(multiPanel, "multiChoice");
		
		JLabel label_4 = new JLabel("Frage:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblFrageMulti = new JLabel("<html>" + "" + "</html>");
		lblFrageMulti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrageMulti.setVerticalAlignment(SwingConstants.TOP);
		border = BorderFactory.createLineBorder(Color.BLACK);
		lblFrageMulti.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel lblAntwort_2 = new JLabel("Antwort:");
		lblAntwort_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox multiAnswer1 = new JCheckBox("-");
		multiAnswer1.setBackground(Color.WHITE);
		multiAnswer1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox multiAnswer2 = new JCheckBox("-");
		multiAnswer2.setBackground(Color.WHITE);
		multiAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox multiAnswer3 = new JCheckBox("-");
		multiAnswer3.setBackground(Color.WHITE);
		multiAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox multiAnswer4 = new JCheckBox("-");
		multiAnswer4.setBackground(Color.WHITE);
		multiAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox multiAnswer5 = new JCheckBox("-");
		multiAnswer5.setBackground(Color.WHITE);
		multiAnswer5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//Gloable Antowrten Liste - von Solver abgegeben
		ArrayList<ArrayList<Integer>> allAnswers = currentAuswertung.getAllAntworten();
		Iterator<ArrayList<Integer>> allAnswersIt = allAnswers.iterator();
		
			
		fragenList = currentFB.getFragen();
		String fragenDescr = "";
		int fragenID = 0;
		ArrayList<String> antwortenList = new ArrayList<String>();
		//System.out.println(fragenList);
		if(!fragenList.isEmpty()){
			ArrayList<Integer> innerNumbers = allAnswersIt.next();
			Iterator<Integer> innerNumbersIt = innerNumbers.iterator();
			//System.out.println("Fragenlist nicht leer");
			Frage currentFrage = fragenList.get(fragenZahl);
			fragenID = currentFrage.getFragetyp();
			fragenDescr = currentFrage.getFragebeschreibung();
			antwortenList = currentFrage.getAntwortmoeglichkeiten();
			
			if(fragenID == 0){
				//JA/NEIN
				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "jaNeinChoice");
			    int erste = innerNumbersIt.next();
			    int alle = currentAuswertung.getAnzahlAntworten();
			    rdbtnJa.setText("Ja [" + erste + " / " + alle + "]");
			    int zweite = innerNumbersIt.next();
			    rdbtnNein.setText("Nein [" + zweite + " / " + alle + "]");
			    lblFrage.setText("<html>" + fragenDescr + "</html>");
			   
			}else if(fragenID == 1){
				//Single-Choice
				

				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "singleChoice");
			    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
			    int alle = currentAuswertung.getAnzahlAntworten();
			    for(int i = 0; i < antwortenList.size();i++){
			    	if(innerNumbersIt.hasNext()){
			    		comboBox.addItem(antwortenList.get(i) + " [" + innerNumbersIt.next() + " / " + alle + "]");
			    	}
			    }
			    
			}else if(fragenID == 2){
				//Multiple-Choice
				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "multiChoice");
			    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
			    int alle = currentAuswertung.getAnzahlAntworten();
			    if(antwortenList.size()==1){
			    	int erste;
			    	if(innerNumbersIt.hasNext()){
			    		erste = innerNumbersIt.next();
			    	}else{
			    		erste = 0;
			    	}
			    	multiAnswer1.setText(antwortenList.get(0) + " [" + erste + " / " + alle + "]");
			    	multiAnswer2.setVisible(false);
			    	multiAnswer3.setVisible(false);
			    	multiAnswer4.setVisible(false);
			    	multiAnswer5.setVisible(false);
			    	
			    	multiAnswer2.setText("-");
			    	multiAnswer3.setText("-");
			    	multiAnswer4.setText("-");
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==2){
			    	int erste;
			    	int zweite;
			    	if(innerNumbersIt.hasNext()){
			    		erste = innerNumbersIt.next();
			    	}else{
			    		erste = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		zweite = innerNumbersIt.next();
			    	}else{
			    		zweite = 0;
			    	}
			    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
			    	multiAnswer1.setVisible(true);
			    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
			    	multiAnswer2.setVisible(true);
			    	multiAnswer3.setVisible(false);
			    	multiAnswer4.setVisible(false);
			    	multiAnswer5.setVisible(false);
			    	
			    	multiAnswer3.setText("-");
			    	multiAnswer4.setText("-");
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==3){
			    	
			    	int erste;
			    	int zweite;
			    	int dritte;
			    	if(innerNumbersIt.hasNext()){
			    		erste = innerNumbersIt.next();
			    	}else{
			    		erste = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		zweite = innerNumbersIt.next();
			    	}else{
			    		zweite = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		dritte = innerNumbersIt.next();
			    	}else {
			    		dritte = 0;
			    	}
			    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
			    	multiAnswer1.setVisible(true);
			    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
			    	multiAnswer2.setVisible(true);
			    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
			    	multiAnswer3.setVisible(true);
			    	multiAnswer4.setVisible(false);
			    	multiAnswer5.setVisible(false);
			    	
			    	multiAnswer4.setText("-");
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==4){
			    	
			    	int erste;
			    	int zweite;
			    	int dritte;
			    	int vierte;
			    	if(innerNumbersIt.hasNext()){
			    		erste = innerNumbersIt.next();
			    	}else{
			    		erste = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		zweite = innerNumbersIt.next();
			    	}else{
			    		zweite = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		dritte = innerNumbersIt.next();
			    	}else {
			    		dritte = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		vierte = innerNumbersIt.next();
			    	}else{
			    		vierte = 0;
			    	}
			    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
			    	multiAnswer1.setEnabled(true);
			    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
			    	multiAnswer2.setVisible(true);
			    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
			    	multiAnswer3.setVisible(true);
			    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
			    	multiAnswer4.setVisible(true);
			    	multiAnswer5.setVisible(false);
			    	
			    	
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==5){
			    	
			    	int erste;
			    	int zweite;
			    	int dritte;
			    	int vierte;
			    	int fünfte;
			    	if(innerNumbersIt.hasNext()){
			    		erste = innerNumbersIt.next();
			    	}else{
			    		erste = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		zweite = innerNumbersIt.next();
			    	}else{
			    		zweite = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		dritte = innerNumbersIt.next();
			    	}else {
			    		dritte = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		vierte = innerNumbersIt.next();
			    	}else{
			    		vierte = 0;
			    	}
			    	
			    	if(innerNumbersIt.hasNext()){
			    		fünfte = innerNumbersIt.next();
			    	}else{
			    		fünfte = 0;
			    	}
			    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
			    	multiAnswer1.setVisible(true);
			    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
			    	multiAnswer2.setVisible(true);
			    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
			    	multiAnswer3.setVisible(true);
			    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
			    	multiAnswer4.setVisible(true);
			    	multiAnswer5.setText(antwortenList.get(4)+ " [" + fünfte + " / " + alle + "]");
			    	multiAnswer5.setVisible(true);
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else{
			    //Practically Impossible to reach
			    	
			    }
			    
			    
			}else{
				//Error
				
			}
		}
		
		btnNext2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Next question
				//Get selected index, -> int
				
				comboBox.removeAllItems();
				
				
				if(fragenList.size()>fragenZahl+1){
					
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();
					//System.out.println(fragenList);
					if(!fragenList.isEmpty()){
						ArrayList<Integer> innerNumbers = allAnswersIt.next();
						Iterator<Integer> innerNumbersIt = innerNumbers.iterator();
						//System.out.println("Fragenlist nicht leer");
						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    int erste = innerNumbersIt.next();
						    int alle = currentAuswertung.getAnzahlAntworten();
						    rdbtnJa.setText("Ja [" + erste + " / " + alle + "]");
						    int zweite = innerNumbersIt.next();
						    rdbtnNein.setText("Nein [" + zweite + " / " + alle + "]");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							

							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    int alle = currentAuswertung.getAnzahlAntworten();
						    for(int i = 0; i < antwortenList.size();i++){
						    	if(innerNumbersIt.hasNext()){
						    		comboBox.addItem(antwortenList.get(i) + " [" + innerNumbersIt.next() + " / " + alle + "]");
						    	}
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    //System.out.println("-><- " + antwortenList.size());
						    int alle = currentAuswertung.getAnzahlAntworten();
						    if(antwortenList.size()==1){
						    	int erste = innerNumbersIt.next();
						    	
						    	multiAnswer1.setText(antwortenList.get(0) + " [" + erste + " / " + alle + "]");
						    	multiAnswer2.setVisible(false);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer2.setText("-");
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==2){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setEnabled(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setVisible(false);
						    	
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	int fünfte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setText(antwortenList.get(4)+ " [" + fünfte + " / " + alle + "]");
						    	multiAnswer5.setVisible(true);
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    //Practically Impossible to reach
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
				}else{
					//done
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		
		
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//next question
				
				rdbtnJa.setSelected(false);
				rdbtnNein.setSelected(false);
				if(fragenList.size()>fragenZahl+1){
					
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();
					
					if(!fragenList.isEmpty()){
						ArrayList<Integer> innerNumbers = allAnswersIt.next();
						Iterator<Integer> innerNumbersIt = innerNumbers.iterator();

						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    int erste = innerNumbersIt.next();
						    int alle = currentAuswertung.getAnzahlAntworten();
						    rdbtnJa.setText("Ja [" + erste + " / " + alle + "]");
						    int zweite = innerNumbersIt.next();
						    rdbtnNein.setText("Nein [" + zweite + " / " + alle + "]");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							

							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    int alle = currentAuswertung.getAnzahlAntworten();
						    for(int i = 0; i < antwortenList.size();i++){
						    	if(innerNumbersIt.hasNext()){
						    		comboBox.addItem(antwortenList.get(i) + " [" + innerNumbersIt.next() + " / " + alle + "]");
						    	}
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    int alle = currentAuswertung.getAnzahlAntworten();
						    if(antwortenList.size()==1){
						    	int erste = innerNumbersIt.next();
						    	
						    	multiAnswer1.setText(antwortenList.get(0) + " [" + erste + " / " + alle + "]");
						    	multiAnswer2.setVisible(false);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer2.setText("-");
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==2){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setEnabled(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setVisible(false);
						    	
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	int fünfte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setText(antwortenList.get(4)+ " [" + fünfte + " / " + alle + "]");
						    	multiAnswer5.setVisible(true);
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    //Practically Impossible to reach
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
				}else{
					//Done
					tabbedPane.setSelectedIndex(2);
				}
				
				
			}
		});
		
		JButton btnNext3 = new JButton("N\u00E4chste Frage");
		btnNext3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(fragenList.size()>fragenZahl+1){
					
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();

					if(!fragenList.isEmpty()){
						ArrayList<Integer> innerNumbers = allAnswersIt.next();
						Iterator<Integer> innerNumbersIt = innerNumbers.iterator();
						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    int erste = innerNumbersIt.next();
						    int alle = currentAuswertung.getAnzahlAntworten();
						    rdbtnJa.setText("Ja [" + erste + " / " + alle + "]");
						    int zweite = innerNumbersIt.next();
						    rdbtnNein.setText("Nein [" + zweite + " / " + alle + "]");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							

							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    int alle = currentAuswertung.getAnzahlAntworten();
						    for(int i = 0; i < antwortenList.size();i++){
						    	if(innerNumbersIt.hasNext()){
						    		comboBox.addItem(antwortenList.get(i) + " [" + innerNumbersIt.next() + " / " + alle + "]");
						    	}
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    int alle = currentAuswertung.getAnzahlAntworten();
						    if(antwortenList.size()==1){
						    	int erste = innerNumbersIt.next();
						    	
						    	multiAnswer1.setText(antwortenList.get(0) + " [" + erste + " / " + alle + "]");
						    	multiAnswer2.setVisible(false);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer2.setText("-");
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==2){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setVisible(false);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setVisible(false);
						    	multiAnswer5.setVisible(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setEnabled(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setVisible(false);
						    	
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	int erste = innerNumbersIt.next();
						    	int zweite = innerNumbersIt.next();
						    	int dritte = innerNumbersIt.next();
						    	int vierte = innerNumbersIt.next();
						    	int fünfte = innerNumbersIt.next();
						    	multiAnswer1.setText(antwortenList.get(0)+ " [" + erste + " / " + alle + "]");
						    	multiAnswer1.setVisible(true);
						    	multiAnswer2.setText(antwortenList.get(1)+ " [" + zweite + " / " + alle + "]");
						    	multiAnswer2.setVisible(true);
						    	multiAnswer3.setText(antwortenList.get(2)+ " [" + dritte + " / " + alle + "]");
						    	multiAnswer3.setVisible(true);
						    	multiAnswer4.setText(antwortenList.get(3)+ " [" + vierte + " / " + alle + "]");
						    	multiAnswer4.setVisible(true);
						    	multiAnswer5.setText(antwortenList.get(4)+ " [" + fünfte + " / " + alle + "]");
						    	multiAnswer5.setVisible(true);
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    //Practically Impossible to reach
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
					
				}else{
					//Done
					tabbedPane.setSelectedIndex(2);
				}

					
					
			}
		});
		btnNext3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		
		
		GroupLayout gl_multiPanel = new GroupLayout(multiPanel);
		gl_multiPanel.setHorizontalGroup(
			gl_multiPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_multiPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_multiPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNext3, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_multiPanel.createSequentialGroup()
							.addComponent(label_4)
							.addGap(15)
							.addComponent(lblFrageMulti, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_multiPanel.createSequentialGroup()
							.addComponent(lblAntwort_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_multiPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(multiAnswer1)
								.addComponent(multiAnswer2)
								.addComponent(multiAnswer3)
								.addComponent(multiAnswer4)
								.addComponent(multiAnswer5))))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_multiPanel.setVerticalGroup(
			gl_multiPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_multiPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_multiPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFrageMulti, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_multiPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAntwort_2)
						.addComponent(multiAnswer1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multiAnswer2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multiAnswer3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multiAnswer4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(multiAnswer5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNext3)
					.addContainerGap(171, Short.MAX_VALUE))
		);
		multiPanel.setLayout(gl_multiPanel);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Hiermit gelangen Sie zur\u00FCck zum Men\u00FC.");
		tabbedPane.addTab("Schließen", null, panel, null);
		
		JButton btnFragebogenAbgeben = new JButton("Zur\u00FCck zum Men\u00FC");
		btnFragebogenAbgeben.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Menu.launchMenu();
				setVisible(false);
			}
		});
		btnFragebogenAbgeben.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(btnFragebogenAbgeben, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnFragebogenAbgeben, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
}
