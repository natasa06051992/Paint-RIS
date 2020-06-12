package command;

import geometry.Shape;
import model.Model;
import view.Draw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModifyPointCommand implements ICommand{
    private Model model;
    private Shape shape;
    private Draw draw;
    public ModifyPointCommand(Draw draw) {
        this.model = draw.getModel();
        this.shape = draw.getSelectedShape();
        this.draw=draw;
    }
    @Override
    public void execute() {
        Object[] options = {"Modify", "Cancel"};
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
        if(result == JOptionPane.OK_OPTION){

            try{
                ((geometry.Point) draw.getSelectedShape()).moveTo(Integer.parseInt(newX.getText()), Integer.parseInt(newY.getText()));
                draw.getBtnColor().setBackground(ccColorPoint.getColor());
            }
            catch (NumberFormatException e1) {

                JOptionPane.showMessageDialog(null,
                        "Input must be numbers",
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
