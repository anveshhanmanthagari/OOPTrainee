package week8;

import javax.swing.*;
import java.awt.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * SecureSure Claim Tracker
 * This program implements a GUI application to track insurance claims processed by the SecureSure company.
 * It allows users to add claims, reset the claim count, and close the application.
 * The program also logs each claim entry and celebrates milestones every 10 claims with a popup message.
 *
 * @author Anvesh Hanmanthagari
 *
 */
public class SecureSureClaimTracker extends JFrame {

    private int claimCount = 0;

    // ArrayList to store each claim entry (log simulation)
    private ArrayList<String> claimLog = new ArrayList<>();
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // GUI Components
    private JLabel claimLabel;
    private JButton addButton;
    private JButton resetButton;
    private JButton shutdownFrame;
    private JTextArea logArea;

    // Main Method (Runs GUI on EDT)

    /**
     * The main entry point of the SecureSure Claim Tracker application.
     *
     * This method initializes the graphical user interface (GUI) and ensures
     * that it is created and executed on the Event Dispatch Thread (EDT).
     * Running the application on the EDT guarantees thread safety, as Swing
     * components are not thread-safe and must be accessed only from this thread.
     *
     * The method prints a startup message to the console and launches the
     * SecureSureClaimTracker frame, making it visible to the user.
     *
     * @param args Command-line arguments passed to the application (not used).
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

    /**
     * Constructs and initializes the SecureSure Claim Tracker graphical user interface.
     *
     * This constructor configures the main application window, including layout
     * structure, visual styling, and interactive components. The frame uses a
     * BorderLayout to organize the interface into three primary sections:
     *
     * 1. Top Section (NORTH):
     *    Displays the company logo, application title, and a dynamically updated
     *    label showing the total number of processed claims.
     *
     * 2. Middle Section (CENTER):
     *    Contains a non-editable text area within a scroll pane to display
     *    real-time claim log entries, including timestamps.
     *
     * 3. Bottom Section (SOUTH):
     *    Provides inline action buttons for adding claims, resetting the tracker,
     *    and closing the application.
     *
     * Event listeners are attached to each button to handle user interactions.
     * The "Add Claim" button updates the claim count and logs the transaction.
     * The "Reset" button clears all stored data and refreshes the display.
     * The "Close" button terminates the application safely.
     *
     * The layout design emphasizes clarity, maintainability, and professional
     * usability standards suitable for internal business tools.
     */
    public SecureSureClaimTracker() {

        setTitle("SecureSure Claim Tracker");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color skyBlue = new Color(135, 206, 235);

        // =======================
        // TOP SECTION
        // =======================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(skyBlue);
        topPanel.setPreferredSize(new Dimension(750, 150));

        // Logo
        ImageIcon logoIcon = new ImageIcon("src/main/resources/img.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Title + Count
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(skyBlue);

        JLabel titleLabel = new JLabel("SECURE SURE CLAIM TRACKER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.DARK_GRAY);

        claimLabel = new JLabel("Total Claims: 0", SwingConstants.CENTER);
        claimLabel.setFont(new Font("Arial", Font.BOLD, 20));
        claimLabel.setForeground(Color.BLACK);

        titlePanel.add(titleLabel);
        titlePanel.add(claimLabel);

        topPanel.add(titlePanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // =======================
        // MIDDLE SECTION (Claim Logs)
        // =======================
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        logArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // =======================
        // BOTTOM SECTION (Buttons Inline)
        // =======================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));

        addButton = new JButton("ADD CLAIM");
        addButton.setForeground(new Color(34, 139, 34));
        addButton.setFont(new Font("Arial", Font.BOLD, 16));

        resetButton = new JButton("RESET");
        resetButton.setForeground(new Color(255, 204, 0));
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));

        shutdownFrame = new JButton("CLOSE");
        shutdownFrame.setForeground(new Color(220, 20, 60));
        shutdownFrame.setFont(new Font("Arial", Font.BOLD, 16));

        bottomPanel.add(addButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(shutdownFrame);

        add(bottomPanel, BorderLayout.SOUTH);

        // =======================
        // ACTION LISTENERS
        // =======================
        addButton.addActionListener(e -> updateClaimCount());

        resetButton.addActionListener(e -> resetClaims());

        shutdownFrame.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }

    /**
     * Increments the claim count, logs the transaction with a precise timestamp,
     * updates the user interface, and triggers milestone notifications.
     *
     * This method is executed when the user clicks the "ADD CLAIM" button.
     * It performs the following operations:
     *
     * 1. Increments the internal claim counter.
     * 2. Captures the current date and time (including milliseconds) at the
     *    exact moment the event is triggered.
     * 3. Creates a formatted log entry combining the timestamp and claim number.
     * 4. Stores the log entry in the claimLog collection to preserve historical data.
     * 5. Updates the total claims label displayed in the top section of the GUI.
     * 6. Refreshes the log display area by iterating through all stored entries
     *    using a for-loop to ensure consistent rendering.
     * 7. Displays a celebration popup when a milestone (every 10 claims) is reached.
     *
     * This design ensures accurate event-based logging, maintains data integrity,
     * and provides real-time visual feedback to the user. The method supports
     * enterprise-style transaction tracking and milestone monitoring.
     */
    private void updateClaimCount() {


        claimCount++;

        // Capture timestamp at trigger time
        String timestamp = LocalDateTime.now().format(formatter);

        // Create log entry
        String logEntry = "[" + timestamp + "] Claim #" + claimCount + " added successfully.";

        System.out.println(logEntry);

        // Store full log entry (not just count)
        claimLog.add(logEntry);

        // Update total label
        claimLabel.setText("Total Claims: " + claimCount);

        // Refresh display using FOR loop
        logArea.setText("");
        for (int i = 0; i < claimLog.size(); i++) {
            logArea.append(claimLog.get(i) + "\n");
        }

        // Milestone popup
        if (claimCount % 10 == 0) {

            JPanel celebrationPanel = new JPanel();
            celebrationPanel.setBackground(new Color(255, 230, 150));
            celebrationPanel.setLayout(new BorderLayout(10, 10));

            JLabel message = new JLabel(
                    "🎉 Congratulations! " + claimCount + " Claims Achieved! 🎉",
                    SwingConstants.CENTER
            );
            message.setFont(new Font("Arial", Font.BOLD, 18));
            message.setForeground(new Color(0, 128, 255));

            celebrationPanel.add(message, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(
                    this,
                    celebrationPanel,
                    "Milestone Achievement",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    /**
     * Resets the claim tracker after user confirmation and updates the interface accordingly.
     *
     * This method is triggered when the user clicks the "RESET" button.
     * It first displays a confirmation dialog to prevent accidental data loss.
     * If the user confirms the reset action, the method performs the following steps:
     *
     * 1. Resets the internal claim counter to zero.
     * 2. Clears all stored claim log entries from memory.
     * 3. Updates the total claims label to reflect the reset state.
     * 4. Clears the displayed log area in the GUI.
     * 5. Displays a styled notification panel indicating that all claims
     *    have been successfully reset.
     *
     * The confirmation step ensures safe state management, while the
     * visual feedback reinforces transparency and user awareness.
     * This approach aligns with professional software design practices
     * that prioritize data protection and clear user communication.
     */
    private void resetClaims() {

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to reset all claims?",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            claimCount = 0;
            claimLog.clear();
            claimLabel.setText("Total Claims: 0");
            logArea.setText("");

            // Sad panel
            JPanel sadPanel = new JPanel();
            sadPanel.setBackground(new Color(220, 220, 220));
            sadPanel.setLayout(new BorderLayout(10, 10));

            JLabel sadMessage = new JLabel(
                    "😢 All claims have been reset.",
                    SwingConstants.CENTER
            );
            sadMessage.setFont(new Font("Arial", Font.BOLD, 16));
            sadMessage.setForeground(Color.RED);

            sadPanel.add(sadMessage, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(
                    this,
                    sadPanel,
                    "Tracker Reset",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}