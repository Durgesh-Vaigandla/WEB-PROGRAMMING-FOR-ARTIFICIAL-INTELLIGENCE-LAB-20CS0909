import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class Fee extends JFrame {
    // Components
    private JTextField tfName, tfFather, tfRoll, tfEmail, tfContact, tfNationality, tf10Points, tf12Percent;
    private JTextArea taAddress, taReceipt;
    private JRadioButton rbMale, rbFemale, rbSeas, rbSlabs, rbHosteller, rbDayScholar;
    private JComboBox<String> cb10Year, cb12Year, cbCourse;
    private JList<String> listGroup, listHostel;
    private JLabel lblSelection;

    public Fee() {
        super("Fee Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 850);
        setLayout(null);
        getContentPane().setBackground(Color.CYAN);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Title
        JLabel lblTitle = new JLabel("FEE REPORT");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(450, 20, 200, 30);
        add(lblTitle);

        // Name
        addLabel("Name of the Student:", 50, 80);
        tfName = addTextField(250, 80);

        // Father's Name
        addLabel("Name of the Father:", 50, 130);
        tfFather = addTextField(250, 130);

        // Roll Number
        addLabel("Roll Number:", 50, 180);
        tfRoll = addTextField(250, 180);

        // Email
        addLabel("Email ID:", 50, 230);
        tfEmail = addTextField(250, 230);

        // Contact
        addLabel("Contact Number:", 50, 280);
        tfContact = addTextField(250, 280);

        // Address
        addLabel("Address:", 50, 330);
        taAddress = new JTextArea();
        taAddress.setBounds(250, 330, 300, 60);
        taAddress.setLineWrap(true);
        taAddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(taAddress);

        // Gender
        addLabel("Gender:", 50, 410);
        rbMale = addRadioButton("Male", 250, 410);
        rbFemale = addRadioButton("Female", 350, 410);
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);

        // Nationality
        addLabel("Nationality:", 50, 450);
        tfNationality = addTextField(250, 450);

        // Year of passing 10th
        addLabel("Year of passing 10th:", 50, 490);
        cb10Year = new JComboBox<>(new String[] {"2016", "2015", "2014"});
        cb10Year.setBounds(250, 490, 100, 20);
        add(cb10Year);

        // Year of passing 12th
        addLabel("Year of passing 12th:", 50, 530);
        cb12Year = new JComboBox<>(new String[] {"2019", "2018", "2017"});
        cb12Year.setBounds(250, 530, 100, 20);
        add(cb12Year);

        // Points in 10th
        addLabel("Points Secured in 10th:", 50, 570);
        tf10Points = addTextField(250, 570);

        // Percentage in 12th
        addLabel("Percentage in 12th:", 50, 610);
        tf12Percent = addTextField(250, 610);

        // Course selection
        addLabel("Select Course:", 50, 650);
        cbCourse = new JComboBox<>(new String[] {"CSE", "ECE", "EEE", "CIVIL", "MECH"});
        cbCourse.setBounds(250, 650, 120, 20);
        add(cbCourse);

        // Groups offered
        addLabel("Groups Offered here are:", 600, 80);
        rbSeas = addRadioButton("SEAS", 600, 110);
        rbSlabs = addRadioButton("SLABS", 700, 110);
        ButtonGroup bgGroup = new ButtonGroup();
        bgGroup.add(rbSeas);
        bgGroup.add(rbSlabs);

        // Hostel options
        addLabel("Hostel Options:", 600, 160);
        rbHosteller = addRadioButton("Hosteller", 600, 190);
        rbDayScholar = addRadioButton("Day Scholar", 700, 190);
        ButtonGroup bgHostel = new ButtonGroup();
        bgHostel.add(rbHosteller);
        bgHostel.add(rbDayScholar);

        // Fee Group list
        DefaultListModel<String> lmGroup = new DefaultListModel<>();
        lmGroup.addElement("CSE (2,50,000)");
        lmGroup.addElement("ECE (2,50,000)");
        lmGroup.addElement("EEE (2,50,000)");
        lmGroup.addElement("MECH (2,50,000)");
        lmGroup.addElement("CIVIL (2,50,000)");
        listGroup = new JList<>(lmGroup);
        listGroup.setBorder(BorderFactory.createTitledBorder("Department Fees"));
        listGroup.setBounds(600, 240, 200, 100);
        add(listGroup);

        // Hostel list
        DefaultListModel<String> lmHostel = new DefaultListModel<>();
        lmHostel.addElement("2 SHARE (1,50,000)");
        lmHostel.addElement("3 SHARE (1,40,000)");
        lmHostel.addElement("5 SHARE (1,20,000)");
        lmHostel.addElement("8 SHARE (1,10,000)");
        lmHostel.addElement("Bus (40,000)");
        listHostel = new JList<>(lmHostel);
        listHostel.setBorder(BorderFactory.createTitledBorder("Hostel Fees"));
        listHostel.setBounds(850, 240, 200, 100);
        add(listHostel);

        // Selection label
        lblSelection = new JLabel();
        lblSelection.setBounds(600, 350, 450, 30);
        add(lblSelection);

        // Receipt area
        taReceipt = new JTextArea();
        taReceipt.setBounds(600, 400, 450, 250);
        taReceipt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(taReceipt);

        // Buttons
        JButton btnShow = new JButton("Show Selection");
        btnShow.setBounds(600, 670, 140, 30);
        add(btnShow);

        JButton btnReceipt = new JButton("Generate Receipt");
        btnReceipt.setBounds(760, 670, 140, 30);
        add(btnReceipt);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(920, 670, 80, 30);
        add(btnReset);

        JButton btnPrint = new JButton("Print");
        btnPrint.setBounds(1010, 670, 80, 30);
        add(btnPrint);

        // Action Listeners
        btnShow.addActionListener(e -> updateSelectionLabel());

        btnReceipt.addActionListener(e -> generateReceipt());

        btnReset.addActionListener(e -> resetForm());

        btnPrint.addActionListener(e -> {
            try {
                taReceipt.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error printing: " + ex.getMessage());
            }
        });
    }

    private JLabel addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 200, 20);
        add(lbl);
        return lbl;
    }

    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 200, 20);
        add(tf);
        return tf;
    }

    private JRadioButton addRadioButton(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 100, 20);
        add(rb);
        return rb;
    }

    private void updateSelectionLabel() {
        String text = "";
        if (!listGroup.isSelectionEmpty()) {
            text += "Department: " + listGroup.getSelectedValue() + "  ";
        }
        if (!listHostel.isSelectionEmpty()) {
            text += "Hostel: " + listHostel.getSelectedValue();
        }
        lblSelection.setText(text);
    }

    private void generateReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------FEE RECEIPT-----------\n");
        sb.append("Student Name: ").append(tfName.getText()).append("\n");
        sb.append("Father's Name: ").append(tfFather.getText()).append("\n");
        sb.append("Roll Number: ").append(tfRoll.getText()).append("\n");
        sb.append("Email ID: ").append(tfEmail.getText()).append("\n");
        sb.append("Contact Number: ").append(tfContact.getText()).append("\n");
        sb.append("Address: ").append(taAddress.getText()).append("\n");
        sb.append("Nationality: ").append(tfNationality.getText()).append("\n");
        sb.append("10th Year: ").append(cb10Year.getSelectedItem()).append("  Points: ").append(tf10Points.getText()).append("\n");
        sb.append("12th Year: ").append(cb12Year.getSelectedItem()).append("  %: ").append(tf12Percent.getText()).append("\n");
        sb.append("Course: ").append(cbCourse.getSelectedItem()).append("\n");
        sb.append("Gender: ").append(rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "").append("\n");
        sb.append("Group: ").append(rbSeas.isSelected() ? "SEAS" : rbSlabs.isSelected() ? "SLABS" : "").append("\n");
        sb.append(lblSelection.getText()).append("\n");

        int idx = listHostel.getSelectedIndex();
        String amount;
        switch (idx) {
            case 0: amount = "1.50 Lakhs"; break;
            case 1: amount = "1.40 Lakhs"; break;
            case 2: amount = "1.20 Lakhs"; break;
            case 3: amount = "1.10 Lakhs"; break;
            case 4: amount = "0.40 Lakhs"; break;
            default: amount = "N/A";
        }
        sb.append("Hostel Fee: ").append(amount).append("\n");

        taReceipt.setText(sb.toString());

        try (FileWriter fw = new FileWriter("fee_receipt.txt", true)) {
            fw.write(sb.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving receipt: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(this, "Receipt Generated and Saved.");
    }

    private void resetForm() {
        tfName.setText("");
        tfFather.setText("");
        tfRoll.setText("");
        tfEmail.setText("");
        tfContact.setText("");
        taAddress.setText("");
        tfNationality.setText("");
        tf10Points.setText("");
        tf12Percent.setText("");
        cb10Year.setSelectedIndex(0);
        cb12Year.setSelectedIndex(0);
        cbCourse.setSelectedIndex(0);
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
        rbSeas.setSelected(false);
        rbSlabs.setSelected(false);
        rbHosteller.setSelected(false);
        rbDayScholar.setSelected(false);
        listGroup.clearSelection();
        listHostel.clearSelection();
        lblSelection.setText("");
        taReceipt.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Fee::new);
    }
}
