package br.edu.opet.interdisciplinardois.dto;

import java.util.List;

import br.edu.opet.interdisciplinardois.model.Curso;

public class CursoDto {
	
	private boolean        ok;
    private String         mensagem;
    private Curso          curso;
    private List<Curso>    lista;

    public CursoDto()
    {

    }

    public CursoDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public CursoDto(boolean pOk, String pMensagem, Curso pCurso)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        curso = pCurso;
    }

    public CursoDto(boolean pOk, String pMensagem, List<Curso> pLista)
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

    public Curso getCurso()
    {
        return curso;
    }

    public void setCurso(Curso pCurso)
    {
        curso = pCurso;
    }

    public List<Curso> getLista()
    {
        return lista;
    }

    public void setLista(List<Curso> pLista)
    {
        lista = pLista;
    }


}
