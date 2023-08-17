/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infosystem;

/**
 *
 * @author Khen
 */
import java.awt.event.*;
import javax.swing.*;

public class menu extends JFrame {

    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem frmStudentMenu = new JMenuItem("Students");
    JMenuItem frmCourse = new JMenuItem("Course");
    JMenuItem frmSubject = new JMenuItem("Subject");
    JMenuItem exit = new JMenuItem("Exit");

    event click = new event();

    public menu() {
        super(" Information System");

        setJMenuBar(menubar);
        //THIS AREA IS UNDER FILE MENU
        file.setMnemonic('F');
        menubar.add(file);

        frmStudentMenu.setMnemonic('S');
        file.add(frmStudentMenu);

        frmCourse.setMnemonic('C');
        file.add(frmCourse);

        frmSubject.setMnemonic('U');
        file.add(frmSubject);

        exit.setMnemonic('X');
        file.add(exit);

        frmStudentMenu.addActionListener(click);
        frmCourse.addActionListener(click);
        frmSubject.addActionListener(click);
        exit.addActionListener(click);

    }

    public class event implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem) e.getSource();
            if (source == frmStudentMenu) {
                frmStudents f1 = new frmStudents();
                f1.setSize(600, 600);
                f1.setVisible(true);
            } else if (source == frmCourse) {
                frmCourse c1 = new frmCourse();
                c1.setSize(600, 600);
                c1.setVisible(true);
            } else if (source == frmSubject) {
                frmSubjects s1 = new frmSubjects();
                s1.setSize(600, 600);
                s1.setVisible(true);
            } else if (source == exit) {
                String messageBox = "Are you sure you want to close the system?";
                int answer = JOptionPane.showConfirmDialog(null, messageBox, "Exit", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }
}
