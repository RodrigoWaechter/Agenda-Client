package com.projeto.agenda.client.form;

import com.projeto.agenda.client.custom.BaseForm;

import javax.swing.*;
import java.awt.*;


public class CadastroFrm extends BaseForm {


    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public CadastroFrm() throws HeadlessException {
        setSize(300,400);
        setTitle("Cadastro");

    }

    @Override
    protected void createComponents() {


    }

    @Override
    protected JComponent createMainPanel() {
        return null;
    }
}
