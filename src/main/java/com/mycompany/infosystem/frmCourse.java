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

public class frmCourse extends JFrame {

    public frmCourse() {
        super("Course");
        setLayout(null);

        JTable tblStd = new JTable();
        JTable tblCourse = new JTable();

        JLabel lblCourse = new JLabel("Course Code");
        lblCourse.setBounds(100, 30, 200, 30);
        add(lblCourse);

        JTextField txtCourse = new JTextField(25);
        txtCourse.setBounds(220, 30, 200, 30);
        add(txtCourse);

        JLabel lblDesc = new JLabel("Description");
        lblDesc.setBounds(100, 65, 200, 30);
        add(lblDesc);

        JTextField txtDesc = new JTextField(15);
        txtDesc.setBounds(220, 65, 200, 30);
        add(txtDesc);

        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(tblStd);
        sp1.setBounds(100, 205, 350, 280);
        setSize(200, 200);
        setVisible(true);
        add(sp1);

        JButton btnSave1 = new JButton("Save");
        btnSave1.setBounds(115, 500, 100, 30);
        add(btnSave1);

        JButton btnUpdate1 = new JButton("Update");
        btnUpdate1.setBounds(225, 500, 100, 30);
        add(btnUpdate1);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(335, 500, 100, 30);
        add(btnCancel);

    }
}
