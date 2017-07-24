package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Diseñador;
import logica.Empresa;
import logica.EmpresaRps;
import logica.JefeDeProyecto;
import logica.Planificador;
import logica.Programador;
import logica.Trabajador;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Evaluacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private static EmpresaRps empresaRps;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		try {
			Evaluacion dialog = new Evaluacion(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Evaluacion(EmpresaRps emp) {
		empresaRps=emp;
		setTitle("Evaluaci\u00F3n De Personal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Evaluacion.class.getResource("/img/004-signature.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(11, 11, 413, 197);
			llenarTextArea();
			panel.add(textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							EmpresaRps.getInstance().evaluacionPersonal();
							JOptionPane.showMessageDialog(null, "operacion exitosa", "informacion",JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}
				});
				btnGuardar.setIcon(new ImageIcon(Evaluacion.class.getResource("/img/001-technology.png")));
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int opcion = JOptionPane.showConfirmDialog(null, "Desea cerra??","Advertencia",JOptionPane.WARNING_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							dispose();
						}
					}
				});
				btnCancelar.setIcon(new ImageIcon(Evaluacion.class.getResource("/img/001-delete.png")));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	public void llenarTextArea() {

			
			String tipo="";
			textArea.append("               Nombre de la empresa"+"\n");
			textArea.append("                 EVALUACION DE PERSONAL"+"\n"+"\n");
			textArea.append("____________________________________________________________"+"\n"+"\n");
			textArea.append("                    DATOS TRABAJADOR"+"\n"+"\n");
			textArea.append("____________________________________________________________"+"\n"+"\n");
			
			for(Trabajador trabajador: EmpresaRps.getInstance().getMistrabajadores()) {
				if(trabajador instanceof JefeDeProyecto) {
					tipo="Jefe De Proyecto";
				}
				if(trabajador instanceof Programador) {
					tipo="Programador";
				}
				if(trabajador instanceof Planificador) {
					tipo="Planificador";
				}
				if(trabajador instanceof Diseñador) {
					tipo="Diseñador";
				}
				textArea.append("Cédula                        : "+trabajador.getCedula()+"\n");
				textArea.append("Nombre                      : "+trabajador.getNombre()+"\n");
				textArea.append("Apellido                      : "+trabajador.getApellido()+"\n");
				textArea.append("Tipo                             : "+tipo+"\n");
				textArea.append("Salario                        : "+trabajador.getSalario()+"\n");
				textArea.append("Proyectos                   : "+""+"\n");
				textArea.append("Evaluación                 : "+trabajador.getEvaluacion()+"\n");
				textArea.append("____________________________________________________________"+"\n");
			}
				
			
			
			
		
	}
}
