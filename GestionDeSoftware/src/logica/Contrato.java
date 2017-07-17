package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Contrato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int code = 10000;
	private String codigoContrato;
	private Cliente cliente;
	private Calendar fechaInicio;
	private Calendar fechaFinal;
	private Proyecto proyecto;  
	private float preciofinal;
	private float tasaDolar;
	private float precioDolar; 
	

	public Contrato(String codigoContrato, Cliente cliente, Calendar fechaInicio,
			Calendar fechaFinal, Proyecto proyecto, float preciofinal,
			float tasaDolar, float precioDolar) {
		super();
		setCodigoContrato();
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.proyecto = proyecto;
		this.preciofinal = preciofinal;
		this.tasaDolar = tasaDolar;
		this.precioDolar = precioDolar;
	}
	
	//setters y getters

	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato() {
		this.codigoContrato = String.valueOf(code++) + "-" + String.valueOf(LocalDate.now().getYear());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Calendar fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public double getPreciofinal() {
		return preciofinal;
	}

	public void setPreciofinal(float preciofinal) {
		this.preciofinal = preciofinal;
	}

	public float getTasaDolar() {
		return tasaDolar;
	}

	public void setTasaDolar(float tasaDolar) {
		this.tasaDolar = tasaDolar;
	}

	public double getPrecioDolar() {
		return precioDolar;
	}

	public void setPrecioDolar(float precioDolar) {
		this.precioDolar = precioDolar;
	}

	public static String getCode() {
		return String.valueOf(code) + "-" + String.valueOf(LocalDate.now().getYear());
	}
	
	
	
	
	

}
