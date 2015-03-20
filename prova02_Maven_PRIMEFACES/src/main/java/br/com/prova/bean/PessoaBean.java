package br.com.prova.bean;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.prova.modelo.Pessoa;
import br.com.prova.negocios.PessoaNegocios;
import br.com.prova.persistencia.JPAUtil;

@ApplicationScoped
@ManagedBean
public class PessoaBean {

	private Pessoa Pessoa = new Pessoa();
	private List <Pessoa> variasPessoas;
	

	@SuppressWarnings("unchecked")
	public List<Pessoa> getVariasPessoas() {
		
		EntityManager em=JPAUtil.getConexao();
				Query consulta=em.createQuery("from Pessoa",Pessoa.class);
		variasPessoas=consulta.getResultList();
		
		return variasPessoas;
	}

	public Pessoa getPessoa() {
		return Pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		Pessoa = pessoa;
	}
	
	//////////////////////////////////////////////////////////
	//CRUDS
	//////////////////////////////////////////////////////////
	public void cadastrar() {

		PessoaNegocios pn=new PessoaNegocios();
		if(pn.controleValidacao(Pessoa)==true){
			
			//grave no banco
			EntityManager em = JPAUtil.getConexao();
			EntityTransaction tx = em.getTransaction();

			tx.begin();

			em.persist(Pessoa);
			tx.commit();

			em.close();
			
			// EXIBE MENSAGEM DE CADASTRO EFETUADO
			FacesContext contextCadast= FacesContext.getCurrentInstance();
			contextCadast.addMessage(null, new FacesMessage("CADASTRO EFETUADO"));
						
		}else{
			
			FacesContext contextCadast= FacesContext.getCurrentInstance();
			contextCadast.addMessage(null, new FacesMessage("ALGO NÃO FOI VALIDADO"));
			//algo não foi validado
		}

	}
	
	public String preparaEdicao(Pessoa pessoaAlt){
		
		this.Pessoa=pessoaAlt;
		
		return "editar.xhtml";
	}
	
	public String gravaEdicao(){
		
		EntityManager em = JPAUtil.getConexao();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.merge(Pessoa);
		tx.commit();

		em.close();
		
		return "listar.xhtml";
	}
	
	public void excluir(Pessoa pessoaExcl){
		
		EntityManager em = JPAUtil.getConexao();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		pessoaExcl=em.merge(pessoaExcl);
		em.remove(pessoaExcl);
		
		tx.commit();

		em.close();
		
	}
	//////////////////////////////////////////////////////////
	public void limpaCampos(){
	
		this.Pessoa=new Pessoa();
		this.algumasPessoas=null;
		
	}	
	//////////////////////////////////////////////////////////
	//CONSULTAS INDIVIDUAIS
	//////////////////////////////////////////////////////////
	private List <Pessoa> algumasPessoas;
	
	public void setAlgumasPessoas(List<Pessoa> algumasPessoas) {
		this.algumasPessoas = algumasPessoas;
	}

	public List<Pessoa> getAlgumasPessoas() {
		return algumasPessoas;
	}

	public void consultaPNome(){
		
		EntityManager em=JPAUtil.getConexao();
		Query consulta = em.createQuery("select a from Pessoa a where nome='"
				+ Pessoa.getNome() + "'", Pessoa.class);
		algumasPessoas=consulta.getResultList();
	}
	
	public void consultaDepend(){
		
		EntityManager em=JPAUtil.getConexao();
		Query consulta = em.createQuery("select a from Pessoa a where depend='"
				+ Pessoa.getDepend() + "'", Pessoa.class);
		algumasPessoas=consulta.getResultList();
	}
	
	public void consultaSalario(){
		
		EntityManager em=JPAUtil.getConexao();
		Query consulta = em.createQuery("select a from Pessoa a where salario='"
				+ Pessoa.getSalario() + "'", Pessoa.class);
		algumasPessoas=consulta.getResultList();
	}
	
	public void consultaNasc(){
		
		EntityManager em=JPAUtil.getConexao();
		Query consulta = em.createQuery("select a from Pessoa a where nasc='"
				+ Pessoa.getNasc() + "'", Pessoa.class);
		algumasPessoas=consulta.getResultList();
	
	}
}
