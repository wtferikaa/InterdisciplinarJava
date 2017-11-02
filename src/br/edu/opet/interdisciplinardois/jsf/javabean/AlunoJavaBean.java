package br.edu.opet.interdisciplinardois.jsf.javabean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.opet.interdisciplinardois.controller.AlunoController;
import br.edu.opet.interdisciplinardois.dto.AlunoDto;
import br.edu.opet.interdisciplinardois.model.Aluno;

@ViewScoped
@ManagedBean(name = "AlunoVB")
public class AlunoJavaBean {
	
	 // Atributos - Valores dos componentes visuais
    private Integer        id;
    private String         email;
    private String         senha;
    private String         nome;
    private Long           telefone;
    private String         turno;
	private String         turma;
    private boolean        edicao;
    private String         tela;
    private List<Aluno> listaAluno;

    @PostConstruct
    public void init()
    {
        Aluno tAluno = (Aluno) FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestMap().get("ALUNO");
        if (tAluno != null)
        {
            id = tAluno.getId();
            email = tAluno.getEmail();
            senha = tAluno.getSenha();
            nome = tAluno.getNome();
            telefone = tAluno.getTelefone();
            turno = tAluno.getTurno();
            turma = tAluno.getTurma();
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

    public Long getTelefone()
    {
        return telefone;
    }

    public void setTelefone(Long pTelefone)
    {
        telefone = pTelefone;
    }

    
    public String getTurno()
    {
        return turno;
    }

    public void setTurno(String pTurno)
    {
        turno = pTurno;
    }
    
    public String getTurma()
    {
        return turma;
    }

    public void setTurma(String pTurma)
    {
        turma = pTurma;
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

    public List<Aluno> getListaAluno()
    {
        return listaAluno;
    }

    public void setListaAluno(List<Aluno> pListaAluno)
    {
        listaAluno = pListaAluno;
    }

    // Métodos da Controller
    public String limpar()
    {
        id = null;
        email = null;
        senha = null;
        nome = null;
        telefone = null;
        turno = null;
        turma = null;
        edicao = false;

        return tela;
    }

    public String cadastrar()
    {
        System.out.println("AlunoVB - Cadastrar : " + this);

        Aluno tAluno = new Aluno();
        tAluno.setEmail(email);
        tAluno.setSenha(senha);
        tAluno.setNome(nome);
        tAluno.setTelefone(telefone);
        tAluno.setTurno(turno);
        tAluno.setTurma(turma);

        AlunoController tController = new AlunoController();

        AlunoDto tDto = tController.criarAluno(tAluno);
        if (tDto.isOk())
        {
            // Ok, incluído
            id = tDto.getAluno().getId();

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
        System.out.println("AlunoVB - Alterar : " + this);

        Aluno tAluno = new Aluno();
        tAluno.setId(id);
        tAluno.setEmail(email);
        tAluno.setSenha(senha);
        tAluno.setNome(nome);
        tAluno.setTelefone(telefone);
        tAluno.setTurno(turno);
        tAluno.setTurma(turma);

        AlunoController tController = new AlunoController();

        AlunoDto tDto = tController.atualizarAluno(tAluno);
        if (tDto.isOk())
        {
            // Ok, alterado
            id = tDto.getAluno().getId();

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
        System.out.println("AlunoVB - Consultar : " + this);

        AlunoController tController = new AlunoController();

        AlunoDto tDto = tController.recuperarAluno(id);
        if (tDto.isOk())
        {
            // Ok, recuperado
            Aluno tAluno = tDto.getAluno();
            id = tAluno.getId();
            email = tAluno.getEmail();
            senha = tAluno.getSenha();
            nome = tAluno.getNome();
            telefone = tAluno.getTelefone();
            turno = tAluno.getTurno();
            turma = tAluno.getTurma();

            // indicando que a pesquisa deu certo
            edicao = true;

            // Passando o obejto para outra instância de ViewBean, se necessário
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Aluno", tAluno);
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
        System.out.println("AlunoVB - Excluir : " + this);

        AlunoController tController = new AlunoController();

        AlunoDto tDto = tController.removeAluno(id);
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
        System.out.println("AlunoVB - Pesquisar : " + this);

        AlunoController tController = new AlunoController();

        AlunoDto tDto = tController.pesquisarAlunosPorNome(nome);
        if (tDto.isOk())
        {
            // Ok, recuperado
            listaAluno = tDto.getLista();
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
        tBuilder.append(", ");
        tBuilder.append(telefone);
        tBuilder.append(", ");
        tBuilder.append(turno);
        tBuilder.append(", ");
        tBuilder.append(turma);
        tBuilder.append("]");
        return tBuilder.toString();
    }
}



