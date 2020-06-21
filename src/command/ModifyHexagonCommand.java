package command;

import geometry.HexagonAdapter;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyHexagonCommand implements ICommand{
    private Model model;
    private HexagonAdapter original;
    private HexagonAdapter newHexagon;
    private HexagonAdapter oldHexagon;
    private HexagonAdapter fromHexagon;

    public ModifyHexagonCommand(Model model, HexagonAdapter hexagon, HexagonAdapter newHexagon) {
        this.model =model;
        this.oldHexagon=hexagon;
        this.newHexagon=newHexagon;
        original = new HexagonAdapter();
    }

    @Override
    public void execute() {

        original.setR(oldHexagon.getR());
        original.setCenter(oldHexagon.getCenter());
        original.setCEdge(oldHexagon.getCEdge());
        original.setCInside(oldHexagon.getCInside());
        fromHexagon = original;
        oldHexagon.setR(newHexagon.getR());
        oldHexagon.setCenter(newHexagon.getCenter());
        oldHexagon.setCEdge(newHexagon.getCEdge());
        oldHexagon.setCInside(newHexagon.getCInside());
    }

    @Override
    public void undo() {
        fromHexagon=newHexagon;
        oldHexagon.setR(original.getR());
        oldHexagon.setCenter(original.getCenter());
        oldHexagon.setCEdge(original.getCEdge());
        oldHexagon.setCInside(original.getCInside());
    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+ newHexagon.toString()+" -> "+oldHexagon.toString();
    }
}
