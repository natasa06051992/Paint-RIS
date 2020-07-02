package controller;

import command.*;
import geometry.*;
import geometry.Point;
import geometry.Shape;
import model.Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* *
 * * The ExecuteCommandsFromLog class that reads log and execute actions
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class ExecuteCommandsFromLog {
    ArrayList<String> logList;
    CommandManager manager;
    Model model;

    /**
     * Constructor that creates ExecuteCommandsFromLog object
     * @param manager CommandManager class that manages commands
     * @param model Model that contains shapes
     */
    public ExecuteCommandsFromLog(CommandManager manager, Model model) {
        this.manager=manager;
        this.model=model;
    }

    private static String line;

    /**
     * Converts log to list of commands
     * @param fileName Name of log file
     */
    public void convertLogToList(String fileName){

        try{
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            while ((line = br.readLine()) != null)   {

                if(line.startsWith("[")){
                    //line with action
                    String[] commandMessage = line.split("[\\s]");
                    var isCommand = false;
                    for(int i=0;i<commandMessage.length;i++)
                    {
                        if(commandMessage[i].equals("Command")|| isCommand) {
                            isCommand = true;
                            logList.add(commandMessage[i]);
                        }
                    }

                }
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Adds shape to manager
     * @param shape Shape that is added
     */
    public void AddShapeAction(Shape shape) {
        AddShapeCommand addComand= new AddShapeCommand(model, shape);
        List<ICommand> actionList = new ArrayList<>();
        actionList.add(addComand);
        manager.execute(actionList);
    }

    /**
     * Removes shape to manager
     * @param shapes Shape that is added
     */
    public void RemoveShapeAction(ArrayList<Shape> shapes) {
        List<ICommand> actionList = new ArrayList<>();
        for (var shape:shapes) {
            RemoveShapeCommand removeCommand= new RemoveShapeCommand(model, shape);
            actionList.add(removeCommand);
        }
        manager.execute(actionList);
    }

    /**
     * Executes list of commands
     */
    public void execute() {
        List<String> commands = new ArrayList<String>();
        for(String line : logList){
            if(line.equals("Command"))
            {
                if(!commands.isEmpty()){
                    createShape(commands);
                    commands.clear();
                }
            }
            commands.add(line);
        }
        createShape(commands);
    }

    /**
     * Create shape and executes command
     * @param commands List of commands
     */
    private void createShape(List<String> commands) {
        Shape shape=null;
        List<ICommand> actionList = new ArrayList<>();
        ArrayList<Shape> shapes= new ArrayList<>();
        if(commands.get(commands.size()-1).equals("Undo")){
            manager.undo();
            return;
        }
        else if(commands.get(commands.size()-1).equals("Redo")){
            manager.redo();
            return;
        }
        if(commands.get(2).equals("Point")){
            Color color = new Color(Integer.parseInt(commands.get(5)));
            shape=new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4)),color);
            if(commands.get(1).equals("Modify")) {

                ModifyPointCommand command = new ModifyPointCommand(model, (geometry.Point)shape,
                        new geometry.Point(Integer.parseInt(commands.get(8)),Integer.parseInt(commands.get(9)),new Color(Integer.parseInt(commands.get(10)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(2).equals("Line")){
            Color color = new Color(Integer.parseInt(commands.get(7)));
            shape=new Line(new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4))),
                    new geometry.Point(Integer.parseInt(commands.get(5)),Integer.parseInt(commands.get(6))),
                    color);
            if(commands.get(1).equals("Modify")) {

                var command = new ModifyLineCommand(model, (Line)shape,
                        new Line(new geometry.Point(Integer.parseInt(commands.get(10)),Integer.parseInt(commands.get(11))),
                                new geometry.Point(Integer.parseInt(commands.get(12)),Integer.parseInt(commands.get(13))),
                                new Color(Integer.parseInt(commands.get(14)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(2).equals("Square")){
            Color color = new Color(Integer.parseInt(commands.get(6)));
            Color colorEdge = new Color(Integer.parseInt(commands.get(7)));
            shape=new Square(new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4))),
                    Integer.parseInt(commands.get(5)),
                    color,
                    colorEdge);
            if(commands.get(1).equals("Modify")) {

                var command = new ModifySquareCommand(model, (Square) shape,
                        new Square(new geometry.Point(Integer.parseInt(commands.get(10)),Integer.parseInt(commands.get(11))),
                                Integer.parseInt(commands.get(12)),
                                new Color(Integer.parseInt(commands.get(13))),
                                new Color(Integer.parseInt(commands.get(14)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(2).equals("Rectangle")){
            Color color = new Color(Integer.parseInt(commands.get(7)));
            Color colorEdge = new Color(Integer.parseInt(commands.get(8)));
            shape=new geometry.Rectangle(new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4))),
                    Integer.parseInt(commands.get(5)),
                    Integer.parseInt(commands.get(6)),
                    color,
                    colorEdge);
            if(commands.get(1).equals("Modify")) {

                var command = new ModifyRectangleCommand(model, (geometry.Rectangle)shape,
                        new geometry.Rectangle(new geometry.Point(Integer.parseInt(commands.get(11)),Integer.parseInt(commands.get(12))),
                                Integer.parseInt(commands.get(13)),
                                Integer.parseInt(commands.get(14)),
                                new Color(Integer.parseInt(commands.get(15))),
                                new Color(Integer.parseInt(commands.get(16)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(2).equals("Circle")){
            Color color = new Color(Integer.parseInt(commands.get(6)));
            Color colorEdge = new Color(Integer.parseInt(commands.get(7)));
            shape=new Circle(new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4))),
                    Integer.parseInt(commands.get(5)),
                    color,
                    colorEdge);
            if(commands.get(1).equals("Modify")) {

                var command = new ModifyCircleCommand(model, (Circle)shape,
                        new Circle(new geometry.Point(Integer.parseInt(commands.get(10)),Integer.parseInt(commands.get(11))),
                                Integer.parseInt(commands.get(12)),
                                new Color(Integer.parseInt(commands.get(13))),
                                new Color(Integer.parseInt(commands.get(14)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(2).equals("Hexagon")){
            Color color = new Color(Integer.parseInt(commands.get(6)));
            Color colorEdge = new Color(Integer.parseInt(commands.get(7)));
            shape=new HexagonAdapter(new geometry.Point(Integer.parseInt(commands.get(3)),Integer.parseInt(commands.get(4))),
                    Integer.parseInt(commands.get(5)),
                    color,
                    colorEdge);
            if(commands.get(1).equals("Modify")) {

                var command = new ModifyHexagonCommand(model, (HexagonAdapter) shape,
                        new HexagonAdapter(new Point(Integer.parseInt(commands.get(10)),Integer.parseInt(commands.get(11))),
                                Integer.parseInt(commands.get(12)),
                                new Color(Integer.parseInt(commands.get(13))),
                                new Color(Integer.parseInt(commands.get(14)))));
                actionList.add(command);
                manager.execute(actionList);
            }
        }
        else if(commands.get(1).equals("BringToFront")){
            var command = new BringToFrontCommand(model, Integer.parseInt(commands.get(5)));
            actionList.add(command);
            manager.execute(actionList);
        }
        else if(commands.get(1).equals("SendToBack")){
            var command = new SendToBackCommand(model, Integer.parseInt(commands.get(5)));
            actionList.add(command);
            manager.execute(actionList);
        }
        else if(commands.get(1).equals("StepToBack")){
            var command = new StepToBackCommand(model, Integer.parseInt(commands.get(5)));
            actionList.add(command);
            manager.execute(actionList);
        }
        else if(commands.get(1).equals("StepToFront")){
            var command = new StepToFrontCommand(model, Integer.parseInt(commands.get(5)));
            actionList.add(command);
            manager.execute(actionList);
        }
        if(commands.get(1).equals("Add")){
            AddShapeAction(shape);
        }
        else if(commands.get(1).equals("Remove")){
            shapes.add(shape);
            RemoveShapeAction(shapes);
        }
    }
}
