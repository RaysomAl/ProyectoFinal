package logica;

import java.io.Serializable;

public class Programador extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lenguajeProgramacion;
	private String tipoProgamador;
	
	public Programador() {
		// TODO Auto-generated constructor stub
	}
	
	// constructor

	public Programador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, int disponible,
			String telefono, float eficiencia) {
		super(cedula, nombre, apellido, direccion, sexo, salario, evaluacion,
				pagoHoras, horastrabajadas, disponible, telefono, eficiencia);
		this.lenguajeProgramacion = lenguajeProgramacion;
		this.tipoProgamador = tipoProgamador;
		// TODO Auto-generated constructor stub
	}
	
	//setters y getters
	

	public String getLenguajeProgramacion() {
		return lenguajeProgramacion;
	}

	public void setLenguajeProgramacion(String lenguajeProgramacion) {
		this.lenguajeProgramacion = lenguajeProgramacion;
	}

	public String getTipoProgamador() {
		return tipoProgamador;
	}

	public void setTipoProgamador(String tipoProgamador) {
		this.tipoProgamador = tipoProgamador;
	}
	
	
	
	

}
