package br.edu.opet.interdisciplinardois.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.opet.interdisciplinardois.dto.CursoDto;
import br.edu.opet.interdisciplinardois.dao.AlunoDao;
import br.edu.opet.interdisciplinardois.dao.CursoDao;
import br.edu.opet.interdisciplinardois.model.Curso;

public class CursoController {
	
	public CursoDto cadastrarCurso(Curso pCurso)
    {
        // Verificar as informa��es
        if (pCurso == null)
        {
            return new CursoDto(false, "Tentativa de inclus�o de curso nulo");
        }

        // Criando o objeto de persist�ncia
        CursoDao tDao = new CursoDao();

        // Verificando se o curso j� existe
        Curso tCurso = tDao.recovery(pCurso.getId());
        if (tCurso != null)
        {
            return new CursoDto(false, "J� existe esse curso com o id informado");
        }

        // Incluindo o paciente
        tCurso = tDao.create(pCurso);
        if (tCurso == null)
        {
            return new CursoDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso inclu�do com sucesso", tCurso);
    }

    public CursoDto recuperarCurso(int pId)
    {
        // Verificar as informa��es
        if (pId <= 0)
        {
            return new CursoDto(false, "Identificador do curso inv�lido");
        }

        // Criando o objeto de persist�ncia
        CursoDao tDao = new CursoDao();

        // Recuperando o curso
        Curso tCurso = tDao.recovery(pId);
        if (tCurso == null)
        {
            return new CursoDto(false, "N�o existe curso com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso recuperado com sucesso", tCurso);
    }

    public CursoDto atualizarCurso(Curso pCurso)
    {
        // Verificar as informa��es
        if (pCurso == null)
        {
            return new CursoDto(false, "Tentativa de atualiza��o de curso nulo");
        }

        // Criando o objeto de persist�ncia
       CursoDao tDao = new CursoDao();

        // Recuperando o curso
        Curso tCurso = tDao.recovery(pCurso.getId());
        if (tCurso == null)
        {
            return new CursoDto(false, "N�o existe curso com o identificador informado");
        }

        if (!pCurso.getNome().equals(tCurso.getNome()))
        {
            // Verificando se existe um paciente com o novo nome
            tCurso = tDao.recoveryByNome(pCurso.getNome());
            if (tCurso != null)
            {
                return new CursoDto(false, "J� existe curso com o nome informado");
            }
        }

        // Atualizando o curso
        tCurso = tDao.update(pCurso);
        if (tCurso == null)
        {
            return new CursoDto(false, "N�o existe curso com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso alterado com sucesso", tCurso);
    }

    public CursoDto removeCurso(int pId)
    {
        // Verificar as informa��es
        if (pId <= 0)
        {
            return new CursoDto(false, "Identificador do curso inv�lido");
        }

        // Criando o objeto de persist�ncia
        AlunoDao tDaoAluno = new AlunoDao();

        // Verificando se o aluno j� existe
        int tQtde = tDaoAluno.countByCurso(pId);
        if (tQtde != 0)
        {
            return new CursoDto(false, "N�o � possivel remover o curso, aluno j� est� cadastrado no curso. Remo��o pro�bida");
        }

        // Criando o objeto de persist�ncia
        CursoDao tDao = new CursoDao();

        // Incluindo o paciente
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new CursoDto(true, "Curso removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new CursoDto(false, "Erro no processo de remo��o");
    }

    public CursoDto pesquisarCursoPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Curso> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
       CursoDao tDao = new CursoDao();

        // Recuperando o paciente
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Lista de cursos recuperada com sucesso", tLista);
    }

}
