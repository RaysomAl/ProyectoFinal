package logica;

import java.io.Serializable;

public class Empresa extends Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1871068573319650181L;
	private String rnc;
	private String email;

	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	public Empresa(String rnc,String nombre,String email, String telefono, String ciudad, String pais) {
		super(nombre, telefono, ciudad, pais);
		this.rnc=rnc;
		this.email=email;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
