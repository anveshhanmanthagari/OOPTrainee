package week8;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

/**
 * SecureSure Claim Tracker
 * This program implements a GUI application to track insurance claims processed by the SecureSure company.
 * It allows users to add claims, reset the claim count, and close the application. The program also logs each claim entry and celebrates milestones every 10 claims with a popup message.
 *
 * @author Anvesh Hanmanthagari
 *
 */
public class SecureSureClaimTracker extends JFrame {

    private int claimCount = 0;

    // ArrayList to store each claim entry (log simulation)
    private ArrayList<Integer> claimLog = new ArrayList<>();

    // GUI Components
    private JLabel claimLabel;
    private JButton addButton;
    private JButton resetButton;
    private JButton shutdownFrame;

    /**
     * Constructor to set up the GUI components and layout for the SecureSure Claim Tracker application.
     * It initializes the frame, header panel with logo and title, and the main center panel with claim count
     * label and action buttons. It also sets up action listeners for each button to handle claim processing,
     * resetting, and shutting down the application.
     */
    public SecureSureClaimTracker() {

        // Frame setup
        setTitle(" SecureSure Claim Tracker ");
        setSize(750, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Custom colors
        Color skyBlue = new Color(135, 206, 235);

        // Header Panel with logo and title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(skyBlue);
        headerPanel.setPreferredSize(new Dimension(750, 120));

        // Logo (left)
        ImageIcon logoIcon = new ImageIcon("src/main/resources/img.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        // Add logo to header panel
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Title (center)
        JLabel titleLabel = new JLabel(" SECURE SURE CLAIM TRACKER ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Add header panel to frame
        add(headerPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        centerPanel.setBackground(Color.WHITE);


        // Claim Label
        claimLabel = new JLabel(" CLAIMS PROCESSED : 0 ", SwingConstants.CENTER);
        claimLabel.setFont(new Font("Arial", Font.BOLD, 24));
        claimLabel.setForeground(Color.BLACK);
        centerPanel.add(claimLabel);


        // Add Button
        addButton = new JButton(" ADD CLAIM TO TRACKER ");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));

        //addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(new Color(34, 139, 34));
        centerPanel.add(addButton);


        // Reset Button
        resetButton = new JButton(" RESET TRACKER ");
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));

        //resetButton.setBackground(new Color(255, 204, 0));
        resetButton.setForeground(new Color(255, 204, 0));
        centerPanel.add(resetButton);


        // Close Button
        shutdownFrame = new JButton(" CLOSE TRACKER ");
        shutdownFrame.setFont(new Font("Arial", Font.BOLD, 18));

        //shutdownFrame.setBackground(new Color(220, 20, 60));
        shutdownFrame.setForeground(new Color(220, 20, 60));
        centerPanel.add(shutdownFrame);
        add(centerPanel, BorderLayout.CENTER);

        // Action Listeners
        addButton.addActionListener(e -> {
            System.out.println("Processing new claim...");
            updateClaimCount();
        });

        // Reset button action listener to reset claim count and log
        resetButton.addActionListener(e -> {
            System.out.println("Resetting claim count and log...");
            resetClaims();
        });

        // Shutdown button action listener to close the application
        shutdownFrame.addActionListener(e -> {
            System.out.println("Shutting down SecureSure Claim Tracker...");
            dispose();
            System.exit(0);
        });
    }

    /**
     *
     * Method to update the claim count, log the claim, and update the label.
     * It also checks for milestones every 10 claims and displays a celebration message.
     * This method is called when the "ADD CLAIM TO TRACKER" button is clicked, allowing the user to increment
     * the claim count and see real-time updates in the GUI.
     * It provides console feedback for each claim processed and celebrates milestones
     * with a popup message and color change in the label.
     *
     */
    private void updateClaimCount() {

        claimCount++;

        System.out.println("CLAIMS #" + claimCount + " processed.");
        // Store in ArrayList
        claimLog.add(claimCount);

        // Update label
        claimLabel.setText(" CLAIMS PROCESSED : " + claimCount);

        // Conditional milestone message
        if (claimCount % 10 == 0) {
            System.out.println("Milestone reached: " + claimCount + " claims processed!");

            System.out.println("Milestone reached!");

            // Change label color for celebration
            claimLabel.setForeground(new Color(0, 128, 255));

            // Custom celebration panel
            JPanel celebrationPanel = new JPanel();
            celebrationPanel.setBackground(new Color(255, 230, 150));
            celebrationPanel.add(new JLabel("🎉 Congratulations! " + claimCount + " Claims Achieved! 🎉"));

            JOptionPane.showMessageDialog(
                    this,
                    celebrationPanel,
                    "Milestone Achievement!",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Reset label color after popup
            claimLabel.setForeground(Color.DARK_GRAY);

        }

    }

    /*
     * Method to reset claim count and log, and update the label accordingly.
     * This method is called when the reset button is clicked,
     * allowing the user to start fresh with a new claim count and an empty log.
     * It also provides console feedback about the reset action.
     */
    private void resetClaims() {

        claimCount = 0;
        claimLog.clear();
        System.out.println("Claim log reset.");
        claimLabel.setText(" CLAIMS PROCESSED : 0 ");

    }

    // Main Method (Runs GUI on EDT)

    /**
     * The main entry point of the application. It initializes the SecureSure Claim Tracker GUI and makes it visible.
     * The GUI is launched on the Event Dispatch Thread (EDT) to ensure thread safety for Swing components.
     * @param args
     */
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