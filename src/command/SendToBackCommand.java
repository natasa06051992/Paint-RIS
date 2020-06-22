package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class SendToBackCommand implements ICommand {
    Model model;
    Shape shape;
    int index;
    public SendToBackCommand(Model model, Shape shape){
        this.model=model;
        this.shape=shape;
        index = model.getShapes().indexOf(shape);
    }
    @Override
    public void execute() {
        if(index>=0){
            for(int i = index; i>0; i--){
                Collections.swap(model.getShapes(), i, i-1);
            }
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
