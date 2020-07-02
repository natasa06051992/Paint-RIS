package command;

import geometry.Point;
import model.Model;

/* *
 * * The ModifyPointCommand class that implements ICommand and modify point
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class ModifyPointCommand implements ICommand{
    private Model model;
    private Point newPoint;
    private Point oldPoint;
    private Point original;
    private Point fromPoint;

    /**
     * Constructor that creates object of class ModifyPointCommand
     * @param model Model that contains shapes
     * @param oldPoint Old point
     * @param newPoint New point
     */
    public ModifyPointCommand(Model model, Point oldPoint, Point newPoint) {
        this.model = model;
        this.newPoint=newPoint;
        this.oldPoint=oldPoint;
        original= new Point();
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        original.setX(oldPoint.getX());
        original.setY(oldPoint.getY());
        original.setcColor(oldPoint.getcColor());

        oldPoint.setX(newPoint.getX());
        oldPoint.setY(newPoint.getY());
        oldPoint.setcColor(newPoint.getcColor());
        fromPoint= original;
    }

    /**
     * Undoes command
     */
    @Override
    public void undo() {
        oldPoint.setX(original.getX());
        oldPoint.setY(original.getY());
        oldPoint.setcColor(original.getcColor());
        fromPoint = newPoint;
    }
    /**
     * Sets name of command and returns it
     * @return name of command
     */
    @Override
    public String getNameOfCommand() {
        return "Command Modify "+fromPoint.toString() +" -> "+newPoint.toString();
    }
}
