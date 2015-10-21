package br.gov.serpro.projetoalm.managedbean;

import br.gov.serpro.projetoalm.bean.Pergunta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="clienteALMView")
@SessionScoped
public class ClientAlmView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean valorIntegracao;
    
	private List<Pergunta> perguntas;
	

	public ClientAlmView(){
		listaPerguntas();
	}
	
	public void salvarIntegracao(){
		System.out.println("valor integracao:" + perguntas.get(0).getId());
		
	}
	
	public void listaPerguntas()
	{
		perguntas = new ArrayList<>();
		
		// Instanciando pergunta da categoria Integração
		
		Pergunta p = new Pergunta();
		p.setId("1");
		p.setDescricao("As integrações trafegam um grande volume de dados (uniformemente ou picos) ?");
		p.setCategoria("Integracao");
		
		perguntas.add(p);
		
		// Instanciando pergunta da categoria Controle Transacional
		
		Pergunta p2 = new Pergunta();
		p2.setId("2");
		p2.setDescricao("Existe a possibilidade das aplicações a que ela se integra ficarem indisponíveis 2?");
		p2.setCategoria("ControleTransacional");		
		perguntas.add(p2);
		
		Pergunta p3 = new Pergunta();
		p3.setId("3");
		p3.setDescricao("Existe a possibilidade das aplicações a que ela se integra ficarem indisponíveis 3?");
		p3.setCategoria("ControleTransacional");		
		perguntas.add(p3);
		
		Pergunta p4 = new Pergunta();
		p4.setId("4");
		p4.setDescricao("Existe a possibilidade das aplicações a que ela se integra ficarem indisponíveis 4?");
		p4.setCategoria("ControleTransacional");		
		perguntas.add(p4);
		
		Pergunta p5 = new Pergunta();
		p5.setId("5");
		p5.setDescricao("Existe a possibilidade das aplicações a que ela se integra ficarem indisponíveis 5?");
		p5.setCategoria("ControleTransacional");		
		perguntas.add(p5);
		
		Pergunta p6 = new Pergunta();
		p6.setId("6");
		p6.setDescricao("Existe a possibilidade das aplicações a que ela se integra ficarem indisponíveis 6?");
		p6.setCategoria("ControleTransacional");		
		perguntas.add(p6);
	
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public String integracao(){
    	return "integracao";
    }
	
	public String monitoracao(){
	 
	 return "monitoracao";
 }  
   
    public boolean isValorIntegracao() {
		return valorIntegracao;
	}

	public void setValorIntegracao(boolean valorIntegracao) {
		this.valorIntegracao = valorIntegracao;
	}


}	

