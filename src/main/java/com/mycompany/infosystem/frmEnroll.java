/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infosystem;

/**
 *
 * @author Gyeoulie
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class frmEnroll extends JFrame implements ActionListener {

    //-----------------------INITIALIZE-----------------------
    //DBMODEL CLASS
    DBModel courseRecord = new DBModel();

    //DEFAULT TABLE MODEL
    DefaultTableModel model = new DefaultTableModel();

    //BOOLEAN VALUES FOR BUTTONS
    boolean mjrbtn = true, mnrbtn = true;

    //LABEL
    JLabel lblName = new JLabel("Name");
    JLabel lblSex = new JLabel("Gender");
    JLabel lblUnits = new JLabel("Total Units");
    JLabel lblCost = new JLabel("Cost per Unit");
    JLabel lblTuition = new JLabel("Total Tuition Fee");
    JLabel lblCash = new JLabel("Cash Tendered");
    JLabel lblChange = new JLabel("Change");

    //SCROLL PANE
    JScrollPane sp1 = new JScrollPane();
    JTable tblSubj = new JTable();

    //TEXT FIELD
    JTextField txtName = new JTextField(15);
    JTextField txtSex = new JTextField(25);
    JTextField txtUnits = new JTextField(25);
    JTextField txtCost = new JTextField(25);
    JTextField txtTuition = new JTextField(25);
    JTextField txtCash = new JTextField(25);
    JTextField txtChange = new JTextField(25);

    //BUTTONS
    JButton btnMajor = new JButton("Major");
    JButton btnMinor = new JButton("Minor");
    JButton btnTotal = new JButton("Total Fee");
    JButton btnCompute = new JButton("Compute");

    public frmEnroll() {
        super("Assesment");
        setLayout(null);

        //-----------------------LABEL FIELDS-----------------------
        //NAME LABEL
        lblName.setBounds(100, 65, 200, 30);
        add(lblName);
        //GENDER LABEL
        lblSex.setBounds(100, 100, 200, 30);
        add(lblSex);
        //TOTAL UNITS LABEL
        lblUnits.setBounds(250, 500, 100, 30);
        add(lblUnits);
        //COST PER UNITS LABEL
        lblCost.setBounds(250, 530, 100, 30);
        add(lblCost);
        //TOTAL TUITION FEE LABEL
        lblTuition.setBounds(250, 560, 100, 30);
        add(lblTuition);
        //CASH TENDERED LABEL
        lblCash.setBounds(250, 590, 100, 30);
        add(lblCash);
        //CHANGE LABEL
        lblChange.setBounds(250, 620, 100, 30);
        add(lblChange);

        //-----------------------TEXT FIELDS-----------------------
        //NAME TEXTFIELD
        txtName.setBounds(220, 65, 200, 30);
        add(txtName);
        txtName.setEditable(false);
        txtName.setBackground(Color.WHITE);
        txtName.setForeground(Color.BLACK);
        txtName.setBackground(UIManager.getColor("TextField.background"));
        txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        //GENDER FIELD
        txtSex.setBounds(220, 100, 200, 30);
        add(txtSex);
        txtSex.setEditable(false);
        txtSex.setBackground(Color.WHITE);
        txtSex.setForeground(Color.BLACK);
        txtSex.setBackground(UIManager.getColor("TextField.background"));
        txtSex.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtSex.setHorizontalAlignment(SwingConstants.CENTER);
        //TOTAL UNITS FIELD
        txtUnits.setBounds(350, 500, 100, 30);
        add(txtUnits);
        txtUnits.setEditable(false);
        txtUnits.setBackground(Color.WHITE);
        txtUnits.setForeground(Color.BLACK);
        txtUnits.setBackground(UIManager.getColor("TextField.background"));
        txtUnits.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtUnits.setHorizontalAlignment(SwingConstants.CENTER);
        //COST PER UNITS FIELD
        txtCost.setBounds(350, 530, 100, 30);
        add(txtCost);
        txtCost.setBackground(UIManager.getColor("TextField.background"));
        txtCost.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCost.setHorizontalAlignment(SwingConstants.CENTER);
        //TOTAL TUITION FEE FIELD
        txtTuition.setBounds(350, 560, 100, 30);
        add(txtTuition);
        txtTuition.setEditable(false);
        txtTuition.setBackground(Color.WHITE);
        txtTuition.setForeground(Color.BLACK);
        txtTuition.setBackground(UIManager.getColor("TextField.background"));
        txtTuition.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTuition.setHorizontalAlignment(SwingConstants.CENTER);
        //CASH TENDERED FIELD
        txtCash.setBounds(350, 590, 100, 30);
        add(txtCash);
        txtCash.setBackground(UIManager.getColor("TextField.background"));
        txtCash.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtCash.setHorizontalAlignment(SwingConstants.CENTER);
        //CHANGE FIELD
        txtChange.setBounds(350, 620, 100, 30);
        add(txtChange);
        txtChange.setEditable(false);
        txtChange.setBackground(Color.WHITE);
        txtChange.setForeground(Color.BLACK);
        txtChange.setBackground(UIManager.getColor("TextField.background"));
        txtChange.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtChange.setHorizontalAlignment(SwingConstants.CENTER);

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

        //TABLE MODEL
        //SET THE TABLE TO DEFAULT TABLE MODEL
        tblSubj.setModel(model);
        //ADDING COLUMNS(HEADERS) TO THE TABLE MODEL
        model.addColumn("CODE");
        model.addColumn("DESCRIPTION");
        model.addColumn("UNITS");

        //-----------------------BUTTONS-----------------------
        //MAJOR SUBJ BUTTON
        btnMajor.setBounds(100, 500, 100, 30);
        add(btnMajor);
        //MINOR SUBJ BUTTON
        btnMinor.setBounds(100, 530, 100, 30);
        add(btnMinor);
        //TOTAL FEE BUTTON
        btnTotal.setBounds(100, 560, 100, 30);
        add(btnTotal);
        //COMPUTE BUTTON
        btnCompute.setBounds(100, 590, 100, 30);
        add(btnCompute);

        //-----------------------ACTION LISTENERS-----------------------
        //MAJOR BUTTON
        btnMajor.addActionListener(this);
        //MINOR BUTTON
        btnMinor.addActionListener(this);
        //TOTAL BUTTON
        btnTotal.addActionListener(this);
        //COMPUTE BUTTON
        btnCompute.addActionListener(this);

    }

    //ACTION PERFORMED METHOD
    public void actionPerformed(ActionEvent e) {
        //CHECKS IF MAJOR BUTTON IS PRESSED
        if (e.getSource() == btnMajor) {
            //CHECKS IF MAJOR BUTTON HAS ALREADY BEEN PRESSED ONCE
            if (mjrbtn == true) {
                //CALLS THE SUBJECT POPULATE METHOD FOR MAJOR SUBJECTS
                courseRecord.subjectPopulate(tblSubj, "Major");

                //CHECKS IF TXTUNITS IS EMPTY IS SET IT TO VALUE 0 IF ITS EMPTY
                int units = txtUnits.getText().isEmpty() ? 0 : Integer.parseInt(txtUnits.getText());
                //CALLS THE METHOD TO COUNT TOTAL UNITS
                txtUnits.setText(String.valueOf(courseRecord.getTotalUnits(units, "Major")));

                //SETS THE MAJOR BUTTON TO FALSE == PRESSED 
                mjrbtn = false;
            } else {
                //ERROR MESSAGE IF ALREADY PRESSED ONCE
                JOptionPane.showMessageDialog(null, "Major Subjects Already Added!", "Major", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnMinor) {
            //CHECKS IF MINOR BUTTON HAS ALREADY BEEN PRESSED ONCE
            if (mnrbtn == true) {
                //CALLS THE SUBJECT POPULATE METHOD FOR MINOR SUBJECTS
                courseRecord.subjectPopulate(tblSubj, "Minor");

                //CHECKS IF TXTUNITS IS EMPTY IS SET IT TO VALUE 0 IF ITS EMPTY
                int units = txtUnits.getText().isEmpty() ? 0 : Integer.parseInt(txtUnits.getText());
                //CALLS THE METHOD TO COUNT TOTAL UNITS
                txtUnits.setText(String.valueOf(courseRecord.getTotalUnits(units, "Minor")));

                //SETS THE MINOR BUTTON TO FALSE == PRESSED 
                mnrbtn = false;
            } else {
                //ERROR MESSAGE IF ALREADY PRESSED ONCE
                JOptionPane.showMessageDialog(null, "Minor Subjects Already Added!", "Minor", JOptionPane.ERROR_MESSAGE);
            }

            //CHECKS IF TOTAL BUTTON IS PRESSED
        } else if (e.getSource() == btnTotal) {
            //INITIALIZE VARIABLES
            int cost, numunits, tf;
            try {
                //CHECK IF THE FIELDS ARE EMPTY
                if (txtCost.getText().equals("") || txtUnits.getText().equals("")) {
                    //MESSAGE
                    JOptionPane.showMessageDialog(null, "All Fields are Required!", "Cost Per Unit", JOptionPane.ERROR_MESSAGE);
                    txtCost.requestFocus();
                    return;
                }
                //PARSE STRING TO INTEGER
                cost = Integer.parseInt(txtCost.getText());
                numunits = Integer.parseInt(txtUnits.getText());

                //COMPUTE TOTAL COST
                tf = cost * numunits;

                //SETS TOTAL COST TO TEXT FIELD
                txtTuition.setText(String.valueOf(tf));

                //ERROR IF INVALID INPUT
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Cost!", "Cost Per Unit", JOptionPane.ERROR_MESSAGE);
                txtCost.requestFocus();
            }

            //CHECKS IF COMPUTE BUTTON IS PRESSED
        } else if (e.getSource() == btnCompute) {
            //PARSE STRING TO INTEGER
            int TF, CASH, CHANGE;

            try {
                //CHECK IF THE FIELDS ARE EMPTY
                if (txtTuition.getText().equals("") || txtCash.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "All Fields are Required!", "Cash Tendered", JOptionPane.ERROR_MESSAGE);
                    txtCash.requestFocus();
                    return;
                }
                //PARSE STRING TO INTEGER
                TF = Integer.parseInt(txtTuition.getText());
                CASH = Integer.parseInt(txtCash.getText());

                //CHECKS IF CASH TENDERED IS LOWER THAN TOTAL COST
                if (TF > CASH) {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds!", "Cash Tendered", JOptionPane.ERROR_MESSAGE);
                    txtCash.setText("");
                    txtCash.requestFocus();
                } else {
                    //COMPUTE FOR THE CHANGE
                    CHANGE = CASH - TF;
                    txtChange.setText(String.valueOf(CHANGE));
                    JOptionPane.showMessageDialog(null, "Payment Success!", "Tuition Fee", JOptionPane.INFORMATION_MESSAGE);
                }

                //ERROR IF INVALID INPUT
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Input!", "Cash Tendered", JOptionPane.ERROR_MESSAGE);
                txtCash.requestFocus();
            }
        }
    }

}
