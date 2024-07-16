package com.projeto.agenda.client.tableModel;

import com.jgoodies.binding.list.SelectionInList;
import com.projeto.agenda.components.AbstractListTableAdapter;
import com.projeto.agenda.server.domain.Atendimento;


@SuppressWarnings("serial")
public class AtendimentoTableModel extends AbstractListTableAdapter<Atendimento> {

    public AtendimentoTableModel() {
        this(new SelectionInList<>());
    }

    public AtendimentoTableModel(SelectionInList<Atendimento> selectionInList) {
        super(selectionInList, new String[] {"Cliente", "Data", "Hora Inicio", "Hora Fim" });
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atendimento bean = (Atendimento) getRow(rowIndex);
        switch (columnIndex) {
            case 0:
                return bean.getIdAtendimento();
            case 1:
                return bean.getServico();
            case 2:
                return bean.getCliente();
            case 3:
                return bean.getHorarioInicio();
            case 4:
                return bean.getHorarioFim();
            default:
                return null;
        }
    }
}
