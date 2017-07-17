package logica;

import java.io.Serializable;

public class Indepediente extends Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1594731227393175043L;
	private String cedula;
	private String ampellido;
	private String sector;
	private String provincia; 
	

	public Indepediente(String cedula,String nombre,String ampellido, String telefono,  String ciudad,String sector, String provincia,String pais) {
		super(nombre, telefono,  ciudad, pais);
		this.cedula=cedula;
		this.ampellido=ampellido;
		this.sector=sector;
		this.provincia=provincia;
		
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getAmpellido() {
		return ampellido;
	}

	public void setApellido(String apellido) {
		this.ampellido = apellido;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	

}
