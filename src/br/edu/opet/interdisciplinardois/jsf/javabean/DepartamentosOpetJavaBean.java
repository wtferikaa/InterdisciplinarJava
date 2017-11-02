package br.edu.opet.interdisciplinardois.jsf.javabean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.opet.interdisciplinardois.controller.DepartamentosOpetController;
import br.edu.opet.interdisciplinardois.dto.DepartamentosOpetDto;
import br.edu.opet.interdisciplinardois.model.DepartamentosOpet;



	@ViewScoped
	@ManagedBean(name = "DepartamentosOpetVB")
	public class DepartamentosOpetJavaBean
	{
	    // Atributos - Valores dos componentes visuais
	    private Integer        id;
	    private String         email;
	    private String         senha;
	    private String         nome;
	    private boolean        edicao;
	    private String         tela;
	    private List<DepartamentosOpet> listaDepartamentosOpet;

	    @PostConstruct
	    public void init()
	    {
	        DepartamentosOpet tDepartamentosOpet = (DepartamentosOpet) FacesContext.getCurrentInstance().getExternalContext()
	                        .getRequestMap().get("DEPARTAMENTOSOPET");
	        if (tDepartamentosOpet!= null)
	        {
	            id = tDepartamentosOpet.getId();
	            email = tDepartamentosOpet.getEmail();
	            senha = tDepartamentosOpet.getSenha();
	            nome = tDepartamentosOpet.getNome();
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

	    public String getEmail()
	    {
	        return email;
	    }

	    public void setEmail(String pEmail)
	    {
	        email = pEmail;
	    }

	    public String getSenha()
	    {
	        return senha;
	    }

	    public void setSenha(String pSenha)
	    {
	        senha = pSenha;
	    }

	    public String getNome()
	    {
	        return nome;
	    }

	    public void setNome(String pNome)
	    {
	        nome = pNome;
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

	    public List<DepartamentosOpet> getListaDepartamentosOpet()
	    {
	        return listaDepartamentosOpet;
	    }

	    public void setListaDepartamentosOpet(List<DepartamentosOpet> pListaDepartamentosOpet)
	    {
	        listaDepartamentosOpet = pListaDepartamentosOpet;
	    }
	    

	    // Métodos da Controller
	    public String limpar()
	    {
	        id = null;
	        email = null;
	        senha = null;
	        nome = null;

	        return tela;
	    }

	    public String cadastrar()
	    {
	        System.out.println("DepartamentosOpetVB - Cadastrar : " + this);

	        DepartamentosOpet tDepartamentosOpet = new DepartamentosOpet();
	        tDepartamentosOpet.setEmail(email);
	        tDepartamentosOpet.setSenha(senha);
	        tDepartamentosOpet.setNome(nome);
	        

	        DepartamentosOpetController tController = new DepartamentosOpetController();

	        DepartamentosOpetDto tDto = tController.cadastrarDepartamentosOpet(tDepartamentosOpet);
	        if (tDto.isOk())
	        {
	            // Ok, incluído
	            id = tDto.getDepartamentosOpet().getId();

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
	        System.out.println("DepartamentosOpetVB - Alterar : " + this);

	        DepartamentosOpet tDepartamentosOpet = new DepartamentosOpet();
	        tDepartamentosOpet.setId(id);
	        tDepartamentosOpet.setEmail(email);
	        tDepartamentosOpet.setSenha(senha);
	        tDepartamentosOpet.setNome(nome);

	        DepartamentosOpetController tController = new DepartamentosOpetController();

	        DepartamentosOpetDto tDto = tController.atualizarDepartamento(tDepartamentosOpet);
	        if (tDto.isOk())
	        {
	            // Ok, alterado
	            id = tDto.getDepartamentosOpet().getId();

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
	        System.out.println("DepartamentosOpetVB - Consultar : " + this);

	        DepartamentosOpetController tController = new DepartamentosOpetController();

	        DepartamentosOpetDto tDto = tController.recuperarDepartamento(id);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	        	DepartamentosOpet tDepartamentosOpet = tDto.getDepartamentosOpet();
	            id = tDepartamentosOpet.getId();
	            email = tDepartamentosOpet.getEmail();
	            senha = tDepartamentosOpet.getSenha();
	            nome = tDepartamentosOpet.getNome();
	            

	            // indicando que a pesquisa deu certo
	            edicao = true;

	            // Passando o obejto para outra instância de ViewBean, se necessário
	            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DEPARTAMENTOSOPET", tDepartamentosOpet);
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
	        System.out.println("DepartamentosOpetVB - Excluir : " + this);

	        DepartamentosOpetController tController = new DepartamentosOpetController();

	        DepartamentosOpetDto tDto = tController.removeDepartamentosOpet(id);
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
	        System.out.println("DepartamentosOpetVB - Pesquisar : " + this);

	        DepartamentosOpetController tController = new DepartamentosOpetController();

	        DepartamentosOpetDto tDto = tController.pesquisarDepartamentoPorNome(nome);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            listaDepartamentosOpet = tDto.getLista();
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
	        tBuilder.append(email);
	        tBuilder.append(", ");
	        tBuilder.append(senha);
	        tBuilder.append(", ");
	        tBuilder.append(nome);
	        tBuilder.append("]");
	        return tBuilder.toString();

}
	}

