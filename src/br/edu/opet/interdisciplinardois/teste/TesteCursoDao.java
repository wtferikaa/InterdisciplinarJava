package br.edu.opet.interdisciplinardois.teste;

import java.util.List;

import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.model.Curso;

class TesteCursoDao {
	


	public static void main(String[] pArgs) {
		// Criar um Curso
		Curso tCursoA = new Curso(0,  "TDS", "Ronaldo");
		Curso tCursoB = new Curso(0,  "Estética", "Maria");

		// Criando o objeto de persistência
		CursoDao tDao = new CursoDao();

		// Incluir o Curso
		System.out.println();
		System.out.println("Incluindo");
		Curso tCurso2a = tDao.create(tCursoA);
		if (tCurso2a != null)
			System.out.println("OK...... : " + tCurso2a);
		else
			System.out.println("ERRO.... : " + tCurso2a);
		Curso tCurso2b = tDao.create(tCursoB);
		if (tCurso2b != null)
			System.out.println("OK...... : " + tCurso2b);
		else
			System.out.println("ERRO.... : " + tCurso2b);

		// Recuperando o Curso
		System.out.println();
		System.out.println("Recuperando");
		Curso tCurso3a = tDao.recovery(tCurso2a.getId());
		if (tCurso3a != null)
			System.out.println("OK...... : " + tCurso3a);
		else
			System.out.println("ERRO.... : " + tCurso3a);
		Curso tCurso3b = tDao.recovery(tCurso2b.getId());
		if (tCurso3b != null)
			System.out.println("OK...... : " + tCurso3b);
		else
			System.out.println("ERRO.... : " + tCurso3b);

		// Atualizando o Curso primeiro Curso
		System.out.println();
		System.out.println("Atualizando");
		tCurso2a.setNome("TDSAM");
		tCurso2a.setNomeCoordenador("Ronaldo Rodrigues Robaldo");
		Curso tCurso4a = tDao.update(tCurso2a);
		if (tCurso4a != null)
			System.out.println("OK...... : " + tCurso4a);
		else
			System.out.println("ERRO.... : " + tCurso4a);
		
		//Atualizando o Segundo Curso
		tCurso2b.setNome("Estética Opet");
		tCurso2b.setNomeCoordenador("Maria Irineia");
		Curso tCurso4b = tDao.update(tCurso2b);
		if (tCurso4a != null)
			System.out.println("OK...... : " + tCurso4b);
		else
			System.out.println("ERRO.... : " + tCurso4b);

		// Recuperando os dois Cursos
		System.out.println();
		System.out.println("Recuperando");
		Curso tCurso5a = tDao.recovery(tCurso2a.getId());
		if (tCurso5a != null)
			System.out.println("OK...... : " + tCurso5a);
		else
			System.out.println("ERRO.... : " + tCurso5a);
		Curso tCurso5b = tDao.recovery(tCurso2b.getId());
		if (tCurso5b != null)
			System.out.println("OK...... : " + tCurso5b);
		else
			System.out.println("ERRO.... : " + tCurso5b);

		// Listar os Cursos
		List<Curso> tLista = tDao.search();
		System.out.println();
		System.out.println("Pesquisando");
		for (Curso tCurso : tLista) {
			System.out.println("OK...... : " + tCurso);
		}
		
		// pesquisar os cursos por nome
        tLista = tDao.searchByNome(tCurso2a.getNome());
        System.out.println();
        System.out.println("Pesquisando por nome");
        for (Curso tCurso : tLista)
        {
            System.out.println("OK...... : " + tCurso);
        }
        

		// Remover os dois Cursos
		System.out.println();
		System.out.println("Removendo");
		if (tDao.delete(tCurso2a.getId()))
			System.out.println("OK...... : " + tCurso2a);
		else
			System.out.println("ERRO.... : " + tCurso2a);
		if (tDao.delete(tCurso2b.getId()))
			System.out.println("OK...... : " + tCurso2b);
		else
			System.out.println("ERRO.... : " + tCurso2b);

		// Verificando se removeu
		System.out.println();
		System.out.println("Verificando a remoção");
		if (tDao.delete(tCurso2a.getId()))
			System.out.println("ERRO.... : " + tCurso2a);
		else
			System.out.println("OK...... : " + tCurso2a);
		if (tDao.delete(tCurso2b.getId()))
			System.out.println("ERRO.... : " + tCurso2b);
		else
			System.out.println("OK...... : " + tCurso2b);
	}

}
