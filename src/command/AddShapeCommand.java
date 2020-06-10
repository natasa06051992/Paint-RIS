package command;

import geometry.Shape;
import model.Model;

public class AddShapeCommand implements ICommand{
    private Model model;
    private Shape shape;
    public AddShapeCommand(Model model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.addShape(shape);
    }

    @Override
    public void unexecute() {
        model.removeShape(shape);
    }

    @Override
    public String getNameOfClass() {
        return "Command Add";
    }
}
