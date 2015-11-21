package br.com.salao.jdbc.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.salao.jdbc.dao.PessoaDAO;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.exception.NegocioException;
import br.com.salao.jdbc.exception.ValidacaoException;

public class PessoaBO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void cadastrar(ProfissionalDTO pessoaDTO) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.inserir(pessoaDTO);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public List<ProfissionalDTO> listagem() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			return pessoaDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	public String[][] listagem(List<Integer> idsPessoas) throws NegocioException {
		int numCols = 10;
		String[][] listaRetorno = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			List<ProfissionalDTO> lista = pessoaDAO.listarTodos();
			listaRetorno = new String[lista.size()][numCols];
			
			for (int i = 0; i < lista.size(); i++) {
				ProfissionalDTO pessoa = lista.get(i);
				EnderecoDTO enderecoDTO = pessoa.getEnderecoDTO();
				
				listaRetorno[i][0] = pessoa.getIdPessoa().toString();
				idsPessoas.add(pessoa.getIdPessoa());
				listaRetorno[i][1] = pessoa.getNome();
				listaRetorno[i][2] = pessoa.getCpf().toString();
				listaRetorno[i][3] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
				listaRetorno[i][4] = dateFormat.format(pessoa.getDtNascimento());
				listaRetorno[i][5] = enderecoDTO.getLogradouro();
				listaRetorno[i][6] = enderecoDTO.getCep().toString();
				listaRetorno[i][7] = enderecoDTO.getUfDTO().getDescricao();
				listaRetorno[i][8] = "Editar";
				listaRetorno[i][9] = "Deletar";
			}
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
		return listaRetorno;
	}
	
	public boolean validaNome(String nome) throws ValidacaoException {
		boolean ehValido = true;
		if (nome == null || nome.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo nome é obrigatório!");
		} else if (nome.length() > 30) {
			ehValido = false;
			throw new ValidacaoException("Campo nome comporta no máximo 30 chars!");
		}
		return ehValido;
	}
	
	public boolean validaCpf(String cpf) throws ValidacaoException {
		boolean ehValido = true;
		if (cpf == null || cpf.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo CPF é obrigatório!");
		} else if (cpf.length() != 11) {
			ehValido = false;
			throw new ValidacaoException("Campo CPF deve ter 11 dígitos!");
		} else {
			char[] digitos = cpf.toCharArray();
			for (char digito : digitos) {
				if (!Character.isDigit(digito)) {
					ehValido = false;
					throw new ValidacaoException("Campo CPF é somente numérico!");
				}
			}
		}
		return ehValido;
	}
	
	public boolean validaEndereco(EnderecoDTO enderecoDTO) throws ValidacaoException {
		boolean ehValido = true;
		if (enderecoDTO.getLogradouro() == null || enderecoDTO.getLogradouro().equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo Logradouro é obrigatório!");
		} else if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo Bairro é obrigatório!");
		} else if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero().equals(0)) {
			ehValido = false;
			throw new ValidacaoException("Campo Número é obrigatório!");
		} else if (enderecoDTO.getCep() == null || enderecoDTO.getCep().equals(0)) {
			ehValido = false;
			throw new ValidacaoException("Campo CEP é obrigatório!");
		}
		return ehValido;
	}
	
	public boolean validaDtNasc(String dtNasc) throws ValidacaoException {
		boolean ehValido = true;
		if (dtNasc == null || dtNasc.equals("")) {
			ehValido = false;
			throw new ValidacaoException("Campo Dt. Nasc. é obrigatório!");
		} else {
			ehValido = false;
			try {
				dateFormat.parse(dtNasc);
			} catch (ParseException e) {
				throw new ValidacaoException("Formato inválido de data!");
			}
		}
		return ehValido;
	}
	
	public String[][] listaConsulta(String nome, Long cpf, char sexo, String orderBy) throws NegocioException {
		int numCols = 6;
		String[][] listaRetorno = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			
			List<ProfissionalDTO> lista = pessoaDAO.filtraPessoa(nome, cpf, String.valueOf(sexo), orderBy);
			listaRetorno = new String[lista.size()][numCols];
			
			for (int i = 0; i < lista.size(); i++) {
				ProfissionalDTO pessoa = lista.get(i);
				listaRetorno[i][0] = pessoa.getIdPessoa().toString();
				listaRetorno[i][1] = pessoa.getNome();
				listaRetorno[i][2] = pessoa.getCpf().toString();
				listaRetorno[i][4] = pessoa.getSexo() == 'M' ? "Masculino" : "Feminino";
				listaRetorno[i][5] = dateFormat.format(pessoa.getDtNascimento());
			}
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
		return listaRetorno;
	}
	
	public void removePessoa(Integer idPessoa, Integer idEndereco) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletar(idPessoa);
			pessoaDAO.deletarEndereco(idEndereco);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}

	public void removeTudo() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletarTudo();
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public ProfissionalDTO buscaPorId(Integer idPessoa) throws NegocioException {
		ProfissionalDTO pessoaDTO = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDTO = pessoaDAO.buscarPorId(idPessoa);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return pessoaDTO;
	}
	
	public void atualizar(ProfissionalDTO pessoaDTO) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.atualizar(pessoaDTO);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
