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
        // Verificar as informa��es
        if (pDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Tentativa de inclus�o de departamento nulo");
        }

        // Criando o objeto de persist�ncia
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Verificando se o departamento j� existe
        DepartamentosOpet tDepartamentosOpet = tDao.recoveryByNome(pDepartamentosOpet.getNome());
        if (tDepartamentosOpet != null)
        {
            return new DepartamentosOpetDto(false, "J� existe esse departamento com o nome informado");
        }

        // Incluindo o departamento
        tDepartamentosOpet = tDao.create(pDepartamentosOpet);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Departamento inclu�do com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto recuperarDepartamento(int pId)
    {
        // Verificar as informa��es
        if (pId <= 0)
        {
            return new DepartamentosOpetDto(false, "Identificador do departamento inv�lido");
        }

        // Criando o objeto de persist�ncia
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o departamento
        DepartamentosOpet tDepartamentosOpet = tDao.recovery(pId);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "N�o existe departamento com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Departamento recuperado com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto atualizarDepartamento(DepartamentosOpet pDepartamentosOpet)
    {
        // Verificar as informa��es
        if (pDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "Tentativa de atualiza��o de departamento nulo");
        }

        // Criando o objeto de persist�ncia
       DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o paciente
        DepartamentosOpet tDepartamentosOpet = tDao.recovery(pDepartamentosOpet.getId());
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "N�o existe departamento com o identificador informado");
        }

        if (!pDepartamentosOpet.getNome().equals(tDepartamentosOpet.getNome()))
        {
            // Verificando se existe um paciente com o novo nome
            tDepartamentosOpet = tDao.recoveryByNome(pDepartamentosOpet.getNome());
            if (tDepartamentosOpet != null)
            {
                return new DepartamentosOpetDto(false, "J� existe departamento com o nome informado");
            }
        }

        // Atualizando o paciente
        tDepartamentosOpet = tDao.update(pDepartamentosOpet);
        if (tDepartamentosOpet == null)
        {
            return new DepartamentosOpetDto(false, "N�o existe departamento com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "departamento alterado com sucesso", tDepartamentosOpet);
    }

    public DepartamentosOpetDto removeDepartamentosOpet(int pId)
    {
        // Verificar as informa��es
        if (pId <= 0)
        {
            return new DepartamentosOpetDto(false, "Identificador do departamento inv�lido");
        }

        // Criando o objeto de persist�ncia
        IdeiaDao tDaoIdeia = new IdeiaDao();

        // Verificando se o departamento j� existe na ideia
        int tQtde = tDaoIdeia.countByDepartamento(pId);
        if (tQtde != 0)
        {
            return new DepartamentosOpetDto(false, "departamento j� tem ideias no sistema. Remo��o pro�bida");
        }

        // Criando o objeto de persist�ncia
        DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Incluindo o departamento
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new DepartamentosOpetDto(true, "Departamento removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new DepartamentosOpetDto(false, "Erro no processo de remo��o");
    }

    public DepartamentosOpetDto pesquisarDepartamentoPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<DepartamentosOpet> tLista = new ArrayList<>();

        // Criando o objeto de persist�ncia
       DepartamentosOpetDao tDao = new DepartamentosOpetDao();

        // Recuperando o paciente
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new DepartamentosOpetDto(true, "Lista de departamentos recuperada com sucesso", tLista);
    }

}
