package command;

import geometry.HexagonAdapter;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyHexagonCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifyHexagonCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
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

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Color newColor = ccEdgeHexagon.getColor();
                ((HexagonAdapter)draw.getSelectedShape()).setCEdge(newColor);
            }
        };
        ccEdgeHexagon.getSelectionModel().addChangeListener(changeListener);

        JColorChooser ccInsideHexagon = new JColorChooser();
        ccInsideHexagon.setColor(((HexagonAdapter)draw.getSelectedShape()).getCEdge());

        ChangeListener changeL = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Color newColor = ccInsideHexagon.getColor();
                ((HexagonAdapter)draw.getSelectedShape()).setCEdge(newColor);
            }
        };
        ccInsideHexagon.getSelectionModel().addChangeListener(changeL);

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
                hexagon.getCenter().setX(Integer.parseInt(centerX.getText()));
                hexagon.getCenter().setY(Integer.parseInt(centerY.getText()));
                hexagon.setR(Integer.parseInt(R.getText()));
                hexagon.setCEdge(ccEdgeHexagon.getColor());
                hexagon.setCInside(ccInsideHexagon.getColor());
                draw.getSelectedShape().setSelected(false);

                draw.getBtnColor().setBackground(ccEdgeHexagon.getColor());
                draw.getBtnColorInside().setBackground(ccInsideHexagon.getColor());
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

    @Override
    public void unexecute() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Point";
    }
}
