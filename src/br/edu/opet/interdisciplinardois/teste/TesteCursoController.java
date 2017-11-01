package br.edu.opet.interdisciplinardois.teste;

import java.text.ParseException;
import java.time.LocalDate;

import br.edu.opet.interdisciplinardois.controller.CursoController;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dto.CursoDto;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;

public class TesteCursoController {

	public static void main(String[] pArgs) throws ParseException {

		//
		// Pré Teste
		//
		// Criar um curso
		Curso tCursoA = new Curso(0, "jessica@gmail.com", "12345", "Direito", "Jessica");

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
		Aluno tAlunoA = new Aluno(0, "joão@gmail.com", "1234", "João", 41998071, "Manhã", "TDS171A", tCurso2a.getId());

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
		DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "qualquerdepartamento@gmail.com", "12345",
				"Departamento Qualquer");

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
		Ideia tIdeiaA = new Ideia(0, "ideia 1", "problema 1", "resolver o problema 1", tDataCadastro1, tDataAnalise1,
				true, "resposta 1", tAluno2a.getId(), tDepartamentosOpet2a.getId());

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

		//Teste
		// Criando o objeto de Controller
		CursoController tController = new CursoController();

		// Criar um Curso
		Curso tCursoB = new Curso(0, "emailnovo@gmail.com", "1234", "CursoLegal", "CoordenadorNovo");

		// Criar o Curso
		System.out.println();
		System.out.println("Incluindo um Curso via controller");
		CursoDto tDto = tController.cadastrarCurso(tCursoB);
		if (tDto.isOk()) {
			// Recuperando o Curso incluído para obter o ID gerado
			tCursoB = tDto.getCurso();
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tCursoB);
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Incluindo um Curso nulo");
		tDto = tController.cadastrarCurso(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar o Curso
		System.out.println();
		System.out.println("Recuperando um Curso via controller");
		tDto = tController.recuperarCurso(tCursoB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getCurso());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando um Curso com id inválido");
		tDto = tController.recuperarCurso(-32432);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando um Curso não existente");
		tDto = tController.recuperarCurso(9999999);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Atualizar o Curso
		tCursoB.setEmail("ronaldo@outlook.com");
		tCursoB.setSenha("1234");
		tCursoB.setNome(tCurso2a.getNome() + " DIREITO");
		tCursoB.setNomeCoordenador("Ronaldo Rodrigues Robaldo");
		
		
		System.out.println();
		System.out.println("Atualizando um Curso para um nome que existe");
		tDto = tController.atualizarCurso(tCursoB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} 
		else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Acertando o nome para a atualização
		tCursoB.setNome("Analise de sistema");

		// Atualizando o Curso
		System.out.println();
		System.out.println("Atualizando um Curso via controller");
		tDto = tController.atualizarCurso(tCursoB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getCurso());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Atualizando um Curso nulo");
		tDto = tController.atualizarCurso(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		Curso tCursoC = new Curso(0, "sememail@gmail.com", "semsenha", "sem nome", "sem coordenador");

		System.out.println();
		System.out.println("Atualizando um Curso que não existe");
		tDto = tController.atualizarCurso(tCursoC);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar o Curso
		System.out.println();
		System.out.println("Recuperando um Curso via controller");
		tDto = tController.recuperarCurso(tCursoB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getCurso());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Removendo o Curso
		System.out.println();
		System.out.println("Removendo um Curso via controller");
		tDto = tController.removeCurso(tCursoB.getId());
		if (tDto.isOk())
		{
			System.out.println("OK...... : " + tDto.getMensagem());
		} 
		else 
		{
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo um Curso com id inválido");
		tDto = tController.removeCurso(-4);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo um Curso com aluno");
		tDto = tController.removeCurso(tCursoA.getId());
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
