package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ApagarFrm extends BaseForm {
    List<String> listaServicos;
    JComboBox<String> cmbPesquisa;
    JTextField txtPesquisa;
    JButton btnPesquisa;
    JTable tabelaResultado;


    public ApagarFrm() {
        setSize(463, 600);
        startup();
    }

    @Override
    protected void createComponents() {
        txtPesquisa = new JTextField(8);
        btnPesquisa = new JButton("Pesquisar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        listaServicos = new ArrayList<>();
        listaServicos.add("Dia");
        listaServicos.add("Nome");
        cmbPesquisa = new JComboBox<>(listaServicos.toArray(new String[0]));

        tabelaResultado = new JTable();
        tabelaResultado.setFont(new Font("Arial", Font.PLAIN,15));
        tabelaResultado.setBackground(Color.LIGHT_GRAY);
        tabelaResultado.setRowHeight(35);
        tabelaResultado.setDefaultEditor(Object.class, null);
        tabelaResultado.setColumnSelectionAllowed(false);
        tabelaResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaResultado.setFocusable(false);

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref , 5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        JPanel panelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelCabecalho.setBackground(Color.LIGHT_GRAY);

        panelCabecalho.add(createConfiguredLabel("Pesquisar por:"));
        panelCabecalho.add(cmbPesquisa);
        panelCabecalho.add(txtPesquisa);
        panelCabecalho.add(btnPesquisa);
        builder.add(panelCabecalho).xy(1,1);
        builder.add(tabelaResultado).xy(1,3);

        return builder.build();
    }

    @Override
    protected String getPanelName() {
        return "Apagar";
    }
}