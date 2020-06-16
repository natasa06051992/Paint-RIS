package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Shiftable{
	private int x;
	private int y;


	public Point(){

	}
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	public Point(int x, int y, String color){
		this(x,y);
		setColor(color);
		
	}
	
	public Point(int x, int y, Color color){
		this(x,y);
		setcColor(color);
		
	}
	
	public double distance(Point point){
		int dx = x - point.x;
		int dy = this.y - point.y;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	public void moveTo(int newX, int newY){
		x = newX;
		y = newY;
	}
	public void moveFor(int poX, int poY){
		x = x + poX;
		y = y + poY;
	}


	// (x,y)
	public String toString(){
		return "Point :"+x+", "+y+", "+getcColor().getRGB();
	}

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
	public boolean contains(int x, int y){
		Point point = new Point(x, y);
		if(point.distance(this)<=2)
			return true;
		else
			return false;	
	}
	public void selected(Graphics g){
		g.setColor(findColor("blue"));
		g.drawRect(x-3, y-3, 6, 6);
	}
	public void draw(Graphics g) {
		g.setColor(getcColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected())
			selected(g);
	}

	public int compareTo(Object o) {
		if(o instanceof Point){
			Point zero = new Point(0, 0);
			Point second = (Point) o;
			return (int) (this.distance(zero) - second.distance(zero));

		}
		return 0;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
}
