package gui;

import model.Account;
import service.BankService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateAccountFrame extends JFrame {

        private final BankService bankService;

        public CreateAccountFrame(BankService bankService) {

                this.bankService = bankService;

                setTitle("Create Bank Account");
                setSize(600, 650);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);

                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBackground(new Color(240, 244, 248));

                JPanel cardPanel = new JPanel(new BorderLayout(10, 15));
                cardPanel.setBackground(Color.WHITE);
                cardPanel.setBorder(
                                new EmptyBorder(30, 40, 30, 40));

                // ---------- HEADER ----------

                JPanel headerPanel = new JPanel();
                headerPanel.setLayout(
                                new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
                headerPanel.setBackground(Color.WHITE);

                JLabel bankName = new JLabel("ROYAL TRUST BANK");
                bankName.setFont(new Font("Arial", Font.BOLD, 26));
                bankName.setForeground(new Color(25, 55, 95));
                bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel title = new JLabel("CREATE NEW ACCOUNT");
                title.setFont(new Font("Arial", Font.BOLD, 20));
                title.setForeground(new Color(60, 70, 80));
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel subtitle = new JLabel("Enter your details to open an account");

                subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
                subtitle.setForeground(Color.GRAY);
                subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

                headerPanel.add(bankName);
                headerPanel.add(Box.createVerticalStrut(5));
                headerPanel.add(title);
                headerPanel.add(Box.createVerticalStrut(5));
                headerPanel.add(subtitle);

                // ---------- FORM ----------

                JPanel formPanel = new JPanel(new GridBagLayout());
                formPanel.setBackground(Color.WHITE);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(8, 8, 8, 8);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel nameLabel = new JLabel("Full Name");
                JTextField nameField = new JTextField();

                JLabel ageLabel = new JLabel("Age");
                JTextField ageField = new JTextField();

                JLabel phoneLabel = new JLabel("Phone Number");
                JTextField phoneField = new JTextField();

                JLabel addressLabel = new JLabel("Address");

                JTextArea addressArea = new JTextArea(3, 20);
                addressArea.setLineWrap(true);
                addressArea.setWrapStyleWord(true);

                JScrollPane addressScroll = new JScrollPane(addressArea);

                JLabel typeLabel = new JLabel("Account Type");

                String[] accountTypes = {
                                "Savings Account",
                                "Current Account"
                };

                JComboBox<String> typeBox = new JComboBox<>(accountTypes);

                JLabel pinLabel = new JLabel("Create PIN");
                JPasswordField pinField = new JPasswordField();

                JLabel confirmPinLabel = new JLabel("Confirm PIN");
                JPasswordField confirmPinField = new JPasswordField();

                JLabel[] labels = {
                                nameLabel,
                                ageLabel,
                                phoneLabel,
                                addressLabel,
                                typeLabel,
                                pinLabel,
                                confirmPinLabel
                };

                for (JLabel label : labels) {
                        label.setFont(new Font("Arial", Font.BOLD, 14));
                        label.setForeground(
                                        new Color(50, 60, 70));
                }

                JTextField[] fields = {
                                nameField,
                                ageField,
                                phoneField
                };

                for (JTextField field : fields) {

                        field.setPreferredSize(
                                        new Dimension(240, 35));

                        field.setFont(
                                        new Font("Arial", Font.PLAIN, 14));
                }

                pinField.setPreferredSize(
                                new Dimension(240, 35));

                confirmPinField.setPreferredSize(
                                new Dimension(240, 35));

                typeBox.setPreferredSize(
                                new Dimension(240, 35));

                // Name
                addRow(formPanel, gbc, 0,
                                nameLabel, nameField);

                // Age
                addRow(formPanel, gbc, 1,
                                ageLabel, ageField);

                // Phone
                addRow(formPanel, gbc, 2,
                                phoneLabel, phoneField);

                // Address
                gbc.gridx = 0;
                gbc.gridy = 3;
                formPanel.add(addressLabel, gbc);

                gbc.gridx = 1;
                formPanel.add(addressScroll, gbc);

                // Account type
                addRow(formPanel, gbc, 4,
                                typeLabel, typeBox);

                // PIN
                addRow(formPanel, gbc, 5,
                                pinLabel, pinField);

                // Confirm PIN
                addRow(formPanel, gbc, 6,
                                confirmPinLabel, confirmPinField);

                // ---------- BUTTONS ----------

                JButton createButton = new JButton("CREATE ACCOUNT");

                JButton resetButton = new JButton("RESET");

                JButton backButton = new JButton("BACK");

                styleButton(
                                createButton,
                                new Color(50, 110, 90),
                                Color.WHITE);

                styleButton(
                                resetButton,
                                new Color(220, 225, 230),
                                Color.BLACK);

                styleButton(
                                backButton,
                                new Color(25, 55, 95),
                                Color.WHITE);

                JPanel buttonPanel = new JPanel(
                                new FlowLayout(
                                                FlowLayout.CENTER,
                                                12,
                                                0));

                buttonPanel.setBackground(Color.WHITE);

                buttonPanel.add(createButton);
                buttonPanel.add(resetButton);
                buttonPanel.add(backButton);

                JLabel footer = new JLabel("Your information is kept secure.");

                footer.setFont(
                                new Font("Arial", Font.ITALIC, 12));

                footer.setForeground(Color.GRAY);
                footer.setHorizontalAlignment(
                                SwingConstants.CENTER);

                cardPanel.add(
                                headerPanel,
                                BorderLayout.NORTH);

                cardPanel.add(
                                formPanel,
                                BorderLayout.CENTER);

                JPanel bottomPanel = new JPanel(new BorderLayout(0, 12));

                bottomPanel.setBackground(Color.WHITE);

                bottomPanel.add(
                                buttonPanel,
                                BorderLayout.CENTER);

                bottomPanel.add(
                                footer,
                                BorderLayout.SOUTH);

                cardPanel.add(
                                bottomPanel,
                                BorderLayout.SOUTH);

                mainPanel.add(cardPanel);
                add(mainPanel);

                // ---------- CREATE ACCOUNT ----------

                createButton.addActionListener(e -> {

                        String name = nameField.getText().trim();

                        String ageText = ageField.getText().trim();

                        String phone = phoneField.getText().trim();

                        String address = addressArea.getText().trim();

                        String accountType = (String) typeBox.getSelectedItem();

                        String pin = new String(
                                        pinField.getPassword());

                        String confirmPin = new String(
                                        confirmPinField.getPassword());

                        // Empty fields
                        if (name.isEmpty()
                                        || ageText.isEmpty()
                                        || phone.isEmpty()
                                        || address.isEmpty()
                                        || pin.isEmpty()
                                        || confirmPin.isEmpty()) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Please fill all the fields.",
                                                "Incomplete Information",
                                                JOptionPane.WARNING_MESSAGE);

                                return;
                        }

                        // Age validation
                        int age;

                        try {
                                age = Integer.parseInt(ageText);
                        } catch (NumberFormatException ex) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Age must be a valid number.",
                                                "Invalid Age",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // Phone validation
                        if (!phone.matches("\\d{10}")) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Phone number must contain 10 digits.",
                                                "Invalid Phone Number",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // PIN validation
                        if (!pin.matches("\\d{4}")) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "PIN must contain exactly 4 digits.",
                                                "Invalid PIN",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // Confirm PIN
                        if (!pin.equals(confirmPin)) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "PIN and Confirm PIN do not match.",
                                                "PIN Error",
                                                JOptionPane.ERROR_MESSAGE);

                                return;
                        }

                        // Create real account
                        Account account = bankService.createAccount(
                                        name,
                                        age,
                                        phone,
                                        address,
                                        accountType,
                                        pin);
                        // Save account permanently
                        boolean saved = bankService.saveData();
                        // Show account number
                        JOptionPane.showMessageDialog(
                                        this,
                                        (saved
                                                        ? "Account created and saved successfully!"
                                                        : "Account created, but permanent saving failed.")
                                                        + "\n\nAccount Number: "
                                                        + account.getAccountNumber()
                                                        + "\nAccount Holder: "
                                                        + account.getName()
                                                        + "\nAccount Type: "
                                                        + account.getAccountType()
                                                        + "\n\nPlease remember your account number.",
                                        saved ? "Account Created" : "Saving Error",
                                        saved
                                                        ? JOptionPane.INFORMATION_MESSAGE
                                                        : JOptionPane.WARNING_MESSAGE);

                        // Clear form
                        nameField.setText("");
                        ageField.setText("");
                        phoneField.setText("");
                        addressArea.setText("");
                        typeBox.setSelectedIndex(0);
                        pinField.setText("");
                        confirmPinField.setText("");
                });

                // Reset
                resetButton.addActionListener(e -> {

                        nameField.setText("");
                        ageField.setText("");
                        phoneField.setText("");
                        addressArea.setText("");
                        typeBox.setSelectedIndex(0);
                        pinField.setText("");
                        confirmPinField.setText("");
                });

                // Back
                backButton.addActionListener(e -> dispose());

                setVisible(true);
        }

        private void addRow(
                        JPanel panel,
                        GridBagConstraints gbc,
                        int row,
                        Component label,
                        Component field) {

                gbc.gridx = 0;
                gbc.gridy = row;
                gbc.weightx = 0;

                panel.add(label, gbc);

                gbc.gridx = 1;
                gbc.weightx = 1;

                panel.add(field, gbc);
        }

        private void styleButton(
                        JButton button,
                        Color background,
                        Color foreground) {

                button.setPreferredSize(
                                new Dimension(150, 42));

                button.setFont(
                                new Font("Arial", Font.BOLD, 13));

                button.setBackground(background);
                button.setForeground(foreground);

                button.setFocusPainted(false);
                button.setBorderPainted(false);
        }
}
