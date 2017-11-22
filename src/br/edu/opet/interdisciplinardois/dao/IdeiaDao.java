package br.edu.opet.interdisciplinardois.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import br.edu.opet.interdisciplinardois.jdbc.Conexao;

import br.edu.opet.interdisciplinardois.model.Ideia;
import br.edu.opet.interdisciplinardois.util.ExceptionUtil;


public class IdeiaDao {


		private String comandoCreate = "INSERT INTO IDEIA" 
		                             + "(ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO,"
		                             + " DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO,  ID_DEPARTAMENTOSOPET)"
				                     + "VALUES (IDEIA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private String comandoRecovery = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO,"
				                     + " DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
				                     + "FROM IDEIA "
				                     + "WHERE ID = ?";
		private String comandoRecoveryByNome = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO, "
				+ "DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
	            + "FROM IDEIA "
	            + "WHERE NOME = ?";
		private String comandoUpdate = "UPDATE IDEIA " 
				                     + "SET NOME = ?, " 
				                     + "DESCRICAODOPROBLEMA = ?, " 
				                     + "RECOMENDACAO = ?, "  
				                     + "DATACADASTRO = ?, "
				                     + "DATAANALISE = ?, "
				                     + "APROVADO = ?, "
				                     + "RESPOSTA = ?, "
				                     + "ID_ALUNO = ?, "
				                     + "ID_DEPARTAMENTOSOPET = ? "
				                     + "WHERE ID = ? ";
		private String comandoDelete = "DELETE FROM IDEIA " 
				                     + "WHERE ID = ?";
		private String comandoSearch = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO, "
				                     + "DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
				                     + "FROM IDEIA";
	   private String comandoSearchByAluno   = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO, "
	   		     + "DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
                 + "FROM IDEIA "
                 + "WHERE ID_ALUNO = ?";
         private String comandoCountByAluno   = "SELECT COUNT(ID_ALUNO) "
                 + "FROM IDEIA "
                 + "WHERE ID_ALUNO = ?";
         private String comandoSearchByDepartamento   = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO, "
	   		     + "DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
                 + "FROM IDEIA "
                 + "WHERE ID_DEPARTAMENTOSOPET = ?";
         private String comandoCountByDepartamento   = "SELECT COUNT(ID_DEPARTAMENTOSOPET) "
                 + "FROM IDEIA "
                 + "WHERE ID_DEPARTAMENTOSOPET = ?";
         private String comandoSearchByNome = "SELECT ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO, "
         		 + "DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO, ID_DEPARTAMENTOSOPET "
                 + "FROM IDEIA "
                 + "WHERE UPPER(NOME) LIKE UPPER(?)";
		

		public Ideia create(Ideia pIdeia) {
			try {
				// Obter a conexão
				Connection tConexao = Conexao.getConexao();

				// Criar o comando
				PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

				
				// Preencher o comando
				int i = 1;
				
				//private String comandoCreate = "INSERT INTO IDEIA" 
               // + "(ID, NOME, DESCRICAODOPROBLEMA, RECOMENDACAO, DATACADASTRO,"
                //+ " DATAANALISE, APROVADO, RESPOSTA, ID_ALUNO,  ID_DEPARTAMENTOSOPET)"
                //+ "VALUES (IDEIA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				
				tComandoJdbc.setString(i++, pIdeia.getNome());
				tComandoJdbc.setString(i++, pIdeia.getDescricaoProblema());
				tComandoJdbc.setString(i++, pIdeia.getRecomendacao());
				tComandoJdbc.setDate(i++, Date.valueOf(pIdeia.getDataCadastro()));
				
				if (pIdeia.getDataAnalise() != null)
					tComandoJdbc.setDate(i++, Date.valueOf(pIdeia.getDataAnalise()));
				else 
					tComandoJdbc.setNull(i++, Types.DATE);
				
				if (pIdeia.getAprovado() != null)
					tComandoJdbc.setString(i++, pIdeia.getAprovado()?"S":"N");
				else 
					tComandoJdbc.setNull(i++, Types.CHAR);
				
				if (pIdeia.getResposta() != null)
					tComandoJdbc.setString(i++, pIdeia.getResposta());
				else
					tComandoJdbc.setNull(i++, Types.VARCHAR);
				
				tComandoJdbc.setInt(i++, pIdeia.getIdAluno());
				tComandoJdbc.setInt(i++, pIdeia.getIdDepartamentosOpet());

				// Executar o comando
				int tQtd = tComandoJdbc.executeUpdate();

				// Processar o resultado
				if (tQtd == 1) {
					// Copiando o parametro
					Ideia tIdeia = pIdeia;

					// Recuperando o código gerado pelo banco de dados
					ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
					tRsChave.next();

					// Assinalar a chave primária gerada no objeto
					pIdeia.setId(tRsChave.getInt(1));

					// Liberar os recursos
					tRsChave.close();
					tComandoJdbc.close();

					// Retornando o objeto inserido
					return tIdeia;
				}
			} catch (SQLException tExcept) {
				ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Ideia");
			}

			// Retorna null indicando algum erro de processamento
			return null;
		}

