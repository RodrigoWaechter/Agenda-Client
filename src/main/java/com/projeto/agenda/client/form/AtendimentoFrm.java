package com.projeto.agenda.client.form;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.tableModel.AtendimentoTableModel;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.components.HourPickerField;
import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class AtendimentoFrm extends BaseForm<Atendimento> {
	private static final long serialVersionUID = 1L;
	private JTable tabelaServicos;
	JDateChooser dtNovo;
	JTextField txtNome;
	private HourPickerField horaInicio;
	private HourPickerField horaFim;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	@Override
	// Função para criar os componentes da interface
	protected void createComponents() {
		txtNome = ComponentFactoryAgenda.textField(getModel("cliente"));
		horaInicio = ComponentFactoryAgenda.hourPickerField(getModel("horarioInicio"));
		horaFim = ComponentFactoryAgenda.hourPickerField(getModel("horarioFim"));

		dtNovo = ComponentFactoryAgenda.dateField(getModel("data"));

		tabelaServicos = new JTable(new AtendimentoTableModel());
		tabelaServicos.setFont(new Font("Arial", Font.PLAIN, 15));
		tabelaServicos.setBackground(Color.black);
		tabelaServicos.setRowHeight(35);
		tabelaServicos.setDefaultEditor(Object.class, null);
		tabelaServicos.setColumnSelectionAllowed(false);
		tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaServicos.setFocusable(false);

		btnAdicionar = ComponentFactoryAgenda.button("Adicionar");
		btnEditar = ComponentFactoryAgenda.button("Editar/Salvar");
		btnExcluir = ComponentFactoryAgenda.button("Excluir");

		addActionListeners();

	}

	@Override
	// Cria o painel layout e o painel
	protected JPanel createMainPanel() {
		FormLayout layout = new FormLayout(
				"pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow",
				"pref, 5px, pref, 5px, pref");
		FormBuilder builder = FormBuilder.create().layout(layout);

		builder.addLabel("Cliente:").xy(1, 1);
		builder.add(txtNome).xy(3, 1);

		builder.addLabel("Data consulta:").xy(5, 1);
		builder.add(dtNovo).xy(7, 1);

		builder.addLabel("Hora Inicio:").xy(9, 1);
		builder.add(horaInicio).xy(11, 1);

		builder.addLabel("Hora Fim:").xy(13, 1);
		builder.add(horaFim).xy(15, 1);

		builder.add(tabelaServicos).xyw(1, 3, 15);
		builder.add(createPanelButton()).xyw(1, 5, 15);

		return builder.build();// Constrói e retorna o painel
	}

	// Função que cria um painel para melhor centralizar os botões
	private JPanel createPanelButton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panel.add(btnAdicionar);
		panel.add(btnEditar);
		panel.add(btnExcluir);

		return panel;
	}

	// Adiciona os listeners aos botões
	private void addActionListeners() {
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().isEmpty() || dtNovo.getDate() == null) {
					System.out.println("Valores Invalidos");
					return;
				} else {
					// coloca os valores dentro do DB
				}

			}
		});

		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	@Override
	// Define o nome do painel
	protected String getPanelName() {
		return "Atendimento";
	}
}
