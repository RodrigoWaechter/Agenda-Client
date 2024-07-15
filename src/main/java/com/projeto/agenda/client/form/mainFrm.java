package com.projeto.agenda.client.form;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainFrm extends JFrame {
	private static final long serialVersionUID = 1L;

	public mainFrm() {
		setTitle("AtendPlus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(0, 0);

		AtendimentoFrm tela = new AtendimentoFrm();
		NovoFrm novo = new NovoFrm();

		JTabbedPane pane = new JTabbedPane();
		pane.addTab(tela.getPanelName(), tela);
		pane.addTab(novo.getPanelName(), novo);

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