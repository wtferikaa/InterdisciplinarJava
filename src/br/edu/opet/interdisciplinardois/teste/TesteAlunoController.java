package br.edu.opet.interdisciplinardois.teste;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import br.edu.opet.interdisciplinardois.controller.AlunoController;

import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;

import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;

public class TesteAlunoController {

	public static void main(String[] pArgs) throws ParseException {
	
	//
	// Pré Teste
	//
	// Criar um curso
	Curso tCursoA = new Curso(0, "Feriado@gmail.com", "feriado", "Curso do feriado",
			"coordenador feriadão");

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
	Aluno tAlunoA = new Aluno(0, "solange@gmail.com", "solzinho", "Solange", 419981718, "Noite", "Gastronomia",
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
	DepartamentosOpet tDepartamentosOpetA = new DepartamentosOpet(0, "departamentoirineu@gmail.com",
			"nemeu", "Departamento Irineu");

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
	Ideia tIdeiaA = new Ideia(0, "ideia irineu 1", "problema irineu 1", "resolver o problema irineu 1", tDataCadastro1,
			tDataAnalise1, true, "resposta irineu", tAluno2a.getId(), tDepartamentosOpet2a.getId());

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
			AlunoController tController = new AlunoController();

			// Criar um aluno
			Aluno tAlunoB = new Aluno(0, "camila@gmail.com", "cami", "Camila", 41999718, "Noite", "Psicologia",
					tCurso2a.getId());

			// Criar o aluno
			System.out.println();
			System.out.println("Incluindo um aluno via controller");
			AlunoDto tDto = tController.criarAluno(tAlunoB);
			if (tDto.isOk()) {
				// Recuperando o paciente incluído para obter o ID gerado
				tAlunoB = tDto.getAluno();
				System.out.println("OK...... : " + tDto.getMensagem());
				System.out.println("           " + tAlunoB);
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Incluindo um aluno nulo");
			tDto = tController.criarAluno(null);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			// Recuperar o aluno
			System.out.println();
			System.out.println("Recuperando um aluno via controller");
			tDto = tController.recuperarAluno(tAlunoB.getId());
			if (tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
				System.out.println("           " + tDto.getAluno());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Recuperando um aluno com id inválido");
			tDto = tController.recuperarAluno(-32432);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Recuperando um aluno não existente");
			tDto = tController.recuperarAluno(9999999);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			// Atualizar aluno
			tAlunoB.setEmail("alunonovo@gmail.com");
			tAlunoB.setSenha("senhanova");
			tAlunoB.setNome(tAlunoB.getNome() + " Aluno Novo");
			tAlunoB.setTelefone(0);
			tAlunoB.setTurno("Manhã");
			tAlunoB.setTurma("turma nova");
			
			System.out.println();
			System.out.println("Atualizando um aluno com um nome que já existe");
			tDto = tController.atualizarAluno(tAlunoB);
			if (tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			// Acertando o id para a atualização
			tAlunoB.setNome("Departamento com nome que já existe");

			// Atualizando o aluno
			System.out.println();
			System.out.println("Atualizando um departamento via controller");
			tDto = tController.atualizarAluno(tAlunoB);
			if (tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
				System.out.println("           " + tDto.getAluno());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Atualizando um aluno nulo");
			tDto = tController.atualizarAluno(null);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			Aluno tAlunoC = new Aluno(0, "alunoAtualizado@gmail.com", "aluno12", "Aluno Atualizado", 41999908, "Tarde", "Estética",
					tCurso2a.getId());

			System.out.println();
			System.out.println("Atualizando um aluno que não existe");
			tDto = tController.atualizarAluno(tAlunoC);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			// Recuperar o aluno
			System.out.println();
			System.out.println("Recuperando um aluno via controller");
			tDto = tController.recuperarAluno(tAlunoB.getId());
			if (tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
				System.out.println("           " + tDto.getAluno());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}
			
			// Recuperando os alunos por curso
	        System.out.println();
	        System.out.println("Recuperando os alunos por curso");
	        tDto = tController.pesquisarAlunoPorCurso(tCurso2a.getId());
	        if (tDto.isOk())
	        {
	            System.out.println("OK...... : " + tDto.getMensagem());
	            List<Aluno> tLista = tDto.getLista();
	            for (Aluno tIdeia : tLista)
	            {
	                System.out.println("         : " + tIdeia);
	            }
	        }
	        else
	        {
	            System.out.println("ERRO.... : " + tDto.getMensagem());
	        }

	        // Recuperando os alunos por nome
	        System.out.println();
	        System.out.println("Recuperando os alunos por nome");
	        tDto = tController.pesquisarAlunosPorNome(tAluno2a.getNome());
	        if (tDto.isOk())
	        {
	            System.out.println("OK...... : " + tDto.getMensagem());
	            List<Aluno> tLista = tDto.getLista();
	            for (Aluno tIdeia : tLista)
	            {
	                System.out.println("         : " + tIdeia);
	            }
	        }
	        else
	        {
	            System.out.println("ERRO.... : " + tDto.getMensagem());
	        }

			// Removendo o paciente
			System.out.println();
			System.out.println("Removendo um aluno via controller");
			tDto = tController.removeAluno(tAlunoB.getId());
			if (tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Removendo um aluno com id inválido");
			tDto = tController.removeAluno(-4);
			if (!tDto.isOk()) {
				System.out.println("OK...... : " + tDto.getMensagem());
			} else {
				System.out.println("ERRO.... : " + tDto.getMensagem());
			}

			System.out.println();
			System.out.println("Removendo um aluno com ideia");
			tDto = tController.removeAluno(tAlunoA.getId());
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


