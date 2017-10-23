package br.edu.opet.interdisciplinardois.teste;

import java.text.ParseException;
import java.util.List;

import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;

class TesteAlunoDao {

	public static void main(String[] pArgs) throws ParseException {
		//
		// Pré Teste
		//
		// Criar um Curso
		Curso tCursoA = new Curso(0, "baracho@gmail.com", "123", "TDS", "Baracho");
		Curso tCursoB = new Curso(0, "julia@gmail.com", "321", "Estética", "Julia");

		// Criando o objeto de persistência
		CursoDao tCursoDao = new CursoDao();

		// Incluindo o Curso 
		System.out.println();
		System.out.println("Incluindo o curso");
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
		// Teste
		
		//
		// Criar um Aluno
		Aluno tAlunoA = new Aluno(0, "erika@gmail.com", "123", "Erika", 41987013, "Manhã", "TDS171A", tCurso2a.getId());
		Aluno tAlunoB = new Aluno(0, "francielli@gmail.com", "321", "Fran", 41988141, "Manhã", "TDS171A", tCurso2b.getId());

		// Criando o objeto de persistência
		AlunoDao tDao = new AlunoDao();

		// Incluindo o Aluno
		System.out.println();
		System.out.println("Incluindo");
		Aluno tAluno2a = tDao.create(tAlunoA);
		if (tAluno2a != null)
			System.out.println("OK...... : " + tAluno2a);
		else
			System.out.println("ERRO.... : " + tAluno2a);
		Aluno tAluno2b = tDao.create(tAlunoB);
		if (tAluno2b != null)
			System.out.println("OK...... : " + tAluno2b);
		else
			System.out.println("ERRO.... : " + tAluno2b);

		// Recuperando o Aluno
		System.out.println();
		System.out.println("Recuperando");
		Aluno tAluno3a = tDao.recovery(tAluno2a.getId());
		if (tAluno3a != null)
			System.out.println("OK...... : " + tAluno3a);
		else
			System.out.println("ERRO.... : " + tAluno3a);
		Aluno tAluno3b = tDao.recovery(tAluno2b.getId());
		if (tAluno3b != null)
			System.out.println("OK...... : " + tAluno3b);
		else
			System.out.println("ERRO.... : " + tAluno3b);
		
		// Atualizando o Aluno
        System.out.println();
        System.out.println("Atualizando");
        tAluno2a.setEmail("erikapaiva7@hotmail.com");
        tAluno2a.setSenha("123456");
        tAluno2a.setNome("Erika Paiva Rodrigues");
        tAluno2a.setTelefone(41987013605L);
        tAluno2a.setTurno("Noite");
        tAluno2a.setTurma("TDS171AM");
        tAluno2a.setIdCurso(tCurso2b.getId());
        Aluno tAluno4a = tDao.update(tAluno2a);
        if (tAluno4a != null)
            System.out.println("OK...... : " + tAluno4a);
        else
            System.out.println("ERRO.... : " + tAluno4a);
		tAluno2b.setEmail("francielli@hotmail.com");
		tAluno2b.setSenha("54321");
		tAluno2b.setNome("Francielli Ferreira");
		tAluno2b.setTelefone(41998997709L);
		tAluno2b.setTurno("Noite");
		tAluno2b.setTurma("TDS171AM");
		tAluno2b.setIdCurso(tCurso2a.getId());
		Aluno tAluno4b = tDao.update(tAluno2b);
		if (tAluno4a != null)
			System.out.println("OK...... : " + tAluno4b);
		else
			System.out.println("ERRO.... : " + tAluno4b);

		// Recuperando o aluno
		System.out.println();
		System.out.println("Recuperando");
		Aluno tAluno5a = tDao.recovery(tAluno2a.getId());
		if (tAluno5a != null)
			System.out.println("OK...... : " + tAluno5a);
		else
			System.out.println("ERRO.... : " + tAluno5a);
		Aluno tAluno5b = tDao.recovery(tAluno2b.getId());
		if (tAluno5b != null)
			System.out.println("OK...... : " + tAluno5b);
		else
			System.out.println("ERRO.... : " + tAluno5b);

		// Listar os Alunos
		List<Aluno> tLista = tDao.search();
		System.out.println();
		System.out.println("Pesquisando");
		for (Aluno tAluno : tLista) {
			System.out.println("OK...... : " + tAluno);
		}
		
		// pesquisar os alunos por nome
        tLista = tDao.searchByNome(tAluno2a.getNome());
        System.out.println();
        System.out.println("Pesquisando por nome");
        for (Aluno tAluno : tLista)
        {
            System.out.println("OK...... : " + tAluno);
        }
        

		//Pesquisando por curso 
		tLista = tDao.searchByIdCurso(tCurso2a.getId());
		System.out.println();
		System.out.println("Pesquisando por Curso");
		for (Aluno tAluno : tLista) {
			System.out.println("OK...... : " + tAluno);
		}
		

		// Remover o aluno 
		System.out.println();
		System.out.println("Removendo");
		if (tDao.delete(tAluno2a.getId()))
			System.out.println("OK...... : " + tAluno2a);
		else
			System.out.println("ERRO.... : " + tAluno2a);
		if (tDao.delete(tAluno2b.getId()))
			System.out.println("OK...... : " + tAluno2b);
		else
			System.out.println("ERRO.... : " + tAluno2b);
		

		// Verificando se removeu
		System.out.println();
		System.out.println("Verificando a remoção");
		if (tDao.delete(tAluno2a.getId()))
			System.out.println("ERRO.... : " + tAluno2a);
		else
			System.out.println("OK...... : " + tAluno2a);
		if (tDao.delete(tAluno2b.getId()))
			System.out.println("ERRO.... : " + tAluno2b);
		else
			System.out.println("OK...... : " + tAluno2b);

		//
		// Pós teste
		//
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
