package command;

import model.Model;
import java.util.Collections;

/* *
 * * The BringToFrontCommand class that implements ICommand and brings to front shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class BringToFrontCommand implements ICommand {
    Model model;
    int index;
    int tempIndex;

    /**
     * Constructor that creates object BringToFrontCommand
     * @param model Model that contains shapes++
     * @param index of selected shape
     */
    public BringToFrontCommand(Model model, int index){
        this.model=model;
        this.index=index;
        tempIndex= index;
    }

    /**
     * Brings to front selected shape
     */
    @Override
    public void execute() {

        for(int i = index; i < model.getShapes().size()-1; i++){
            Collections.swap(model.getShapes(), i, i+1);
        }
    }

    /**
     * Reverts executed action
     */
    @Override
    public void undo() {
        for(int i=model.getShapes().size()-1; i>index; i--){
            if(i==0){
                continue;
            }
            Collections.swap(model.getShapes(), i, i-1);
        }
    }

    /**
     * This method returns name of command with index of selected shape
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command BringToFront shape at index "+ tempIndex;
    }
}
