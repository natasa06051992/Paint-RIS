package geometry;
/* *
 * * The Shiftable interface
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public interface Shiftable {
	/**
	 * Moves shape to x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public abstract void moveTo(int x, int y);
	/**
	 * Moves shape for x and y
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	void moveFor(int x, int y);
}
