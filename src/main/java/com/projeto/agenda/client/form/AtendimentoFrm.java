package com.projeto.agenda.client.form;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;
import com.projeto.agenda.client.tableModel.AtendimentoTableModel;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.components.HourPickerField;
import com.projeto.agenda.server.domain.Atendimento;
import com.projeto.agenda.server.domain.Cliente;
import com.projeto.agenda.server.enums.ServicoEnum;
import com.toedter.calendar.JDateChooser;

public class AtendimentoFrm extends BaseForm<Atendimento> {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JDateChooser dtAtendimento;
    private JTextField txtNome;
    private HourPickerField horaInicio;
    private HourPickerField horaFim;
    private JComboBox<ServicoEnum> cobServico;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private AtendimentoTableModel tableModel;

    @Override
    protected void createComponents() {
        txtNome = ComponentFactoryAgenda.textField(getModel("cliente"));
        horaInicio = ComponentFactoryAgenda.hourPickerField(getModel("horarioInicio"));
        horaFim = ComponentFactoryAgenda.hourPickerField(getModel("horarioFim"));
        dtAtendimento = ComponentFactoryAgenda.dateField(getModel("data"));
        cobServico = ComponentFactoryAgenda.enumComboBox(getModel("servico"), ServicoEnum.class);
        btnAdicionar = ComponentFactoryAgenda.button("Adicionar", actionAdicionar());
        btnEditar = ComponentFactoryAgenda.button("Editar", actionEditar());
        btnExcluir = ComponentFactoryAgenda.button("Excluir", actionExcluir());
        
        // Inicializar o modelo da tabela com dados fictícios
        tableModel = new AtendimentoTableModel();
        initializeTableWithDummyData(); // Método para adicionar dados fictícios
        table = ComponentFactoryAgenda.table(tableModel);
        
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Atendimento atendimentoSelecionado = tableModel.getAtendimento(selectedRow);
                txtNome.setText(atendimentoSelecionado.getCliente().getNomeCliente());
                dtAtendimento.setDate(atendimentoSelecionado.getData());
                horaInicio.setTime(atendimentoSelecionado.getHorarioInicio().toString());
                horaFim.setTime(atendimentoSelecionado.getHorarioFim().toString());
                cobServico.setSelectedItem(atendimentoSelecionado.getServico());
                btnAdicionar.setEnabled(false);
            } else {
                clearFields();
                btnAdicionar.setEnabled(true);
            }
        });
    }

    // Método para inicializar a tabela com dados fictícios
 // Método para inicializar a tabela com dados fictícios
    private void initializeTableWithDummyData() {
        // Adicionar alguns atendimentos fictícios
        tableModel.addAtendimento(new Atendimento(1, ServicoEnum.BARBEIRO, new Cliente("Cliente A"), LocalTime.of(9, 0), LocalTime.of(12, 0), new Date()));
        tableModel.addAtendimento(new Atendimento(2, ServicoEnum.CONSULTA, new Cliente("Cliente B"), LocalTime.of(13, 0), LocalTime.of(16, 0), new Date()));
    }


    @Override
    protected JPanel createMainPanel() {
        return FormBuilder.create()
                .columns("pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow, 5px, pref, 5px, 100dlu:grow")
                .rows("pref, 5px, pref, 5px, pref, 5px, pref")
                .addLabel("Cliente:").xy(1, 1)
                .add(txtNome).xy(3, 1)
                .addLabel("Data consulta:").xy(5, 1)
                .add(dtAtendimento).xy(7, 1)
                .addLabel("Hora Inicio:").xy(9, 1)
                .add(horaInicio).xy(11, 1)
                .addLabel("Hora Fim:").xy(13, 1)
                .add(horaFim).xy(15, 1)
                .addLabel("Serviço: ").xy(5, 3)
                .add(cobServico).xy(7, 3)
                .add(new JScrollPane(table)).xyw(1, 5, 15)
                .add(createPanelButton()).xyw(1, 7, 15)
                .build();
    }

    private JPanel createPanelButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        return panel;
    }

    private ActionListener actionAdicionar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = txtNome.getText();
                Date data = dtAtendimento.getDate();
                ServicoEnum servico = (ServicoEnum) cobServico.getSelectedItem();
                Atendimento novoAtendimento = new Atendimento();
                novoAtendimento.setCliente(new Cliente(nomeCliente));
                novoAtendimento.setData(data);
                novoAtendimento.setHorarioInicio(LocalTime.parse(horaInicio.getTime()));
                novoAtendimento.setHorarioFim(LocalTime.parse(horaFim.getTime()));
                novoAtendimento.setServico(servico);
                tableModel.addAtendimento(novoAtendimento);
                clearFields();
            }
        };
    }

    private ActionListener actionEditar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Atendimento atendimentoSelecionado = tableModel.getAtendimento(selectedRow);
                    atendimentoSelecionado.getCliente().setNomeCliente(txtNome.getText());
                    atendimentoSelecionado.setData(dtAtendimento.getDate());
                    atendimentoSelecionado.setHorarioInicio(LocalTime.parse(horaInicio.getTime()));
                    atendimentoSelecionado.setHorarioFim(LocalTime.parse(horaFim.getTime()));
                    atendimentoSelecionado.setServico((ServicoEnum) cobServico.getSelectedItem());
                    tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(AtendimentoFrm.this, "Selecione um atendimento para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener actionExcluir() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.deleteAtendimento(selectedRow);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(AtendimentoFrm.this, "Selecione um atendimento para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void clearFields() {
        txtNome.setText("");
        dtAtendimento.setDate(null);
        horaInicio.setTime("00:00");
        horaFim.setTime("00:00");
        cobServico.setSelectedIndex(0);
        btnAdicionar.setEnabled(true);
    }

    @Override
    protected String getPanelName() {
        return "Atendimento";
    }
}
