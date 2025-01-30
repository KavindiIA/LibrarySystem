package com.EAD.LibrarySystem;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResetPassword {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtCode;
	private String generatedCode;

	/**
	 * Create the application.
	 */
	public ResetPassword() {
		initialize();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(425, 0, 394, 491);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(29, 23, 339, 447);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(221, 160, 221));
		panel_1.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Reset Password");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(10, 31, 319, 37);
		panel_2.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblUsername.setBounds(37, 119, 148, 27);
		panel_2.add(lblUsername);
		
		JLabel lblCode = new JLabel("Enter code here");
		lblCode.setForeground(Color.BLACK);
		lblCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblCode.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblCode.setBounds(37, 254, 148, 27);
		panel_2.add(lblCode);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtEmail.setColumns(10);
		txtEmail.setBounds(73, 156, 232, 27);
		panel_2.add(txtEmail);
		
		txtCode = new JTextField();
		txtCode.setForeground(Color.BLACK);
		txtCode.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtCode.setColumns(10);
		txtCode.setBounds(73, 307, 232, 27);
		panel_2.add(txtCode);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCode.getText().equals(generatedCode)) {
                    frame.dispose();
                    ConfirmPassword co = new ConfirmPassword();
                    co.show();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid code, please try again.");
                }
			}
		});
		btnVerify.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnVerify.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnVerify.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnVerify.setForeground(Color.WHITE);
		btnVerify.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnVerify.setBackground(Color.BLACK);
		btnVerify.setBounds(220, 366, 85, 27);
		panel_2.add(btnVerify);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
                if (emailExistsInDatabase(email)) {
                    generatedCode = generateCode();
                    sendEmail(email, generatedCode);
                    JOptionPane.showMessageDialog(frame, "Verification code sent to your email.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Email not found in database.");
                }
			}
		});
		btnSend.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnSend.setBackground(java.awt.Color.DARK_GRAY); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnSend.setBackground(java.awt.Color.BLACK); // Reset to the original color
		    }
		});
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSend.setBackground(Color.BLACK);
		btnSend.setBounds(220, 210, 85, 27);
		panel_2.add(btnSend);
		
		JLabel lblGoBackTo = new JLabel("Back");
		lblGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				LogIn log = new LogIn();
				log.show();
			}
			@Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
				lblGoBackTo.setForeground(java.awt.Color.WHITE); // Change to the desired color
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	lblGoBackTo.setForeground(java.awt.Color.BLUE); // Reset to the original color
		    }
		});
		lblGoBackTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblGoBackTo.setForeground(Color.BLUE);
		lblGoBackTo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblGoBackTo.setBounds(37, 410, 41, 27);
		panel_2.add(lblGoBackTo);
		
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
		lblNewLabel_1.setIcon(new ImageIcon(ResetPassword.class.getResource("/images/44758162 (1).png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 77, 385, 393);
		panel.add(lblNewLabel_1);
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
	
	private boolean emailExistsInDatabase(String email) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM AdminsAndLibrarians WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Method to generate a unique code
    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);  // Ensure the code is 6 digits long
    }

    // Method to send the generated code to the email
    private void sendEmail(String toEmail, String code) {
        String fromEmail = "iimasha136@gmail.com";  
        String password = "Kavi0329@";  
        String host = "smtp.gmail.com";
        String port = "587";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Your Verification Code");
            message.setText("Your verification code is: " + code);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


