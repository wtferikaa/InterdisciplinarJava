package br.edu.opet.interdisciplinardois.jsf.javabean;

import java.time.LocalDate;
import java.util.Date;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.opet.interdisciplinardois.controller.AlunoController;
import br.edu.opet.interdisciplinardois.controller.DepartamentosOpetController;
import br.edu.opet.interdisciplinardois.controller.IdeiaController;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;
import br.edu.opet.interdisciplinardois.dto.DepartamentosOpetDto;
import br.edu.opet.interdisciplinardois.dto.IdeiaDto;
import br.edu.opet.interdisciplinardois.model.Aluno;

import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;
import br.edu.opet.interdisciplinardois.model.Ideia;

@ViewScoped
@ManagedBean(name = "IdeiaVB")
public class IdeiaJavaBean {

	// Atributos - Valores dos componentes visuais
	private Integer id;
	private Integer idAluno;
	private Integer idDepartamentosOpet;
	private String nome;
	private String nomeAluno;
	private String nomeDepartamentosOpet;
	private String descricaoProblema;
	private String recomendacao;
	private Date dataCadastro;
	private Date dataAnalise;
	private Boolean aprovado;
	private String resposta;
	private boolean edicao;
	private boolean edicaoAluno;
	private boolean edicaoDepartamentosOpet;
	private String tela;
	private List<Ideia> listaIdeia;
	private List<Aluno> listaAluno;
	private List<DepartamentosOpet> listaDepartamentosOpet;
	private Map<Integer, Aluno> mapaAluno;
	private Map<Integer, DepartamentosOpet> mapaDepartamentosOpet;

