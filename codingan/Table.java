package TubesRPL;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Table
{

    private static Connection con = null;
    private static String URL = "jdbc:mysql://localhost:3306/tubes_pbo";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String pass = "";

    private Container c;
    private JLabel nama;
    private JLabel judul;
    private JLabel lokasi;
    private JLabel tanggal;

    public static void main(String[] args) throws SQLException
    {

        // a MySQL statement
        Statement stmt;
        // a MySQL query
        String query;
        // the results from a MySQL query
        ResultSet rs;

        // 2 dimension array to hold table contents
        // it holds temp values for now
        Object rowData[][] = {{"1", "Zidan", "Tes"}};
        // array to hold column names
        Object columnNames[] = {"id", "nama_pelapor", "judul_laporan"};

        // create a table model and table based on it
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(mTableModel);

        // try and connect to the database
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(URL, user, pass);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        // run the desired query
        query = "select * from pengaduan_masyarakat";
        // make a statement with the server
        stmt = con.createStatement();
        // execute the query and return the result
        rs = stmt.executeQuery(query);

        // create the gui
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);

        // remove the temp row
        mTableModel.removeRow(0);

        // create a temporary object array to hold the result for each row
        Object[] rows;
        // for each row returned
        while (rs.next()) {
            // add the values to the temporary row
            rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)};
            // add the temp row to the table
            mTableModel.addRow(rows);
        }
    }
}