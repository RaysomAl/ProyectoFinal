package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class EmpresaRps implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> misclientes = new ArrayList<>();
	private ArrayList<Contrato> miscontratos  = new ArrayList<>();
	private ArrayList<Trabajador> mistrabajadores = new ArrayList<>();
	private ArrayList<Proyecto> misproyectos = new ArrayList<>();
	private static EmpresaRps instance = null;//patron singleton
	  
	private EmpresaRps() {
		 this.misclientes=new ArrayList<>();
		    this.miscontratos=new ArrayList<>();
		    this.mistrabajadores=new ArrayList<>();
		    this.misproyectos = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public static EmpresaRps getInstance () {
        if (instance == null)  
            instance = new EmpresaRps();
        return instance;
    }
	
	//setters y getters

	public ArrayList<Cliente> getMisclientes() {
		return misclientes;
	}

	public void setMisclientes(ArrayList<Cliente> misclientes) {
		this.misclientes = misclientes;
	}

	public ArrayList<Contrato> getMiscontratos() {
		return miscontratos;
	}

	public void setMiscontratos(ArrayList<Contrato> miscontratos) {
		this.miscontratos = miscontratos;
	}

	public ArrayList<Trabajador> getMistrabajadores() {
		return mistrabajadores;
	}

	public void setMistrabajadores(ArrayList<Trabajador> mistrabajadores) {
		this.mistrabajadores = mistrabajadores;
	}

	public ArrayList<Proyecto> getMisproyectos() {
		return misproyectos;
	}

	public void setMisproyectos(ArrayList<Proyecto> misproyectos) {
		this.misproyectos = misproyectos;
	}

	public static void setInstance(EmpresaRps instance) {
		EmpresaRps.instance = instance;
	}
	
	//*****************************METODO TRABAJADOR***********************************
	public void agregarTrabajador(Trabajador trabajador){//metodo para agregar un trabajador
		if (!mistrabajadores.contains(trabajador)) {
			mistrabajadores.add(trabajador);
		System.out.println("Trabajador Agregado");	
		}
		/*contains  devuelve true si el arraylist mistrabajadores contiene al menos un 
		 * elemento de trabajador*/
	}
	 public Trabajador buscarTrabajador(String cedula){
	    	Trabajador aux = null;
	    	for (Trabajador trab : mistrabajadores) {
				if(trab.getCedula().equalsIgnoreCase(cedula)){
					aux = trab;
				}
			}
	    	return aux;
	    }
	//********************************************************************************
	
	
	
	//*****************************METODO CLIENTE***********************************
	
	public void agregarCliente(Cliente cliente) {
		for(Cliente c: misclientes) {
			
			if(c instanceof Indepediente && ((Indepediente) c).getCedula().equalsIgnoreCase( ((Indepediente) cliente).getCedula())) {
				System.out.println("La cedula :"+((Indepediente) c).getCedula()+" ya existe en el registro, verifique de nuevo");
			} 
			
			else if(c instanceof Empresa && ((Empresa) c).getRnc().equalsIgnoreCase( ((Empresa) cliente).getRnc())) {
				System.out.println("El RNC :"+((Empresa) c).getRnc()+" ya existe en el registro, verifique de nuevo");
			} 
			
			else {
				misclientes.add(cliente);
				System.out.println("Cliente Agregado");
			}
		}
		

	}
	
	public void eliminarCliente(String indentificador) {
		Cliente c=buscarCliente(indentificador);
		if(c!=null) {
			misclientes.remove(c);
		}
	

	}
	
	public Cliente buscarCliente(String indentificador) {//ralddy-buscar y devuelve  un cliente a partir de su cedula o rnc
		Cliente cliente=null;
		for (Cliente c : misclientes) {
			if(c instanceof Indepediente && ((Indepediente) c).getCedula().equalsIgnoreCase(indentificador)) {
				cliente=c;
			}
			
			if(c instanceof Empresa && ((Empresa) c).getRnc().equalsIgnoreCase(indentificador)) {
				cliente=c;
			}
		}
		
		if(cliente!=null) {
			System.out.println("Se encontro el cliente");
		} else {
			System.out.println("Cliente "+indentificador+" no existe");
		}
		
		return cliente;
	}

	//********************************************************************************
	
	
	
	
	
	

}
