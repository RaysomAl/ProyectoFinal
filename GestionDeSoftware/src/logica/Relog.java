package logica;

import java.io.Serializable;
import java.util.Calendar;

public class Relog extends Thread implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String minutos, segundos, horas;
	private Thread hilo;
	private boolean estado;
	
	public Relog() {
		super();
		Calendar fecha = Calendar.getInstance();
		horas = String.valueOf(fecha.get(Calendar.HOUR));
		minutos = String.valueOf(fecha.get(Calendar.MINUTE));
		segundos = String.valueOf(fecha.get(Calendar.SECOND));
	}
	@Override
	public void run() {
		Calendar fecha = Calendar.getInstance();
		horas = String.valueOf(fecha.get(Calendar.HOUR));
		minutos = String.valueOf(fecha.get(Calendar.MINUTE));
		segundos = String.valueOf(fecha.get(Calendar.SECOND));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start() {
		do {
			run();
		}while(estado);
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String getSegundos() {
		return segundos;
	}

	public void setSegundos(String segundos) {
		this.segundos = segundos;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}
	public Thread getHilo() {
		return hilo;
	}

	public void setHilo(Thread hilo) {
		this.hilo = hilo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
