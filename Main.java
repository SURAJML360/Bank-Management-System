import gui.CreateAccountFrame;
import gui.LoginFrame;
import service.BankService;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // One BankService for the entire application
            BankService bankService = new BankService();

            JFrame frame = new JFrame("Bank Management System");

            frame.setSize(550, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel mainPanel = new JPanel(new GridBagLayout());
            mainPanel.setBackground(new Color(240, 244, 248));

            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setBorder(
                    BorderFactory.createEmptyBorder(35, 50, 35, 50)
            );

            JLabel bankName = new JLabel("ROYAL TRUST BANK");
            bankName.setFont(new Font("Arial", Font.BOLD, 28));
            bankName.setForeground(new Color(25, 55, 95));
            bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel systemTitle =
                    new JLabel("BANK MANAGEMENT SYSTEM");

            systemTitle.setFont(new Font("Arial", Font.BOLD, 20));
            systemTitle.setForeground(new Color(60, 70, 80));
            systemTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel welcome =
                    new JLabel("Welcome to Royal Trust Bank");

            welcome.setFont(new Font("Arial", Font.BOLD, 16));
            welcome.setForeground(new Color(70, 70, 70));
            welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel subtitle =
                    new JLabel("Secure • Simple • Reliable Banking");

            subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
            subtitle.setForeground(Color.GRAY);
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton loginButton = new JButton("LOGIN");
            JButton createButton = new JButton("CREATE ACCOUNT");
            JButton exitButton = new JButton("EXIT");

            styleButton(
                    loginButton,
                    new Color(25, 55, 95),
                    Color.WHITE
            );

            styleButton(
                    createButton,
                    new Color(50, 110, 90),
                    Color.WHITE
            );

            styleButton(
                    exitButton,
                    new Color(220, 225, 230),
                    Color.BLACK
            );

            cardPanel.add(bankName);
            cardPanel.add(Box.createVerticalStrut(8));

            cardPanel.add(systemTitle);
            cardPanel.add(Box.createVerticalStrut(20));

            cardPanel.add(welcome);
            cardPanel.add(Box.createVerticalStrut(5));

            cardPanel.add(subtitle);
            cardPanel.add(Box.createVerticalStrut(35));

            cardPanel.add(loginButton);
            cardPanel.add(Box.createVerticalStrut(15));

            cardPanel.add(createButton);
            cardPanel.add(Box.createVerticalStrut(15));

            cardPanel.add(exitButton);

            cardPanel.add(Box.createVerticalStrut(25));

            JLabel footer =
                    new JLabel("Your trusted banking partner");

            footer.setFont(new Font("Arial", Font.ITALIC, 12));
            footer.setForeground(Color.GRAY);
            footer.setAlignmentX(Component.CENTER_ALIGNMENT);

            cardPanel.add(footer);

            mainPanel.add(cardPanel);
            frame.add(mainPanel);

            // Login
            loginButton.addActionListener(e -> {
                new LoginFrame(bankService);
            });

            // Create Account
            createButton.addActionListener(e -> {
                new CreateAccountFrame(bankService);
            });

            // Exit
            exitButton.addActionListener(e -> {

                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit?",
                        "Exit Bank Management System",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            });

            frame.setVisible(true);
        });
    }

    private static void styleButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setPreferredSize(new Dimension(250, 45));
        button.setMaximumSize(new Dimension(250, 45));
        button.setMinimumSize(new Dimension(250, 45));

        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setBackground(background);
        button.setForeground(foreground);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}