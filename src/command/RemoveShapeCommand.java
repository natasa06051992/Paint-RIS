package command;

import geometry.Shape;
import model.Model;

/* *
 * * The RemoveShapeCommand class that implements ICommand and remove shape
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class RemoveShapeCommand implements ICommand{
    private Model model;
    private Shape shape;

    /**
     * Constructor that creates object of class RemoveShapeCommand
     * @param model Model that contains shapes
     * @param shape Index of selected shape
     */
    public RemoveShapeCommand(Model model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    /**
     * Executes command
     */
    @Override
    public void execute() {
        model.removeShape(shape);
    }
    /**
     * Undoes command
     */
    @Override
    public void undo() {
        model.addShape(shape);
    }
    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command Remove "+shape.toString();
    }
}
