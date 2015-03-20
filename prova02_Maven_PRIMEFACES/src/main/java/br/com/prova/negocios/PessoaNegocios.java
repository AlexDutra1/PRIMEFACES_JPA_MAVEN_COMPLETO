package br.com.prova.negocios;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.prova.modelo.Pessoa;

public class PessoaNegocios {

	private boolean validou;
	
	public boolean isValidou() {
		return validou;
	}
	public void setValidou(boolean validou) {
		this.validou = validou;
	}
	
	//Nao pode ter mais de 100 dependentes e nem negativos
	public boolean validanDepentes(int dependentes){
		
		if(dependentes>=0 & dependentes<=100){
			return true;	
		}
		else{
			FacesContext contextCadas= FacesContext.getCurrentInstance();
			contextCadas.addMessage(null, new FacesMessage("NO MAXIMO 100 DEPENDENTES"));
			return false;
		}	
	}
	//Verifica se salario é menor que salario minimo
	public boolean validaSalarioMin(BigDecimal salario){
		
		if(salario.compareTo(new BigDecimal("788.00"))<0){
			FacesContext contexCadas=FacesContext.getCurrentInstance();
			contexCadas.addMessage(null, new FacesMessage("VALOR PRECISA SER NO MINIMO UM SALARIO"));
			return false;
		}
		else{
			return true;
		}
		
	}
	
	//VERIFICA SE DATA É DO PASSADO
	public boolean validaNasc(Date data){
	
		boolean res=data.before(new Date());
		if(res==true){
			
			return true;
		}else{
			FacesContext contextCadast=FacesContext.getCurrentInstance();
			contextCadast.addMessage(null, new FacesMessage("DATA PRECISA SER NO PASSADO"));
			
			return false;
		}
		
	}
	
	//VERIFICA SE O REGISTRO JA EXISTE NO BANCO
	
	//ESSE METODO RETORNA TRUE SE TUDO FOR VALIDADO CORRETAMENTE
	public boolean controleValidacao(Pessoa pesValidar){
		
		//LISTA DE METODOS VALIDADORES
		boolean result1=this.validanDepentes(pesValidar.getDepend());
		boolean result2=this.validaSalarioMin(pesValidar.getSalario());
		boolean result3=this.validaNasc(pesValidar.getNasc());
		
		//VERIFICA SE TODOS METODOS FORAM VALIDADOS
		if(result1==true & result2==true & result3==true){
			this.setValidou(true);
		}else{
			this.setValidou(false);
		}
		
		return this.isValidou();
	}
	
}
