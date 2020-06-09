package view;

import geometry.HexagonAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DlgHexagon extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField radius;
    public HexagonAdapter hexagon;
    JButton btnSubmit;
    public HexagonAdapter getHexagon(){
        return hexagon;
    }
    public JTextField getRadius() {
        return radius;
    }

    public void setRadius(JTextField radius) {
        this.radius = radius;
    }

    public void setHexagon(HexagonAdapter hexagon) {
        this.hexagon = hexagon;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public JButton getBtnDismiss() {
        return btnDismiss;
    }

    JButton btnDismiss;

    public static void main(String[] args) {
        try {
            DlgHexagon dialog = new DlgHexagon();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgHexagon() {
        setBounds(100, 100, 450, 300);
        setModal(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new GridLayout(3, 2, 5, 10));
            {
                JLabel lblRadius = new JLabel("Enter radius of Hexagon: ");
                panel.add(lblRadius);
            }
            {
                radius = new JTextField();
                panel.add(radius);
                radius.setColumns(10);
            }
        }
        {
            JPanel btnPanel = new JPanel();
            btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(btnPanel, BorderLayout.SOUTH);
            {
                btnSubmit = new JButton("Submit");
                btnSubmit.setActionCommand("Submit");
                btnPanel.add(btnSubmit);
                getRootPane().setDefaultButton(btnSubmit);
            }
            {
                btnDismiss = new JButton("Dismiss");
                btnDismiss.setActionCommand("Dismiss");
                btnPanel.add(btnDismiss);
            }
        }
    }

}
