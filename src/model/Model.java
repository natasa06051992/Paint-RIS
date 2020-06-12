package model;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import geometry.Shape;

public class Model extends JPanel {

	// private int x = 10;
	// private int y = 10;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public Model() {
		super();
		shapes = new ArrayList<Shape>();
	}

	public void addShape(Shape o) {
		shapes.add(o);
		o.draw(this.getGraphics());

	}

	public void removeShape(Shape o) {
		shapes.remove(o);
		repaint();
	}

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

	public void paint(Graphics g) {
		super.paint(g);
		for (Shape o : shapes) {
			o.draw(g);
		}
		repaint();
	}
}
