package br.edu.opet.interdisciplinardois.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.opet.interdisciplinardois.jdbc.Conexao;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.util.ExceptionUtil;


public class CursoDao {

	private String comandoCreate = "INSERT INTO CURSO " 
	                             + "(ID, EMAIL, SENHA, NOME, NOMECOORDENADOR)"
			                     + "VALUES (CURSO_SEQ.NEXTVAL, ?, ?, ?, ?)";
	private String comandoRecovery = "SELECT ID, EMAIL, SENHA, NOME, NOMECOORDENADOR "
			                     + "FROM CURSO "
			                     + "WHERE ID = ?";
	private String comandoRecoveryByNome = "SELECT ID, EMAIL, SENHA, NOME, NOMECOORDENADOR " 
			                     + "FROM CURSO "
			                     + "WHERE NOME = ?";
	private String comandoUpdate = "UPDATE CURSO " 
			                     + "SET EMAIL = ?, " 
			                     + "SENHA = ?, " 
			                     + "NOME = ?, " 
			                     + "NOMECOORDENADOR = ? "  
			                     + "WHERE ID = ?";
	private String comandoDelete = "DELETE FROM CURSO " 
			                     + "WHERE ID = ?";
	private String comandoSearch = "SELECT ID, EMAIL, SENHA, NOME, NOMECOORDENADOR " 
			                     + "FROM CURSO";

	public Curso create(Curso pCurso) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

			
			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pCurso.getEmail());
            tComandoJdbc.setString(i++, pCurso.getSenha());
			tComandoJdbc.setString(i++, pCurso.getNome());
			tComandoJdbc.setString(i++, pCurso.getNomeCoordenador());


			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				Curso tCurso = pCurso;

				// Recuperando o código gerado pelo banco de dados
				ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
				tRsChave.next();

				// Assinalar a chave primária gerada no objeto
				pCurso.setId(tRsChave.getInt(1));

				// Liberar os recursos
				tRsChave.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tCurso;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Curso");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public Curso recovery(int pId) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecovery);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setInt(i++, pId);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			if (tResultSet.next()) {
				// Criando o objeto
				Curso tCurso = recuperarCurso(tResultSet);

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tCurso;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do Curso");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public Curso recoveryByNome(String pNome) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecoveryByNome);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pNome);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			if (tResultSet.next()) {
				// Criando o objeto
				Curso tCurso = recuperarCurso(tResultSet);

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tCurso;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do do Curso por nome");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public Curso update(Curso pCurso) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pCurso.getEmail());
            tComandoJdbc.setString(i++, pCurso.getSenha());
			tComandoJdbc.setString(i++, pCurso.getNome());
			tComandoJdbc.setString(i++, pCurso.getNomeCoordenador());
			tComandoJdbc.setInt(i++, pCurso.getId());

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				Curso tCurso = pCurso;

				// Liberar os recursos
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tCurso;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na atualização do Curso");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public boolean delete(int pId) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoDelete);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setInt(i++, pId);

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Liberar os recursos
				tComandoJdbc.close();

				// Retornando o indicativo de sucesso
				return true;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do Curso");
		}

		// Retorna falso indicando algum erro de processamento
		return false;
	}

	public List<Curso> search() {
		List<Curso> tLista = new ArrayList<>();

		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			while (tResultSet.next()) {
				Curso tCurso = recuperarCurso(tResultSet);

				// Adicionar o o bjeto na lista
				tLista.add(tCurso);
			}

			// Liberar os recursos
			tResultSet.close();
			tComandoJdbc.close();
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na listagem do Curso");
		}

		// Retornando a lista de objetos
		return tLista;
	}
	

	private Curso recuperarCurso(ResultSet tResultSet) throws SQLException {
		Curso tCurso = new Curso();

		// Recuperando os dados do resultSet
		tCurso.setId(tResultSet.getInt("ID"));
		tCurso.setEmail(tResultSet.getString("EMAIL"));
		tCurso.setSenha(tResultSet.getString("SENHA"));
		tCurso.setNome(tResultSet.getString("NOME"));
		tCurso.setNomeCoordenador(tResultSet.getString("NOMECOORDENADOR"));
		return tCurso;
	}
}
