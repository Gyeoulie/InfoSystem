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

public class frmStudents extends JFrame implements ActionListener {

    //-----------------------INITIALIZE-----------------------
    //DBMODEL CLASS
    DBModel studRecord = new DBModel();

    //LABEL
    JLabel lblName = new JLabel("Name");
    JLabel lblSex = new JLabel("Sex");
    JLabel lblSearch = new JLabel("Search");

    //INPUTFIELDS
    JTextField txtName = new JTextField(15);
    JTextField txtSearch = new JTextField(15);

    //COMBO BOX
    String[] val = {"Male", "Female"};
    JComboBox cbbSex = new JComboBox(val);

    //SCROLL PANE - TABLE
    JScrollPane sp = new JScrollPane();
    JTable tblStd = new JTable();

    //BUTTONS
    JButton btnSave = new JButton("Save");
    JButton btnEnroll = new JButton("Enroll");
    JButton btnPopulate = new JButton("View List");

    public frmStudents() {
        super("Students");
        setLayout(null);

        //-----------------------LABEL FIELDS-----------------------
        //NAME LABEL
        lblName.setBounds(100, 65, 200, 30);
        add(lblName);
        //GENDER LABEL
        lblSex.setBounds(100, 100, 200, 30);
        add(lblSex);
        //SERACH LABEL
        lblSearch.setBounds(100, 145, 200, 30);
        add(lblSearch);

        //-----------------------TEXT FIELDS-----------------------
        //NAME TEXTFIELD
        txtName.setBounds(220, 65, 200, 30);
        add(txtName);
        //SEARCH FIELD
        txtSearch.setBounds(220, 145, 200, 30);
        add(txtSearch);

        //GENDER COMBOBOX
        cbbSex.setBounds(220, 100, 200, 30);
        add(cbbSex);

        //-----------------------TABLE-----------------------
        //SCROLL PANE - TABLE - STUDENTLIST
        sp.setViewportView(tblStd);
        sp.setBounds(100, 205, 350, 280);
        setSize(200, 200);
        setVisible(true);
        add(sp);

        //SET THE TABLE TO NOT BE EDITABLE
        tblStd.setDefaultEditor(Object.class, null);

        //-----------------------BUTTONS-----------------------
        //SAVE BUTTON
        btnSave.setBounds(114, 500, 100, 30);
        add(btnSave);
        //UPDATE BUTTON
        btnEnroll.setBounds(224, 500, 100, 30);
        add(btnEnroll);
        //POPULATE BUTTON
        btnPopulate.setBounds(334, 500, 100, 30);
        add(btnPopulate);

        //-----------------------ACTION LISTENERS-----------------------
        //SETTING ACTION LISTENER
        //SAVE BUTTON
        btnSave.addActionListener(this);
        //ENROLL BUTTON
        btnEnroll.addActionListener(this);
        ///VIEWLIST BUTTON
        btnPopulate.addActionListener(this);
    }

    //ACTION PERFORMED METHOD
    public void actionPerformed(ActionEvent e) {
        //CHECKS IF SAVE BUTTON IS PRESSED
        if (e.getSource() == btnSave) {
            //CHECKS IF FIELDS ARE EMPTY
            if (txtName.getText().equals("")) {
                //SHOWS AN ERROR IF EMPTY
                JOptionPane.showMessageDialog(null, "All Fields are Required!", "Student Name", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                cbbSex.setSelectedIndex(0);
            } else {
                //CHECKS IF THE ADD STUDENT METHOD IS SUCCESS
                if (studRecord.addStudent(txtName.getText(), cbbSex.getSelectedItem().toString()) == true) {
                    //SHOWS A SUCCESS MESSAGE
                    JOptionPane.showMessageDialog(null, "Record Added!", "Save", JOptionPane.INFORMATION_MESSAGE);
                    //EMPTY THE FIELDS AND SETS FOCUS TO NAME
                    txtName.setText("");
                    cbbSex.setSelectedIndex(0);
                    txtName.requestFocus();
                    studRecord.tablePopulate(tblStd);
                } else {
                    //SHOWS AN ERROR MESSAGE IF FAILED TO ADD
                    JOptionPane.showMessageDialog(null, "Failed to Add!", "Save", JOptionPane.ERROR_MESSAGE);
                }
            }

            //CHECKS IF ENROLL BUTTON IS PRESSED
        } else if (e.getSource() == btnEnroll) {
            //INDEX FOR THE SELECTED ROW
            int index = tblStd.getSelectedRow();

            //CHECKS IF A ROW IS SELECTED
            if (index != -1) {
                //INITIALIZE OBJECT
                var e1 = new frmEnroll();
                //CLASS THE CLASS
                e1.setSize(600, 700);
                e1.setVisible(true);
                e1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //PASS THE SELECTED ROW TO THE FIELDS OF THE OTHER CLASS
                e1.txtName.setText(tblStd.getValueAt(index, 1).toString());
                e1.txtSex.setText(tblStd.getValueAt(index, 2).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Must Select a Student First!", "Enrollment", JOptionPane.ERROR_MESSAGE);
            }

            //CHECKS IF VIEWLIST BUTTON IS PRESSED
        } else if (e.getSource() == btnPopulate) {
            //POPULATE THE TABLE
            studRecord.tablePopulate(tblStd);
        }

        //SEARCH KEY LISTENER
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent args) {
                //CALLS THE SEARCH METHOD
                studRecord.searchStudents(txtSearch.getText(), tblStd);
            }
        });
    }
}
