package command;

import geometry.Point;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyPointCommand implements ICommand{
    private Model model;
    private Point newPoint;
    private Point oldPoint;
    private Point original;
    private Point fromPoint;

    public ModifyPointCommand(Model model, geometry.Point oldPoint, geometry.Point newPoint) {
        this.model = model;
        this.newPoint=newPoint;
        this.oldPoint=oldPoint;
        original= new Point();
    }

    @Override
    public void execute() {
        original.setX(oldPoint.getX());
        original.setY(oldPoint.getY());
        original.setcColor(oldPoint.getcColor());

        oldPoint.setX(newPoint.getX());
        oldPoint.setY(newPoint.getY());
        oldPoint.setcColor(newPoint.getcColor());
        fromPoint= original;
    }

    @Override
    public void undo() {
        oldPoint.setX(original.getX());
        oldPoint.setY(original.getY());
        oldPoint.setcColor(original.getcColor());
        fromPoint = newPoint;
    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+fromPoint.toString() +" -> "+newPoint.toString();
    }
}
