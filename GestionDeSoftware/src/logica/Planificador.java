package logica;

import java.io.Serializable;

public class Planificador extends Trabajador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cantDias;
	
	
	
	public Planificador() {
		// TODO Auto-generated constructor stub
	}


     //constructor
	public Planificador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, float eficiencia) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, disponible, telefono, eficiencia);
		this.cantDias = cantDias;
	
		// TODO Auto-generated constructor stub
	}
	
	//setters y getters


	public int getCantDias() {
		return cantDias;
	}


	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}
	
	
	
	
	

}
