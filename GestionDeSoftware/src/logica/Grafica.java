package logica;

import java.io.Serializable;

public class Grafica implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 
     */
    
    private String tipo;
    private int frec;

    public Grafica() {
		// TODO Auto-generated constructor stub
	}

    public Grafica(String tipo, int frec) {
		super();
		this.tipo = tipo;
		this.frec = frec;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFrec() {
		return frec;
	}

	public void setFrec(int frec) {
		this.frec = frec;
	}
    
    
    


    
    

}
