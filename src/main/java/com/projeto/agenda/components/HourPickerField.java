package com.projeto.agenda.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;

public class HourPickerField extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JButton button;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private Calendar calendario = Calendar.getInstance();
    private Calendar calendarioInicial = Calendar.getInstance();
    private JPopupMenu popupMenu; 
   
    

    public HourPickerField() {
        setLayout(new BorderLayout());

        textField = new JTextField(5);
        textField.setEditable(false);
        button = new JButton("...");
        button.setPreferredSize(new Dimension(20,15));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHourPickerPopup();
            }
        });

        add(textField, BorderLayout.CENTER);
        add(button, BorderLayout.EAST);

        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendarioInicial.setTime(calendario.getTime());
        atualizarCampoTexto();
    }

    private void showHourPickerPopup() {
        popupMenu = new JPopupMenu(); 
        popupMenu.setLayout(new BorderLayout());

        JPanel hourPickerPanel = createHourPickerPanel();

        popupMenu.add(hourPickerPanel);
        popupMenu.show(this, 0, getHeight());
    }

    private JPanel createHourPickerPanel() {
        JPanel hourPickerPanel = new JPanel(new BorderLayout());
        hourPickerPanel.setPreferredSize(new Dimension(230, 110));
        hourPickerPanel.setBackground(Color.white);

        JPanel contentPane = FormBuilder.create()
                .columns("pref, 5px, pref, 5px, pref")
                .rows("pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref")
                .padding("5px, 5px, 5px, 5px")
                .add(setConfigBotao("+10 H")).xy(1, 1)
                .add(setConfigBotao("-10 H")).xy(1, 3)
                .add(setConfigBotao("+1 H")).xy(1, 5)
                .add(setConfigBotao("-1 H")).xy(1, 7)
                .add(setConfigBotao("Save")).xy(3, 3)
                .add(setConfigBotao("Reset")).xy(3, 5)
                .add(setConfigBotao("+10 M")).xy(5, 1)
                .add(setConfigBotao("-10 M")).xy(5, 3)
                .add(setConfigBotao("+1 M")).xy(5, 5)
                .add(setConfigBotao("-1 M")).xy(5, 7)
                .build();

        hourPickerPanel.add(contentPane, BorderLayout.CENTER);

        return hourPickerPanel;
    }

    private void atualizarCampoTexto() {
        Date horaAtual = calendario.getTime();
        textField.setText(timeFormat.format(horaAtual));
    }

    private void addFuncionalidadeBotoes(JButton botao, boolean aumentar, boolean isHora, int valor) {
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aumentar) {
                    if (isHora) {
                        calendario.add(Calendar.HOUR_OF_DAY, valor);
                    } else {
                        calendario.add(Calendar.MINUTE, valor);
                    }
                } else {
                    if (isHora) {
                        calendario.add(Calendar.HOUR_OF_DAY, -valor);
                    } else {
                        calendario.add(Calendar.MINUTE, -valor);
                    }
                }
                atualizarCampoTexto();
            }
        });
    }

    private JButton setConfigBotao(String nomeBotao) {
        JButton botao = new JButton(nomeBotao);
        botao.setPreferredSize(new Dimension(70, 20));
        botao.setFont(new Font("Arial", Font.PLAIN, 12));
        botao.setFocusable(false);

        switch (nomeBotao) {
            case "+10 H":
                addFuncionalidadeBotoes(botao, true, true, 10);
                break;
            case "+1 H":
                addFuncionalidadeBotoes(botao, true, true, 1);
                break;
            case "-10 H":
                addFuncionalidadeBotoes(botao, false, true, 10);
                break;
            case "-1 H":
                addFuncionalidadeBotoes(botao, false, true, 1);
                break;
            case "+10 M":
                addFuncionalidadeBotoes(botao, true, false, 10);
                break;
            case "+1 M":
                addFuncionalidadeBotoes(botao, true, false, 1);
                break;
            case "-10 M":
                addFuncionalidadeBotoes(botao, false, false, 10);
                break;
            case "-1 M":
                addFuncionalidadeBotoes(botao, false, false, 1);
                break;
            case "Save":
                botao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Calendar selectedTime = (Calendar) calendario.clone(); 
                        textField.setText(timeFormat.format(selectedTime.getTime()));
                        if (popupMenu != null) {
                            popupMenu.setVisible(false); 
                        }
                    }
                });
                break;
            case "Reset":
                botao.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calendario.setTime(calendarioInicial.getTime());
                        atualizarCampoTexto();
                    }
                });
                break;
        }

        return botao;
    }

    public String getSelectedTime() {
        return textField.getText();
    }

    public void setSelectedTime(String tempo) {
        try {
            Date date = timeFormat.parse(tempo);
            calendario.setTime(date);
            atualizarCampoTexto();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}