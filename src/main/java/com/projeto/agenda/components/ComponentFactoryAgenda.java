package com.projeto.agenda.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueModel;
import com.toedter.calendar.JDateChooser;

public class ComponentFactoryAgenda {

	public static JFormattedTextField textField(ValueModel valueModel) {
		return createFormattedField(valueModel, true, null);
	}

	public static JButton button(String nome, ActionListener action) {
		return createButton(true, nome, action);
	}

	public static JDateChooser dateField(ValueModel valueModel) {
		return createDateField(valueModel, true, "dd/MM/yyyy");
	}

	public static JDateChooser dateField(ValueModel valueModel, String formatter) {
		return createDateField(valueModel, true, formatter);
	}

	public static HourPickerField hourPickerField(ValueModel model) {
		return hourPickerField(model, "00:00");
	}

	public static HourPickerField hourPickerField(ValueModel model, String setTime) {
		return createHourPicker(model, setTime, true);
	}

	public static JTable table(TableModel tableModel) {
		return createTable(tableModel, true);
	}

	public static <E extends Enum<E>> JComboBox<E> enumComboBox(ValueModel model, Class<E> enumClass) {
		JComboBox<E> comboBox = new JComboBox<>(enumClass.getEnumConstants());

		comboBox.setSelectedItem(model.getValue());

		model.addValueChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				comboBox.setSelectedItem(evt.getNewValue());
			}
		});

		comboBox.addActionListener(e -> model.setValue(comboBox.getSelectedItem()));

		return comboBox;
	}

	private static JFormattedTextField createFormattedField(ValueModel valueModel, boolean enabled,
			AbstractFormatter formatter) {
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setEnabled(enabled);
		field.setHorizontalAlignment(JTextField.CENTER);
		Bindings.bind(field, valueModel);
		return field;
	}

	private static JButton createButton(boolean enabled, String nome, ActionListener action) {
		JButton button = new JButton(nome);
		button.setEnabled(enabled);
		button.setHorizontalAlignment(JButton.CENTER);
		button.setBorder(new RoundedBorder(Color.LIGHT_GRAY, 5));
		button.setPreferredSize(new Dimension(150, 40));
		button.setFocusable(false);
		button.addActionListener(action);
		return button;
	}

	private static HourPickerField createHourPicker(ValueModel model, String setTime, boolean enabled) {
		HourPickerField hp = new HourPickerField();
		hp.setTime(setTime);
		hp.setEnabled(enabled);
		return hp;
	}

	private static JDateChooser createDateField(ValueModel valueModel, boolean enabled, String formatter) {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString(formatter);
		return dateChooser;
	}

	private static JTable createTable(TableModel tableModel, boolean enabled) {
		JTable table = new JTable();
		table.setModel(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		return table;
	}
}