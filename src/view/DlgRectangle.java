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

/* *
 * * The DlgRectangle class that extends JDialog
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField widthRectangle;
	private JTextField heightRectangle;
	public Rectangle rectangle;
	private Color colorEdge = Color.BLACK;
	JButton btnDismiss;
	private Color colorInside = Color.WHITE;
	JButton btnSubmit;

	/**
	 * Sets Rectangle
	 * @param rectangle Rectangle
	 */
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	/**
	 * Gets edge color
	 * @return edge inside
	 */
	public Color getColorEdge() {
		return colorEdge;
	}

	/**
	 * Gets inside color
	 * @return color inside
	 */
	public Color getColorInside() {
		return colorInside;
	}


	/**
	 * Gets button btnSubmit
	 * @return btnSubmit
	 */
	public JButton getBtnSubmit()
	{
		return btnSubmit;
	}

	/**
	 * Gets button btnDismiss
	 * @return btnDismiss
	 */
	public JButton getBtnDismiss() {
		return btnDismiss;
	}

	/**
	 * Gets text field widthRectangle
	 * @return text field widthRectangle
	 */
	public JTextField getWidthRectangle() {
		return widthRectangle;
	}

	/**
	 * Gets text field heightRectangle
	 * @return text field heightRectangle
	 */
	public JTextField getHeightRectangle() {
		return heightRectangle;
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

	/**
	 * Gets rectangle
	 * @return rectangle
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}
}
