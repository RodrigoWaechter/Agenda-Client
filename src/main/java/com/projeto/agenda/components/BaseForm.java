package com.projeto.agenda.components;

import javax.swing.JPanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ValueModel;

public abstract class BaseForm<T> extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private PresentationModel<T> model;

    public BaseForm() {
        this.model = new PresentationModel<>();
        startup();
    }

    protected void startup() {
        createComponents();
        add(createMainPanel());
    }

    protected abstract void createComponents();

    protected abstract JPanel createMainPanel();

    protected abstract String getPanelName();

    protected ValueModel getModel(String propertyName) {
        return model.getModel(propertyName);
    }

    protected T getBean() {
        return model.getBean();
    }
}
