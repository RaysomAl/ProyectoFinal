package logica;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cliente implements Serializable {
	//ralddy-creacion de la clase indepediente y empresa derivadas de la ahora abstracta cliente

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String nombre;
	protected String telefono;  
	protected ArrayList<Contrato> misContratos;
	//private int proyectosActivos;-Ralddy-lo cambie por la funcion cantidadProyectosActivo
	protected String ciudad;
	protected String pais;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente( String nombre,String telefono,String ciudad, String pais) {
		super();

		this.nombre = nombre;

		this.telefono = telefono;


		this.ciudad = ciudad;

		this.pais = pais;
		this.misContratos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}

	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	//ralddy-cree el atributo activo en proyecto para esto
	public int cantidadProyectosActivos() {
		int cantidad=0;
		for (Contrato contrato : misContratos) {
			if(contrato.getProyecto().isActivo()) {
				cantidad++;
			}
			
		}
		return cantidad;
	}
	
	
	

}
