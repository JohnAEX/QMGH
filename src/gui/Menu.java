package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

import guiModules.PersistenzModul;
import upper.containertier.Gesamtsystem;
import user.Creator;
import user.Solver;
import user.User;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 * @author Jonathan Grenda
 *
 */
public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static boolean userIsCreator = true;
	private static User currentUser;
	private static Gesamtsystem currentGesSys;
	private String pfad = "";
	private String dateiname = "QuestionMarkFile";

	/**
	 * Launch the application.
	 */
	public static void launchMenu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchSolverMenu(Solver currentUserImp, Gesamtsystem currentSys){
		userIsCreator = false;
		currentUser = currentUserImp;
		currentGesSys = currentSys;
		launchMenu();
	}
	
	public static void launchCreatorMenu(Creator currentUserImp, Gesamtsystem currentSys){
		userIsCreator = true;
		currentUser = currentUserImp;
		currentGesSys = currentSys;
		launchMenu();
	}
	
	public static Gesamtsystem getSystem(){
		return currentGesSys;
	}
	public static User getUser(){
		return currentUser;
	}
	public static boolean userIsCreator(){
		return userIsCreator;
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		pfad = new File("").getAbsolutePath();
		setVisible(true);
		setResizable(false);
		setTitle("QuestionMark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblMainmenu = new JLabel("Mainmenu");
		lblMainmenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblMainmenu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblUserid = new JLabel("UserID:");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("<ID>");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setText(currentUser.getVorname() + " " + currentUser.getNachname());
		
		JLabel label_1 = new JLabel("1.");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnFragebogenErstellen = new JButton("Fragebogen erstellen");
		btnFragebogenErstellen.setToolTipText("Zum Erstellen eines Fragebogens dr\u00FCcken. Nur verf\u00FCgbar f\u00FCr Creator!");
		if(!userIsCreator){
			btnFragebogenErstellen.setEnabled(false);
		}else{
			btnFragebogenErstellen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
					FBCreate.mainRun();
				}
			});
			btnFragebogenErstellen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		btnFragebogenErstellen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("2.");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnFragebogenVerwalten = new JButton("Fragebogen verwalten");
		btnFragebogenVerwalten.setToolTipText("Zum Verwalten von Frageb\u00F6gen dr\u00FCcken. Nur verf\u00FCgbar f\u00FCr Creator!");
		if(!userIsCreator){
			btnFragebogenVerwalten.setEnabled(false);
		}else{
			btnFragebogenVerwalten.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnFragebogenVerwalten.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					FBManage.mainRun();
					setVisible(false);
				}
			});
		}
		btnFragebogenVerwalten.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_3 = new JLabel("3.");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnFragebogenAusfllen = new JButton("Fragebogen ausf\u00FCllen");
		btnFragebogenAusfllen.setToolTipText("Zum Auf\u00FCllen eines Fragebogens dr\u00FCcken. Nur verf\u00FCgbar f\u00FCr Solver!");
		
		//Decide UI Btn Ausfüllen
		if(userIsCreator){
			btnFragebogenAusfllen.setEnabled(false);
		}else{
			btnFragebogenAusfllen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					FBChoice.mainRun();
					setVisible(false);
				
				}
			});
		}
		btnFragebogenAusfllen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_4 = new JLabel("4.");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnProgrammBeenden = new JButton("Programm beenden");
		btnProgrammBeenden.setToolTipText("Zum Beenden und Speichern des Programms dr\u00FCcken");
		btnProgrammBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Close Programm
				//Save First:
				PersistenzModul.saveGesamtsystem(pfad, dateiname, currentGesSys);
				System.exit(0);
			}
		});
		btnProgrammBeenden.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUserid)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(90)
							.addComponent(label_1)
							.addGap(22)
							.addComponent(btnFragebogenErstellen, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(90)
							.addComponent(label_2)
							.addGap(22)
							.addComponent(btnFragebogenVerwalten))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(90)
							.addComponent(label_3)
							.addGap(22)
							.addComponent(btnFragebogenAusfllen, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(90)
							.addComponent(label_4)
							.addGap(22)
							.addComponent(btnProgrammBeenden, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(124, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblUserid))
					.addGap(100)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(label_1))
						.addComponent(btnFragebogenErstellen))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(label_2))
						.addComponent(btnFragebogenVerwalten))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(label_3))
						.addComponent(btnFragebogenAusfllen))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(label_4))
						.addComponent(btnProgrammBeenden)))
		);
		panel_1.setLayout(gl_panel_1);
	}
}
