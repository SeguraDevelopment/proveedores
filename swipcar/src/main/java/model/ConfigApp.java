package model;

import java.util.Map;

import org.springframework.stereotype.Component;


public class ConfigApp {
	//private String urlProveedor;
	private String proveedor;
	private String pathDriverFirefox;
	private String pathFirefox;

	private Map<String, String> flota;

	public void setFlota(Map<String, String> flota) {
	      this.flota = flota;
	}
	
	public Map<String, String> getFlota() {
	      return this.flota;
	}
	  
	public String getPathDriverFirefox() {
		return pathDriverFirefox;
	}

	public void setPathDriverFirefox(String pathDriverFirefox) {
		this.pathDriverFirefox = pathDriverFirefox;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	/*
	public String getUrlProveedor() {
		return urlProveedor;
	}

	public void setUrlProveedor(String urlProveedor) {
		this.urlProveedor = urlProveedor;
	}
	*/

	public String getPathFirefox() {
		return pathFirefox;
	}

	public void setPathFirefox(String pathFirefox) {
		this.pathFirefox = pathFirefox;
	}
	

}
