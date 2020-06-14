package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField widthRectangle;
	private JTextField heightRectangle;
	public Rectangle rectangle;
	private Color colorEdge = Color.BLACK;

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Color getColorEdge() {
		return colorEdge;
	}

	public Color getColorInside() {
		return colorInside;
	}

	private Color colorInside = Color.WHITE;
	JButton btnSubmit;

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JButton getBtnDismiss() {
		return btnDismiss;
	}

	public void setBtnDismiss(JButton btnDismiss) {
		this.btnDismiss = btnDismiss;
	}

	JButton btnDismiss;

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getWidthRectangle() {
		return widthRectangle;
	}

	public void setWidthRectangle(JTextField widthRectangle) {
		this.widthRectangle = widthRectangle;
	}

	public JTextField getHeightRectangle() {
		return heightRectangle;
	}

	public void setHeightRectangle(JTextField heightRectangle) {
		this.heightRectangle = heightRectangle;
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(4, 2, 3, 10));
			{
				JLabel lblWidth = new JLabel("Enter width:");
				panel.add(lblWidth);
			}
			{
				widthRectangle = new JTextField();
				widthRectangle.setColumns(10);
				panel.add(widthRectangle);
			}
			{
				JLabel lblHeight = new JLabel("Enter height:");
				panel.add(lblHeight);
			}
			{
				heightRectangle = new JTextField();
				panel.add(heightRectangle);
				heightRectangle.setColumns(10);
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

	public Rectangle getRectangle() {
		return rectangle;
	}
}
