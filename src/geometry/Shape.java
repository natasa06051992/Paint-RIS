package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
/* *
 * * The Shape class that implements Serializable
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public abstract class Shape implements Serializable {

	private String colorString = "black";
	private  Color color = Color.lightGray;
	private boolean selected;
	private static final long serialversionUID = 129343489838L;
	/**
	 * Default constructor
	 */
	public Shape(){
		
	}

	/**
	 * Creates Shape object
	 * @param color color
	 */
	public Shape(String color){
		this.colorString = color;
	}

	/**
	 * Creates Shape object
	 * @param color color
	 */
	public Shape(Color color){
		this.color = color;
	}
	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public abstract void draw(Graphics g);
	/**
	 * Set shape to selected
	 * @param g Graphics
	 */
	public abstract void selected(Graphics g);
	/**
	 * Checks if one shape contains another
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if contains, otherwise false
	 */
	public abstract boolean contains(int x, int y);

	/**
	 * Retrurns color
	 * @param color color
	 * @return color
	 */
	public static Color findColor(String color){
		if(color.equalsIgnoreCase("blue"))
			return Color.BLUE;
		else if(color.equalsIgnoreCase("white"))
			return Color.WHITE;
		else if(color.equalsIgnoreCase("green"))
			return Color.GREEN;
		else if(color.equalsIgnoreCase("red"))
			return Color.RED;
		else if(color.equalsIgnoreCase("yellow"))
			return Color.YELLOW;
		else if(color.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;
	}

	/**
	 * Gets color
	 * @return color
	 */
	public String getColor() {
		return colorString;
	}

	/**
	 * sets color
	 * @param color color
	 */
	public void setColor(String color) {
		this.colorString = color;
	}

	/**
	 * True if selected, otherwise false
	 * @return True if selected, otherwise false
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Sets if shape is selected
	 * @param selected   True if selected, otherwise false
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Gets color
	 * @return color
	 */
	public Color getcColor() {
		return color;
	}

	/**
	 * Sets color
	 * @param color color
	 */
	public void setcColor(Color color) {
		this.color = color;
	}
}
