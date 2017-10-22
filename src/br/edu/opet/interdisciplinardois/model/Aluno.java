package br.edu.opet.interdisciplinardois.model;

public class Aluno extends Usuario {
	// Atributos
	private long telefone;
	private String turno;
	private String turma;
	private int idCurso;

	// Construtores

	public Aluno() {
		super();

	}

	public Aluno(int pId, String pEmail, String pSenha, String pNome, long pTelefone, String pTurno, String pTurma, int pIdCurso) 
	{
		super(pId, pEmail, pSenha, pNome);
		setTelefone(pTelefone);
		setTurno(pTurno);
		setTurma(pTurma);
		setIdCurso(pIdCurso);
	}

	// Métodos de acesso

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long pTelefone) {
		telefone = pTelefone;
	}


	public String getTurno() {
		return turno;
	}

	public void setTurno(String pTurno) {
		turno = pTurno;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String pTurma) {
		turma = pTurma;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int pIdCurso) {
		idCurso = pIdCurso;
	}

	// Métodos gerais

	@Override
	public String toString() {
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append("[");
		tBuilder.append(super.toString());
		tBuilder.append(", ");
		tBuilder.append(getTelefone());
		tBuilder.append(", ");
		tBuilder.append(getTurno());
		tBuilder.append(", ");
		tBuilder.append(getTurma());
		tBuilder.append(", ");
		tBuilder.append(getIdCurso());
		tBuilder.append("]");
		return tBuilder.toString();
	}

}
