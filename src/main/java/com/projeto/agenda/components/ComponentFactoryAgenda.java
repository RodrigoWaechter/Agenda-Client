package com.projeto.agenda.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.common.format.EmptyNumberFormat;
import com.toedter.calendar.JDateChooser;

public class ComponentFactoryAgenda {

	public static JLabel createLabel(ValueModel label) {
		return BasicComponentFactory.createLabel(label);
	}

	public static JFormattedTextField integerField(ValueModel valueModel) {
		return integerField(valueModel, new DecimalFormat("#"));
	}

	public static JFormattedTextField integerField(ValueModel valueModel, NumberFormat format) {
		return createIntegerField(valueModel, format, true);
	}

	public static JFormattedTextField textField(ValueModel valueModel) {
		return createFormattedField(valueModel, true, null);
	}

	public static JPasswordField passwordField(ValueModel valueModel) {
		return createPasswordField(valueModel, true);
	}

	public static JCheckBox jCheckBox(ValueModel model) {
		return jCheckBox(model, true);
	}

	public static JCheckBox jCheckBox(ValueModel model, String description) {
		return createJCheckBox(model, description, true);
	}

	public static JCheckBox jCheckBox(ValueModel model, boolean enabled) {
		return createJCheckBox(model, null, enabled);
	}

	public static JCheckBox optionCheckBox(final ValueModel model, final Object optionValue) {
		return optionCheckBox(model, optionValue, String.valueOf(optionValue));
	}

	public static JCheckBox optionCheckBox(final ValueModel model, final Object optionValue, String description) {
		return optionCheckBox(model, optionValue, description, true);
	}

	public static JCheckBox optionCheckBox(final ValueModel model, final Object optionValue, String description,
			boolean enabled) {
		final ValueModel valueModel = new ValueHolder(optionValue.equals(model.getValue()), true);
		valueModel.addValueChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				boolean marcou = evt.getNewValue() != null && (Boolean) evt.getNewValue();

				if (marcou) {
					model.setValue(optionValue);
				} else if (model.getValue().equals(optionValue)) {
					model.setValue(null);
				}
			}
		});

		model.addValueChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				valueModel.setValue(optionValue.equals(evt.getNewValue()));
			}
		});

		return createJCheckBox(valueModel, description, enabled);
	}

	public static JDateChooser dateField(ValueModel valueModel) {
		return createDateField(valueModel, true, "dd/MM/yyyy");
	}

	public static JDateChooser dateField(ValueModel valueModel, String formatter) {
		return createDateField(valueModel, true, formatter);
	}

	private static JDateChooser createDateField(ValueModel valueModel, boolean enabled, String formatter) {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString(formatter);
		return dateChooser;
	}

	private static JFormattedTextField createFormattedField(ValueModel valueModel, boolean enabled,
			AbstractFormatter formatter) {
		JFormattedTextField field = new JFormattedTextField(formatter);
		field.setEnabled(enabled);
		field.setHorizontalAlignment(JTextField.CENTER);
		Bindings.bind(field, valueModel);
		return field;
	}

	private static JPasswordField createPasswordField(ValueModel valueModel, boolean enabled) {
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setEnabled(enabled);
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		Bindings.bind(passwordField, valueModel);
		return passwordField;
	}

	private static JCheckBox createJCheckBox(ValueModel valueModel, String description, boolean enabled) {
		JCheckBox jCheckBox = new JCheckBox(description);
		jCheckBox.setEnabled(enabled);
		Bindings.bind(jCheckBox, valueModel);
		return jCheckBox;
	}

	private static JFormattedTextField createIntegerField(ValueModel valueModel, NumberFormat format, boolean enabled) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumIntegerDigits(999999999);
		numberFormat.setMinimumIntegerDigits(0);
		JFormattedTextField formattedField = createFormattedField(valueModel, enabled,
				new NumberFormatter(new EmptyNumberFormat(numberFormat)));
		formattedField.setHorizontalAlignment(JTextField.CENTER);
		Bindings.bind(formattedField, valueModel);
		return formattedField;
	}
}
