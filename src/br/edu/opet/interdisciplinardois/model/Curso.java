package br.edu.opet.interdisciplinardois.model;


public class Curso extends Usuario {

	// Atributos
	private String nomeCoordenador;

	// Construtores

	public Curso() {
		super();

	}

	public Curso(int pId, String pEmail, String pSenha, String pNome, String pNomeCoordenador) {
		super(pId, pEmail, pSenha, pNome);
		setNomeCoordenador(pNomeCoordenador);
	}

	// Métodos de Acesso
	
	public String getNomeCoordenador() {
		return nomeCoordenador;
	}

	public void setNomeCoordenador(String pNomeCoordenador) {
		nomeCoordenador = pNomeCoordenador;
	}

	// Métodos Gerais
	@Override
	public String toString() {
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append("[");
		tBuilder.append(super.toString());
		tBuilder.append(", ");
		tBuilder.append(getNomeCoordenador());
		tBuilder.append("]");
		return tBuilder.toString();
	}

}
