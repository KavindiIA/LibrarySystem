package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class AccountDetails {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the application.
	 */
	public AccountDetails() {
		initialize();
		frame.setLocationRelativeTo(null);
		fetchData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 833, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(250, 240, 230));
		panel.setBounds(0, 0, 819, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountDetails = new JLabel("Account Details");
		lblAccountDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountDetails.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblAccountDetails.setBounds(340, 26, 150, 26);
		panel.add(lblAccountDetails);
		
		// Create table with column headings
        String[] columnHeadings = {"ID", "Name", "Email", "Contact No", "Role", "Username"};
        Object[][] data = {}; // Empty rows initially
        tableModel = new DefaultTableModel(data, columnHeadings);

        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        table.setRowHeight(30); // Set row height
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(200, 200, 200));
        table.getTableHeader().setForeground(Color.BLACK); 

        // Add scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint()); // Get the row under the mouse
                if (row > -1) {
                    table.setRowSelectionInterval(row, row); // Select the row
                    table.setSelectionBackground(Color.PINK); // Set custom background color for selection
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                table.clearSelection(); // Clear row selection when the mouse exits the table
            }
        });
        scrollPane.setBounds(38, 80, 745, 342);
        panel.add(scrollPane);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnBack.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnBack.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(678, 438, 105, 27);
		panel.add(btnBack);
	}

	public void show() {
		frame.setVisible(true);
	}
	
	private void fetchData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM AdminsAndLibrarians"; 
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Get each column data and add it to the table
                    String userID = rs.getString("UserID");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String contactNo = rs.getString("ContactNumber");
                    String role = rs.getString("Role");
                    String username = rs.getString("Username");

                    // Add a row to the table
                    tableModel.addRow(new Object[]{userID, name, email, contactNo, role, username});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching data from the database: " + e.getMessage());
        }
    }
}
