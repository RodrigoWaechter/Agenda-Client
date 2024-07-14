package com.projeto.agenda.client.form;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.server.domain.Atendimento;
import com.toedter.calendar.JDateChooser;

public class EditarFrm extends BaseForm<Atendimento> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> listaServicos;
    JComboBox<String> cmbPesquisa;
    JTextField txtPesquisa;
    JButton btnPesquisa;
    JTable tabelaResultado;

    JDateChooser dtNovo;
    JTextField txtNome;
    List<String> metodoPagamento;
    JComboBox<String> cmbMetodo;

    public EditarFrm() {
        
        startup();
    }

    @Override
    protected void createComponents() {
        
    	txtNome = new JTextField("Nome");
        tabelaResultado = new JTable();
        tabelaResultado.setFont(new Font("Arial", Font.PLAIN,15));
        tabelaResultado.setBackground(Color.LIGHT_GRAY);
        tabelaResultado.setRowHeight(35);
        tabelaResultado.setDefaultEditor(Object.class, null);
        tabelaResultado.setColumnSelectionAllowed(false);
        tabelaResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaResultado.setFocusable(false);

        dtNovo = new JDateChooser();
        dtNovo.setDateFormatString("dd/MM/yyyy");

        metodoPagamento = new ArrayList<>();
        metodoPagamento.add("Dinheiro");
        metodoPagamento.add("PIX");
        metodoPagamento.add("Cartão de Débito");
        metodoPagamento.add("Cartão de Crédito");
        cmbMetodo = new JComboBox<>(metodoPagamento.toArray(new String[0]));

    }

    @Override
    protected JPanel createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref , 5px, pref, 5px, pref, pref, pref, pref, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        FormLayout layout2 = new FormLayout("pref, 100dlu:grow", " pref , pref, pref, pref, pref, pref, pref");
        FormBuilder rodaPe = FormBuilder.create().debug(true).layout(layout2);


        builder.add(tabelaResultado).xy(1,1);

        
        rodaPe.addLabel("Nova data:").xy(1,1);
        rodaPe.add(dtNovo).xy(2,1);

        rodaPe.addLabel("Editar nome:").xy(1,3);
        rodaPe.add(txtNome).xy(2,3);

        rodaPe.addLabel("Editar método:").xy(1,5);
        rodaPe.add(cmbMetodo).xy(2,5);


        return builder.build();
    }

    @Override
    protected String getPanelName() {
        return "Editar";
    }
}