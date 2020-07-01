package controller;

import command.*;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.*;
import seriazable.Deserialize;
import seriazable.Serialize;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* *
 * * The Controller class that
 * * can draw, modify and delete points, lines, squares, hexagones, circles and rectangles
 * *
 * * @author  Natasa Pajic
 * * @version 1.0
 * * @since   2020-06-28
 * */
public class Controller
{
    Draw draw;
    public CommandManager commandManager;
    public final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * This constructor is used for creating Controller object
     */
    public Controller(Draw draw){
        this.draw = draw;
        commandManager = CommandManager.getInstance();
        commandManager.setDraw(draw);
    }

    /**
     * This method is used to add shape to actionList and then send it to commandManager
     * @param shape Shape that will be added to actionList
     */
    public void AddShapeAction(Shape shape) {
        AddShapeCommand addComand= new AddShapeCommand(draw.getModel(), shape);
        List<ICommand> actionList = new ArrayList<>();
        actionList.add(addComand);
        commandManager.execute(actionList);
    }

    /**
     * This method is used to add action listeners to class Draw
     */
    public void setListeners()
    {
        draw.getOpenLog().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser j = new JFileChooser();
                j.setCurrentDirectory(new File(System.getProperty("user.home")));
                j.setFileSelectionMode(JFileChooser.FILES_ONLY);
                j.addChoosableFileFilter(new FileNameExtensionFilter("Log files", "log"));
                j.setAcceptAllFileFilterUsed(true);

                int result = j.showOpenDialog(draw);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = j.getSelectedFile();
                    draw.getModel().deselectAllShapes();

                    var executeCommandsFromLog = new ExecuteCommandsFromLog(commandManager, draw.getModel());
                    executeCommandsFromLog.convertLogToList(selectedFile.getPath());
                    executeCommandsFromLog.execute();
                }
            }
        });

        draw.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser j = new JFileChooser();
                j.setCurrentDirectory(new File(System.getProperty("user.home")));
                j.setFileSelectionMode(JFileChooser.FILES_ONLY);
                j.addChoosableFileFilter(new FileNameExtensionFilter("Ser files", "ser"));
                j.setAcceptAllFileFilterUsed(true);

                int result = j.showSaveDialog(draw);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = j.getSelectedFile();


                    Serialize serialize = new Serialize();
                    serialize.serialize(selectedFile.getPath(), draw.getModel().getShapes());
                }
            }
        });

        draw.getOpenFile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser j = new JFileChooser();
                j.setCurrentDirectory(new File(System.getProperty("user.home")));
                j.setFileSelectionMode(JFileChooser.FILES_ONLY);
                j.addChoosableFileFilter(new FileNameExtensionFilter("Ser files", "ser"));
                j.setAcceptAllFileFilterUsed(true);

                int result = j.showOpenDialog(draw);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = j.getSelectedFile();
                    draw.getModel().deselectAllShapes();

                    Deserialize deserialize = new Deserialize();
                    var shapes = deserialize.deserialize(selectedFile.getPath());
                    shapes.forEach(shape->draw.getModel().addShape(shape));
                }
            }
        });
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
                 commandManager.undo();
          }
      });
      draw.getRedo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    commandManager.redo();

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

                        List<ICommand> actionList = new ArrayList<>();
                        for (var shape:draw.getSelectedShapes()) {
                            RemoveShapeCommand removeCommand= new RemoveShapeCommand(draw.getModel(), shape);
                            actionList.add(removeCommand);
                        }
                        commandManager.execute(actionList);
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

                    cmd = modifyPoint(options);
                }
                else if (draw.getSelectedShape() instanceof Line){
                    cmd = modifyLine(options);
                }
                else if ((draw.getSelectedShape() instanceof Square) && !(draw.getSelectedShape() instanceof geometry.Rectangle))
                {
                    cmd = modifySquare(options);
                }
                else if (draw.getSelectedShape() instanceof geometry.Rectangle){
                    cmd = modifyRectangle(options);
                }
                else if(draw.getSelectedShape() instanceof HexagonAdapter){

                    cmd = modifyHexagon(options);

                }
                else if(draw.getSelectedShape() instanceof Circle){
                    cmd = modifyCircle(options);
                }
                actionList.add(cmd);
                commandManager.execute(actionList);
                draw.getSelectedShapes().clear();
                draw.getBtnDelete().setEnabled(false);
                draw.getBtnDelete().setEnabled(false);
                draw.getBtnModify().setEnabled(false);
            }
        });

      draw.getModel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1)
                {
                draw.setX(e.getX());
                draw.setY(e.getY());
                var shape = draw.getCbxShapes().getSelectedItem();
                if(shape.equals("Point")) {
                    createPoint();
                }
                else if(shape.equals("Line")){
                    createLine(e);
                }
                else if(shape.equals("Circle")){
                    createCircleDialog();
                }
                else if(shape.equals("Hexagon")){
                    createHexagonDialog();
                }
                else if(shape.equals("Square")){
                    createSquareDialog();
                }
                else if(shape.equals("Rectangle")){
                    createRectangleDialog();
                }
                else if (shape.toString().equals("Choose shape"))
                {
                    manageButtons();
                }
                }else if (e.getButton() == MouseEvent.BUTTON3 &&
                        draw.getSelectedShapes() != null&&
                        draw.getSelectedShapes().size()==1){

                    setlistenerToRightClick(e);
                }
            }
        });
    }

    /**
     *This method changes selected circle
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifyCircle(Object[] options) {
        Circle circle = (Circle)draw.getSelectedShape();

        ICommand cmd = null;
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
        return cmd;
    }

    /**
     *This method changes selected hexagon
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifyHexagon(Object[] options) {
        HexagonAdapter hexagon = (HexagonAdapter)draw.getSelectedShape();
        JTextField centerX = new JTextField();
        JTextField centerY = new JTextField();
        JTextField R = new JTextField();
        ICommand cmd = null;
        centerX.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getCenter().getX()));
        centerY.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getCenter().getY()));
        R.setText(String.valueOf(((HexagonAdapter) draw.getSelectedShape()).getR()));

        Color edge = hexagon.getCEdge();
        Color inside = hexagon.getCInside();

        JColorChooser ccEdgeHexagon = new JColorChooser();

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
        return cmd;
    }

    /**
     *This method changes selected rectangle
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifyRectangle(Object[] options) {
        Rectangle rectangle = (Rectangle)draw.getSelectedShape();

        JTextField upLeftX = new JTextField();
        JTextField upLeftY = new JTextField();
        JTextField length = new JTextField();
        JTextField height = new JTextField();
        ICommand cmd = null;
        upLeftX.setText(String.valueOf(((Rectangle) draw.getSelectedShape()).getUpLeft().getX()));
        upLeftY.setText(String.valueOf(((Rectangle) draw.getSelectedShape()).getUpLeft().getY()));
        length.setText(String.valueOf(((Rectangle) draw.getSelectedShape()).getSideLength()));
        height.setText(String.valueOf(((Rectangle) draw.getSelectedShape()).getHeight()));

        Color edge = rectangle.getCEdge();
        Color inside = rectangle.getCInside();

        JColorChooser ccEdgeRghtAngle = new JColorChooser();

        JColorChooser ccInsideRightAngle = new JColorChooser();

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
        return cmd;
    }

    /**
     *This method changes selected square
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifySquare( Object[] options) {
        Square square = (Square)draw.getSelectedShape();

        ICommand cmd = null;
        JTextField upLeftX = new JTextField();
        JTextField upLeftY = new JTextField();
        JTextField lengthLine = new JTextField();

        upLeftX.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getX()));
        upLeftY.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getY()));
        lengthLine.setText(String.valueOf(((Square) draw.getSelectedShape()).getSideLength()));

        Color edge = square.getCEdge();
        Color inside = square.getCInside();

        JColorChooser ccEdgeSquare = new JColorChooser();

        JColorChooser ccInside = new JColorChooser();


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
        return cmd;
    }

    /**
     *This method changes selected line
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifyLine(Object[] options) {
        JTextField startX = new JTextField();
        JTextField startY = new JTextField();

        ICommand cmd = null;
        startX.setText(String.valueOf(((Line) draw.getSelectedShape()).getpStart().getX()));
        startY.setText(String.valueOf(((Line) draw.getSelectedShape()).getpStart().getY()));

        JTextField endX  = new JTextField();
        JTextField endY = new JTextField();

        endX.setText(String.valueOf(((Line) draw.getSelectedShape()).getpEnd().getX()));
        endY.setText(String.valueOf(((Line) draw.getSelectedShape()).getpEnd().getY()));

        JColorChooser ccColorLine = new JColorChooser();

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
                cmd =new ModifyLineCommand(draw.getModel(), (Line)draw.getSelectedShape(),
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
        return cmd;
    }

    /**
     *This method changes selected point
     * @param options Options for dialog (Modify or Cancel)
     * @return ICommand object
     */
    private ICommand modifyPoint(Object[] options) {
        JTextField newX = new JTextField();
        JTextField newY = new JTextField();

        ICommand cmd = null;
        newX.setText(String.valueOf(((Point) draw.getSelectedShape()).getX()));
        newY.setText(String.valueOf(((Point) draw.getSelectedShape()).getY()));

        JColorChooser ccColorPoint = new JColorChooser();

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
            } catch (NumberFormatException e1) {

                JOptionPane.showMessageDialog(null,
                        "Input must be numberc",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            draw.getSelectedShape().setSelected(false);
        }
        return cmd;
    }

    /**
     * Creates point
     */
    private void createPoint() {
        AddShapeAction(new Point(draw.getX(),draw.getY(), draw.getBtnColor().getBackground()));
    }

    /**
     * Creates new line
     * @param e MouseEvent to get coordinates for second point
     */
    private void createLine(MouseEvent e) {
        draw.setSecondClick(draw.getSecondClick()+1);

        if(draw.getSecondClick() == 2){
            draw.setX(e.getX());
            draw.setY(e.getY());
            AddShapeAction(new Line(draw.getFirstPoint(), new Point(draw.getX(),draw.getY()), draw.getBtnColor().getBackground()));
            draw.setSecondClick(0);

        }
        else{
            draw.setFirstPoint(new Point(draw.getX(), draw.getY()));
        }
    }

    /**
     * Opens circle dialog and adds new circle
     */
    private void createCircleDialog() {
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
            Point center = new Point(draw.getX(), draw.getY());
            circle.setCenter(center);
            circle.setCEdge(draw.getBtnColor().getBackground());
            circle.setCInside(draw.getBtnColorInside().getBackground());

            AddShapeAction(circle);
        }
    }

    /**
     * Opens hexagon dialog and adds new circle
     */
    private void createHexagonDialog() {
        DlgHexagon dlgHexagon = new DlgHexagon();

        dlgHexagon.getBtnDismiss().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dlgHexagon.dispose();
            }
        });
        dlgHexagon.getBtnSubmit().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    var radius = Integer.parseInt(dlgHexagon.getRadius().getText());
                    dlgHexagon.setHexagon(new HexagonAdapter(draw.getX(), draw.getY(), radius));
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
            Point center = new Point(draw.getX(), draw.getY());
            hexagon.setCenter(center);
            hexagon.setCEdge(draw.getBtnColor().getBackground());
            hexagon.setCInside(draw.getBtnColorInside().getBackground());

            AddShapeAction(hexagon);
        }
    }

    /**
     * Opens square dialog and adds new square
     */
    private void createSquareDialog() {
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

        Point upLeft = new Point(draw.getX(), draw.getY());

        if(kvDlg.getSquare() != null){
            Square square = kvDlg.getSquare();
            square = kvDlg.getSquare();
            square.setUpLeft(upLeft);
            square.setCEdge(draw.getBtnColor().getBackground());
            square.setCInside(draw.getBtnColorInside().getBackground());

            AddShapeAction(square);
        }
    }

    /**
     * Opens rectangle dialog and adds new rectangle
     */
    private void createRectangleDialog() {
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
                    JOptionPane.showMessageDialog(null, "Insert integer values!",
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
        Point upLeft = new Point(draw.getX(), draw.getY());

        if(dlgP.getRectangle() != null){

            Rectangle r = dlgP.getRectangle();

            r.setUpLeft(upLeft);
            r.setCEdge(draw.getBtnColor().getBackground());
            r.setCInside(draw.getBtnColorInside().getBackground());

            AddShapeAction(r);
        }
    }

    /**
     * Manage btnModify and btnDelete and enable them or not
     */
    private void manageButtons() {
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

    /**
     * set listener to right click
     * @param e MouseEvent for getting coordinates x and y
     */
    private void setlistenerToRightClick(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem bringToFront= new JMenuItem("Bring To Front");
        JMenuItem sendToBack = new JMenuItem("Send To Back");
        JMenuItem stepToFront = new JMenuItem("Step To Front");
        JMenuItem stepToBack = new JMenuItem("Step To Back");

        menu.add(bringToFront);
        menu.add(sendToBack);
        menu.add(stepToFront);
        menu.add(stepToBack);
        bringToFront.setEnabled(true);
        stepToFront.setEnabled(true);
        sendToBack.setEnabled(true);
        stepToBack.setEnabled(true);
        if(draw.getModel().getShapes().indexOf(draw.getSelectedShape())==0){

            sendToBack.setEnabled(false);
            stepToBack.setEnabled(false);
            bringToFront.setEnabled(true);
            stepToFront.setEnabled(true);
        }
        else if(draw.getModel().getShapes().indexOf(draw.getSelectedShape())==
                draw.getModel().getShapes().size()-1){
            bringToFront.setEnabled(false);
            stepToFront.setEnabled(false);
            sendToBack.setEnabled(true);
            stepToBack.setEnabled(true);
        }
        menu.show(e.getComponent(), e.getX(), e.getY());

        bringToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                var command = new BringToFrontCommand(draw.getModel(), draw.getModel().getShapes().indexOf(draw.getSelectedShape()));
                var list = new ArrayList<ICommand>();
                list.add(command);
                commandManager.execute(list);
            }
        });
        sendToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var command = new SendToBackCommand(draw.getModel(), draw.getModel().getShapes().indexOf(draw.getSelectedShape()));
                var list = new ArrayList<ICommand>();
                list.add(command);
                commandManager.execute(list);
            }
        });
        stepToFront.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var command = new StepToFrontCommand(draw.getModel(), draw.getModel().getShapes().indexOf(draw.getSelectedShape()));
                var list = new ArrayList<ICommand>();
                list.add(command);
                commandManager.execute(list);
            }
        });
        stepToBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var command = new StepToBackCommand(draw.getModel(), draw.getModel().getShapes().indexOf(draw.getSelectedShape()));
                var list = new ArrayList<ICommand>();
                list.add(command);
                commandManager.execute(list);
            }
        });
    }
}
