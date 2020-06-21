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
    private Line fromLine;
    public ModifyLineCommand(Model model, Line oldLine, Line newLine) {
        this.model=model;
        this.oldLine=oldLine;
        this.newLine=newLine;
        original = new Line();
    }

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

    @Override
    public void undo() {
        fromLine= newLine;
        oldLine.setpEnd(original.getpEnd());
        oldLine.setpStart(original.getpStart());
        oldLine.setcColor(original.getcColor());
    }

    @Override
    public String getNameOfClass() {
        return "Command Modify "+fromLine +" -> "+oldLine.toString();
    }
}
