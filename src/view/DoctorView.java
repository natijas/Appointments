package view;

import dao.DoctorDAO;
import dao.ShiftDAO;
import model.Doctor;
import model.Shift;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * A class representing a window of information about doctors, the user has access to view information about the doctor of interest.
 */
public class DoctorView {
    private JComboBox doctor;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField gender;
    private JTextField specialization;
    private JPanel mainPanel;
    private JTable shifts;

    /**
     * Constructor of DoctorView class
     */
    public DoctorView() {
        doctor.setRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value == null) return new JLabel("(select a doctor)");
                Doctor doctor = (Doctor) value;
                return new JLabel(doctor.getFirstName() + " " + doctor.getLastName());
            }
        });
        ((DefaultComboBoxModel) doctor.getModel()).addAll(new DoctorDAO().getAll());
        doctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInformation();
            }
        });

        ((DefaultTableModel) shifts.getModel()).addColumn("Localization");
        ((DefaultTableModel) shifts.getModel()).addColumn("Day");
        ((DefaultTableModel) shifts.getModel()).addColumn("Start time");
        ((DefaultTableModel) shifts.getModel()).addColumn("End time");
    }

    /**
     * JPanel's getter
     * @return mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Doctor's getter
     * @return selected doctor
     */
    public Doctor getDoctor() {
        return (Doctor) doctor.getSelectedItem();
    }

    /**
     * Doctor's setter, iterates through all doctors
     * @param d doctor
     */
    public void setDoctor(Doctor d) {
        if (d == null) {
            doctor.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < doctor.getModel().getSize(); i++) {
            if (((Doctor) doctor.getItemAt(i)).getID() == d.getID()) {
                doctor.setSelectedIndex(i);
                return;
            }
        }
        doctor.setSelectedItem(null);
    }

    /**
     * Function that updates data about doctors
     */
    private void updateInformation() {
        Doctor doctor = getDoctor();

        if (doctor == null) {
            firstName.setText("");
            lastName.setText("");
            gender.setText("");
            specialization.setText("");
            setShifts(List.of());
            return;
        }

        firstName.setText(doctor.getFirstName());
        lastName.setText(doctor.getLastName());
        gender.setText(doctor.getGender());
        specialization.setText(doctor.getSpecialization().getName());
        setShifts(new ShiftDAO().getDoctorShift(doctor));
    }

    /**
     * Function that sets up doctor's duty hours / shifts
     * @param shiftList list of shifts
     */
    private void setShifts(List<Shift> shiftList) {
        while (shifts.getModel().getRowCount() != 0) {
            ((DefaultTableModel) shifts.getModel()).removeRow(0);
        }
        for (Shift s : shiftList) {
            ((DefaultTableModel) shifts.getModel()).addRow(new String[]{
                    s.getLocalization().getName(),
                    DayOfWeek.of(s.getDayOfWeek()).name(),
                    DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.of(s.getShiftStart(), 0)),
                    DateTimeFormatter.ofPattern("HH:mm").format(LocalTime.of(s.getShiftEnd(),0)),
            });
        }
    }
}
