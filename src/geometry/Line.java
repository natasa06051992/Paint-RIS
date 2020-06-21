package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	private Point pStart;
	private Point pEnd;

	public Line(){

	}
	public Line(Point pStart, Point pEnd){
		this.pStart = pStart;
		this.pEnd = pEnd;
	}
	public Line(Point pStart, Point pEnd,String color){
		this(pStart,pEnd);
		setColor(color);
	}
	public Line(Point pStart, Point pEnd, Color color){
		this(pStart,pEnd);
		setcColor(color); 
	}
	
	public void moveFor(int poX, int poY){
		pStart.setX(pStart.getX()+poX);
		pStart.setY(pStart.getY()+poY);
		pEnd.setX(pEnd.getX()+poX);
		pEnd.setY(pEnd.getY()+poY);
	}
	public void moveTo(Line line){
		this.setpStart(line.getpStart());
		this.setpEnd(line.getpEnd());
	}
	public double length(){
		return pStart.distance(pEnd);
	}

	public String toString(){
		return "Line "+pStart.getX()+" "+pStart.getY()+" "+pEnd.getX()+" "+pEnd.getY()+" "+getcColor().getRGB();
	}

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
	public boolean contains(int x, int y){
		Point helperLine = new Point(x, y);
		if(helperLine.distance(pStart)+helperLine.distance(pEnd)-length()<=0.5)
			return true;
		else
			return false;
	}
	public void selected(Graphics g){
		g.setColor(findColor("blue"));
		pStart.selected(g);
		pEnd.selected(g);
	}
	public void draw(Graphics g) {
		//g.setColor(pronadjiBoju(getBoja()));
		g.setColor(getcColor());
		g.drawLine(pStart.getX(), pStart.getY(), pEnd.getX(), pEnd.getY());
		if(isSelected())
			selected(g);
	}
	public int compareTo(Object o) {
		if(o instanceof Line){
			Line helperLine = (Line) o;
			return (int)this.length()-(int)helperLine.length();
		}
		else 
			return 0;
	}

	public Point getpStart(){
		return pStart;
	}
	public Point getpEnd(){
		return pEnd;
	}
	public void setpStart(Point point){
		pStart = point;
	}
	public void setpEnd(Point point){
		pEnd = point;
	}

}
