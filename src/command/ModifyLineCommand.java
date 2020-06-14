package command;

import geometry.Line;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyLineCommand implements ICommand{
    private Model model;
    private Line original;
    private Line oldLine;
    private Line newLine;
    public ModifyLineCommand(Model model, Line oldLine, Line newLine) {
        this.model=model;
        this.oldLine=oldLine;
        this.newLine=newLine;
    }

    @Override
    public void execute() {
        original=oldLine;
        oldLine.moveTo(newLine);
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Line";
    }
}
