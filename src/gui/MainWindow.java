package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.LineBorder;

import guiModules.LoginModul;
import guiModules.PersistenzModul;
import upper.containertier.Gesamtsystem;
import user.Creator;
import user.Solver;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
/**
 * @author Jonathan Grenda
 *
 */
public class MainWindow {	
	
	private JFrame frmQuestionmark;
	private JTextField textField;
	private JPasswordField passwordField;
	private String pfad = "C:\\OOP - Projekt";
	private String dateiname = "QuestionMarkFile";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmQuestionmark.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Gesamtsytem laden basierend auf pfad & dateiname
		Gesamtsystem currentSys = PersistenzModul.loadGesamtsystem(pfad, dateiname);
		
		//Kontrolle ob das System gefunden wurde
		if(currentSys == null){
			JFrame framePop = new JFrame();
			JOptionPane.showMessageDialog(framePop, "Es ist ein Fehler aufgetreten, das benötigte Gesamtsystem konnte nicht gefunden werden.\nStellen Sie sicher, dass das Programm korrekt installiert wurde.\nDas Programm beendet sich nun automatisch!","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		/**
		 * Windowbuilder Code
		 * |
		 * V
		 */
		
		frmQuestionmark = new JFrame();
		frmQuestionmark.setResizable(false);
		frmQuestionmark.setTitle("QuestionMark");
		frmQuestionmark.setBounds(100, 100, 500, 300);
		frmQuestionmark.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{478, 0};
		gridBagLayout.rowHeights = new int[]{37, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frmQuestionmark.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frmQuestionmark.getContentPane().add(panel, gbc_panel);
		
		JLabel lblWillkommenBeiQuestionmark = new JLabel("Willkommen bei QuestionMark");
		lblWillkommenBeiQuestionmark.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblWillkommenBeiQuestionmark);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("CheckBox.background"));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frmQuestionmark.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblUserid = new JLabel("UserID:");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setToolTipText("Eingabefeld Benutzername");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(15);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Eingabefeld Passwort");
		passwordField.setColumns(15);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//Händischer Code
		JButton btnHelp = new JButton("Help");
		btnHelp.setToolTipText("Zum Aufrufen der HelpPage dr\u00FCcken");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Display Help HTML page
				try {
					File helpPage = new File("src/help/helpPage.html");
					java.awt.Desktop.getDesktop().open(helpPage);;
				} catch (Exception e) {
					System.out.println("Die HelpPage konnte nicht gefunden werden");
				}
				
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblTest = new JLabel("");
		lblTest.setForeground(Color.RED);
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setToolTipText("Login");
		
		//Händischer Code
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Handle Login Button pressed
				
				//Use LoginModul -> attempt Login
				//
				// if return != null -> success
				// pass UserShell along to Menu
				
				
				if(textField.getText().startsWith("s")){
					String psw = new String(passwordField.getPassword());
					Solver currentUser = LoginModul.attemptSolverLogin(currentSys, textField.getText(), psw);
					if(currentUser!=null){
						Menu.launchSolverMenu(currentUser, currentSys);
						frmQuestionmark.setVisible(false);
					}else{
						lblTest.setText("Login fehlerhaft!");
					}
					
				}else if(textField.getText().startsWith("c")){
					String psw = new String(passwordField.getPassword());
					Creator currentUser = LoginModul.attemptCreatorLogin(currentSys, textField.getText(), psw);
					if(currentUser!=null){
						Menu.launchCreatorMenu(currentUser, currentSys);
						frmQuestionmark.setVisible(false);
					}else{
						lblTest.setText("Login fehlerhaft!");
					}
					
				}else{
					//Error
					lblTest.setText("Login fehlerhaft!");
				}
				
			}
		});
		
		/**
		 * Windowbuilder Code
		 * |
		 * V
		 */
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(btnHelp)
							.addGap(12)
							.addComponent(btnNewButton))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPasswort)
								.addComponent(lblUserid))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(131))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)))
							.addGap(210)))
					.addGap(398))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTest, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserid))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPasswort))
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHelp)
						.addComponent(btnNewButton))
					.addGap(3)
					.addComponent(lblTest, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		
	}

}
