package com.projeto.agenda.client.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.projeto.agenda.server.domain.Cliente;

@SuppressWarnings("serial")
public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private final String[] columnNames = {"Nome", "Nascimento", "CPF", "Telefone"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cliente.getNomeCliente();
            case 1:
                return cliente.getFormmatedDate();
            case 2:
                return cliente.getCpfCliente();
            case 3:
                return cliente.getTelefoneCliente();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
        fireTableRowsInserted(clientes.size() - 1, clientes.size() - 1);
    }

    public void deleteCliente(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                clientes.remove(rowIndex);
                fireTableRowsDeleted(rowIndex, rowIndex);
            }
        } else {
            System.err.println("Índice inválido para exclusão: " + rowIndex);
        }
    }

    public void updateCliente(int rowIndex, Cliente cliente) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            clientes.set(rowIndex, cliente);
            fireTableRowsUpdated(rowIndex, rowIndex);
        } else {
            System.err.println("Índice inválido para atualização: " + rowIndex);
        }
    }

    public Cliente getCliente(int rowIndex) {
        return clientes.get(rowIndex);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }
}
