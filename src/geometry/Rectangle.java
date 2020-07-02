package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The Rectangle class that extends Square
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Rectangle extends Square{

	private int height;
	private static final long serialversionUID = 1293484439838L;
	/**
	 * Default constructor
	 */
	public Rectangle(){
	}

	/**
	 * Constructor that creates Rectangle object
	 * @param point point
	 * @param width width
	 * @param height height
	 */
	public Rectangle(Point point, int width, int height){
		super(point, width);
		this.height=height;	
	}

	/**
	 * Constructor that creates Rectangle object
	 * @param point point
	 * @param width width
	 * @param height height
	 * @param colorEdge color of edge
	 * @param colorInside inside color
	 */
	public Rectangle(Point point, int width, int height, Color colorEdge, Color colorInside){
		this(point, width, height);
		setCEdge(colorEdge);
		setCInside(colorInside);
	}

	/**
	 * Measures area
	 * @return area
	 */
	public int area(){
		return sideLength * height;
	}

	/**
	 * Measures girth
	 * @return girth of rectangle
	 */
	public int girth(){
		return 2 * (sideLength + height);
	}

	/**
	 * Measures diagonal
	 * @return diagonal
	 */
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX()+sideLength, upLeft.getY()+height));
	}
	/**
	 * Method that return shape with all parameters
	 * @return shape with all parameters
	 */
	public String toString(){
		return "Rectangle "+upLeft.getX()+" "+upLeft.getY()+" "+sideLength+" "+height+" "+getCEdge().getRGB()+" "+getCInside().getRGB();
	}
	/**
	 * Checks if one object is equal to another
	 * @param obj object
	 * @return true if equals, otherwise false
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) obj;
			if (this.upLeft.equals(rectangle.upLeft) && this.sideLength == rectangle.sideLength && this.height==rectangle.height)
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
	public boolean contains(int x, int y) {
		if(this.getUpLeft().getX()<=x 
				&& x<=(this.getUpLeft().getX() + sideLength)
				&& this.getUpLeft().getY()<=y 
				&& y<=(this.getUpLeft().getY() + height))
			return true;
		else 
			return false;
	}
	/**
	 * Set shape to selected
	 * @param g Graphics
	 */
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX()+sideLength, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+height)).selected(g);
		new Line(new Point(getUpLeft().getX()+sideLength, getUpLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+height), diagonal().getpEnd()).selected(g);
	}
	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(getCEdge());
		g.drawRect(upLeft.getX(), upLeft.getY(), sideLength, height);
		if(isSelected())
			selected(g);
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, height-1);
	}

	/**
	 * Fills rectangle
	 * @param g
	 */
	public void fill(Graphics g){
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, height-1);

	}

	/**
	 * Gets height
	 * @return height
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * Sets hight
	 * @param height hight
	 */
	public void setHeight(int height){
		this.height = height;
	}
}
