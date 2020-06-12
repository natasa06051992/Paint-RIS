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

public class Controller
{
    Draw draw;
    public Controller(Draw draw){
        this.draw = draw;
    }
    public void AddShapeAction(Shape shape) {
        AddShapeCommand addComand= new AddShapeCommand(draw.getModel(), shape);
        addComand.execute();
    }
    public void RemoveShapeAction(Shape shape) {
        RemoveShapeCommand removeComand= new RemoveShapeCommand(draw.getModel(), shape);
        removeComand.execute();
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
                        for(int i=0;i<draw.getSelectedShapes().size();i++)
                        {
                            RemoveShapeAction(draw.getSelectedShapes().get(i));
                        }

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

                Object[] options = {"Modify", "Cancel"};

                if (draw.getSelectedShape() instanceof geometry.Point){
                    ModifyPointCommand cmdPoint=new ModifyPointCommand(draw);
                    cmdPoint.execute();
                }
                else if (draw.getSelectedShape() instanceof Line){
                    ModifyLineCommand cmd = new ModifyLineCommand(draw);
                    cmd.execute();
                }
                else if ((draw.getSelectedShape() instanceof Square) && !(draw.getSelectedShape() instanceof geometry.Rectangle)){
                    ModifySquareCommand cmd = new ModifySquareCommand(draw);
                    cmd.execute();
                }
                else if (draw.getSelectedShape() instanceof geometry.Rectangle){
                    ModifyRectangleCommand cmd = new ModifyRectangleCommand(draw);
                    cmd.execute();
                }
                else if(draw.getSelectedShape() instanceof HexagonAdapter){

                    ModifyHexagonCommand cmd = new ModifyHexagonCommand(draw);
                    cmd.execute();
                }
                else if(draw.getSelectedShape() instanceof Circle){
                    ModifyCircleCommand cmd = new ModifyCircleCommand(draw);
                    cmd.execute();

                }
                draw.getSelectedShapes().clear();
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
                                dlgP.getRectangle().setHeight(dlgP.getHeight());
                                dlgP.getRectangle().setSideLength(dlgP.getWidth());
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
