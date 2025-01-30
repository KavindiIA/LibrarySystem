package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseEvent;

public class LibrarianDashboard {

	private JFrame frame;
	private JTextField txtTotLib;
	private JTextField txtStu;
	private JTextField txtFine;
	private JTextField txtTotBook;
	private JTextField txtBorroBook;
	private JTextField txtAvailBook;
	private String totalStudents;
	private String totalLibrarians;
	private double collectedFine;
	private String totalBooks;
	private String borrowedBooks;
	private String availBooks;
	private String formattedFine;

	/**
	 * Create the application.
	 */
	public LibrarianDashboard() {
		initialize();
		displayTotalStudents();
		displayTotalLibrarians();
		displayTotalBooks();
		displayAvailableBooks();
		displayBorrowedBooks();
		displayTotalFineCollected();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblOverview = new JLabel("Overview");
		lblOverview.setForeground(Color.BLACK);
		lblOverview.setHorizontalAlignment(SwingConstants.LEFT);
		lblOverview.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblOverview.setBounds(94, 38, 93, 27);
		panel.add(lblOverview);
		
		JLabel lblBooksDetails = new JLabel("Books Details");
		lblBooksDetails.setForeground(Color.BLACK);
		lblBooksDetails.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	lblBooksDetails.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblBooksDetails.setForeground(java.awt.Color.BLACK); // Reset to the original color
		    }
			@Override
			public void mouseClicked(MouseEvent e) {
				BookDetails book = new BookDetails();
				book.show();
			}
		});
		lblBooksDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblBooksDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBooksDetails.setBounds(21, 144, 107, 27);
		panel.add(lblBooksDetails);
		
		JLabel lblBorrowDetails = new JLabel("Borrow Details");
		lblBorrowDetails.setForeground(Color.BLACK);
		lblBorrowDetails.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        lblBorrowDetails.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        lblBorrowDetails.setForeground(java.awt.Color.BLACK); // Reset to the original color
		    }
			@Override
			public void mouseClicked(MouseEvent e) {
				BorrowBookDetails borrow = new BorrowBookDetails();
				borrow.show();
			}
		});
		lblBorrowDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblBorrowDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBorrowDetails.setBounds(21, 107, 113, 27);
		panel.add(lblBorrowDetails);
		
		JLabel lblBorrowBooks = new JLabel("Borrow Books");
		lblBorrowBooks.setForeground(Color.BLACK);
		lblBorrowBooks.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	lblBorrowBooks.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblBorrowBooks.setForeground(java.awt.Color.BLACK); // Reset to the original color
		    }
			@Override
			public void mouseClicked(MouseEvent e) {
				BorrowBooks b = new BorrowBooks();
				b.show();
			}
		});
		lblBorrowBooks.setHorizontalAlignment(SwingConstants.LEFT);
		lblBorrowBooks.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBorrowBooks.setBounds(21, 183, 113, 27);
		panel.add(lblBorrowBooks);
		
		JLabel lblReturnBooks = new JLabel("Return Books");
		lblReturnBooks.setForeground(Color.BLACK);
		lblReturnBooks.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	lblReturnBooks.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblReturnBooks.setForeground(java.awt.Color.BLACK); // Reset to the original color
		    }
			@Override
			public void mouseClicked(MouseEvent e) {
				ReturnBooks re = new ReturnBooks();
				re.show();
			}
		});
		lblReturnBooks.setHorizontalAlignment(SwingConstants.LEFT);
		lblReturnBooks.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblReturnBooks.setBounds(21, 220, 107, 27);
		panel.add(lblReturnBooks);
		
		JLabel lblReports = new JLabel("Reports");
		JPopupMenu popupMenuR = new JPopupMenu();

        // Add menu items to the popup menu
        JMenuItem op1 = new JMenuItem("Non-Returned Books Report");
        JMenuItem op2 = new JMenuItem("Monthly Fine Report");
        JMenuItem op3 = new JMenuItem("Newly Added Books Report");

        popupMenuR.add(op1);
        popupMenuR.add(op2);
        popupMenuR.add(op3);

        // Add action listeners to menu items
        op1.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Generating Non-Returned Books Report");
            try {
                String jrxmlFilePath = "/reports/UnreturnedBooks.jrxml";

                // Compile the JRXML file to a JasperReport
                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);
                Connection connection = DatabaseConnection.getConnection();

                // Fill the report with data from the database
                JasperPrint jasper = JasperFillManager.fillReport(jasperReport, null, connection);

                JasperViewer.viewReport(jasper);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to generate report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        op2.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Generating Monthly Fine Report");
            try {
                String jrxmlFilePath = "/reports/MonthlyFine.jrxml";

             // Compile the JRXML file to a JasperReport
                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);
                Connection connection = DatabaseConnection.getConnection();

                // Fill the report with data from the database
                JasperPrint jasper = JasperFillManager.fillReport(jasperReport, null, connection);

                JasperViewer.viewReport(jasper);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to generate report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        op3.addActionListener(e -> {
        	JOptionPane.showMessageDialog(frame, "Generating Newly Added Books Report");
            try {
                String jrxmlFilePath = "/reports/NewlyAddedBooks.jrxml";

             // Compile the JRXML file to a JasperReport
                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);
                Connection connection = DatabaseConnection.getConnection();

                // Fill the report with data from the database
                JasperPrint jasper = JasperFillManager.fillReport(jasperReport, null, connection);

                JasperViewer.viewReport(jasper);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to generate report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
		lblReports.setForeground(Color.BLACK);
		lblReports.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	lblReports.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblReports.setForeground(java.awt.Color.BLACK); // Reset to the original color
		    }
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button
                    popupMenuR.show(lblReports, e.getX(), e.getY());
                }
			}
		});
		lblReports.setHorizontalAlignment(SwingConstants.LEFT);
		lblReports.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblReports.setBounds(21, 255, 137, 27);
		panel.add(lblReports);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnLogOut.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnLogOut.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnLogOut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.dispose(); 
		        LogIn logIn = new LogIn();
		        logIn.show();
		    }
		});
		
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLogOut.setBackground(Color.BLACK);
		btnLogOut.setBounds(54, 442, 101, 27);
		panel.add(btnLogOut);
		
		JPanel panel_2_6 = new JPanel();
		panel_2_6.setBackground(Color.BLACK);
		panel_2_6.setBounds(0, 93, 209, 4);
		panel.add(panel_2_6);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setIcon(new ImageIcon(LibrarianDashboard.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setBounds(21, 21, 63, 62);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to The Library Management System");
		lblWelcomeToThe.setBounds(106, 34, 437, 26);
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel_1.add(lblWelcomeToThe);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(218, 112, 214));
		panel_2.setBounds(35, 92, 157, 77);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("No of Librarians");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 10, 137, 24);
		panel_2.add(lblNewLabel_2);
		
		txtTotLib = new JTextField(""+totalLibrarians);
		txtTotLib.setEditable(false);
		txtTotLib.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotLib.setBackground(new Color(148, 0, 211));
		txtTotLib.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTotLib.setBounds(10, 48, 96, 19);
		panel_2.add(txtTotLib);
		txtTotLib.setColumns(10);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(218, 112, 214));
		panel_2_1.setBounds(230, 92, 157, 77);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("No of Students");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(10, 10, 137, 24);
		panel_2_1.add(lblNewLabel_2_1);
		
		txtStu = new JTextField(""+totalStudents);
		txtStu.setEditable(false);
		txtStu.setHorizontalAlignment(SwingConstants.CENTER);
		txtStu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtStu.setColumns(10);
		txtStu.setBackground(new Color(148, 0, 211));
		txtStu.setBounds(10, 48, 96, 19);
		panel_2_1.add(txtStu);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(new Color(218, 112, 214));
		panel_2_2.setBounds(421, 92, 157, 77);
		panel_1.add(panel_2_2);
		panel_2_2.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("Collected Fine ");
		lblNewLabel_2_2.setForeground(Color.BLACK);
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(10, 10, 137, 24);
		panel_2_2.add(lblNewLabel_2_2);
		
		txtFine = new JTextField(""+formattedFine);
		txtFine.setEditable(false);
		txtFine.setHorizontalAlignment(SwingConstants.CENTER);
		txtFine.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtFine.setColumns(10);
		txtFine.setBackground(new Color(148, 0, 211));
		txtFine.setBounds(10, 48, 96, 19);
		panel_2_2.add(txtFine);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBackground(new Color(218, 112, 214));
		panel_2_3.setBounds(35, 193, 157, 77);
		panel_1.add(panel_2_3);
		panel_2_3.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("Total Books");
		lblNewLabel_2_3.setForeground(Color.BLACK);
		lblNewLabel_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_3.setBounds(10, 10, 137, 24);
		panel_2_3.add(lblNewLabel_2_3);
		
		txtTotBook = new JTextField(""+totalBooks);
		txtTotBook.setEditable(false);
		txtTotBook.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotBook.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTotBook.setColumns(10);
		txtTotBook.setBackground(new Color(148, 0, 211));
		txtTotBook.setBounds(10, 48, 96, 19);
		panel_2_3.add(txtTotBook);
		
		JPanel panel_2_4 = new JPanel();
		panel_2_4.setBackground(new Color(218, 112, 214));
		panel_2_4.setBounds(35, 292, 157, 77);
		panel_1.add(panel_2_4);
		panel_2_4.setLayout(null);
		
		JLabel lblNewLabel_2_4 = new JLabel("Borrowed Books");
		lblNewLabel_2_4.setForeground(Color.BLACK);
		lblNewLabel_2_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_4.setBounds(10, 10, 137, 24);
		panel_2_4.add(lblNewLabel_2_4);
		
		txtBorroBook = new JTextField(""+borrowedBooks);
		txtBorroBook.setEditable(false);
		txtBorroBook.setHorizontalAlignment(SwingConstants.CENTER);
		txtBorroBook.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtBorroBook.setColumns(10);
		txtBorroBook.setBackground(new Color(148, 0, 211));
		txtBorroBook.setBounds(10, 48, 96, 19);
		panel_2_4.add(txtBorroBook);
		
		JPanel panel_2_5 = new JPanel();
		panel_2_5.setBackground(new Color(218, 112, 214));
		panel_2_5.setBounds(35, 393, 157, 77);
		panel_1.add(panel_2_5);
		panel_2_5.setLayout(null);
		
		JLabel lblNewLabel_2_5 = new JLabel("Available Books");
		lblNewLabel_2_5.setForeground(Color.BLACK);
		lblNewLabel_2_5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2_5.setBounds(10, 10, 137, 24);
		panel_2_5.add(lblNewLabel_2_5);
		
		txtAvailBook = new JTextField(""+availBooks);
		txtAvailBook.setEditable(false);
		txtAvailBook.setHorizontalAlignment(SwingConstants.CENTER);
		txtAvailBook.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtAvailBook.setColumns(10);
		txtAvailBook.setBackground(new Color(148, 0, 211));
		txtAvailBook.setBounds(10, 48, 96, 19);
		panel_2_5.add(txtAvailBook);
		
		JLabel lblNewLabel = new JLabel("dsdf");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setIcon(new ImageIcon(LibrarianDashboard.class.getResource("/images/ffgf.jpg")));
		lblNewLabel.setBounds(230, 251, 348, 240);
		panel_1.add(lblNewLabel);
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
	
	private void displayTotalStudents() {
	    String query = "SELECT COUNT(*) AS TotalStudents FROM Students";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
                totalStudents = rs.getString("TotalStudents");
                
                System.out.println("Total Students: " + totalStudents); 
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error retrieving total students: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void displayTotalLibrarians() {
	    String query = "SELECT COUNT(*) AS totalLibrarians FROM AdminsAndLibrarians WHERE Role = 'Librarian'";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	totalLibrarians = rs.getString("totalLibrarians");
                
                System.out.println("Total Librarians: " + totalLibrarians); 
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error retrieving total librarians: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void displayTotalBooks() {
	    String query = "SELECT SUM(TotalCopies) AS totalBooks FROM Books";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	totalBooks = rs.getString("totalBooks");
                
                System.out.println("Total Books: " + totalBooks); 
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error retrieving total books: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void displayAvailableBooks() {
	    String query = "SELECT SUM(AvailableCopies) AS availBooks FROM Books";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	availBooks = rs.getString("availBooks");
                
                System.out.println("Available Books: " + availBooks); 
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error retrieving available books: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void displayBorrowedBooks() {
	    String query = "SELECT SUM(TotalCopies) - SUM(AvailableCopies) AS borrowedBooks FROM Books";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	borrowedBooks = rs.getString("borrowedBooks");
                
                System.out.println("Borrowed Books: " + borrowedBooks); 
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(frame, "Error retrieving borrowed books: " + e.getMessage(),
	                "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void displayTotalFineCollected() {
	    // SQL query to calculate the sum of fines collected
	    String query = "SELECT SUM(Fine) AS collectedFine FROM BorrowRecords";

	    try (Connection con = DatabaseConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	collectedFine = rs.getDouble("collectedFine"); // Retrieve the total fine as a double
	        	formattedFine = String.format("$%.2f", collectedFine);
	            
	            // Display the result
	            System.out.println("Total Fine Collected: " + formattedFine);
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, 
	            "Error retrieving total fine: " + e.getMessage(),
	            "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
}
