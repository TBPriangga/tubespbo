package TubesRPL;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class FormAdmin {
    private JFrame frame;
    private JTextField tStatus;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtCity;
    private JTable table;
    private String id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormAdmin window = new FormAdmin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public FormAdmin(){
        initialize();
        Connect();
        loadData();
    }

    Connection con = null;
    PreparedStatement pst;
    ResultSet rs;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tubes_pbo";
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Clear All
    public void clear() {
        tStatus.setText("");
    }

    // Load Table
    public void loadData() {
        try {
            pst = con.prepareStatement("Select * from pengaduan_masyarakat");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initialize() {
        frame = new JFrame();
//        frame.setTitle("CURD Operation Swing MySQL");
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Pengaduan Masyarakat (Admin)");
//        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(200, 5, 300, 60);
        frame.getContentPane().add(lblNewLabel);
//
        JLabel lblNewLabel_1 = new JLabel("Status");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(40, 65, 46, 24);
        frame.add(lblNewLabel_1);

        tStatus = new JTextField();
//        txtID.setEditable(false);
        tStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tStatus.setBounds(90, 65, 287, 24);
        frame.add(tStatus);
//        txtID.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUpdate.setBounds(40, 120, 89, 23);
        frame.add(btnUpdate);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update Details
                String status = tStatus.getText();

                if (status == null || status.isEmpty() || status.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Status Kosong");
                    tStatus.requestFocus();
                    return;
                }

                try {
                    String sql = "update pengaduan_masyarakat set status=? where ID=?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, status);
                    pst.setString(2, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Update Success");
                    clear();
                    loadData();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.setBounds(140, 120, 89, 23);
        frame.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {

                    int result = JOptionPane.showConfirmDialog(null, "Yakin akan menghapus data tersebut?", "Delete",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "delete from pengaduan_masyarakat where ID=?";
                            pst = con.prepareStatement(sql);
                            pst.setString(1, id);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Menghapus data berhasil");
                            clear();
                            loadData();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });

        JButton clear_all = new JButton("Clear All");
        clear_all.setFont(new Font("Arial", Font.PLAIN, 15));
        clear_all.setBounds(240, 120, 89, 23);
        frame.add(clear_all);
        clear_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setBounds(550, 455, 89, 23);
        frame.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 150, 600, 300);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = table.getSelectedRow();
                TableModel model = table.getModel();
                // ID NAME AGE CITY
                id = model.getValueAt(index, 0).toString();
                tStatus.setText(model.getValueAt(index, 6).toString());
            }
        });
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        scrollPane.setViewportView(table);
        frame.setBounds(100, 100, 700, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
