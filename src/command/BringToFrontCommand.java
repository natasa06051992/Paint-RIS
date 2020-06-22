package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class BringToFrontCommand implements ICommand {
    Model model;
    Shape shape;
    int index;
    public BringToFrontCommand(Model model, Shape shape){
        this.model=model;
        this.shape=shape;
        index=model.getShapes().indexOf(shape);
    }
    @Override
    public void execute() {

        for(int i = index; i < model.getShapes().size()-1; i++){
            Collections.swap(model.getShapes(), i, i+1);
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public String getNameOfClass() {
        return null;
    }
}
