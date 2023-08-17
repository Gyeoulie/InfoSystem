/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infosystem;

/**
 *
 * @author Khen
 */
import javax.swing.*;
import java.awt.event.*;

public class frmSubjects extends JFrame implements ActionListener {

    //-----------------------INITIALIZE-----------------------
    //DBMODEL CLASS
    DBModel courseRecord = new DBModel();

    //LABELS
    JLabel lblSubject = new JLabel("Subject Code");
    JLabel lblDesc = new JLabel("Description");
    JLabel lblUnits = new JLabel("Units");
    JLabel lblCategory = new JLabel("Category");

    //TEXT FIELDS
    JTextField txtSubject = new JTextField(25);
    JTextField txtDesc = new JTextField(15);
    JTextField txtUnits = new JTextField(25);

    //COMBO BOX
    String[] val = {"Major", "Minor"};
    JComboBox cbbCategory = new JComboBox(val);

    //SCROLL PANE - TABLE
    JScrollPane sp1 = new JScrollPane();
    JTable tblSubj = new JTable();

    //BUTTONS
    JButton btnSave = new JButton("Save");
    JButton btnCancel = new JButton("Cancel");

    public frmSubjects() {
        super("Subjects");
        setLayout(null);

        //-----------------------LABEL FIELDS-----------------------
        //SUBJECT LABEL
        lblSubject.setBounds(100, 30, 200, 30);
        add(lblSubject);
        //DESCRIPTION LABEL
        lblDesc.setBounds(100, 65, 200, 30);
        add(lblDesc);
        //UNIT LABEL
        lblUnits.setBounds(100, 100, 200, 30);
        add(lblUnits);
        //CATEGORY LABEL
        lblCategory.setBounds(100, 135, 200, 30);
        add(lblCategory);

        //-----------------------TEXT FIELDS-----------------------
        //SUBJECT TEXTFIELD
        txtSubject.setBounds(220, 30, 200, 30);
        add(txtSubject);
        //DESCRIPTION TEXTFIELD
        txtDesc.setBounds(220, 65, 200, 30);
        add(txtDesc);
        //UNITS TEXTFIELD
        txtUnits.setBounds(220, 100, 200, 30);
        add(txtUnits);

        //CATEGORY COMBOBOX
        cbbCategory.setBounds(220, 135, 200, 30);
        add(cbbCategory);

        //-----------------------TABLE-----------------------
        //SCROLL PANE - TABLE - SUBJECTLIST
        sp1.setViewportView(tblSubj);
        sp1.setBounds(100, 205, 350, 280);
        setSize(200, 200);
        setVisible(true);
        add(sp1);

        //SET THE TABLE TO NOT BE EDITABLE/CLICKABLE
        tblSubj.setCellSelectionEnabled(false);
        tblSubj.setRowSelectionAllowed(false);
        tblSubj.setColumnSelectionAllowed(false);
        tblSubj.setEnabled(false);

        //POPULATE THE TABLE
        courseRecord.subjectPopulate(tblSubj);

        //-----------------------BUTTONS-----------------------
        //SAVE BUTTON
        btnSave.setBounds(165, 500, 100, 30);
        add(btnSave);
        //CANCEL BUTTON
        btnCancel.setBounds(285, 500, 100, 30);
        add(btnCancel);

        //-----------------------ACTION LISTENERS-----------------------
        //SAVE BUTTON
        btnSave.addActionListener(this);
        //CANCEL BUTTON
        btnCancel.addActionListener(this);
    }

    //ACTION PERFORMED METHOD
    @Override
    public void actionPerformed(ActionEvent e) {

        //CHECKS IF SAVE BUTTON IS PRESSED
        if (e.getSource() == btnSave) {
            //CHECKS IF THE FIELDS ARE EMPTY
            if (txtSubject.getText().equals("") || txtDesc.getText().equals("") || txtUnits.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "All Fields are Required!", "Subjects", JOptionPane.ERROR_MESSAGE);
                txtSubject.requestFocus();
                cbbCategory.setSelectedIndex(0);
            } else {
                try {
                    int units = Integer.parseInt(txtUnits.getText());

                    //CHECKS IF THE INSERT METHOD IS SUCCESS
                    switch (courseRecord.addSubject(txtSubject.getText(), txtDesc.getText(), txtUnits.getText(), cbbCategory.getSelectedItem().toString())) {
                        case 1:
                            //MESSAGE
                            JOptionPane.showMessageDialog(null, "Subject Added!", "Save", JOptionPane.INFORMATION_MESSAGE);
                            //EMPTY THE FIELDS
                            txtSubject.setText("");
                            txtDesc.setText("");
                            txtUnits.setText("");
                            cbbCategory.setSelectedIndex(0);
                            courseRecord.subjectPopulate(tblSubj);
                            break;
                        case 2:
                            //MESSAGE IF SUBJECT ALREADY TAKEN
                            JOptionPane.showMessageDialog(null, "Subject Already Taken!", "Save", JOptionPane.ERROR_MESSAGE);
                            txtSubject.requestFocus();
                            break;
                        case 3:
                            //MESSAGE IF INSERT FAILED
                            JOptionPane.showMessageDialog(null, "Failed to Add!", "Save", JOptionPane.ERROR_MESSAGE);
                            break;
                        default:
                            break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Units must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    txtUnits.requestFocus();
                }
            }
            //CHECKS IF CANCEL BUTTON IS PRESSED
        } else if (e.getSource() == btnCancel) {
            //OPTION MESSAGE
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "CANCEL",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                //IF YES OPTION IS SELECTED, EMPTY THE FIELD
                txtSubject.setText("");
                txtDesc.setText("");
                txtUnits.setText("");
                cbbCategory.setSelectedIndex(0);
                txtSubject.requestFocus();
            } else {
                // no option
            }
        }
    }
}
