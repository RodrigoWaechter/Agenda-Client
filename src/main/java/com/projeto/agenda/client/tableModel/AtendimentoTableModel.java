package com.projeto.agenda.client.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.projeto.agenda.server.domain.Atendimento;

@SuppressWarnings("serial")
public class AtendimentoTableModel extends AbstractTableModel {
    private List<Atendimento> atendimentos;
    private final String[] columnNames = {"Cliente", "Serviço", "Data", "Hora Início", "Hora Fim"};

    public AtendimentoTableModel() {
        this.atendimentos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return atendimentos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atendimento atendimento = atendimentos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return atendimento.getCliente().getNomeCliente();
            case 1:
                return atendimento.getServico();
            case 2:
                return atendimento.getFormmatedDate();
            case 3:
                return atendimento.getHorarioInicio();
            case 4:
                return atendimento.getHorarioFim();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
        fireTableRowsInserted(atendimentos.size() - 1, atendimentos.size() - 1);
    }

    public void deleteAtendimento(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este atendimento?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                atendimentos.remove(rowIndex);
                fireTableRowsDeleted(rowIndex, rowIndex);
            }
        } else {
            System.err.println("Índice inválido para exclusão: " + rowIndex);
        }
    }

    public void updateAtendimento(int rowIndex, Atendimento atendimento) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            atendimentos.set(rowIndex, atendimento);
            fireTableRowsUpdated(rowIndex, rowIndex);
        } else {
            System.err.println("Índice inválido para atualização: " + rowIndex);
        }
    }

    public Atendimento getAtendimento(int rowIndex) {
        return atendimentos.get(rowIndex);
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
        fireTableDataChanged();
    }
}
