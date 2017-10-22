package br.edu.opet.interdisciplinardois.dto;

import java.util.List;

import br.edu.opet.interdisciplinardois.model.Aluno;

public class AlunoDto {

	   private boolean        ok;
	    private String         mensagem;
	    private Aluno          aluno;
	    private List<Aluno>    lista;

	    public AlunoDto()
	    {

	    }

	    public AlunoDto(boolean pOk, String pMensagem)
	    {
	        super();
	        ok = pOk;
	        mensagem = pMensagem;
	    }

	    public AlunoDto(boolean pOk, String pMensagem, Aluno pAluno)
	    {
	        super();
	        ok = pOk;
	        mensagem = pMensagem;
	        aluno = pAluno;
	    }

	    public AlunoDto(boolean pOk, String pMensagem, List<Aluno> pLista)
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

	    public Aluno getAluno()
	    {
	        return aluno;
	    }

	    public void setAluno(Aluno pAluno)
	    {
	        aluno = pAluno;
	    }

	    public List<Aluno> getLista()
	    {
	        return lista;
	    }

	    public void setLista(List<Aluno> pLista)
	    {
	        lista = pLista;
	    }

}
