package br.com.prova03.beans;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.prova03.modelo.Pessoa;
import br.com.prova03.persistencia.JPAUtil;

@ManagedBean
public class PessoaBean {

	private Pessoa Pessoa = new Pessoa();
	private List <Pessoa> variasPessoas;
	
	public List<Pessoa> getVariasPessoas() {
		
		EntityManager em=JPAUtil.getConexao();
		Query consulta=em.createQuery("select a from Pessoa a",Pessoa.class);
		variasPessoas= consulta.getResultList();
		
		return variasPessoas;
	}

	public void consulta(){
		
		//variasPessoas=resultado da consulta
	}
	
	public Pessoa getPessoa() {

		return Pessoa;
	}

	public void setPessoa(Pessoa pessoa) {

		this.Pessoa = pessoa;
	}

	public String guardar() {
		
		EntityManager em = JPAUtil.getConexao();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		em.persist(Pessoa);

		tx.commit();
		em.close();
		
		
		
		return "index.xhtml";
	}
	
	public String prepararEdicao(Pessoa pessoaAlt){
		
		this.Pessoa=pessoaAlt;		
		
		return "edicao.xhtml";
	}
	
	public String excluir(Pessoa pessoaExcl){
		
		EntityManager em=JPAUtil.getConexao();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		pessoaExcl=em.merge(pessoaExcl);
		em.remove(pessoaExcl);
		
		tx.commit();
		
		em.close();
		
		return "index.xhtml";
	}
	
	public String gravarEdicao(){
		
		EntityManager em=JPAUtil.getConexao();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		em.merge(Pessoa);
		
		tx.commit();
		em.close();
		
		return "index.xhtml";
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
}
