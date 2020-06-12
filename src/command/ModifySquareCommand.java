package command;

import geometry.Shape;
import geometry.Square;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifySquareCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifySquareCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
        Square k = (Square)draw.getSelectedShape();

        JTextField upLeftX = new JTextField();
        JTextField upLeftY = new JTextField();
        JTextField lengthLine = new JTextField();

        upLeftX.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getX()));
        upLeftY.setText(String.valueOf(((Square) draw.getSelectedShape()).getUpLeft().getY()));
        lengthLine.setText(String.valueOf(((Square) draw.getSelectedShape()).getSideLength()));

        Color edge = k.getCEdge();
        Color inside = k.getCInside();

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
                k.getUpLeft().setX(Integer.parseInt(upLeftX.getText()));
                k.getUpLeft().setY(Integer.parseInt(upLeftY.getText()));
                k.setSideLength(Integer.parseInt(lengthLine.getText()));
                k.setCEdge(ccEdgeSquare.getColor());
                k.setCInside(ccInside.getColor());
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
            k.setCEdge(edge);
            k.setCInside(inside);
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
