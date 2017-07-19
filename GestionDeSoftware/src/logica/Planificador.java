package logica;

import java.io.Serializable;

public class Planificador extends Trabajador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cantDias;
	private int anosExp;
	
	
	
	public Planificador() {
		// TODO Auto-generated constructor stub
	}


     
	//setters y getters


	public Planificador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, float eficiencia,int cantDias, int anosExp) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, disponible, telefono, eficiencia);
		this.cantDias = cantDias;
		this.anosExp = anosExp;
		// TODO Auto-generated constructor stub
	}



	public int getCantDias() {
		return cantDias;
	}


	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}



	public int getAnosExp() {
		return anosExp;
	}



	public void setAnosExp(int anosExp) {
		this.anosExp = anosExp;
	}
	
	
	
	
	
	

}
