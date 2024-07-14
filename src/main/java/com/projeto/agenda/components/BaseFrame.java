package com.projeto.agenda.components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.ValueModel;

public abstract class BaseFrame<T> extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private PresentationModel<T> model;

    public BaseFrame() {
        this.model = new PresentationModel<>();
        setTitle(getFrameTitle());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startup();
    }

    protected void startup() {
        createComponents();
        add(createMainPanel());
        pack();
        setLocationRelativeTo(null); 
    }

    protected abstract void createComponents();

    protected abstract JPanel createMainPanel();

    protected abstract String getFrameTitle();

    protected ValueModel getModel(String propertyName) {
        return model.getModel(propertyName);
    }

    protected T getBean() {
        return model.getBean();
    }
}
