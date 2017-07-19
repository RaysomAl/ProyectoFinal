package logica;

import java.io.Serializable;

public class Diseñador extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lenguajeDiseno;
	
	
	public Diseñador() {
		// TODO Auto-generated constructor stub
	}
	//constructor

	public Diseñador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, float eficiencia,String lenguajeDiseno) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, disponible, telefono, eficiencia);
		this.lenguajeDiseno=lenguajeDiseno;
		
		// TODO Auto-generated constructor stub
	}
	//setters y gettes

	public String getLenguajeDiseno() {
		return lenguajeDiseno;
	}

	public void setLenguajeDiseno(String lenguajeDiseno) {
		this.lenguajeDiseno = lenguajeDiseno;
	}

	
	
	
	
	

}
