package br.edu.opet.interdisciplinardois.jsf.javabean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.opet.interdisciplinardois.controller.IdeiaController;
import br.edu.opet.interdisciplinardois.dto.IdeiaDto;
import br.edu.opet.interdisciplinardois.model.Ideia;



@ViewScoped
@ManagedBean(name = "IdeiaVB")
public class IdeiaJavaBean {

	
	// Atributos - Valores dos componentes visuais
	private Integer        id;
	private String         nome;
	private String         descricaoProblema;
	private String         recomendacao;
	private Date      dataCadastro;
	private Date      dataAnalise;
	private Boolean        aprovado;
	private String         resposta;
    private boolean        edicao;
    private String         tela;
    private List<Ideia>    listaIdeia;

    @PostConstruct
    public void init()
    {
        Ideia tIdeia = (Ideia) FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestMap().get("Ideia");
        if (tIdeia != null)
        {
            id = tIdeia.getId();
            nome = tIdeia.getNome();
            descricaoProblema = tIdeia.getDescricaoProblema();
            recomendacao = tIdeia.getRecomendacao();
            dataCadastro = java.sql.Date.valueOf(tIdeia.getDataCadastro());
            dataAnalise = java.sql.Date.valueOf(tIdeia.getDataAnalise());
            aprovado = tIdeia.getAprovado();
            resposta = tIdeia.getResposta();
            edicao = true;
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer pId)
    {
        id = pId;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public String getDescricaoProblema()
    {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String pDescricaoProblema)
    {
        descricaoProblema = pDescricaoProblema;
    }

    public String getRecomendacao()
    {
        return recomendacao;
    }

    public void setRecomendacao(String pRecomendacao)
    {
        recomendacao = pRecomendacao;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date pDataCadastro)
    {
        dataCadastro = pDataCadastro;
    }

    public Date getDataAnalise()
    {
        return dataAnalise;
    }

    public void setDataAnalise(Date pDataAnalise)
    {
        dataAnalise = pDataAnalise;
    }

    public Boolean getAprovado()
    {
        return aprovado;
    }

    public void setAprovado(Boolean pAprovado)
    {
        aprovado = pAprovado;
    }
    
    public String getResposta()
    {
        return resposta;
    }

    public void setResposta(String pResposta)
    {
        resposta = pResposta;
    }

    public boolean isEdicao()
    {
        return edicao;
    }

    public void setEdicao(boolean pEdicao)
    {
        edicao = pEdicao;
    }

    public String getTela()
    {
        return tela;
    }

    public void setTela(String pTela)
    {
        tela = pTela;
    }

    public List<Ideia> getListaIdeia()
    {
        return listaIdeia;
    }

    public void setListaIdeia(List<Ideia> pListaIdeia)
    {
        listaIdeia = pListaIdeia;
    }

    // Métodos da Controller
    public String limpar()
    {
        id = null;
        nome = null;
        descricaoProblema = null;
        recomendacao = null;
        dataCadastro = null;
        dataAnalise = null;
        aprovado = null;
        resposta = null;
        edicao = false;

        return tela;
    }

    public String cadastrar()
    {
        System.out.println("IdeiaVB - Cadastrar : " + this);

        Ideia tIdeia = new Ideia();
        tIdeia.setNome(nome);
        tIdeia.setDescricaoProblema(descricaoProblema);
        tIdeia.setRecomendacao(recomendacao);
        LocalDate tDataCadastro = new java.sql.Date(dataCadastro.getTime()).toLocalDate();
        tIdeia.setDataCadastro(tDataCadastro);
        LocalDate tDataAnalise = new java.sql.Date(dataAnalise.getTime()).toLocalDate();
        tIdeia.setDataAnalise(tDataAnalise);
        tIdeia.setAprovado(aprovado);
        tIdeia.setResposta(resposta);

        IdeiaController tController = new IdeiaController();

        IdeiaDto tDto = tController.criarIdeia(tIdeia);
        if (tDto.isOk())
        {
            // Ok, incluído
            id = tDto.getIdeia().getId();

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
        }
        else
        {
            // Erro de inclusão

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
        return null;
    }

    public String alterar()
    {
        System.out.println("PIdeiaVB - Alterar : " + this);

        Ideia tIdeia = new Ideia();
        tIdeia.setNome(nome);
        tIdeia.setDescricaoProblema(descricaoProblema);
        tIdeia.setRecomendacao(recomendacao);
        LocalDate tDataCadastro = new java.sql.Date(dataCadastro.getTime()).toLocalDate();
        tIdeia.setDataCadastro(tDataCadastro);
        LocalDate tDataAnalise = new java.sql.Date(dataAnalise.getTime()).toLocalDate();
        tIdeia.setDataAnalise(tDataAnalise);
        tIdeia.setAprovado(aprovado);
        tIdeia.setResposta(resposta);
        
        IdeiaController tController = new IdeiaController();

        IdeiaDto tDto = tController.atualizarIdeia(tIdeia);
        if (tDto.isOk())
        {
            // Ok, alterado
            id = tDto.getIdeia().getId();

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
        }
        else
        {
            // Erro de alteração

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }
        return null;
    }

    public String consultar()
    {
        System.out.println("IdeiaVB - Consultar : " + this);

        IdeiaController tController = new IdeiaController();

        IdeiaDto tDto = tController.recuperarIdeia(id);
        if (tDto.isOk())
        {
            // Ok, recuperado
            Ideia tIdeia = tDto.getIdeia();
            tIdeia.setNome(nome);
            tIdeia.setDescricaoProblema(descricaoProblema);
            tIdeia.setRecomendacao(recomendacao);
            LocalDate tDataCadastro = new java.sql.Date(dataCadastro.getTime()).toLocalDate();
            tIdeia.setDataCadastro(tDataCadastro);
            LocalDate tDataAnalise = new java.sql.Date(dataAnalise.getTime()).toLocalDate();
            tIdeia.setDataAnalise(tDataAnalise);
            tIdeia.setAprovado(aprovado);
            tIdeia.setResposta(resposta);
            
            // indicando que a pesquisa deu certo
            edicao = true;

            // Passando o obejto para outra instância de ViewBean, se necessário
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ideia", tIdeia);
        }
        else
        {
            // Erro de consulta
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }

        return tela;
    }

    public String excluir()
    {
        System.out.println("IdeiaVB - Excluir : " + this);

        IdeiaController tController = new IdeiaController();

        IdeiaDto tDto = tController.deletarIdeia(id);
        if (tDto.isOk())
        {
            // Ok, exluido
            limpar();

            // indicando que a pesquisa deu certo
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

        }
        else
        {
            // Erro de consulta
            edicao = false;

            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }

        return null;
    }

    public String pesquisar()
    {
        System.out.println("IdeiaVB - Pesquisar : " + this);

        IdeiaController tController = new IdeiaController();

        IdeiaDto tDto = tController.pesquisarIdeiaPorNome(nome);
        if (tDto.isOk())
        {
            // Ok, recuperado
            listaIdeia = tDto.getLista();
        }
        else
        {
            // Colocando a mensagem do sistema
            FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
        }

        return null;
    }

    // Métodos Gerais
    @Override
    public String toString()
    {
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


