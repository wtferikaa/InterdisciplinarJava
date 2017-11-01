package br.edu.opet.interdisciplinardois.teste;

import java.time.LocalDate;
import java.util.List;


import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Ideia;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;




class TesteIdeiaDao {
	
	public static void main(String[] pArgs) {
	    //
	    // Pré teste do departamento
	    //
		// Criar um DepartamentosOpet
				DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "fabricaOpet@gmail.com", "123", "Fábrica de Software");
				DepartamentosOpet tDepartamentosOpetB = new DepartamentosOpet(0, "FabLab@gmail.com", "321", "FabLab");

				// Criando o objeto de persistência
				DepartamentosOpetDao tDepartamentosOpetDao = new DepartamentosOpetDao();

				// Incluir o DepartamentosOpet
				System.out.println();
				System.out.println("Incluindo");
				DepartamentosOpet tDepartamentosOpet2a = tDepartamentosOpetDao.create(tDepartamentosOpetA);
				if (tDepartamentosOpet2a != null)
					System.out.println("OK...... : " + tDepartamentosOpet2a);
				else
					System.out.println("ERRO.... : " + tDepartamentosOpet2a);
				DepartamentosOpet tDepartamentosOpet2b = tDepartamentosOpetDao.create(tDepartamentosOpetB);
				if (tDepartamentosOpet2b != null)
					System.out.println("OK...... : " + tDepartamentosOpet2b);
				else
					System.out.println("ERRO.... : " + tDepartamentosOpet2b);
	
			//Pré teste Curso
			//
			// Criar um Curso
			Curso tCursoA = new Curso(0, "ronaldo@gmail.com", "123", "TDS", "Ronaldo");
			Curso tCursoB = new Curso(0, "maria@gmail.com", "321", "Estética", "Maria");

			// Criando o objeto de persistência
			CursoDao tCursoDao = new CursoDao();

			// Incluir o Curso
			System.out.println();
			System.out.println("Incluindo");
			Curso tCurso2a = tCursoDao.create(tCursoA);
			if (tCurso2a != null)
				System.out.println("OK...... : " + tCurso2a);
			else
				System.out.println("ERRO.... : " + tCurso2a);
			Curso tCurso2b = tCursoDao.create(tCursoB);
			if (tCurso2b != null)
				System.out.println("OK...... : " + tCurso2b);
			else
				System.out.println("ERRO.... : " + tCurso2b);
			
		//
		// Pré Teste do Aluno
		//
		// Criar um Aluno
				Aluno tAlunoA = new Aluno(0, "erika@gmail.com", "123", "Erika", 41987013, "Manhã", "TDS171A", tCurso2a.getId());
				Aluno tAlunoB = new Aluno(0, "francielli@gmail.com", "321", "Fran", 41988141, "Manhã", "TDS171A", tCurso2b.getId());

				// Criando o objeto de persistência
				AlunoDao tAlunoDao = new AlunoDao();

				// Incluindo o Aluno
				System.out.println();
				System.out.println("Incluindo");
				Aluno tAluno2a = tAlunoDao.create(tAlunoA);
				if (tAluno2a != null)
					System.out.println("OK...... : " + tAluno2a);
				else
					System.out.println("ERRO.... : " + tAluno2a);
				Aluno tAluno2b = tAlunoDao.create(tAlunoB);
				if (tAluno2b != null)
					System.out.println("OK...... : " + tAluno2b);
				else
					System.out.println("ERRO.... : " + tAluno2b);

				
				// Teste
				
				//
				// Criar uma Ideia 
				Ideia tIdeiaA = new Ideia(0, "ideia 1", "problema 1", "resolver o problema 1", LocalDate.now(), LocalDate.of(2017, 10, 29), true, "resposta 1", tAluno2a.getId(), tDepartamentosOpet2a.getId());
				Ideia tIdeiaB = new Ideia(0, "ideia 2", "problema 2", "resolver o problema 2", LocalDate.now(), LocalDate.of(2017, 10, 30), true, "resposta 2", tAluno2b.getId(), tDepartamentosOpet2b.getId());

