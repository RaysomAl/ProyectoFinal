package logica;

import java.io.Serializable;

public class Diseñador extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diseno;
	private int anosExperiencia;
	
	public Diseñador() {
		// TODO Auto-generated constructor stub
	}
	//constructor

	public Diseñador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, float eficiencia) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, disponible, telefono, eficiencia);
		this.diseno=diseno;
		this.anosExperiencia = anosExperiencia;
		// TODO Auto-generated constructor stub
	}
	//setters y gettes


	public int getAnosExperiencia() {
		return anosExperiencia;
	}

	public void setAnosExperiencia(int anosExperiencia) {
		this.anosExperiencia = anosExperiencia;
	}

	public String getDiseno() {
		return diseno;
	}

	public void setDiseno(String diseno) {
		this.diseno = diseno;
	}
	
	
	
	
	

}
