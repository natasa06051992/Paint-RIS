package command;

import geometry.Shape;
import model.Model;

/* *
 * * The AddShapeCommand class that implements ICommand and adds shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class AddShapeCommand implements ICommand{
    private Model model;
    private Shape shape;

    /**
     * Constructor that creates object AddShapeCommand
     * @param model Model where shape is added
     * @param shape Shape is added to model
     */
    public AddShapeCommand(Model model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    /**
     * This method adds shape to model
     */
    @Override
    public void execute() {
        model.addShape(shape);
    }

    /**
     * Removes shape from model
     */
    @Override
    public void undo() {
        model.removeShape(shape);
    }

    /**
     * This method returns name of command with added shape
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command Add "+shape.toString();
    }
}
