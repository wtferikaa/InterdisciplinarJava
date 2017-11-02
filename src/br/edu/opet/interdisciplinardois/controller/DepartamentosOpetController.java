package br.edu.opet.interdisciplinardois.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;
import br.edu.opet.interdisciplinardois.dto.DepartamentosOpetDto;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;

public class DepartamentosOpetController {
	
	public DepartamentosOpetDto cadastrarDepartamentosOpet(DepartamentosOpet pDepartamentosOpet)
    {
        // Verificar as informações
        if (pDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Tentativa de inclusão de departamento nulo");
        }

        // Criando o objeto de persistência
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Verificando se o departamento já existe
        DepartamentosOpet tDepartamentosOpet = tDao.recoveryByNome(pDepartamentosOpet.getNome());
        if (tDepartamentosOpet != null)
        {
            return new DepartamentosOpetDto(false, "Já existe esse departamento com o nome informado");
        }

        // Incluindo o departamento
        tDepartamentosOpet = tDao.create(pDepartamentosOpet);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Departamento incluído com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto recuperarDepartamento(int pId)
    {
        // Verificar as informações
        if (pId <= 0)
        {
            return new DepartamentosOpetDto(false, "Identificador do departamento inválido");
        }

        // Criando o objeto de persistência
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o departamento
        DepartamentosOpet tDepartamentosOpet = tDao.recovery(pId);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Não existe departamento com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Departamento recuperado com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto atualizarDepartamento(DepartamentosOpet pDepartamentosOpet)
    {
        // Verificar as informações
        if (pDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Tentativa de atualização de departamento nulo");
        }

        // Criando o objeto de persistência
       DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o paciente
        DepartamentosOpet tDepartamentosOpet = tDao.recovery(pDepartamentosOpet.getId());
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Não existe departamento com o identificador informado");
        }

        if (!pDepartamentosOpet.getNome().equals(tDepartamentosOpet.getNome()))
        {
            // Verificando se existe um paciente com o novo nome
            tDepartamentosOpet = tDao.recoveryByNome(pDepartamentosOpet.getNome());
            if (tDepartamentosOpet != null)
            {
                return new DepartamentosOpetDto(false, "Já existe departamento com o nome informado");
            }
        }

        // Atualizando o paciente
        tDepartamentosOpet = tDao.update(pDepartamentosOpet);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Não existe departamento com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "departamento alterado com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto removeDepartamentosOpet(int pId)
    {
        // Verificar as informações
        if (pId <= 0)
        {
            return new DepartamentosOpetDto(false, "Identificador do departamento inválido");
        }

        // Criando o objeto de persistência
        IdeiaDao tDaoIdeia = new IdeiaDao();

        // Verificando se o departamento já existe na ideia
        int tQtde = tDaoIdeia.countByDepartamento(pId);
        if (tQtde != 0)
        {
            return new DepartamentosOpetDto(false, "departamento já tem ideias no sistema. Remoção proíbida");
        }

        // Criando o objeto de persistência
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Incluindo o departamento
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new DepartamentosOpetDto(true, "Departamento removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new DepartamentosOpetDto(false, "Erro no processo de remoção");
    }

    public DepartamentosOpetDto pesquisarDepartamentoPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<DepartamentosOpet> tLista = new ArrayList<>();

        // Criando o objeto de persistência
       DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o paciente
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Lista de departamentos recuperada com sucesso", tLista);
    }

}
