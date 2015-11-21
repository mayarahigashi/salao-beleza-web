package br.com.salao.jdbc.dto;

import java.util.Date;

public class AgendaDTO {
	
	private int idAgenda;
	private Date data;
	private String hora; // ver como declara HORA
	private String observacoes;
	private ProfissionalDTO profissionalDTO;
	private ClienteDTO clienteDTO;
	private ServicoDTO servicoDTO;
	
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public ProfissionalDTO getProfissionalDTO() {
		return profissionalDTO;
	}
	public void setProfissionalDTO(ProfissionalDTO profissionalDTO) {
		this.profissionalDTO = profissionalDTO;
	}
	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}
	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	public ServicoDTO getServicoDTO() {
		return servicoDTO;
	}
	public void setServicoDTO(ServicoDTO servicoDTO) {
		this.servicoDTO = servicoDTO;
	}

	
	
}
