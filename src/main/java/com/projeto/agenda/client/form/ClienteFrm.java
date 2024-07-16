package com.projeto.agenda.client.form;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgoodies.forms.builder.FormBuilder;
import com.projeto.agenda.client.tableModel.ClienteTableModel;
import com.projeto.agenda.components.BaseForm;
import com.projeto.agenda.components.ComponentFactoryAgenda;
import com.projeto.agenda.server.domain.Cliente;
import com.toedter.calendar.JDateChooser;

public class ClienteFrm extends BaseForm<Cliente> {
    private static final long serialVersionUID = 1L;

    private JTextField txtNomeCliente;
    private JDateChooser dataNascimentoCliente;
    private JTextField txtcpfCliente;
    private JTextField txttelefoneCliente;
    private JTable table;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private ClienteTableModel tableModel;
    private JButton btnPesquisar;
    public ClienteFrm() {
    }

    @Override
    protected void createComponents() {
        txtNomeCliente = ComponentFactoryAgenda.textField(getModel("cliente"));
        dataNascimentoCliente = ComponentFactoryAgenda.dateField(getModel("datanascimento"));
        txtcpfCliente = ComponentFactoryAgenda.textField(getModel("cpf"));
        txttelefoneCliente = ComponentFactoryAgenda.textField(getModel("telefone"));
        btnAdicionar = ComponentFactoryAgenda.button("Adicionar", actionAdicionar());
        btnEditar = ComponentFactoryAgenda.button("Editar", actionEditar());
        btnExcluir = ComponentFactoryAgenda.button("Excluir", actionExcluir());
        tableModel = new ClienteTableModel();
       // initializeTableWithDummyClientes();
        table = ComponentFactoryAgenda.table(tableModel);
        btnPesquisar = ComponentFactoryAgenda.button("Pesquisar", actionPesquisar());

        // Adicionar listener para preencher campos ao clicar em uma linha da tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                Cliente clienteSelecionado = tableModel.getCliente(selectedRow);
                fillFormFields(clienteSelecionado);
            }
        });
    }

    @Override
    protected JPanel createMainPanel() {
        return FormBuilder.create()
                .columns("pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 9px, 100dlu:grow, 9px, pref, 10px, 100dlu:grow")
                .rows(" pref ,5px, pref, 5px, pref")
                .addLabel("Nome:").xy(1, 1)
                .add(txtNomeCliente).xy(3, 1)
                .addLabel("CPF:").xy(5, 1)
                .add(txtcpfCliente).xy(7, 1)
                .addLabel("Data nascimento:").xy(9, 1)
                .add(dataNascimentoCliente).xy(11, 1)
                .addLabel("Telefone:").xy(13, 1)
                .add(txttelefoneCliente).xy(15, 1)
                .add(new JScrollPane(table)).xyw(1, 3, 15)
                .add(createPanelButton()).xyw(1, 5, 15)
                .build();
    }

    private JPanel createPanelButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnPesquisar);

        return panel;
    }

    private ActionListener actionAdicionar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setNomeCliente(txtNomeCliente.getText());
                cliente.setNascimentoCliente(dataNascimentoCliente.getDate());
                cliente.setCpfCliente(txtcpfCliente.getText());
                cliente.setTelefoneCliente(txttelefoneCliente.getText());
                tableModel.addCliente(cliente);
                
                try {
                    URL url = new URL("http://localhost:8081/Cliente/save"); 
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonInputString = mapper.writeValueAsString(cliente);

                    try (OutputStream os = con.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        System.out.println("Cliente saved successfully.");
                    } else {
                        System.out.println("Failed to save Cliente. Response Code: " + responseCode);
                    }
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                clearFormFields();
            }
        };
    }
    private ActionListener actionPesquisar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> clientes = findClientes();
               
                updateTable(clientes);
            }
        };
    }

    private List<Cliente> findClientes() {
    	 String baseUrl = "http://localhost:8081"; 
    	    String findAllUrl = baseUrl + "/Cliente/findAll";

    	    RestTemplate restTemplate = new RestTemplate();
    	    ResponseEntity<Cliente[]> response = restTemplate.getForEntity(findAllUrl, Cliente[].class);

    	    if (response.getStatusCode().is2xxSuccessful()) {
    	        return Arrays.asList(response.getBody());
    	    } else {
    	        System.out.println("Failed to fetch clientes. Response Code: " + response.getStatusCodeValue());
    	        return Collections.emptyList();
    	    }
    }

    private void updateTable(List<Cliente> clientes) {
        // Limpar o tableModel atual
        tableModel.getClientes().clear();

        // Adicionar os clientes obtidos ao tableModel
        for (Cliente cliente : clientes) {
            tableModel.addCliente(cliente);
        }
    }
    private ActionListener actionEditar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                    Cliente clienteSelecionado = tableModel.getCliente(selectedRow);
                    updateCliente(clienteSelecionado);
                }
            }
        };
    }

    private ActionListener actionExcluir() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // Verifica se alguma linha está selecionada
                    tableModel.deleteCliente(selectedRow);
                    clearFormFields();
                }
            }
        };
    }

    private void fillFormFields(Cliente cliente) {
        txtNomeCliente.setText(cliente.getNomeCliente());
        dataNascimentoCliente.setDate(cliente.getNascimentoCliente());
        txtcpfCliente.setText(cliente.getCpfCliente());
        txttelefoneCliente.setText(cliente.getTelefoneCliente());
    }

    private void updateCliente(Cliente cliente) {
        // Atualiza o cliente com os dados do formulário
        cliente.setNomeCliente(txtNomeCliente.getText());
        cliente.setNascimentoCliente(dataNascimentoCliente.getDate());
        cliente.setCpfCliente(txtcpfCliente.getText());
        cliente.setTelefoneCliente(txttelefoneCliente.getText());
        
        // Chama o método de atualização no table model
        tableModel.updateCliente(table.getSelectedRow(), cliente);
        
        // Limpa os campos do formulário
        clearFormFields();
    }
    private void initializeTableWithDummyClientes() {
        // Adicionar alguns clientes fictícios
        Date dataNascimento1 = new Date(); 
        Date dataNascimento2 = new Date();
        Cliente cliente1 = new Cliente(null, "João da Silva", dataNascimento1, "123.456.789-00", "(11) 98765-4321");
        Cliente cliente2 = new Cliente(null, "Maria Oliveira", dataNascimento2, "987.654.321-00", "(21) 12345-6789");

        tableModel.addCliente(cliente1);
        tableModel.addCliente(cliente2);
    }

    private void clearFormFields() {
        txtNomeCliente.setText("");
        dataNascimentoCliente.setDate(null);
        txtcpfCliente.setText("");
        txttelefoneCliente.setText("");
    }

    @Override
    protected String getPanelName() {
        return "Cliente";
    }
}
