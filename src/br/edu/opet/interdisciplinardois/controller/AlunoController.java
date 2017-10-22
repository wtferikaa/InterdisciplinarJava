package br.edu.opet.interdisciplinardois.controller;



import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;
import br.edu.opet.interdisciplinardois.model.Aluno;

public class AlunoController {
	public AlunoDto cadastrarAluno(Aluno pAluno)
    {
        // Verificar as informações
        if (pAluno == null)
        {
            return new AlunoDto(false, "Tentativa de inclusão de aluno nulo");
        }
        
     // Criando o objeto de persistência
        AlunoDao tDao = new AlunoDao();

        // Verificando se o paciente já existe
        Aluno tAluno = tDao.recovery(pAluno.getId());
        if (tAluno != null)
        {
            return new AlunoDto(false, "Já existe este aluno cadastrado");
        }
        
        // Incluindo o paciente
        tAluno = tDao.create(pAluno);
        if (tAluno == null)
        {
            return new AlunoDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new AlunoDto(true, "Aluno incluído com sucesso", tAluno);
    }
	
	 public AlunoDto atualizarAluno(Aluno pAluno)
	    {
	        // Verificar as informações
	        if (pAluno == null)
	        {
	            return new AlunoDto(false, "Tentativa de atualização de aluno nulo");
	        }

	        // Criando o objeto de persistência
	        AlunoDao tDao = new AlunoDao();

	        // Recuperando o paciente
	        Aluno tAluno = tDao.recovery(pAluno.getId());
	        if (tAluno == null)
	        {
	            return new AlunoDto(false, "Não existe aluno com o identificador informado");
	        }

	        if (pAluno.getId() != tAluno.getId())
	        {
	            // Verificando se existe um paciente com o novo Id
	            tAluno = tDao.recovery(pAluno.getId());
	            if (tAluno != null)
	            {
	                return new AlunoDto(false, "Já existe aluno com o id/matrícula informado");
	            }
	        }

	        // Atualizando o aluno
	        tAluno = tDao.update(pAluno);
	        if (tAluno == null)
	        {
	            return new AlunoDto(false, "Não existe aluno com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new AlunoDto(true, "Aluno alterado com sucesso", tAluno);
	    }

	    public AlunoDto removeAluno(int pId)
	    {
	        // Verificar as informações
	        if (pId <= 0)
	        {
	            return new AlunoDto(false, "Identificador do aluno inválido");
	        }

	        // Criando o objeto de persistência
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        // Verificando se o paciente já existe
	        int tQtde = tDaoIdeia.countByAluno(pId); //tem que criar isso ainda 
	        if (tQtde != 0)
	        {
	            return new AlunoDto(false, "Aluno já tem ideias no sistema. Remoção proíbida");
	        }

	        // Criando o objeto de persistência
	        AlunoDao tDao = new AlunoDao();

	        // Incluindo o paciente
	        if (tDao.delete(pId))
	        {
	            // Retornando o indicativo de sucesso
	            return new AlunoDto(true, "Aluno removido com sucesso");
	        }

	        // Retornando o indicativo de erro
	        return new AlunoDto(false, "Erro no processo de remoção");
	    }
	}

