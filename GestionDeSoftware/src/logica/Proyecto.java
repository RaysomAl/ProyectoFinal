package logica;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Proyecto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int code = 1;
	private int codigo;
	private String nombreproyecto;
	private String lenguaje;
    private String Observacion;
    private JefeDeProyecto jefe;
    private float precioProyecto;  //se necesita para el contrato
    private boolean activo;// ralddy- lo cree para la funcion cantidadProyectosActivos en clientes
	   
	public Proyecto(String nombreproyecto, String lenguaje,String tipo,JefeDeProyecto jefe) {
		super();
		setCodigo();
		this.nombreproyecto = nombreproyecto;
		this.lenguaje = lenguaje;
		this.Observacion = tipo;
		this.jefe = jefe;
		activo=true;//ralddy- cuando un proyecto se crea este esta activo
	}




	// setters y getters
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo() {
		this.codigo = (int) (code + (Math.pow(10, 5)*(LocalDate.now().getYear())));
		code++;
	}


	public String getNombreproyecto() {
		return nombreproyecto;
	}


	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}


	public String getLenguaje() {
		return lenguaje;
	}


	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}


	public String getTipo() {
		return Observacion;
	}


	public void setTipo(String tipo) {
		this.Observacion = tipo;
	}

	public float getPrecioProyecto() {
		return precioProyecto;
	}


	public void setPrecioProyecto(float precioProyecto) {
		this.precioProyecto = precioProyecto;
	}


	public static int getCode() {
		return (int) (code + (Math.pow(10, 5)*(LocalDate.now().getYear())));
	}
	
	
    public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}




	public JefeDeProyecto getJefe() {
		return jefe;
	}




	public void setJefe(JefeDeProyecto jefe) {
		this.jefe = jefe;
	}
    
    

}
