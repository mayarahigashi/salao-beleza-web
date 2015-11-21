package br.com.salao.jdbc.bo;

import br.com.salao.jdbc.dao.LoginDAO;
import br.com.salao.jdbc.dto.LoginDTO;
import br.com.salao.jdbc.exception.NegocioException;

public class LoginBO {

	public boolean logar(LoginDTO loginDTO) throws NegocioException {
		boolean resultado = false;
		try {
			if (loginDTO.getNome() == null || "".equals(loginDTO.getNome())) {
				throw new NegocioException("Login obrigat�rio!");
			} else if (loginDTO.getSenha() == null || "".equals(loginDTO.getSenha())) {
				throw new NegocioException("Senha obrigat�ria!");
			} else {
				LoginDAO loginDAO = new LoginDAO();
				resultado = loginDAO.logar(loginDTO);
			}
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return resultado;
	}
	
}
