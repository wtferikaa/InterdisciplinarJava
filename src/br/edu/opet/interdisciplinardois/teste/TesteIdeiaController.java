package br.edu.opet.interdisciplinardois.teste;

import java.text.ParseException;

import java.time.LocalDate;

import java.util.List;

import br.edu.opet.interdisciplinardois.controller.IdeiaController;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;

import br.edu.opet.interdisciplinardois.dto.IdeiaDto;
import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;

public class TesteIdeiaController {

	public static void main(String[] pArgs) throws ParseException {

		//
		// Pré Teste
		//
		// Criar um Curso
		Curso tCursoA = new Curso(0, "Estética", "maria");
		Curso tCursoB = new Curso(0, "TDS", "Ronaldo");
		Curso tCursoC = new Curso(0, "Engenharia", "Fernando");

		// Criando o objeto de persistência
		CursoDao tCursoDao = new CursoDao();

		// Incluir o Curso
		System.out.println();
		System.out.println("Incluindo o Curso");
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
		Curso tCurso2c = tCursoDao.create(tCursoC);
		if (tCurso2c != null)
			System.out.println("OK...... : " + tCurso2c);
		else
			System.out.println("ERRO.... : " + tCurso2c);

		// Criar um aluno
		Aluno tAlunoA = new Aluno(0, "miley@gmail.com", "mi123", "Miley", 419981738, "Noite", "Estética",
				tCurso2a.getId());
		Aluno tAlunoB = new Aluno(0, "noah@gmail.com", "noah123", "Noah", 419768718, "Manhã", "TDS", tCurso2b.getId());
		Aluno tAlunoC = new Aluno(0, "xxx@gmail.com", "xxx123", "XXX Tentacion", 4166668, "Tarde", "Engenharia",
				tCurso2c.getId());

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
		Aluno tAluno2b = tAlunoDao.create(tAlunoB);
		if (tAluno2b != null)
			System.out.println("OK...... : " + tAluno2b);
		else
			System.out.println("ERRO.... : " + tAluno2b);
		Aluno tAluno2c = tAlunoDao.create(tAlunoC);
		if (tAluno2c != null)
			System.out.println("OK...... : " + tAluno2c);
		else
			System.out.println("ERRO.... : " + tAluno2c);

		// Criar departamentos
		DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "departamentoUm@gmail.com", "senha1",
				"Departamento Um");
		DepartamentosOpet tDepartamentosOpetB = new DepartamentosOpet(0, "departamentoDois@gmail.com", "senha2",
				"Departamento Dois");
		DepartamentosOpet tDepartamentosOpetC = new DepartamentosOpet(0, "departamentoTres@gmail.com", "senha3",
				"Departamento Tres");

		// Criando o objeto de persistência
		DepartamentosOpetDao tDepartamentosOpetDao = new DepartamentosOpetDao();

