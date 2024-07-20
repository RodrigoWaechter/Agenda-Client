package com.projeto.agenda.client.form;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;
import com.projeto.agenda.client.helper.ClienteHelper;
import com.projeto.agenda.client.tableModel.ClienteTableModel;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.server.domain.Cliente;
import com.toedter.calendar.JDateChooser;

public class ClienteFrm extends BaseForm<Cliente> {
	private static final long serialVersionUID = 1L;

	private JTextField txtNomeCliente;
	private JDateChooser dataNascimentoCliente;
	private JTextField txtcpfCliente;
	private JTextField txttelefoneCliente;
	private JTable table;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	private ClienteTableModel tableModel;
	private ClienteHelper helper;

	private boolean isEditMode = false;

	public ClienteFrm() {
		this.helper = new ClienteHelper();
	//	setUpListeners();
	}

	@Override
	protected void createComponents() {
		txtNomeCliente = ComponentFactoryAgenda.textField(getModel("cliente"));
		dataNascimentoCliente = ComponentFactoryAgenda.dateField(getModel("datanascimento"));
		txtcpfCliente = ComponentFactoryAgenda.textField(getModel("cpf"));
		txttelefoneCliente = ComponentFactoryAgenda.textField(getModel("telefone"));
		btnAdicionar = ComponentFactoryAgenda.button("Adicionar", e -> helper.actionAdicionar(this));
		btnEditar = ComponentFactoryAgenda.button("Editar", e -> toggleEditMode());
		btnExcluir = ComponentFactoryAgenda.button("Excluir", e -> helper.actionExcluir(this));
		btnPesquisar = ComponentFactoryAgenda.button("Pesquisar", e -> helper.actionPesquisar(this));
		tableModel = new ClienteTableModel();
		table = ComponentFactoryAgenda.table(tableModel);

		table.getSelectionModel().addListSelectionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				Cliente clienteSelecionado = tableModel.getCliente(selectedRow);
				preencheCampos(clienteSelecionado);
			}
		});
	}
/*
	private void setUpListeners() {
		btnAdicionar.addActionListener(e -> helper.actionAdicionar(this));
		btnEditar.addActionListener(e -> toggleEditMode());
		btnExcluir.addActionListener(e -> helper.actionExcluir(this));
		btnPesquisar.addActionListener(e -> helper.actionPesquisar(this));
	}
*/
	@Override
	protected JPanel createMainPanel() {
		return FormBuilder.create()
				.columns("pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 10px, 100dlu:grow")
				.rows(" pref ,5px, pref, 5px, pref")
				.addLabel("Nome:").xy(1, 1)
				.add(txtNomeCliente).xy(3, 1)
				.addLabel("CPF:").xy(5, 1)
				.add(txtcpfCliente).xy(7, 1)
				.addLabel("Data nascimento:").xy(9, 1)
				.add(dataNascimentoCliente).xy(11, 1)
				.addLabel("Telefone:").xy(13, 1)
				.add(txttelefoneCliente).xy(15, 1)
				.add(new JScrollPane(table)).xyw(1, 3, 15)
				.add(createPanelButton()).xyw(1, 5, 15)
				.build();
	}

	private JPanel createPanelButton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		panel.add(btnAdicionar);
		panel.add(btnEditar);
		panel.add(btnExcluir);
		panel.add(btnPesquisar);

		return panel;
	}

	public void toggleEditMode() {
        if (!isEditMode()) {        
            // Enable edit mode
            int selectedRow = getTable().getSelectedRow();
            if (selectedRow != -1) {
                Cliente clienteSelecionado = getTableModel().getCliente(selectedRow);
                preencheCampos(clienteSelecionado);
                enableAllButtons(false);
                getBtnEditar().setEnabled(true);
                getBtnEditar().setText("Salvar");
                setEditMode(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
            }
        }else {
        	helper.actionEditar(this);
        }
    }

	public void preencheCampos(Cliente cliente) {
		txtNomeCliente.setText(cliente.getNomeCliente());
		dataNascimentoCliente.setDate(cliente.getNascimentoCliente());
		txtcpfCliente.setText(cliente.getCpfCliente());
		txttelefoneCliente.setText(cliente.getTelefoneCliente());
	}

	public void updateTable(List<Cliente> clientes) {
		tableModel.getClientes().clear();
		for (Cliente cliente : clientes) {
			tableModel.addCliente(cliente);
		}
	}

	public void clearFormFields() {
		txtNomeCliente.setText("");
		dataNascimentoCliente.setDate(null);
		txtcpfCliente.setText("");
		txttelefoneCliente.setText("");
	}

	public void enableAllButtons(boolean enable) {
		btnAdicionar.setEnabled(enable);
		btnEditar.setEnabled(enable);
		btnExcluir.setEnabled(enable);
		btnPesquisar.setEnabled(enable);
	}

	@Override
	protected String getPanelName() {
		return "Cliente";
	}

	// Getters for the form fields
	public JTextField getTxtNomeCliente() {
		return txtNomeCliente;
	}

	public JDateChooser getDataNascimentoCliente() {
		return dataNascimentoCliente;
	}

	public JTextField getTxtcpfCliente() {
		return txtcpfCliente;
	}

	public JTextField getTxttelefoneCliente() {
		return txttelefoneCliente;
	}

	public JTable getTable() {
		return table;
	}

	public ClienteTableModel getTableModel() {
		return tableModel;
	}

	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnExcluir() {
		return btnExcluir;
	}

	public JButton getBtnPesquisar() {
		return btnPesquisar;
	}

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}
}
