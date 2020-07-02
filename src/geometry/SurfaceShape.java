package geometry;

import java.awt.Color;
import java.awt.Graphics;
/* *
 * * The SurfaceShape class that extends Shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public abstract class SurfaceShape extends Shape{
	private String colorInside;// = "bela";
	private String colorEdge; //= "crna";
	private static final long serialversionUID = 129543489838L;
	private Color cEdge = Color.LIGHT_GRAY;
	private Color cInside= Color.LIGHT_GRAY;

	/**
	 * Fills rectangle
	 * @param g
	 */
	public abstract void fill(Graphics g);

	/**
	 * Sets inside color
	 * @param colorInside color
	 */
	public void setColorInside(String colorInside) {
		this.colorInside = colorInside;
	}

	/**
	 * Sets edge color
	 * @param colorEdge color
	 */
	public void setColorEdge(String colorEdge) {
		this.colorEdge = colorEdge;
	}

	/**
	 * Gets edge color
	 * @return color
	 */
	public Color getCEdge() {
		return cEdge;
	}

	/**
	 * Gets color inside
	 * @return color
	 */
	public Color getCInside() {
		return cInside;
	}

	/**
	 * Sets edge color
	 * @param cEdge color
	 */
	public void setCEdge(Color cEdge) {
		this.cEdge = cEdge;
	}

	/**
	 * Sets inside color
	 * @param cInside color
	 */
	public void setCInside(Color cInside) {
		this.cInside = cInside;
	}
	
}
