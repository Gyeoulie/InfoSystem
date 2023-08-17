/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.infosystem;

/**
 *
 * @author Khen
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBModel {

    //CONNECTION METHOD
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itep201infosystem", "root", "");
        return conn;
    }

    //POPULATE STUDENT TABLE
    public void tablePopulate(JTable tblStd) {
        try {
            Connection conn = getConnection();

            //SELECT QUERY
            String query = "SELECT studId, studName, studSex from tblstudents";
            PreparedStatement ps = conn.prepareStatement(query);

            //EXECUTE QUERY
            ResultSet rs = ps.executeQuery();

            //CREATE DEFAULT TABLE MODEL
            DefaultTableModel model = new DefaultTableModel();

            //ADD COLUMNS(HEADER) TO MODEL
            model.addColumn("ID");
            model.addColumn("NAME");
            model.addColumn("GENDER");

            //ADD ROWS TO MODEL
            while (rs.next()) {
                Object[] row = {rs.getString("studId"), rs.getString("studName"), rs.getString("studSex")};
                model.addRow(row);
            }

            //SET MODEL TO TABLE
            tblStd.setModel(model);

            conn.close();
        } catch (Exception err) {
            System.out.print(err);
        }
    }

    //POPULATE SUBJECT TABLE
    public void subjectPopulate(JTable tblSubj) {
        try {
            Connection conn = getConnection();

            //SELECT QUERY
            String query = "SELECT subjectCode, subjectDesc, subjectUnits, subjectCategory from tblsubjects";
            PreparedStatement ps = conn.prepareStatement(query);

            //EXECUTE QUERY
            ResultSet rs = ps.executeQuery();

            //CREATE DEFAULT TABLE MODEL
            DefaultTableModel model = new DefaultTableModel();

            //ADD COLUMNS(HEADER) TO MODEL
            model.addColumn("CODE");
            model.addColumn("DESCRIPTION");
            model.addColumn("UNITS");
            model.addColumn("CATEGORY");

            //ADD ROWS TO MODEL
            while (rs.next()) {
                Object[] row = {rs.getString("subjectCode"), rs.getString("subjectDesc"), rs.getString("subjectUnits"), rs.getString("subjectCategory")};
                model.addRow(row);
            }

            //SET MODEL TO TABLE
            tblSubj.setModel(model);

            conn.close();
        } catch (Exception err) {
            System.out.print(err);
        }
    }

    //POPULATE SUBJECT TABLE BASE ON CATEGORY
    public void subjectPopulate(JTable tblSubj, String Categ) {
        try {
            Connection conn = getConnection();

            //SELECT QUERY
            String query = "SELECT subjectCode, subjectDesc, subjectUnits from tblsubjects WHERE subjectCategory = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //SETS THE VALUES
            ps.setString(1, Categ);

            //EXECUTE QUERY
            ResultSet rs = ps.executeQuery();

            //GET THE CURRENT TABLE MODEL OF THE JTABLE
            DefaultTableModel model = (DefaultTableModel) tblSubj.getModel();

            //ADD ROWS TO THE TABLE MODEL
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getString("subjectCode");
                row[1] = rs.getString("subjectDesc");
                row[2] = rs.getString("subjectUnits");

                model.addRow(row);
            }

            conn.close();
        } catch (Exception err) {
            System.out.print(err);
        }
    }

    //GET AND RETURN TOTAL OF UNITS
    public int getTotalUnits(int units, String Category) {
        int totalUnits = 0;
        try {
            Connection conn = getConnection();

            //SELECT QUERY
            String query = "SELECT SUM(subjectUnits) AS totalUnits FROM tblsubjects WHERE subjectCategory = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //SETS THE VALUES
            ps.setString(1, Category);

            //EXECUTE THE QUERY
            ResultSet rs = ps.executeQuery();

            //GETS THE SUM OF THE ENTIRE COLUMN BASE ON CATEGORY
            if (rs.next()) {
                totalUnits = rs.getInt("totalUnits");
            }
            conn.close();

        } catch (Exception err) {
            System.out.print(err);
        }
        //RETURN TOTAL COMPUTED UNITS + CURRENT UNITS
        return totalUnits + units;
    }

    //METHOD FOR ADDING STUDENT
    public boolean addStudent(String name, String gender) {
        try {
            Connection conn = getConnection();

            //ADD QUERY
            String addquery = "INSERT INTO tblstudents (studName, studSex) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(addquery);

            //SETS THE VALUES
            ps.setString(1, name);
            ps.setString(2, gender);

            //EXECUTE THE UPDATE
            int result = ps.executeUpdate();

            //CHECKS IF THE EXECUTION IS SUCCESSFUL
            if (result == 0) {
                return false;
            }

            conn.close();

        } catch (Exception err) {
            System.out.print(err);
        }

        return true;
    }

    //METHOD FOR ADDING SUBJECT
    public int addSubject(String Code, String Desc, String Units, String Category) {
        try {
            Connection conn = getConnection();

            //SELECT QUERY TO CHECK IF SUBJECT ALREADY EXIST
            String checkquery = "SELECT subjectCode, subjectDesc FROM tblsubjects WHERE subjectCode = ? OR subjectDesc = ?";
            PreparedStatement ps = conn.prepareStatement(checkquery);

            //SETS THE VALUES
            ps.setString(1, Code);
            ps.setString(2, Desc);

            //STORES IN RESULT AND EXECUTE THE QUERY
            ResultSet rs = ps.executeQuery();

            //CHECKS IF EXIST IN THE TABLE
            if (rs.next()) {
                return 2;
            } else {
                //ADD QUERY
                String addquery = "INSERT INTO tblsubjects (subjectCode, subjectDesc, subjectUnits, subjectCategory) VALUES (?,?,?,?)";
                ps = conn.prepareStatement(addquery);

                //SETS THE VALUES
                ps.setString(1, Code);
                ps.setString(2, Desc);
                ps.setString(3, Units);
                ps.setString(4, Category);

                //EXECUTE THE UPDATE
                int result = ps.executeUpdate();

                //CHECKS IF THE EXECUTION IS SUCCESSFUL
                if (result == 0) {
                    return 3;
                }
            }

            conn.close();

        } catch (Exception err) {
            System.out.print(err);
        }

        return 1;
    }

    public void searchStudents(String ID, JTable tblStd) {
        try {
            Connection conn = getConnection();

            // POPULATE THE TABLE IF SEARCHFIELD IS EMPTY
            if (ID.equals("")) {
                tablePopulate(tblStd);
            } else {
                // QUERY FOR SEARCH
                String qryFind = "SELECT * FROM tblstudents WHERE studId = ?";
                PreparedStatement find = conn.prepareStatement(qryFind);

                // SETS THE VALUES
                find.setString(1, ID);

                // EXECUTE QUERY
                ResultSet rs = find.executeQuery();

                //CREATE DEFAULT TABLE MODEL
                DefaultTableModel model = new DefaultTableModel();

                //ADD COLUMNS(HEADER) TO MODEL
                model.addColumn("ID");
                model.addColumn("NAME");
                model.addColumn("GENDER");

                //ADD ROWS TO MODEL
                while (rs.next()) {
                    Object[] row = {rs.getString("studId"), rs.getString("studName"), rs.getString("studSex")};
                    model.addRow(row);
                }

                //SET MODEL TO TABLE
                tblStd.setModel(model);
            }

            conn.close();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
