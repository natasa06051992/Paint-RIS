package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape implements Shiftable{
	protected Point upLeft;
	protected int sideLength;
	private static final long serialversionUID = 129348983778L;
	public Square() {

	}

	public Square(Point upLeft, int sideLength) {
		this.upLeft = upLeft;
		this.sideLength = sideLength;
	}
	public Square(Point upLeft, int sideLength,String colorEdge, String colorInside) {
		this(upLeft, sideLength);
		setColorEdge(colorEdge);
		setColorInside(colorInside);
	}
	
	public Square(Point upLeft, int sideLength,Color colorEdge, Color colorInside) {
		this(upLeft, sideLength);
		setCEdge(colorEdge);
		setCInside(colorInside);
	}

	public void moveTo(int x, int y) {
		upLeft.moveTo(x, y);
	}

	public void moveFor(int x, int y) {
		upLeft.moveFor(x, y);
	}

	public int area() {
		return sideLength * sideLength;
	}

	public int girth() {
		return 4 * sideLength;
	}

	// Point gore levo=(xGoreLevo,yGoreLevo)
	// , duzina stranice=sideLength
	public String toString() {
		return "Square "+upLeft.getX()+" "+upLeft.getY() +" "+ sideLength+ " "+getCEdge().getRGB()+" "+getCInside().getRGB();
	}

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
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX()+sideLength, upLeft.getY()+sideLength));
	}
	public boolean contains(int x, int y) {
		if(this.getUpLeft().getX()<=x 
				&& x<=(this.getUpLeft().getX()+sideLength)
				&& this.getUpLeft().getY()<=y
				&& y<=(this.getUpLeft().getY()+sideLength))
			return true;
		else
			return false;
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX()+sideLength, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+sideLength)).selected(g);
		new Line(new Point(getUpLeft().getX()+sideLength, getUpLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+sideLength), diagonal().getpEnd()).selected(g);

	}

	public void fill(Graphics g) {
		//g.setColor(pronadjiBoju(getBojaUnutrasnjosti()));
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, sideLength-1);
	}
	public void draw(Graphics g) {
		//g.setColor(pronadjiBoju(getBoja()));
		g.setColor(getCEdge());
		g.drawRect(upLeft.getX(), upLeft.getY(), sideLength, sideLength);
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, sideLength-1);
		if(isSelected())
			selected(g);
	}
	public int compareTo(Object o) {
		if(o instanceof Square){
			Square square = (Square) o;
			return (int) (this.area()-square.area());
		}
		else 
			return 0;
	}
	public Point getUpLeft() {
		return upLeft;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
}

