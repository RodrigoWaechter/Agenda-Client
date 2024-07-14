package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CadastroFrm extends BaseForm {
    JTextField txtNomeEmpresario;
    JTextField txtNomeEmpresa;
    JTextField txtCNPJ;
    JTextField txtEmail;
    JPasswordField txtSenha;
    JPasswordField txtConfirmSenha;
    JButton btnCadastro;


    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public CadastroFrm() throws HeadlessException {
        setSize(400,500);
        startup();
    }


    protected void createComponents() {
        txtNomeEmpresario = new JTextField(20);
        txtNomeEmpresa = new JTextField(20);
        txtCNPJ = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSenha = new JTextField(20);
        txtConfirmSenha = new JTextField(20);
        btnCadastro = new JButton("Cadastrar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu:grow", " pref, 5px , pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout).padding("5px,5px,5px,5px");


        builder.add(createConfiguredLabel("Nome Completo:")).xy(1,1);
        builder.add(txtNomeEmpresario).xy(3,1);

        builder.add(createConfiguredLabel("Nome Fantasia:")).xy(1,3);
        builder.add(txtNomeEmpresa).xy(3,3);

        builder.add(createConfiguredLabel("CNPJ:")).xy(1,5);
        builder.add(txtCNPJ).xy(3,5);

        builder.add(createConfiguredLabel("Email::")).xy(1,7);
        builder.add(txtEmail).xy(3,7);

        builder.add(createConfiguredLabel("Senha:")).xy(1,9);
        builder.add(txtSenha).xy(3,9);

        builder.add(createConfiguredLabel("Confirme sua senha: ")).xy(1,11);
        builder.add(txtConfirmSenha).xy(3,11);

        builder.add(btnCadastro).xy(3,13);



        return builder.build();

    }

    @Override
    protected String getPanelName() {
        return "Cadastro";
    }
}
