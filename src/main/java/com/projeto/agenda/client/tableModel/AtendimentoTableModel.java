package com.projeto.agenda.client.tableModel;

import com.jgoodies.binding.list.SelectionInList;
import com.projeto.agenda.components.AbstractListTableAdapter;
import com.projeto.agenda.server.domain.Cliente;

@SuppressWarnings("serial")
public class AtendimentoTableModel extends AbstractListTableAdapter<Cliente> {

    public AtendimentoTableModel() {
        this(new SelectionInList<>());
    }

    public AtendimentoTableModel(SelectionInList<Cliente> selectionInList) {
        super(selectionInList, new String[] {"Cliente", "Data", "Hora Inicio", "Hora Fim" });
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
