package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square{

	private int height;

	public Rectangle(){

	}
	public Rectangle(Point point, int width, int height){
		super(point, width);
		this.height=height;	
	}
	public Rectangle(Point point, int width, int height, String colorEdge, String colorInside){
		this(point, width, height);
		setColorEdge(colorEdge);
		setColorInside(colorInside);
	}
	public Rectangle(Point point, int width, int height, Color colorEdge, Color colorInside){
		this(point, width, height);
		setCEdge(colorEdge);
		setCInside(colorInside);
	}
	public int area(){
		return sideLength * height;
	}
	public int girth(){
		return 2 * (sideLength + height);
	}
	public Line diagonal(){
		return new Line(upLeft, new Point(upLeft.getX()+sideLength, upLeft.getY()+height));
	}
	// Point gore levo=(xGoreLevo,yGoreLevo)
	// , sirina=sirina, height=height
	public String toString(){
		return "Rectangle :"+upLeft.getX()+", "+upLeft.getY()+", "+sideLength+", "+height+", "+getCEdge().getRGB()+", "+getCInside().getRGB();
	}
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
	public boolean contains(int x, int y) {
		if(this.getUpLeft().getX()<=x 
				&& x<=(this.getUpLeft().getX() + sideLength)
				&& this.getUpLeft().getY()<=y 
				&& y<=(this.getUpLeft().getY() + height))
			return true;
		else 
			return false;

	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX()+sideLength, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY()+height)).selected(g);
		new Line(new Point(getUpLeft().getX()+sideLength, getUpLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY()+height), diagonal().getpEnd()).selected(g);
	}
	public void draw(Graphics g) {
		//g.setColor(pronadjiBoju(getBoja()));
		g.setColor(getCEdge());
		g.drawRect(upLeft.getX(), upLeft.getY(), sideLength, height);
		if(isSelected())
			selected(g);
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, height-1);
	}
	public void fill(Graphics g){
		//g.setColor(pronadjiBoju(getBojaUnutrasnjosti()));
		g.setColor(getCInside());
		g.fillRect(upLeft.getX()+1, upLeft.getY()+1, sideLength-1, height-1);

	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
}
