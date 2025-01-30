package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

public class AccountManagement {

	private JFrame frame;
	private JTextField txtUserID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JTextField txtUsername;
	private JPasswordField pswdPassword;
	JComboBox<String> cmbRole;
	private JComboBox<String> cmbSearch;
	private List<String> userIDList = new ArrayList<>();
	JLabel lblNewLabel;
	
	/**
	 * Create the application.
	 */
	public AccountManagement() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(186, 85, 211));
		panel.setBounds(0, 0, 209, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblAccountManagement_1 = new JLabel("Account \r\n");
		lblAccountManagement_1.setForeground(Color.BLACK);
		lblAccountManagement_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountManagement_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAccountManagement_1.setBounds(94, 32, 93, 27);
		panel.add(lblAccountManagement_1);
		
		JLabel lblDetails = new JLabel("Search Here!");
		lblDetails.setForeground(Color.BLACK);
		lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblDetails.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDetails.setBounds(21, 120, 178, 27);
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
		    	btnBack.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnBack.setBackground(java.awt.Color.BLACK);
		    }
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.setBackground(Color.BLACK);
		btnBack.setBounds(51, 454, 105, 27);
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
				loadUserDataFromSearch();
			}
		});
		cmbSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbSearch.setBounds(31, 157, 168, 27);
		panel.add(cmbSearch);
		fetchUserIDs();
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserID.setText("");
				txtEmail.setText("");
				txtName.setText("");
				txtContact.setText("");
				txtUsername.setText("");
				pswdPassword.setText("");
			}
		});
		btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnClear.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnClear.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClear.setBackground(Color.BLACK);
		btnClear.setBounds(51, 415, 105, 27);
		panel.add(btnClear);
		
		JButton btnView = new JButton("View ");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountDetails acd = new AccountDetails();
				acd.show();
			}
		});
		btnView.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnView.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnView.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnView.setForeground(Color.WHITE);
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnView.setBackground(Color.BLACK);
		btnView.setBounds(51, 378, 105, 27);
		panel.add(btnView);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 104, 209, 4);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AccountManagement.class.getResource("/images/twemoji--books (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 21, 74, 73);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.textHighlightText);
		panel_1.setBounds(208, 0, 611, 491);
		frame.getContentPane().add(panel_1);
		
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccounts.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblAccounts.setBounds(98, 20, 437, 26);
		panel_1.add(lblAccounts);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setForeground(Color.BLACK);
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUserId.setBounds(36, 80, 178, 27);
		panel_1.add(lblUserId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblName.setBounds(36, 150, 178, 27);
		panel_1.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblEmail.setBounds(36, 199, 178, 27);
		panel_1.add(lblEmail);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setForeground(Color.BLACK);
		lblContactNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactNo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblContactNo.setBounds(36, 248, 178, 27);
		panel_1.add(lblContactNo);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setForeground(Color.BLACK);
		lblRole.setHorizontalAlignment(SwingConstants.LEFT);
		lblRole.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRole.setBounds(36, 287, 178, 27);
		panel_1.add(lblRole);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(36, 330, 178, 27);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPassword.setBounds(36, 375, 178, 27);
		panel_1.add(lblPassword);
		
		txtUserID = new JTextField();
		txtUserID.setEditable(false);
		txtUserID.setForeground(Color.BLACK);
		txtUserID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtUserID.setColumns(10);
		txtUserID.setBounds(311, 81, 261, 26);
		panel_1.add(txtUserID);
		
		txtName = new JTextField();
		txtName.setForeground(Color.BLACK);
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtName.setColumns(10);
		txtName.setBounds(311, 151, 261, 26);
		panel_1.add(txtName);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtEmail.setColumns(10);
		txtEmail.setBounds(311, 200, 261, 26);
		panel_1.add(txtEmail);
		
		txtContact = new JTextField();
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtContact.setColumns(10);
		txtContact.setBounds(311, 249, 261, 26);
		panel_1.add(txtContact);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtUsername.setColumns(10);
		txtUsername.setBounds(311, 331, 261, 26);
		panel_1.add(txtUsername);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertAccount();
			}
		});
		btnInsert.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnInsert.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnInsert.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setBounds(36, 454, 105, 27);
		panel_1.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedUserID = txtUserID.getText().trim();
			    if (selectedUserID != null && !selectedUserID.isEmpty()) {
			        int confirmed = JOptionPane.showConfirmDialog(frame, 
			            "Are you sure you want to delete the user with UserID: " + selectedUserID + "?", 
			            "Delete User", JOptionPane.YES_NO_OPTION);
			        if (confirmed == JOptionPane.YES_OPTION) {
			            try (Connection conn = DatabaseConnection.getConnection()) {
			                String query = "DELETE FROM AdminsAndLibrarians WHERE UserID = ?";
			                try (PreparedStatement ps = conn.prepareStatement(query)) {
			                    ps.setString(1, selectedUserID);  
			                    int rowsAffected = ps.executeUpdate();
			                    if (rowsAffected > 0) {
			                        JOptionPane.showMessageDialog(frame, "User deleted successfully.");
			                        fetchUserIDs(); // Refresh the list in ComboBox
			                        txtUserID.setText("");
			        				txtEmail.setText("");
			        				txtName.setText("");
			        				txtContact.setText("");
			        				txtUsername.setText("");
			        				pswdPassword.setText("");
			                    } else {
			                        JOptionPane.showMessageDialog(frame, "No user found with the given UserID.");
			                    }
			                }
			            } catch (SQLException ex) {
			                JOptionPane.showMessageDialog(frame, "Error deleting user: " + ex.getMessage());
			            }
			        }
			    } else {
			        JOptionPane.showMessageDialog(frame, "Please enter a valid UserID.");
			    }
			}
		});
		btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnDelete.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnDelete.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.setBackground(Color.BLACK);
		btnDelete.setBounds(467, 454, 105, 27);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID = txtUserID.getText().trim();  
			    if (userID != null && !userID.isEmpty()) {
			        String name = txtName.getText().trim();
			        String email = txtEmail.getText().trim();
			        String contact = txtContact.getText().trim();
			        String username = txtUsername.getText().trim();
			        String password = new String(pswdPassword.getPassword()).trim();
			        String role = (String) cmbRole.getSelectedItem();

			        if (!validatePassword(password)) {
			        	lblNewLabel.setForeground(Color.RED);
			        	return;
			        }
			        
			        if (!validatePassword(password) || !validateContact(contact) || !validateEmail(email)) {
			            return;
			        }
			        if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || username.isEmpty() || password.isEmpty() || role == null) {
			            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
			            return;
			        }
			        
			        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

			        try (Connection conn = DatabaseConnection.getConnection()) {
			            String query = "UPDATE AdminsAndLibrarians SET Username = ?, Password = ?, Role = ?, Name = ?, Email = ?, ContactNumber = ? WHERE UserID = ?";
			            try (PreparedStatement ps = conn.prepareStatement(query)) {
			                ps.setString(1, username);
			                ps.setString(2, hashedPassword);
			                ps.setString(3, role);
			                ps.setString(4, name);
			                ps.setString(5, email);
			                ps.setString(6, contact);
			                ps.setString(7, userID);  

			                int rowsAffected = ps.executeUpdate();
			                if (rowsAffected > 0) {
			                    JOptionPane.showMessageDialog(frame, "User data updated successfully.");
			                    txtUserID.setText("");
			    				txtEmail.setText("");
			    				txtName.setText("");
			    				txtContact.setText("");
			    				txtUsername.setText("");
			    				pswdPassword.setText("");
			                } else {
			                    JOptionPane.showMessageDialog(frame, "No user found with the given UserID.");
			                }
			            }
			        } catch (SQLException ex) {
			            JOptionPane.showMessageDialog(frame, "Error updating user data: " + ex.getMessage());
			        }
			    } else {
			        JOptionPane.showMessageDialog(frame, "Please enter a valid UserID.");
			    }
			}
		});
		btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnUpdate.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnUpdate.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(255, 454, 105, 27);
		panel_1.add(btnUpdate);
		
		pswdPassword = new JPasswordField();
		pswdPassword.setForeground(Color.BLACK);
		pswdPassword.setBounds(311, 375, 261, 27);
		panel_1.add(pswdPassword);
		
		String[] roles = {"Select Role", "Admin", "Librarian"};
		cmbRole = new JComboBox<>(roles);
		cmbRole.setForeground(Color.BLACK);
		cmbRole.setToolTipText("");
		cmbRole.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cmbRole.setBounds(311, 292, 261, 27);
		panel_1.add(cmbRole);
		
		lblNewLabel = new JLabel("Use at least 1 CAPITAL letter,number and special character.");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(243, 412, 342, 13);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.BLACK);
		panel_2_1.setBounds(25, 117, 555, 4);
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

    private boolean validatePassword(String password) {
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(frame, "Password must contain at least one capital letter.");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(frame, "Password must contain at least one number.");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            JOptionPane.showMessageDialog(frame, "Password must contain at least one special character.");
            return false;
        }
        return true;
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

    private void insertAccount() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String username = txtUsername.getText();
        char[] passwordChars = pswdPassword.getPassword();
        String password = new String(passwordChars);
        String role = (String) cmbRole.getSelectedItem();
        
        if (!validatePassword(password)) {
        	lblNewLabel.setForeground(Color.RED);
        	return;
        }

        if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || username.isEmpty() || password.isEmpty() || role.equals("Select Role")) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
            return;
        }

        if (!validatePassword(password) || !validateContact(contact) || !validateEmail(email)) {
            return;
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO AdminsAndLibrarians (Username, Password, Role, Name, Email, ContactNumber) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, username); 
                ps.setString(2, hashedPassword);
                ps.setString(3, role);
                ps.setString(4, name);
                ps.setString(5, email);
                ps.setString(6, contact);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Account successfully inserted!");
                    fetchUserIDs();
                    txtUserID.setText("");
    				txtEmail.setText("");
    				txtName.setText("");
    				txtContact.setText("");
    				txtUsername.setText("");
    				pswdPassword.setText("");
                }
                
                lblNewLabel.setForeground(Color.BLACK);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error inserting account: " + e.getMessage());
        }
    }
    
    private void fetchUserIDs() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT UserID FROM AdminsAndLibrarians";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                cmbSearch.removeAllItems();
                userIDList.clear(); 

                while (rs.next()) {
                    String userID = rs.getString("UserID"); 
                    userIDList.add(userID);
                    cmbSearch.addItem(userID); 
                }
                System.out.println("ComboBox populated: " + userIDList); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching User IDs: " + e.getMessage());
        }
    }

    
    private void loadUserDataFromSearch() {
        String selectedUserID = (String) cmbSearch.getSelectedItem(); 
        System.out.println("Selected UserID: " + selectedUserID);

        if (selectedUserID != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT * FROM AdminsAndLibrarians WHERE UserID = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, selectedUserID);  
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("User data found: " + rs.getString("UserID"));
                            // Fill the text fields with data from the result set
                            txtUserID.setText(rs.getString("UserID"));
                            txtName.setText(rs.getString("Name"));
                            txtEmail.setText(rs.getString("Email"));
                            txtContact.setText(rs.getString("ContactNumber"));
                            txtUsername.setText(rs.getString("Username"));
                            cmbRole.setSelectedItem(rs.getString("Role"));
                        } else {
                            System.out.println("No data found for UserID: " + selectedUserID); 
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching user data: " + ex.getMessage());
            }
        }
    }
}
