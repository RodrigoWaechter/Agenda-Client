package com.projeto.agenda.client.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.FormBuilder;

import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.components.HourPicker;
import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class AtendimentoFrm extends BaseForm<Atendimento> {
    private static final long serialVersionUID = 1L;
    private JTable tabelaServicos;
    JDateChooser dtNovo;
    JTextField txtNome;
    private HourPicker horaInicio;
    private HourPicker horaFim;
    
    public AtendimentoFrm() {
    
    }

    @Override
    protected void createComponents() {
    	txtNome = ComponentFactoryAgenda.textField(getModel("cliente"));
    	horaInicio = ComponentFactoryAgenda.hourPickerField(getModel("horarioInicio"));
    	horaFim = ComponentFactoryAgenda.hourPickerField(getModel("horarioFim"));
    	
    	horaInicio.setPreferredSize(new Dimension(215,75));
    	horaFim.setPreferredSize(new Dimension(215,75));
    	
        dtNovo = new JDateChooser();
        dtNovo.setDateFormatString("dd/MM/yyyy");

        tabelaServicos = new JTable();
        tabelaServicos.setFont(new Font("Arial", Font.PLAIN, 15));
        tabelaServicos.setBackground(Color.LIGHT_GRAY);
        tabelaServicos.setRowHeight(35);
        tabelaServicos.setDefaultEditor(Object.class, null);
        tabelaServicos.setColumnSelectionAllowed(false);
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaServicos.setFocusable(false);
    }

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu", "pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);
        
        JPanel panelCabecalho = new JPanel();

        builder.addLabel("Cliente:").xy(1,1);
        builder.add(txtNome).xy(3,1);
        
        builder.addLabel("Data consulta:").xy(1,3);
        builder.add(dtNovo).xy(3,3);

        builder.addLabel("Hora Inicio:").xy(1,5);
        builder.add(horaInicio).xy(3, 5);
        
        builder.addLabel("Hora Fim:").xy(1,7);
        builder.add(horaFim).xy(3, 7);
        
        
        panelCabecalho.add(tabelaServicos);
        builder.add(panelCabecalho).xy(3, 9);
        

        return builder.build();
    }

    @Override
    protected String getPanelName() {
        return "Atendimento";
    }
}
