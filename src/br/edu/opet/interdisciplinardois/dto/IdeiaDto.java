package br.edu.opet.interdisciplinardois.dto;

import java.util.List;

import br.edu.opet.interdisciplinardois.model.Ideia;

public class IdeiaDto {
	
    private boolean        ok;
    private String         mensagem;
    private Ideia          ideia;
    private List<Ideia>    lista;

    public IdeiaDto()
    {

    }

    public IdeiaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public IdeiaDto(boolean pOk, String pMensagem, Ideia pIdeia)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        ideia = pIdeia;
    }

    public IdeiaDto(boolean pOk, String pMensagem, List<Ideia> pLista)
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

    public Ideia getMedico()
    {
        return ideia;
    }

    public void setIdeia(Ideia pIdeia)
    {
        ideia = pIdeia;
    }

    public List<Ideia> getLista()
    {
        return lista;
    }

    public void setLista(List<Ideia> pLista)
    {
        lista = pLista;
    }


}
