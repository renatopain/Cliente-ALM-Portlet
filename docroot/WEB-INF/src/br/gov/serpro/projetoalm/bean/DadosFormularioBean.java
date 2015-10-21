package br.gov.serpro.projetoalm.bean;

public class DadosFormularioBean
{
   private String usuario;
   
   private String senha;
   
   private String areaProjetoId;
   
   private String atividadeId;
   
   private String titulo;
   
   private String descricao;

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getAreaProjetoId() {
		return areaProjetoId;
	}
	
	public void setAreaProjetoId(String areaProjetoId) {
		this.areaProjetoId = areaProjetoId;
	}
	
	public String getAtividadeId() {
		return atividadeId;
	}
	
	public void setAtividadeId(String atividadeId) {
		this.atividadeId = atividadeId;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
