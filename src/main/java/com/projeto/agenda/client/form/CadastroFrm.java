package com.projeto.agenda.client.form;



import javax.swing.*;
import java.awt.*;


public class CadastroFrm extends JFrame{
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public CadastroFrm() throws HeadlessException {
        setSize(300,400);
        setTitle("Cadastro");

        JPanel panel = new JPanel();

        JTextField email = new JTextField("Email:");
        panel.add(email);




    }
}
