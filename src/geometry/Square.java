package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The Square class that extends SurfaceShape implements Shiftable
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Square extends SurfaceShape implements Shiftable{
	protected Point upLeft;
	protected int sideLength;
	private static final long serialversionUID = 129348983778L;
	/**
	 * Default constructor
	 */
	public Square() {

	}

	/**
	 * Constructor that creates Square object
	 * @param upLeft point
	 * @param sideLength length
	 */
	public Square(Point upLeft, int sideLength) {
		this.upLeft = upLeft;
		this.sideLength = sideLength;
	}

	/**
	 *  Constructor that creates Square object
	 * @param upLeft point
	 * @param sideLength length
	 * @param colorEdge color of edge
	 * @param colorInside inside color
	 */
	public Square(Point upLeft, int sideLength,Color colorEdge, Color colorInside) {
		this(upLeft, sideLength);
		setCEdge(colorEdge);
		setCInside(colorInside);
	}
	/**
	 * Moves shape to x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void moveTo(int x, int y) {
		upLeft.moveTo(x, y);
	}
	/**
	 * Moves shape for x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public void moveFor(int x, int y) {
		upLeft.moveFor(x, y);
	}
	/**
	 * Method that return shape with all parameters
	 * @return shape with all parameters
	 */
	public String toString() {
		return "Square "+upLeft.getX()+" "+upLeft.getY() +" "+ sideLength+ " "+getCEdge().getRGB()+" "+getCInside().getRGB();
	}
	/**
	 * Checks if one object is equal to another
	 * @param o1 object
	 * @return true if equals, otherwise false
	 */
	public boolean equals(Object o1) {
		if (o1 instanceof Square) {
			Square square = (Square) o1;
			if (this.upLeft.equals(square.upLeft) && (this.sideLength == square.sideLength))
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * diagonal
	 * @return diagonal
	 */
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX()+sideLength, upLeft.getY()+sideLength));
	}
	/**
	 * Checks if one shape contains another
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return true if contains, otherwise false
	 */
	public boolean contains(int x, int y) {
		if(this.getUpLeft().getX()<=x 
				&& x<=(this.getUpLeft().getX()+sideLength)
				&& this.getUpLeft().getY()<=y
				&& y<=(this.getUpLeft().getY()+sideLength))
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
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+sideLength)).selected(g);
		new Line(new Point(getUpLeft().getX()+sideLength, getUpLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+sideLength), diagonal().getpEnd()).selected(g);

	}
	/**
	 * Fills rectangle
	 * @param g
	 */
	public void fill(Graphics g) {
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, sideLength-1);
	}
	/**
	 * Draws shape
	 * @param g Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(getCEdge());
		g.drawRect(upLeft.getX(), upLeft.getY(), sideLength, sideLength);
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, sideLength-1);
		if(isSelected())
			selected(g);
	}

	/**
	 * up left point
	 * @return point
	 */
	public Point getUpLeft() {
		return upLeft;
	}

	/**
	 * side lenght
	 * @return side lenght
	 */
	public int getSideLength() {
		return sideLength;
	}

	/**
	 * up left point
	 * @param upLeft point
	 */
	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	/**
	 * Sets sideLength
	 * @param sideLength sideLength
	 */
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
}

