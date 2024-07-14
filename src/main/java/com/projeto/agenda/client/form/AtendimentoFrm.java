package com.projeto.agenda.client.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.server.domain.Atendimento;

public class AtendimentoFrm extends BaseForm<Atendimento> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable tabelaServicos;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnApagar;

    public AtendimentoFrm() {
        setSize(800, 800);

        startup();
    }

    @Override
    protected void createComponents() {
    	

        tabelaServicos = new JTable();
        tabelaServicos.setFont(new Font("Arial", Font.PLAIN, 15));
        tabelaServicos.setBackground(Color.LIGHT_GRAY);
        tabelaServicos.setRowHeight(35);
        tabelaServicos.setDefaultEditor(Object.class, null);
        tabelaServicos.setColumnSelectionAllowed(false);
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaServicos.setFocusable(false);

        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NovoFrm novoAtendimentoFrm = new NovoFrm();
                novoAtendimentoFrm.setVisible(true);
            }
        });
        
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarFrm editarAtendimentoFrm = new EditarFrm();
                editarAtendimentoFrm.setVisible(true);
            }
        });

        btnApagar = new JButton("Apagar");
        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApagarFrm apagarAtendimentoFrm = new ApagarFrm();
                apagarAtendimentoFrm.setVisible(true);
            }
        });
    }

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref,100dlu:grow", "pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);



  
        builder.add(tabelaServicos).xy(1, 1);
        
;

        return builder.build();
    }


	@Override
	protected String getPanelName() {
		// TODO Auto-generated method stub
		return "Atendimento";
	}
}
