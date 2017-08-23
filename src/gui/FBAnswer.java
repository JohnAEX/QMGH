package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import guiModules.FBSSubmissionModul;
import umfrage.Frage;
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
import javax.swing.JScrollPane;

public class FBAnswer extends JFrame {

	private JPanel contentPane;
	private static FragebogenWithAntwortmoeglichkeit currentFB;
	static ArrayList<Frage> fragenList = new ArrayList<Frage>();
	static ArrayList<ArrayList<Integer>> antwortenSolver = new ArrayList<ArrayList<Integer>>();
	Iterator<Frage> fragenIt = fragenList.iterator();
	private static int fragenZahl = 0;
	/**
	 * Launch the application.
	 */
	public static void mainRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FBAnswer frame = new FBAnswer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void answerFB(FragebogenWithAntwortmoeglichkeit choosenFB){
		currentFB = choosenFB;
		mainRun();
		fragenList = currentFB.getFragen();
	}
	/**
	 * Create the frame.
	 */
	public FBAnswer() {
		
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
		
		JLabel lblFrageMulti = new JLabel("<html>" + "" + "</html>");
		
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
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Iterator outerIt = antwortenSolver.iterator();
				int cc = 1;
				while(outerIt.hasNext()){
					ArrayList<Integer> now = (ArrayList<Integer>) outerIt.next();
					System.out.println("Frage: " + cc);
					Iterator innerIt = now.iterator();
					while(innerIt.hasNext()){
						int abc = (int) innerIt.next();
						System.out.println("\t>Inner: " + abc);
					}
					cc++;
				}
				
			}
		});

		
		//Wrapped by HTML usage -> Dokument explanation
		
		
		GroupLayout gl_introPanel = new GroupLayout(introPanel);
		gl_introPanel.setHorizontalGroup(
			gl_introPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_introPanel.createSequentialGroup()
					.addGroup(gl_introPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_introPanel.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_introPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_1)
								.addComponent(lbl_1)))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_introPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addComponent(lblExpose, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addContainerGap())
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
						.addGroup(gl_introPanel.createSequentialGroup()
							.addComponent(lbl_1)
							.addGap(100)
							.addComponent(btnNewButton))
						.addComponent(lblExpose, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(334, Short.MAX_VALUE))
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
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_jaNeinPanel = new GroupLayout(jaNeinPanel);
		gl_jaNeinPanel.setHorizontalGroup(
			gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jaNeinPanel.createSequentialGroup()
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jaNeinPanel.createSequentialGroup()
							.addGap(15)
							.addComponent(lbl_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jaNeinPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(rdbtnNein, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_jaNeinPanel.createSequentialGroup()
									.addComponent(lblAntwort, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rdbtnJa, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_jaNeinPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(177, Short.MAX_VALUE))
		);
		gl_jaNeinPanel.setVerticalGroup(
			gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jaNeinPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_3)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_jaNeinPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAntwort)
						.addComponent(rdbtnJa))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNein)
					.addGap(18)
					.addComponent(btnNext)
					.addContainerGap(336, Short.MAX_VALUE))
		);
		
		JLabel lblFrage = new JLabel("<html>" + "" + "</html>");
		lblFrage.setBackground(Color.WHITE);
		scrollPane.setViewportView(lblFrage);
		lblFrage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrage.setVerticalAlignment(SwingConstants.TOP);
		lblFrage.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		jaNeinPanel.setLayout(gl_jaNeinPanel);
		
		JPanel singlePanel = new JPanel();
		singlePanel.setBackground(Color.WHITE);
		currentQuestion.add(singlePanel, "singleChoice");
		
		JLabel label_2 = new JLabel("Frage:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel lblAntwort_1 = new JLabel("Antwort:");
		lblAntwort_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNext2 = new JButton("N\u00E4chste Frage");
		
		btnNext2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane_1 = new JScrollPane();
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
							.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_singlePanel.setVerticalGroup(
			gl_singlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_singlePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_singlePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_singlePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAntwort_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addComponent(btnNext2)
					.addContainerGap(339, Short.MAX_VALUE))
		);
		
		JLabel lblFrageSingle = new JLabel("<html>" + "" + "</html>");
		scrollPane_1.setViewportView(lblFrageSingle);
		lblFrageSingle.setVerticalAlignment(SwingConstants.TOP);
		lblFrageSingle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrageSingle.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		singlePanel.setLayout(gl_singlePanel);
		
		JPanel multiPanel = new JPanel();
		multiPanel.setBackground(Color.WHITE);
		currentQuestion.add(multiPanel, "multiChoice");
		
		JLabel label_4 = new JLabel("Frage:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		border = BorderFactory.createLineBorder(Color.BLACK);
		
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
		
			
		fragenList = currentFB.getFragen();
		String fragenDescr = "";
		int fragenID = 0;
		ArrayList<String> antwortenList = new ArrayList<String>();
		//System.out.println(fragenList);
		if(!fragenList.isEmpty()){
			//System.out.println("Fragenlist nicht leer");
			Frage currentFrage = fragenList.get(fragenZahl);
			fragenID = currentFrage.getFragetyp();
			fragenDescr = currentFrage.getFragebeschreibung();
			antwortenList = currentFrage.getAntwortmoeglichkeiten();
			System.out.println("FragenID: " + fragenID);
			if(fragenID == 0){
				//JA/NEIN
				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "jaNeinChoice");
			    lblFrage.setText("<html>" + fragenDescr + "</html>");
			   
			}else if(fragenID == 1){
				//Single-Choice
				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "singleChoice");
			    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
			    for(int i = antwortenList.size();i>0;i--){
			    	comboBox.addItem(antwortenList.get(i-1));
			    }
			    
			}else if(fragenID == 2){
				//Multiple-Choice
				CardLayout cl = (CardLayout)(currentQuestion.getLayout());
			    cl.show(currentQuestion, "multiChoice");
			    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
			    //System.out.println("-><- " + antwortenList.size());
			    
			    if(antwortenList.size()==1){
			    	
			    	multiAnswer1.setText(antwortenList.get(0));
			    	multiAnswer2.setEnabled(false);
			    	multiAnswer3.setEnabled(false);
			    	multiAnswer4.setEnabled(false);
			    	multiAnswer5.setEnabled(false);
			    	
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
			    	
			    	multiAnswer1.setText(antwortenList.get(0));
			    	multiAnswer2.setText(antwortenList.get(1));
			    	multiAnswer3.setEnabled(false);
			    	multiAnswer4.setEnabled(false);
			    	multiAnswer5.setEnabled(false);
			    	
			    	multiAnswer3.setText("-");
			    	multiAnswer4.setText("-");
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==3){
			    	
			    	multiAnswer1.setText(antwortenList.get(0));
			    	multiAnswer2.setText(antwortenList.get(1));
			    	multiAnswer3.setText(antwortenList.get(2));
			    	multiAnswer4.setEnabled(false);
			    	multiAnswer5.setEnabled(false);
			    	
			    	multiAnswer4.setText("-");
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==4){
			    	
			    	multiAnswer1.setText(antwortenList.get(0));
			    	multiAnswer2.setText(antwortenList.get(1));
			    	multiAnswer3.setText(antwortenList.get(2));
			    	multiAnswer4.setText(antwortenList.get(3));
			    	multiAnswer5.setEnabled(false);
			    	
			    	multiAnswer5.setText("-");
			    	
			    	multiAnswer1.setSelected(false);
			    	multiAnswer2.setSelected(false);
			    	multiAnswer3.setSelected(false);
			    	multiAnswer4.setSelected(false);
			    	multiAnswer5.setSelected(false);
			    	
			    }else if(antwortenList.size()==5){
			    	
			    	multiAnswer1.setText(antwortenList.get(0));
			    	multiAnswer2.setText(antwortenList.get(1));
			    	multiAnswer3.setText(antwortenList.get(2));
			    	multiAnswer4.setText(antwortenList.get(3));
			    	multiAnswer5.setText(antwortenList.get(4));
			    	
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
				ArrayList<Integer> innerAntworten = new ArrayList<Integer>();
				innerAntworten.add((comboBox.getSelectedIndex()+1));
				antwortenSolver.add(innerAntworten);
				System.out.println(comboBox.getSelectedIndex());
				comboBox.removeAllItems();
				
				if(fragenList.size()>fragenZahl+1){
					System.out.println("Next question detected!");
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();
					if(!fragenList.isEmpty()){
						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						System.out.println("FragenID: " + fragenID);
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    for(int i = antwortenList.size();i>0;i--){
						    	comboBox.addItem(antwortenList.get(i-1));
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    
						    if(antwortenList.size()==1){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setEnabled(false);
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
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
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setText(antwortenList.get(4));
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
				}else{
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		
		
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//next question
				if(rdbtnJa.isSelected()){
					ArrayList<Integer> innerAntworten = new ArrayList<Integer>();
					//-> Answered JA
					innerAntworten.add(1);
					antwortenSolver.add(innerAntworten);
					System.out.println(1);
				}else if (rdbtnNein.isSelected()){
					ArrayList<Integer> innerAntworten = new ArrayList<Integer>();
					//-> Answered NEIN
					innerAntworten.add(2);
					antwortenSolver.add(innerAntworten);
					System.out.println(2);
				}else{
					//No answer selected
					//Code below can't be run!
				}
				rdbtnJa.setSelected(false);
				rdbtnNein.setSelected(false);
				if(fragenList.size()>fragenZahl+1){
					System.out.println("Next question detected!");
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();
					//System.out.println(fragenList);
					if(!fragenList.isEmpty()){
						//System.out.println("Fragenlist nicht leer");
						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						System.out.println("FragenID: " + fragenID);
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    for(int i = antwortenList.size();i>0;i--){
						    	comboBox.addItem(antwortenList.get(i-1));
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    //System.out.println("-><- " + antwortenList.size());
						    
						    if(antwortenList.size()==1){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setEnabled(false);
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
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
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setText(antwortenList.get(4));
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
				}else{
					tabbedPane.setSelectedIndex(2);
				}
				
				
			}
		});
		
		JButton btnNext3 = new JButton("N\u00E4chste Frage");
		btnNext3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//String multiAntwort = "";
				ArrayList<Integer> innerAntworten = new ArrayList<Integer>();
				if(multiAnswer1.isSelected() && multiAnswer1.isEnabled()){
					//multiAntwort = multiAntwort + "0";
					innerAntworten.add(1);
				}
				if(multiAnswer2.isSelected() && multiAnswer2.isEnabled()){
					//multiAntwort = multiAntwort + "1";
					innerAntworten.add(2);
				}
				if(multiAnswer3.isSelected() && multiAnswer3.isEnabled()){
					//multiAntwort = multiAntwort + "2";
					innerAntworten.add(3);
				}
				if(multiAnswer4.isSelected() && multiAnswer4.isEnabled()){
					//multiAntwort = multiAntwort + "3";
					innerAntworten.add(4);
				}
				if(multiAnswer5.isSelected() && multiAnswer5.isEnabled()){
					//multiAntwort = multiAntwort + "4";
					innerAntworten.add(5);
				}


					antwortenSolver.add(innerAntworten);

				
				if(fragenList.size()>fragenZahl+1){
					System.out.println("Next question detected! - change coming");
					fragenZahl++;
					tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), "Frage " + (fragenZahl+1));
					fragenList = currentFB.getFragen();
					String fragenDescr = "";
					int fragenID = 0;
					ArrayList<String> antwortenList = new ArrayList<String>();
					//System.out.println(fragenList);
					if(!fragenList.isEmpty()){
						//System.out.println("Fragenlist nicht leer");
						Frage currentFrage = fragenList.get(fragenZahl);
						fragenID = currentFrage.getFragetyp();
						fragenDescr = currentFrage.getFragebeschreibung();
						antwortenList = currentFrage.getAntwortmoeglichkeiten();
						System.out.println("FragenID: " + fragenID);
						if(fragenID == 0){
							//JA/NEIN
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "jaNeinChoice");
						    lblFrage.setText("<html>" + fragenDescr + "</html>");
						   
						}else if(fragenID == 1){
							//Single-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "singleChoice");
						    lblFrageSingle.setText("<html>" + fragenDescr + "</html>");
						    for(int i = 0; i < antwortenList.size();i++){
						    	comboBox.addItem(antwortenList.get(i));
						    }
						    
						}else if(fragenID == 2){
							//Multiple-Choice
							CardLayout cl = (CardLayout)(currentQuestion.getLayout());
						    cl.show(currentQuestion, "multiChoice");
						    lblFrageMulti.setText("<html>" + fragenDescr + "</html>");
						    //System.out.println("-><- " + antwortenList.size());
						    
						    if(antwortenList.size()==1){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setEnabled(false);
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
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
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setEnabled(false);
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer3.setText("-");
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==3){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setEnabled(false);
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer4.setText("-");
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==4){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setEnabled(false);
						    	
						    	multiAnswer5.setText("-");
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else if(antwortenList.size()==5){
						    	
						    	multiAnswer1.setText(antwortenList.get(0));
						    	multiAnswer2.setText(antwortenList.get(1));
						    	multiAnswer3.setText(antwortenList.get(2));
						    	multiAnswer4.setText(antwortenList.get(3));
						    	multiAnswer5.setText(antwortenList.get(4));
						    	
						    	multiAnswer1.setSelected(false);
						    	multiAnswer2.setSelected(false);
						    	multiAnswer3.setSelected(false);
						    	multiAnswer4.setSelected(false);
						    	multiAnswer5.setSelected(false);
						    	
						    }else{
						    	
						    }
						    
						    
						}else{
							//Error
							
						}
					}
					
				}else{
					tabbedPane.setSelectedIndex(2);
				}
			//Next question
			//Get selected indexes -> int? - 01234	
					
					
			}
		});
		btnNext3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		
		
		
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
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_multiPanel.createSequentialGroup()
							.addComponent(lblAntwort_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_multiPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(multiAnswer1)
								.addComponent(multiAnswer2)
								.addComponent(multiAnswer3)
								.addComponent(multiAnswer4)
								.addComponent(multiAnswer5))))
					.addContainerGap(251, Short.MAX_VALUE))
		);
		gl_multiPanel.setVerticalGroup(
			gl_multiPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_multiPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_multiPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(339, Short.MAX_VALUE))
		);
		
		
		scrollPane_2.setViewportView(lblFrageMulti);
		lblFrageMulti.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFrageMulti.setVerticalAlignment(SwingConstants.TOP);
		lblFrageMulti.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		multiPanel.setLayout(gl_multiPanel);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("FB abgeben", null, panel, null);
		
		JButton btnFragebogenAbgeben = new JButton("Fragebogen abgeben");
		btnFragebogenAbgeben.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				boolean success = FBSSubmissionModul.submitFBS((Solver) Menu.getUser(), currentFB, antwortenSolver);
				System.err.println(success);
				if(success){
					Menu.launchMenu();
					setVisible(false);
				}
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
					.addComponent(btnFragebogenAbgeben, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	public static void updateGui(){
		
	}
}
