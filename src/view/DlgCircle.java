package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;


public class DlgCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField radius;
	public Circle circle;
	JButton btnSubmit;

	public JTextField getRadius() {
		return radius;
	}

	public void setRadius(JTextField radius) {
		this.radius = radius;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public JButton getBtnDismiss() {
		return btnDismiss;
	}

	JButton btnDismiss;

	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(3, 2, 5, 10));
			{
				JLabel lblRadius = new JLabel("Enter radius of circle: ");
				panel.add(lblRadius);
			}
			{
				radius = new JTextField();
				panel.add(radius);
				radius.setColumns(10);
			}
		}
		{
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPanel, BorderLayout.SOUTH);
			{
				btnSubmit = new JButton("Submit");
				btnSubmit.setActionCommand("Submit");
				btnPanel.add(btnSubmit);
				getRootPane().setDefaultButton(btnSubmit);
			}
			{
				btnDismiss = new JButton("Dismiss");
				btnDismiss.setActionCommand("Dismiss");
				btnPanel.add(btnDismiss);
			}
		}
	}
	public Circle getCircle(){
		return circle; 
	}
}