package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Square;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgSquare extends JDialog {

	private final JPanel basicPanel = new JPanel();
	private JTextField txtXUpLeft;
	public int x;
	public int y;
	public int lengthSide;
	public String colorEdge;
	public String colorInside;
	private JTextField txtLengthSide;
	private JTextField txtYUpLeft;
	private JComboBox cbxColorEdge;
	private JComboBox cbxColorInside;
	Square square;
	private JButton btnDismiss;
	private JButton btnSubmit;

	/**
	 * Create the dialog.
	 */
	public DlgSquare(boolean flag) {
		setModal(true);
		setTitle("Square");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		basicPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(basicPanel, BorderLayout.CENTER);
		GridBagLayout gbl_basicPanel = new GridBagLayout();
		gbl_basicPanel.columnWidths = new int[]{0, 0, 0};
		gbl_basicPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_basicPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_basicPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		basicPanel.setLayout(gbl_basicPanel);
		{
			JLabel lblX = new JLabel("X coordinate point up left:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
			basicPanel.add(lblX, gbc_lblX);
		}
		{
			txtXUpLeft = new JTextField();
			GridBagConstraints gbc_txtXUpLeft = new GridBagConstraints();
			gbc_txtXUpLeft.insets = new Insets(0, 0, 5, 4);
			gbc_txtXUpLeft.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXUpLeft.gridx = 1;
			gbc_txtXUpLeft.gridy = 1;
			basicPanel.add(txtXUpLeft, gbc_txtXUpLeft);
			txtXUpLeft.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate point up left:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 2;
			basicPanel.add(lblY, gbc_lblY);
		}
		{
			txtYUpLeft = new JTextField();
			GridBagConstraints gbc_txtYUpLeft = new GridBagConstraints();
			gbc_txtYUpLeft.insets = new Insets(0, 0, 5, 4);
			gbc_txtYUpLeft.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYUpLeft.gridx = 1;
			gbc_txtYUpLeft.gridy = 2;
			basicPanel.add(txtYUpLeft, gbc_txtYUpLeft);
			txtYUpLeft.setColumns(10);
		}
		{
			JLabel lblLengthSide = new JLabel("Length of side:");
			GridBagConstraints gbc_lblLengthSide = new GridBagConstraints();
			gbc_lblLengthSide.anchor = GridBagConstraints.EAST;
			gbc_lblLengthSide.insets = new Insets(0, 0, 5, 5);
			gbc_lblLengthSide.gridx = 0;
			gbc_lblLengthSide.gridy = 3;
			basicPanel.add(lblLengthSide, gbc_lblLengthSide);
		}
		{
			txtLengthSide = new JTextField();
			GridBagConstraints gbc_txtLengthSide = new GridBagConstraints();
			gbc_txtLengthSide.insets = new Insets(0, 0, 5, 4);
			gbc_txtLengthSide.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLengthSide.gridx = 1;
			gbc_txtLengthSide.gridy = 3;
			basicPanel.add(txtLengthSide, gbc_txtLengthSide);
			txtLengthSide.setColumns(10);
		}
		{
			JLabel lblColorEdge = new JLabel("Color of side:");
			lblColorEdge.setForeground(Color.BLACK);
			GridBagConstraints gbc_lblColorEdge = new GridBagConstraints();
			gbc_lblColorEdge.anchor = GridBagConstraints.EAST;
			gbc_lblColorEdge.insets = new Insets(0, 0, 5, 5);
			gbc_lblColorEdge.gridx = 0;
			gbc_lblColorEdge.gridy = 4;
			basicPanel.add(lblColorEdge, gbc_lblColorEdge);
		}
		{
			cbxColorEdge = new JComboBox();
			cbxColorEdge.setModel(new DefaultComboBoxModel(new String[] {"black", "white", "green", "yellow","blue","red"}));
			GridBagConstraints gbc_cbxColorEdge = new GridBagConstraints();
			gbc_cbxColorEdge.insets = new Insets(0, 0, 5, 0);
			gbc_cbxColorEdge.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxColorEdge.gridx = 1;
			gbc_cbxColorEdge.gridy = 4;
			basicPanel.add(cbxColorEdge, gbc_cbxColorEdge);
			cbxColorEdge.addActionListener(cbxColorEdge);
		}
		{
			JLabel lblColorInside = new JLabel("Color of inside:");
			GridBagConstraints gbc_lblColorInside = new GridBagConstraints();
			gbc_lblColorInside.anchor = GridBagConstraints.EAST;
			gbc_lblColorInside.insets = new Insets(0, 0, 5, 5);
			gbc_lblColorInside.gridx = 0;
			gbc_lblColorInside.gridy = 5;
			basicPanel.add(lblColorInside, gbc_lblColorInside);
		}
		{
			cbxColorInside = new JComboBox();
			cbxColorInside.setModel(new DefaultComboBoxModel(new String[] {"black", "white", "green", "yellow","blue","red"}));
			GridBagConstraints gbc_cbxColorInside = new GridBagConstraints();
			gbc_cbxColorInside.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxColorInside.anchor = GridBagConstraints.SOUTH;
			gbc_cbxColorInside.insets = new Insets(0, 0, 5, 0);
			gbc_cbxColorInside.gridx = 1;
			gbc_cbxColorInside.gridy = 5;
			basicPanel.add(cbxColorInside, gbc_cbxColorInside);
			cbxColorInside.addActionListener(cbxColorInside);
		}
		{
			JPanel pnlBtn = new JPanel();
			pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlBtn, BorderLayout.SOUTH);
			{
				btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {			
						if(flag){
							try {
						
							x = Integer.parseInt(txtXUpLeft.getText());
							y = Integer.parseInt(txtYUpLeft.getText());
							lengthSide = Integer.parseInt(txtLengthSide.getText());
							colorEdge = cbxColorEdge.getSelectedItem().toString();
							colorInside = cbxColorInside.getSelectedItem().toString();
							square= new Square(new Point(x, y), lengthSide, colorEdge, colorInside);
							Steak.dlm.add(0,square); 
							Sort.dlm.addElement(square);
							dispose();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "You must enter integer values!","Warning", JOptionPane.WARNING_MESSAGE);
						}}
						else {		
							 String ObjButtons[] = {"Yes","No"};
							    int PromptResult = JOptionPane.showOptionDialog(null, 
							        "Do you want to delete square?", "Security question",
							        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
							        ObjButtons,ObjButtons[1]);
							    if(PromptResult==0)
							    {							
									Steak.dlm.remove(0);
									
									dispose(); 
							    }
						}
					}}
				);
				btnSubmit.setActionCommand("Submit");
				pnlBtn.add(btnSubmit);	
				getRootPane().setDefaultButton(btnSubmit);
			}
			{
				btnDismiss = new JButton("Dismiss");
				btnDismiss.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnDismiss.setActionCommand("Dismiss");
				pnlBtn.add(btnDismiss);			
			}
		}
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLengthSide() {
		return lengthSide;
	}

	public String getColorEdge() {
		return colorEdge;
	}

	public String getColorInside() {
		return colorInside;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLengthSide(int lengthSide) {
		this.lengthSide = lengthSide;
	}

	public void setColorEdge(String colorEdge) {
		this.colorEdge = colorEdge;
	}

	public void setColorInside(String colorInside) {
		this.colorInside = colorInside;
	}

	public JTextField getTxtXUpLeft() {
		return txtXUpLeft;
	}

	public JTextField getTxtLengthSide() {
		return txtLengthSide;
	}

	public JTextField getTxtYUpLeft() {
		return txtYUpLeft;
	}

	public JComboBox getCbxColorEdge() {
		return cbxColorEdge;
	}

	public JComboBox getCbxColorInside() {
		return cbxColorInside;
	}

	public void setTxtXUpLeft(JTextField txtXUpLeft) {
		this.txtXUpLeft = txtXUpLeft;
	}

	public void setTxtLengthSide(JTextField txtLengthSide) {
		this.txtLengthSide = txtLengthSide;
	}

	public void setTxtYUpLeft(JTextField txtYUpLeft) {
		this.txtYUpLeft = txtYUpLeft;
	}

	public void setCbxColorEdge(JComboBox cbxColorEdge) {
		this.cbxColorEdge = cbxColorEdge;
	}

	public void setCbxColorInside(JComboBox cbxColorInside) {
		this.cbxColorInside = cbxColorInside;
	}

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
}