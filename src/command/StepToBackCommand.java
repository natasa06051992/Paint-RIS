package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class StepToBackCommand implements ICommand {
    private final int index;
    Model model;
    Shape shape;
    public StepToBackCommand(Model model, Shape shape){
        this.model=model;
        this.shape=shape;
        index = model.getShapes().indexOf(shape);
    }
    @Override
    public void execute() {
        if(index>0){
            Collections.swap(model.getShapes(), index, index-1);
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
