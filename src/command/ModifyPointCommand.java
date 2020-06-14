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
    private geometry.Point newPoint;
    private geometry.Point oldPoint;
    private Point original;
    public ModifyPointCommand(Model model, geometry.Point oldPoint, geometry.Point newPoint) {
        this.model = model;
        this.newPoint=newPoint;
        this.oldPoint=oldPoint;
    }

    @Override
    public void execute() {
        original=oldPoint;
        oldPoint.moveTo(newPoint.getX(), newPoint.getY());
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Point";
    }
}
