package command;

import geometry.HexagonAdapter;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/* *
 * * The ModifyHexagonCommand class that implements ICommand and modify hexagon
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class ModifyHexagonCommand implements ICommand{
    private Model model;
    private HexagonAdapter original;
    private HexagonAdapter newHexagon;
    private HexagonAdapter oldHexagon;
    private HexagonAdapter fromHexagon;

    /**
     * Constructor that creates object of class ModifyHexagonCommand
     * @param model Model that contains shapes
     * @param hexagon Old hexagon
     * @param newHexagon New hexagon
     */
    public ModifyHexagonCommand(Model model, HexagonAdapter hexagon, HexagonAdapter newHexagon) {
        this.model =model;
        this.oldHexagon=hexagon;
        this.newHexagon=newHexagon;
        original = new HexagonAdapter();
    }

    /**
     * Executes command
     */
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

    /**
     * Undoes command
     */
    @Override
    public void undo() {
        fromHexagon=newHexagon;
        oldHexagon.setR(original.getR());
        oldHexagon.setCenter(original.getCenter());
        oldHexagon.setCEdge(original.getCEdge());
        oldHexagon.setCInside(original.getCInside());
    }

    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command Modify "+ newHexagon.toString()+" -> "+oldHexagon.toString();
    }
}
