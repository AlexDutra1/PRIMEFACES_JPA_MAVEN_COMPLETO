package prova02_Maven_PRIMEFACES;

import java.math.BigDecimal;
import java.util.Date;

import br.com.prova.modelo.Pessoa;
import br.com.prova.negocios.PessoaNegocios;

public class TestePessoaNegocios {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pessoa a1=new Pessoa();
		a1.setNome("AZULE");
		a1.setNasc(new Date());
		a1.setDepend(33);
		a1.setSalario(new BigDecimal("900.00"));
				
		PessoaNegocios teste=new PessoaNegocios();
		boolean result=teste.controleValidacao(a1);
		System.out.println("RESULTADO DA CLASSE DE VALIDACAO: "+result);
		
	}

}
