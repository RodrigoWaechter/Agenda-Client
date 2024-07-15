package com.projeto.agenda.client.form;


import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class NovoFrm extends BaseForm<Atendimento> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNomeCliente;
    private JDateChooser dataNascimentoCliente;
    private JTextField txtcpfCliente;
    private JTextField txttelefoneCliente;
	

    public NovoFrm() {

    }

    @Override
    protected void createComponents() {
    	
    	txtNomeCliente = ComponentFactoryAgenda.textField(getModel("cliente"));
        dataNascimentoCliente = new JDateChooser();
        txtcpfCliente = ComponentFactoryAgenda.textField(getModel("cpf"));
        txttelefoneCliente = ComponentFactoryAgenda.textField(getModel("telefone"));

    }
        

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref, 5px, 100dlu:grow", " pref ,5px, pref, 5px, pref, 5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        
        builder.addLabel("Nome completo:").xy(1, 1);
        builder.add(txtNomeCliente).xy(3, 1);

        builder.addLabel("Data nascimento:").xy(1, 3);
        builder.add(dataNascimentoCliente).xy(3, 3);

        builder.addLabel("CPF:").xy(1, 5);
        builder.add(txtcpfCliente).xy(3, 5);

        builder.addLabel("Telefone:").xy(1, 7);
        builder.add(txttelefoneCliente).xy(3, 7);
        
        


        return builder.build();
    }

    @Override
    protected String getPanelName() {
        return "Novo";
    }
}
