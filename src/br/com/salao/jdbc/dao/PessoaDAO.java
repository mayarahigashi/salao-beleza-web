package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.PersistenciaException;


public class PessoaDAO implements GenericoDAO<ProfissionalDTO> {

	@Override
	public void inserir(ProfissionalDTO pessoaDTO) throws PersistenciaException {
		try {
			int chaveEnd = insereEndereco(pessoaDTO.getEnderecoDTO());
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO profissional(nome, cpf, sexo, dtNascimento, email, senha, celular, idEndereco) " +
					"VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pessoaDTO.getNome());
			statement.setLong(2, pessoaDTO.getCpf());
			statement.setString(3, String.valueOf(pessoaDTO.getSexo()));
			statement.setDate(4, new Date(pessoaDTO.getDtNascimento().getTime()));
			statement.setString(5, pessoaDTO.getEmail());
			statement.setString(6, pessoaDTO.getSenha());
			statement.setString(7, pessoaDTO.getCelular());
			statement.setInt(8, chaveEnd);
			
			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	private int insereEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		int chave = 0;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO endereco(logradouro, bairro, cidade, numero, cep, idUf) " +
					"VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, enderecoDTO.getLogradouro());
			statement.setString(2, enderecoDTO.getBairro());
			statement.setString(3, enderecoDTO.getCidade());
			statement.setLong(4, enderecoDTO.getNumero());
			statement.setInt(5, enderecoDTO.getCep());
			statement.setInt(6, enderecoDTO.getUfDTO().getIdUf());
			
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				chave = result.getInt(1);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return chave;
	}
	
	@Override
	public void atualizar(ProfissionalDTO pessoaDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE usuario " +
					" SET NOME = ?, " +
					" CPF = ?," +
					" SEXO = ?," +
					" DT_NASC = ? " +
					" WHERE ID_PESSOA = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pessoaDTO.getNome());
			statement.setLong(2, pessoaDTO.getCpf());
			statement.setString(3, String.valueOf(pessoaDTO.getSexo()));
			statement.setDate(4, new Date(pessoaDTO.getDtNascimento().getTime()));
			statement.setInt(5, pessoaDTO.getIdPessoa());
			
			statement.execute();
			connection.close();
			// Atualiza agora o relacionamento da pessoa
			atualizaEndereco(pessoaDTO.getEnderecoDTO());
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	private void atualizaEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE endereco " +
					" SET LOGRADOURO = ?," +
					" BAIRRO = ?, " +
					" CIDADE = ?," +
					" NUMERO = ?," +
					" CEP = ?," +
					" COD_UF = ?" +
					" WHERE ID_ENDERECO = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, enderecoDTO.getLogradouro());
			preparedStatement.setString(2, enderecoDTO.getBairro());
			preparedStatement.setString(3, enderecoDTO.getCidade());
			preparedStatement.setLong(4, enderecoDTO.getNumero());
			preparedStatement.setInt(5, enderecoDTO.getCep());
			preparedStatement.setInt(6, enderecoDTO.getUfDTO().getIdUf());
			preparedStatement.setInt(7, enderecoDTO.getIdEndereco());
			
			preparedStatement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public void deletar(Integer idPessoa) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM usuario WHERE ID_PESSOA = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idPessoa);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletarEndereco(Integer idEndereco) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM endereco WHERE ID_ENDERECO = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEndereco);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletarTudo() throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM usuario";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProfissionalDTO> listarTodos() throws PersistenciaException {
		List<ProfissionalDTO> listaPessoas = new ArrayList<ProfissionalDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM usuario";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ProfissionalDTO pessoaDTO = new ProfissionalDTO();
				pessoaDTO.setIdPessoa(resultSet.getInt("id_pessoa"));
				pessoaDTO.setNome(resultSet.getString("nome"));
				pessoaDTO.setCpf(resultSet.getLong("cpf"));
				pessoaDTO.setDtNascimento(resultSet.getDate("dt_nasc"));
				pessoaDTO.setSexo(resultSet.getString("sexo").charAt(0));
				pessoaDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("cod_endereco")));
				
				listaPessoas.add(pessoaDTO);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaPessoas;
	}
	
	public EnderecoDTO buscaEnderecoPorId(Integer idEndereco) throws PersistenciaException {
		EnderecoDTO enderecoDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM endereco WHERE ID_ENDERECO = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEndereco);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				enderecoDTO = new EnderecoDTO();
				enderecoDTO.setIdEndereco(result.getInt(1));
				enderecoDTO.setLogradouro(result.getString(2));
				enderecoDTO.setBairro(result.getString(3));
				enderecoDTO.setCidade(result.getString(4));
				enderecoDTO.setNumero(result.getLong(5));
				enderecoDTO.setCep(result.getInt(6));
				enderecoDTO.setUfDTO(buscaUFPorID(result.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return enderecoDTO;
	}
	
	private UfDTO buscaUFPorID(Integer idUf) throws PersistenciaException {
		UfDTO ufDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM tabela_uf WHERE ID_UF = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUf);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				ufDTO = new UfDTO();
				ufDTO.setIdUf(result.getInt(1));
				ufDTO.setSiglaUf(result.getString(2));
				ufDTO.setDescricao(result.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return ufDTO;
	}

	@Override
	public ProfissionalDTO buscarPorId(Integer id) throws PersistenciaException {
		ProfissionalDTO pessoaDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM usuario WHERE ID_PESSOA = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				pessoaDTO = new ProfissionalDTO();
				pessoaDTO.setIdPessoa(resultSet.getInt("id_pessoa"));
				pessoaDTO.setNome(resultSet.getString("nome"));
				pessoaDTO.setCpf(resultSet.getLong("cpf"));
				pessoaDTO.setDtNascimento(resultSet.getDate("dt_nasc"));
				pessoaDTO.setSexo(resultSet.getString("sexo").charAt(0));
				pessoaDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("cod_endereco")));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return pessoaDTO;
	}
	
	public List<ProfissionalDTO> filtraPessoa(String nome, Long cpf, String sexo, String orderBy) throws PersistenciaException {
		List<ProfissionalDTO> lista = new ArrayList<ProfissionalDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM usuario ";
			boolean ultimo = false;
			if (nome != null && !nome.equals("")) {
				sql += " WHERE NOME LIKE ?";
				ultimo = true;
			}
			
			if (cpf != null && !cpf.equals("")) {
				if (ultimo) {
					sql += " AND ";
				} else {
					sql += " WHERE ";
					ultimo = true;
				}
				sql += " CPF LIKE ?";
			}
			
			if (sexo != null && !sexo.equals("")) {
				if (ultimo) {
					sql += " AND ";
				} else {
					sql += " WHERE ";
				}
				sql += " SEXO = ?";
			}
			sql += " ORDER BY " + orderBy;
			
			PreparedStatement statement = connection.prepareStatement(sql);
			int cont = 0;
			if (nome != null && !nome.equals("")) {
				statement.setString(++cont, "%" + nome + "%");
			}
			
			if (cpf != null && !cpf.equals("")) {
				statement.setString(++cont, "%" + cpf + "%");
			}
			
			if (sexo != null && !sexo.equals("")) {
				statement.setString(++cont, sexo);
			}
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ProfissionalDTO pessoaDTO = new ProfissionalDTO();
				pessoaDTO.setIdPessoa(resultSet.getInt("id_pessoa"));
				pessoaDTO.setNome(resultSet.getString("nome"));
				pessoaDTO.setCpf(resultSet.getLong("cpf"));
				pessoaDTO.setDtNascimento(resultSet.getDate("dt_nasc"));
				pessoaDTO.setSexo(resultSet.getString("sexo").charAt(0));
				
				lista.add(pessoaDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return lista;
	}
}
