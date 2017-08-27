package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import guiModules.FBSLoaderModul;
import umfrage.FragebogenWithAntwortmoeglichkeit;
import user.Creator;
import user.Solver;
import user.User;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;
import javax.swing.JLabel;
/**
 * @author Jonathan Grenda
 *
 */
public class FBChoice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void mainRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FBChoice frame = new FBChoice();
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
	public FBChoice() {
		setResizable(false);
		setTitle("QuestionMark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> list = new JList<String>(model);
		User currentUser = Menu.getUser();
		if(!Menu.userIsCreator()){
			ArrayList<FragebogenWithAntwortmoeglichkeit> fbList = FBSLoaderModul.loadFBS((Solver) currentUser);
			//Iterate through list and add to Model from JLIST - List
			Iterator<FragebogenWithAntwortmoeglichkeit> fbListIt = fbList.iterator();
			FragebogenWithAntwortmoeglichkeit currentFB;
			while(fbListIt.hasNext()){
				currentFB = fbListIt.next();
				String titel = currentFB.getTitel();
				model.addElement(titel);
			}
		}
		
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		border = BorderFactory.createLineBorder(Color.BLACK);
		
		JButton btnChoose = new JButton("Ausw\u00E4hlen");
		btnChoose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//IF FB Valid -> Open FB for answers
				int choice = list.getSelectedIndex();
				ArrayList<FragebogenWithAntwortmoeglichkeit> fbList = FBSLoaderModul.loadFBS((Solver) currentUser);
				FragebogenWithAntwortmoeglichkeit choosenFB = fbList.get(choice);
				FBAnswer.answerFB(choosenFB);
				setVisible(false);
				
			}
		});
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAbbrechen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			//Close FBChoice -> return to Menu
				Menu.launchMenu();
				setVisible(false);
			}
		});
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label = new JLabel("<ID>");
		label.setForeground(Color.BLUE);
		label.setText(currentUser.getVorname());
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(15)
								.addComponent(btnChoose))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(lblId)
								.addGap(1)
								.addComponent(label)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnAbbrechen)))
					.addGap(140))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnChoose)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
							.addComponent(btnAbbrechen))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		scrollPane.setViewportView(list);
		list.removeAll();
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.setLayout(gl_contentPane);
	}
}
