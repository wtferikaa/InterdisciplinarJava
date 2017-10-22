package br.edu.opet.interdisciplinardois.controller;



import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;
import br.edu.opet.interdisciplinardois.model.Aluno;

public class AlunoController {
	public AlunoDto cadastrarAluno(Aluno pAluno)
    {
        // Verificar as informa��es
        if (pAluno == null)
        {
            return new AlunoDto(false, "Tentativa de inclus�o de aluno nulo");
        }
        
     // Criando o objeto de persist�ncia
        AlunoDao tDao = new AlunoDao();

        // Verificando se o paciente j� existe
        Aluno tAluno = tDao.recovery(pAluno.getId());
        if (tAluno != null)
        {
            return new AlunoDto(false, "J� existe este aluno cadastrado");
        }
        
        // Incluindo o paciente
        tAluno = tDao.create(pAluno);
        if (tAluno == null)
        {
            return new AlunoDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new AlunoDto(true, "Aluno inclu�do com sucesso", tAluno);
    }
	
	 public AlunoDto atualizarAluno(Aluno pAluno)
	    {
	        // Verificar as informa��es
	        if (pAluno == null)
	        {
	            return new AlunoDto(false, "Tentativa de atualiza��o de aluno nulo");
	        }

	        // Criando o objeto de persist�ncia
	        AlunoDao tDao = new AlunoDao();

	        // Recuperando o paciente
	        Aluno tAluno = tDao.recovery(pAluno.getId());
	        if (tAluno == null)
	        {
	            return new AlunoDto(false, "N�o existe aluno com o identificador informado");
	        }

	        if (pAluno.getId() != tAluno.getId())
	        {
	            // Verificando se existe um paciente com o novo Id
	            tAluno = tDao.recovery(pAluno.getId());
	            if (tAluno != null)
	            {
	                return new AlunoDto(false, "J� existe aluno com o id/matr�cula informado");
	            }
	        }

	        // Atualizando o aluno
	        tAluno = tDao.update(pAluno);
	        if (tAluno == null)
	        {
	            return new AlunoDto(false, "N�o existe aluno com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new AlunoDto(true, "Aluno alterado com sucesso", tAluno);
	    }

	    public AlunoDto removeAluno(int pId)
	    {
	        // Verificar as informa��es
	        if (pId <= 0)
	        {
	            return new AlunoDto(false, "Identificador do aluno inv�lido");
	        }

	        // Criando o objeto de persist�ncia
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        // Verificando se o paciente j� existe
	        int tQtde = tDaoIdeia.countByAluno(pId); //tem que criar isso ainda 
	        if (tQtde != 0)
	        {
	            return new AlunoDto(false, "Aluno j� tem ideias no sistema. Remo��o pro�bida");
	        }

	        // Criando o objeto de persist�ncia
	        AlunoDao tDao = new AlunoDao();

	        // Incluindo o paciente
	        if (tDao.delete(pId))
	        {
	            // Retornando o indicativo de sucesso
	            return new AlunoDto(true, "Aluno removido com sucesso");
	        }

	        // Retornando o indicativo de erro
	        return new AlunoDto(false, "Erro no processo de remo��o");
	    }
	}

