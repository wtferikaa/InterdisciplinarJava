package br.edu.opet.interdisciplinardois.controller;


import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import br.edu.opet.interdisciplinardois.dao.AlunoDao;

import br.edu.opet.interdisciplinardois.dao.DepartamentosOpetDao;
import br.edu.opet.interdisciplinardois.dao.IdeiaDao;

import br.edu.opet.interdisciplinardois.dto.IdeiaDto;
import br.edu.opet.interdisciplinardois.model.Aluno;

import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;


public class IdeiaController {

	  public IdeiaDto criarIdeia(Ideia pIdeia)
	    {
		  
		// Verificar as informa��es
	        if (pIdeia == null)
	        {
	            return new IdeiaDto(false, "Tentativa de inclus�o de ideia nula");
	        }
	        //Verificando a data do cadastro
	        //comparar a data apenas 
	        LocalDate tDataAtual = LocalDate.now(); 
	       
	        if  (pIdeia.getDataCadastro().isBefore(tDataAtual))
	        {
	            return new IdeiaDto(false, "Data de cadastro anterior a data atual");
	        }
	        
	     // Criando os objetos DAO
	        AlunoDao tDaoAluno = new AlunoDao();
	        DepartamentosOpetDao tDaoDepartamentosOpet = new DepartamentosOpetDao();
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        // Validando se os identificadores existem na base de dados
	        Aluno tAluno = tDaoAluno.recovery(pIdeia.getIdAluno());
	        if (tAluno == null)
	        {
	            return new IdeiaDto(false, "N�o existe aluno com o identificador informado");
	        }
	        DepartamentosOpet tDepartamentosOpet = tDaoDepartamentosOpet.recovery(pIdeia.getIdDepartamentosOpet());
	        if (tDepartamentosOpet == null)
	        {
	            return new IdeiaDto(false, "N�o existe departamento com o identificador informado");
	        }
	        
	     // Incluindo a ideia
	        Ideia tIdeia = tDaoIdeia.create(pIdeia);
	        if (tIdeia == null)
	        {
	            return new IdeiaDto(false, "Erro no processo de inclus�o");
	        }
	        

	        // Retornando o indicativo de sucesso
	        return new IdeiaDto(true, "Ideia inclu�da com sucesso", tIdeia);
	    }

	    public IdeiaDto recuperarIdeia(int pIdIdeia)
	    {
	        // Verificar as informa��es
	        if (pIdIdeia <= 0)
	        {
	            return new IdeiaDto(false, "Identificador da ideia inv�lido");
	        }

	        // Criando os objetos DAO
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        // Validando se os identificadores existem na base de dados
	        Ideia tIdeia = tDaoIdeia.recovery(pIdIdeia);
	        if (tIdeia == null)
	        {
	            return new IdeiaDto(false, "N�o existe ideia com o identificador informado");
	        }

	        // Retornando o indicativo de sucesso
	        return new IdeiaDto(true, "Ideiaa recuperada com sucesso", tIdeia);
	    }
	    
	    public IdeiaDto atualizarIdeia(Ideia pIdeia)
	    {
	    	 // Verificar as informa��es
            if (pIdeia == null)
            {
                return new IdeiaDto(false, "Tentativa de atualiza��o de ideia nula");
            }

            // Criando o objeto de persist�ncia
           IdeiaDao tDao = new IdeiaDao();

            // Recuperando o aluno
            Ideia tIdeia = tDao.recovery(pIdeia.getId());
            if (tIdeia == null)
            {
                return new IdeiaDto(false, "N�o existe ideia com o identificador informado");
            }

            if (!pIdeia.getNome().equals(tIdeia.getNome()))
            {
                // Verificando se existe um paciente com o novo nome
                tIdeia = tDao.recoveryByNome(pIdeia.getNome());
                if (tIdeia != null)
                {
                    return new IdeiaDto(false, "J� existe ideia com o nome informado");
                }
            }

            //Atualizando a ideia
            tIdeia = tDao.update(pIdeia);
            if (tIdeia == null)
            {
                return new IdeiaDto(false, "N�o existe ideia com o identificador informado");
            }

            // Retornando o indicativo de sucesso
            return new IdeiaDto(true, "Ideia alterado com sucesso", tIdeia);
        }
	    
	    
	    public IdeiaDto deletarIdeia(int pIdIdeia)
	    {
	        // Verificar as informa��es
	        if (pIdIdeia <= 0)
	        {
	            return new IdeiaDto(false, "Identificador da ideia inv�lido");
	        }

	        // Criando os objetos DAO
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        // Validando se os identificadores existem na base de dados
	        Ideia tIdeia = tDaoIdeia.recovery(pIdIdeia);
	        if (tIdeia == null)
	        {
	            return new IdeiaDto(false, "N�o existe ideia com o identificador informado");
	        }

	        // Removendo a ideia
	        if (!tDaoIdeia.delete(pIdIdeia))
	        {
	            return new IdeiaDto(false, "Erro no processo de remo��o");
	        }

	        // Retornando o indicativo de sucesso
	        return new IdeiaDto(true, "Ideia deletada com sucesso", tIdeia);
	    }
	    public IdeiaDto pesquisarIdeiaPorAluno(int pIdAluno)
	    {
	        // Verificar as informa��es
	        if (pIdAluno <= 0)
	        {
	            return new IdeiaDto(false, "Identificador do aluno inv�lido");
	        }

	        // Criando os objetos DAO
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        List<Ideia> tLista =tDaoIdeia.searchByIdAluno(pIdAluno);

	        // Retornar a lista
	        return new IdeiaDto(true, "Lista de ideias recuperada com sucesso", tLista);
	    }

	    public IdeiaDto pesquisarIdeiaPorDepartamento(int pIdDepartamentosOpet)
	    {
	        // Verificar as informa��es
	        if (pIdDepartamentosOpet <= 0)
	        {
	            return new IdeiaDto(false, "Identificador do departamento inv�lido");
	        }

	        // Criando os objetos DAO
	        IdeiaDao tDaoIdeia = new IdeiaDao();

	        List<Ideia> tLista =tDaoIdeia.searchByIdDepartamentosOpet(pIdDepartamentosOpet);

	        // Retornar a lista
	        return new IdeiaDto(true, "Lista de ideias recuperada com sucesso", tLista);
	    }
	    public IdeiaDto pesquisarIdeiaPorNome(String pNome)
	    {
	        // Criando a lista de retorno
	        List<Ideia> tLista = new ArrayList<>();

	        // Criando o objeto de persist�ncia
	       IdeiaDao tDao = new IdeiaDao();

	        // Recuperando o paciente
	        tLista = tDao.searchByNome(pNome);

	        // Retornando o indicativo de sucesso
	        return new IdeiaDto(true, "Lista de ideias por nome recuperada com sucesso", tLista);
	    }
	    
	    
	    public IdeiaDto pesquisarIdeia() {
			// Criando a lista de retorno
			List<Ideia> tLista = new ArrayList<>();

			// Criando o objeto de persist�ncia
			IdeiaDao tDao = new IdeiaDao();

			// Recuperando o Venda
			tLista = tDao.search();

			// Retornando o indicativo de sucesso
			return new IdeiaDto(true, "Lista de ideias recuperada com sucesso", tLista);
		}

	}

