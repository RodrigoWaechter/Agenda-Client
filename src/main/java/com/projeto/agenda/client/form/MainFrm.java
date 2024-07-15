package com.projeto.agenda.client.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrm extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrm() {
		setTitle("AtendPlus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(0, 0);
		
		AtendimentoFrm tela = new AtendimentoFrm();
		ClienteFrm cliente = new ClienteFrm();

		JTabbedPane pane = new JTabbedPane();
		pane.addTab(tela.getPanelName(), tela);
		pane.addTab(cliente.getPanelName(), cliente);

		getContentPane().add(pane);
		pack();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MainFrm mainFrame = new MainFrm();
			mainFrame.setVisible(true);
		});	
	}
}
