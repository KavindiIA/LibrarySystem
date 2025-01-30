package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
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
import com.toedter.calendar.JDateChooser;

public class BorrowBooks {

	private JFrame frame;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtAvailCopies;
	private JTextField txtStudentID;
	private JTextField txtBookID;
	private JDateChooser dateChooserBorrow;
	private JComboBox<String> cmbSearch;
	private List<String> bookIDList = new ArrayList<>();

	/**
	 * Create the application.
	 */
	public BorrowBooks() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountManagement_1 = new JLabel("Borrow");
		lblAccountManagement_1.setForeground(Color.BLACK);
		lblAccountManagement_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1.setBounds(94, 32, 93, 27);
		panel.add(lblAccountManagement_1);
		
		JLabel lblDetails = new JLabel("Search Here!");
		lblDetails.setForeground(Color.BLACK);
		lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDetails.setBounds(21, 129, 178, 27);
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
		lblAccountManagement_1_1.setForeground(Color.BLACK);
		lblAccountManagement_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1_1.setBounds(94, 58, 105, 27);
		panel.add(lblAccountManagement_1_1);
		
		cmbSearch = new JComboBox<>();
		cmbSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("ComboBox clicked!");
				loadBookDataFromSearch();
			}
		});
		cmbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbSearch.setBounds(31, 166, 168, 27);
		panel.add(cmbSearch);
		fetchBookIDs();
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitle.setText(""); 
				txtGenre.setText(""); 
				txtBookID.setText(""); 
				txtAvailCopies.setText("");
				txtAuthor.setText("");
				txtStudentID.setText("");
				dateChooserBorrow.setDate(new Date());
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
		panel_2.setBounds(0, 104, 209, 4);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(BorrowBooks.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 21, 74, 73);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		
		JLabel lblBorrowBooks = new JLabel("Borrow Books");
		lblBorrowBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrowBooks.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblBorrowBooks.setBounds(98, 34, 437, 26);
		panel_1.add(lblBorrowBooks);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTitle.setBounds(36, 103, 178, 27);
		panel_1.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(Color.BLACK);
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAuthor.setBounds(36, 151, 178, 27);
		panel_1.add(lblAuthor);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setForeground(Color.BLACK);
		lblGenre.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblGenre.setBounds(36, 200, 178, 27);
		panel_1.add(lblGenre);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblStudentId.setBounds(36, 353, 85, 27);
		panel_1.add(lblStudentId);
		
		JLabel lblAvailCopies = new JLabel("Available Copies");
		lblAvailCopies.setForeground(Color.BLACK);
		lblAvailCopies.setHorizontalAlignment(SwingConstants.LEFT);
		lblAvailCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAvailCopies.setBounds(36, 249, 178, 27);
		panel_1.add(lblAvailCopies);
		
		txtTitle = new JTextField();
		txtTitle.setForeground(Color.BLACK);
		txtTitle.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtTitle.setColumns(10);
		txtTitle.setBounds(311, 103, 261, 26);
		panel_1.add(txtTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setForeground(Color.BLACK);
		txtAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(311, 151, 261, 26);
		panel_1.add(txtAuthor);
		
		txtGenre = new JTextField();
		txtGenre.setForeground(Color.BLACK);
		txtGenre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtGenre.setColumns(10);
		txtGenre.setBounds(311, 200, 261, 26);
		panel_1.add(txtGenre);
		
		txtAvailCopies = new JTextField();
		txtAvailCopies.setForeground(Color.BLACK);
		txtAvailCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtAvailCopies.setColumns(10);
		txtAvailCopies.setBounds(311, 249, 261, 26);
		panel_1.add(txtAvailCopies);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertData(txtStudentID, txtBookID, dateChooserBorrow);
			}
		});
		btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnEnter.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnEnter.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnEnter.setBackground(Color.BLACK);
		btnEnter.setBounds(467, 438, 105, 27);
		panel_1.add(btnEnter);
		
		JLabel lblAccountManagement_1_2 = new JLabel("Book Details");
		lblAccountManagement_1_2.setForeground(new Color(255, 20, 147));
		lblAccountManagement_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAccountManagement_1_2.setBounds(36, 66, 110, 27);
		panel_1.add(lblAccountManagement_1_2);
		
		JLabel lblAccountManagement_1_2_1 = new JLabel("Borrow Details");
		lblAccountManagement_1_2_1.setForeground(new Color(255, 20, 147));
		lblAccountManagement_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAccountManagement_1_2_1.setBounds(36, 305, 123, 27);
		panel_1.add(lblAccountManagement_1_2_1);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setForeground(Color.BLACK);
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBookId.setBounds(36, 393, 92, 26);
		panel_1.add(lblBookId);
		
		JLabel lblBorrowDate = new JLabel("Borrow Date");
		lblBorrowDate.setForeground(Color.BLACK);
		lblBorrowDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblBorrowDate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBorrowDate.setBounds(36, 438, 105, 27);
		panel_1.add(lblBorrowDate);
		
		txtStudentID = new JTextField();
		txtStudentID.setForeground(Color.BLACK);
		txtStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(151, 353, 247, 26);
		panel_1.add(txtStudentID);
		
		txtBookID = new JTextField();
		txtBookID.setForeground(Color.BLACK);
		txtBookID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtBookID.setColumns(10);
		txtBookID.setBounds(151, 393, 247, 26);
		panel_1.add(txtBookID);
		
		dateChooserBorrow = new JDateChooser();
		dateChooserBorrow.setForeground(Color.BLACK);
		dateChooserBorrow.setDateFormatString("yyyy-MM-dd");
		dateChooserBorrow.setBounds(151, 438, 247, 27);
		panel_1.add(dateChooserBorrow);
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
            String query = "SELECT BookID FROM Books";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                cmbSearch.removeAllItems(); 
                bookIDList.clear(); 

                while (rs.next()) {
                    String bookID = rs.getString("BookID"); 
                    bookIDList.add(bookID);
                    cmbSearch.addItem(bookID); 
                }
                System.out.println("ComboBox populated: " + bookIDList);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching Book IDs: " + e.getMessage());
        }
    }
	
	private void loadBookDataFromSearch() {
	    String bookID = (String) cmbSearch.getSelectedItem();
	    
	    System.out.println("Selected BookID: " + bookID);

	    if (bookID == null || bookID.isEmpty()) {
	        JOptionPane.showMessageDialog(frame, "Please select a valid Book ID.");
	        return;
	    }

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        String query = "SELECT * FROM Books WHERE BookID=?";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, bookID);
	            
	            // Debugging: Print the query and the value of bookID
	            System.out.println("Executing query: " + query + " with BookID=" + bookID);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    txtTitle.setText(rs.getString("Title"));
	                    txtAuthor.setText(rs.getString("Author"));
	                    txtGenre.setText(rs.getString("Genre"));
	                    txtAvailCopies.setText(rs.getString("AvailableCopies"));
	                } else {
	                    JOptionPane.showMessageDialog(frame, "No book found with the selected Book ID.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        // Handle any SQL exceptions
	        JOptionPane.showMessageDialog(frame, "Error loading book details: " + e.getMessage());
	    }
	}
	
	private void InsertData(JTextField txtStudentID, JTextField txtBookID, JDateChooser dateChooserBorrow) {
	    String studentID = txtStudentID.getText().trim();
	    String bookID = txtBookID.getText().trim();
	    Date borrowDate = dateChooserBorrow.getDate();
	    java.sql.Date borrowDate_Conv = new java.sql.Date(borrowDate.getTime());

	    // Validation: Check for empty fields
	    if (studentID.isEmpty() || bookID.isEmpty() || borrowDate == null) {
	        JOptionPane.showMessageDialog(frame, "Please fill in all fields. None of the fields can be empty.",
	                "Validation Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // First, check if the book is available (if AvailableCopies > 0)
	    try (Connection conn = DatabaseConnection.getConnection()) {
	        String checkQuery = "SELECT AvailableCopies FROM Books WHERE BookID = ?";
	        try (PreparedStatement psCheck = conn.prepareStatement(checkQuery)) {
	            psCheck.setString(1, bookID);
	            ResultSet rs = psCheck.executeQuery();
	            if (rs.next()) {
	                int availableCopies = rs.getInt("AvailableCopies");
	                if (availableCopies <= 0) {
	                    JOptionPane.showMessageDialog(frame, "This book is not available for borrowing.",
	                            "Book Unavailable", JOptionPane.WARNING_MESSAGE);
	                    return;  // Exit if the book is not available
	                }

	                // Proceed with inserting the borrow record
	                String insertQuery = "INSERT INTO BorrowRecords (StudentID, BookID, BorrowDate) VALUES (?, ?, ?)";
	                try (PreparedStatement psInsert = conn.prepareStatement(insertQuery)) {
	                    psInsert.setString(1, studentID);
	                    psInsert.setString(2, bookID);
	                    psInsert.setDate(3, borrowDate_Conv);

	                    int rowsAffected = psInsert.executeUpdate();
	                    if (rowsAffected > 0) {
	                        String updateQuery = "UPDATE Books SET AvailableCopies = AvailableCopies - 1 WHERE BookID = ?";
	                        try (PreparedStatement psUpdate = conn.prepareStatement(updateQuery)) {
	                            psUpdate.setString(1, bookID);
	                            psUpdate.executeUpdate();
	                        }

	                        JOptionPane.showMessageDialog(frame, "Record added successfully! The book has been borrowed.",
	                                "Success", JOptionPane.INFORMATION_MESSAGE);
	                        txtStudentID.setText("");
	                        txtBookID.setText("");
	                        dateChooserBorrow.setDate(null);
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Error inserting record.", "Database Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            } else {
	                JOptionPane.showMessageDialog(frame, "Book not found.", "Database Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(frame, "Error inserting record: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
