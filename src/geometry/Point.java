package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The Point class that extends SurfaceShape implements Shiftable
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Point extends Shape implements Shiftable{
	private int x;
	private int y;

	private static final long serialversionUID = 129348922838L;
	/**
	 * Default constructor
	 */
	public Point(){
	}

	/**
	 * Constructor that creates Point object
	 * @param x coordinate x
	 * @param y coordinate y
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		
	}

	/**
	 * Constructor that creates Point object
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param color color
	 */
	public Point(int x, int y, String color){
		this(x,y);
		setColor(color);
		
	}

	/**
	 * Constructor that creates Point object
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param color color
	 */
	public Point(int x, int y, Color color){
		this(x,y);
		setcColor(color);
		
	}

	/**
	 * Measures distance from two points
	 * @param point point
	 * @return distance from two points
	 */
	public double distance(Point point){
		int dx = x - point.x;
		int dy = this.y - point.y;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	/**
	 * Moves shape to x and y
	 * @param newX X coordinate
	 * @param newY Y coordinate
	 */
	public void moveTo(int newX, int newY){
		x = newX;
		y = newY;
	}
	/**
	 * Moves shape for x and y
	 * @param poX X coordinate
	 * @param poY Y coordinate
	 */
	public void moveFor(int poX, int poY){
		x = x + poX;
		y = y + poY;
	}

	/**
	 * Method that return shape with all parameters
	 * @return shape with all parameters
	 */
	public String toString(){
		return "Point "+x+" "+y+" "+getcColor().getRGB();
	}

	/**
	 * Checks if one object is equal to another
	 * @param obj object
	 * @return true if equals, otherwise false
	 */
	public boolean equals(Object obj){
		if(obj instanceof Point){
			Point point=(Point)obj;
			if(this.x==point.getX() &&  this.y==point.y)	
				return true;
			else
				return false;
		}
		return false;	
	}

	/**
	 * Checks if one shape contains another
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if contains, otherwise false
	 */
	public boolean contains(int x, int y){
		Point point = new Point(x, y);
		if(point.distance(this)<=2)
			return true;
		else
			return false;	
	}
	/**
	 * Set shape to selected
	 * @param g Graphics
	 */
	public void selected(Graphics g){
		g.setColor(findColor("blue"));
		g.drawRect(x-3, y-3, 6, 6);
	}

	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(getcColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected())
			selected(g);
	}

	/**
	 *  Gets x coordinate
	 * @return x
	 */
	public int getX(){
		return x;
	}

	/**
	 * Gets y coordinate
	 * @return y
	 */
	public int getY(){
		return y;
	}

	/**
	 * Sets x coordinate
	 * @param newX new x
	 */
	public void setX(int newX){
		x = newX;
	}

	/**
	 * Sets y coordinate
	 * @param newY new y
	 */
	public void setY(int newY){
		y = newY;
	}
}
