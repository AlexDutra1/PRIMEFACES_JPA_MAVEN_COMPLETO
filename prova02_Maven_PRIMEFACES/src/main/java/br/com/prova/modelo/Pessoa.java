package br.com.prova.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private int depend;
	@Column(precision=19,scale=4)
	private BigDecimal salario;
	@Temporal(TemporalType.DATE)
	private Date nasc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDepend() {
		return depend;
	}
	public void setDepend(int depend) {
		this.depend = depend;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Date getNasc() {
		return nasc;
	}
	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}
	
	
	
	
	
	
	
	
	
	
}
