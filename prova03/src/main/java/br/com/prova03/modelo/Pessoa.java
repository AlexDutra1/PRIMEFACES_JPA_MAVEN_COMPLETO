package br.com.prova03.modelo;

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
	private int idade;
	@Temporal(TemporalType.DATE)
	private Date datPagamento;
	@Column(precision=5,scale=2)
	private BigDecimal salario;

	public Pessoa() {
	}

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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Date getDatPagamento() {
		return datPagamento;
	}

	public void setDatPagamento(Date datPagamento) {
		this.datPagamento = datPagamento;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}