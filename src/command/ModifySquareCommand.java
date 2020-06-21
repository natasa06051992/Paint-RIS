package command;

import geometry.Shape;
import geometry.Square;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifySquareCommand implements ICommand{
    private Model model;
    private Square original;
    private Square oldSquare;
    private Square newSquare;
    private Square from;

    public ModifySquareCommand(Model model, Square oldSquare, Square newSquare) {
        this.model = model;
        this.oldSquare=oldSquare;
        this.newSquare=newSquare;
        original = new Square();
    }

    @Override
    public void execute() {
        original.setUpLeft(oldSquare.getUpLeft());
        original.setSideLength(oldSquare.getSideLength());
        original.setCEdge(oldSquare.getCEdge());
        original.setCInside(oldSquare.getCInside());

        oldSquare.setUpLeft(newSquare.getUpLeft());
        oldSquare.setSideLength(newSquare.getSideLength());
        oldSquare.setCEdge(newSquare.getCEdge());
        oldSquare.setCInside(newSquare.getCInside());
        from= original;
    }

    @Override
    public void undo() {
        oldSquare.setUpLeft(original.getUpLeft());
        oldSquare.setSideLength(original.getSideLength());
        oldSquare.setCEdge(original.getCEdge());
        oldSquare.setCInside(original.getCInside());
        from = newSquare;
    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+from.toString()+" -> "+newSquare.toString();
    }
}
