package view;

import controller.Controller;
import geometry.Point;
import geometry.Shape;
import logging.LogWriter;
import model.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/* *
 * * The Draw class that extends JFrame
 * * Main GUI
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Draw extends JFrame {

	private final Menu menuFile;
	private final MenuItem OpenLog;
	private final MenuItem OpenFile;
	private final MenuItem Save;

	public MenuItem getOpenFile() {
		return OpenFile;
	}

	public MenuItem getSave() {
		return Save;
	}

	public MenuItem getOpenLog() {
		return OpenLog;
	}

	public JTextArea getTxtInfo() {
		return txtInfo;
	}

	public void setTxtInfo(JTextArea txtInfo) {
		this.txtInfo = txtInfo;
	}

	private JTextArea txtInfo;
	private JPanel contentPane;
	public static DefaultListModel dlm = new DefaultListModel();
	private int x;
	private int y;
	private int secondClick = 0; 
	private Point firstPoint;
	private JComboBox cbxShapes;
	private Color colorEdge = Color.BLACK;
	private Color colorInside= Color.WHITE;
	private Shape selectedShape;
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private JTextField selectedShapeText;
/*	static JButton btnColor;
	static JButton btnColorInside;*/
	JButton btnModify;
	JButton btnDelete;
	MenuItem Undo;
	MenuItem Redo;

	public MenuItem getUndo() {
		return Undo;
	}

	public void setUndo(MenuItem undo) {
		Undo = undo;
	}

	public MenuItem getRedo() {
		return Redo;
	}

	public void setRedo(MenuItem redo) {
		Redo = redo;
	}

	java.awt.MenuBar menuBar;
	Menu menuEdit;
	public void setFirstPoint(Point firstPoint) {
		this.firstPoint = firstPoint;
	}

	Model model;

	public Model getModel() {
		return model;
	}

	public void setSecondClick(int secondClick) {
		this.secondClick = secondClick;
	}

	@Override
	public JPanel getContentPane() {
		return contentPane;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draw frame = new Draw();
					frame.setVisible(true);
					LogWriter.setup();
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Color getColorEdge() {
		return colorEdge;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	public int getSecondClick() {
		return secondClick;
	}

	public Point getFirstPoint() {
		return firstPoint;
	}

	public JComboBox getCbxShapes() {
		return cbxShapes;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColorInside() {
		return colorInside;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public JTextField getSelectedShapeText() {
		return selectedShapeText;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public JButton getBtnColorInside() {
		return btnColorInside;
	}
	public static Logger logger;
	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}
	final JButton btnColor ;
	final JButton btnColorInside;
	/**
	 * Create the frame.
	 */
	public Draw() {
		setTitle("RIS");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Controller controler = new Controller(this);
		setBounds(100, 100, 573, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlSever = new JPanel();
		pnlSever.setBackground(new Color(100, 149, 237));
		contentPane.add(pnlSever, BorderLayout.NORTH);
		GridBagLayout gbl_pnlSever = new GridBagLayout();
		gbl_pnlSever.columnWidths = new int[] {0, 100};
		gbl_pnlSever.rowHeights = new int[] {50, 0, 30, 30, 30, 30, 200};
		gbl_pnlSever.columnWeights = new double[]{0.0, 1.0};
		gbl_pnlSever.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0};
		pnlSever.setLayout(gbl_pnlSever);

		model = new Model();

		btnColor = new JButton("Color");
		btnColor.setBackground(Color.LIGHT_GRAY);

		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor.gridx = 0;
		gbc_btnColor.gridy = 5;
		pnlSever.add(btnColor, gbc_btnColor);

		btnColorInside = new JButton("Inside color");
		btnColorInside.setBackground(Color.LIGHT_GRAY);

		GridBagConstraints gbc_btnColorInside = new GridBagConstraints();
		gbc_btnColorInside.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnColorInside.anchor = GridBagConstraints.NORTH;
		gbc_btnColorInside.insets = new Insets(0, 0, 0, 5);
		gbc_btnColorInside.gridx = 0;
		gbc_btnColorInside.gridy = 6;
		pnlSever.add(btnColorInside, gbc_btnColorInside);

		btnModify = new JButton("Modify");

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setFocusPainted(false);

		btnModify.setEnabled(false);
		btnModify.setFocusPainted(false); 		


		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		pnlSever.add(scrollPane, gbc_scrollPane);

		selectedShapeText = new JTextField();
		txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setColumns(12);
		txtInfo.setText("");
		scrollPane.setViewportView(txtInfo);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModify.insets = new Insets(0, 0, 5, 5);
		gbc_btnModify.gridx = 0;
		gbc_btnModify.gridy = 2;
		pnlSever.add(btnModify, gbc_btnModify);
		selectedShapeText.setEnabled(false);

		model.setBorder(new LineBorder(new Color(0, 0, 0)));
		model.setBackground(Color.WHITE);

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 3;
		pnlSever.add(btnDelete, gbc_btnDelete);


		GridBagConstraints gbc_pnlForDraw = new GridBagConstraints();
		gbc_pnlForDraw.gridheight = 6;
		gbc_pnlForDraw.fill = GridBagConstraints.BOTH;
		gbc_pnlForDraw.gridx = 1;
		gbc_pnlForDraw.gridy = 1;
		pnlSever.add(model, gbc_pnlForDraw);

		cbxShapes = new JComboBox();
		GridBagConstraints gbc_cbxShapes = new GridBagConstraints();
		gbc_cbxShapes.insets = new Insets(0, 0, 5, 5);
		gbc_cbxShapes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxShapes.gridx = 0;
		gbc_cbxShapes.gridy = 4;

		Undo = new MenuItem("Undo");
		Redo = new MenuItem("Redo");
		OpenLog = new MenuItem("Open log");
		menuBar = new java.awt.MenuBar();
		setMenuBar(menuBar);
		menuFile = new Menu("File");
		menuBar.add(menuFile);
		menuFile.add(OpenLog);
		OpenFile = new MenuItem("Open file");
		Save = new MenuItem("Save");
		menuFile.add(OpenFile);
		menuFile.add(Save);
		menuEdit = new Menu("Edit");
		menuBar.add(menuEdit);
		menuEdit.add(Undo);
		menuEdit.add(Redo);

		cbxShapes.setModel(new DefaultComboBoxModel(new String[] {"Choose shape", "Point", "Line", "Square", "Rectangle", "Circle", "Hexagon"}));
		pnlSever.add(cbxShapes, gbc_cbxShapes);

		controler.setListeners();
	}

}

