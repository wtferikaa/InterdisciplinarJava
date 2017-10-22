package br.edu.opet.interdisciplinardois.teste;


import java.util.List;

import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;

public class TesteDepartamentosOpetDao {

	public static void main(String[] pArgs) {
		// Criar um DepartamentosOpet
		DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "fabricaOpet@gmail.com", "123", "Fábrica de Software");
		DepartamentosOpet tDepartamentosOpetB = new DepartamentosOpet(0, "FabLab@gmail.com", "321", "FabLab");

		// Criando o objeto de persistência
		DepartamentosOpetDao tDao = new DepartamentosOpetDao();

		// Incluir o DepartamentosOpet
		System.out.println();
		System.out.println("Incluindo");
		DepartamentosOpet tDepartamentosOpet2a = tDao.create(tDepartamentosOpetA);
		if (tDepartamentosOpet2a != null)
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);
		DepartamentosOpet tDepartamentosOpet2b = tDao.create(tDepartamentosOpetB);
		if (tDepartamentosOpet2b != null)
			System.out.println("OK...... : " + tDepartamentosOpet2b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2b);

		// Recuperando o DepartamentosOpet
		System.out.println();
		System.out.println("Recuperando");
		DepartamentosOpet tDepartamentosOpet3a = tDao.recovery(tDepartamentosOpet2a.getId());
		if (tDepartamentosOpet3a != null)
			System.out.println("OK...... : " + tDepartamentosOpet3a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet3a);
		DepartamentosOpet tDepartamentosOpet3b = tDao.recovery(tDepartamentosOpet2b.getId());
		if (tDepartamentosOpet3b != null)
			System.out.println("OK...... : " + tDepartamentosOpet3b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet3b);

		// Atualizando o DepartamentosOpet
		System.out.println();
		System.out.println("Atualizando");
		tDepartamentosOpet2a.setEmail("fabrica_software_opet@outlook.com");
		tDepartamentosOpet2a.setSenha("1234");
		tDepartamentosOpet2a.setNome(" Fábrica de Software da Opet");
		DepartamentosOpet tDepartamentosOpet4a = tDao.update(tDepartamentosOpet2a);
		if (tDepartamentosOpet4a != null)
			System.out.println("OK...... : " + tDepartamentosOpet4a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet4a);

		tDepartamentosOpet2b.setEmail("fabrica_lab@outlook.com");
		tDepartamentosOpet2b.setSenha("3210");
		tDepartamentosOpet2b.setNome( " FábricaLab");
		DepartamentosOpet tDepartamentosOpet4b = tDao.update(tDepartamentosOpet2b);
		if (tDepartamentosOpet4a != null)
			System.out.println("OK...... : " + tDepartamentosOpet4b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet4b);

		// Recuperando o DepartamentosOpet
		System.out.println();
		System.out.println("Recuperando");
		DepartamentosOpet tDepartamentosOpet5a = tDao.recovery(tDepartamentosOpet2a.getId());
		if (tDepartamentosOpet5a != null)
			System.out.println("OK...... : " + tDepartamentosOpet5a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet5a);
		DepartamentosOpet tDepartamentosOpet5b = tDao.recovery(tDepartamentosOpet2b.getId());
		if (tDepartamentosOpet5b != null)
			System.out.println("OK...... : " + tDepartamentosOpet5b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet5b);

		// Listar os DepartamentosOpets
		List<DepartamentosOpet> tLista = tDao.search();
		System.out.println();
		System.out.println("Pesquisando");
		for (DepartamentosOpet tDepartamentosOpet : tLista) {
			System.out.println("OK...... : " + tDepartamentosOpet);
		}

		// Remover o DepartamentosOpet
		System.out.println();
		System.out.println("Removendo");
		if (tDao.delete(tDepartamentosOpet2a.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);
		if (tDao.delete(tDepartamentosOpet2b.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2b);

		// Verificando se removeu
		System.out.println();
		System.out.println("Verificando a remoção");
		if (tDao.delete(tDepartamentosOpet2a.getId()))
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);
		else
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		if (tDao.delete(tDepartamentosOpet2b.getId()))
			System.out.println("ERRO.... : " + tDepartamentosOpet2b);
		else
			System.out.println("OK...... : " + tDepartamentosOpet2b);
	}

}
