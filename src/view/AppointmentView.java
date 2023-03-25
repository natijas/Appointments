package view;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.LocalizationDAO;
import dao.SpecializationDAO;
import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;

/**
 *  An abstract Appointment viewer class
 */

public abstract class AppointmentView {
    private JComboBox specialization;
    private JTable appointmentList;
    private JPanel mainPanel;
    private JButton appointmentActionButton;
    private JComboBox doctor;
    private JPanel firstDate;
    private JPanel lastDate;
    private JButton searchButton;
    private JComboBox localization;
    private JPanel filterPanel;

    Patient patientToSearch;

    static private final Specialization specializationAll = new Specialization("(all)");
    static private final Doctor doctorAll = new Doctor("(all)", "", "", specializationAll);
    static private final Localization localizationAll = new Localization("(all)");

    List<Doctor> allDoctors;

    boolean forceBeginCurrentDate;
    boolean forceEndCurrentDate;

    /**
     * Constructor of AppointmentView class
     * @param patientToSearch
     * @param hideFilters
     * @param actionName
     * @param forceBeginCurrentDate
     * @param forceEndCurrentDate
     */

    public AppointmentView(Patient patientToSearch, boolean hideFilters, String actionName, boolean forceBeginCurrentDate, boolean forceEndCurrentDate) {
        this.patientToSearch = patientToSearch;
        this.forceBeginCurrentDate = forceBeginCurrentDate;
        this.forceEndCurrentDate = forceEndCurrentDate;

        allDoctors = new DoctorDAO().getAll();

        ((DefaultComboBoxModel) localization.getModel()).addElement(localizationAll);
        if (!hideFilters) ((DefaultComboBoxModel) localization.getModel()).addAll(new LocalizationDAO().getAll());

        ((DefaultComboBoxModel) specialization.getModel()).addElement(specializationAll);
        if (!hideFilters) ((DefaultComboBoxModel) specialization.getModel()).addAll(new SpecializationDAO().getAll());

        doctor.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Doctor doctor = (Doctor) value;
                if (doctor == doctorAll) return new JLabel("(all)");
                return new JLabel(doctor.getFirstName() + " " + doctor.getLastName() + " (" + doctor.getSpecialization().getName() + ")");
            }
        });
        updateDoctors();
        appointmentActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointment appointment = ((AppointmentList)appointmentList).getSelectedAppointment();
                if (appointment == null) return;
                onAppointmentAction(appointment);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((DatePicker) firstDate).getDate() != null && ((DatePicker) lastDate).getDate() != null &&
                        ((DatePicker) firstDate).getDate().toEpochDay() > ((DatePicker) lastDate).getDate().toEpochDay()) {
                    JOptionPane.showMessageDialog(new JFrame(), "First date cannot be before last date", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                updateAppointmentList();
                if (((AppointmentList)appointmentList).isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "There are not available appointments matching your search criteria", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        specialization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDoctors();
            }
        });

        ((DefaultListSelectionModel) appointmentList.getSelectionModel()).addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                appointmentActionButton.setEnabled(((AppointmentList)appointmentList).getSelectedAppointment() != null);
            }
        });

        if (actionName == null) appointmentActionButton.setVisible(false);
        else appointmentActionButton.setText(actionName);

        if (!hideFilters) {
            ((DatePicker) firstDate).setYearRange(LocalDate.now().getYear() - 1, LocalDate.now().getYear() + 1);
            ((DatePicker) firstDate).setDate(LocalDate.now());

            ((DatePicker) lastDate).setYearRange(LocalDate.now().getYear() - 1, LocalDate.now().getYear() + 1);
            ((DatePicker) lastDate).setDate(LocalDate.now());
        }

        if (hideFilters) {
            filterPanel.setVisible(false);
        }

        updateAppointmentList();
    }

    /**
     * JPanel's getter function
     * @return mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Function that updates list of doctors
     */
    private void updateDoctors() {
        Doctor current = (Doctor) doctor.getSelectedItem();
        ((DefaultComboBoxModel) doctor.getModel()).removeAllElements();
        ((DefaultComboBoxModel) doctor.getModel()).addElement(doctorAll);
        if (specialization.getSelectedIndex() == 0) ((DefaultComboBoxModel) doctor.getModel()).addAll(allDoctors);
        else {
            Specialization sp = (Specialization) specialization.getSelectedItem();
            for (Doctor d : allDoctors) {
                if (d.getSpecialization().getID() == sp.getID()) {
                    ((DefaultComboBoxModel) doctor.getModel()).addElement(d);
                }
            }
        }
        doctor.setSelectedItem(doctor);
    }

    /**
     * Function that updates appointment list and uses some filters.
     */
    public void updateAppointmentList() {
        Map<String, Integer> filters = new HashMap<>();

        if (patientToSearch == null) filters.put("patient", null);
        else filters.put("patient", patientToSearch.getUserID());

        if (specialization.getSelectedItem() != null && specialization.getSelectedItem() != specializationAll) {
            filters.put("specialization", ((Specialization) specialization.getSelectedItem()).getID());
        }

        if (doctor.getSelectedItem() != null && doctor.getSelectedItem() != doctorAll) {
            filters.put("doctor", ((Doctor) doctor.getSelectedItem()).getID());
        }

        if (localization.getSelectedItem() != null && localization.getSelectedItem() != localizationAll) {
            filters.put("localization", ((Localization) localization.getSelectedItem()).getID());
        }

        LocalDateTime beginDate = null;
        if (((DatePicker) firstDate).getDate() != null) beginDate = ((DatePicker) firstDate).getDate().atStartOfDay();
        if (forceBeginCurrentDate && (beginDate == null || beginDate.toEpochSecond(ZoneOffset.UTC) < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))) {
            beginDate = LocalDateTime.now();
        }

        LocalDateTime endDate = null;
        if (((DatePicker) lastDate).getDate() != null) endDate = ((DatePicker) lastDate).getDate().atTime(23, 59, 59);
        if (forceEndCurrentDate && (endDate == null || endDate.toEpochSecond(ZoneOffset.UTC) > LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))) {
            endDate = LocalDateTime.now();
        }

        List<Appointment> appointments = new AppointmentDAO().search(beginDate, endDate, filters);
        ((AppointmentList) appointmentList).setAppointments(appointments);
    }

    /**
     * Function that creates UI components
     */
    private void createUIComponents() {
        appointmentList = new AppointmentList();

        firstDate = new DatePicker();
        lastDate = new DatePicker();
    }
     /**
      * An abstract function used in MenuView
     */
    public abstract void onAppointmentAction(Appointment appointment);
}
