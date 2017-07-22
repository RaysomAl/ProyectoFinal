package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class JefeDeProyecto extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Trabajador> misTrabajadores;
	private int anosExperiencia;
	
	public JefeDeProyecto(){
		
	}

	

	public JefeDeProyecto(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, String telefono,
			int eficiencia, int edad,int anosExperiencia) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, telefono, eficiencia, edad);
		this.anosExperiencia = anosExperiencia;
		// TODO Auto-generated constructor stub
	}

	
	
	//getters y setters


	public ArrayList<Trabajador> getMisTrabajadores() {
		return misTrabajadores;
	}

	public int getAnosExperiencia() {
		return anosExperiencia;
	}

	public void setAnosExperiencia(int anosExperiencia) {
		this.anosExperiencia = anosExperiencia;
	}

	public void setMisTrabajadores(ArrayList<Trabajador> misTrabajadores) {
		this.misTrabajadores = misTrabajadores;
	}
	
	
	
	
	

}