				// Criando o objeto de persistência
				IdeiaDao tDao = new IdeiaDao();

				// Incluindo o Ideia
				System.out.println();
				System.out.println("Incluindo");
				Ideia tIdeia2a = tDao.create(tIdeiaA);
				if (tIdeia2a != null)
					System.out.println("OK...... : " + tIdeia2a);
				else
					System.out.println("ERRO.... : " + tIdeia2a);
				Ideia tIdeia2b = tDao.create(tIdeiaB);
				if (tIdeia2b != null)
					System.out.println("OK...... : " + tIdeia2b);
				else
					System.out.println("ERRO.... : " + tIdeia2b);

				// Recuperando o Ideia
				System.out.println();
				System.out.println("Recuperando");
				Ideia tIdeia3a = tDao.recovery(tIdeia2a.getId());
				if (tIdeia3a != null)
					System.out.println("OK...... : " + tIdeia3a);
				else
					System.out.println("ERRO.... : " + tIdeia3a);
				Ideia tIdeia3b = tDao.recovery(tIdeia2b.getId());
				if (tIdeia3b != null)
					System.out.println("OK...... : " + tIdeia3b);
				else
					System.out.println("ERRO.... : " + tIdeia3b);
				
				// Atualizando a ideia 
		        System.out.println();
		        System.out.println("Atualizando");
		        tIdeia2a.setNome("Ideia 1.1");
		        tIdeia2a.setDescricaoProblema("problema 1.1");
		        tIdeia2a.setRecomendacao("resolver o problema 1.1");
		        tIdeia2a.setDataCadastro(LocalDate.now());
		        tIdeia2a.setDataAnalise(LocalDate.of(2017, 10, 30));
		        tIdeia2a.setAprovado(false);
		        tIdeia2a.setResposta("Resposta 1.1");
		        tIdeia2a.setIdAluno(tAluno2a.getId());
		        tIdeia2a.setIdDepartamentosOpet(tDepartamentosOpet2a.getId());
		        Ideia tIdeia4a = tDao.update(tIdeia2a);
		        if (tIdeia4a != null)
		            System.out.println("OK...... : " + tIdeia4a);
		        else
		            System.out.println("ERRO.... : " + tIdeia4a);
				tIdeia2b.setNome("Ideia 2.1");
				tIdeia2b.setDescricaoProblema("Problema 2.1");
				tIdeia2b.setRecomendacao("resolver o problema 2.1");
				tIdeia2a.setDataCadastro(LocalDate.now());
		        tIdeia2a.setDataAnalise(LocalDate.of(2017, 11, 5));
				tIdeia2b.setAprovado(false);
				tIdeia2b.setResposta("Resposta 2.1");
				tIdeia2b.setIdAluno(tAluno2b.getId());
				tIdeia2b.setIdDepartamentosOpet(tDepartamentosOpet2b.getId());
				Ideia tIdeia4b = tDao.update(tIdeia2b);
				if (tIdeia4a != null)
					System.out.println("OK...... : " + tIdeia4b);
				else
					System.out.println("ERRO.... : " + tIdeia4b);

				// Recuperando a ideia
				System.out.println();
				System.out.println("Recuperando");
				Ideia tIdeia5a = tDao.recovery(tIdeia2a.getId());
				if (tIdeia5a != null)
					System.out.println("OK...... : " + tIdeia5a);
				else
					System.out.println("ERRO.... : " + tIdeia5a);
				Ideia tIdeia5b = tDao.recovery(tIdeia2b.getId());
				if (tIdeia5b != null)
					System.out.println("OK...... : " + tIdeia5b);
				else
					System.out.println("ERRO.... : " + tIdeia5b);

				// Listar as ideias
				List<Ideia> tLista = tDao.search();
				System.out.println();
				System.out.println("Pesquisando");
				for (Ideia tIdeia : tLista) {
					System.out.println("OK...... : " + tIdeia);
				}
				
