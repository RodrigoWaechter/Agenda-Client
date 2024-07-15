package com.projeto.agenda.client.tableModel;

import com.jgoodies.binding.list.SelectionInList;
import com.projeto.agenda.components.AbstractListTableAdapter;
import com.projeto.agenda.server.domain.Cliente;

@SuppressWarnings("serial")
public class ClienteTableModel extends AbstractListTableAdapter<Cliente> {

    public ClienteTableModel() {
        this(new SelectionInList<>());
    }

    public ClienteTableModel(SelectionInList<Cliente> selectionInList) {
        super(selectionInList, new String[] { "ID", "Nome", "Nascimento", "CPF", "Telefone" });
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente bean = (Cliente) getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                return bean.getIdCliente();
            case 1:
                return bean.getNomeCliente();
            case 2:
                return bean.getNascimentoCliente();
            case 3:
                return bean.getCpfCliente();
            case 4:
                return bean.getTelefoneCliente();
            default:
                return null;
        }
    }
}