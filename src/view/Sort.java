package view;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import geometry.Square;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;

public class Sort extends JFrame {

	static DefaultListModel dlm = new DefaultListModel();
	private JPanel pnlSortMain;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sort() {

		setTitle("RIS");
		JPanel pnlSortMain = new JPanel();
		getContentPane().add(pnlSortMain, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Da", "Ne" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Da li ste sigurni da �elite da iza�ete?",
						"Potvrda izlaska", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);
				if (PromptResult == 0) {
					System.exit(0);
				}
			}
		});
		GridBagLayout gbl_pnlSortMain = new GridBagLayout();
		gbl_pnlSortMain.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlSortMain.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlSortMain.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlSortMain.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlSortMain.setLayout(gbl_pnlSortMain);

		JScrollPane scrollPaneSort = new JScrollPane();
		GridBagConstraints gbc_scrollPaneSort = new GridBagConstraints();
		gbc_scrollPaneSort.gridheight = 4;
		gbc_scrollPaneSort.gridwidth = 3;
		gbc_scrollPaneSort.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneSort.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneSort.gridx = 3;
		gbc_scrollPaneSort.gridy = 1;
		pnlSortMain.add(scrollPaneSort, gbc_scrollPaneSort);

		JList lstSort = new JList();
		scrollPaneSort.setViewportView(lstSort);
		lstSort.setModel(dlm);

		JButton btnSort = new JButton("Sortiraj");
		buttonGroup.add(btnSort);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Square> squares = new ArrayList<Square>();

				for (int i = 0; i < dlm.size(); i++) {
					squares.add((Square) dlm.get(i));
				}
				Collections.sort(squares);
				dlm.removeAllElements();
				for (Square kv : squares) {
					dlm.addElement(kv);
				}
			}
		});

		JButton btnAddInList = new JButton("Dodaj u listu");
		buttonGroup.add(btnAddInList);
		btnAddInList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSquare dlgs = new DlgSquare(true);
				dlgs.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnAddInList = new GridBagConstraints();
		gbc_btnAddInList.anchor = GridBagConstraints.SOUTH;
		gbc_btnAddInList.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddInList.gridx = 3;
		gbc_btnAddInList.gridy = 5;
		pnlSortMain.add(btnAddInList, gbc_btnAddInList);
		GridBagConstraints gbc_btnSort = new GridBagConstraints();
		gbc_btnSort.insets = new Insets(0, 0, 5, 5);
		gbc_btnSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSort.gridx = 4;
		gbc_btnSort.gridy = 5;
		pnlSortMain.add(btnSort, gbc_btnSort);

	}
}
