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

    public ModifyHexagonCommand(Model model, HexagonAdapter hexagon, HexagonAdapter newHexagon) {
        this.model =model;
        this.oldHexagon=hexagon;
        this.newHexagon=newHexagon;
    }

    @Override
    public void execute() {
        original=oldHexagon;
        oldHexagon.setR(newHexagon.getR());
        oldHexagon.setCenter(newHexagon.getCenter());
        oldHexagon.setBorderColor(newHexagon.getCEdge());
        oldHexagon.setAreaColor(newHexagon.getCInside());
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+newHexagon.toString();
    }
}