	@PostConstruct
	public void init() {
		Ideia tIdeia = (Ideia) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("Ideia");
		if (tIdeia != null) {
			id = tIdeia.getId();
			idAluno = tIdeia.getIdAluno();
			idDepartamentosOpet = tIdeia.getIdDepartamentosOpet();

			nome = tIdeia.getNome();
			descricaoProblema = tIdeia.getDescricaoProblema();
			recomendacao = tIdeia.getRecomendacao();
			dataCadastro = java.sql.Date.valueOf(tIdeia.getDataCadastro());
			dataAnalise = java.sql.Date.valueOf(tIdeia.getDataAnalise());
			aprovado = tIdeia.getAprovado();
			resposta = tIdeia.getResposta();
			edicao = true;

			AlunoController tAlunoController = new AlunoController();

			AlunoDto tAlunoDto = tAlunoController.recuperarAluno(idAluno);
			if (tAlunoDto.isOk()) {
				Aluno tAluno = tAlunoDto.getAluno();
				idAluno = tAluno.getId();
				nomeAluno = tAluno.getNome();
			} else {
				idAluno = null;
				nomeAluno = null;
			}

			DepartamentosOpetController tDepartamentosOpetController = new DepartamentosOpetController();

			DepartamentosOpetDto tDepartamentosOpetDto = tDepartamentosOpetController
					.recuperarDepartamento(idDepartamentosOpet);
			if (tDepartamentosOpetDto.isOk()) {
				DepartamentosOpet tDepartamentosOpet = tDepartamentosOpetDto.getDepartamentosOpet();
				idDepartamentosOpet = tDepartamentosOpet.getId();
				nomeDepartamentosOpet = tDepartamentosOpet.getNome();
			} else {
				idDepartamentosOpet = null;
				nomeDepartamentosOpet = null;
			}
		}

		AlunoController tAlunoController1 = new AlunoController();

		AlunoDto tDto = tAlunoController1.pesquisarAluno();
		if (tDto.isOk()) {
			// Ok, recuperado

			listaAluno = tDto.getLista();

		} else {
			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		DepartamentosOpetController tDepartamentosOpetController1 = new DepartamentosOpetController();

		DepartamentosOpetDto tDepartamentosOpetDto1 = tDepartamentosOpetController1.pesquisarDepartamentosOpet();
		if (tDepartamentosOpetDto1.isOk()) {
			// Ok, recuperado
			listaDepartamentosOpet = tDepartamentosOpetDto1.getLista();

		} else {
			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer pId) {
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date pDataCadastro) {
		dataCadastro = pDataCadastro;
	}

	public Date getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(Date pDataAnalise) {
		dataAnalise = pDataAnalise;
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

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean pEdicao) {
		edicao = pEdicao;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String pTela) {
		tela = pTela;
	}

	public List<Ideia> getListaIdeia() {
		return listaIdeia;
	}

	public void setListaIdeia(List<Ideia> pListaIdeia) {
		listaIdeia = pListaIdeia;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Integer getIdDepartamentosOpet() {
		return idDepartamentosOpet;
	}

	public void setIdDepartamentosOpet(Integer idDepartamentosOpet) {
		this.idDepartamentosOpet = idDepartamentosOpet;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getNomeDepartamentosOpet() {
		return nomeDepartamentosOpet;
	}

	public void setNomeDepartamentosOpet(String nomeDepartamentosOpet) {
		this.nomeDepartamentosOpet = nomeDepartamentosOpet;
	}

	public boolean isEdicaoAluno() {
		return edicaoAluno;
	}

	public void setEdicaoAluno(boolean edicaoAluno) {
		this.edicaoAluno = edicaoAluno;
	}

	public boolean isEdicaoDepartamentosOpet() {
		return edicaoDepartamentosOpet;
	}

	public void setEdicaoDepartamentosOpet(boolean edicaoDepartamentosOpet) {
		this.edicaoDepartamentosOpet = edicaoDepartamentosOpet;
	}

	public List<Aluno> getListaAluno() {
		return listaAluno;
	}

	public void setListaAluno(List<Aluno> listaAluno) {
		this.listaAluno = listaAluno;
	}

	public List<DepartamentosOpet> getListaDepartamentosOpet() {
		return listaDepartamentosOpet;
	}

	public void setListaDepartamentosOpet(List<DepartamentosOpet> listaDepartamentosOpet) {
		this.listaDepartamentosOpet = listaDepartamentosOpet;
	}

	public Map<Integer, Aluno> getMapaAluno() {
		return mapaAluno;
	}

	public void setMapaAluno(Map<Integer, Aluno> mapaAluno) {
		this.mapaAluno = mapaAluno;
	}

	public Map<Integer, DepartamentosOpet> getMapaDepartamentosOpet() {
		return mapaDepartamentosOpet;
	}

	public void setMapaDepartamentosOpet(Map<Integer, DepartamentosOpet> mapaDepartamentosOpet) {
		this.mapaDepartamentosOpet = mapaDepartamentosOpet;
	}

	// Métodos da Controller
	public String limpar() {
		id = null;
		idAluno = null;
		idDepartamentosOpet = null;
		nome = null;
		nomeAluno = null;
		nomeDepartamentosOpet = null;
		descricaoProblema = null;
		recomendacao = null;
		dataCadastro = null;
		dataAnalise = null;
		aprovado = null;
		resposta = null;
		edicao = false;
		edicaoAluno = false;
		edicaoDepartamentosOpet = false;

		return tela;
	}

	public String cadastrar() {
		System.out.println("IdeiaVB - Cadastrar : " + this);

		Ideia tIdeia = new Ideia();
		tIdeia.setIdAluno(idAluno);
		tIdeia.setIdDepartamentosOpet(idDepartamentosOpet);

		tIdeia.setNome(nome);
		tIdeia.setDescricaoProblema(descricaoProblema);
		tIdeia.setRecomendacao(recomendacao);
		LocalDate tDataCadastro = new java.sql.Date(dataCadastro.getTime()).toLocalDate();
		tIdeia.setDataCadastro(tDataCadastro);

		if (dataAnalise != null) {
			LocalDate tDataAnalise = new java.sql.Date(dataAnalise.getTime()).toLocalDate();
			tIdeia.setDataAnalise(tDataAnalise);
		}

		if (aprovado != null) {
			tIdeia.setAprovado(aprovado);
		}
		if (resposta != null) {
			tIdeia.setResposta(resposta);
		}
		
		IdeiaController tController = new IdeiaController();

		IdeiaDto tDto = tController.criarIdeia(tIdeia);
		if (tDto.isOk()) {
			// Ok, incluído
			id = tDto.getIdeia().getId();

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
		} else {
			// Erro de inclusão

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}
		return null;
	}

	public String alterar() {
		System.out.println("PIdeiaVB - Alterar : " + this);

		Ideia tIdeia = new Ideia();
		
	    tIdeia.setId(id);
		tIdeia.setNome(nome);
		tIdeia.setDescricaoProblema(descricaoProblema);
		tIdeia.setRecomendacao(recomendacao);
		LocalDate tDataCadastro = new java.sql.Date(dataCadastro.getTime()).toLocalDate();
		tIdeia.setDataCadastro(tDataCadastro);
		
		if (dataAnalise != null) {
			LocalDate tDataAnalise = new java.sql.Date(dataAnalise.getTime()).toLocalDate();
			tIdeia.setDataAnalise(tDataAnalise);
		}

		if (aprovado != null) {
			tIdeia.setAprovado(aprovado);
		}
		if (resposta != null) {
			tIdeia.setResposta(resposta);
		}
		
		tIdeia.setIdAluno(idAluno);
		tIdeia.setIdDepartamentosOpet(idDepartamentosOpet);

		
		

		IdeiaController tController = new IdeiaController();

		IdeiaDto tDto = tController.atualizarIdeia(tIdeia);
		if (tDto.isOk()) {
			// Ok, alterado
			id = tDto.getIdeia().getId();

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
		} else {
			// Erro de alteração

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}
		return null;
	}

	public String consultar() {
		System.out.println("IdeiaVB - Consultar : " + this);

		IdeiaController tController = new IdeiaController();

		IdeiaDto tDto = tController.recuperarIdeia(id);
		if (tDto.isOk()) {
			// Ok, recuperado

			Ideia tIdeia = tDto.getIdeia();
			id = tIdeia.getId();
			idAluno = tIdeia.getIdAluno();
			idDepartamentosOpet = tIdeia.getIdDepartamentosOpet();
			nome = tIdeia.getNome();
			descricaoProblema = tIdeia.getDescricaoProblema();
			recomendacao = tIdeia.getRecomendacao();
			dataCadastro = java.sql.Date.valueOf(tIdeia.getDataCadastro());
			if (tIdeia.getDataAnalise() != null) {
				dataAnalise = java.sql.Date.valueOf(tIdeia.getDataAnalise());
			}
			else 
				dataAnalise = null;
			
			aprovado = tIdeia.getAprovado();
			resposta = tIdeia.getResposta();
			
            // Recuperando o nome
			
			AlunoController tAlunoController = new AlunoController();

			AlunoDto tAlunoDto = tAlunoController.recuperarAluno(idAluno);
			if (tAlunoDto.isOk()) {
				// Ok, recuperado
				
				
				nomeAluno = tAlunoDto.getAluno().getNome();
				
				
				
			}
			else nomeAluno = null;
			
			
			DepartamentosOpetController tDepartamentosOpetController = new DepartamentosOpetController();

			DepartamentosOpetDto tDepartamentosOpetDto = tDepartamentosOpetController.recuperarDepartamento(idDepartamentosOpet);
			if (tDepartamentosOpetDto.isOk()) {
				// Ok, recuperado
				
				
				nomeDepartamentosOpet = tDepartamentosOpetDto.getDepartamentosOpet().getNome();
				
			}
			else nomeDepartamentosOpet = null;
			
            
			// indicando que a pesquisa deu certo
			edicao = true;

			// Passando o obejto para outra instância de ViewBean, se necessário
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ideia", tIdeia);
		} else {
			// Erro de consulta
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return tela;
	}

	public String excluir() {
		System.out.println("IdeiaVB - Excluir : " + this);

		IdeiaController tController = new IdeiaController();

		IdeiaDto tDto = tController.deletarIdeia(id);
		if (tDto.isOk()) {
			// Ok, exluido
			limpar();

			// indicando que a pesquisa deu certo
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

		} else {
			// Erro de consulta
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return null;
	}

	public String pesquisar() {
		System.out.println("IdeiaVB - Pesquisar : " + this);

		IdeiaController tController = new IdeiaController();

		IdeiaDto tDto = tController.pesquisarIdeiaPorNome(nome);
		if (tDto.isOk()) {
			// Ok, recuperado
			listaIdeia = tDto.getLista();
		} else {
			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return null;
	}

	// Métodos Gerais
	@Override
	public String toString() {
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append(" [");
		tBuilder.append(id);
		tBuilder.append(", ");
		tBuilder.append(nome);
		tBuilder.append(", ");
		tBuilder.append(descricaoProblema);
		tBuilder.append(", ");
		tBuilder.append(recomendacao);
		tBuilder.append(", ");
		tBuilder.append(dataCadastro);
		tBuilder.append(", ");
		tBuilder.append(dataAnalise);
		tBuilder.append(", ");
		tBuilder.append(aprovado);
		tBuilder.append(", ");
		tBuilder.append(resposta);
		tBuilder.append("]");
		return tBuilder.toString();
	}
}
