package com.projeto.agenda.client.form;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainFrm extends JFrame {
    private static final long serialVersionUID = 1L;

    public mainFrm() {
        setTitle("Aplicação com Abas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLocationRelativeTo(null);
        
        AtendimentoFrm tela = new AtendimentoFrm();
        NovoFrm novo = new NovoFrm();
        EditarFrm editar = new EditarFrm();
        
        
        JTabbedPane pane = new JTabbedPane();
        pane.addTab(tela.getPanelName(), tela);
        pane.addTab(novo.getPanelName(), novo);
        pane.addTab(editar.getPanelName(), editar);
        
        getContentPane().add(pane);
        pack();
        
        
        
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            mainFrm mainFrame = new mainFrm();
            mainFrame.setVisible(true);
        });
    }
}