package br.edu.opet.interdisciplinardois.jsf.javabean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import br.edu.opet.interdisciplinardois.controller.CursoController;
import br.edu.opet.interdisciplinardois.dto.CursoDto;
import br.edu.opet.interdisciplinardois.model.Curso;

@ViewScoped
@ManagedBean(name = "CursoVB")
public class CursoJavaBean {
	
	 // Atributos - Valores dos componentes visuais
    private Integer        id;
    private String         nome;
    private String         nomeCoordenador;
    private boolean        edicao;
    private String         tela;
    private List<Curso> listaCurso;

    @PostConstruct
    public void init()
    {
        Curso tCurso = (Curso) FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestMap().get("Curso");
        if (tCurso != null)
        {
            id = tCurso.getId();
            nome = tCurso.getNome();
            nomeCoordenador = tCurso.getNomeCoordenador();
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

    public String getNomeCoordenador()
    {
        return nomeCoordenador;
    }

    public void setNomeCoordenador(String pNomeCoordenador)
    {
        nomeCoordenador = pNomeCoordenador;
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

    public List<Curso> getListaCurso()
    {
        return listaCurso;
    }

    public void setListaCurso(List<Curso> pListaCurso)
    {
        listaCurso = pListaCurso;
    }

    // Métodos da Controller
    public String limpar()
    {
        id = null;
        nome = null;
        nomeCoordenador = null;
        edicao = false;

        return tela;
    }

    public String cadastrar()
    {
        System.out.println("CursoVB - Cadastrar : " + this);

        Curso tCurso = new Curso();
        tCurso.setNome(nome);
        tCurso.setNomeCoordenador(nomeCoordenador);
        

        CursoController tController = new CursoController();

        CursoDto tDto = tController.cadastrarCurso(tCurso);
        if (tDto.isOk())
        {
            // Ok, incluído
            id = tDto.getCurso().getId();

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
        System.out.println("CursoVB - Alterar : " + this);
        Curso tCurso = new Curso();
        tCurso.setId(id);
        tCurso.setNome(nome);
        tCurso.setNomeCoordenador(nomeCoordenador);

        CursoController tController = new CursoController();

        CursoDto tDto = tController.atualizarCurso(tCurso);
        if (tDto.isOk())
        {
            // Ok, alterado
            id = tDto.getCurso().getId();

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
        System.out.println("CursoVB - Consultar : " + this);

        CursoController tController = new CursoController();

        CursoDto tDto = tController.recuperarCurso(id);
        if (tDto.isOk())
        {
            // Ok, recuperado
        	Curso tCurso = tDto.getCurso();
            id = tCurso.getId();
            nome = tCurso.getNome();
            nomeCoordenador = tCurso.getNomeCoordenador();

            // indicando que a pesquisa deu certo
            edicao = true;

            // Passando o obejto para outra instância de ViewBean, se necessário
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CURSO", tCurso);
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
        System.out.println("CursoVB - Excluir : " + this);

        CursoController tController = new CursoController();

        CursoDto tDto = tController.removeCurso(id);
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
        System.out.println("CursoVB - Pesquisar : " + this);

        CursoController tController = new CursoController();

        CursoDto tDto = tController.pesquisarCursoPorNome(nome);
        if (tDto.isOk())
        {
            // Ok, recuperado
            listaCurso = tDto.getLista();
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
        tBuilder.append(nomeCoordenador);
        tBuilder.append("]");
        return tBuilder.toString();
    }
}



