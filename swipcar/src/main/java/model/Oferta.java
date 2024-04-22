package model;

public class Oferta {
	@Override
	public String toString() {
		return "Oferta [Proveedor=" + proveedor + ", vehiculo=" + vehiculo + ", precio=" + precio + ", urlAnuncio=" + urlAnuncio + ", tipo=" + tipo
				+ ", urlFotoVehiculo=" + urlFotoVehiculo + "]";
	}
	private String vehiculo;
	private float precio;
	private String urlAnuncio;
	private String tipo;
	private String urlFotoVehiculo;
	private Proveedor proveedor;

	
	
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getUrlAnuncio() {
		return urlAnuncio;
	}
	public void setUrlAnuncio(String urlAnuncio) {
		this.urlAnuncio = urlAnuncio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getUrlFotoVehiculo() {
		return urlFotoVehiculo;
	}
	public void setUrlFotoVehiculo(String urlFotoVehiculo) {
		this.urlFotoVehiculo = urlFotoVehiculo;
	}
	

}
