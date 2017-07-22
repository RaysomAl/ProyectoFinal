package logica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

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
	public static float tasaDolar=(float)45.50;
	private static char[] contraseña;
	private static int intento=0;
	  
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
	
	public static char[] getContraseña() {
		return contraseña;
	}
	
	public static int getIntento() {
		return intento;
	}

	public static void setIntento(int intento) {
		EmpresaRps.intento = intento;
	}


	
	public static boolean setContraseña(char[] contraseñavieja, char[] ContraseñaNueva) {
		if(intento==0) {
			EmpresaRps.contraseña = ContraseñaNueva;
			intento++;
			return true;
		}else {
			if(compararPassFields(contraseñavieja, EmpresaRps.contraseña)) {
				EmpresaRps.contraseña = ContraseñaNueva;
				return true;
			}else {
				return false;	
			}
		}
	}

	private static boolean compararPassFields(char[] nueva, char[] confirmar) {
		if(nueva.length==confirmar.length) {
			for (int i = 0; i < confirmar.length; i++) 
				if(confirmar[i]!=nueva[i])
					return false;
			return true;
		}else {
			return false;
		}
	}

	public static float getTasaDolar() {
		return tasaDolar;
	}

	public static boolean setTasaDolar(float tasaDolar, char[] pass) {
		if(compararPassFields(EmpresaRps.contraseña, pass)) {
			EmpresaRps.tasaDolar = tasaDolar;
			return true;
		}
		return false;
	}
	
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
		//if (!mistrabajadores.contains(trabajador)) {
			mistrabajadores.add(trabajador);
		System.out.println("Trabajador Agregado");	
		}
		/*contains  devuelve true si el arraylist mistrabajadores contiene al menos un 
		 * elemento de trabajador*/
	//}
	
	 
	 
	//********************************************************************************
	
	
	
	//*****************************METODO CLIENTE***********************************
	
	public boolean agregarCliente(Cliente cliente) {
		boolean p=true;
		for(Cliente c: misclientes) {
			
			if(c instanceof Indepediente && cliente instanceof Indepediente) {
				if(((Indepediente) c).getCedula().equalsIgnoreCase(((Indepediente) cliente).getCedula())) {
				p=false;
				}
			} 
			
			
			if(c instanceof Empresa && cliente instanceof Empresa ) {
				if(((Empresa) c).getRnc().equalsIgnoreCase(((Empresa) cliente).getRnc())) {
				p=false;
				}
			} 
			

		}
		if(p) {
			misclientes.add(cliente);
			
		} 
		return p;
		
		
		

	}
	
	public void eliminarCliente(Cliente cliente) {
		
		if(cliente!=null) {
			misclientes.remove(cliente);
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
	
	public void modificarCliente(Cliente modi) {
		String ind="";
		if(modi instanceof Indepediente) {
			ind=((Indepediente) modi).getCedula();
		} else {
			ind=((Empresa) modi).getRnc();
		}
		Cliente c=buscarCliente(ind);
		int index=misclientes.indexOf(c);
		misclientes.set(index, modi);
	}


	//********************************************************************************
	
	//*************************METODOS PARA GRAFICA EL LEGUAJE MAS USADO*************************
	
	

	private ArrayList<String> getlenguajes(){
	    ArrayList<String> listaLen = new ArrayList<>();
	    for (Contrato c : miscontratos) {
		String prin= c.getProyecto().getLenguaje();
		listaLen.add(prin);
	    }
	    return listaLen;
	
	}
	
	public Grafica getLenguajeMasUsado(ArrayList<String>lenguajeExist){
	    ArrayList<String>todosLen=getlenguajes();
	    int uso=0;
	    int aux=0;
	    int indexM=0;
	    for(int i=0; i<lenguajeExist.size();i++){
	    	uso=Collections.frequency(todosLen, lenguajeExist.get(i));
		    if(uso>aux){
			aux=uso;
			indexM=i;
		    }
	    }
	    String nom=lenguajeExist.get(indexM);
	    int frecs=aux;
	    Grafica lenguajeMasUsado=new Grafica(nom, frecs);
	    return lenguajeMasUsado;
	}
	
	public Grafica getLenguajeMenosUsado(ArrayList<String>lenguajeExist){
	    ArrayList<String>todosLen=getlenguajes();
	    int uso=0;
	    int aux=50;
	    int indexMenos=0;
	    for(int i=0; i<lenguajeExist.size();i++){
	    	uso=Collections.frequency(todosLen, lenguajeExist.get(i));
		    if(uso<aux){
			aux=uso;
			indexMenos=i;
		    }
	    }
	    String nom=lenguajeExist.get(indexMenos);
	    int frecs=aux;
	    Grafica lenguajemenosUsado=new Grafica(nom, frecs);
	    return lenguajemenosUsado;
	}
	
	//**********************************************************************************
	
	
	
	
	
	
	
	
	//*************************FICHERO BINARIO****************************************

	/*public void guardarFicheroBinario() {
		FileOutputStream auxFileOutputStream = null;
		ObjectOutputStream empresaRpsObjectOutputStream = null;
		try {
			auxFileOutputStream = new FileOutputStream("empresaRps.dat");
			empresaRpsObjectOutputStream = new ObjectOutputStream(auxFileOutputStream);
			empresaRpsObjectOutputStream.writeObject(EmpresaRps.getInstance());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		EL FICHERO ESTA COMENTADO PARA CUANDO LO QUIERAN COMENZAR A USAR, 
		TAMBIEN ESTA COMENTADO EN LA PRINCIPAL
		
	}*/
	//**********************************************************************************
	

}
