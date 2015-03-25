package prova03;

import java.math.BigDecimal;
import java.util.Date;

import br.com.prova03.beans.PessoaBean;
import br.com.prova03.modelo.Pessoa;

public class PessoaTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pessoa pes1=new Pessoa();
		pes1.setNome("Jorjao");
		pes1.setDependentes(3);
		pes1.setSalario(new BigDecimal("987.8876"));
		pes1.setNascimento(new Date());
	
		PessoaBean pesB=new PessoaBean();
		pesB.cadastrar();
	
	}

}
