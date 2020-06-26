package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class StepToFrontCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    public StepToFrontCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }
    @Override
    public void execute() {
        if(index<model.getShapes().size()){
            Collections.swap(model.getShapes(), index, index+1);
        }
    }

    @Override
    public void undo() {
        Collections.swap(model.getShapes(), index+1, index);

    }

    @Override
    public String getNameOfClass() {
        return "Command StepToFront shape at intex "+ tempIndex;
    }
}
