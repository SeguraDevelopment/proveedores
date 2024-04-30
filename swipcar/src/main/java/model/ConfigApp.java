package model;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Component;


public class ConfigApp {
	//private String urlProveedor;

	
	private String pathDriverFirefox;
	private String pathFirefox;

	
	private List<Proveedor> proveedores;

	

	  
	public String getPathDriverFirefox() {
		return pathDriverFirefox;
	}

	public void setPathDriverFirefox(String pathDriverFirefox) {
		this.pathDriverFirefox = pathDriverFirefox;
	}


	

	public String getPathFirefox() {
		return pathFirefox;
	}

	public void setPathFirefox(String pathFirefox) {
		this.pathFirefox = pathFirefox;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;

	}
	

}
