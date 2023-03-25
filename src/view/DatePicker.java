package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to select the date by the user.
 */

public class DatePicker extends JPanel {
    private JComboBox month = new JComboBox();
    private JComboBox day = new JComboBox();
    private JComboBox year = new JComboBox();

    public DatePicker() {
        year.setToolTipText("Year");
        month.setToolTipText("Month");
        day.setToolTipText("Day");

        setLayout(new GridLayout());
        add(year);
        add(month);
        add(day);

        ((DefaultComboBoxModel)month.getModel()).addAll(List.of(Month.values()));
        year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPossibleDays();
            }
        });
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPossibleDays();
            }
        });

    }

    /**
     * A function that sets possible days to be selected by the user.
     */
    private void setPossibleDays() {
        Integer y = (Integer) year.getSelectedItem();
        int m = month.getSelectedIndex() + 1;

        Integer current = (Integer) day.getSelectedItem();

        ((DefaultComboBoxModel)day.getModel()).removeAllElements();
        if (y != null && m != 0) {

            LocalDate d = LocalDate.of(y.intValue(), m, 1);
            ArrayList<Integer> range = new ArrayList<>();
            while (d.getMonth().getValue() == m) {
                range.add(Integer.valueOf(d.getDayOfMonth()));
                d = d.plusDays(1);
            }
            ((DefaultComboBoxModel)day.getModel()).addAll(range);

            if (current != null) {
                if (current.intValue() > range.get(range.size() - 1)) day.setSelectedIndex(range.size() - 1);
                else day.setSelectedItem(current);
            }
        }
    }

    /**
     * Function that sets the range of years to be entered by the user
     * @param firstYear firstYear
     * @param lastYear lastYear
     */
    public void setYearRange(int firstYear, int lastYear) {
        Integer current = (Integer) year.getSelectedItem();
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = firstYear; i <= lastYear; i++) {
            range.add(Integer.valueOf(i));
        }
        ((DefaultComboBoxModel)year.getModel()).removeAllElements();
        ((DefaultComboBoxModel)year.getModel()).addAll(range);

        if (current != null && !range.isEmpty()) {
            if (current.intValue() < firstYear) year.setSelectedIndex(0);
            else if (current.intValue() > lastYear) year.setSelectedIndex(range.size() - 1);
            else year.setSelectedItem(current);
        }
    }

    /**
     * Date getting function
     * @return date
     */
    public LocalDate getDate() {
        Integer y = (Integer) year.getSelectedItem();
        int m = month.getSelectedIndex() + 1;
        Integer d = (Integer) day.getSelectedItem();

        if (y == null || m == 0 || d == null) return null;
        return LocalDate.of(y.intValue(), m, d.intValue());
    }

    /**
     * Date setting function
     * @param date date
     */
    public void setDate(LocalDate date) {
        year.setSelectedItem(Integer.valueOf(date.getYear()));
        month.setSelectedIndex(date.getMonthValue() - 1);
        day.setSelectedItem(Integer.valueOf(date.getDayOfMonth()));
    }
}
