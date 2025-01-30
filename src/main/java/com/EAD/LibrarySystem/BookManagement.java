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
import java.util.List;
import java.awt.event.ActionEvent;

public class BookManagement {

	private JFrame frame;
	private JTextField txtBookID;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtGenre;
	private JTextField txtTotCopies;
	private JTextField txtAvailCopies;
	private JComboBox<String> cmbSearch;
	private List<String> bookIDList = new ArrayList<>();

	/**
	 * Create the application.
	 */
	public BookManagement() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountManagement_1 = new JLabel("Book");
		lblAccountManagement_1.setForeground(Color.BLACK);
		lblAccountManagement_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1.setBounds(94, 32, 93, 27);
		panel.add(lblAccountManagement_1);
		
		JLabel lblDetails = new JLabel("Search Here!");
		lblDetails.setForeground(Color.BLACK);
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
		
		JLabel lblAccountManagement_1_1 = new JLabel("Management");
		lblAccountManagement_1_1.setForeground(Color.BLACK);
		lblAccountManagement_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1_1.setBounds(94, 58, 105, 27);
		panel.add(lblAccountManagement_1_1);
		
		cmbSearch = new JComboBox<>();
		cmbSearch.setForeground(Color.BLACK);
		cmbSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("ComboBox clicked!");
				loadBookDataFromSearch();
			}
		});
		cmbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbSearch.setBounds(31, 162, 168, 27);
		panel.add(cmbSearch);
		fetchBookIDs();
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBookID.setText(""); 
				txtTitle.setText(""); 
				txtGenre.setText(""); 
				txtTotCopies.setText(""); 
				txtAvailCopies.setText("");
				txtAuthor.setText(""); 
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
		
		JButton btnView = new JButton("View ");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDetails bod = new BookDetails();
				bod.show();
			}
		});
		btnView.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnView.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnView.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnView.setForeground(Color.WHITE);
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnView.setBackground(Color.BLACK);
		btnView.setBounds(51, 362, 105, 27);
		panel.add(btnView);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 103, 209, 4);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(BookManagement.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 20, 74, 73);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		
		JLabel lblBooks = new JLabel("Books");
		lblBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooks.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblBooks.setBounds(98, 34, 437, 26);
		panel_1.add(lblBooks);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setForeground(Color.BLACK);
		lblBookId.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBookId.setBounds(36, 93, 178, 27);
		panel_1.add(lblBookId);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTitle.setBounds(36, 168, 178, 27);
		panel_1.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setForeground(Color.BLACK);
		lblAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAuthor.setBounds(36, 217, 178, 27);
		panel_1.add(lblAuthor);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setForeground(Color.BLACK);
		lblGenre.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblGenre.setBounds(36, 266, 178, 27);
		panel_1.add(lblGenre);
		
		JLabel lblTotalCopies = new JLabel("Total Copies");
		lblTotalCopies.setForeground(Color.BLACK);
		lblTotalCopies.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblTotalCopies.setBounds(36, 305, 178, 27);
		panel_1.add(lblTotalCopies);
		
		JLabel lblAvailCopies = new JLabel("Available Copies");
		lblAvailCopies.setForeground(Color.BLACK);
		lblAvailCopies.setHorizontalAlignment(SwingConstants.LEFT);
		lblAvailCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblAvailCopies.setBounds(36, 348, 178, 27);
		panel_1.add(lblAvailCopies);
		
		txtBookID = new JTextField();
		txtBookID.setForeground(Color.BLACK);
		txtBookID.setEditable(false);
		txtBookID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtBookID.setColumns(10);
		txtBookID.setBounds(311, 94, 261, 26);
		panel_1.add(txtBookID);
		
		txtTitle = new JTextField();
		txtTitle.setForeground(Color.BLACK);
		txtTitle.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtTitle.setColumns(10);
		txtTitle.setBounds(311, 169, 261, 26);
		panel_1.add(txtTitle);
		
		txtAuthor = new JTextField();
		txtAuthor.setForeground(Color.BLACK);
		txtAuthor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(311, 218, 261, 26);
		panel_1.add(txtAuthor);
		
		txtGenre = new JTextField();
		txtGenre.setForeground(Color.BLACK);
		txtGenre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtGenre.setColumns(10);
		txtGenre.setBounds(311, 267, 261, 26);
		panel_1.add(txtGenre);
		
		txtTotCopies = new JTextField();
		txtTotCopies.setForeground(Color.BLACK);
		txtTotCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtTotCopies.setColumns(10);
		txtTotCopies.setBounds(311, 306, 261, 26);
		panel_1.add(txtTotCopies);
		
		txtAvailCopies = new JTextField();
		txtAvailCopies.setForeground(Color.BLACK);
		txtAvailCopies.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtAvailCopies.setColumns(10);
		txtAvailCopies.setBounds(311, 349, 261, 26);
		panel_1.add(txtAvailCopies);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRecords();
			}
		});
		btnInsert.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnInsert.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnInsert.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setBounds(36, 438, 105, 27);
		panel_1.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedBookID = txtBookID.getText().trim();
			    if (selectedBookID != null && !selectedBookID.isEmpty()) {
			        int confirmed = JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to delete the book with BookID: " + selectedBookID + "?", 
			            "Delete Book", JOptionPane.YES_NO_OPTION);
			        if (confirmed == JOptionPane.YES_OPTION) {
			            try (Connection conn = DatabaseConnection.getConnection()) {
			                String query = "DELETE FROM Books WHERE BookID = ?";
			                try (PreparedStatement ps = conn.prepareStatement(query)) {
			                    ps.setString(1, selectedBookID);  
			                    int rowsAffected = ps.executeUpdate();
			                    if (rowsAffected > 0) {
			                        JOptionPane.showMessageDialog(frame, "Book deleted successfully.");
			                        fetchBookIDs(); // Refresh the list in ComboBox
			                        txtBookID.setText("");
			        				txtTitle.setText("");
			        				txtAuthor.setText("");
			        				txtGenre.setText("");
			        				txtTotCopies.setText("");
			        				txtAvailCopies.setText("");
			                    } else {
			                        JOptionPane.showMessageDialog(frame, "No book found with the given BookID.");
			                    }
			                }
			            } catch (SQLException ex) {
			                JOptionPane.showMessageDialog(frame, "Error deleting book: " + ex.getMessage());
			            }
			        }
			    } else {
			        JOptionPane.showMessageDialog(frame, "Please enter a valid BookID.");
			    }
			}
		});
		btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDelete.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnDelete.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setBounds(467, 438, 105, 27);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookID = txtBookID.getText();
		        String title = txtTitle.getText();
		        String author = txtAuthor.getText();
		        String genre = txtGenre.getText();
		        String totCopies = txtTotCopies.getText();
		        String availCopies = txtAvailCopies.getText();

		        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || totCopies.isEmpty() || availCopies.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please fill all fields.");
		            return;
		        }

		        try (Connection conn = DatabaseConnection.getConnection()) {
		            String query = "UPDATE Books SET Title=?, Author=?, Genre=?, TotalCopies=?, AvailableCopies=? WHERE BookID=?";
		            try (PreparedStatement ps = conn.prepareStatement(query)) {
		                ps.setString(1, title);
		                ps.setString(2, author);
		                ps.setString(3, genre);
		                ps.setInt(4, Integer.parseInt(totCopies));
		                ps.setInt(5, Integer.parseInt(availCopies));
		                ps.setString(6, bookID);

		                int rowsAffected = ps.executeUpdate();
		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(frame, "Book updated successfully.");
		                }
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Error updating book: " + ex.getMessage());
		        }
			}
		});
		btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnUpdate.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnUpdate.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(255, 438, 105, 27);
		panel_1.add(btnUpdate);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.BLACK);
		panel_2_1.setBounds(24, 130, 555, 4);
		panel_1.add(panel_2_1);
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
                cmbSearch.removeAllItems(); // Clear previous items
                bookIDList.clear(); // Clear the bookID list

                while (rs.next()) {
                    String bookID = rs.getString("BookID"); // Fetch bookID as a string
                    bookIDList.add(bookID);
                    cmbSearch.addItem(bookID); // Add bookID to ComboBox
                }
                System.out.println("ComboBox populated: " + bookIDList); // Debug line
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching Book IDs: " + e.getMessage());
        }
    }
	
	private void loadBookDataFromSearch() {
	    String bookID = (String) cmbSearch.getSelectedItem();
	    
	    // Debugging: Print the selected bookID to check if it's valid
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
	                    // Populate the fields with data
	                    txtBookID.setText(rs.getString("BookID"));
	                    txtTitle.setText(rs.getString("Title"));
	                    txtAuthor.setText(rs.getString("Author"));
	                    txtGenre.setText(rs.getString("Genre"));
	                    txtTotCopies.setText(rs.getString("TotalCopies"));
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

	
	private void insertRecords() {
        String title = txtTitle.getText().trim();
        String author = txtAuthor.getText().trim();
        String genre = txtGenre.getText().trim();
        String totalCopies = txtTotCopies.getText().trim();
        String availCopies = txtAvailCopies.getText().trim();

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || totalCopies.isEmpty() || availCopies.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
        } else {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO Books (Title, Author, Genre, TotalCopies, AvailableCopies) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, title);
                    ps.setString(2, author);
                    ps.setString(3, genre);
                    ps.setInt(4, Integer.parseInt(totalCopies));
                    ps.setInt(5, Integer.parseInt(availCopies));

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Book inserted successfully.");
                        fetchBookIDs(); // Refresh the list in ComboBox
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error inserting the book.");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error inserting book: " + ex.getMessage());
            }
        }
	}

}
