package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

public class SendToBackCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    public SendToBackCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
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
        for(int i=0; i < index; i++){
            Collections.swap(model.getShapes(), i, i+1);
        }
    }

    @Override
    public String getNameOfCommand() {
        return "Command SendToBack shape at index "+ tempIndex;
    }
}
