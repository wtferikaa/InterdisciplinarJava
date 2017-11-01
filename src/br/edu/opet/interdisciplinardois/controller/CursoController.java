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
        // Verificar as informações
        if (pCurso == null)
        {
            return new CursoDto(false, "Tentativa de inclusão de curso nulo");
        }

        // Criando o objeto de persistência
        CursoDao tDao = new CursoDao();

        // Verificando se o curso já existe
        Curso tCurso = tDao.recovery(pCurso.getId());
        if (tCurso != null)
        {
            return new CursoDto(false, "Já existe esse curso com o id informado");
        }

        // Incluindo o paciente
        tCurso = tDao.create(pCurso);
        if (tCurso == null)
        {
            return new CursoDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso incluído com sucesso", tCurso);
    }

    public CursoDto recuperarCurso(int pId)
    {
        // Verificar as informações
        if (pId <= 0)
        {
            return new CursoDto(false, "Identificador do curso inválido");
        }

        // Criando o objeto de persistência
        CursoDao tDao = new CursoDao();

        // Recuperando o curso
        Curso tCurso = tDao.recovery(pId);
        if (tCurso == null)
        {
            return new CursoDto(false, "Não existe curso com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso recuperado com sucesso", tCurso);
    }

    public CursoDto atualizarCurso(Curso pCurso)
    {
        // Verificar as informações
        if (pCurso == null)
        {
            return new CursoDto(false, "Tentativa de atualização de curso nulo");
        }

        // Criando o objeto de persistência
       CursoDao tDao = new CursoDao();

        // Recuperando o curso
        Curso tCurso = tDao.recovery(pCurso.getId());
        if (tCurso == null)
        {
            return new CursoDto(false, "Não existe curso com o identificador informado");
        }

        if (!pCurso.getNome().equals(tCurso.getNome()))
        {
            // Verificando se existe um paciente com o novo nome
            tCurso = tDao.recoveryByNome(pCurso.getNome());
            if (tCurso != null)
            {
                return new CursoDto(false, "Já existe curso com o nome informado");
            }
        }

        // Atualizando o curso
        tCurso = tDao.update(pCurso);
        if (tCurso == null)
        {
            return new CursoDto(false, "Não existe curso com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Curso alterado com sucesso", tCurso);
    }

    public CursoDto removeCurso(int pId)
    {
        // Verificar as informações
        if (pId <= 0)
        {
            return new CursoDto(false, "Identificador do curso inválido");
        }

        // Criando o objeto de persistência
        AlunoDao tDaoAluno = new AlunoDao();

        // Verificando se o aluno já existe
        int tQtde = tDaoAluno.countByCurso(pId);
        if (tQtde != 0)
        {
            return new CursoDto(false, "Não é possivel remover o curso, aluno já está cadastrado no curso. Remoção proíbida");
        }

        // Criando o objeto de persistência
        CursoDao tDao = new CursoDao();

        // Incluindo o paciente
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new CursoDto(true, "Curso removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new CursoDto(false, "Erro no processo de remoção");
    }

    public CursoDto pesquisarCursoPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Curso> tLista = new ArrayList<>();

        // Criando o objeto de persistência
       CursoDao tDao = new CursoDao();

        // Recuperando o paciente
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new CursoDto(true, "Lista de cursos recuperada com sucesso", tLista);
    }

}
