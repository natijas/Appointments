package view;

import dao.PatientDAO;
import dao.UserDAO;
import model.User;
import model.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing the login window for a user
 */

public class LoginView extends JFrame{
    private JPanel mainPanel;
    private JTextField username;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    UserDAO userDAO = new UserDAO();

    /**
     * Constructor of LoginView class containing implementation of Login and Register button, which redirects to the
     * corresponding application header window or registration.
     * Handles basic errors that the user may make.
     */
    public LoginView() {

        setTitle("Login");
        setContentPane(mainPanel);
        setMinimumSize(mainPanel.getMinimumSize());
        setResizable(false);
        setVisible(true);
        LoginView self = this;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = userDAO.getSignIn(username.getText(), passwordField.getText());;
                if (id == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User user = new User(id, username.getText(), passwordField.getText());
                Patient patient = new PatientDAO().get(user.getID()).get();
                if (patient == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Could not retrive patient information", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                MenuView ui = new MenuView(user, patient, self);
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterView registerView = new RegisterView(self);

            }
        });
    }
}

