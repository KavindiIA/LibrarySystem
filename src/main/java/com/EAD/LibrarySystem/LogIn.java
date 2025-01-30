package com.EAD.LibrarySystem;

import javax.swing.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class LogIn {

	private JFrame frmLibraryManagement;
	private JTextField txtUsername;
	private JPasswordField pswdPassword;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frmLibraryManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
		frmLibraryManagement.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibraryManagement = new JFrame("Log in");
		frmLibraryManagement.setTitle("Library Management");
		frmLibraryManagement.setResizable(false);
		frmLibraryManagement.setBounds(100, 100, 833, 528);
		frmLibraryManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibraryManagement.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 426, 491);
		frmLibraryManagement.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblL = new JLabel("Library Management System");
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblL.setBounds(10, 33, 406, 26);
		panel.add(lblL);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(LogIn.class.getResource("/images/44758162 (1).png")));
		lblNewLabel_1.setBounds(20, 77, 385, 393);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(425, 0, 394, 491);
		frmLibraryManagement.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(221, 160, 221));
		panel_2.setBounds(29, 23, 339, 447);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 31, 319, 37);
		panel_2.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(37, 119, 148, 27);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPassword.setBounds(37, 220, 148, 27);
		panel_2.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtUsername.setBounds(73, 156, 232, 27);
		panel_2.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnLogin.setBackground(java.awt.Color.DARK_GRAY); 
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnLogin.setBackground(java.awt.Color.BLACK); 
		    }
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
                String password = new String(pswdPassword.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frmLibraryManagement, "Please enter both username and password.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Connection connection = DatabaseConnection.getConnection();

                    // Fetch the hashed password from the database
                    String query = "SELECT Password, Role FROM AdminsAndLibrarians WHERE Username = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);

                    ResultSet resultSet = statement.executeQuery();
                    
                    if (resultSet.next()) {
                        String storedHashedPassword = resultSet.getString("Password");
                        String role = resultSet.getString("Role");

                        if (BCrypt.checkpw(password, storedHashedPassword)) {
                            switch (role) {
                                case "Admin":
                                    frmLibraryManagement.dispose();
                                    AdminDashboard adminDashboard = new AdminDashboard();
                                    adminDashboard.show();
                                    break;

                                case "Librarian":
                                    frmLibraryManagement.dispose();
                                    LibrarianDashboard librarianDashboard = new LibrarianDashboard();
                                    librarianDashboard.show();
                                    break;

                                default:
                                    JOptionPane.showMessageDialog(frmLibraryManagement, "Unknown role: " + role, "Login Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                       } else {
                    	   JOptionPane.showMessageDialog(frmLibraryManagement, "Invalid password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                       }
                       
                    } else {
                        JOptionPane.showMessageDialog(frmLibraryManagement, "Invalid username.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException exe) {
                    JOptionPane.showMessageDialog(frmLibraryManagement, "Database Error: " + exe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmLibraryManagement, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLogin.setBounds(137, 362, 85, 27);
		panel_2.add(btnLogin);
		
		JLabel lblForgetPassword = new JLabel("Forgot Password?");
		lblForgetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmLibraryManagement.dispose();
				ResetPassword re = new ResetPassword();
				re.show();
			}
			@Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblForgetPassword.setForeground(java.awt.Color.WHITE);
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblForgetPassword.setForeground(java.awt.Color.BLUE);
		    }
		});
		lblForgetPassword.setForeground(Color.BLUE);
		lblForgetPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblForgetPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblForgetPassword.setBounds(37, 305, 148, 27);
		panel_2.add(lblForgetPassword);
		
		pswdPassword = new JPasswordField();
		pswdPassword.setForeground(Color.BLACK);
		pswdPassword.setBounds(73, 260, 232, 27);
		panel_2.add(pswdPassword);
	}

	public void show() {
		frmLibraryManagement.setVisible(true);
	}
}
