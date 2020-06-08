package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	private String colorInside;// = "bela";
	private String colorEdge; //= "crna";
	
	private Color cEdge;
	private Color cInside;
	
	public abstract void fill(Graphics g);

	public String getColorInside() {
		return colorInside;
	}

	public String getColorEdge() {
		return colorEdge;
	}

	public void setColorInside(String colorInside) {
		this.colorInside = colorInside;
	}

	public void setColorEdge(String colorEdge) {
		this.colorEdge = colorEdge;
	}

	public Color getCEdge() {
		return cEdge;
	}

	public Color getCInside() {
		return cInside;
	}

	public void setCEdge(Color cIvice) {
		this.cEdge = cIvice;
	}

	public void setCInside(Color cUnutra) {
		this.cInside = cUnutra;
	}
	
}
