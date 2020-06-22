package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {

	private String colorString = "black";
	private  Color color = Color.lightGray;
	private boolean selected;
	private static final long serialversionUID = 129343489838L;
	public Shape(){
		
	}
	public Shape(String boja){
		this.colorString = boja;
	}
	public Shape(Color color){
		this.color = color;
	}
	
	public abstract void draw(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public static Color findColor(String color){
		if(color.equalsIgnoreCase("blue"))
			return Color.BLUE;
		else if(color.equalsIgnoreCase("white"))
			return Color.WHITE;
		else if(color.equalsIgnoreCase("green"))
			return Color.GREEN;
		else if(color.equalsIgnoreCase("red"))
			return Color.RED;
		else if(color.equalsIgnoreCase("yellow"))
			return Color.YELLOW;
		else if(color.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;
	}
	
	public String getColor() {
		return colorString;
	}

	public void setColor(String color) {
		this.colorString = color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getcColor() {
		return color;
	}
	public void setcColor(Color color) {
		this.color = color;
	}
}
