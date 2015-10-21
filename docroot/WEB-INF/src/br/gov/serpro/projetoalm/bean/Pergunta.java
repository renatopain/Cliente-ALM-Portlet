package br.gov.serpro.projetoalm.bean;

public class Pergunta
{
	private String id;
	private String descricao;
	private String categoria;
	private boolean result = false;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