				// pesquisar as ideias por nome
		        tLista = tDao.searchByNome(tIdeia2a.getNome());
		        System.out.println();
		        System.out.println("Pesquisando por nome");
		        for (Ideia tIdeia : tLista)
		        {
		            System.out.println("OK...... : " + tIdeia);
		        }
		        
				// pesquisar as ideias por aluno
		        tLista = tDao.searchByIdAluno(tAluno2a.getId());
		        System.out.println();
		        System.out.println("Pesquisando por aluno");
		        for (Ideia tIdeia : tLista)
		        {
		            System.out.println("OK...... : " + tIdeia);
		        }
		        
		     // pesquisar as ideias por departamento
		        tLista = tDao.searchByIdDepartamentosOpet(tDepartamentosOpet2a.getId());
		        System.out.println();
		        System.out.println("Pesquisando por departamento");
		        for (Ideia tIdeia : tLista)
		        {
		            System.out.println("OK...... : " + tIdeia);
		        }
		        
				// Contar as ideias por aluno
		        int tQtde = tDao.countByAluno(tAluno2a.getId());
		        System.out.println();
		        System.out.println("Contando as ideias por aluno");
		        System.out.println("OK...... : " + tQtde);
		        
		     // Contar as ideias por departamento
		        int tQtdeDepartamento = tDao.countByDepartamento(tDepartamentosOpet2a.getId());
		        System.out.println();
		        System.out.println("Contando as ideias por departamento");
		        System.out.println("OK...... : " + tQtdeDepartamento);
					
					// Remover a ideia
					System.out.println();
					System.out.println("Removendo");
					if (tDao.delete(tIdeia2a.getId()))
						System.out.println("OK...... : " + tIdeia2a);
					else
						System.out.println("ERRO.... : " + tIdeia2a);
					if (tDao.delete(tIdeia2b.getId()))
						System.out.println("OK...... : " + tIdeia2b);
					else
						System.out.println("ERRO.... : " + tIdeia2b);
					

					// Verificando se removeu
					System.out.println();
					System.out.println("Verificando a remoção");
					if (tDao.delete(tIdeia2a.getId()))
						System.out.println("ERRO.... : " + tIdeia2a);
					else
						System.out.println("OK...... : " + tIdeia2a);
					if (tDao.delete(tIdeia2b.getId()))
						System.out.println("ERRO.... : " + tIdeia2b);
					else
						System.out.println("OK...... : " + tIdeia2b);
				//Pós teste 
				//
					// Remover o DepartamentosOpet
					System.out.println();
					System.out.println("Removendo");
					if (tDepartamentosOpetDao.delete(tDepartamentosOpet2a.getId()))
						System.out.println("OK...... : " + tDepartamentosOpet2a);
					else
						System.out.println("ERRO.... : " + tDepartamentosOpet2a);
					if (tDepartamentosOpetDao.delete(tDepartamentosOpet2b.getId()))
						System.out.println("OK...... : " + tDepartamentosOpet2b);
					else
						System.out.println("ERRO.... : " + tDepartamentosOpet2b);
				
					
					
					// Remover o aluno 
					System.out.println();
					System.out.println("Removendo");
					if (tAlunoDao.delete(tAluno2a.getId()))
						System.out.println("OK...... : " + tAluno2a);
					else
						System.out.println("ERRO.... : " + tAluno2a);
					if (tAlunoDao.delete(tAluno2b.getId()))
						System.out.println("OK...... : " + tAluno2b);
					else
						System.out.println("ERRO.... : " + tAluno2b);
					
					// Remover o curso
					System.out.println();
					System.out.println("Removendo");
					if (tCursoDao.delete(tCurso2a.getId()))
						System.out.println("OK...... : " + tCurso2a);
					else
						System.out.println("ERRO.... : " + tCurso2a);
					if (tCursoDao.delete(tCurso2b.getId()))
						System.out.println("OK...... : " + tCurso2b);
					else
						System.out.println("ERRO.... : " + tCurso2b);
					
					
					
				
					

				}

	
}


