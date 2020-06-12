package command;

import geometry.Line;
import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyLineCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifyLineCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
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

    @Override
    public void unexecute() {

    }

    @Override
    public String getNameOfClass() {
        return "Command Modify Point";
    }
}
