package view;

import dao.PatientDAO;
import dao.UserDAO;
import model.Patient;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The window used to register a new user.
 */
public class RegisterView extends JFrame{
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JTextField firstName;
    private JTextField lastName;
    private JComboBox gender;
    private JPanel birthDate;
    private JTextField username;
    private JButton registerButton;
    private JPanel registerPanel;

    UserDAO userDAO = new UserDAO();
    PatientDAO patientDAO = new PatientDAO();

    /**
     * RegisterView class constructor.
     * Implement the registration button, handling incorrect actions performed by the user,
     * such as providing an incorrect password, providing an empty field, and failing to select a field specified by
     * the applications.
     * @param parent parent
     */

    public RegisterView(JFrame parent) {

        setTitle("Register");
        setContentPane(registerPanel);
        setMinimumSize(registerPanel.getMinimumSize());
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!Objects.equals(new String(passwordConfirmField.getText()), new String(passwordField.getText()))){
                    JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (firstName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "First name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (lastName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Last name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (gender.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Select gender", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LocalDate date = ((DatePicker) birthDate).getDate();
                if (date == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "Select date", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User user = new User(username.getText(), passwordField.getText());
                int userID = userDAO.save(user);
                if (userID == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Cannot add an user", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Patient patient = new Patient(userID, firstName.getText(), lastName.getText(), gender.getSelectedItem().toString(), date);
                boolean ok = patientDAO.save(patient);
                if (!ok) {
                    JOptionPane.showMessageDialog(new JFrame(), "Cannot add an user", "Error", JOptionPane.ERROR_MESSAGE);
                    user.setID(userID);
                    userDAO.delete(user);
                    return;
                }
                JOptionPane.showMessageDialog(new JFrame(), "Successfully added an user", "Info", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
    }

    /**
     * Function that creates UI components
     */
    private void createUIComponents() {
        birthDate = new DatePicker();
        ((DatePicker) birthDate).setYearRange(1900, LocalDate.now().getYear());
    }
}
