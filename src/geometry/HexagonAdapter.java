package geometry;

import heksagon.Hexagon;

import java.awt.*;

public class HexagonAdapter extends SurfaceShape implements Shiftable{
    private Point center;
    Hexagon hexagon;
    public HexagonAdapter(int x, int y, int radius){
        this.hexagon=new Hexagon(x, y, radius);
        hexagon.setR(radius);
        hexagon.setX(x);
        hexagon.setY(y);
        this.center=new Point(x, y);
    }
    public HexagonAdapter(Hexagon hexagon){
        this.hexagon=hexagon;
        this.center=new Point(hexagon.getX(), hexagon.getY());
    }
    @Override
    public void fill(Graphics g) {
        hexagon.setBorderColor(getCEdge());
        hexagon.setAreaColor(getCInside());
    }

    @Override
    public void draw(Graphics g)
    {
        hexagon.setBorderColor(getCEdge());
        hexagon.setAreaColor(getCInside());
        hexagon.paint(g);
        if(isSelected())
            selected(g);
    }
    public Point getCenter() {
        return center;
    }
    public int getR() {
        return hexagon.getR();
    }
    public void setCenter(Point centar) {
        this.center = centar;
    }
    public void setR(int r) {
        hexagon.setR(r);
    }
    @Override
    public void selected(Graphics g) {

    }

    @Override
    public boolean contains(int x, int y) {
        return hexagon.doesContain(x,y);
    }

    @Override
    public void moveTo(int x, int y) {
        hexagon.setX(x);
        hexagon.setY(y);
    }

    @Override
    public void moveFor(int x, int y) {
        hexagon.setX(hexagon.getX() + x);
        hexagon.setY(hexagon.getY() + y);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof HexagonAdapter){
            HexagonAdapter helperHexagon = (HexagonAdapter) o;
            return (int) (this.area()-helperHexagon.area());
        }
        else
            return 0;
    }

    public double area(){
        return 1.5 * hexagon.getR() * hexagon.getR() * Math.sqrt(3);
    }
}
