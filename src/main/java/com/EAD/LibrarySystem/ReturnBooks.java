package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class ReturnBooks {

	private JFrame frame;
	private JTextField txtStudentID;
	private JTextField txtDelay;
	private JTextField txtFine;
	private JTextField txtBookID;
	private JDateChooser dateChooserReturn;
	private JDateChooser dateChooserBorrow;
	private static final int FINE_PER_DAY = 20;
	private JComboBox<String> cmbSearch;
	private List<String> recordIDList = new ArrayList<>();
	private JTextField txtRecordID;

	/**
	 * Create the application.
	 */
	public ReturnBooks() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountManagement_1 = new JLabel("Return");
		lblAccountManagement_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1.setBounds(94, 32, 93, 27);
		panel.add(lblAccountManagement_1);
		
		JLabel lblDetails = new JLabel("Search Here!");
		lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDetails.setBounds(21, 125, 178, 27);
		panel.add(lblDetails);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnBack.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnBack.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(51, 438, 105, 27);
		panel.add(btnBack);
		
		JLabel lblAccountManagement_1_1 = new JLabel("Books");
		lblAccountManagement_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1_1.setBounds(94, 58, 105, 27);
		panel.add(lblAccountManagement_1_1);
		
		cmbSearch = new JComboBox<>();
		cmbSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("ComboBox clicked!");
				loadBorrowDataFromSearch();
			}
		});
		cmbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbSearch.setBounds(31, 162, 168, 27);
		panel.add(cmbSearch);
		fetchBookIDs();
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStudentID.setText(""); 
				txtDelay.setText(""); 
				txtBookID.setText(""); 
				txtFine.setText("");
				dateChooserReturn.setDate(null);
				dateChooserBorrow.setDate(null);
			}
		});
		btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnClear.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnClear.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClear.setBackground(Color.BLACK);
		btnClear.setBounds(51, 399, 105, 27);
		panel.add(btnClear);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 107, 209, 4);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ReturnBooks.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 24, 74, 73);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnBooks.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblReturnBooks.setBounds(98, 34, 437, 26);
		panel_1.add(lblReturnBooks);
		
		JLabel lblRecordId = new JLabel("Return Date");
		lblRecordId.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecordId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRecordId.setBounds(36, 306, 85, 27);
		panel_1.add(lblRecordId);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblStudentId.setBounds(36, 135, 85, 27);
		panel_1.add(lblStudentId);
		
		JLabel lblAvailCopies = new JLabel("Fine");
		lblAvailCopies.setHorizontalAlignment(SwingConstants.LEFT);
		lblAvailCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAvailCopies.setBounds(36, 397, 61, 27);
		panel_1.add(lblAvailCopies);
		
		txtStudentID = new JTextField();
		txtStudentID.setEditable(false);
		txtStudentID.setForeground(Color.BLACK);
		txtStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(311, 135, 261, 26);
		panel_1.add(txtStudentID);
		
		txtBookID = new JTextField();
		txtBookID.setEditable(false);
		txtBookID.setForeground(Color.BLACK);
		txtBookID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtBookID.setColumns(10);
		txtBookID.setBounds(311, 183, 261, 26);
		panel_1.add(txtBookID);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateReturnDateAndFine(txtRecordID, txtFine, txtBookID, dateChooserReturn);
			}
		});
		btnContinue.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnContinue.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnContinue.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnContinue.setBackground(Color.BLACK);
		btnContinue.setBounds(467, 443, 105, 27);
		panel_1.add(btnContinue);
		
		JLabel lblAccountManagement_1_2 = new JLabel("Borrow Details");
		lblAccountManagement_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_2.setForeground(new Color(255, 20, 147));
		lblAccountManagement_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAccountManagement_1_2.setBounds(36, 59, 134, 27);
		panel_1.add(lblAccountManagement_1_2);
		
		JLabel lblAccountManagement_1_2_1 = new JLabel("Return Book and Calculater Fine");
		lblAccountManagement_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_2_1.setForeground(new Color(255, 20, 147));
		lblAccountManagement_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAccountManagement_1_2_1.setBounds(36, 269, 261, 27);
		panel_1.add(lblAccountManagement_1_2_1);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBookId.setBounds(36, 183, 92, 26);
		panel_1.add(lblBookId);
		
		JLabel lblBorrowDate = new JLabel("Borrow Date");
		lblBorrowDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblBorrowDate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBorrowDate.setBounds(36, 232, 105, 27);
		panel_1.add(lblBorrowDate);
		
		txtDelay = new JTextField();
		txtDelay.setForeground(Color.BLACK);
		txtDelay.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtDelay.setColumns(10);
		txtDelay.setBounds(311, 350, 261, 26);
		panel_1.add(txtDelay);
		
		txtFine = new JTextField();
		txtFine.setForeground(Color.BLACK);
		txtFine.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtFine.setColumns(10);
		txtFine.setBounds(311, 397, 261, 26);
		panel_1.add(txtFine);
		
		JLabel lblNoOfDelay = new JLabel("No of delay dates");
		lblNoOfDelay.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoOfDelay.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNoOfDelay.setBounds(36, 350, 134, 27);
		panel_1.add(lblNoOfDelay);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateFineAndDelay();
			}
		});
		btnCalculate.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnCalculate.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnCalculate.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnCalculate.setForeground(Color.WHITE);
		btnCalculate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCalculate.setBackground(Color.BLACK);
		btnCalculate.setBounds(36, 448, 105, 27);
		panel_1.add(btnCalculate);
		
		dateChooserReturn = new JDateChooser();
		dateChooserReturn.setForeground(Color.BLACK);
		dateChooserReturn.setDateFormatString("yyyy-MM-dd");
		dateChooserReturn.setBounds(311, 307, 261, 26);
		panel_1.add(dateChooserReturn);
		
		dateChooserBorrow = new JDateChooser();
		dateChooserBorrow.setForeground(Color.BLACK);
		dateChooserBorrow.setDateFormatString("yyyy-MM-dd");
		dateChooserBorrow.setBounds(311, 232, 261, 26);
		panel_1.add(dateChooserBorrow);
		
		JLabel lblRecordId_1 = new JLabel("Record ID");
		lblRecordId_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecordId_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRecordId_1.setBounds(36, 92, 85, 27);
		panel_1.add(lblRecordId_1);
		
		txtRecordID = new JTextField();
		txtRecordID.setForeground(Color.BLACK);
		txtRecordID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtRecordID.setEditable(false);
		txtRecordID.setColumns(10);
		txtRecordID.setBounds(311, 92, 261, 26);
		panel_1.add(txtRecordID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 833, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void show() {
		frame.setVisible(true);
	}
	
	private void fetchBookIDs() {
		try (Connection conn = DatabaseConnection.getConnection()) {
	        String query = "SELECT DISTINCT RecordID FROM BorrowRecords WHERE ReturnDate IS NULL";
	        try (PreparedStatement ps = conn.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            cmbSearch.removeAllItems(); //Remove all items from the item list
	            recordIDList.clear(); //clears all elements from a List or ArrayList object 

	            while (rs.next()) {
	                String recordID = rs.getString("RecordID"); 
	                recordIDList.add(recordID);
	                cmbSearch.addItem(recordID); 
	            }

	            System.out.println("ComboBox populated with not returned books: " + recordIDList); 
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Error fetching not returned Record IDs: " + e.getMessage());
	    }
    }
	
	private void loadBorrowDataFromSearch() {
	    String recordID = (String) cmbSearch.getSelectedItem();
	    System.out.println("Selected RecordID: " + recordID);

	    if (recordID == null || recordID.isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "Please select a valid Record ID.");
	        return;
	    }

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        String query = "SELECT * FROM BorrowRecords WHERE RecordID=?";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, recordID);
	            
	            System.out.println("Executing query: " + query + " with RecordID=" + recordID);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	txtRecordID.setText(rs.getString("RecordID"));
	                    txtStudentID.setText(rs.getString("StudentID"));
	                    txtBookID.setText(rs.getString("BookID"));

	                    Date borrowDate = rs.getDate("BorrowDate");
	                    if (borrowDate != null) {
	                        dateChooserBorrow.setDate(borrowDate);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(frame, "No book found with the selected Record ID.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Error loading record details: " + e.getMessage());
	    }
	}
	
	private void calculateDelay() {
	    java.util.Date borrowDate = dateChooserBorrow.getDate();
	    Date borrowDate_Conv = new Date(borrowDate.getTime());
	    java.util.Date returnDate = dateChooserReturn.getDate();
	    Date returnDate_Conv = new Date(returnDate.getTime());

	    if (borrowDate != null && returnDate != null) {
	        // Calculate the number of delay days
	        long delayInMillis = returnDate_Conv.getTime() - borrowDate_Conv.getTime();
	        long delayDays = delayInMillis / (1000 * 60 * 60 * 24); // Convert milliseconds to days

	        if (delayDays > 0) {
	            txtDelay.setText(String.valueOf(delayDays)); 
	            calculateFine(); 
	        } else {
	            txtDelay.setText("0"); 
	            txtFine.setText("0");  
	        }
	    }
	}

	private void calculateFine() {
	    try {
	        int delayDays = Integer.parseInt(txtDelay.getText());
	        
	        if (delayDays > 14) {
	        	double fineAmount = delayDays * FINE_PER_DAY; // Ensure fineAmount is treated as a double
		        txtFine.setText(String.format("$%.2f", fineAmount));
	        } else {
	        	double fineAmount = 0;
	        	txtFine.setText(String.format("$%.2f", fineAmount));
	        }  
	    } catch (NumberFormatException ex) {
	        txtFine.setText("$0.00"); 
	    }
	}

	private void calculateFineAndDelay() {
	    calculateDelay(); 
	    calculateFine();
	}
	
	private void updateReturnDateAndFine(JTextField txtRecordID, JTextField txtFine, JTextField txtBookID, JDateChooser dateChooserReturn) {
	    String recordID = txtRecordID.getText();
	    String fineText = txtFine.getText();
	    String bookID = txtBookID.getText().trim();
	    Date returnDate = dateChooserReturn.getDate();
	    java.sql.Date returnDate_Conv = new java.sql.Date(returnDate.getTime());

	    if (recordID.isEmpty() || fineText.isEmpty() || returnDate == null) {
	        JOptionPane.showMessageDialog(frame, "All fields are required.");
	        return;
	    }

	    try {
	        // Remove the "$" symbol and convert fineText to a numeric value
	        fineText = fineText.replace("$", "").trim();
	        double fineAmount = Double.parseDouble(fineText); // Parse as double to handle decimals

	        try (Connection conn = DatabaseConnection.getConnection()) {
	            String query = "UPDATE BorrowRecords SET ReturnDate = ?, Fine = ? WHERE RecordID = ?";
	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setDate(1, returnDate_Conv);
	                ps.setDouble(2, fineAmount); 
	                ps.setString(3, recordID);
	                ps.executeUpdate();
	                
	                //Update the database avilable copies
	                String updateBookQuery = "UPDATE Books SET AvailableCopies = AvailableCopies + 1 WHERE BookID = ?";
	                try (PreparedStatement psUpdateBook = conn.prepareStatement(updateBookQuery)) {
	                    psUpdateBook.setString(1, bookID);
	                    psUpdateBook.executeUpdate();
	                }
	                
	                JOptionPane.showMessageDialog(frame, "Record updated successfully!");
	            }
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(frame, "Invalid fine amount. Please check the fine value.");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Error updating record: " + e.getMessage());
	    }
	}
}
