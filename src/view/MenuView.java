package view;

import dao.AppointmentDAO;
import model.Appointment;
import model.Patient;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing the main user panel
 */

public class MenuView extends JFrame {


    private JPanel mainPanel;
    private JLabel nameField;
    private JTabbedPane tabbedPane;
    private JPanel yourAppointments;
    private JPanel reserveAppointment;
    private JPanel appointmentHistory;
    private JPanel browseDoctors;
    private JButton logOutButton;

    private AppointmentView yourAppointmentsView;
    private AppointmentView reserveAppointmentView;
    private AppointmentView appointmentHistoryView;
    private DoctorView doctorView;

    private final int YOUR_APPOINTMENTS_TAB_INDEX = 0;
    private final int BROWSE_DOCTOR_TAB_INDEX = 3;

    User user;
    Patient patient;

    /**
     * Menu class constructor, sets the application window
     * @param user user
     * @param patient patient
     * @param parent
     */
    public MenuView(User user, Patient patient, JFrame parent) {
        this.user = user;
        this.patient = patient;
        setTitle("Menu");
        setContentPane(mainPanel);
        setSize(mainPanel.getPreferredSize());
        setLocationRelativeTo(parent);
        setVisible(true);
        setResizable(false);
        nameField.setText(patient.getFirstName() + " " + patient.getLastName());
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * A function that creates components and redirects to the appropriate panel depending on the user's choice.
     */
    private void createUIComponents() {
        yourAppointmentsView = new AppointmentView(patient, true, "Resign appointment", true, false) {
            @Override
            public void onAppointmentAction(Appointment appointment) {
                new AppointmentDAO().resignAppointment(appointment);

                JOptionPane.showMessageDialog(new JFrame(), "The appointment was resigned", "Info", JOptionPane.INFORMATION_MESSAGE);
                reserveAppointmentView.updateAppointmentList();
                yourAppointmentsView.updateAppointmentList();
            }
        };
        yourAppointments = yourAppointmentsView.getMainPanel();

        reserveAppointmentView = new AppointmentView( null, false, "Reserve appointment", true, false) {
            @Override
            public void onAppointmentAction(Appointment appointment) {
                new AppointmentDAO().update(appointment, patient);

                JOptionPane.showMessageDialog(new JFrame(), "The appointment got reserved", "Info", JOptionPane.INFORMATION_MESSAGE);
                reserveAppointmentView.updateAppointmentList();
                yourAppointmentsView.updateAppointmentList();

                tabbedPane.setSelectedIndex(YOUR_APPOINTMENTS_TAB_INDEX);
            }
        };
        reserveAppointment = reserveAppointmentView.getMainPanel();

        appointmentHistoryView = new AppointmentView( patient, true, "Show doctor", false, true) {
            @Override
            public void onAppointmentAction(Appointment appointment) {
                doctorView.setDoctor(appointment.getDoctor());
                tabbedPane.setSelectedIndex(BROWSE_DOCTOR_TAB_INDEX);
            }
        };
        appointmentHistory = appointmentHistoryView.getMainPanel();

        doctorView = new DoctorView();
        browseDoctors = doctorView.getMainPanel();
    }

}
