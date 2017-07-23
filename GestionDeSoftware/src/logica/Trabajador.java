package logica;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class Trabajador implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected String sexo;
	protected int edad;
	protected float salario;
	protected String evaluacion;
	protected int disp=0;
	protected float pagoHoras;
	protected int horastrabajadas;
	protected String telefono;
	protected ArrayList<Contrato> misContratos = new ArrayList<>();
	protected int eficiencia;
	protected boolean Disponible = true;
	
	public Trabajador() {
		// TODO Auto-generated constructor stub
	}

	
	//constructor
	public Trabajador(String cedula, String nombre, String apellido,
			String direccion, String sexo, float salario, String evaluacion,
			float pagoHoras, int horastrabajadas, 
			String telefono, int eficiencia,int edad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.sexo = sexo;
		this.salario = salario;
		this.evaluacion = evaluacion;
		this.pagoHoras = pagoHoras;
		this.horastrabajadas = horastrabajadas;
		this.telefono = telefono;
		this.edad = edad;
		this.eficiencia = eficiencia;
	}
	
	//setters y getters auto generados


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public float getSalario() {
		return salario;
	}


	public void setSalario(float salario) {
		this.salario = salario;
	}


	public String getEvaluacion() {
		return evaluacion;
	}


	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}


	public float getPagoHoras() {
		return pagoHoras;
	}


	public void setPagoHoras(float pagoHoras) {
		this.pagoHoras = pagoHoras;
	}


	public int getHorastrabajadas() {
		return horastrabajadas;
	}


	public void setHorastrabajadas(int horastrabajadas) {
		this.horastrabajadas = horastrabajadas;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}


	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}


	public int getEficiencia() {
		return eficiencia;
	}


	public void setEficiencia(int eficiencia) {
		this.eficiencia = eficiencia;
	}


	public void setDisponible(boolean disponible) {
		Disponible = disponible;
	}
	
	public boolean isDisponible(){
		return Disponible;
	}


	public int getDisp() {
		return disp;
	}


	public void setDisp(int disp) {
		this.disp = disp;
	}
	
	public void estardisp(){
	    this.disp--;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int cantidadProyectosActivos() {
		int cantidad=0;
		for (Contrato contrato : misContratos) {
			if(contrato.getProyecto().isActivo()) {
				cantidad++;
			}
		}
		return cantidad;
	}
	
	
	

}
