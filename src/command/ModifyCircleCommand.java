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

    public ModifyCircleCommand(Model model, Circle circle, Circle newCircle) {
        this.model = model;
        this.oldCircle=circle;
        this.newCircle=newCircle;
    }

    @Override
    public void execute() {
        original=oldCircle;
        oldCircle.setR(newCircle.getR());
        oldCircle.setCenter(newCircle.getCenter());
        oldCircle.setCEdge(newCircle.getCEdge());
        oldCircle.setCInside(newCircle.getCInside());
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Circle";
    }
}
