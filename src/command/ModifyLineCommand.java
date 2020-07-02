package command;

import geometry.Line;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
/* *
 * * The ModifyLineCommand class that implements ICommand and modify line
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class ModifyLineCommand implements ICommand{
    private Model model;
    private Line original;
    private Line oldLine;
    private Line newLine;
    private Line fromLine;
    /**
     * Constructor that creates object of class ModifyLineCommand
     * @param model Model that contains shapes
     * @param oldLine Old line
     * @param newLine New line
     */
    public ModifyLineCommand(Model model, Line oldLine, Line newLine) {
        this.model=model;
        this.oldLine=oldLine;
        this.newLine=newLine;
        original = new Line();
    }
    /**
     * Executes command
     */
    @Override
    public void execute() {
        original.setpEnd(oldLine.getpEnd());
        original.setpStart(oldLine.getpStart());
        original.setcColor(oldLine.getcColor());
        oldLine.setpEnd(newLine.getpEnd());
        oldLine.setpStart(newLine.getpStart());
        oldLine.setcColor(newLine.getcColor());
        fromLine = original;
    }

    /**
     * Undoes command
     */
    @Override
    public void undo() {
        fromLine= newLine;
        oldLine.setpEnd(original.getpEnd());
        oldLine.setpStart(original.getpStart());
        oldLine.setcColor(original.getcColor());
    }

    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command Modify "+fromLine +" -> "+oldLine.toString();
    }
}
