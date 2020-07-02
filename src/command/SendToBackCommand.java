package command;

import model.Model;

import java.util.Collections;

/* *
 * * The SendToBackCommand class that implements ICommand and sends to back selected shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class SendToBackCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    /**
     * Constructor that creates object of class SendToBackCommand
     * @param model Model that contains shapes
     * @param index Index of selected shape
     */
    public SendToBackCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }
    /**
     * Executes command
     */
    @Override
    public void execute() {
        if(index>=0){
            for(int i = index; i>0; i--){
                Collections.swap(model.getShapes(), i, i-1);
            }
        }
    }
    /**
     * Undoes command
     */
    @Override
    public void undo() {
        for(int i=0; i < index; i++){
            Collections.swap(model.getShapes(), i, i+1);
        }
    }
    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command SendToBack shape at index "+ tempIndex;
    }
}
