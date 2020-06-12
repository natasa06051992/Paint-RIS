package command;

import geometry.Circle;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyCircleCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifyCircleCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
        Circle circle = (Circle)draw.getSelectedShape();


        JTextField centerX = new JTextField();
        JTextField centerY = new JTextField();
        JTextField R = new JTextField();

        centerX.setText(String.valueOf(((Circle) draw.getSelectedShape()).getCenter().getX()));
        centerY.setText(String.valueOf(((Circle) draw.getSelectedShape()).getCenter().getY()));
        R.setText(String.valueOf(((Circle) draw.getSelectedShape()).getR()));

        Color edge = circle.getCEdge();
        Color inside = circle.getCInside();

        JColorChooser ccEdgeCircle = new JColorChooser();
        ccEdgeCircle.setColor(((Circle)draw.getSelectedShape()).getCEdge());

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Color newColor = ccEdgeCircle.getColor();
                ((Circle)draw.getSelectedShape()).setCEdge(newColor);
            }
        };
        ccEdgeCircle.getSelectionModel().addChangeListener(changeListener);

        JColorChooser ccInsideCircle = new JColorChooser();
        ccInsideCircle.setColor(((Circle)draw.getSelectedShape()).getCEdge());

        ChangeListener changeL = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Color newColor = ccInsideCircle.getColor();
                ((Circle)draw.getSelectedShape()).setCEdge(newColor);
            }
        };
        ccInsideCircle.getSelectionModel().addChangeListener(changeL);

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
                circle.getCenter().setX(Integer.parseInt(centerX.getText()));
                circle.getCenter().setY(Integer.parseInt(centerY.getText()));
                circle.setR(Integer.parseInt(R.getText()));
                circle.setCEdge(ccEdgeCircle.getColor());
                circle.setCInside(ccInsideCircle.getColor());
                draw.getSelectedShape().setSelected(false);

                draw.getBtnColor().setBackground(ccEdgeCircle.getColor());
                draw.getBtnColorInside().setBackground(ccInsideCircle.getColor());
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

    @Override
    public void unexecute() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Point";
    }
}
