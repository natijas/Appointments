package view;

import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to display a list of appointments in the user's head window,
 * Displays a table containing the date, doctor and location.
 */
public class AppointmentList extends JTable {

    List<Appointment> appointments = new ArrayList<>();

    public AppointmentList() {
        setSelectionModel(new DefaultListSelectionModel());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ((DefaultTableModel) getModel()).addColumn("Date");
        ((DefaultTableModel) getModel()).addColumn("Doctor");
        ((DefaultTableModel) getModel()).addColumn("Localization");
    }

    /**
     * A function that allows you to unedit the cells of the displayed table.
     * @param row      row whose value is to be queried
     * @param column  column whose value is to be queried
     * @return no ability to edit cells
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * A function that sets appointments in a table, displays date, doctor with specialty and location.
     * @param appointments list of appointments
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;

        while (getModel().getRowCount() != 0) {
            ((DefaultTableModel) getModel()).removeRow(0);
        }
        for (Appointment app : appointments) {
            ((DefaultTableModel) getModel()).addRow(new String[] {
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(app.getDate()),
                    app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName() + " (" + app.getDoctor().getSpecialization().getName() + ")",
                    app.getLocalization().getName(),
            });
        }
    }

    /**
     * Visit selection function
     * @return returns a row of visit
     */
    public Appointment getSelectedAppointment() {
        if (getSelectedRow() == -1) return null;
        return appointments.get(getSelectedRow());
    }

    /**
     * A function that checks if the visits table is empty.
     */
    public boolean isEmpty() {
        return getModel().getRowCount() == 0;
    }
}
