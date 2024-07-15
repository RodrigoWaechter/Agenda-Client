package com.projeto.agenda.client.form;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.server.domain.Atendimento;

public class LoginFrm extends BaseForm<Atendimento> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtNome;
    JPasswordField txtSenha;
    JButton btnLogin;
    JButton btnCadastro;

    public LoginFrm() {


    }


    @Override
    protected void createComponents() {
        txtNome = ComponentFactoryAgenda.textField(getModel(""));
        txtSenha = ComponentFactoryAgenda.passwordField(getModel(""));

    }

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu:grow", " pref, 5px , pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout).padding("5px,5px,5px,5px");

        builder.add(new JLabel("Login:")).xy(1,1);
        builder.add(txtNome).xy(3,1);

        builder.add(new JLabel("Senha:")).xy(1,3);
        builder.add(txtSenha).xy(3,3);

        builder.add(btnLogin).xy(3,5);

        builder.add(btnCadastro).xy(3,7);

        return builder.build();

    }

    @Override
    protected String getPanelName() {
        return "Login";
    }

    public static void main(String[] args) {
        LoginFrm tela = new LoginFrm();
        tela.setVisible(true);
    }
}

