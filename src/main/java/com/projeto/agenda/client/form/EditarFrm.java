package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.custom.BaseForm;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditarFrm extends BaseForm {
    List<String> listaServicos;
    JComboBox<String> cmbPesquisa;
    JTextField txtPesquisa;
    JButton btnPesquisa;
    JTable tabelaResultado;

    JDateChooser dtNovo;
    JTextField txtNome;
    List<String> metodoPagamento;
    JComboBox<String> cmbMetodo;

    public EditarFrm() {
        setTitle("Editar atendimento");
        setSize(463, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        startup();
    }

    @Override
    protected void createComponents() {
        txtPesquisa = createConfiguredTextField(8);
        btnPesquisa = createConfiguredButton("Pesquisar", new ActionListener() {
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

        dtNovo = new JDateChooser();
        dtNovo.setDateFormatString("dd/MM/yyyy");
        txtNome = createConfiguredTextField(20);
        metodoPagamento = new ArrayList<>();
        metodoPagamento.add("Dinheiro");
        metodoPagamento.add("PIX");
        metodoPagamento.add("Cartão de Débito");
        metodoPagamento.add("Cartão de Crédito");
        cmbMetodo = new JComboBox<>(metodoPagamento.toArray(new String[0]));

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref , 5px, pref, 5px, pref, pref, pref, pref, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        FormLayout layout2 = new FormLayout("pref, 100dlu:grow", " pref , pref, pref, pref, pref, pref, pref");
        FormBuilder rodaPe = FormBuilder.create().debug(true).layout(layout2);

        JPanel panelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelCabecalho.setBackground(Color.LIGHT_GRAY);


        panelCabecalho.add(createConfiguredLabel("Pesquisar por:"));
        panelCabecalho.add(cmbPesquisa);
        panelCabecalho.add(txtPesquisa);
        panelCabecalho.add(btnPesquisa);
        builder.add(panelCabecalho).xy(1,1);
        builder.add(tabelaResultado).xy(1,3);

        

        rodaPe.add(createConfiguredLabel("Nova data:")).xy(1,1);
        rodaPe.add(dtNovo).xy(2,1);

        rodaPe.add(createConfiguredLabel("Editar nome:")).xy(1,3);
        rodaPe.add(txtNome).xy(2,3);

        rodaPe.add(createConfiguredLabel("Editar método:")).xy(1,5);
        rodaPe.add(cmbMetodo).xy(2,5);


        return builder.build();
    }
}