package command;

import geometry.Shape;
import model.Model;

import java.util.Collections;

/* *
 * * The StepToFrontCommand class that implements ICommand and sends one step to front selected shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class StepToFrontCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;
    /**
     * Constructor that creates object of class StepToFrontCommand
     * @param model Model that contains shapes
     * @param index Index of selected shape
     */
    public StepToFrontCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }
    /**
     * Executes command
     */
    @Override
    public void execute() {
        if(index<model.getShapes().size()){
            Collections.swap(model.getShapes(), index, index+1);
        }
    }
    /**
     * Undoes command
     */
    @Override
    public void undo() {
        Collections.swap(model.getShapes(), index+1, index);

    }
    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command StepToFront shape at intex "+ tempIndex;
    }
}
