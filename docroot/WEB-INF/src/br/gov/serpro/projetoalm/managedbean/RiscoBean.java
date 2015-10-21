package br.gov.serpro.projetoalm.managedbean;

import br.gov.serpro.projetoalm.bean.AreaProjetoBean;
import br.gov.serpro.projetoalm.bean.AtividadeBean;
import br.gov.serpro.projetoalm.bean.DadosFormularioBean;
import br.gov.serpro.projetoweb.business.RiscoBO;

import com.liferay.faces.portal.context.LiferayFacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name="riscoBean")
@RequestScoped
public class RiscoBean implements Serializable
{
   private static final long serialVersionUID = 1L;

	private String user;
   
    private String password;
   
    private String title;
   
    private String description;
    
    private List<AreaProjetoBean> projectAreas;
    
    private List<AtividadeBean> activities;
    
    private String selectedProjectArea; 

	private String selectedActivity; 

	private HtmlSelectOneMenu htmlSelectProjects;
	
	private HtmlSelectOneMenu htmlSelectActivities;

	private static final String SELECIONE_AREA_PROJETO = "Selecione uma área de projeto";
	
	private static final String SELECIONE_ATIVIDADE = "Selecione uma atividade";
    
    private RiscoBO riscoBO;
    
    public RiscoBean(){
    	riscoBO = new RiscoBO();
    }    
    
    public void listarAreasProjeto()
    {    	
        
    	try
    	{
    		projectAreas = riscoBO.listarAreasProjeto(user);
    		
    		if (htmlSelectProjects == null)
        		htmlSelectProjects = new HtmlSelectOneMenu();
        	
        	htmlSelectProjects.getChildren().clear();
        	
        	UISelectItem item;    	
        	
        	List<SelectItem> areasProjeto = new ArrayList<SelectItem>();
        	
        	areasProjeto.add(new SelectItem("DEFAULT", SELECIONE_AREA_PROJETO));
        	
        	Iterator itr = projectAreas.iterator();
        	
        	while (itr.hasNext())
        	{
        		AreaProjetoBean areaProjetoBean = (AreaProjetoBean) itr.next();
        		
        		areasProjeto.add(new SelectItem(areaProjetoBean.getId(), areaProjetoBean.getNome()));
        	}
        	
        	UISelectItems items = new UISelectItems();
        	
        	items.setValue(areasProjeto);
        	
        	htmlSelectProjects.getChildren().add(items);
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    }
    
    public void listarAtividades()
    {
    
    	try	
    	{
    		activities = riscoBO.listarAtividades(selectedProjectArea);
    		
    		if (htmlSelectActivities == null)
        		htmlSelectActivities = new HtmlSelectOneMenu();
        	
        	htmlSelectActivities.getChildren().clear();
        	
        	UISelectItem item;    	
        	
        	List<SelectItem> atividades = new ArrayList<SelectItem>();
        	
        	atividades.add(new SelectItem("DEFAULT", SELECIONE_ATIVIDADE));
        	
        	Iterator itr = activities.iterator();
        	
        	while (itr.hasNext())
        	{
        		AtividadeBean atividadeBean = (AtividadeBean) itr.next();    		
        		
        		atividades.add(new SelectItem(atividadeBean.getId(), atividadeBean.getNome()));
        	}
        	
        	UISelectItems items = new UISelectItems();
        	
        	items.setValue(atividades);
        	
        	htmlSelectActivities.getChildren().add(items);    		
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public String incluirRisco() 
    {
    	DadosFormularioBean dadosFormularioBean = new DadosFormularioBean();
    	
    	try
    	{  
    		dadosFormularioBean.setUsuario(user);
    		
    		dadosFormularioBean.setSenha(password);
    		
    		dadosFormularioBean.setAreaProjetoId(selectedProjectArea);
    		
    		dadosFormularioBean.setAtividadeId(selectedActivity);
    		
    		dadosFormularioBean.setTitulo(title);
    		
    		dadosFormularioBean.setDescricao(description);
    		
    		riscoBO.incluirRisco(dadosFormularioBean);    	
    	}
    	
    	catch (Exception e)
    	{
    		LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
    		liferayFacesContext.addGlobalErrorMessage("Ocorreu um erro no envio do formulário");
    		e.printStackTrace();
    		
    		return null;
    	}
    	
		return "success";
    }
    
    public String carregarFormulario()
    {
    	return "form";
    }

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public List<AreaProjetoBean> getProjectAreas() {
		return projectAreas;
	}

	public void setProjectAreas(List<AreaProjetoBean> projectAreas) {
		this.projectAreas = projectAreas;
	}

	public String getSelectedProjectArea() {
		return selectedProjectArea;
	}

	public void setSelectedProjectArea(String selectedProjectArea) {
		this.selectedProjectArea = selectedProjectArea;
	}

	public HtmlSelectOneMenu getHtmlSelectProjects() {
		return htmlSelectProjects;
	}

	public void setHtmlSelectProjects(HtmlSelectOneMenu htmlSelectProjects) {
		this.htmlSelectProjects = htmlSelectProjects;
	}
	
	public List<AtividadeBean> getActivities() {
		return activities;
	}

	public void setActivities(List<AtividadeBean> activities) {
		this.activities = activities;
	}

	public String getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(String selectedActivity) {
		this.selectedActivity = selectedActivity;
	}
	
	public HtmlSelectOneMenu getHtmlSelectActivities() {
		return htmlSelectActivities;
	}

	public void setHtmlSelectActivities(HtmlSelectOneMenu htmlSelectActivities) {
		this.htmlSelectActivities = htmlSelectActivities;
	}
}
