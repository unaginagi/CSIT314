/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package JeromePackage;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public static boolean isLoggedin = false;

    public LoginPage() {
        setTitle("UNAGI Cinema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Authentication Result
        if (LoginPageLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            
            dispose();
            //dashboard
            SwingUtilities.invokeLater(() -> {
            Dashboard dashboardFrame = new Dashboard();
            dashboardFrame.setVisible(true);
        });
            
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean LoginPageLogin(String username, String password) {
        boolean isLoginSuccessful = false;
        //isLoginSuccessful = UserAdminLoginController.login(username,password);
        try {
            isLoginSuccessful = LoginController.login(username,password);
	} catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
	} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);      
        }
        return isLoginSuccessful;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
        /*
        if(isLoggedin == true){
            SwingUtilities.invokeLater(() -> {
            Dashboard dashboardFrame = new Dashboard();
            dashboardFrame.setVisible(true);
        });
        }else{
        SwingUtilities.invokeLater(LoginPage::new);
        }*/

    }
}


