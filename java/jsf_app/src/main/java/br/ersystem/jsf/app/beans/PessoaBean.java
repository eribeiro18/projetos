package br.ersystem.jsf.app.beans;

import br.ersystem.jsf.app.beans.dto.EnderecoDto;
import br.ersystem.jsf.app.beans.dto.PessoaDto;
import br.ersystem.jsf.app.beans.mapper.EnderecoMapperImpl;
import br.ersystem.jsf.app.beans.mapper.PessoaMapperImpl;
import br.ersystem.jsf.app.domain.Endereco;
import br.ersystem.jsf.app.domain.Pessoa;
import br.ersystem.jsf.app.util.JsfUtilities;
import br.ersystem.jsf.app.facade.PessoaFacade;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class PessoaBean extends JsfUtilities implements Serializable {
    public static final long serialVersionUID = 1l;
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private UserTransaction userTransaction;
    private final PessoaFacade pessoaFacade = new PessoaFacade();
    private final PessoaMapperImpl pessoaMapper = new PessoaMapperImpl();
    private final EnderecoMapperImpl enderecoMapper = new EnderecoMapperImpl();
    private Pessoa pessoa;
    private PessoaDto pessoaDto;
    private Endereco endereco;
    private String idade;
    private List<Pessoa> pessoasCollection;
    private Boolean permitirExclusao = Boolean.FALSE;
    private Boolean permitirEdicao = Boolean.FALSE;

    @PostConstruct
    public void initialize() {
        String pathRequest = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
        if (pathRequest.contains("filtro")) {
            permitirExclusao = Boolean.TRUE;
        }
        if (pathRequest.contains("edit")) {
            permitirEdicao = Boolean.TRUE;
        }
        this.carregarListaUsuarios();
    }

    private void carregarListaUsuarios() {
        this.pessoaFacade.setEntityManager(this.entityManager);
        this.getPessoasCollection().clear();
        this.setPessoasCollection(this.pessoaFacade.findAll());
    }

    public void carregarDadosUsuario(Pessoa pessoa) {
        this.setPessoa(pessoa);
        this.setEndereco(pessoa.getEndereco());

        this.setPessoaDto(this.pessoaMapper.toDto(pessoa));
        this.getPessoaDto().setEnderecoDto(this.enderecoMapper.toDto(pessoa.getEndereco()).getEnderecoDto());

        if (this.getPessoaDto().getIdade() != null)
            this.pessoaDto.setIdade(new SimpleDateFormat("dd/MM/yyyy").format(this.getPessoa().getIdade()));
        showMessage(FacesMessage.SEVERITY_WARN, "Atenção", String.format("Usuário %s selecionado!", this.getPessoa().getNome()));
    }

    private void inserirNovoUsuario() throws Exception {
        if(!this.getIdade().equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date age = sdf.parse(this.getIdade());
            this.getPessoa().setIdade(age);
        }
        this.getEndereco().setPessoa(this.getPessoa());
        this.getPessoa().setEndereco(this.getEndereco());

        this.pessoaFacade.merge(this.getPessoa());
        this.carregarListaUsuarios();
        showMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa registrada com sucesso!");
    }

    private void atualizarUsuario() throws Exception {
        Pessoa pessoaEntity = this.pessoaMapper.toEntity(this.getPessoaDto());
        Endereco enderecoEtity = this.enderecoMapper.toEntity(this.getPessoaDto());

        this.setPessoa(pessoaEntity);
        this.setEndereco(enderecoEtity);

        String msg = this.pessoaFacade.actionValidationUpdateForm(this.getPessoa());
        if(msg.equals(""))msg = this.pessoaFacade.actionValidationUpdateForm(this.getEndereco());

        if(msg.equals("")) {
            this.getPessoa().setEndereco(enderecoEtity);
            this.getEndereco().setPessoa(this.getPessoa());

            this.pessoaFacade.merge(this.getPessoa());
            this.carregarListaUsuarios();
            showMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa alterada com sucesso!");
        }else{
            showMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg);
        }
    }

    public void salvar(int mode) {
        try {
            this.pessoaFacade.setEntityManager(this.entityManager);
            this.pessoaFacade.setUserTransaction(this.userTransaction);

            if (mode == 0) {//Insert mode
                this.inserirNovoUsuario();
            } else {
                this.atualizarUsuario();
            }
        } catch (Exception ex) {
            showMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro no registro da pessoa. [Erro] = " + ex.getMessage());
        }
    }

    public void remover(Pessoa pessoa) {
        try {
            this.setPessoa(pessoa);
            this.pessoaFacade.setEntityManager(this.entityManager);
            this.pessoaFacade.setUserTransaction(this.userTransaction);
            this.pessoaFacade.delete(this.getPessoa());
            this.carregarListaUsuarios();

            showMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa excluída com sucesso!");
        } catch (Exception ex) {
            showMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na exclusão da pessoa. [Erro] = " + ex.getMessage());
        }
    }

    public void buscarPessoas() {
        try {
            Pessoa p = this.pessoaMapper.toEntity(this.pessoaDto);
            Endereco e = this.enderecoMapper.toEntity(this.pessoaDto);

            this.getPessoasCollection().clear();
            this.pessoaFacade.setEntityManager(this.entityManager);
            this.setPessoasCollection(this.pessoaFacade.buscarPorFiltros(p, e));
        } catch (Exception ex) {
            this.showMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro na conversao de dados. [Erro] = " + ex.getMessage());
        }
    }

    public Pessoa getPessoa() {
        if (this.pessoa == null) {
            this.pessoa = new Pessoa();
            this.pessoa.setEndereco(new Endereco());
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        if (this.endereco == null) {
            this.endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getIdade() {
        if (this.idade == null) {
            this.idade = "";
        }
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public List<Pessoa> getPessoasCollection() {
        if (this.pessoasCollection == null) {
            this.pessoasCollection = new ArrayList<>();
        }
        return pessoasCollection;
    }

    public void setPessoasCollection(List<Pessoa> pessoasCollection) {
        this.pessoasCollection = pessoasCollection;
    }

    public Boolean getPermitirExclusao() {
        return permitirExclusao;
    }

    public void setPermitirExclusao(Boolean permitirExclusao) {
        this.permitirExclusao = permitirExclusao;
    }

    public Boolean getPermitirEdicao() {
        return permitirEdicao;
    }

    public void setPermitirEdicao(Boolean permitirEdicao) {
        this.permitirEdicao = permitirEdicao;
    }

    public PessoaDto getPessoaDto() {
        if (this.pessoaDto == null) {
            this.pessoaDto = new PessoaDto();
            this.pessoaDto.setEnderecoDto(new EnderecoDto());
        }
        return pessoaDto;
    }

    public void setPessoaDto(PessoaDto pessoaDto) {
        this.pessoaDto = pessoaDto;
    }

    public PessoaFacade getPessoaFacade() {
        return pessoaFacade;
    }
}
