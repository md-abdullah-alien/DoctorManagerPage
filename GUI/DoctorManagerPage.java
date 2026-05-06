package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class DoctorManagerPage extends JFrame implements ActionListener {

    Font font15 = new Font("Arial", Font.BOLD, 18);

    JLabel title, doctorNoLabel, doctorNameLabel;
    JLabel patientDoctorNoLabel, positionLabel, nameLabel, idLabel, billLabel;

    JButton createDoctorButton, removeDoctorButton;
    JButton createPatientButton, removePatientButton;
    JButton clearDoctorButton, clearPatientButton;
    JButton saveButton;

    JTextField doctorNoField, doctorNameField;
    JTextField patientDoctorNoField, positionField, nameField, idField, billField;

    JTextArea screen;

    Doctor[] doctors = new Doctor[10];

    public DoctorManagerPage() {

        super("AIUB Hospital Management System");

        this.setSize(800, 650);
        this.setLocation(200, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
		this.setIconImage(new ImageIcon("./images/AIUB.png").getImage());
		this.setBackground(Color.GREEN);
		this.setForeground(Color.RED);
		this.setResizable(false);
		
        //this.setLayout(new FlowLayout());

        // Load Data
        FileIO.loadFromFile(doctors);
				
        // Title
        title = createLabel(250, 10, 300, 30, "AIUB Hospital Management");
		title.setForeground(Color.BLUE);

        // Doctor Section
        doctorNoLabel = createLabel(20, 60, 120, 30, "Chembar No:");
        doctorNoField = createTextField(150, 60, 120, 30, "");

        doctorNameLabel = createLabel(20, 100, 120, 30, "Doctor Name:");
        doctorNameField = createTextField(150, 100, 120, 30, "");
		
        createDoctorButton = createButton(20, 140, 250, 30, "Create Doctor");
		createDoctorButton.setBackground(Color.GREEN);
		createDoctorButton.setForeground(Color.RED);
		
        removeDoctorButton = createButton(20, 180, 250, 30, "Remove Doctor");
		removeDoctorButton.setBackground(Color.GREEN);
		removeDoctorButton.setForeground(Color.RED);
		
        clearDoctorButton = createButton(20, 220, 250, 30, "Clear");
		clearDoctorButton.setBackground(Color.GREEN);
		clearDoctorButton.setForeground(Color.RED);
		
        // Patient Section
        patientDoctorNoLabel = createLabel(20, 270, 120, 30, "Chembar No:");
        patientDoctorNoField = createTextField(150, 270, 120, 30, "");

        positionLabel = createLabel(20, 310, 120, 30, "Serial No:");
        positionField = createTextField(150, 310, 120, 30, "");

        nameLabel = createLabel(20, 350, 120, 30, "Patient ID:");
        nameField = createTextField(150, 350, 120, 30, "");

        idLabel = createLabel(20, 390, 130, 30, "Patient Name:");
        idField = createTextField(150, 390, 120, 30, "");

        billLabel = createLabel(20, 430, 120, 30, "Bill:");
        billField = createTextField(150, 430, 120, 30, "");

        createPatientButton = createButton(20, 470, 250, 30, "Add Patient");
		createPatientButton.setBackground(Color.GREEN);
		createPatientButton.setForeground(Color.RED);
		
        removePatientButton = createButton(20, 510, 250, 30, "Remove Patient");
		removePatientButton.setBackground(Color.GREEN);
		removePatientButton.setForeground(Color.RED);
		
        clearPatientButton = createButton(20, 550, 250, 30, "Clear");
		clearPatientButton.setBackground(Color.GREEN);
		clearPatientButton.setForeground(Color.RED);
		
        // Save Button
        saveButton = createButton(600, 10, 150, 30, "Save");
		saveButton.setBackground(Color.GREEN);
		saveButton.setForeground(Color.RED);

        // Screen
        screen = new JTextArea();
        screen.setFont(font15);

        JScrollPane sp = new JScrollPane(screen);
        sp.setBounds(300, 60, 460, 500);
        this.add(sp);

        updateScreen();

        this.setVisible(true);
    }

    // Helper Methods
    JLabel createLabel(int x, int y, int w, int h, String text) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, w, h);
        l.setFont(font15);
        this.add(l);
        return l;
    }

    JTextField createTextField(int x, int y, int w, int h, String text) {
        JTextField t = new JTextField(text);
        t.setBounds(x, y, w, h);
        t.setFont(font15);
        this.add(t);
        return t;
    }

    JButton createButton(int x, int y, int w, int h, String text) {
        JButton b = new JButton(text);
        b.setBounds(x, y, w, h);
        b.setFont(font15);
        b.addActionListener(this);
        this.add(b);
        return b;
    }

    void updateScreen() {
        String data = "";
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i] != null) {
                data += i + ". " + doctors[i].getDoctor() + "\n";
            }
        }
        screen.setText(data);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Create Doctor
        if (e.getSource() == createDoctorButton) {
            int no = Integer.parseInt(doctorNoField.getText());
            String name = doctorNameField.getText();

            if (doctors[no] == null) {
                doctors[no] = new Doctor(name);
                updateScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Doctor already exists!");
            }
        }

        // Remove Doctor
        else if (e.getSource() == removeDoctorButton) {
            int no = Integer.parseInt(doctorNoField.getText());
            doctors[no] = null;
            updateScreen();
        }

        // Add Patient
        else if (e.getSource() == createPatientButton) {
            int dNo = Integer.parseInt(patientDoctorNoField.getText());
            int pos = Integer.parseInt(positionField.getText());

            String name = nameField.getText();
            int id = Integer.parseInt(idField.getText());
            double bill = Double.parseDouble(billField.getText());

            Patient p = new Patient(name, id, bill);
            doctors[dNo].insertPatient(pos, p);

            updateScreen();
        }

        // Remove Patient
        else if (e.getSource() == removePatientButton) {
            int dNo = Integer.parseInt(patientDoctorNoField.getText());
            int pos = Integer.parseInt(positionField.getText());

            doctors[dNo].removePatient(pos);
            updateScreen();
        }

        // Clear Fields
        else if (e.getSource() == clearDoctorButton) {
            doctorNoField.setText("");
            doctorNameField.setText("");
        }

        else if (e.getSource() == clearPatientButton) {
            patientDoctorNoField.setText("");
            positionField.setText("");
            nameField.setText("");
            idField.setText("");
            billField.setText("");
        }

        // Save
        else if (e.getSource() == saveButton) {
            FileIO.saveChangesInFile(doctors);
            JOptionPane.showMessageDialog(this, "Saved!");
        }
    }
}