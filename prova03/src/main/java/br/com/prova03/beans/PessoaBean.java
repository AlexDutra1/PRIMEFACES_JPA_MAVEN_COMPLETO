package br.com.prova03.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.context.RequestContext;

import br.com.prova03.modelo.Pessoa;
import br.com.prova03.negocios.PessoaNegocios;
import br.com.prova03.persistencia.JPAUtil;


@ApplicationScoped
@ManagedBean
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();	
	private List <Pessoa> muitasPessoas;
	
	//GETTS AND SETTERS
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getMuitasPessoas() {
		return muitasPessoas;
	}

	//CRUD
	public void cadastrar(){
		
		PessoaNegocios validacaoPessoa=new PessoaNegocios();
		
		if(validacaoPessoa.controleValidacao(pessoa)==true){
		
			EntityManager em=JPAUtil.getConexao();
			EntityTransaction tx=em.getTransaction();
			tx.begin();
			
			em.persist(pessoa);
			
			tx.commit();
			em.close();
			
			this.limpaCampos();
			
			FacesContext contextCadastro=FacesContext.getCurrentInstance();
			contextCadastro.addMessage(null, new FacesMessage("CADASTRO EFETUADO COM SUCESSO"));
		
		}else{
			
			FacesContext contextCadastro=FacesContext.getCurrentInstance();
			contextCadastro.addMessage(null, new FacesMessage("ALGO NÃO FOI VALIDADO"));
				
		}
	}
	
	public String prepararEdicao(Pessoa pessoaAlt){
		
		this.pessoa=pessoaAlt;
		
		return "editar.xhtml";
	}
	
	public String gravaEdicao(){
		
		EntityManager em=JPAUtil.getConexao();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		em.merge(pessoa);
		
		tx.commit();
		em.close();
		
		FacesContext contextCadastro=FacesContext.getCurrentInstance();
		contextCadastro.addMessage(null, new FacesMessage("O REGISTRO FOI ATUALIZADO"));		
		
		return null;
	}
	
	public String excluir(Pessoa pessoaExcl){
		
		EntityManager em=JPAUtil.getConexao();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		System.out.println(""+pessoaExcl.getNome());
		
		pessoaExcl=em.merge(pessoaExcl);
		em.remove(pessoaExcl);
		
		tx.commit();
		em.close();
		
		limpaCampos();
		listaTodos();
		
		FacesContext contextCadastro=FacesContext.getCurrentInstance();
		contextCadastro.addMessage(null, new FacesMessage("O REGISTRO "+pessoa.getNome()+" FOI EXLCUIDO"));				
		
		//Atualiza tabela
		RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));		
		
		return null;
	}
	
	public String listaTodos(){
		
		if(muitasPessoas==null){
			
			EntityManager em=JPAUtil.getConexao();
			Query consulta=em.createQuery("select a from Pessoa a",Pessoa.class);
			muitasPessoas=consulta.getResultList();
			
			//Atualiza tabela
			RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
		}
		
		return "listar.xhtml";
	}
	
	//CONSULTAS INDIVIDUAIS
	public String consultaPNome(){
		
		if(muitasPessoas==null){
			
			EntityManager em=JPAUtil.getConexao();
			Query consulta=em.createQuery("select a from Pessoa a where nome='"+pessoa.getNome()+"'",Pessoa.class);
			muitasPessoas=consulta.getResultList();
			
			//Atualiza tabela
			RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
		
		}	
		
		return null;
	}
	
	public String consultaPDepend(){
		
		if(muitasPessoas==null){
			
			EntityManager em=JPAUtil.getConexao();
			Query consulta=em.createQuery("select a from Pessoa a where dependentes='"+pessoa.getDependentes()+"'",Pessoa.class);
			muitasPessoas=consulta.getResultList();
			
			//Atualiza tabela
			RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
			
		}
		
		return null;
	}
	
	public String consultaPSalario(){
		
		if(muitasPessoas==null){
			
			EntityManager em=JPAUtil.getConexao();
			Query consulta=em.createQuery("select a from Pessoa a where salario='"+pessoa.getSalario()+"'",Pessoa.class);
			muitasPessoas=consulta.getResultList();
			
			//Atualiza tabela
			RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
		
		}		
		
		return null;
	}

	public String consultaPNascimento(){
	
		if(muitasPessoas==null){
			
			EntityManager em=JPAUtil.getConexao();
			Query consulta=em.createQuery("select a from Pessoa a where nascimento='"+pessoa.getNascimento()+"'",Pessoa.class);
			muitasPessoas=consulta.getResultList();
	
			//Atualiza tabela
			RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
	
		}
		
	return null;
}
	
	public void limpaCampos(){
		
		this.pessoa=new Pessoa();
		this.muitasPessoas=null;
		
		//Atualiza databela e panelgrid
		RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:tabelaPessoa"));
		RequestContext.getCurrentInstance().update(Arrays.asList("formPessoa:pConsultas"));
	}



}
