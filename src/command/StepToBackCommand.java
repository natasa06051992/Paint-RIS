package command;

import model.Model;

import java.util.Collections;

/* *
 * * The StepToBackCommand class that implements ICommand and sends one step to back selected shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class StepToBackCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    /**
     * Constructor that creates object of class StepToBackCommand
     * @param model Model that contains shapes
     * @param index Index of selected shape
     */
    public StepToBackCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }
    /**
     * Executes command
     */
    @Override
    public void execute() {
        if(index>0){
            Collections.swap(model.getShapes(), index, index-1);
        }
    }
    /**
     * Undoes command
     */
    @Override
    public void undo() {
        Collections.swap(model.getShapes(), index-1, index);
    }
    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command StepToBack shape at intex "+ tempIndex;
    }
}
