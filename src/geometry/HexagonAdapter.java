package geometry;

import heksagon.Hexagon;

import java.awt.*;

/* *
 * * The HexagonAdapter class that extends SurfaceShape implements Shiftable
 * * Adapter pattern for Hexagon class
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class HexagonAdapter extends SurfaceShape implements Shiftable{
    private Point center;
    Hexagon hexagon;
    private static final long serialversionUID = 12934898318L;

    /**
     * Constructor that creates HexagonAdapter
     * @param x x coordinate
     * @param y y coordinate
     * @param radius radius
     */
    public HexagonAdapter(int x, int y, int radius){
        this.hexagon=new Hexagon(x, y, radius);
        hexagon.setR(radius);
        hexagon.setX(x);
        hexagon.setY(y);
        this.center=new Point(x, y);
    }

    /**
     * Default constructor
     */
    public HexagonAdapter(){
        hexagon=new Hexagon(1,1,1);
    }

    /**
     *  Constructor that creates HexagonAdapter
     * @param point Center of hexagon
     * @param radius radius
     * @param colorEdge color of edge
     * @param colorInside inside color
     */
    public HexagonAdapter(Point point, int radius, Color colorEdge, Color colorInside) {
        this.hexagon=new Hexagon(point.getX(), point.getY(), radius);
        hexagon.setR(radius);
        hexagon.setX(point.getX());
        hexagon.setY(point.getY());
        this.center=point;
        setCEdge(colorEdge);
        setCInside(colorInside);
    }

    /**
     * Fills shape with color
     * @param g Graphics
     */
    @Override
    public void fill(Graphics g) {
        hexagon.setBorderColor(getCEdge());
        hexagon.setAreaColor(getCInside());
    }

    /**
     * Draws shape
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g)
    {
        hexagon.setBorderColor(getCEdge());
        hexagon.setAreaColor(getCInside());
        hexagon.paint(g);
        if(isSelected())
            selected(g);
    }

    /**
     * Returns center
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets radius
     * @return radius
     */
    public int getR() {
        return hexagon.getR();
    }

    /**
     * Sets center
     * @param center center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Sets radius
     * @param r radius
     */
    public void setR(int r) {
        hexagon.setR(r);
    }

    /**
     * Gets height
     * @return height
     */
    public double getHeight(){
        return 0.5*hexagon.getR()*Math.sqrt(3);
    }

    /**
     * Get left point
     * @return point
     */
    public Point getLeftPoint(){
        return new Point(hexagon.getX()-hexagon.getR(), hexagon.getY());
    }

    /**
     * Get left bottom point
     * @return point
     */
    public Point getLeftBottomPoint(){
        return new Point(hexagon.getX()-hexagon.getR()/2, hexagon.getY()-(int)getHeight());
    }
    /**
     * Get right bottom point
     * @return point
     */
    public Point getRightBottomPoint(){
        return new Point(hexagon.getX()+hexagon.getR()/2, hexagon.getY()-(int)getHeight());
    }
    /**
     * Get right point
     * @return point
     */
    public Point getRightPoint(){
        return new Point(hexagon.getX()+hexagon.getR(), hexagon.getY());
    }
    /**
     * Get right up point
     * @return point
     */
    public Point getRightUpPoint(){
        return new Point(hexagon.getX()+hexagon.getR()/2, hexagon.getY()-(int)getHeight() + 2*(int)getHeight());
    }
    /**
     * Get left up point
     * @return point
     */
    public Point getLeftUpPoint(){
        return new Point(hexagon.getX()-hexagon.getR()/2, hexagon.getY()-(int)getHeight() +2*(int)getHeight());
    }
    /**
     * Set shape to selected
     * @param g Graphics
     */
    @Override
    public void selected(Graphics g) {
        g.setColor(Color.BLUE);
        new Line(getLeftPoint(), getLeftUpPoint()).selected(g);
        new Line(getLeftPoint(), getLeftBottomPoint()).selected(g);
        new Line(getLeftUpPoint(), getRightUpPoint()).selected(g);
        new Line(getRightUpPoint(), getRightPoint()).selected(g);
        new Line(getRightPoint(), getRightBottomPoint()).selected(g);
        new Line(getRightBottomPoint(), getLeftBottomPoint()).selected(g);
    }
    /**
     * Checks if one shape contains another
     * @param x x coordinate
     * @param y y coordinate
     * @return true if contains, otherwise false
     */
    @Override
    public boolean contains(int x, int y) {
        return hexagon.doesContain(x,y);
    }
    /**
     * Moves shape to x and y
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void moveTo(int x, int y) {
        hexagon.setX(x);
        hexagon.setY(y);
    }

    /**
     * Moves shape for x and y
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void moveFor(int x, int y) {
        hexagon.setX(hexagon.getX() + x);
        hexagon.setY(hexagon.getY() + y);
    }
    /**
     * Method that return shape with all parameters
     * @return shape with all parameters
     */
    public String toString(){
        return "Hexagon "+center.getX()+" "+center.getY()+" "+getR()+" "+getCEdge().getRGB()+" "+getCInside().getRGB();
    }
}
