package command;

import geometry.Shape;
import model.Model;

public class ModifyShapeCommand implements ICommand{
    private Model model;
    private Shape shape;
    public ModifyShapeCommand(Model model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify";
    }
}
