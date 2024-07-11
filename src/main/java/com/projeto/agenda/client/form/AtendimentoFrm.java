package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.custom.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoFrm extends BaseForm {
    List<String> listaServicos;
    JComboBox<String> cmbPesquisa;
    JButton btnConfig;
    JTextField txtPesquisa;
    JButton btnPesquisa;
    JTable tabelaServicos;
    JButton btnNovo;
    JButton btnEditar;
    JButton btnApagar;



    public AtendimentoFrm() {
        setTitle("Atendimento");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        startup();
    }


    @Override
    protected void createComponents() {
        btnConfig = new JButton("Configurações");
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

        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigFrm telaConfigFrm = new ConfigFrm();
                telaConfigFrm.setVisible(true);

            }
        });

        tabelaServicos = new JTable();
        tabelaServicos.setFont(new Font("Arial", Font.PLAIN,15));
        tabelaServicos.setBackground(Color.LIGHT_GRAY);
        tabelaServicos.setRowHeight(35);
        tabelaServicos.setDefaultEditor(Object.class, null);
        tabelaServicos.setColumnSelectionAllowed(false);
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaServicos.setFocusable(false);

        btnNovo = createConfiguredButton("Novo", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            NovoFrm novoAtendimentoFrm = new NovoFrm();
            novoAtendimentoFrm.setVisible(true);

            }
        });

        btnEditar = createConfiguredButton("Editar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            EditarFrm editarAtendimentoFrm = new EditarFrm();
            editarAtendimentoFrm.setVisible(true);

            }
        });
        btnApagar = createConfiguredButton("Apagar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            ApagarFrm apagarAtendimentoFrm = new ApagarFrm();
            apagarAtendimentoFrm.setVisible(true);
            }
        });

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref , pref, pref, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        JPanel panelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelCabecalho.setBackground(Color.LIGHT_GRAY);

        JPanel panelBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
        panelBaixo.setBackground(Color.LIGHT_GRAY);

        btnConfig.setFont(new Font("Arial",Font.PLAIN, 10));
        btnConfig.setSize(new Dimension(5,10));

        panelCabecalho.add(btnConfig);

        panelCabecalho.add(createConfiguredLabel("Pesquisar por:"));

        panelCabecalho.add(cmbPesquisa);
        panelCabecalho.add(txtPesquisa);
        panelCabecalho.add(btnPesquisa);

        builder.add(panelCabecalho).xy(1,1);

        builder.add(tabelaServicos).xy(1,2);
        builder.add(panelBaixo).xy(1,4);
        panelBaixo.add(btnNovo);
        panelBaixo.add(btnEditar);
        panelBaixo.add(btnApagar);

        return builder.build();
    }
    public static void main(String[] args) {
        AtendimentoFrm tela = new AtendimentoFrm();
        tela.setVisible(true);
    }
}
