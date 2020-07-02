package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The Circle class that extends SurfaceShape implements Shiftable
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Circle extends SurfaceShape implements Shiftable{
	private static final long serialversionUID = 1293489838L;
	private Point center;
	private int r;

	/**
	 * Defualt constructor
	 */
	public Circle(){ }

	/**
	 * Constructor for creating Circle object
	 * @param center point of center
	 * @param r Radius
	 */
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}

	/**
	 * Constructor for creating Circle object
	 * @param center point of center
	 * @param r Radius
	 * @param colorEdge color of edge
	 * @param colorInside inside color
	 */
	public Circle(Point center, int r,String colorEdge, String colorInside){
		this(center, r);
		setColorEdge(colorEdge);
		setColorInside(colorInside);
	}

	/**
	 * Constructor for creating Circle object
	 * @param center point of center
	 * @param r Radius
	 * @param cEdge color of edge
	 * @param cInside inside color
	 */
	public Circle(Point center, int r, Color cEdge, Color cInside) {
		this(center, r);
		setCEdge(cEdge);
		setCInside(cInside);
	}

	/**
	 * Moves circle to x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void moveTo(int x, int y){
		center.moveTo(x, y);
	}
	/**
	 * Moves circle for x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void moveFor(int x, int y){
		center.moveFor(x, y);
	}

	/**
	 * Method that return circle with all parameters
	 * @return circle with all parameters
	 */
	public String toString(){
		return "Circle "+center.getX()+" "+center.getY()+" "+r+" "+getCEdge().getRGB()+" "+getCInside().getRGB();
	}

	/**
	 * Checks equality of two object
	 * @param obj Object
	 * @return true if is equal, otherwise false
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle helperCircle = (Circle) obj;
			if (this.center.equals(helperCircle.center) && this.r == helperCircle.r)
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * Fills shape with color
	 * @param g Graphics
	 */
	public void fill(Graphics g){
		g.setColor(getCInside());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
	}

	/**
	 * Set shape to selected
	 * @param g Graphics
	 */
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
		center.selected(g);
	}

	/**
	 * Checks if one shape contains another
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if contains, otherwise false
	 */
	public boolean contains(int x, int y) {
		if(new Point(x,y).distance(center)<=r)
			return true;
		else
			return false;
	}

	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(getCEdge());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, 2*r);
		g.setColor(getCInside());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
		if(isSelected())
			selected(g);
	}

	/**
	 * Returns center
	 * @return center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Returns radius
	 * @return r
	 */
	public int getR() {
		return r;
	}

	/**
	 * Sets center
	 * @param center
	 */
	public void setCenter(Point center) {
		this.center = center;
	}

	/**
	 * Sets radius
	 * @param r radius
	 */
	public void setR(int r) {
		this.r = r;
	}
}
