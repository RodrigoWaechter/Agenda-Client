package com.projeto.agenda.client.form;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.HourPicker;
import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class NovoFrm extends BaseForm<Atendimento> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDateChooser dtNovo;
    JTextField txtNome;
    private HourPicker horaInicio;
    private HourPicker horaFim;


    public NovoFrm() {

    }

    @Override
    protected void createComponents() {
    	
    	txtNome = new JTextField("");
    	horaInicio = new HourPicker();
    	horaFim = new HourPicker();
    	
    	horaInicio.setPreferredSize(new Dimension(215,75));
    	horaFim.setPreferredSize(new Dimension(215,75));
    	
        dtNovo = new JDateChooser();
        dtNovo.setDateFormatString("dd/MM/yyyy");
        
     
    }
        

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu:grow", " pref ,5px, pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);
        
        
        builder.addLabel("Cliente:").xy(1,1);
        builder.add(txtNome).xy(3,1);
        
        builder.addLabel("Data consulta:").xy(1,3);
        builder.add(dtNovo).xy(3,3);

        builder.addLabel("Hora Inicio:").xy(1,5);
        builder.add(horaInicio).xy(3, 5);
        
        builder.addLabel("Hora Fim:").xy(1,7);
        builder.add(horaFim).xy(3, 7);


        return builder.build();
    }

    @Override
    protected String getPanelName() {
        return "Novo";
    }
}
