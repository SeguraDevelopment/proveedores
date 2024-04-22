package model;

public class Proveedor {
	private String proveedor;
	private String flota;
	private int idProveedor;
	private String urlFlota;
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getFlota() {
		return flota;
	}
	public void setFlota(String flota) {
		this.flota = flota;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getUrlFlota() {
		return urlFlota;
	}
	public void setUrlFlota(String urlFlota) {
		this.urlFlota = urlFlota;
	}
	@Override
	public String toString() {
		return "Proveedor [proveedor=" + proveedor + ", flota=" + flota + ", idProveedor=" + idProveedor + ", urlFlota="
				+ urlFlota + "]";
	}
}
