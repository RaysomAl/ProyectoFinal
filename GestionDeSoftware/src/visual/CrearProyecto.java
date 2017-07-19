package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import logica.Diseñador;
import logica.EmpresaRps;
import logica.JefeDeProyecto;
import logica.Planificador;
import logica.Programador;
import logica.Proyecto;
import logica.Trabajador;

import javax.swing.event.ChangeEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.event.PopupMenuEvent;

public class CrearProyecto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JFormattedTextField ftxObservacion;
	private JFormattedTextField ftxTitulo;
	private JComboBox<String> cbxLeguaje;
	private JSpinner spnTrabajadores;
	private JComboBox<String> cbxJefe;
	private JComboBox<String> cbxProgramador1;
	private JComboBox<String> cbxProgramador2;
	private JComboBox<String> cbxProgramador3;
	private JComboBox<String> cbxAux1;
	private JComboBox<String> cbxAux2;
	private JComboBox<String> cbxAux3;
	private JComboBox<String> cbxTipoAux1;
	private JComboBox<String> cbxTipoAux2; 
	private JComboBox<String>cbxTipoAux3;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearProyecto dialog = new CrearProyecto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearProyecto() {
		setTitle("Crear Proyecto");
		setBounds(100, 100, 561, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel registrarProyecto = new JPanel();
			registrarProyecto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrar Proyecto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			registrarProyecto.setBounds(0, 0, 537, 360);
			panel.add(registrarProyecto);
			registrarProyecto.setLayout(null);
			
			JLabel lblNombre = new JLabel("C\u00F3digo:");
			lblNombre.setBounds(10, 40, 46, 22);
			registrarProyecto.add(lblNombre);
			
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.setText(String.valueOf(Proyecto.getCode()));
			txtCodigo.setBounds(73, 40, 150, 23);
			registrarProyecto.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNombre_1 = new JLabel("Titulo:");
			lblNombre_1.setBounds(10, 100, 78, 22);
			registrarProyecto.add(lblNombre_1);
			
			ftxTitulo = new JFormattedTextField(createDirr("********************"));
			ftxTitulo.setBounds(73, 100, 150, 23);
			registrarProyecto.add(ftxTitulo);
			
			JLabel lblLenguajeDeProgramcion = new JLabel("Lenguaje:");
			lblLenguajeDeProgramcion.setBounds(10, 160, 69, 22);
			registrarProyecto.add(lblLenguajeDeProgramcion);
			
			cbxLeguaje = new JComboBox<String>();
			cbxLeguaje.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>","Java","C","C++","Python","C#","Visual Basic. NET","JavaScript","PHP","Perl","Assembly language"}));
			cbxLeguaje.setBounds(73, 160, 150, 23);
			registrarProyecto.add(cbxLeguaje);
			
			JLabel lblCantidadDeTrabajadores = new JLabel("Trabajadores:");
			lblCantidadDeTrabajadores.setBounds(10, 220, 94, 22);
			registrarProyecto.add(lblCantidadDeTrabajadores);
			
			spnTrabajadores = new JSpinner();
			spnTrabajadores.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					cargarcbxAux();
				}
			});
			spnTrabajadores.setModel(new SpinnerNumberModel(3, 3, 6, 1));
			spnTrabajadores.setBounds(100, 220, 123, 23);
			registrarProyecto.add(spnTrabajadores);
			
			JPanel detallesTrabajadores = new JPanel();
			detallesTrabajadores.setBorder(new TitledBorder(null, "Detalles de los trabajadores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			detallesTrabajadores.setBounds(233, 23, 294, 326);
			registrarProyecto.add(detallesTrabajadores);
			detallesTrabajadores.setLayout(null);
			
			JLabel lblJefeDelProyecto = new JLabel("Jefe de proyecto:");
			lblJefeDelProyecto.setBounds(20, 25, 128, 22);
			detallesTrabajadores.add(lblJefeDelProyecto);
			
			cbxJefe = new JComboBox<String>();
			cbxJefe.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cbxJefe.removeAllItems();
					cbxJefe.addItem("<Seleccione>");
					for (Trabajador trabajador : EmpresaRps.getInstance().getMistrabajadores()) {
						if(trabajador instanceof JefeDeProyecto)
							if(trabajador.isDisponible())
								cbxJefe.addItem(trabajador.getNombre());
					}
				}
			});
			cbxJefe.setBounds(139, 25, 145, 22);
			cbxJefe.addItem("<Seleccione>");
			detallesTrabajadores.add(cbxJefe);
			
			JLabel lblProgramador = new JLabel("Programador:");
			lblProgramador.setBounds(21, 65, 88, 22);
			detallesTrabajadores.add(lblProgramador);
			
			JLabel lblProgramador_1 = new JLabel("Programador:");
			lblProgramador_1.setBounds(21, 105, 88, 22);
			detallesTrabajadores.add(lblProgramador_1);
			
			JLabel lblProgramador_2 = new JLabel("Programador:");
			lblProgramador_2.setBounds(21, 145, 88, 22);
			detallesTrabajadores.add(lblProgramador_2);
			
			cbxProgramador1 = new JComboBox<String>();
			cbxProgramador1.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxProgramador1, "Programador");
				}
			});
			cbxProgramador1.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxProgramador1.setBounds(139, 65, 145, 22);
			detallesTrabajadores.add(cbxProgramador1);
			
			cbxProgramador2 = new JComboBox<String>();
			cbxProgramador2.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxProgramador2, "Programador");
				}
			});
			cbxProgramador2.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxProgramador2.setBounds(139, 105, 145, 22);
			detallesTrabajadores.add(cbxProgramador2);
			
			cbxProgramador3 = new JComboBox<String>();
			cbxProgramador3.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxProgramador3, "Programador");
				}
			});
			cbxProgramador3.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxProgramador3.setBounds(139, 145, 145, 22);
			detallesTrabajadores.add(cbxProgramador3);
			
			cbxAux1 = new JComboBox<String>();
			cbxAux1.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxAux1, (String)cbxTipoAux1.getSelectedItem());
				}
			});
			cbxAux1.setEnabled(false);
			cbxAux1.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxAux1.setBounds(139, 185, 145, 22);
			detallesTrabajadores.add(cbxAux1);
			
			cbxAux2 = new JComboBox<String>();
			cbxAux2.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxAux2, (String)cbxTipoAux2.getSelectedItem());
				}
			});
			cbxAux2.setEnabled(false);
			cbxAux2.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxAux2.setBounds(139, 230, 145, 22);
			detallesTrabajadores.add(cbxAux2);
			
			cbxAux3 = new JComboBox<String>();
			cbxAux3.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cargarTrabajadores(cbxAux3, (String)cbxTipoAux3.getSelectedItem());
				}
			});
			cbxAux3.setEnabled(false);
			cbxAux3.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
			cbxAux3.setBounds(139, 275, 145, 22);
			detallesTrabajadores.add(cbxAux3);
			
			cbxTipoAux1 = new JComboBox<String>();
			cbxTipoAux1.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					if(cbxTipoAux1.getSelectedIndex()!=0)
						cbxAux1.setEnabled(true);
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cbxAux1.setSelectedIndex(0);
					cargarContentCbx(cbxTipoAux1);
					cbxAux1.setEnabled(false);
				}
			});
			cbxTipoAux1.setEnabled(false);
			cbxTipoAux1.setModel(new DefaultComboBoxModel<String>(new String[] {"<Tipo>"}));
			cbxTipoAux1.setBounds(21, 185, 108, 22);
			detallesTrabajadores.add(cbxTipoAux1);
			
			cbxTipoAux2 = new JComboBox<String>();
			cbxTipoAux2.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					if(cbxTipoAux2.getSelectedIndex()!=0)
						cbxAux2.setEnabled(true);
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cbxAux2.setSelectedIndex(0);
					cargarContentCbx(cbxTipoAux2);
					cbxAux2.setEnabled(false);
				}
			});
			cbxTipoAux2.setEnabled(false);
			cbxTipoAux2.setModel(new DefaultComboBoxModel<String>(new String[] {"<Tipo>"}));
			cbxTipoAux2.setBounds(21, 230, 108, 22);
			detallesTrabajadores.add(cbxTipoAux2);
			
			cbxTipoAux3 = new JComboBox<String>();
			cbxTipoAux3.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					if(cbxTipoAux3.getSelectedIndex()!=0)
						cbxAux3.setEnabled(true);
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					cbxAux3.setSelectedIndex(0);
					cargarContentCbx(cbxTipoAux3);
					cbxAux3.setEnabled(false);
				}
				}
			);
			cbxTipoAux3.setEnabled(false);
			cbxTipoAux3.setModel(new DefaultComboBoxModel<String>(new String[] {"<Tipo>"}));
			cbxTipoAux3.setBounds(21, 275, 108, 22);
			detallesTrabajadores.add(cbxTipoAux3);
			cargarContentCbx(cbxTipoAux1);
			cargarContentCbx(cbxTipoAux3);
			cargarContentCbx(cbxTipoAux2);
			
			JLabel lblObservaciones = new JLabel("Observaciones:");
			lblObservaciones.setBounds(10, 284, 94, 14);
			registrarProyecto.add(lblObservaciones);
			
			ftxObservacion = new JFormattedTextField(createDirr("********************"));
			ftxObservacion.setBounds(100, 281, 123, 23);
			registrarProyecto.add(ftxObservacion);
			Programador e = new Programador("031-0200031-1","Marco", "","", "", (float)1.1, "",(float)1.1, 1, 1,"", (float) 1.1);
			Programador z = new Programador("031-0200031-1","Maria", "","", "", (float)1.1, "",(float)1.1, 1, 1,"", (float) 1.1);
			Programador a = new Programador("031-0200031-1","Juan", "","", "", (float)1.1, "",(float)1.1, 1, 1,"", (float) 1.1);
			JefeDeProyecto alpha = new JefeDeProyecto("", "Estela", "","", "", (float)1.1, "",(float)1.1, 1, 1,"", (float)1.1);
			EmpresaRps.getInstance().getMistrabajadores().add(e);
			EmpresaRps.getInstance().getMistrabajadores().add(a);
			EmpresaRps.getInstance().getMistrabajadores().add(z);
			EmpresaRps.getInstance().getMistrabajadores().add(alpha);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear Contrato");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(todoSeleccionado()){
							ArrayList<Trabajador> empleados = new ArrayList<Trabajador>();
							empleados.add(buscarTrabajador(cbxProgramador1,"Programador"));
							empleados.add(buscarTrabajador(cbxProgramador2,"Programador"));
							empleados.add(buscarTrabajador(cbxProgramador3,"Programador"));
							if(cbxAux1.isEnabled())
								empleados.add(buscarTrabajador(cbxAux1,(String)cbxTipoAux1.getSelectedItem()));
							if(cbxAux2.isEnabled())
								empleados.add(buscarTrabajador(cbxAux2,(String) cbxTipoAux2.getSelectedItem()));
							if(cbxAux3.isEnabled())
								empleados.add(buscarTrabajador(cbxAux3,(String) cbxTipoAux3.getSelectedItem()));
							JefeDeProyecto lider = (JefeDeProyecto) buscarTrabajador(cbxJefe, "JefeDeProyecto");
							lider.setMisTrabajadores(empleados);
							Proyecto nuevo = new Proyecto(ftxTitulo.getText(),(String)cbxLeguaje.getSelectedItem(),ftxObservacion.getText(),lider);
							CrearContrato nuevoC = new CrearContrato(nuevo);
							dispose();
							nuevoC.setModal(true);
							nuevoC.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Debe llenar todos los requisitos para poder avanzar", "Advertencia",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??  No sé salvará ningún Dato","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	static MaskFormatter createDirr(String format) {/*hace que un string de X tamaño permita solo lo 
		caracteres de setValidChatacters*/
		/*(ARGUMENTO)recibe un String llenado con '*' , hasta donde llegen los '*' sera su tamaño maximo*/
		//(RETORNA)Formato para FORMATETEXTFIELDS
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter(format);
			mask.setValidCharacters(" qwertyuiopasdfghjklzxcvbnm"+" QWERTYUIOPASDFGHJKLZXCVBNM "+ "0123456789-");
			mask.setPlaceholderCharacter(' ');
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return mask;
	}

	private void cargarTrabajadores(JComboBox<String> cbx, String trabajo) {
		/*limpia y carga los trabajadores en un combobox
		(ARGUMENTO) cbx a donde van y sub clase de trabajador en string bien escrita*/
		cbx.removeAllItems();
		cbx.addItem("<Seleccione>");
		for (Trabajador trabajador : EmpresaRps.getInstance().getMistrabajadores()) {
			if(trabajo.equalsIgnoreCase(Programador.class.getSimpleName()))
				if(trabajador instanceof Programador)
					estadisponible(trabajador,cbx,trabajo);
			if(trabajo.equalsIgnoreCase(Diseñador.class.getSimpleName()))
				if(trabajador instanceof Diseñador)
					estadisponible(trabajador,cbx,trabajo);
			if(trabajo.equalsIgnoreCase(JefeDeProyecto.class.getSimpleName()))
				if(trabajador instanceof JefeDeProyecto)
					estadisponible(trabajador,cbx,trabajo);
			if(trabajo.equalsIgnoreCase(Planificador.class.getSimpleName()))
				if(trabajador instanceof Planificador)
					estadisponible(trabajador,cbx,trabajo);
		}
	}

	private void estadisponible(Trabajador trabajador, JComboBox<String> cbx, String trabajo) {
		//busca un si un trabajador esta disponible o esta en otro cbx para poder usarlo en este proyecto 
		//y lo carga al combobox argumento
		if(trabajo.equalsIgnoreCase(Programador.class.getSimpleName()))
			if(trabajador.isDisponible())
				if(!trabajador.getNombre().equalsIgnoreCase((String)(cbxProgramador1.getSelectedItem())) && 
						!trabajador.getNombre().equalsIgnoreCase((String)(cbxProgramador2.getSelectedItem())) && 
						!trabajador.getNombre().equalsIgnoreCase((String)(cbxProgramador3.getSelectedItem()))){
					if(trabajo.equalsIgnoreCase((String)cbxTipoAux1.getSelectedItem()))
						if(trabajador.getNombre().equalsIgnoreCase((String)(cbxAux1.getSelectedItem())))
							return ;
					if(trabajo.equalsIgnoreCase((String)cbxTipoAux2.getSelectedItem()))
						if(trabajador.getNombre().equalsIgnoreCase((String)(cbxAux2.getSelectedItem())))
							return ;
					if(trabajo.equalsIgnoreCase((String)cbxTipoAux3.getSelectedItem()))
						if(trabajador.getNombre().equalsIgnoreCase((String)(cbxAux3.getSelectedItem())))
							return ;
					cbx.addItem(/*trabajador.getCedula()+"("+*/trabajador.getNombre()/*+")"*/);
				}
	}

	private void cargarcbxAux() {//le da un enable o disable a los cbx auxiliares usando el spinner
		int index = (int) spnTrabajadores.getValue();
		activar(true,index);
	}

	private void activar(boolean b, int i) {/*Funcion que recibe una booleana y activa cbx basado en 
		el numero de spnTrabajadores*/
		if(i==3){
			cbxTipoAux1.setEnabled(!b);
		}
		if(i>=4){
			cbxTipoAux1.setEnabled(b);
			cbxTipoAux2.setEnabled(!b);
			cbxTipoAux3.setEnabled(!b);
		}
		if(i>=5){
			cbxTipoAux2.setEnabled(b);
			cbxTipoAux3.setEnabled(!b);
		}
		if(i>=6)
			cbxTipoAux3.setEnabled(b);
		
	}
	
	private Trabajador buscarTrabajador(JComboBox<String> cbxProgramador1, String string) {
		for (Trabajador worker : EmpresaRps.getInstance().getMistrabajadores()) {
			if(string.equalsIgnoreCase(JefeDeProyecto.class.getSimpleName()))
				if(worker instanceof JefeDeProyecto)
					if(worker.getNombre().equalsIgnoreCase((String) cbxProgramador1.getSelectedItem()))
						return worker;
			if(string.equalsIgnoreCase(Programador.class.getSimpleName()))
				if(worker instanceof Programador)
					if(worker.getNombre().equalsIgnoreCase((String) cbxProgramador1.getSelectedItem()))
						return worker;
			if(string.equalsIgnoreCase(Planificador.class.getSimpleName()))
				if(worker instanceof Planificador)
					if(worker.getNombre().equalsIgnoreCase((String) cbxProgramador1.getSelectedItem()))
						return worker;
			if(string.equalsIgnoreCase(Diseñador.class.getSimpleName()))
				if(worker instanceof Diseñador)
					if(worker.getNombre().equalsIgnoreCase((String) cbxProgramador1.getSelectedItem()))
						return worker;
		}
		return null;
	}
	
	private void cargarContentCbx(JComboBox<String> cbx) {//Cargar modelos de los cbx que tienen tipo de trabajadores aux
		String[] aus ;
		ArrayList<String> aux = new ArrayList<String>();
		aux.add("<Seleccione>");
		if(!"Planificador".equalsIgnoreCase(((String) (cbxTipoAux1.getSelectedItem())))&&
				!"Planificador".equalsIgnoreCase(((String) (cbxTipoAux2.getSelectedItem())))&&
				!"Planificador".equalsIgnoreCase(((String) (cbxTipoAux3.getSelectedItem()))))
			aux.add("Planificador");
		if(!"Diseñador".equalsIgnoreCase(((String) (cbxTipoAux1.getSelectedItem())))&&
				!"Diseñador".equalsIgnoreCase(((String) (cbxTipoAux2.getSelectedItem())))&&
				!"Diseñador".equalsIgnoreCase(((String) (cbxTipoAux3.getSelectedItem()))))
			aux.add("Diseñador");
		aux.add("Programador");
		aus = new String[aux.size()];
		for (int i = 0; i < aux.size(); i++) 
			aus[i] = aux.get(i);
		DefaultComboBoxModel<String> mod =new DefaultComboBoxModel<String>(aus);
		cbx.setModel(mod);
	}
	private boolean todoSeleccionado() {
		boolean nitido = true;
		if(cbxLeguaje.getSelectedIndex()==0||cbxJefe.getSelectedIndex()==0||cbxProgramador1.getSelectedIndex()==0
				||cbxProgramador2.getSelectedIndex()==0||cbxProgramador3.getSelectedIndex()==0||
				cbxTipoAux1.isEnabled()&&cbxAux1.getSelectedIndex()==0||cbxTipoAux2.isEnabled()&&cbxAux2.getSelectedIndex()==0
				||cbxTipoAux3.isEnabled()&&cbxAux3.getSelectedIndex()==0)
			nitido = false;
		return nitido;
	}
}
