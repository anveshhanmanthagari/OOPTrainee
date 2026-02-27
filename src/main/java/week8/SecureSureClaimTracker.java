package week8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SecureSureClaimTracker extends JFrame {

    private int claimCount = 0;

    // ArrayList to store each claim entry (log simulation)
    private ArrayList<Integer> claimLog = new ArrayList<>();

    private JLabel claimLabel;
    private JButton addButton;
    private JButton resetButton;

    public SecureSureClaimTracker() {

        setTitle("SecureSure Claim Tracker");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        claimLabel = new JLabel("Claims Processed: 0");
        claimLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(claimLabel);

        // Add Claim and reset Buttons
        addButton = new JButton("Add Claim");
        add(addButton);
        resetButton = new JButton("Reset");
        add(resetButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateClaimCount();

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                resetClaims();

            }
        });
    }

    /*
    * Method to update claim count, log the claim, and update the label. Also checks for milestones.
     */
    private void updateClaimCount() {

        claimCount++;

        System.out.println("Claim #" + claimCount + " processed.");
        // Store in ArrayList
        claimLog.add(claimCount);

        // Update label
        claimLabel.setText("Claims Processed: " + claimCount);

        // Conditional milestone message
        if (claimCount % 10 == 0) {
            System.out.println("Milestone reached: " + claimCount + " claims processed!");

            JOptionPane.showMessageDialog(this,
                    "Congratulations! You've logged " + claimCount + " claims.");

        }

    }

    /*
        * Method to reset claim count and log, and update the label accordingly.
     */
    private void resetClaims() {

        claimCount = 0;

        claimLog.clear();
        System.out.println("Claim log reset.");
        claimLabel.setText("Claims Processed: 0");

    }

    // Main Method (Runs GUI on EDT)
    public static void main(String[] args) {
        System.out.println("Starting SecureSure Claim Tracker...");

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                new SecureSureClaimTracker().setVisible(true);

            }

        });

    }
}