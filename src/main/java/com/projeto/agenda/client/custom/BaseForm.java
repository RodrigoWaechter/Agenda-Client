package com.projeto.agenda.client.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public abstract class BaseForm extends JFrame {
    private static final long serialVersionUID = 1L;

    protected JLabel createConfiguredLabel(String nomeLabel) {
        JLabel label = new JLabel(nomeLabel);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.white);
        label.setBackground(Color.pink);
        return label;
    }

    protected JTextField createConfiguredTextField(int columns) {
        JTextField txt = new JTextField(columns);
        txt.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setFont(new Font("Arial", Font.PLAIN, 20));
        txt.setPreferredSize(new Dimension(30, 40));
        return txt;
    }
    protected JPasswordField createConfiguredPasswordField(int columns) {
        JPasswordField txt = new JPasswordField(columns);
        txt.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setFont(new Font("Arial", Font.PLAIN, 20));
        txt.setPreferredSize(new Dimension(30, 40));
        return txt;
    }

    protected JButton createConfiguredButton(String nomeBotao, ActionListener actionListener) {
        JButton botao = new JButton(nomeBotao);
        botao.setFont(new Font("Arial", Font.PLAIN, 20));
        botao.setPreferredSize(new Dimension(100, 50));
        botao.setBorder(new RoundedBorder(Color.BLACK, 5));
        botao.setFocusable(false);
        botao.setBackground(Color.WHITE);
        botao.setForeground(Color.BLACK);
        botao.addActionListener(actionListener);
        return botao;
    }

    protected void startup() {
        createComponents();
        getContentPane().add(createMainPanel());
        getContentPane().setBackground(Color.darkGray);
    }

    protected abstract void createComponents();
    protected abstract JComponent createMainPanel();
}