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
    public HexagonAdapter(){
        hexagon=new Hexagon(1,1,1);
    }

    public HexagonAdapter(Point point, int radius, Color colorEdge, Color colorInside) {
        this.hexagon=new Hexagon(point.getX(), point.getY(), radius);
        hexagon.setR(radius);
        hexagon.setX(point.getX());
        hexagon.setY(point.getY());
        this.center=point;
        setCEdge(colorEdge);
        setCInside(colorInside);
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
    public void setAreaColor(Color color){
        hexagon.setAreaColor(color);
    }

    public void setHexagon(Hexagon hexagon){
        this.hexagon=hexagon;
    }
    public Hexagon getHexagon(){
        return hexagon;
    }
    public void setBorderColor(Color color){
        hexagon.setBorderColor(color);
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
    public double getHeight(){
        return 0.5*hexagon.getR()*Math.sqrt(3);
    }
    public Point getLeftPoint(){
        return new Point(hexagon.getX()-hexagon.getR(), hexagon.getY());
    }
    public Point getLeftBottomPoint(){
        return new Point(hexagon.getX()-hexagon.getR()/2, hexagon.getY()-(int)getHeight());
    }
    public Point getRightBottomPoint(){
        return new Point(hexagon.getX()+hexagon.getR()/2, hexagon.getY()-(int)getHeight());
    }
    public Point getRightPoint(){
        return new Point(hexagon.getX()+hexagon.getR(), hexagon.getY());
    }
    public Point getRightUpPoint(){
        return new Point(hexagon.getX()+hexagon.getR()/2, hexagon.getY()-(int)getHeight() + 2*(int)getHeight());
    }
    public Point getLeftUpPoint(){
        return new Point(hexagon.getX()-hexagon.getR()/2, hexagon.getY()-(int)getHeight() +2*(int)getHeight());
    }
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
    public String toString(){
        return "Hexagon :"+center.getX()+", "+center.getY()+", "+getR()+", "+getCEdge().getRGB()+", "+getCInside().getRGB();
    }
    public double area(){
        return 1.5 * hexagon.getR() * hexagon.getR() * Math.sqrt(3);
    }
}
