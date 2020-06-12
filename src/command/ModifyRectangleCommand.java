package command;

import geometry.Rectangle;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyRectangleCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifyRectangleCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
        geometry.Rectangle p = (geometry.Rectangle)draw.getSelectedShape();

        JTextField upLeftX = new JTextField();
        JTextField upLeftY = new JTextField();
        JTextField length = new JTextField();
        JTextField height = new JTextField();

        upLeftX.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getUpLeft().getX()));
        upLeftY.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getUpLeft().getY()));
        length.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getSideLength()));
        height.setText(String.valueOf(((geometry.Rectangle) draw.getSelectedShape()).getHeight()));

        Color edge = p.getCEdge();
        Color inside = p.getCInside();

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
                p.getUpLeft().setX(Integer.parseInt(upLeftX.getText()));
                p.getUpLeft().setY(Integer.parseInt(upLeftY.getText()));
                p.setSideLength(Integer.parseInt(length.getText()));
                p.setHeight(Integer.parseInt(height.getText()));
                p.setCEdge(ccEdgeRghtAngle.getColor());
                p.setCInside(ccInsideRightAngle.getColor());
                draw.getSelectedShape().setSelected(false);

                draw.getBtnColor().setBackground(ccEdgeRghtAngle.getColor());
                draw.getBtnColorInside().setBackground(ccInsideRightAngle.getColor());
            }
            catch (NumberFormatException e1) {

                JOptionPane.showMessageDialog(null,
                        "Input must be numeric",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else {
            p.setCEdge(edge);
            p.setCInside(inside);
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
