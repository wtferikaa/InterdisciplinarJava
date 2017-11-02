package br.edu.opet.interdisciplinardois.controller;




import java.util.ArrayList;
import java.util.List;

import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;

import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;

import br.edu.opet.interdisciplinardois.model.Aluno;
import br.edu.opet.interdisciplinardois.model.Curso;


public class AlunoController {
	

	public AlunoDto criarAluno(Aluno pAluno)
    {
		
		// Verificar as informa��es
        if (pAluno == null)
        {
            return new AlunoDto(false, "Tentativa de inclus�o de aluno nulo");
        }
      
        // Criando os objetos DAO
        CursoDao tDaoCurso = new CursoDao();
        AlunoDao tDaoAluno = new AlunoDao();
    
     // Validando se os identificadores existem na base de dados
       Curso tCurso = tDaoCurso.recovery(pAluno.getIdCurso());
        if (tCurso == null)
        {
            return new AlunoDto(false, "N�o existe curso com o identificador informado");
        }
        
     // Incluindo o aluno
        Aluno tAluno = tDaoAluno.create(pAluno);
        if (tAluno == null)
        {
            return new AlunoDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new AlunoDto(true, "Aluno inclu�do com sucesso", tAluno);
    }
        
        public AlunoDto recuperarAluno(int pId)
        {
            // Verificar as informa��es
            if (pId <=0)
            {
                return new AlunoDto(false, "Identificador do aluno inv�lido");
            }

            // Criando o objeto de persist�ncia
            AlunoDao tDao = new AlunoDao();

            // Recuperando o aluno
            Aluno tAluno = tDao.recovery(pId);
            if (tAluno == null)
            {
                return new AlunoDto(false, "N�o existe aluno com o identificador informado");
            }

            // Retornando o indicativo de sucesso
            return new AlunoDto(true, "Aluno recuperado com sucesso", tAluno);
        }
        
        public AlunoDto atualizarAluno (Aluno pAluno)
        {
        	 // Verificar as informa��es
            if (pAluno == null)
            {
                return new AlunoDto(false, "Tentativa de atualiza��o de aluno nulo");
            }

            // Criando o objeto de persist�ncia
           AlunoDao tDao = new AlunoDao();

            // Recuperando o aluno
            Aluno tAluno = tDao.recovery(pAluno.getId());
            if (tAluno == null)
            {
                return new AlunoDto(false, "N�o existe aluno com o identificador informado");
            }

            if (!pAluno.getNome().equals(tAluno.getNome()))
            {
                // Verificando se existe um paciente com o novo nome
                tAluno = tDao.recoveryByNome(pAluno.getNome());
                if (tAluno != null)
                {
                    return new AlunoDto(false, "J� existe aluno com o nome informado");
                }
            }

            // Atualizando o aluno
            tAluno = tDao.update(pAluno);
            if (tAluno == null)
            {
                return new AlunoDto(false, "N�o existe aluno com o identificador informado");
            }

            // Retornando o indicativo de sucesso
            return new AlunoDto(true, "aluno alterado com sucesso", tAluno);
        }
        
        public AlunoDto removeAluno(int pId)
        {
            // Verificar as informa��es
            if (pId <=0)
            {
                return new AlunoDto(false, "Identificador do aluno inv�lido");
            }

            // Criando o objeto de persist�ncia
            IdeiaDao tDaoIdeia = new IdeiaDao();

            // Verificando se o aluno ja existe
            int tQtde = tDaoIdeia.countByAluno(pId);
            if (tQtde != 0)
            {
                return new AlunoDto(false, "Aluno j� tem ideias no sistema. Remo��o pro�bida");
            }

            // Criando o objeto de persist�ncia
            AlunoDao tDao = new AlunoDao();

            // Incluindo o medico
            if (tDao.delete(pId))
            {
                // Retornando o indicativo de sucesso
                return new AlunoDto(true, "Aluno removido com sucesso");
            }

            // Retornando o indicativo de erro
            return new AlunoDto(false, "Erro no processo de remo��o");
        }
        public AlunoDto pesquisarAlunoPorCurso(int pIdCurso)
        {
            // Verificar as informa��es
            if (pIdCurso <= 0)
            {
                return new AlunoDto(false, "Identificador do curso inv�lido");
            }

            // Criando os objetos DAO
            AlunoDao tDaoAluno = new AlunoDao();

            List<Aluno> tLista =tDaoAluno.searchByIdCurso(pIdCurso);

            // Retornar a lista
            return new AlunoDto(true, "Lista de alunos recuperada com sucesso", tLista);
        }
        
        public AlunoDto pesquisarAlunosPorNome(String pNome)
        {
            // Criando a lista de retorno
            List<Aluno> tLista = new ArrayList<>();

            // Criando o objeto de persist�ncia
            AlunoDao tDao = new AlunoDao();

            // Recuperando o paciente
            tLista = tDao.searchByNome(pNome);

            // Retornando o indicativo de sucesso
            return new AlunoDto(true, "Lista de alunos por nome recuperada com sucesso", tLista);
        }

        
        
    }     
    