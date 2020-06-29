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
    Rectangle from;

    public ModifyRectangleCommand(Model model, Rectangle rectangle, Rectangle newRectangle) {
        this.model=model;
        oldRectangle = rectangle;
        this.newRectangle=newRectangle;
        original = new Rectangle();
    }

    @Override
    public void execute() {
        original.setHeight(oldRectangle.getHeight());
        original.setSideLength(oldRectangle.getSideLength());
        original.setUpLeft(oldRectangle.getUpLeft());
        original.setCInside(oldRectangle.getCInside());
        original.setCEdge(oldRectangle.getCEdge());

        oldRectangle.setHeight(newRectangle.getHeight());
        oldRectangle.setSideLength(newRectangle.getSideLength());
        oldRectangle.setUpLeft(newRectangle.getUpLeft());
        oldRectangle.setCInside(newRectangle.getCInside());
        oldRectangle.setCEdge(newRectangle.getCEdge());
        from= original;
    }

    @Override
    public void undo() {
        oldRectangle.setHeight(original.getHeight());
        oldRectangle.setSideLength(original.getSideLength());
        oldRectangle.setUpLeft(original.getUpLeft());
        oldRectangle.setCInside(original.getCInside());
        oldRectangle.setCEdge(original.getCEdge());
        from = newRectangle;
    }

    @Override
    public String getNameOfCommand() {
        return "Command Modify "+from.toString()+" -> "+oldRectangle.toString();
    }
}
