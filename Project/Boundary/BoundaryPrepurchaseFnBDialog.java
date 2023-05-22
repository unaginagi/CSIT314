package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoundaryPrepurchaseFnBDialog extends JDialog {
    private boolean answer;

    public BoundaryPrepurchaseFnBDialog(JFrame parent, String message) {
        super(parent, "Pre-purchase food?", true);

        // Create the message label
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add action listeners to the buttons
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = true;
                dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = false;
                dispose();
            }
        });

        // Create a panel to hold the label and buttons
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(label);
        panel.add(yesButton);
        panel.add(noButton);

        // Set the layout and add the panel to the dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        // Set the size and display the dialog
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean getAnswer() {
        return answer;
    }
}