		public Ideia recovery(int pId) {
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
					Ideia tIdeia = recuperarIdeia(tResultSet);

					// Liberar os recursos
					tResultSet.close();
					tComandoJdbc.close();

					// Retornando o objeto inserido
					return tIdeia;
				}
			} catch (SQLException tExcept) {
				ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação do Ideia");
			}

			// Retorna null indicando algum erro de processamento
			return null;
		}
		
		public Ideia recoveryByNome(String pNome) {
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
					Ideia tIdeia = recuperarIdeia(tResultSet);

					// Liberar os recursos
					tResultSet.close();
					tComandoJdbc.close();

					// Retornando o objeto inserido
					return tIdeia;
				}
			} catch (SQLException tExcept) {
				ExceptionUtil.mostrarErro(tExcept, "Problemas na recuperação de ideia por Nome");
			}

			// Retorna null indicando algum erro de processamento
			return null;
		}


		public Ideia update(Ideia pIdeia) {
			try {
				// Obter a conexão
				Connection tConexao = Conexao.getConexao();

				// Criar o comando
				PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

				// Preencher o comando
				int i = 1;
				
				/*private String comandoUpdate = "UPDATE IDEIA " 
	                     + "SET NOME = ?, " 
	                     + "DESCRICAODOPROBLEMA = ?, " 
	                     + "RECOMENDACAO = ?, "  
	                     + "DATACADASTRO = ?, "
	                     + "DATAANALISE = ?, "
	                     + "APROVADO = ?, "
	                     + "RESPOSTA = ?, "
	                     + "ID_ALUNO = ?, "
	                     + "ID_DEPARTAMENTOSOPET = ? "
	                     + "WHERE ID = ? ";*/
				tComandoJdbc.setString(i++, pIdeia.getNome());
				tComandoJdbc.setString(i++, pIdeia.getDescricaoProblema());
				tComandoJdbc.setString(i++, pIdeia.getRecomendacao());
				tComandoJdbc.setDate(i++, Date.valueOf(pIdeia.getDataCadastro()));
				
				if (pIdeia.getDataAnalise() != null)
					tComandoJdbc.setDate(i++, Date.valueOf(pIdeia.getDataAnalise()));
				else 
					tComandoJdbc.setNull(i++, Types.DATE);
				
				if (pIdeia.getAprovado() != null)
					tComandoJdbc.setString(i++, pIdeia.getAprovado()?"S":"N");
				else 
					tComandoJdbc.setNull(i++, Types.CHAR);
				
				if (pIdeia.getResposta() != null)
					tComandoJdbc.setString(i++, pIdeia.getResposta());
				else
					tComandoJdbc.setNull(i++, Types.VARCHAR);
				
				tComandoJdbc.setInt(i++, pIdeia.getIdAluno());
				tComandoJdbc.setInt(i++, pIdeia.getIdDepartamentosOpet());
				tComandoJdbc.setInt(i++, pIdeia.getId());


				// Executar o comando
				int tQtd = tComandoJdbc.executeUpdate();

				// Processar o resultado
				if (tQtd == 1) {
					// Copiando o parametro
					Ideia tIdeia = pIdeia;

					// Liberar os recursos
					tComandoJdbc.close();

					// Retornando o objeto inserido
					return tIdeia;
				}
			} catch (SQLException tExcept) {
				ExceptionUtil.mostrarErro(tExcept, "Problemas na atualização do Ideia");
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
				ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do Ideia");
			}

			// Retorna falso indicando algum erro de processamento
			return false;
		}

		public List<Ideia> search() {
			List<Ideia> tLista = new ArrayList<>();

			try {
				// Obter a conexão
				Connection tConexao = Conexao.getConexao();

				// Criar o comando
				PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

				// Executar o comando
				ResultSet tResultSet = tComandoJdbc.executeQuery();

				// Processar o resultado
				while (tResultSet.next()) {
					Ideia tIdeia = recuperarIdeia(tResultSet);

					// Adicionar o o bjeto na lista
					tLista.add(tIdeia);
				}

				// Liberar os recursos
				tResultSet.close();
				tComandoJdbc.close();
			} catch (SQLException tExcept) {
				ExceptionUtil.mostrarErro(tExcept, "Problemas na listagem do Ideia");
			}

			// Retornando a lista de objetos
			return tLista;
		}
		
		
		 public List<Ideia> searchByIdAluno(int pIdAluno)
		    {
		        List<Ideia> tLista = new ArrayList<>();

		        try
		        {
		            // Obter a conexão
		            Connection tConexao = Conexao.getConexao();

		            // Criar o comando
		            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByAluno);

		            // Preencher o comando
		            int i = 1;
		            tComandoJdbc.setInt(i++, pIdAluno);

		            // Executar o comando
		            ResultSet tResultSet = tComandoJdbc.executeQuery();

		            // Processar o resultado
		            while (tResultSet.next())
		            {
		                Ideia tIdeia = recuperarIdeia(tResultSet);

		                // Adicionar o o bjeto na lista
		                tLista.add(tIdeia);
		            }

		            // Liberar os recursos
		            tResultSet.close();
		            tComandoJdbc.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa das ideias por Aluno");
		        }

		        // Retornando a lista de objetos
		        return tLista;
		    }
		 
		 public int countByAluno(int pIdAluno)
		    {
		        int tQtde = 0;

		        try
		        {
		            // Obter a conexão
		            Connection tConexao = Conexao.getConexao();

		            // Criar o comando
		            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByAluno);

		            // Preencher o comando
		            int i = 1;
		            tComandoJdbc.setInt(i++, pIdAluno);

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
		            ExceptionUtil.mostrarErro(tExcept, "Problemas na contagem das ideias por aluno");
		        }

		        // Retornando a lista de objetos
		        return tQtde;
		    }
		 
		 public List<Ideia> searchByIdDepartamentosOpet(int pIdDepartamentosOpet)
		    {
		        List<Ideia> tLista = new ArrayList<>();

		        try
		        {
		            // Obter a conexão
		            Connection tConexao = Conexao.getConexao();

		            // Criar o comando
		            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByDepartamento);

		            // Preencher o comando
		            int i = 1;
		            tComandoJdbc.setInt(i++, pIdDepartamentosOpet);

		            // Executar o comando
		            ResultSet tResultSet = tComandoJdbc.executeQuery();

		            // Processar o resultado
		            while (tResultSet.next())
		            {
		               Ideia tIdeia = recuperarIdeia(tResultSet);

		                // Adicionar o o bjeto na lista
		                tLista.add(tIdeia);
		            }

		            // Liberar os recursos
		            tResultSet.close();
		            tComandoJdbc.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa das ideias por departamento");
		        }
		     // Retornando a lista de objetos
		        return tLista;
		    }
		 
		 public int countByDepartamento(int pIdDepartamentosOpet)
		    {
		        int tQtde = 0;

		        try
		        {
		            // Obter a conexão
		            Connection tConexao = Conexao.getConexao();

		            // Criar o comando
		            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByDepartamento);

		            // Preencher o comando
		            int i = 1;
		            tComandoJdbc.setInt(i++, pIdDepartamentosOpet);

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
		            ExceptionUtil.mostrarErro(tExcept, "Problemas na contagem das ideias por departamento");
		        }

		        // Retornando a lista de objetos
		        return tQtde;
		    }
		 
		 public List<Ideia> searchByNome(String pNome)
		    {
		        if (pNome == null || pNome.isEmpty())
		            return search();

		        List<Ideia> tLista = new ArrayList<>();

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
		                Ideia tIdeia = recuperarIdeia(tResultSet);

		                // Adicionar o o bjeto na lista
		                tLista.add(tIdeia);
		            }

		            // Liberar os recursos
		            tResultSet.close();
		            tComandoJdbc.close();
		        }
		        catch (SQLException tExcept)
		        {
		            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa das ideias por nome");
		        }

		        // Retornando a lista de objetos
		        return tLista;
		    }

		 

		private Ideia recuperarIdeia(ResultSet tResultSet) throws SQLException {
			Ideia tIdeia = new Ideia();
			
			

			tIdeia.setId(tResultSet.getInt("ID"));
			tIdeia.setNome(tResultSet.getString("NOME"));
			tIdeia.setDescricaoProblema(tResultSet.getString("DESCRICAODOPROBLEMA"));
			tIdeia.setRecomendacao(tResultSet.getString("RECOMENDACAO"));
			tIdeia.setDataCadastro(tResultSet.getDate("DATACADASTRO").toLocalDate());
			//mostrando que é null
			Date tDataAnalise = tResultSet.getDate("DATAANALISE");
	        if (tResultSet.wasNull())
	            tIdeia.setDataAnalise(null);
	        else
	            tIdeia.setDataAnalise(tDataAnalise.toLocalDate());
	        
	        
	        String tAprovado = tResultSet.getString("APROVADO");
	        if (tResultSet.wasNull())
	            tIdeia.setAprovado(false);
	        else
	            tIdeia.setAprovado(tAprovado.equals("S"));
	        
	        
	        String tReposta = tResultSet.getString("RESPOSTA");
	        if (tResultSet.wasNull())
	            tIdeia.setResposta(null);
	        else
	            tIdeia.setResposta(tReposta);
			
			
			//tIdeia.setDataAnalise(tResultSet.getDate("DATAANALISE").toLocalDate());
			//tIdeia.setAprovado(tResultSet.getBoolean("APROVADO"));
			//tIdeia.setResposta(tResultSet.getString("RESPOSTA"));
			tIdeia.setIdAluno(tResultSet.getInt("ID_ALUNO"));
			tIdeia.setIdDepartamentosOpet(tResultSet.getInt("ID_DEPARTAMENTOSOPET"));
			return tIdeia;
			
			  
		}
	}


