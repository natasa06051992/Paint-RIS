package command;

import geometry.Circle;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyCircleCommand implements ICommand{
    private Model model;
    private Circle oldCircle;
    private Circle newCircle;
    private Circle original;
    private Circle fromShape;

    public ModifyCircleCommand(Model model, Circle circle, Circle shape) {
        this.model = model;
        this.oldCircle=circle;
        this.newCircle=shape;
        original=new Circle();
    }

    @Override
    public void execute() {
        original.setR(oldCircle.getR());
        original.setCenter(oldCircle.getCenter());
        original.setCEdge(oldCircle.getCEdge());
        original.setCInside(oldCircle.getCInside());
        oldCircle.setR(newCircle.getR());
        oldCircle.setCenter(newCircle.getCenter());
        oldCircle.setCEdge(newCircle.getCEdge());
        oldCircle.setCInside(newCircle.getCInside());
        fromShape= original;

    }

    @Override
    public void undo() {
        fromShape = newCircle;
        oldCircle.setR(original.getR());
        oldCircle.setCenter(original.getCenter());
        oldCircle.setCEdge(original.getCEdge());
        oldCircle.setCInside(original.getCInside());
    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+ fromShape.toString() + " -> "+oldCircle.toString();
    }
}
