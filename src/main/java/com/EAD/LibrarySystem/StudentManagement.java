package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
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
import javax.swing.JOptionPane;

public class StudentManagement {

	private JFrame frame;
	private JTextField txtStudentID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JComboBox<String> cmbSearch;
	private List<String> studentIDList = new ArrayList<>();

	/**
	 * Create the application.
	 */
	public StudentManagement() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountManagement_1 = new JLabel("Student");
		lblAccountManagement_1.setForeground(Color.BLACK);
		lblAccountManagement_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1.setBounds(94, 32, 93, 27);
		panel.add(lblAccountManagement_1);
		
		JLabel lblDetails = new JLabel("Search Here!");
		lblDetails.setForeground(Color.BLACK);
		lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDetails.setBounds(21, 122, 178, 27);
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
				loadStudentDataFromSearch();
			}
		});
		cmbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbSearch.setBounds(31, 159, 168, 27);
		panel.add(cmbSearch);
		fetchStudentIDs();
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					txtStudentID.setText("");    
					txtContact.setText(""); 
					txtName.setText(""); 
					txtEmail.setText(""); 
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
				StudentDetails std = new StudentDetails();
				std.show();
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
		lblNewLabel_1.setIcon(new ImageIcon(StudentManagement.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 20, 74, 73);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblStudents.setBounds(98, 34, 437, 26);
		panel_1.add(lblStudents);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(Color.BLACK);
		lblStudentId.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblStudentId.setBounds(62, 90, 178, 27);
		panel_1.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblName.setBounds(62, 182, 178, 27);
		panel_1.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblEmail.setBounds(62, 231, 178, 27);
		panel_1.add(lblEmail);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setForeground(Color.BLACK);
		lblContactNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactNo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblContactNo.setBounds(62, 280, 178, 27);
		panel_1.add(lblContactNo);
		
		txtStudentID = new JTextField();
		txtStudentID.setForeground(Color.BLACK);
		txtStudentID.setEditable(false);
		txtStudentID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtStudentID.setColumns(10);
		txtStudentID.setBounds(294, 90, 261, 26);
		panel_1.add(txtStudentID);
		
		txtName = new JTextField();
		txtName.setForeground(Color.BLACK);
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtName.setColumns(10);
		txtName.setBounds(294, 182, 261, 26);
		panel_1.add(txtName);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtEmail.setColumns(10);
		txtEmail.setBounds(294, 231, 261, 26);
		panel_1.add(txtEmail);
		
		txtContact = new JTextField();
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtContact.setColumns(10);
		txtContact.setBounds(294, 280, 261, 26);
		panel_1.add(txtContact);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertStudent();
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
				String selectedStudentID = txtStudentID.getText().trim();
			    if (selectedStudentID != null && !selectedStudentID.isEmpty()) {
			        int confirmed = JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to delete the stdent with StudentID: " + selectedStudentID + "?", 
			            "Delete Student", JOptionPane.YES_NO_OPTION);
			        if (confirmed == JOptionPane.YES_OPTION) {
			            try (Connection conn = DatabaseConnection.getConnection()) {
			                String query = "DELETE FROM Students WHERE StudentID = ?";
			                try (PreparedStatement ps = conn.prepareStatement(query)) {
			                    ps.setString(1, selectedStudentID);  
			                    int rowsAffected = ps.executeUpdate();
			                    if (rowsAffected > 0) {
			                        JOptionPane.showMessageDialog(frame, "Student deleted successfully.");
			                        fetchStudentIDs(); // Refresh the list in ComboBox
			                        txtStudentID.setText("");
			        				txtName.setText("");
			        				txtContact.setText("");
			        				txtEmail.setText("");
			                    } else {
			                        JOptionPane.showMessageDialog(frame, "No student found with the given StudentID.");
			                    }
			                }
			            } catch (SQLException ex) {
			                JOptionPane.showMessageDialog(frame, "Error deleting student: " + ex.getMessage());
			            }
			        }
			    } else {
			        JOptionPane.showMessageDialog(frame, "Please enter a valid StudentID.");
			    }
			}
		});
		btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDelete.setBackground(java.awt.Color.LIGHT_GRAY); // Change to the desired color
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
				String studentID = txtStudentID.getText();
		        String name = txtName.getText();
		        String email = txtEmail.getText();
		        String contact = txtContact.getText();

		        if (name.isEmpty() || email.isEmpty() || contact.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please fill all fields.");
		            return;
		        }

		        try (Connection conn = DatabaseConnection.getConnection()) {
		            String query = "UPDATE Students SET Name=?, Email=?, ContactNumber=? WHERE StudentID=?";
		            try (PreparedStatement ps = conn.prepareStatement(query)) {
		                ps.setString(1, name);
		                ps.setString(2, email);
		                ps.setString(3, contact);
		                ps.setString(4, studentID);

		                int rowsAffected = ps.executeUpdate();
		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(frame, "Student updated successfully.");
		                }
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Error updating student: " + ex.getMessage());
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
		panel_2_1.setBounds(30, 137, 555, 4);
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
	
	private void fetchStudentIDs() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT StudentID FROM Students";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cmbSearch.addItem(rs.getString("StudentID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private void loadStudentDataFromSearch() {
        String selectedStudentID = (String) cmbSearch.getSelectedItem(); 
        System.out.println("Selected UserID: " + selectedStudentID);

        if (selectedStudentID != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
            	String query = "SELECT * FROM Students WHERE StudentID = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, selectedStudentID);  
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("Student data found: " + rs.getString("StudentID"));
                            // Fill the text fields with data from the result set
                            txtStudentID.setText(rs.getString("StudentID"));
                            txtName.setText(rs.getString("Name"));
                            txtEmail.setText(rs.getString("Email"));
                            txtContact.setText(rs.getString("ContactNumber"));
                        } else {
                            System.out.println("No data found for UserID: " + selectedStudentID); 
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching user data: " + ex.getMessage());
            }
        }
    }
	
	private boolean validateContact(String contact) {
        if (contact.length() != 10 || !contact.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(frame, "Contact number must be 10 digits.");
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(frame, "Email must contain '@'.");
            return false;
        }
        return true;
    }

    private void insertStudent() {
    	String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        
    	if (name.isEmpty() || email.isEmpty() || contact.isEmpty() ) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            return;
        }

        if (!validateContact(contact) || !validateEmail(email)) {
            return;
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Students (Name, Email, ContactNumber) VALUES (?, ?, ?, )";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, txtName.getText());
                ps.setString(2, txtEmail.getText());
                ps.setString(3, txtContact.getText());
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Account successfully inserted!");
                    fetchStudentIDs();
                    txtStudentID.setText("");
    				txtEmail.setText("");
    				txtName.setText("");
    				txtContact.setText("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
