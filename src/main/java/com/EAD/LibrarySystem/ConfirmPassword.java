package com.EAD.LibrarySystem;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ConfirmPassword {

	private JFrame frame;
	private JPasswordField pswdNew;
	private JPasswordField pswdConfirm;
	private JTextField txtUserID;

	/**
	 * Create the application.
	 */
	public ConfirmPassword() {
		initialize();
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 22));
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(425, 0, 394, 491);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(221, 160, 221));
		panel_2.setBounds(29, 23, 339, 447);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Reset Password");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 31, 319, 37);
		panel_2.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("New Password");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(37, 155, 148, 27);
		panel_2.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Confirm Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPassword.setBounds(37, 228, 148, 27);
		panel_2.add(lblPassword);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID = txtUserID.getText().trim();
		        String newPassword = new String(pswdNew.getPassword()).trim();
		        String confirmPassword = new String(pswdConfirm.getPassword()).trim();

		        if (userID.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter a valid User ID.");
		            return;
		        }

		        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Password fields cannot be empty.");
		            return;
		        }

		        if (!newPassword.equals(confirmPassword)) {
		            JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
		            return;
		        }

		        // Hash the password using BCrypt
		        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

		        try (Connection conn = DatabaseConnection.getConnection()) {
		            String query = "UPDATE AdminsAndLibrarians SET Password = ? WHERE UserID = ?";
		            try (PreparedStatement ps = conn.prepareStatement(query)) {
		                ps.setString(1, hashedPassword);
		                ps.setString(2, userID);

		                int rowsAffected = ps.executeUpdate();
		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(frame, "Password updated successfully.");
		                    frame.dispose();
		    				LogIn log = new LogIn();
		    				log.show();
		                } else {
		                    JOptionPane.showMessageDialog(frame, "No user found with the given User ID.");
		                }
		            }
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(frame, "Error updating password: " + ex.getMessage());
		        }
			}
		});
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(220, 366, 85, 27);
		panel_2.add(btnNewButton);
		
		pswdNew = new JPasswordField();
		pswdNew.setForeground(Color.BLACK);
		pswdNew.setBounds(73, 192, 232, 27);
		panel_2.add(pswdNew);
		
		pswdConfirm = new JPasswordField();
		pswdConfirm.setForeground(Color.BLACK);
		pswdConfirm.setBounds(73, 265, 232, 27);
		panel_2.add(pswdConfirm);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setForeground(Color.BLACK);
		lblUserId.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUserId.setBounds(37, 81, 148, 27);
		panel_2.add(lblUserId);
		
		txtUserID = new JTextField();
		txtUserID.setForeground(Color.BLACK);
		txtUserID.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtUserID.setColumns(10);
		txtUserID.setBounds(73, 118, 232, 27);
		panel_2.add(txtUserID);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 426, 491);
		frame.getContentPane().add(panel);
		
		JLabel lblL = new JLabel("Library Management System");
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblL.setBounds(10, 33, 406, 26);
		panel.add(lblL);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ConfirmPassword.class.getResource("/images/44758162 (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 77, 385, 393);
		panel.add(lblNewLabel_1);
		frame.setResizable(false);
		frame.setBounds(100, 100, 833, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void show() {
		frame.setVisible(true);
	}
}
