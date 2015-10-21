package br.gov.serpro.projetoweb.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import br.gov.serpro.projetoalm.bean.AreaProjetoBean;
import br.gov.serpro.projetoalm.bean.AtividadeBean;
import br.gov.serpro.projetoalm.bean.DadosFormularioBean;

public class RiscoBO
{
   public List<AreaProjetoBean> listarAreasProjeto(String user) throws Exception
   {
	   URL resource = getClass().getClassLoader().getResource("Truststore.jks");
	   
	   // Trust own CA and all self-signed certs
	   SSLContext sslcontext = SSLContexts.custom()
	               .loadTrustMaterial(resource, "changeit".toCharArray(),
	               new TrustSelfSignedStrategy())
	               .build();
	    
	   // Allow TLSv1 protocol only
	   SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	               sslcontext,
	               new String[] { "TLSv1" },
	               null,
	               SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	    
	   CloseableHttpClient httpclient = HttpClients.custom()
	               .setSSLSocketFactory(sslsf)
	               .build();
	    
	   String url = "";
	    
	   try
	   {  	
	       url = "https://dev.wsalm.servicos.serpro/alm-servicos-rest/rest/get/areaEquipe?cpf=" + user;	
	    	
	       HttpGet httpget = new HttpGet(url);

	       CloseableHttpResponse response = httpclient.execute(httpget);    
	       
	       try
	       {              
	          BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

	          String line = rd.readLine();
	          
	          JSONObject jsonObject = new JSONObject(line);
	          
	          JSONArray jsonArray = jsonObject.getJSONArray("areaEquipe");
	          
	          List<AreaProjetoBean> areasProjeto = new ArrayList<AreaProjetoBean>();
	          
	          for (int i = 0; i < jsonArray.length(); i++)
	          {
	              JSONObject jsonItem = jsonArray.getJSONObject(i);
	              
	              AreaProjetoBean areaProjetoBean = new AreaProjetoBean();
	              
	              areaProjetoBean.setId((String)jsonItem.get("id"));
	        	  
	        	  areaProjetoBean.setNome((String)jsonItem.get("nome"));
	        	  
	        	  areasProjeto.add(areaProjetoBean);
	          }
	          
	          return areasProjeto;
	       }
	       
	       finally
	       {
	          response.close();
	       }
	   }
	    
	   finally
	   {
	      httpclient.close();
	   }	   
   }
   
   public List<AtividadeBean> listarAtividades(String projectArea) throws Exception
   {
	   URL resource = getClass().getClassLoader().getResource("Truststore.jks");
	   
	// Trust own CA and all self-signed certs
	    SSLContext sslcontext = SSLContexts.custom()
	               .loadTrustMaterial(resource, "changeit".toCharArray(),
	               new TrustSelfSignedStrategy())
	               .build();
	    
	    // Allow TLSv1 protocol only
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	               sslcontext,
	               new String[] { "TLSv1" },
	               null,
	               SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	    
	    CloseableHttpClient httpclient = HttpClients.custom()
	               .setSSLSocketFactory(sslsf)
	               .build();
	    
	    String url = "";
	    
	    try
	    {  	
	       url = "https://dev.wsalm.servicos.serpro/alm-servicos-rest/rest/get/iterations?projectArea=" + projectArea;	
	    	
	       HttpGet httpget = new HttpGet(url);	       

	       CloseableHttpResponse response = httpclient.execute(httpget);    
	       
	       try
	       {              
	          BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

	          String line = rd.readLine();
	          
	          JSONObject jsonObject = XML.toJSONObject(line);
	          
	          JSONArray jsonArray = jsonObject.getJSONObject("foundation").getJSONArray("iteration");
	          
	          String itemId = "";
	          
	          Boolean hasDeliverable = false;
	          
	          List<AtividadeBean> atividades = new ArrayList<AtividadeBean>();
	          
	          for (int i = 0; i < jsonArray.length(); i++)
	          {
	              JSONObject jsonItem = jsonArray.getJSONObject(i);	              
	              
	              itemId = (String) jsonItem.get("itemId");
	              
	              hasDeliverable = (Boolean) jsonItem.get("hasDeliverable");
	              
	              if (hasDeliverable == true)
	              {
	            	  AtividadeBean atividadeBean = new AtividadeBean();              
		        	  
		              atividadeBean.setNome((String)jsonItem.get("name"));
	            	  
	            	  atividadeBean.setId("https://desenvalm.serpro/ccm/oslc/iterations/" + itemId);
	            	  
	            	  atividades.add(atividadeBean);
	              }
	          }
	          
	          return atividades;
	       }
	       
	       finally
	       {
	          response.close();
	       }
	    }
	    
	    finally
	    {
	       httpclient.close();
	    }	    
   }
   
   public void incluirRisco(DadosFormularioBean dadosFormularioBean) throws Exception
   {
	   URL resource = getClass().getClassLoader().getResource("Truststore.jks");
	   
	   // Trust own CA and all self-signed certs
	   SSLContext sslcontext = SSLContexts.custom()
			      .loadTrustMaterial(resource, "changeit".toCharArray(),
			   	  new TrustSelfSignedStrategy())
			   	  .build();
		    
		    // Allow TLSv1 protocol only
       SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		          sslcontext,
		          new String[] { "TLSv1" },
		          null,
		          SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		    
       CloseableHttpClient httpclient = HttpClients.custom()
		          .setSSLSocketFactory(sslsf)
		          .build();
		    
	   String url = "";
	   CloseableHttpResponse response = null;
	   try
	   {	
	    	ArrayList<NameValuePair> postParameters;
	    	
	    	url = "https://dev.wsalm.servicos.serpro/alm-servicos-rest/rest/post/risco";	
	    	
	    	HttpPost httppost = new HttpPost(url);

	        postParameters = new ArrayList<NameValuePair>();
	        
	        postParameters.add(new BasicNameValuePair("user", dadosFormularioBean.getUsuario()));
	        postParameters.add(new BasicNameValuePair("pass", dadosFormularioBean.getSenha()));
	        postParameters.add(new BasicNameValuePair("projectArea", dadosFormularioBean.getAreaProjetoId()));
	        postParameters.add(new BasicNameValuePair("plannedFor", dadosFormularioBean.getAtividadeId()));
	        postParameters.add(new BasicNameValuePair("title", dadosFormularioBean.getTitulo()));
	        postParameters.add(new BasicNameValuePair("description", dadosFormularioBean.getDescricao()));

	        httppost.setEntity(new UrlEncodedFormEntity(postParameters));
	        
	        response = httpclient.execute(httppost);
	        
	        System.out.println("Status da execução = " + response.getStatusLine());
	   }
	   
	   finally
	   {
	       if (!(response.getStatusLine().toString().contains("200 OK")))
	    		   throw new Exception("Ocorreu um erro no envio do formulário");
		   httpclient.close();
	   }	   
   }
}
