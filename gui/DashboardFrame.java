package gui;

import model.Account;
import service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

        private final Account account;
        private final BankService bankService;

        private JLabel balanceLabel;

        public DashboardFrame(Account account, BankService bankService) {

                this.account = account;
                this.bankService = bankService;

                setTitle("Bank Dashboard");
                setSize(700, 600);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);

                // Main background
                JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
                mainPanel.setBackground(new Color(240, 244, 248));
                mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

                // ================= HEADER =================

                JPanel headerPanel = new JPanel(new BorderLayout());
                headerPanel.setBackground(Color.WHITE);
                headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

                JPanel titlePanel = new JPanel();
                titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
                titlePanel.setBackground(Color.WHITE);

                JLabel bankName = new JLabel("ROYAL TRUST BANK");
                bankName.setFont(new Font("Arial", Font.BOLD, 24));
                bankName.setForeground(new Color(25, 55, 95));

                JLabel dashboardTitle = new JLabel("BANKING DASHBOARD");
                dashboardTitle.setFont(new Font("Arial", Font.BOLD, 16));
                dashboardTitle.setForeground(new Color(70, 70, 70));

                titlePanel.add(bankName);
                titlePanel.add(Box.createVerticalStrut(3));
                titlePanel.add(dashboardTitle);

                JLabel welcomeLabel = new JLabel("Welcome, " + account.getName());

                welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
                welcomeLabel.setForeground(new Color(50, 60, 70));

                headerPanel.add(titlePanel, BorderLayout.WEST);
                headerPanel.add(welcomeLabel, BorderLayout.EAST);

                // ================= BALANCE CARD =================

                JPanel balanceCard = new JPanel();
                balanceCard.setLayout(new BoxLayout(balanceCard, BoxLayout.Y_AXIS));
                balanceCard.setBackground(new Color(25, 55, 95));
                balanceCard.setBorder(new EmptyBorder(20, 20, 20, 20));

                JLabel balanceTitle = new JLabel("AVAILABLE BALANCE");
                balanceTitle.setFont(new Font("Arial", Font.BOLD, 14));
                balanceTitle.setForeground(Color.WHITE);
                balanceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                balanceLabel = new JLabel(
                                "₹" + String.format("%.2f", account.getBalance()));

                balanceLabel.setFont(new Font("Arial", Font.BOLD, 30));
                balanceLabel.setForeground(Color.WHITE);
                balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel accountNumberLabel = new JLabel(
                                "Account No: " + account.getAccountNumber());

                accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                accountNumberLabel.setForeground(new Color(220, 230, 240));
                accountNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                balanceCard.add(balanceTitle);
                balanceCard.add(Box.createVerticalStrut(8));
                balanceCard.add(balanceLabel);
                balanceCard.add(Box.createVerticalStrut(8));
                balanceCard.add(accountNumberLabel);

                // ================= BUTTON PANEL =================

                JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 15, 15));
                buttonPanel.setBackground(new Color(240, 244, 248));

                JButton depositButton = new JButton("DEPOSIT");
                JButton withdrawButton = new JButton("WITHDRAW");
                JButton transferButton = new JButton("TRANSFER");
                JButton accountInfoButton = new JButton("ACCOUNT INFO");
                JButton transactionButton = new JButton("TRANSACTIONS");
                JButton logoutButton = new JButton("LOGOUT");

                styleButton(
                                depositButton,
                                new Color(50, 110, 90),
                                Color.WHITE);

                styleButton(
                                withdrawButton,
                                new Color(180, 120, 50),
                                Color.WHITE);

                styleButton(
                                transferButton,
                                new Color(25, 55, 95),
                                Color.WHITE);

                styleButton(
                                accountInfoButton,
                                new Color(80, 90, 105),
                                Color.WHITE);

                styleButton(
                                transactionButton,
                                new Color(100, 80, 130),
                                Color.WHITE);

                styleButton(
                                logoutButton,
                                new Color(190, 65, 65),
                                Color.WHITE);

                buttonPanel.add(depositButton);
                buttonPanel.add(withdrawButton);
                buttonPanel.add(transferButton);
                buttonPanel.add(accountInfoButton);
                buttonPanel.add(transactionButton);
                buttonPanel.add(logoutButton);

                // ================= CUSTOMER INFO =================

                JPanel infoPanel = new JPanel(new FlowLayout(
                                FlowLayout.CENTER, 25, 5));

                infoPanel.setBackground(Color.WHITE);
                infoPanel.setBorder(
                                BorderFactory.createEmptyBorder(5, 10, 5, 10));

                JLabel typeLabel = new JLabel(
                                "Account Type: " + account.getAccountType());

                JLabel phoneLabel = new JLabel(
                                "Phone: " + account.getPhone());

                typeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                phoneLabel.setFont(new Font("Arial", Font.PLAIN, 13));

                typeLabel.setForeground(new Color(80, 80, 80));
                phoneLabel.setForeground(new Color(80, 80, 80));

                infoPanel.add(typeLabel);
                infoPanel.add(phoneLabel);

                // ================= ADD TO MAIN =================

                mainPanel.add(headerPanel, BorderLayout.NORTH);

                JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
                centerPanel.setBackground(new Color(240, 244, 248));

                centerPanel.add(balanceCard, BorderLayout.NORTH);
                centerPanel.add(buttonPanel, BorderLayout.CENTER);
                centerPanel.add(infoPanel, BorderLayout.SOUTH);

                mainPanel.add(centerPanel, BorderLayout.CENTER);

                add(mainPanel);

                // ================= BUTTON ACTIONS =================

                depositButton.addActionListener(e -> {

                        new DepositFrame(account, bankService, () -> {

                                balanceLabel.setText(
                                                "₹" + String.format(
                                                                "%.2f",
                                                                account.getBalance()));

                        });
                });

                withdrawButton.addActionListener(e -> {

                        new WithdrawFrame(account, bankService, () -> {

                                balanceLabel.setText(
                                                "₹" + String.format(
                                                                "%.2f",
                                                                account.getBalance()));

                        });
                });

                transferButton.addActionListener(e -> {

                        new TransferFrame(account, bankService, () -> {

                                balanceLabel.setText(
                                                "₹" + String.format(
                                                                "%.2f",
                                                                account.getBalance()));

                        });
                });

                accountInfoButton.addActionListener(e -> {
                        new AccountInfoFrame(account);
                });

                transactionButton.addActionListener(e -> {
                        new TransactionFrame(account);
                });

                logoutButton.addActionListener(e -> {

                        int choice = JOptionPane.showConfirmDialog(
                                        this,
                                        "Are you sure you want to logout?",
                                        "Logout",
                                        JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                                dispose();
                        }
                });

                setVisible(true);
        }

        private void styleButton(
                        JButton button,
                        Color background,
                        Color foreground) {

                button.setFont(
                                new Font("Arial", Font.BOLD, 14));

                button.setBackground(background);
                button.setForeground(foreground);

                button.setFocusPainted(false);
                button.setBorderPainted(false);

                button.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));
        }
}
