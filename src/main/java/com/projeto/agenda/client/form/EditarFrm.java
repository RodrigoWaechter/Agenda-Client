package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.custom.BaseForm;

import javax.swing.*;

public class EditarFrm extends BaseForm {

    public EditarFrm() {
        setTitle("Editar atendimento");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        startup();
    }

    @Override
    protected void createComponents() {

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref , pref, pref, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);
        return builder.build();
    }
}