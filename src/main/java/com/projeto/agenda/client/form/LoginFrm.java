package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.custom.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends BaseForm {
    JTextField txtNome;
    JPasswordField txtSenha;
    JButton btnLogin;
    JButton btnCadastro;

    public LoginFrm() {
        setTitle("Login");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        startup();


    }


    @Override
    protected void createComponents() {
        txtNome = createConfiguredTextField(20);
        txtSenha = createConfiguredPasswordField(20);
        btnCadastro = createConfiguredButton("Cadastro", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroFrm telaCadastro = new CadastroFrm();
                telaCadastro.setVisible(true);
                setVisible(false);
            }
        });
        btnLogin = createConfiguredButton("Login", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtendimentoFrm telaInicial = new AtendimentoFrm();
                telaInicial.setVisible(true);
                setVisible(false);
                /*if(txtNome.getText().length() > 0 && txtSenha.getText().length() >= 8){
                    AtendimentoFrm telaInicial = new AtendimentoFrm();
                    telaInicial.setVisible(true);
                    setVisible(false);
                }
                else{

                }*/

            }
        });


    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu:grow", " pref, 5px , pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout).padding("5px,5px,5px,5px");

        builder.add(createConfiguredLabel("Login:")).xy(1,1);
        builder.add(txtNome).xy(3,1);

        builder.add(createConfiguredLabel("Senha:")).xy(1,3);
        builder.add(txtSenha).xy(3,3);

        builder.add(btnLogin).xy(3,5);

        builder.add(btnCadastro).xy(3,7);


        return builder.build();
    }
    public static void main(String[] args) {
        LoginFrm tela = new LoginFrm();
        tela.setVisible(true);
    }
}

