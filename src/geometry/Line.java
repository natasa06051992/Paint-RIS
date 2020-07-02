package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The Line class that extends SurfaceShape implements Shiftable
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Line extends Shape{
	private Point pStart;
	private Point pEnd;
	private static final long serialversionUID = 129348469838L;
	/**
	 * Default constructor
	 */
	public Line(){
	}

	/**
	 * Constructor that creates Line
	 * @param pStart start point
	 * @param pEnd end point
	 */
	public Line(Point pStart, Point pEnd){
		this.pStart = pStart;
		this.pEnd = pEnd;
	}

	/**
	 * Constructor that creates Line
	 * @param pStart start point
	 * @param pEnd end point
	 * @param color color of line
	 */
	public Line(Point pStart, Point pEnd,String color){
		this(pStart,pEnd);
		setColor(color);
	}
	/**
	 * Constructor that creates Line
	 * @param pStart start point
	 * @param pEnd end point
	 * @param color color of line
	 */
	public Line(Point pStart, Point pEnd, Color color){
		this(pStart,pEnd);
		setcColor(color); 
	}

	/**
	 * Length of line
	 * @return Length
	 */
	public double length(){
		return pStart.distance(pEnd);
	}
	/**
	 * Method that return shape with all parameters
	 * @return shape with all parameters
	 */
	public String toString(){
		return "Line "+pStart.getX()+" "+pStart.getY()+" "+pEnd.getX()+" "+pEnd.getY()+" "+getcColor().getRGB();
	}

	/**
	 * Method that checks equality
	 * @param obj Object
	 * @return true if one object is equal to another
	 */
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line helperLine=(Line)obj;
			if(this.pStart.equals(helperLine.pStart)&&this.pEnd.equals(helperLine.pEnd))
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
		Point helperLine = new Point(x, y);
		if(helperLine.distance(pStart)+helperLine.distance(pEnd)-length()<=0.5)
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
		pStart.selected(g);
		pEnd.selected(g);
	}
	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		//g.setColor(pronadjiBoju(getBoja()));
		g.setColor(getcColor());
		g.drawLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		if(isSelected())
			selected(g);
	}

	/**
	 * gets start point
	 * @return start point
	 */
	public Point getpStart(){
		return pStart;
	}
	/**
	 * gets end point
	 * @return end point
	 */
	public Point getpEnd(){
		return pEnd;
	}

	/**
	 * Sets start point
	 * @param point point
	 */
	public void setpStart(Point point){
		pStart = point;
	}
	/**
	 * Sets end point
	 * @param point point
	 */
	public void setpEnd(Point point){
		pEnd = point;
	}

}
