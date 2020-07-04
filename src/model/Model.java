package model;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import geometry.Shape;
/* *
 * * The Model class that extends JPanel and manages all shapes
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Model extends JPanel {


	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	/**
	 * Constructor that creates Model object
	 */
	public Model() {
		super();
		shapes = new ArrayList<Shape>();
	}
	/**
	 * This method returns all shapes
	 * @return all shapes
	 */
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	/**
	 * This method adds shape and draws it
	 * @param o Shape that will be added
	 */
	public void addShape(Shape o) {
		shapes.add(o);
		o.draw(this.getGraphics());

	}

	/**
	 * This method removes shape and deletes it
	 * @param o Shape that will be removed
	 */
	public void removeShape(Shape o) {
		shapes.remove(o);
		repaint();
	}

	/**
	 * All shapes gets deselected
	 */
	public void deselectAllShapes(){
		for (int i =shapes.size()-1;i>=0;i--){
			if(shapes.get(i).isSelected()){
				shapes.get(i).setSelected(false);
				selectedShapes.remove(shapes.get(i));
			}
		}
	}

	/**
	 *  Finds selected shape
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return ArrayList of Shapes
	 */
	public ArrayList<Shape> findSelected(int x, int y) 
	{
		for (int i = shapes.size() - 1; i >= 0; i--) 
		{
			if (shapes.get(i).contains(x, y)) 
			{
				if(shapes.get(i).isSelected()) 
				{
					shapes.get(i).setSelected(false);
					selectedShapes.remove(shapes.get(i));
				}
				else 
				{
					selectedShapes.add(shapes.get(i));
					shapes.get(i).setSelected(true);
				}
			}
		}
		if(!selectedShapes.isEmpty()) {
			repaint();
			return selectedShapes;
		}

		for (Shape o : shapes)
			o.setSelected(false);
		repaint();
		selectedShapes.clear();
		return null;
	}

	/**
	 * This method paints all shapes
	 * @param g Graphics g
	 */
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape o : shapes) {
			o.draw(g);
		}
		repaint();
	}
}
