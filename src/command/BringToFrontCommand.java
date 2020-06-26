package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class BringToFrontCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    public BringToFrontCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }
    @Override
    public void execute() {

        for(int i = index; i < model.getShapes().size()-1; i++){
            Collections.swap(model.getShapes(), i, i+1);
        }
    }

    @Override
    public void undo() {
        for(int i=model.getShapes().size()-1; i>index; i--){
            if(i==0){
                continue;
            }
            Collections.swap(model.getShapes(), i, i-1);
        }
    }

    @Override
    public String getNameOfClass() {
        return "Command BringToFront shape at index "+ tempIndex;
    }
}