		// Incluir o departamento
		System.out.println();
		System.out.println("Incluindo o DepartamentosOpet");
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
		DepartamentosOpet tDepartamentosOpet2c = tDepartamentosOpetDao.create(tDepartamentosOpetC);
		if (tDepartamentosOpet2c != null)
			System.out.println("OK...... : " + tDepartamentosOpet2c);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2c);

		// ideia
		Ideia tIdeiaA = new Ideia(0, "ideia irineia 1", "problema irineia 1", "resolver o problema irineia 1",
				LocalDate.of(2017, 11, 02), LocalDate.of(2017, 11, 03), true, "resposta irineia", tAluno2a.getId(),
				tDepartamentosOpet2a.getId());

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
		IdeiaController tController = new IdeiaController();

		// Criar uma ideia
		Ideia tIdeiaB = new Ideia(0, "ideia legal", "problema legal", "resolver o problema legal", LocalDate.now(),
				LocalDate.of(2017, 11, 03), true, "resposta legal", tAluno2a.getId(), tDepartamentosOpet2a.getId());

		// Criar a ideia
		System.out.println();
		System.out.println("Incluindo uma ideia via controller");
		IdeiaDto tDto = tController.criarIdeia(tIdeiaB);
		if (tDto.isOk()) {
			// Recuperando o paciente incluído para obter o ID gerado
			tIdeiaB = tDto.getIdeia();
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tIdeiaB);
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Incluindo uma ideia nulo");
		tDto = tController.criarIdeia(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar a ideia
		System.out.println();
		System.out.println("Recuperando uma ideia via controller");
		tDto = tController.recuperarIdeia(tIdeiaB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getIdeia());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando uma ideia com id inválido");
		tDto = tController.recuperarIdeia(-32432);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Recuperando uma ideia não existente");
		tDto = tController.recuperarIdeia(9999999);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Atualizar ideia
		tIdeiaB.setNome(tIdeiaB.getNome() + " Ideia pro problema cabuloso");
		tIdeiaB.setDescricaoProblema("problema cabuloso");
		tIdeiaB.setRecomendacao("resolver problema");
		tIdeiaB.setDataCadastro(LocalDate.now());
		tIdeiaB.setDataAnalise(LocalDate.of(2017, 11, 2));
		tIdeiaB.setAprovado(false);
		tIdeiaB.setResposta("não gostei");
		// tIdeiaB.setIdAluno(0);
		// tIdeiaB.setIdDepartamentosOpet(0);

		System.out.println();
		System.out.println("Atualizando uma ideia com um nome que já existe");
		tDto = tController.atualizarIdeia(tIdeiaB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Acertando o id para a atualização
		tIdeiaB.setNome("ideia boa");

		// Atualizando a ideia
		System.out.println();
		System.out.println("Atualizando uma ideia via controller");
		tDto = tController.atualizarIdeia(tIdeiaB);
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getIdeia());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Atualizando uma ideia nula");
		tDto = tController.atualizarIdeia(null);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		Ideia tIdeiaC = new Ideia(0, "ideia estranha", "problema estranho", "resolver o problema estranho",
				LocalDate.now(), LocalDate.of(2017, 11, 04), false, "resposta estranha", tAluno2a.getId(),
				tDepartamentosOpet2a.getId());

		System.out.println();
		System.out.println("Atualizando uma ideia que não existe");
		tDto = tController.atualizarIdeia(tIdeiaC);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperar a ideia
		System.out.println();
		System.out.println("Recuperando uma ideia via controller");
		tDto = tController.recuperarIdeia(tIdeiaB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			System.out.println("           " + tDto.getIdeia());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperando as ideias por aluno
		System.out.println();
		System.out.println("Recuperando as ideias por aluno");
		tDto = tController.pesquisarIdeiaPorAluno(tAluno2b.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			List<Ideia> tLista = tDto.getLista();
			for (Ideia tIdeia : tLista) {
				System.out.println("         : " + tIdeia);
			}
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperando as ideias por departamento
		System.out.println();
		System.out.println("Recuperando as ideias por departamento");
		tDto = tController.pesquisarIdeiaPorDepartamento(tDepartamentosOpet2b.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			List<Ideia> tLista = tDto.getLista();
			for (Ideia tIdeia : tLista) {
				System.out.println("         : " + tIdeia);
			}
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Recuperando as ideias por nome
		System.out.println();
		System.out.println("Recuperando as ideias por nome");
		tDto = tController.pesquisarIdeiaPorNome(tIdeia2a.getNome());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
			List<Ideia> tLista = tDto.getLista();
			for (Ideia tIdeia : tLista) {
				System.out.println("         : " + tIdeia);
			}
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		// Removendo o paciente
		System.out.println();
		System.out.println("Removendo uma ideia via controller");
		tDto = tController.deletarIdeia(tIdeiaB.getId());
		if (tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo uma ideia com id inválido");
		tDto = tController.deletarIdeia(-4);
		if (!tDto.isOk()) {
			System.out.println("OK...... : " + tDto.getMensagem());
		} else {
			System.out.println("ERRO.... : " + tDto.getMensagem());
		}

		System.out.println();
		System.out.println("Removendo uma ideia com departamento");
		tDto = tController.deletarIdeia(tAlunoA.getId());
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
		if (tAlunoDao.delete(tAluno2b.getId()))
			System.out.println("OK...... : " + tAluno2b);
		else
			System.out.println("ERRO.... : " + tAluno2b);
		if (tAlunoDao.delete(tAluno2c.getId()))
			System.out.println("OK...... : " + tAluno2c);
		else
			System.out.println("ERRO.... : " + tAluno2c);

		// Remover o departamento
		System.out.println();
		System.out.println("Removendo o departamento");
		if (tDepartamentosOpetDao.delete(tDepartamentosOpet2a.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2a);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2a);
		if (tDepartamentosOpetDao.delete(tDepartamentosOpet2b.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2b);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2b);
		if (tDepartamentosOpetDao.delete(tDepartamentosOpet2c.getId()))
			System.out.println("OK...... : " + tDepartamentosOpet2c);
		else
			System.out.println("ERRO.... : " + tDepartamentosOpet2c);

		// removendo o curso
		System.out.println();
		System.out.println("Removendo o curso");
		if (tCursoDao.delete(tCurso2a.getId()))
			System.out.println("OK...... : " + tCurso2a);
		else
			System.out.println("ERRO.... : " + tCurso2a);
		if (tCursoDao.delete(tAluno2b.getId()))
			System.out.println("OK...... : " + tCurso2b);
		else
			System.out.println("ERRO.... : " + tCurso2b);
		if (tCursoDao.delete(tAluno2c.getId()))
			System.out.println("OK...... : " + tCurso2c);
		else
			System.out.println("ERRO.... : " + tCurso2c);
	}

}
