package com.projeto.agenda.client.helper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.agenda.client.form.ClienteFrm;
import com.projeto.agenda.server.domain.Cliente;

public class ClienteHelper {
	private static final String BASE_URL = "http://localhost:8081/Cliente";

	public void actionAdicionar(ClienteFrm form) {
		Cliente cliente = new Cliente();
		cliente.setNomeCliente(form.getTxtNomeCliente().getText());
		cliente.setNascimentoCliente(form.getDataNascimentoCliente().getDate());
		cliente.setCpfCliente(form.getTxtcpfCliente().getText());
		cliente.setTelefoneCliente(form.getTxttelefoneCliente().getText());
		save(cliente);
		form.getTableModel().addCliente(cliente);
		form.clearFormFields();
	}

	public void actionEditar(ClienteFrm form) {
	        int selectedRow = form.getTable().getSelectedRow();
	        if (selectedRow != -1) {
	            Cliente clienteSelecionado = form.getTableModel().getCliente(selectedRow);
	            clienteSelecionado.setNomeCliente(form.getTxtNomeCliente().getText());
	            clienteSelecionado.setNascimentoCliente(form.getDataNascimentoCliente().getDate());
	            clienteSelecionado.setCpfCliente(form.getTxtcpfCliente().getText());
	            clienteSelecionado.setTelefoneCliente(form.getTxttelefoneCliente().getText());

	            // Update the client
	            update(clienteSelecionado);
	            form.getTableModel().updateCliente(selectedRow, clienteSelecionado);
	            form.clearFormFields();

	            // Restore buttons to initial state
	            form.enableAllButtons(true);
	            form.getBtnEditar().setText("Editar");
	            form.setEditMode(false);
	        
	    }
	}
	public void actionExcluir(ClienteFrm form) {
		int selectedRow = form.getTable().getSelectedRow();
		if (selectedRow != -1) {
			Cliente clienteSelecionado = form.getTableModel().getCliente(selectedRow);
			form.getTableModel().deleteCliente(selectedRow);
			delete(clienteSelecionado.getIdCliente());
			form.clearFormFields();
		}
	}

	public void actionPesquisar(ClienteFrm form) {
		List<Cliente> clientes = findAllClientes();
		form.updateTable(clientes);
	}

	public void save(Cliente cliente) {
		try {
			String saveUrl = BASE_URL + "/save";
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(cliente);

			ResponseEntity<String> response = restTemplate.postForEntity(saveUrl, jsonInputString, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Cliente saved successfully.");
			} else {
				System.out.println("Failed to save Cliente. Response Code: " + response.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Cliente cliente) {
	    try {
	        String updateUrl = BASE_URL + "/updateById/" + cliente.getIdCliente();
	        RestTemplate restTemplate = new RestTemplate();
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInputString = mapper.writeValueAsString(cliente);

	        // Set the headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // Create HttpEntity
	        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

	        // Make the request
	        ResponseEntity<String> response = restTemplate.exchange(updateUrl, HttpMethod.PUT, requestEntity, String.class);
	        if (response.getStatusCode().is2xxSuccessful()) {
	            System.out.println("Cliente updated successfully.");
	        } else {
	            System.out.println("Failed to update Cliente. Response Code: " + response.getStatusCode());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
			

	public void delete(Integer id) {
		try {
			String deleteUrl = BASE_URL + "/deleteById/" + id;
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> response = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				System.out.println("Cliente deleted successfully.");
			} else {
				System.out.println("Failed to delete Cliente. Response Code: " + response.getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> findAllClientes() {
		try {
			String findAllUrl = BASE_URL + "/findAll";
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Cliente[]> response = restTemplate.getForEntity(findAllUrl, Cliente[].class);

			if (response.getStatusCode().is2xxSuccessful()) {
				return Arrays.asList(response.getBody());
			} else {
				System.out.println("Failed to fetch clientes. Response Code: " + response.getStatusCode());
				return Collections.emptyList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
