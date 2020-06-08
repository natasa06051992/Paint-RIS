package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Square;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;

public class DlgSqrC extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField lengthOfSide;
	public Square square;
	private Color colorEdge = Color.BLACK;
	private Color colorInside = Color.WHITE;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JButton btnDismiss;

	public JButton getBtnDismiss() {
		return btnDismiss;
	}

	public void setBtnDismiss(JButton btnDismiss) {
		this.btnDismiss = btnDismiss;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	JButton btnSubmit;

	public void setSquare(Square square) {
		this.square = square;
	}

	public JTextField getLengthOfSide() {
		return lengthOfSide;
	}

	public Color getColorEdge() {
		return colorEdge;
	}

	public Color getColorInside() {
		return colorInside;
	}


	public static void main(String[] args) {
		try {
			DlgSqrC dialog = new DlgSqrC();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSqrC() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(3, 2, 3, 10));
			{
				JLabel lblLengthOfSide = new JLabel("Enter length of side:");
				panel.add(lblLengthOfSide);
			}
			{
				lengthOfSide = new JTextField();
				panel.add(lengthOfSide);
				lengthOfSide.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSubmit = new JButton("Submit");
				buttonGroup.add(btnSubmit);
				btnSubmit.setActionCommand("Submit");
				buttonPane.add(btnSubmit);
				getRootPane().setDefaultButton(btnSubmit);
			}
			{
				btnDismiss = new JButton("Cancel");
				buttonGroup.add(btnDismiss);
				btnDismiss.setActionCommand("Dismiss");
				buttonPane.add(btnDismiss);
			}
		}
	}
	public Square getSquare(){
		return square;
	}
}
