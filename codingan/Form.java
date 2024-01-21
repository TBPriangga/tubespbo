// Java program to implement
// a Simple Registration Form
// using Java Swing
package TubesRPL;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

class Form extends JFrame implements ActionListener {
    // Components of the Form
    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel judul;
    private JTextField tjudul;
    private JLabel lokasi;
    private JTextField tlokasi;
    private JLabel deskripsi;
    private JTextArea tdeskripsi;
    private JLabel mno;
    private JTextField tmno;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JTextField tdate;
    private JComboBox month;
    private JComboBox year;
    private JLabel add;
    private JTextArea tadd;
    private JCheckBox term;
    private JButton sub;
    private JButton submit;
    private JButton update;
    private JButton delete;
    private JButton clear_all;
    private JButton export;
    private JButton exit;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    private JTable table;
    private String id;
    private UtilDateModel modelDate;
    private JDatePickerImpl datePicker;

    private Date dateNow;

    Statement stmt;
    // a MySQL query
    String query;
    // the results from a MySQL query
    ResultSet rs;
    PreparedStatement pst;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026" };

    private static Connection con = null;
    private static String URL = "jdbc:mysql://localhost:3306/tubes_pbo";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String pass = "";

    public void clear() {
        tname.setText("");
        tjudul.setText("");
        tlokasi.setText("");
        modelDate.setDate(2023, 01,01);
        modelDate.setSelected(true);
        tdeskripsi.setText("");
    }

