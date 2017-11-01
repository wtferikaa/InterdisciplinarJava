package br.edu.opet.interdisciplinardois.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.edu.opet.interdisciplinardois.jdbc.Conexao;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.util.ExceptionUtil;

public class AlunoDao {

	private String comandoCreate = "INSERT INTO ALUNO " + "(ID, EMAIL, SENHA, NOME, TELEFONE, TURNO, TURMA, ID_CURSO)"
			                       + "VALUES (ALUNO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private String comandoRecovery = "SELECT ID, EMAIL, SENHA, NOME, TELEFONE, TURNO, TURMA, ID_CURSO " 
			                       + "FROM ALUNO "
			                       + "WHERE ID = ?";
	private String comandoUpdate = "UPDATE ALUNO "
			                       + "SET EMAIL = ?, " 
			                       + "SENHA = ?, " 
			                       + "NOME = ?, "
			                       + "TELEFONE = ?, "
			                       + "TURNO = ?, " 
			                       + "TURMA = ?, " 
			                       + "ID_CURSO = ? "
			                       + "WHERE ID = ? ";
	private String comandoDelete = "DELETE FROM ALUNO "
			                       + "WHERE ID = ?";
	private String comandoSearch = "SELECT ID, EMAIL, SENHA, NOME, TELEFONE, TURNO, TURMA, ID_CURSO " 
			                       + "FROM ALUNO";
	private String comandoSearchByCurso = "SELECT ID, EMAIL, SENHA, NOME, TELEFONE, TURNO, TURMA, ID_CURSO "
			                      + "FROM ALUNO " 
			                      + "WHERE ID_CURSO = ? ";
	 private String comandoCountByCurso   = "SELECT COUNT(ID) "
             + "FROM CURSO "
             + "WHERE ID= ?";
	private String comandoSearchByNome = "SELECT ID, EMAIL, SENHA, NOME, TELEFONE, TURNO, TURMA, ID_CURSO "
            + "FROM ALUNO "
            + "WHERE UPPER(NOME) LIKE UPPER(?)";

	public Aluno create(Aluno pAluno) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setString(i++, pAluno.getEmail());
			tComandoJdbc.setString(i++, pAluno.getSenha());
			tComandoJdbc.setString(i++, pAluno.getNome());
			tComandoJdbc.setLong(i++, pAluno.getTelefone());
			tComandoJdbc.setString(i++, pAluno.getTurno());
			tComandoJdbc.setString(i++, pAluno.getTurma());
			tComandoJdbc.setInt(i++, pAluno.getIdCurso());

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				Aluno tAluno = pAluno;

				// Recuperando o código gerado pelo banco de dados
				ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
				tRsChave.next();

				// Assinalar a chave primária gerada no objeto
				pAluno.setId(tRsChave.getInt(1));

				// Liberar os recursos
				tRsChave.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tAluno;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Aluno");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public Aluno recovery(int pId) {
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
				Aluno tAluno = recuperarAluno(tResultSet);

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tAluno;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do Aluno");
		}

		// Retorna null indicando algum erro de processamento
		return null;
	}

	public Aluno update(Aluno pAluno) {
		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

			// Preencher o comando
			int i = 1;
     
			tComandoJdbc.setString(i++, pAluno.getEmail());
			tComandoJdbc.setString(i++, pAluno.getSenha());
			tComandoJdbc.setString(i++, pAluno.getNome());
			tComandoJdbc.setLong(i++, pAluno.getTelefone());
			tComandoJdbc.setString(i++, pAluno.getTurno());
			tComandoJdbc.setString(i++, pAluno.getTurma());
			tComandoJdbc.setInt(i++, pAluno.getIdCurso());
			tComandoJdbc.setInt(i++, pAluno.getId());
			

			// Executar o comando
			int tQtd = tComandoJdbc.executeUpdate();

			// Processar o resultado
			if (tQtd == 1) {
				// Copiando o parametro
				Aluno tAluno = pAluno;

				// Liberar os recursos
				tComandoJdbc.close();

				// Retornando o objeto inserido
				return tAluno;
			}
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na atualização do Aluno");
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
			ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do Aluno");
		}

		// Retorna falso indicando algum erro de processamento
		return false;
	}

	public List<Aluno> search() {
		List<Aluno> tLista = new ArrayList<>();

		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			while (tResultSet.next()) {
				Aluno tAluno = recuperarAluno(tResultSet);

				// Adicionar o o bjeto na lista
				tLista.add(tAluno);
			}

			// Liberar os recursos
			tResultSet.close();
			tComandoJdbc.close();
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na listagem do Aluno");
		}

		// Retornando a lista de objetos
		return tLista;
	}
	
	public int countByCurso(int pIdCurso)
    {
        int tQtde = 0;

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByCurso);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdCurso);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            tResultSet.next();
            tQtde = tResultSet.getInt(1);

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();

        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na contagem dos alunos por Curso");
        }

        // Retornando a lista de objetos
        return tQtde;
    }

	public List<Aluno> searchByIdCurso(int pIdCurso) {
		List<Aluno> tLista = new ArrayList<>();

		try {
			// Obter a conexão
			Connection tConexao = Conexao.getConexao();

			// Criar o comando
			PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByCurso);

			// Preencher o comando
			int i = 1;
			tComandoJdbc.setInt(i++, pIdCurso);

			// Executar o comando
			ResultSet tResultSet = tComandoJdbc.executeQuery();

			// Processar o resultado
			while (tResultSet.next()) {
				Aluno tAluno = recuperarAluno(tResultSet);

				// Adicionar o o bjeto na lista
				tLista.add(tAluno);
			}

			// Liberar os recursos
			tResultSet.close();
			tComandoJdbc.close();
		} catch (SQLException tExcept) {
			ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do aluno");
		}

		// Retornando a lista de objetos
		return tLista;
	}
	
	public List<Aluno> searchByNome(String pNome)
    {
        if (pNome == null || pNome.isEmpty())
            return search();

        List<Aluno> tLista = new ArrayList<>();

        try
        {
            // Preparando o nome para pesquisa
            String tNome = "%" + pNome + "%";

            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByNome);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, tNome);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Aluno tAluno = recuperarAluno(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tAluno);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos alunos por nome");
        }

        // Retornando a lista de objetos
        return tLista;
    }

	private Aluno recuperarAluno(ResultSet tResultSet) throws SQLException {
		Aluno tAluno = new Aluno();

		// Recuperando os dados do resultSet
		tAluno.setId(tResultSet.getInt("ID"));
		tAluno.setEmail(tResultSet.getString("EMAIL"));
		tAluno.setSenha(tResultSet.getString("SENHA"));
		tAluno.setNome(tResultSet.getString("NOME"));
		tAluno.setTelefone(tResultSet.getLong("TELEFONE"));
		tAluno.setTurno(tResultSet.getString("TURNO"));
		tAluno.setTurma(tResultSet.getString("TURMA"));
		tAluno.setIdCurso(tResultSet.getInt("ID_CURSO"));
		return tAluno;
	}
}
