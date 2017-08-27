package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import guiModules.FBALoaderModul;
import guiModules.FBDistributionModul;
import guiModules.FBLoaderModul;
import guiModules.FBRemovalModul;
import umfrage.Fragebogen;
import user.Creator;

import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import auswertung.Fragebogenauswertung;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * @author Jonathan Grenda
 *
 */
public class FBManage extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void mainRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FBManage frame = new FBManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FBManage() {
		setResizable(false);
		setTitle("QuestionMark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("List", null, panel, null);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		JList<String> auswertungsList = new JList<String>(model2);
		auswertungsList.setToolTipText("Liste aller Auswertungen.");
		
		//Display All FBS
		Creator currentUser = (Creator) Menu.getUser();
		ArrayList<Fragebogen> fbList = FBLoaderModul.loadFB(currentUser);
		Iterator<Fragebogen> fbListIt = fbList.iterator();
		Fragebogen currentFB;  
		while(fbListIt.hasNext()){
			currentFB = fbListIt.next();
			String titel = currentFB.getTitel();
			model.addElement(titel);
		}
		
		
		
		Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title;
		title = BorderFactory.createTitledBorder(blackline, "Verteilung");
		title.setTitleJustification(TitledBorder.CENTER);
		
		JPanel verteilenPanel = new JPanel();
		verteilenPanel.setBorder(title);
		JList<String> list = new JList<String>(model);
		list.setToolTipText("Liste von erstellten Frageb\u00F6gen");

		JButton btnLschen = new JButton("L\u00F6schen");
		btnLschen.setToolTipText("Dr\u00FCcken Sie diesen Knopf um einen ausgew\u00E4hlten Fragebogen zu l\u00F6schen.");
		btnLschen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(list.getSelectedIndex() != -1){
					int choice = list.getSelectedIndex();
					Fragebogen selected = fbList.get(choice);
					FBRemovalModul.removeFB((Creator) Menu.getUser(), selected);
					model.remove(choice);
				}
			
			}
		});
		btnLschen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAbbrechen = new JButton("Zurück");
		btnAbbrechen.setToolTipText("Zur\u00FCck zum Menu");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Close FBManage -> Return to Menu
				Menu.launchMenu();
				setVisible(false);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnLschen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(verteilenPanel, GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE))
						.addComponent(btnAbbrechen, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(verteilenPanel, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLschen)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAbbrechen)
					.addGap(681))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(668, Short.MAX_VALUE))
		);
		
		scrollPane.setViewportView(list);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JCheckBox chckbxKursA = new JCheckBox("Kurs A");
		chckbxKursA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JCheckBox chckbxKursB = new JCheckBox("Kurs B");
		chckbxKursB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnVerteilen = new JButton("Verteilen");
		btnVerteilen.setToolTipText("Dr\u00FCcken Sie den Knopf um den ausgew\u00E4hlten Fragebogen an die gew\u00E4hlten Kurse zu verteilen.");
		btnVerteilen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Distribute FB selected if Kurs chosen
				if((chckbxKursA.isSelected() || chckbxKursB.isSelected()) && list.getSelectedIndex() != -1){
					//Security check
					if(chckbxKursA.isSelected()){
						int choice = list.getSelectedIndex();
						Fragebogen selected = fbList.get(choice);
						boolean success = FBDistributionModul.distributeFB(Menu.getSystem(), true, selected, (Creator) Menu.getUser());
						if(success){
							JFrame framePop = new JFrame();
							JOptionPane.showMessageDialog(framePop, "Der Fragebogen wurde erfolgreich an Kurs A verteilt","Glückwunsch",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(chckbxKursB.isSelected()){
						int choice = list.getSelectedIndex();
						Fragebogen selected = fbList.get(choice);
						boolean success = FBDistributionModul.distributeFB(Menu.getSystem(), false, selected, (Creator) Menu.getUser());
						if(success){
							JFrame framePop = new JFrame();
							JOptionPane.showMessageDialog(framePop, "Der Fragebogen wurde erfolgreich an Kurs B verteilt","Glückwunsch",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				}else{
					JFrame framePop = new JFrame();
					JOptionPane.showMessageDialog(framePop, "Sie müssen einen Kurs auswählen!","Information",JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				
			}
		});
		btnVerteilen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_verteilenPanel = new GroupLayout(verteilenPanel);
		gl_verteilenPanel.setHorizontalGroup(
			gl_verteilenPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_verteilenPanel.createSequentialGroup()
					.addGroup(gl_verteilenPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_verteilenPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_verteilenPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxKursA)
								.addComponent(chckbxKursB)))
						.addComponent(btnVerteilen, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_verteilenPanel.setVerticalGroup(
			gl_verteilenPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_verteilenPanel.createSequentialGroup()
					.addComponent(chckbxKursA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxKursB)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVerteilen, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addGap(314))
		);
		verteilenPanel.setLayout(gl_verteilenPanel);
		panel.setLayout(gl_panel);
		
		JPanel Auswertungen = new JPanel();
		Auswertungen.setBackground(Color.WHITE);
		tabbedPane.addTab("Auswertungen", null, Auswertungen, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnAnzeigen = new JButton("Anzeigen");
		btnAnzeigen.setToolTipText("Zeig die Auswertung des gew\u00E4hlten Fragebogens an.");
		btnAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnzeigen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//FBEvaluate aufrufen:
				if(auswertungsList.getSelectedIndex()!= -1 && btnAnzeigen.isEnabled()){
					int selected = auswertungsList.getSelectedIndex();
					ArrayList<Fragebogenauswertung> fbAs = FBALoaderModul.loadFBA((Creator) Menu.getUser());
					Fragebogenauswertung selectedAuswertung = fbAs.get(selected);
					FBEvaluate.evaluateFB(selectedAuswertung);
					setVisible(false);
				}

				
			}
		});
		btnAnzeigen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnAbbrechen_1 = new JButton("Zurück");
		btnAbbrechen_1.setToolTipText("Zur\u00FCck zum Menu");
		btnAbbrechen_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Return to menu
				Menu.launchMenu();
				setVisible(false);
			}
		});
		btnAbbrechen_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_Auswertungen = new GroupLayout(Auswertungen);
		gl_Auswertungen.setHorizontalGroup(
			gl_Auswertungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Auswertungen.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Auswertungen.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAnzeigen, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
						.addComponent(btnAbbrechen_1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_Auswertungen.setVerticalGroup(
			gl_Auswertungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Auswertungen.createSequentialGroup()
					.addGroup(gl_Auswertungen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Auswertungen.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAnzeigen)
							.addGap(313)
							.addComponent(btnAbbrechen_1))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(668, Short.MAX_VALUE))
		);
		
		
		auswertungsList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ArrayList<Fragebogenauswertung> fbAs = FBALoaderModul.loadFBA((Creator) Menu.getUser());
		Iterator<Fragebogenauswertung> fbAsIt = fbAs.iterator();
		while(fbAsIt.hasNext()){
			Fragebogenauswertung fbAShell = fbAsIt.next();
			model2.addElement(fbAShell.getSourceFragebogen().getTitel() + " - Kurs: " + fbAShell.getOwnedBy().getKursName());
		}
		
		
		scrollPane_1.setViewportView(auswertungsList);
		Auswertungen.setLayout(gl_Auswertungen);
	}
}
