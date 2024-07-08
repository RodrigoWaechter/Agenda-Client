package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;


public class LoginFrm extends JFrame {
    public LoginFrm() {
        JPanel panel = new JPanel();

        setSize(600, 600);
        getContentPane().add(panel);
        setVisible(true);


        DefaultFormBuilder x = new DefaultFormBuilder(new FormLayout("pref, 100dlu"), new FormDebugPanel());


        x.appendRow(RowSpec.decode("18dlu"));
        x.append(new JTextField(""));
        x.append(new JLabel("Entrar:"));




        panel.add(x.getPanel());



        /*JLabel senha = new JLabel("Senha:");
        panel.add(senha);

        JPasswordField password = new JPasswordField(20);
        panel.add(password);

        JButton button = new JButton("Login");
        panel.add(button);

        JButton button_sign_up = new JButton("Cadastro");
        panel.add(button_sign_up);

        /*button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = login.getText();
                String senha = password.getText();
                if(email.length() > 0 && senha.length() > 0) {
                    JOptionPane.showMessageDialog(null, "Login: " + login.getText() + "\nSenha: " + password.getText());

                }
                else{
                    JOptionPane.showMessageDialog(null, "Dados inválidos.");
                }
            }
        });
        button_sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroFrm telaCadastro = new CadastroFrm();
                telaCadastro.setVisible(true);
                setVisible(false);
            }
        });*/

    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrm());
    }
}