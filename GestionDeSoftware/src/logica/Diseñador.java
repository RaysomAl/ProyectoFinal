package logica;

import java.io.Serializable;

public class Dise�ador extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String herramienta;
	
	
	public Dise�ador() {
		// TODO Auto-generated constructor stub
	}
	//constructor

	public Dise�ador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, int eficiencia,String herramienta) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, telefono, eficiencia);
		this.herramienta=herramienta;
		
		// TODO Auto-generated constructor stub
	}
	//setters y gettes

	public String getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(String herramienta) {
		this.herramienta = herramienta;
	}

	
	
	
	
	
	

}
