package gui;

import model.Account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AccountInfoFrame extends JFrame {

    private final Account account;

    public AccountInfoFrame(Account account) {

        this.account = account;

        // ================= FRAME SETTINGS =================

        setTitle("Account Information - Royal Trust Bank");
        setSize(760, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(new Color(240, 244, 248));

        mainPanel.setBorder(
                new EmptyBorder(18, 18, 18, 18)
        );

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        headerPanel.setBackground(new Color(25, 55, 95));

        headerPanel.setBorder(
                new EmptyBorder(18, 20, 18, 20)
        );

        JLabel bankName = new JLabel("ROYAL TRUST BANK");

        bankName.setFont(
                new Font("Arial", Font.BOLD, 27)
        );

        bankName.setForeground(Color.WHITE);

        bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("ACCOUNT INFORMATION");

        title.setFont(
                new Font("Arial", Font.BOLD, 19)
        );

        title.setForeground(new Color(220, 230, 240));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Your account details at a glance"
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitle.setForeground(new Color(220, 230, 240));

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(bankName);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(title);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitle);

        // ================= ACCOUNT DETAILS CARD =================

        JPanel detailsCard = new JPanel(
                new BorderLayout(0, 12)
        );

        detailsCard.setBackground(Color.WHITE);

        detailsCard.setBorder(
                new EmptyBorder(18, 22, 18, 22)
        );

        JLabel sectionTitle = new JLabel(
                "PERSONAL & ACCOUNT DETAILS"
        );

        sectionTitle.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        sectionTitle.setForeground(
                new Color(25, 55, 95)
        );

        // ================= DETAILS GRID =================

        JPanel detailsPanel = new JPanel(
                new GridLayout(3, 2, 12, 12)
        );

        detailsPanel.setBackground(Color.WHITE);

        // Customer name
        detailsPanel.add(
                createDetailCard(
                        "ACCOUNT HOLDER",
                        account.getName()
                )
        );

        // Account number
        detailsPanel.add(
                createDetailCard(
                        "ACCOUNT NUMBER",
                        account.getAccountNumber()
                )
        );

        // Customer age
        detailsPanel.add(
                createDetailCard(
                        "AGE",
                        String.valueOf(account.getAge())
                )
        );

        // Phone number
        detailsPanel.add(
                createDetailCard(
                        "PHONE NUMBER",
                        account.getPhone()
                )
        );

        // Account type
        detailsPanel.add(
                createDetailCard(
                        "ACCOUNT TYPE",
                        account.getAccountType()
                )
        );

        // Account status
        detailsPanel.add(
                createDetailCard(
                        "ACCOUNT STATUS",
                        "Active"
                )
        );

        // Reserve sufficient space for all six cards
        detailsPanel.setPreferredSize(
                new Dimension(650, 205)
        );

        // ================= ADDRESS CARD =================

        JPanel addressPanel = new JPanel();

        addressPanel.setLayout(
                new BoxLayout(addressPanel, BoxLayout.Y_AXIS)
        );

        addressPanel.setBackground(
                new Color(247, 249, 252)
        );

        addressPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 228, 238)
                        ),
                        new EmptyBorder(12, 15, 12, 15)
                )
        );

        JLabel addressHeading = new JLabel(
                "REGISTERED ADDRESS"
        );

        addressHeading.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        addressHeading.setForeground(
                new Color(100, 110, 125)
        );

        JLabel addressValue = new JLabel(
                account.getAddress() == null
                        || account.getAddress().trim().isEmpty()
                        ? "Not provided"
                        : account.getAddress()
        );

        addressValue.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        addressValue.setForeground(
                new Color(35, 50, 70)
        );

        addressPanel.add(addressHeading);

        addressPanel.add(
                Box.createVerticalStrut(7)
        );

        addressPanel.add(addressValue);

        // Add components to details card
        detailsCard.add(
                sectionTitle,
                BorderLayout.NORTH
        );

        detailsCard.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        detailsCard.add(
                addressPanel,
                BorderLayout.SOUTH
        );

        // ================= BALANCE CARD =================

        JPanel balanceCard = new JPanel();

        balanceCard.setLayout(
                new BoxLayout(balanceCard, BoxLayout.Y_AXIS)
        );

        balanceCard.setBackground(
                new Color(50, 110, 90)
        );

        balanceCard.setBorder(
                new EmptyBorder(14, 20, 14, 20)
        );

        JLabel balanceHeading = new JLabel(
                "CURRENT ACCOUNT BALANCE"
        );

        balanceHeading.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        balanceHeading.setForeground(Color.WHITE);

        balanceHeading.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel balanceAmount = new JLabel(
                "₹" + String.format(
                        "%.2f",
                        account.getBalance()
                )
        );

        balanceAmount.setFont(
                new Font("Arial", Font.BOLD, 29)
        );

        balanceAmount.setForeground(Color.WHITE);

        balanceAmount.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        balanceCard.add(balanceHeading);

        balanceCard.add(
                Box.createVerticalStrut(5)
        );

        balanceCard.add(balanceAmount);

        // ================= CENTER CONTENT =================

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(
                new BoxLayout(centerPanel, BoxLayout.Y_AXIS)
        );

        centerPanel.setBackground(
                new Color(240, 244, 248)
        );

        detailsCard.setAlignmentX(Component.LEFT_ALIGNMENT);
        balanceCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPanel.add(detailsCard);

        centerPanel.add(
                Box.createVerticalStrut(12)
        );

        centerPanel.add(balanceCard);

        // ================= FOOTER =================

        JButton closeButton = new JButton("CLOSE");

        styleButton(
                closeButton,
                new Color(25, 55, 95),
                Color.WHITE
        );

        JPanel footerPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        footerPanel.setBackground(
                new Color(240, 244, 248)
        );

        footerPanel.add(closeButton);

        // ================= ASSEMBLE WINDOW =================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ================= CLOSE BUTTON =================

        closeButton.addActionListener(e -> dispose());

        // Display window
        setVisible(true);
    }

    // ================= DETAIL CARD METHOD =================

    private JPanel createDetailCard(
            String heading,
            String value) {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        panel.setBackground(
                new Color(247, 249, 252)
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 228, 238)
                        ),
                        new EmptyBorder(9, 12, 9, 12)
                )
        );

        JLabel headingLabel = new JLabel(heading);

        headingLabel.setFont(
                new Font("Arial", Font.BOLD, 11)
        );

        headingLabel.setForeground(
                new Color(100, 110, 125)
        );

        headingLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        String displayValue =
                value == null || value.trim().isEmpty()
                        ? "Not provided"
                        : value;

        JLabel valueLabel = new JLabel(displayValue);

        valueLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        valueLabel.setForeground(
                new Color(35, 50, 70)
        );

        valueLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(headingLabel);

        panel.add(
                Box.createVerticalStrut(6)
        );

        panel.add(valueLabel);

        return panel;
    }

    // ================= BUTTON STYLING =================

    private void styleButton(
            JButton button,
            Color background,
            Color foreground) {

        button.setPreferredSize(
                new Dimension(150, 42)
        );

        button.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        button.setBackground(background);

        button.setForeground(foreground);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}