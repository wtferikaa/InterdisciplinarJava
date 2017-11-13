package br.edu.opet.interdisciplinardois.teste;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import br.edu.opet.interdisciplinardois.controller.DepartamentosOpetController;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dto.DepartamentosOpetDto;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;

public class TesteDepartamentosOpetController {

	public static void main(String[] pArgs) throws ParseException {

		//
		// Pré Teste
		//
		// Criar um curso
		Curso tCursoA = new Curso(0, "Analise e Desenvolvimento de Sistemas",
				"Coordenador Lucas");

		// Criando o objeto de persistência
		CursoDao tCursoDao = new CursoDao();

		// Incluir o curso
		System.out.println();
		System.out.println("Incluindo o curso");
		Curso tCurso2a = tCursoDao.create(tCursoA);
		if (tCurso2a != null)
			System.out.println("OK...... : " + tCurso2a);
		else
			System.out.println("ERRO.... : " + tCurso2a);

		// Criar um aluno
		Aluno tAlunoA = new Aluno(0, "andrey@gmail.com", "senhaAndrey", "Andrey", 419980718, "Noite", "Direito",
				tCurso2a.getId());

		// Criando o objeto de persistência
		AlunoDao tAlunoDao = new AlunoDao();

		// Incluir o aluno
		System.out.println();
		System.out.println("Incluindo o aluno");
		Aluno tAluno2a = tAlunoDao.create(tAlunoA);
		if (tAluno2a != null)
			System.out.println("OK...... : " + tAluno2a);
		else
			System.out.println("ERRO.... : " + tAluno2a);

		// Criar um departamento
		DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "departamentoTeste@gmail.com",
				"senhadep1", "Departamento Teste");

		// Criando o objeto de persistência
		DepartamentosOpetDao tDepartamentosOpetDao = new DepartamentosOpetDao();

		// Incluir o departamento
		System.out.println();
		System.out.println("Incluindo o departamento");
		DepartamentosOpet tDepartamentosOpet2a = tDepartamentosOpetDao.create(tDepartamentosOpetA);
		if (tDepartamentosOpet2a != null)
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);

		LocalDate tDataCadastro1 = LocalDate.now();
		LocalDate tDataAnalise1 = LocalDate.of(2017, 10, 30);
		Ideia tIdeiaA = new Ideia(0, "ideia teste 1", "problema teste 1", "resolver o problema teste 1", tDataCadastro1,
				tDataAnalise1, true, "resposta 1", tAluno2a.getId(), tDepartamentosOpet2a.getId());

		// Criando o objeto de persistência
		IdeiaDao tDao = new IdeiaDao();

		// Incluir a ideia
		System.out.println();
		System.out.println("Incluindo a ideia");
		Ideia tIdeia2a = tDao.create(tIdeiaA);
		if (tIdeia2a != null)
			System.out.println("OK...... : " + tIdeia2a);
		else
			System.out.println("ERRO.... : " + tIdeia2a);

		// Teste

		// Criando o objeto de Controller
		DepartamentosOpetController tController = new DepartamentosOpetController();

		// Criar um paciente
		DepartamentosOpet tDepartamentosOpetB = new DepartamentosOpet(0, "departamentoLegal@gmail.com",
				"senhalegal", "Departamento Legal");

		// Criar o paciente
		System.out.println();
		System.out.println("Incluindo um departamento via controller");
		DepartamentosOpetDto tDto = tController.cadastrarDepartamentosOpet(tDepartamentosOpetB);
		if (tDto.isOk()) {
			// Recuperando o paciente incluído para obter o ID gerado
			tDepartamentosOpetB = tDto.getDepartamentosOpet();
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDepartamentosOpetB);
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Incluindo um departamento nulo");
		tDto = tController.cadastrarDepartamentosOpet(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar o departamento
		System.out.println();
		System.out.println("Recuperando um departamento via controller");
		tDto = tController.recuperarDepartamento(tDepartamentosOpetB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getDepartamentosOpet());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando um departamento com id inválido");
		tDto = tController.recuperarDepartamento(-32432);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando um departamento não existente");
		tDto = tController.recuperarDepartamento(9999999);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Atualizar departamento
		tDepartamentosOpetB.setEmail("departamento novo@gmail.com");
		tDepartamentosOpetB.setSenha("senhanova");
		tDepartamentosOpetB.setNome(tDepartamentosOpetB.getNome() + " Departamento Novo");

		System.out.println();
		System.out.println("Atualizando um departamento com um nome que já existe");
		tDto = tController.atualizarDepartamento(tDepartamentosOpetB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Acertando o id para a atualização
		tDepartamentosOpetB.setNome("Departamento com nome que já existe");

		// Atualizando o departamento
		System.out.println();
		System.out.println("Atualizando um departamento via controller");
		tDto = tController.atualizarDepartamento(tDepartamentosOpetB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getDepartamentosOpet());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Atualizando um departamento nulo");
		tDto = tController.atualizarDepartamento(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		DepartamentosOpet tDepartamentosOpetC = new DepartamentosOpet(0, "departamentoAtualizado@gmail.com",
				"departamento Atualizado", "Departamento Atualizado");

		System.out.println();
		System.out.println("Atualizando um departamento que não existe");
		tDto = tController.atualizarDepartamento(tDepartamentosOpetC);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar o departamento
		System.out.println();
		System.out.println("Recuperando um departamento via controller");
		tDto = tController.recuperarDepartamento(tDepartamentosOpetB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getDepartamentosOpet());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}
		
		 // Recuperando os departamentos por Nome
        System.out.println();
        System.out.println("Recuperando os departamentos por nome");
        tDto = tController.pesquisarDepartamentoPorNome(tDepartamentosOpet2a.getNome());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            List<DepartamentosOpet> tLista = tDto.getLista();
            for (DepartamentosOpet tDepartamentosOpet : tLista)
            {
                System.out.println("         : " + tDepartamentosOpet);
            }
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }


		// Removendo o departamento
		System.out.println();
		System.out.println("Removendo um departamento via controller");
		tDto = tController.removeDepartamentosOpet(tDepartamentosOpetB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo um departamento com id inválido");
		tDto = tController.removeDepartamentosOpet(-4);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo um departamento com ideia");
		tDto = tController.removeDepartamentosOpet(tDepartamentosOpetA.getId());
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}
		

		//
		// Pós teste
		//

		// Remover o a ideia
		System.out.println();
		System.out.println("Removendo a ideia");
		if (tDao.delete(tIdeia2a.getId()))
			System.out.println("OK...... : " + tIdeia2a);
		else
			System.out.println("ERRO.... : " + tIdeia2a);

		// Remover o aluno
		System.out.println();
		System.out.println("Removendo o aluno");
		if (tAlunoDao.delete(tAluno2a.getId()))
			System.out.println("OK...... : " + tAluno2a);
		else
			System.out.println("ERRO.... : " + tAluno2a);

		// Remover o departamento
		System.out.println();
		System.out.println("Removendo o departamento");
		if (tDepartamentosOpetDao.delete(tDepartamentosOpet2a.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);

		// removendo o curso
		System.out.println();
		System.out.println("Removendo o curso");
		if (tCursoDao.delete(tCurso2a.getId()))
			System.out.println("OK...... : " + tCurso2a);
		else
			System.out.println("ERRO.... : " + tCurso2a);

	}

}
