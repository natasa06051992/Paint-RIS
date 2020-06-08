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
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class DlgDrawing extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	protected JTextField txtX;
	protected JTextField txtY;
	protected JTextField txtLength;
	public Color colorEdge;
	public Color colorInside;

	public static void main(String[] args) {
		try {
			DlgDrawing dialog = new DlgDrawing(true);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDrawing(boolean flag) {
		setTitle("RIS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Yes","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Do you want to exit?", "Security question",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			      System.exit(0);          
			    }
			  }
			});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_sadrzajPanel = new GridBagLayout();
		gbl_sadrzajPanel.columnWidths = new int[]{0, 0, 0};
		gbl_sadrzajPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_sadrzajPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_sadrzajPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_sadrzajPanel);
		{
			JLabel lblX = new JLabel("X coordinate of point up left:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.EAST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate of point up left:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.EAST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setColumns(10);
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
		}
		{
			JLabel lblLength = new JLabel("Length of side:");
			GridBagConstraints gbc_lblLength = new GridBagConstraints();
			gbc_lblLength.anchor = GridBagConstraints.WEST;
			gbc_lblLength.insets = new Insets(0, 0, 5, 5);
			gbc_lblLength.gridx = 0;
			gbc_lblLength.gridy = 2;
			contentPanel.add(lblLength, gbc_lblLength);
		}
		{
			txtLength = new JTextField();
			txtLength.setColumns(10);
			GridBagConstraints gbc_txtLengthSide = new GridBagConstraints();
			gbc_txtLengthSide.insets = new Insets(0, 0, 5, 0);
			gbc_txtLengthSide.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLengthSide.gridx = 1;
			gbc_txtLengthSide.gridy = 2;
			contentPanel.add(txtLength, gbc_txtLengthSide);
		}
		
		{
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(btnPanel, BorderLayout.SOUTH);
			{
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(flag){
							try {
								int lengthSide = Integer.parseInt(txtLength.getText());
								int x = Integer.parseInt(txtX.getText());
								int y = Integer.parseInt(txtY.getText());
								
								Point t1 = new Point(x, y); 
								Square square = new Square(t1, lengthSide);

								dispose();	
								
							} catch (NumberFormatException e) {
							  
								JOptionPane.showMessageDialog(null,
									    "Enter number.",
									    "Wrong input",
									    JOptionPane.ERROR_MESSAGE);
							}

											
						}
						else {		
							 String ObjButtons[] = {"Yes","No"};
							    int PromptResult = JOptionPane.showOptionDialog(null, 
							        "You want to delete square?", "Security question",
							        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
							        ObjButtons,ObjButtons[1]);
							    if(PromptResult==0)
							    {							
									Steak.dlm.remove(0);
									
									dispose(); 
							    }
						}
					}
				}
								
					
				);
				btnSubmit.setActionCommand("Potvrdi");
				btnPanel.add(btnSubmit);
				getRootPane().setDefaultButton(btnSubmit);
			}
			{
				JButton btnDismis = new JButton("Dismiss");
				btnDismis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose(); 
					}
				});
				btnDismis.setActionCommand("Dismiss");
				btnPanel.add(btnDismis);
			}
		}
	}
	public void setValues()
	{
			Square square = (Square) Steak.dlm.get(0); 
			
			txtX.setText(String.valueOf(square.getUpLeft().getX()));
			txtY.setText(String.valueOf(square.getUpLeft().getY()));	
			txtLength.setText(String.valueOf(square.getSideLength()));
	}
}

	