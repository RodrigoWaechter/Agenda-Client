package com.projeto.agenda.client.form;

import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.projeto.agenda.client.custom.BaseForm;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NovoFrm extends BaseForm {
    JDateChooser dtNovo;
    JTextField txtNome;
    List<String> metodoPagamento;
    JComboBox<String> cmbMetodo;


    public NovoFrm() {
        setTitle("Novo atendimento");
        setSize(463, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        startup();
    }

    @Override
    protected void createComponents() {
        dtNovo = new JDateChooser();
        dtNovo.setDateFormatString("dd/MM/yyyy");
        txtNome = createConfiguredTextField(20);
        metodoPagamento = new ArrayList<>();
        metodoPagamento.add("Dinheiro");
        metodoPagamento.add("PIX");
        metodoPagamento.add("Cartão de Débito");
        metodoPagamento.add("Cartão de Crédito");
        cmbMetodo = new JComboBox<>(metodoPagamento.toArray(new String[0]));

    }

    @Override
    protected JComponent createMainPanel() {
        FormLayout layout = new FormLayout("pref, 100dlu:grow", " pref ,5px, pref, 5px, pref, 5px, pref");
        FormBuilder builder = FormBuilder.create().debug(true).layout(layout);

        builder.add(createConfiguredLabel("Data consulta:")).xy(1,1);
        builder.add(dtNovo).xy(2,1);

        builder.add(createConfiguredLabel("Nome do cliente:")).xy(1,3);
        builder.add(txtNome).xy(2,3);

        builder.add(createConfiguredLabel("Método de pagamento:")).xy(1,5);
        builder.add(cmbMetodo).xy(2,5);


        return builder.build();
    }
}
