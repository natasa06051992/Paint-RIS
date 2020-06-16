package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape implements Shiftable{
	private Point center;
	private int r;

	public Circle(){

	}
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	public Circle(Point center, int r,String colorEdge, String colorInside){
		this(center, r);
		setColorEdge(colorEdge);
		setColorInside(colorInside);
	}

	public Circle(Point center, int r, Color cEdge, Color cInside) {
		this(center, r);
		setCEdge(cEdge);
		setCInside(cInside);
	}
	public void moveTo(int x, int y){
		center.moveTo(x, y);
	}
	public void moveFor(int x, int y){
		center.moveFor(x, y);
	}
	public double area(){
		return r * r * Math.PI;
	}
	public double girth(){
		return 2 * r * Math.PI;
	}

	public String toString(){
		return "Circle :"+center.getX()+", "+center.getY()+", "+r+", "+getCEdge().getRGB()+", "+getCInside().getRGB();
	}

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
	public void fill(Graphics g){
		g.setColor(getCInside());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
		center.selected(g);
	}

	public boolean contains(int x, int y) {
		if(new Point(x,y).distance(center)<=r)
			return true;
		else
			return false;
	}
	public void draw(Graphics g) {
		//g.setColor(pronadjiBoju(getBoja()));
		g.setColor(getCEdge());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, 2*r);
		g.setColor(getCInside());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
		if(isSelected())
			selected(g);
	}
	public int compareTo(Object o) {
		if(o instanceof Circle){
			Circle helperCircle = (Circle) o;
			return (int) (this.girth()-helperCircle.girth());
		}
		else 
			return 0;
	}
	public Point getCenter() {
		return center;
	}
	public int getR() {
		return r;
	}
	public void setCenter(Point centar) {
		this.center = centar;
	}
	public void setR(int r) {
		this.r = r;
	}

}
