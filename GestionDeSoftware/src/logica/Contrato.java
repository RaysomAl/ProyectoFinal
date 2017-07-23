package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import sun.awt.SunHints.LCDContrastKey;

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
	private Calendar fechaSaldada;
	private Proyecto proyecto;  
	private float preciofinal;
	private float precioSaldo;
	private float tasaDolar;
	private float precioDolar; 
	private boolean terminado = false;
	
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

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}
	
	public float montoPagar(boolean ingreso) {
		float pago = this.preciofinal;
		float perdida = 0;
		Period resta = Period.between(LocalDate.now() , fechaFinal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		if(resta.getMonths()!=0) {//calcular los dias basados en milisegundos
			Date now = Calendar.getInstance().getTime();
			Date later = fechaFinal.getTime();
			long duracion  = now.getTime()-later.getTime();
			long diffdedias = TimeUnit.MILLISECONDS.toDays(duracion);
			long diff = duracion - TimeUnit.DAYS.toMillis(diffdedias);
			double diffdeHoras = TimeUnit.MILLISECONDS.toHours(diff);
			float horasAdias = (float) (diffdeHoras/24.0);
			float a = horasAdias + diffdedias;
			a=(float) Math.floor(a);
			int b = (int) a;
			for (int i = 0; i < b; i++) {//reduce 1% por cada dia
				perdida += pago*0.01;
				pago -= pago*0.01;
			}
		}
		if(ingreso)
			return (float)Math.floor(pago);
		if(!ingreso)
			return (float)Math.floor(perdida);
		return  0;
	}

	public Calendar getFechaSaldada() {
		return fechaSaldada;
	}

	public void setFechaSaldada(Calendar fechaSaldada) {
		this.fechaSaldada = fechaSaldada;
	}

	public float getPrecioSaldo() {
		return precioSaldo;
	}

	public void setPrecioSaldo(float precioSaldo) {
		this.precioSaldo = precioSaldo;
	}
	
	
	

}
