package br.edu.opet.interdisciplinardois.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.opet.interdisciplinardois.jdbc.Conexao;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.util.ExceptionUtil;




public class DepartamentosOpetDao {

	private String comandoCreate = "INSERT INTO DEPARTAMENTOSOPET " 
	                             + "(ID, EMAIL, SENHA, NOME)"
			                     + "VALUES (DEPARTAMENTOSOPET_SEQ.NEXTVAL, ?, ?, ?)";
	private String comandoRecovery = "SELECT ID, EMAIL, SENHA, NOME "
			                     + "FROM DEPARTAMENTOSOPET "
			                     + "WHERE ID = ?";
	private String comandoRecoveryByNome = "SELECT ID, EMAIL, SENHA, NOME "
			                     + "FROM DEPARTAMENTOSOPET "
			                     + "WHERE NOME = ?";
	private String comandoUpdate = "UPDATE DEPARTAMENTOSOPET " 
			                     + "SET EMAIL = ?, "
			                     + "SENHA = ?, "
			                     + "NOME = ? "
			                     + "WHERE ID = ?";
	private String comandoDelete = "DELETE FROM DEPARTAMENTOSOPET " 
			                     + "WHERE ID = ?";
	private String comandoSearch = "SELECT ID, EMAIL, SENHA, NOME "
			                     + "FROM DEPARTAMENTOSOPET";

	public DepartamentosOpet create(DepartamentosOpet pDepartamentosOpet) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pDepartamentosOpet.getEmail());
			tComandoJdbc.setString(i++, pDepartamentosOpet.getSenha());
			tComandoJdbc.setString(i++, pDepartamentosOpet.getNome());
			

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				DepartamentosOpet tDepartamentosOpet = pDepartamentosOpet;

				// Recuperando o código gerado pelo banco de dados
				ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
				tRsChave.next();

				// Assinalar a chave primária gerada no objeto
				pDepartamentosOpet.setId(tRsChave.getInt(1));

				// Liberar os recursos
				tRsChave.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tDepartamentosOpet;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Departamentos Opet");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public DepartamentosOpet recovery(int pId) {
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
				DepartamentosOpet tDepartamentosOpet = recuperarDepartamentosOpet(tResultSet);

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tDepartamentosOpet;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do Departamentos Opet");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public DepartamentosOpet recoveryByNome(String pNome) {
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
				DepartamentosOpet tDepartamentosOpet = recuperarDepartamentosOpet(tResultSet);

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tDepartamentosOpet;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do DepartamentosOpet por Nome");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public DepartamentosOpet update(DepartamentosOpet pDepartamentosOpet) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pDepartamentosOpet.getEmail());
			tComandoJdbc.setString(i++, pDepartamentosOpet.getSenha());
			tComandoJdbc.setString(i++, pDepartamentosOpet.getNome());
			tComandoJdbc.setInt(i++, pDepartamentosOpet.getId());

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				DepartamentosOpet tDepartamentosOpet = pDepartamentosOpet;

				// Liberar os recursos
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tDepartamentosOpet;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na atualização do Departamentos Opet");
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
			ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do Departamentos Opet");
		}

		// Retorna falso indicando algum erro de processamento
		return false;
	}

	public List<DepartamentosOpet> search() {
		List<DepartamentosOpet> tLista = new ArrayList<>();

		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			while (tResultSet.next()) {
				DepartamentosOpet tDepartamentosOpet = recuperarDepartamentosOpet(tResultSet);

				// Adicionar o o bjeto na lista
				tLista.add(tDepartamentosOpet);
			}

			// Liberar os recursos
			tResultSet.close();
			tComandoJdbc.close();
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na listagem do DepartamentosOpet");
		}

		// Retornando a lista de objetos
		return tLista;
	}

	private DepartamentosOpet recuperarDepartamentosOpet(ResultSet tResultSet) throws SQLException {
		DepartamentosOpet tDepartamentosOpet = new DepartamentosOpet();

		// Recuperando os dados do resultSet
		tDepartamentosOpet.setId(tResultSet.getInt("ID"));
		tDepartamentosOpet.setEmail(tResultSet.getString("EMAIL"));
		tDepartamentosOpet.setSenha(tResultSet.getString("SENHA"));
		tDepartamentosOpet.setNome(tResultSet.getString("NOME"));
		return tDepartamentosOpet;
	}

}
