package view;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import geometry.Square;

import javax.swing.ButtonGroup;

public class Steak extends JFrame {

	private JPanel contentPane;
	static DefaultListModel dlm = new DefaultListModel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnDelete;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Steak frame = new Steak();
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Steak() {
		setTitle("RIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
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

		JPanel pnlCenter = new JPanel();
		getContentPane().add(pnlCenter);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);

		JScrollPane scrPaneSteak = new JScrollPane();
		GridBagConstraints gbc_scrPaneSteak = new GridBagConstraints();
		gbc_scrPaneSteak.gridwidth = 2;
		gbc_scrPaneSteak.insets = new Insets(10, 10, 10, 10);
		gbc_scrPaneSteak.fill = GridBagConstraints.BOTH;
		gbc_scrPaneSteak.gridx = 2;
		gbc_scrPaneSteak.gridy = 2;
		pnlCenter.add(scrPaneSteak, gbc_scrPaneSteak);

		JList listOfSquares = new JList();
		scrPaneSteak.setViewportView(listOfSquares);
		listOfSquares.setModel(dlm);

		JButton btnAdd = new JButton("Dodaj kvadrat");
		buttonGroup.add(btnAdd);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 10);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 4;
		pnlCenter.add(btnAdd, gbc_btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DlgSquare dlgSquare = new DlgSquare(true);
				dlgSquare.setVisible(true);
			}
		});

		btnDelete = new JButton("Izuzmi kvadrat");
		buttonGroup.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dlm.isEmpty()) {
					DlgSquare dlgSquare = new DlgSquare(false);
					Square square = (Square) Steak.dlm.get(0);
					dlgSquare.getTxtXUpLeft().setText(Integer.toString(square.getUpLeft().getX()));
					dlgSquare.getTxtXUpLeft().setEnabled(false);
					dlgSquare.getTxtYUpLeft().setText(Integer.toString(square.getUpLeft().getY()));
					dlgSquare.getTxtYUpLeft().setEnabled(false);
					dlgSquare.getTxtLengthSide().setText(Integer.toString(square.getSideLength()));
					dlgSquare.getTxtLengthSide().setEnabled(false);
					dlgSquare.getCbxColorEdge().setSelectedItem(square.getColorEdge());
					dlgSquare.getCbxColorInside().setSelectedItem(square.getColorInside());
					dlgSquare.getCbxColorEdge().setEnabled(false);
					dlgSquare.getCbxColorInside().setEnabled(false);
					dlgSquare.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Steak je prazan!", "Poruka", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 4;
		pnlCenter.add(btnDelete, gbc_btnDelete);
	}

	public DefaultListModel getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel dlm) {
		this.dlm = dlm;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
}