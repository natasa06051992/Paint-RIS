package command;

import geometry.Rectangle;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyRectangleCommand implements ICommand{
    private Model model;
    Rectangle newRectangle;
    Rectangle oldRectangle;
    Rectangle original;

    public ModifyRectangleCommand(Model model, Rectangle rectangle, Rectangle newRectangle) {
        this.model=model;
        oldRectangle = rectangle;
        this.newRectangle=newRectangle;
    }

    @Override
    public void execute() {
        original=oldRectangle;
        oldRectangle.setHeight(newRectangle.getHeight());
        oldRectangle.setSideLength(newRectangle.getSideLength());
        oldRectangle.setUpLeft(newRectangle.getUpLeft());
        oldRectangle.setCInside(newRectangle.getCInside());
        oldRectangle.setCEdge(newRectangle.getCEdge());
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Rectangle";
    }
}
