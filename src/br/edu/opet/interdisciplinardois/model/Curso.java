package br.edu.opet.interdisciplinardois.model;


public class Curso {

	// Atributos
	private int    id;
	private String nome;
	private String nomeCoordenador;

	// Construtores

	public Curso() {
		super();

	}

	public Curso(int id, String nome, String nomeCoordenador) {
		super();
		setId(id);
		setNome(nome);
		setNomeCoordenador(nomeCoordenador);
	}

	// Métodos de Acesso
	
	public int getId() {
		return id;
	}

	public void setId(int pId) {
		id = pId;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String pNome) {
		nome = pNome;
	}

	
	
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
		tBuilder.append(getId());
		tBuilder.append(", ");
		tBuilder.append(getNome());
		tBuilder.append(", ");
		tBuilder.append(getNomeCoordenador());
		tBuilder.append("]");
		return tBuilder.toString();
	}

}
