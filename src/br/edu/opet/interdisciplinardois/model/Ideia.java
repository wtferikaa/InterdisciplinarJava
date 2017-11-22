package br.edu.opet.interdisciplinardois.model;



import java.time.LocalDate;

public class Ideia {
	//Atributos
	private int id;
	private String nome;
	private String descricaoProblema;
	private String recomendacao;
	private LocalDate dataCadastro;
	private LocalDate dataAnalise;
	private Boolean aprovado;
	private String resposta;
	private int idAluno;
	private int idDepartamentosOpet;
	
	
	
    //Construtores
	public Ideia() {
		super();
		
	}


	public Ideia(int id, String nome, String descricaoProblema, String recomendacao, LocalDate dataCadastro,
			LocalDate dataAnalise, Boolean aprovado, String resposta, int idAluno, int idDepartamentosOpet) {
		super();
		setId(id);
		setNome(nome);
		setDescricaoProblema(descricaoProblema);
		setRecomendacao(recomendacao);
		setDataCadastro(dataCadastro);
		setDataAnalise(dataAnalise);
		setAprovado(aprovado);
		setResposta(resposta);
		setIdAluno(idAluno);
		setIdDepartamentosOpet(idDepartamentosOpet);
	}

    //Métodos de acesso 
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


	public String getDescricaoProblema() {
		return descricaoProblema;
	}


	public void setDescricaoProblema(String pDescricaoProblema) {
		descricaoProblema = pDescricaoProblema;
	}


	public String getRecomendacao() {
		return recomendacao;
	}


	public void setRecomendacao(String pRecomendacao) {
		recomendacao = pRecomendacao;
	}


	

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public LocalDate getDataAnalise() {
		return dataAnalise;
	}


	public void setDataAnalise(LocalDate dataAnalise) {
		this.dataAnalise = dataAnalise;
	}


	public Boolean getAprovado() {
		return aprovado;
	}


	public void setAprovado(Boolean pAprovado) {
		aprovado = pAprovado;
	}


	public String getResposta() {
		return resposta;
	}


	public void setResposta(String pResposta) {
		resposta = pResposta;
	}

	public int getIdAluno() {
		return idAluno;
	}


	public void setIdAluno(int pIdAluno) {
		idAluno = pIdAluno;
	}


	public int getIdDepartamentosOpet() {
		return idDepartamentosOpet;
	}


	public void setIdDepartamentosOpet(int pIdDepartamentosOpet) {
		idDepartamentosOpet = pIdDepartamentosOpet;
	}


	
	
	//Métodos Gerais 
	
	@Override
	public String toString() {
		
		
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append("[");
		tBuilder.append(getId());
		tBuilder.append(", ");
		tBuilder.append(getNome());
		tBuilder.append(", ");
		tBuilder.append(getDescricaoProblema());
		tBuilder.append(", ");
		tBuilder.append(getRecomendacao());
		tBuilder.append(", ");
		tBuilder.append(getDataCadastro());
		tBuilder.append(", ");
		tBuilder.append(getDataAnalise());
		tBuilder.append(", ");
		tBuilder.append(getAprovado());
		tBuilder.append(", ");
		tBuilder.append(getResposta());
		tBuilder.append(", ");
		tBuilder.append(getIdAluno());
		tBuilder.append(", ");
		tBuilder.append(getIdDepartamentosOpet());
		tBuilder.append("]");
		return tBuilder.toString();
	}




}

