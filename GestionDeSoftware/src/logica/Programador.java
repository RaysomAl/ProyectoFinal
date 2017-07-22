package logica;

import java.io.Serializable;

public class Programador extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lenguajeProgramacion;
	
	
	public Programador() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor

	public Programador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, String telefono,
			int eficiencia, int edad,String lenguajeProgramacion) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, telefono, eficiencia, edad);
		this.lenguajeProgramacion = lenguajeProgramacion;
		
		// TODO Auto-generated constructor stub
	}
	
	//setters y getters
	

	

	public String getLenguajeProgramacion() {
		return lenguajeProgramacion;
	}

	public void setLenguajeProgramacion(String lenguajeProgramacion) {
		this.lenguajeProgramacion = lenguajeProgramacion;
	}


	
	
	

}
