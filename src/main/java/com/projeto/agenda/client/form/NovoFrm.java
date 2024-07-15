package com.projeto.agenda.client.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.tableModel.ClienteTableModel;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;

import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class NovoFrm extends BaseForm<Atendimento> {
    private static final long serialVersionUID = 1L;
    private JTextField txtNomeCliente;
    private JDateChooser dataNascimentoCliente;
    private JTextField txtcpfCliente;
    private JTextField txttelefoneCliente;
    private JTable tabelaServicos;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;

    public NovoFrm() {

    }

    @Override
    protected void createComponents() {
        txtNomeCliente = ComponentFactoryAgenda.textField(getModel("cliente"));
        dataNascimentoCliente = ComponentFactoryAgenda.dateField(getModel("datanascimento"));
        txtcpfCliente = ComponentFactoryAgenda.textField(getModel("cpf"));
        txttelefoneCliente = ComponentFactoryAgenda.textField(getModel("telefone"));
        
        btnAdicionar = ComponentFactoryAgenda.button("Adicionar");
        btnEditar = ComponentFactoryAgenda.button("Editar/Salvar");
        btnExcluir = ComponentFactoryAgenda.button("Excluir");

        tabelaServicos = new JTable(new ClienteTableModel());
        tabelaServicos.setFont(new Font("Arial", Font.PLAIN, 15));
        tabelaServicos.setBackground(Color.LIGHT_GRAY);
        tabelaServicos.setRowHeight(35);
        tabelaServicos.setDefaultEditor(Object.class, null);
        tabelaServicos.setColumnSelectionAllowed(false);
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaServicos.setFocusable(false);

        addActionListeners();
    }

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout(
        		"pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 10px, 100dlu:grow",
        		" pref ,11px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);
        
        builder.addLabel("Nome:").xy(1, 1);
        builder.add(txtNomeCliente).xy(3, 1);

        builder.addLabel("CPF:").xy(5, 1);
        builder.add(txtcpfCliente).xy(7, 1);

        builder.addLabel("Data nascimento:").xy(9, 1);
        builder.add(dataNascimentoCliente).xy(11, 1);

        builder.addLabel("Telefone:").xy(13, 1);
        builder.add(txttelefoneCliente).xy(15, 1);

        builder.add(tabelaServicos).xyw(1, 3, 15);
        builder.add(createPanelButton()).xyw(1, 5, 15);

        return builder.build();
    }
    
    private JPanel createPanelButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        return panel;
    }

    private void addActionListeners() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    @Override
    protected String getPanelName() {
        return "Cliente";
    }
}
