package br.edu.opet.interdisciplinardois.dto;

import java.util.List;

import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;

public class DepartamentosOpetDto {
	
	private boolean                    ok;
    private String                     mensagem;
    private DepartamentosOpet          departamentosOpet;
    private List<DepartamentosOpet>    lista;

    public DepartamentosOpetDto()
    {

    }

    public DepartamentosOpetDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public DepartamentosOpetDto(boolean pOk, String pMensagem, DepartamentosOpet pDepartamentosOpet)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        departamentosOpet = pDepartamentosOpet;
    }

    public DepartamentosOpetDto(boolean pOk, String pMensagem, List<DepartamentosOpet> pLista)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        lista = pLista;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean pOk)
    {
        ok = pOk;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String pMensagem)
    {
        mensagem = pMensagem;
    }

    public DepartamentosOpet getDepartamentosOpet()
    {
        return departamentosOpet;
    }

    public void setDepartamentosOpet(DepartamentosOpet pDepartamentosOpet)
    {
        departamentosOpet = pDepartamentosOpet;
    }

    public List<DepartamentosOpet> getLista()
    {
        return lista;
    }

    public void setLista(List<DepartamentosOpet> pLista)
    {
        lista = pLista;
    }



}
