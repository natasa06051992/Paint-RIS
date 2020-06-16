package command;

import geometry.Shape;
import model.Model;

public class RemoveShapeCommand implements ICommand{
    private Model model;
    private Shape shape;
    public RemoveShapeCommand(Model model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.removeShape(shape);
    }

    @Override
    public void undo() {
        model.addShape(shape);
    }

    @Override
    public String getNameOfClass() {
        return "Command Remove "+shape.toString();
    }
}
