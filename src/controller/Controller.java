package controller;

import command.*;
import geometry.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import view.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller
{
    Draw draw;
    private CommandManager manager;
    public final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public Controller(Draw draw){
        this.draw = draw;
        manager = CommandManager.getInstance();
    }
    public void AddShapeAction(Shape shape) {
        AddShapeCommand addComand= new AddShapeCommand(draw.getModel(), shape);
        List<ICommand> actionList = new ArrayList<>();
        actionList.add(addComand);
        manager.execute(actionList);
    }
    public void RemoveShapeAction(ArrayList<Shape> shapes) {
        List<ICommand> actionList = new ArrayList<>();
        for (var shape:shapes) {
            RemoveShapeCommand removeCommand= new RemoveShapeCommand(draw.getModel(), shape);
            actionList.add(removeCommand);
        }

        manager.execute(actionList);
    }
    public void setListeners()
    {
        draw.addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent we)
        {

            String ObjButtons[] = {"Yes","No"};
            int PromptResult = JOptionPane.showOptionDialog(null,
                    "Do you want to exit?", "Safty question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    ObjButtons,ObjButtons[1]);
            if(PromptResult==0)
            {
                logger.log(Level.FINE, "Application is closed.");
                System.exit(0);
            }
        }});

        draw.getBtnColor().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Choose color", draw.getColorEdge());
                draw.getBtnColor().setBackground(c);
            }
        });

      draw.getBtnColorInside().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Color inside", draw.getColorInside());
                draw.getBtnColorInside().setBackground(c);
            }
        });
      draw.getUndo().addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             if(draw.getUndo().isEnabled()){
                 manager.undo();
             }
          }
      });
        draw.getRedo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(draw.getRedo().isEnabled()){
                    manager.redo();
                }
            }
        });
      draw.getBtnDelete().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (draw.getBtnDelete().isEnabled()){
                    Object[] options =  {"Yes", "No"};

                    int result = JOptionPane.showOptionDialog(
                            null,
                            new JLabel("Are you sure?"),
                            "Deleting",
                            JOptionPane.WARNING_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION){
                        RemoveShapeAction(draw.getSelectedShapes());

                        draw.getSelectedShapes().clear();
                        draw.setSelectedShape(null);
                        draw.getBtnDelete().setEnabled(false);
                        draw.getBtnModify().setEnabled(false);
                    }
                }

            }
        });


        draw.getBtnModify().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<ICommand> actionList = new ArrayList<>();
                ICommand cmd = null;
                Object[] options = {"Modify", "Cancel"};

                if (draw.getSelectedShape() instanceof geometry.Point){

                    JTextField newX = new JTextField();
                    JTextField newY = new JTextField();

                    newX.setText(String.valueOf(((geometry.Point) draw.getSelectedShape()).getX()));
                    newY.setText(String.valueOf(((geometry.Point) draw.getSelectedShape()).getY()));

                    JColorChooser ccColorPoint = new JColorChooser();
                    ccColorPoint.setColor(draw.getSelectedShape().getcColor());

                    ChangeListener changeListener = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccColorPoint.getColor();
                            draw.getSelectedShape().setcColor(newColor);
                        }
                    };
                    ccColorPoint.getSelectionModel().addChangeListener(changeListener);

                    final JComponent[] input = new JComponent[]{
                            new JLabel("X coordinate point: "),
                            newX,
                            new JLabel("Y coordinate point: "),
                            newY,
                            ccColorPoint
                    };

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : input){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify point",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if(result == JOptionPane.OK_OPTION) {

                        try {
                            cmd = new ModifyPointCommand(draw.getModel(),(Point)draw.getSelectedShape(), new Point(Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()), ccColorPoint.getColor()));

                            // ((geometry.Point) draw.getSelectedShape()).moveTo(Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()));
                            //   draw.getBtnColor().setBackground(ccColorPoint.getColor());
                        } catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numbers",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        draw.getSelectedShape().setSelected(false);
                    }
                }
                else if (draw.getSelectedShape() instanceof Line){
                    JTextField startX = new JTextField();
                    JTextField startY = new JTextField();

                    startX.setText(String.valueOf(((Line) draw.getSelectedShape()).getpStart().getX()));
                    startY.setText(String.valueOf(((Line) draw.getSelectedShape()).getpStart().getY()));

                    JTextField endX  = new JTextField();
                    JTextField endY = new JTextField();

                    endX.setText(String.valueOf(((Line) draw.getSelectedShape()).getpEnd().getX()));
                    endY.setText(String.valueOf(((Line) draw.getSelectedShape()).getpEnd().getY()));

                    JColorChooser ccColorLine = new JColorChooser();
                    ccColorLine.setColor(draw.getSelectedShape().getcColor());

                    ChangeListener changeListener = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccColorLine.getColor();
                            draw.getSelectedShape().setcColor(newColor);
                        }
                    };
                    ccColorLine.getSelectionModel().addChangeListener(changeListener);

                    final JComponent[] input = new JComponent[]{
                            new JLabel("X coordinate starting point: "),
                            startX,
                            new JLabel("Y coordinate starting point: "),
                            startY,
                            new JLabel("X coordinate end point: "),
                            endX,
                            new JLabel("Y coordinate end point: "),
                            endY,
                            ccColorLine
                    };

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : input){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify line",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION){
                        try{
                            ((Line) draw.getSelectedShape()).getpStart().setX(Integer.parseInt(startX.getText()));
                            ((Line) draw.getSelectedShape()).getpStart().setY(Integer.parseInt(startY.getText()));

                            ((Line) draw.getSelectedShape()).getpEnd().setX(Integer.parseInt(endX.getText()));
                            ((Line) draw.getSelectedShape()).getpEnd().setY(Integer.parseInt(endY.getText()));

                            draw.getBtnColor().setBackground(ccColorLine.getColor());
                            cmd =new  ModifyLineCommand(draw.getModel(), (Line)draw.getSelectedShape(),
                                    new Line(new Point(Integer.parseInt(startX.getText()), Integer.parseInt(startY.getText())),
                                            new Point(Integer.parseInt(endX.getText()), Integer.parseInt(endY.getText())),
                                            ccColorLine.getColor()));
                        }
                        catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numeric",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        draw.getSelectedShape().setSelected(false);

                    }

                }
                else if ((draw.getSelectedShape() instanceof Square) && !(draw.getSelectedShape() instanceof geometry.Rectangle))
                {

                    Square square = (Square)draw.getSelectedShape();

                    JTextField upLeftX = new JTextField();
                    JTextField upLeftY = new JTextField();
                    JTextField lengthLine = new JTextField();

                    upLeftX.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getX()));
                    upLeftY.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getY()));
                    lengthLine.setText(String.valueOf(((Square) draw.getSelectedShape()).getSideLength()));

                    Color edge = square.getCEdge();
                    Color inside = square.getCInside();

                    JColorChooser ccEdgeSquare = new JColorChooser();
                    ccEdgeSquare.setColor(((Square)draw.getSelectedShape()).getCEdge());

                    ChangeListener changeListener = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccEdgeSquare.getColor();
                            ((Square)draw.getSelectedShape()).setCEdge(newColor);
                        }
                    };
                    ccEdgeSquare.getSelectionModel().addChangeListener(changeListener);

                    JColorChooser ccInside = new JColorChooser();
                    ccInside.setColor(((Square)draw.getSelectedShape()).getCInside());

                    ChangeListener chL = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccInside.getColor();
                            ((Square)draw.getSelectedShape()).setCInside(newColor);
                        }
                    };
                    ccInside.getSelectionModel().addChangeListener(chL);

                    final JComponent[] input = new JComponent[]{
                            new JLabel("X coordinate point up left: "),
                            upLeftX,
                            new JLabel("Y coordinate point up left: "),
                            upLeftY,
                            new JLabel("Length of side: "),
                            lengthLine,
                            ccEdgeSquare,
                            ccInside
                    };
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : input){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify square",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION) {
                        try{
                            cmd = new ModifySquareCommand(draw.getModel(), square,
                                    new Square(new Point(Integer.parseInt(upLeftX.getText()), Integer.parseInt(upLeftY.getText())),
                                            Integer.parseInt(lengthLine.getText()),
                                            ccEdgeSquare.getColor(),
                                            ccInside.getColor()));
                            draw.getSelectedShape().setSelected(false);

                            draw.getBtnColor().setBackground(ccEdgeSquare.getColor());
                            draw.getBtnColorInside().setBackground(ccInside.getColor());
                        }
                        catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numeric",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        square.setCEdge(edge);
                        square.setCInside(inside);
                        draw.getSelectedShape().setSelected(false);

                    }

                }
                else if (draw.getSelectedShape() instanceof geometry.Rectangle){
                    geometry.Rectangle rectangle = (geometry.Rectangle)draw.getSelectedShape();

                    JTextField upLeftX = new JTextField();
                    JTextField upLeftY = new JTextField();
                    JTextField length = new JTextField();
                    JTextField height = new JTextField();

                    upLeftX.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getUpLeft().getX()));
                    upLeftY.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getUpLeft().getY()));
                    length.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getSideLength()));
                    height.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getHeight()));

                    Color edge = rectangle.getCEdge();
                    Color inside = rectangle.getCInside();

                    JColorChooser ccEdgeRghtAngle = new JColorChooser();
                    ccEdgeRghtAngle.setColor(((geometry.Rectangle)draw.getSelectedShape()).getCEdge());

                    ChangeListener changeListener = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccEdgeRghtAngle.getColor();
                            ((geometry.Rectangle)draw.getSelectedShape()).setCEdge(newColor);
                        }
                    };
                    ccEdgeRghtAngle.getSelectionModel().addChangeListener(changeListener);

                    JColorChooser ccInsideRightAngle = new JColorChooser();
                    ccInsideRightAngle.setColor(((geometry.Rectangle)draw.getSelectedShape()).getCEdge());

                    ChangeListener changeL = new ChangeListener() {
                        public void stateChanged(ChangeEvent changeEvent) {
                            Color newColor = ccInsideRightAngle.getColor();
                            ((Rectangle)draw.getSelectedShape()).setCEdge(newColor);
                        }
                    };
                    ccInsideRightAngle.getSelectionModel().addChangeListener(changeL);

                    final JComponent[] inputs = new JComponent[]{
                            new JLabel("X coordinate point up left: "),
                            upLeftX,
                            new JLabel("Y coordinate point up left:  "),
                            upLeftY,
                            new JLabel("Length of side: "),
                            length,
                            new JLabel("Hight of side:"),
                            height,
                            ccEdgeRghtAngle,
                            ccInsideRightAngle};
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : inputs){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify rectangle",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION) {
                        try{
                            rectangle.getUpLeft().setX(Integer.parseInt(upLeftX.getText()));
                            rectangle.getUpLeft().setY(Integer.parseInt(upLeftY.getText()));
                            rectangle.setSideLength(Integer.parseInt(length.getText()));
                            rectangle.setHeight(Integer.parseInt(height.getText()));
                            rectangle.setCEdge(ccEdgeRghtAngle.getColor());
                            rectangle.setCInside(ccInsideRightAngle.getColor());
                            draw.getSelectedShape().setSelected(false);

                            draw.getBtnColor().setBackground(ccEdgeRghtAngle.getColor());
                            draw.getBtnColorInside().setBackground(ccInsideRightAngle.getColor());
                            var newRectangle = new Rectangle(new Point(Integer.parseInt(upLeftX.getText()),Integer.parseInt(upLeftY.getText())),
                                    Integer.parseInt(length.getText()),
                                    Integer.parseInt(height.getText()),
                                    ccEdgeRghtAngle.getColor(),
                                    ccInsideRightAngle.getColor());
                            cmd = new ModifyRectangleCommand(draw.getModel(), rectangle, newRectangle);
                        }
                        catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numeric",
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else {
                        rectangle.setCEdge(edge);
                        rectangle.setCInside(inside);
                        draw.getSelectedShape().setSelected(false);

                    }

                }
                else if(draw.getSelectedShape() instanceof HexagonAdapter){

                    HexagonAdapter hexagon = (HexagonAdapter)draw.getSelectedShape();
                    JTextField centerX = new JTextField();
                    JTextField centerY = new JTextField();
                    JTextField R = new JTextField();

                    centerX.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getCenter().getX()));
                    centerY.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getCenter().getY()));
                    R.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getR()));

                    Color edge = hexagon.getCEdge();
                    Color inside = hexagon.getCInside();

                    JColorChooser ccEdgeHexagon = new JColorChooser();
                    ccEdgeHexagon.setColor(((HexagonAdapter)draw.getSelectedShape()).getCEdge());

                    JColorChooser ccInsideHexagon = new JColorChooser();

                    final JComponent[] inputs = new JComponent[]{
                            new JLabel("X coordinate of center: "),
                            centerX,
                            new JLabel("Y coordinate of center:"),
                            centerY,
                            new JLabel("Radius: "),
                            R,
                            ccEdgeHexagon,
                            ccInsideHexagon
                    };
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : inputs){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify hexagon",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION) {
                        try{
                            //    hexagon.getCenter().setX(Integer.parseInt(centerX.getText()));
                            //    hexagon.getCenter().setY(Integer.parseInt(centerY.getText()));
                            //    hexagon.setR(Integer.parseInt(R.getText()));
                            //    hexagon.setCEdge(ccEdgeHexagon.getColor());
                         //   hexagon.setCInside(ccInsideHexagon.getColor());
                            draw.getSelectedShape().setSelected(false);

                            draw.getBtnColor().setBackground(ccEdgeHexagon.getColor());
                            draw.getBtnColorInside().setBackground(ccInsideHexagon.getColor());
                            var newHexagon = new HexagonAdapter(new Point(Integer.parseInt(centerX.getText()),Integer.parseInt(centerY.getText())),
                                    Integer.parseInt(R.getText()),
                                    ccEdgeHexagon.getColor(),
                                    ccInsideHexagon.getColor());
                            cmd = new ModifyHexagonCommand(draw.getModel(), hexagon, newHexagon);
                        }
                        catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numeric",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        hexagon.setcColor(edge);
                        hexagon.setcColor(inside);
                        draw.getSelectedShape().setSelected(false);

                    }

                }
                else if(draw.getSelectedShape() instanceof Circle){
                    Circle circle = (Circle)draw.getSelectedShape();

                    JTextField centerX = new JTextField();
                    JTextField centerY = new JTextField();
                    JTextField R = new JTextField();

                    centerX.setText(String.valueOf(circle.getCenter().getX()));
                    centerY.setText(String.valueOf(circle.getCenter().getY()));
                    R.setText(String.valueOf(circle.getR()));

                    Color edge = circle.getCEdge();
                    Color inside = circle.getCInside();

                    JColorChooser ccEdgeCircle = new JColorChooser();
                    JColorChooser ccInsideCircle = new JColorChooser();

                    final JComponent[] inputs = new JComponent[]{
                            new JLabel("X coordinate of center: "),
                            centerX,
                            new JLabel("Y coordinate of center:"),
                            centerY,
                            new JLabel("Radius: "),
                            R,
                            ccEdgeCircle,
                            ccInsideCircle
                    };
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                    for (JComponent c : inputs){
                        panel.add(c);
                    }

                    int result = JOptionPane.showOptionDialog(
                            null,
                            panel,
                            "Modify circle",
                            JOptionPane.INFORMATION_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            null
                    );
                    if (result == JOptionPane.OK_OPTION) {
                        try{
                            Circle newCircle = new Circle(new Point(Integer.parseInt(centerX.getText()), Integer.parseInt(centerY.getText())),
                                    Integer.parseInt(R.getText()),
                                    ccEdgeCircle.getColor(),
                                    ccInsideCircle.getColor());
                            draw.getSelectedShape().setSelected(false);

                            draw.getBtnColor().setBackground(ccEdgeCircle.getColor());
                            draw.getBtnColorInside().setBackground(ccInsideCircle.getColor());
                            cmd = new ModifyCircleCommand(draw.getModel(), circle, newCircle);
                        }
                        catch (NumberFormatException e1) {

                            JOptionPane.showMessageDialog(null,
                                    "Input must be numeric",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        circle.setcColor(edge);
                        circle.setcColor(inside);
                        draw.getSelectedShape().setSelected(false);

                    }

                }
                actionList.add(cmd);
                manager.execute(actionList);
                draw.getSelectedShapes().clear();
                draw.getBtnDelete().setEnabled(false);
                draw.getBtnDelete().setEnabled(false);
                draw.getBtnModify().setEnabled(false);
            }
        });


      draw.getModel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                draw.setX(e.getX());
                draw.setY(e.getY());

                String shape = draw.getCbxShapes().getSelectedItem().toString();

                if(shape.equals("Point")) {
                    AddShapeAction(new Point(draw.getX(),draw.getY(), draw.getBtnColor().getBackground()));
                }

                if(shape.equals("Line")){

                    draw.setSecondClick(draw.getSecondClick()+1);

                    if(draw.getSecondClick() == 2){
                        draw.setX(e.getX());
                        draw.setY(e.getY());
                        AddShapeAction(new Line(draw.getFirstPoint(), new geometry.Point(draw.getX(),draw.getY()), draw.getBtnColor().getBackground()));
                        draw.setSecondClick(0);

                    }
                    else{
                        draw.setFirstPoint(new geometry.Point(draw.getX(), draw.getY()));
                    }

                }
                if(shape.equals("Circle")){

                    DlgCircle dlgCircle = new DlgCircle();

                    dlgCircle.getBtnDismiss().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dlgCircle.dispose();
                        }
                    });
                    dlgCircle.getBtnSubmit().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                dlgCircle.setCircle(new Circle());
                                dlgCircle.getCircle().setR(Integer.parseInt(dlgCircle.getRadius().getText()));
                                dlgCircle.dispose();
                            } catch (NumberFormatException e1) {
                                e1.printStackTrace();
                                JOptionPane.showMessageDialog(null, "You must enter integer values!","Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                    dlgCircle.setVisible(true);
                    if(dlgCircle.getCircle() != null){
                        Circle circle = dlgCircle.getCircle();
                        geometry.Point center = new geometry.Point(draw.getX(), draw.getY());
                        circle.setCenter(center);
                        circle.setCEdge(draw.getBtnColor().getBackground());
                        circle.setCInside(draw.getBtnColorInside().getBackground());

                        AddShapeAction(circle);
                    }

                }
                if(shape.equals("Hexagon")){

                    DlgHexagon dlgHexagon = new DlgHexagon();

                    dlgHexagon.getBtnDismiss().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dlgHexagon.dispose();
                        }
                    });
                    dlgHexagon.getBtnSubmit().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                dlgHexagon.setHexagon(new HexagonAdapter(draw.getX(), draw.getY(), Integer.parseInt(dlgHexagon.getRadius().getText())));
                                dlgHexagon.dispose();
                            } catch (NumberFormatException e1) {
                                e1.printStackTrace();
                                JOptionPane.showMessageDialog(null, "You must enter integer values!","Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                    dlgHexagon.setVisible(true);
                    if(dlgHexagon.getHexagon() != null){
                        HexagonAdapter hexagon = dlgHexagon.getHexagon();
                        geometry.Point center = new geometry.Point(draw.getX(), draw.getY());
                        hexagon.setCenter(center);
                        hexagon.setCEdge(draw.getBtnColor().getBackground());
                        hexagon.setCInside(draw.getBtnColorInside().getBackground());

                        AddShapeAction(hexagon);
                    }

                }
                if(shape.equals("Square")){

                    DlgSqrC kvDlg = new DlgSqrC();

                    kvDlg.getBtnSubmit().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                kvDlg.setSquare(new Square());
                                kvDlg.getSquare().setSideLength(Integer.parseInt(kvDlg.getLengthOfSide().getText()));
                                kvDlg.getSquare().setCEdge(kvDlg.getColorEdge());
                                kvDlg.getSquare().setCInside(kvDlg.getColorInside());
                                kvDlg.dispose();
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(null, "Enter integer values!","Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }

                    });
                    kvDlg.getBtnDismiss().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            kvDlg.dispose();
                        }
                    });
                    kvDlg.setVisible(true);

                    geometry.Point upLeft = new geometry.Point(draw.getX(), draw.getY());

                    if(kvDlg.getSquare() != null){
                        Square square = kvDlg.getSquare();
                        square = kvDlg.getSquare();
                        square.setUpLeft(upLeft);
                        square.setCEdge(draw.getBtnColor().getBackground());
                        square.setCInside(draw.getBtnColorInside().getBackground());

                        AddShapeAction(square);
                    }

                }
                if(shape.equals("Rectangle")){

                    DlgRectangle dlgP = new DlgRectangle();

                    dlgP.getBtnSubmit().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                dlgP.setRectangle(new Rectangle());
                                dlgP.getRectangle().setHeight(Integer.parseInt(dlgP.getHeightRectangle().getText()));
                                dlgP.getRectangle().setSideLength(Integer.parseInt(dlgP.getWidthRectangle().getText()));
                                dlgP.getRectangle().setcColor(dlgP.getColorEdge());
                                dlgP.getRectangle().setCInside(dlgP.getColorInside());
                                dlgP.dispose();
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(null, "Inser integer values!",
                                        "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });
                    dlgP.getBtnDismiss().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dlgP.dispose();
                        }
                    });
                    dlgP.setVisible(true);
                    geometry.Point upLeft = new Point(draw.getX(), draw.getY());

                    if(dlgP.getRectangle() != null){

                        Rectangle r = dlgP.getRectangle();

                        r.setUpLeft(upLeft);
                        r.setCEdge(draw.getBtnColor().getBackground());
                        r.setCInside(draw.getBtnColorInside().getBackground());

                        AddShapeAction(r);
                    }


                }
                if (shape.equals("Choose shape")){

                    draw.setSelectedShapes(draw.getModel().findSelected(draw.getX(), draw.getY()));

                    if(draw.getSelectedShapes()!=null&&
                       !draw.getSelectedShapes().isEmpty()){
                        draw.getBtnDelete().setEnabled(true);

                        if(draw.getSelectedShapes().size()==1)
                        {
                            draw.setSelectedShape(draw.getSelectedShapes().get(0));
                            draw.getSelectedShapeText().setText(draw.getSelectedShape().toString());
                            draw.getSelectedShapeText().setEnabled(false);
                        }


                        if(draw.getSelectedShapes().size()==1)
                        {
                            draw.getBtnModify().setEnabled(true);
                        }
                        else {
                            draw.getBtnModify().setEnabled(false);
                        }
                    }

                    else{
                        draw.getBtnDelete().setEnabled(false);
                        draw.getBtnModify().setEnabled(false);
                    }

                }
            }
        });
    }
}