    public void loadData() {
        try {
            pst = con.prepareStatement("Select * from pengaduan_masyarakat");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // constructor, to initialize the components
    // with default values.
    public Form() throws SQLException {
        setTitle("");
        setBounds(300, 90, 950, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Pengaduan Masyarakat");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 30);
        title.setLocation(320, 30);
        c.add(title);

        name = new JLabel("Nama");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 25);
        tname.setLocation(100, 125);
        c.add(tname);

        judul = new JLabel("Judul");
        judul.setFont(new Font("Arial", Font.BOLD, 20));
        judul.setSize(100, 20);
        judul.setLocation(100, 170);
        c.add(judul);

        tjudul = new JTextField();
        tjudul.setFont(new Font("Arial", Font.PLAIN, 15));
        tjudul.setSize(190, 25);
        tjudul.setLocation(100, 195);
        c.add(tjudul);

        lokasi = new JLabel("Lokasi");
        lokasi.setFont(new Font("Arial", Font.BOLD, 20));
        lokasi.setSize(100, 20);
        lokasi.setLocation(100, 245);
        c.add(lokasi);

        tlokasi = new JTextField();
        tlokasi.setFont(new Font("Arial", Font.PLAIN, 15));
        tlokasi.setSize(190, 25);
        tlokasi.setLocation(100, 265);
        c.add(tlokasi);

        dob = new JLabel("Tanggal");
        dob.setFont(new Font("Arial", Font.BOLD, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 315);
        c.add(dob);

//        date = new JComboBox(dates);
//        date.setFont(new Font("Arial", Font.PLAIN, 15));
//        date.setSize(50, 20);
//        date.setLocation(100, 340);
//        c.add(date);
//
//        month = new JComboBox(months);
//        month.setFont(new Font("Arial", Font.PLAIN, 15));
//        month.setSize(60, 20);
//        month.setLocation(150, 340);
//        c.add(month);
//
//        year = new JComboBox(years);
//        year.setFont(new Font("Arial", Font.PLAIN, 15));
//        year.setSize(60, 20);
//        year.setLocation(210, 340);
//        c.add(year);

//        tdate = new JTextField();
//        tdate.setFont(new Font("Arial", Font.PLAIN, 15));
////        today.setName("Today");
////        today.setEditable(false);
////        JLabel todayLabel = new JLabel("Date:");
////        todayLabel.setLabelFor(today);
////        tdate.setValue(new Date());
//        tdate.setSize(190, 25);
//        tdate.setLocation(100, 340);
//        c.add(tdate);

        modelDate = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, p);
        // Don't know about the formatter, but there it is...
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setLocation(100, 340);
        datePicker.setSize(190, 30);
        c.add(datePicker);

        deskripsi = new JLabel("Deskripsi");
        deskripsi.setFont(new Font("Arial", Font.BOLD, 20));
        deskripsi.setSize(100, 20);
        deskripsi.setLocation(350, 100);
        c.add(deskripsi);

        tdeskripsi = new JTextArea();
        tdeskripsi.setFont(new Font("Arial", Font.PLAIN, 15));
        tdeskripsi.setSize(500, 230);
        tdeskripsi.setLocation(350, 130);
        tdeskripsi.setLineWrap(true);
        c.add(tdeskripsi);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 30);
        submit.setLocation(100, 390);
        c.add(submit);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tname.getText();
                String judul = tjudul.getText();
                String lokasi = tlokasi.getText();
//                String tanggal = datePicker.getModel().getValue().toString();
                dateNow = (Date) datePicker.getModel().getValue();
                java.sql.Date tanggal = new java.sql.Date(dateNow.getTime());
                String deskripsi = tdeskripsi.getText();
                System.out.println(dateNow);
                System.out.println(datePicker.getModel().getValue());

                if (name == null || name.isEmpty() || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Nama Kosong!");
                    tname.requestFocus();
                    return;
                }

                if (judul == null || judul.isEmpty() || judul.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Judul Kosong!");
                    tjudul.requestFocus();
                    return;
                }

                if (lokasi == null || lokasi.isEmpty() || lokasi.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Lokasi Kosong!");
                    tjudul.requestFocus();
                    return;
                }

                if (dateNow == null && dateNow.toString().isEmpty() && dateNow.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Tanggal Kosong!");
                    tdate.requestFocus();
                    return;
                }

                if (deskripsi == null || deskripsi.isEmpty() || deskripsi.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Deskripsi Kosong!");
                    tdeskripsi.requestFocus();
                    return;
                }

                try {
                    String sql = "insert into pengaduan_masyarakat (nama_pelapor,judul_laporan,lokasi_kejadian,tanggal_kejadian,deskripsi_kejadian,status) values (?,?,?,?,?,?)";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setString(2, judul);
                    pst.setString(3, lokasi);
                    pst.setString(4, String.valueOf(tanggal));
                    pst.setString(5, deskripsi);
                    pst.setString(6, "Menunggu");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data insert Success");
                    clear();
                    loadData();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        update = new JButton("Update");
        update.setFont(new Font("Arial", Font.PLAIN, 15));
        update.setSize(100, 30);
        update.setLocation(210, 390);
        c.add(update);
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tname.getText();
                String judul = tjudul.getText();
                String lokasi = tlokasi.getText();
//                String tanggal = tdate.getText();
                dateNow = (Date) datePicker.getModel().getValue();
                java.sql.Date tanggal = new java.sql.Date(dateNow.getTime());
                String deskripsi = tdeskripsi.getText();

                if (name == null || name.isEmpty() || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Name");
                    tname.requestFocus();
                    return;
                }

                if (judul == null || judul.isEmpty() || judul.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Judul");
                    tjudul.requestFocus();
                    return;
                }

                if (lokasi == null || lokasi.isEmpty() || lokasi.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Lokasi");
                    tjudul.requestFocus();
                    return;
                }

                if (tanggal == null) {
                    JOptionPane.showMessageDialog(null, "Please Enter Tanggal");
                    tdate.requestFocus();
                    return;
                }

                if (deskripsi == null || deskripsi.isEmpty() || deskripsi.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Enter Deskripsi");
                    tdeskripsi.requestFocus();
                    return;
                }

                try {
                    String sql = "update pengaduan_masyarakat set nama_pelapor=?,judul_laporan=?,lokasi_kejadian=?,tanggal_kejadian=?,deskripsi_kejadian=? where ID=?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setString(2, judul);
                    pst.setString(3, lokasi);
                    pst.setString(4, String.valueOf(tanggal));
                    pst.setString(5, deskripsi);
                    pst.setString(6, id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Update Success");
                    clear();
                    loadData();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setFont(new Font("Arial", Font.PLAIN, 15));
        delete.setSize(100, 30);
        delete.setLocation(320, 390);
        c.add(delete);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(id != null){
                    int result = JOptionPane.showConfirmDialog(null, "Yakin akan menghapus data tersebut?", "Delete",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "delete from pengaduan_masyarakat where id=?";
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

        clear_all = new JButton("Clear All");
        clear_all.setFont(new Font("Arial", Font.PLAIN, 15));
        clear_all.setSize(100, 30);
        clear_all.setLocation(430, 390);
        c.add(clear_all);
        clear_all.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        export = new JButton("Export");
        export.setFont(new Font("Arial", Font.PLAIN, 15));
        export.setSize(100, 30);
        export.setLocation(540, 390);
        c.add(export);
        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    int count=table.getRowCount();
                    Document document=new Document();
                    Date date = new Date() ;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
                    String nameFile = "C:/Hasil" + dateFormat.format(date) + ".pdf";
                    PdfWriter.getInstance(document,new FileOutputStream(nameFile));
                    document.open();
                    PdfPTable tab=new PdfPTable(7);
                    tab.addCell("Id");
                    tab.addCell("Nama");
                    tab.addCell("Judul");
                    tab.addCell("Lokasi");
                    tab.addCell("Tanggal");
                    tab.addCell("Deskripsi");
                    tab.addCell("Status");
                    for(int i=0;i<count;i++){
                        Object obj1 = GetData(table, i, 0);
                        Object obj2 = GetData(table, i, 1);
                        Object obj3 = GetData(table, i, 2);
                        Object obj4 = GetData(table, i, 3);
                        Object obj5 = GetData(table, i, 4);
                        Object obj6 = GetData(table, i, 5);
                        Object obj7 = GetData(table, i, 6);
                        String value1=obj1.toString();
                        String value2=obj2.toString();
                        String value3=obj3.toString();
                        String value4=obj4.toString();
                        String value5=obj5.toString();
                        String value6=obj6.toString();
                        String value7=obj7.toString();

                        tab.addCell(value1);
                        tab.addCell(value2);
                        tab.addCell(value3);
                        tab.addCell(value4);
                        tab.addCell(value5);
                        tab.addCell(value6);
                        tab.addCell(value7);
                    }
                    document.add(tab);
                    document.close();
                    JOptionPane.showMessageDialog(null, "Export to PDF Success");
                }
                catch(Exception a){}
            }
        });

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100, 30);
        exit.setLocation(750, 590);
        c.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Object rowData[][] = {{"1", "Zidan", "Tes", "Tes", "Tes", "Tes", "Tes"}};
        // array to hold column names
        Object columnNames[] = {"ID", "Nama Pelapor", "Judul Laporan", "Lokasi Kejadian", "Tanggal Kejadian", "Deskripsi Kejadian", "Status"};

        // create a table model and table based on it
        DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);

        // try and connect to the database
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(URL, user, pass);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        query = "select * from pengaduan_masyarakat";
        // make a statement with the server
        stmt = con.createStatement();
        // execute the query and return the result
        rs = stmt.executeQuery(query);

        // create the gui
        table = new JTable(mTableModel);
        table.setBounds(30,40,200,300);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                TableModel model = table.getModel();
                // ID NAME AGE CITY
                id = model.getValueAt(index, 0).toString();
                tname.setText(model.getValueAt(index, 1).toString());
                tjudul.setText(model.getValueAt(index, 2).toString());
                tlokasi.setText(model.getValueAt(index, 3).toString());

//                tdate.setText(model.getValueAt(index, 4).toString());

                System.out.println(model.getValueAt(index, 4).toString());
                String dateMentah = model.getValueAt(index, 4).toString();
                String[] parts = dateMentah.split("-");
                String yearDate = parts[0];
                String monthDate = parts[1];
                String dayDate = parts[2];
                System.out.println(yearDate);
                System.out.println(monthDate);
                System.out.println(dayDate);

                modelDate.setDate(Integer.parseInt(yearDate), Integer.parseInt(monthDate) - 1, Integer.parseInt(dayDate));
                modelDate.setSelected(true);

                tdeskripsi.setText(model.getValueAt(index, 5).toString());

            }
        });
        JScrollPane sp = new JScrollPane(table);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(scrollPane, BorderLayout.CENTER);
//        table.setSize(750, 150);
//        table.setLocation(100, 400);
        sp.setSize(750, 150);
        sp.setLocation(100, 430);
        sp.setVisible(true);
        c.add(sp);
//        c.add(table);

        // remove the temp row
        mTableModel.removeRow(0);

        // create a temporary object array to hold the result for each row
        Object[] rows;
        // for each row returned
        while (rs.next()) {
            // add the values to the temporary row
            rows = new Object[]{rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)};

            // add the temp row to the table
            mTableModel.addRow(rows);
        }

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    public void actionPerformed(ActionEvent e)
    {

    }

    public Object GetData(JTable table, int row_index, int col_index){
        return table.getModel().getValueAt(row_index, col_index);
    }

    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}

// Driver Code
class Registration {

    public static void main(String[] args) throws Exception
    {
        Form f = new Form();
    }
}
