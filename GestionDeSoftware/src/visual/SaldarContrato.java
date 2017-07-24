package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Contrato;
import logica.EmpresaRps;
import logica.Trabajador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SaldarContrato extends JDialog {
//
	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtCliente;
	private JTextField txtJefe;
	private JTextField txtProyecto;
	private JTextField txtFechaF;
	private JTextField txtFechaS;
	private JTextField txtPrecioF;
	private JTextField txtPrecioS;
	private JTextField txtEmp1;
	private JTextField txtEmp2;
	private JTextField txtEmp3;
	private JTextField txtEmp4;
	private JTextField txtEmp5;
	private JTextField txtEmp6;
	private JTextField txtEmp7;
	private JSpinner spnEmp1;
	private JSpinner spnEmp2;
	private JSpinner spnEmp3;
	private JSpinner spnEmp4;
	private JSpinner spnEmp5;
	private JSpinner spnEmp6;
	private JSpinner spnEmp7;
	private static Contrato contr;
	private int cont;
	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			SaldarContrato dialog = new SaldarContrato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SaldarContrato(Contrato contac) {
		contr = contac;
		setBounds(100, 100, 599, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Datos Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ID Contrato:");
			lblNewLabel.setBounds(25, 35, 84, 14);
			contentPanel.add(lblNewLabel);
		}
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(119, 31, 158, 23);
		contentPanel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setBounds(25, 80, 84, 14);
		contentPanel.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fechas y Precios", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel.setBounds(10, 117, 558, 97);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblFechaInicio = new JLabel("Fecha Final:");
		lblFechaInicio.setBounds(10, 27, 84, 14);
		panel.add(lblFechaInicio);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha Saldada:");
		lblNewLabel_2.setBounds(10, 67, 103, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblPrecioEstablecido = new JLabel("Precio Final:");
		lblPrecioEstablecido.setBounds(272, 27, 96, 14);
		panel.add(lblPrecioEstablecido);
		
		JLabel lblPrecioFinal = new JLabel("Precio de Saldo:");
		lblPrecioFinal.setBounds(272, 67, 96, 14);
		panel.add(lblPrecioFinal);
		
		txtFechaF = new JTextField();
		txtFechaF.setEditable(false);
		txtFechaF.setBounds(104, 23, 158, 23);
		panel.add(txtFechaF);
		txtFechaF.setColumns(10);
		
		txtFechaS = new JTextField();
		txtFechaS.setEditable(false);
		txtFechaS.setBounds(104, 63, 158, 23);
		panel.add(txtFechaS);
		txtFechaS.setColumns(10);
		
		txtPrecioF = new JTextField();
		txtPrecioF.setEditable(false);
		txtPrecioF.setBounds(393, 23, 151, 23);
		panel.add(txtPrecioF);
		txtPrecioF.setColumns(10);
		
		txtPrecioS = new JTextField();
		txtPrecioS.setEditable(false);
		txtPrecioS.setBounds(393, 63, 151, 23);
		panel.add(txtPrecioS);
		txtPrecioS.setColumns(10);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(119, 76, 158, 23);
		contentPanel.add(txtCliente);
		txtCliente.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Jefe de Proyecto:");
		lblNewLabel_3.setBounds(287, 35, 111, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Proyecto:");
		lblNewLabel_4.setBounds(287, 80, 111, 14);
		contentPanel.add(lblNewLabel_4);
		
		txtJefe = new JTextField();
		txtJefe.setEditable(false);
		txtJefe.setBounds(408, 31, 151, 23);
		contentPanel.add(txtJefe);
		txtJefe.setColumns(10);
		
		txtProyecto = new JTextField();
		txtProyecto.setEditable(false);
		txtProyecto.setBounds(408, 76, 151, 23);
		contentPanel.add(txtProyecto);
		txtProyecto.setColumns(10);
		
		JPanel pnlEval = new JPanel();
		pnlEval.setBorder(new TitledBorder(null, "Evaluacion de Empleados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEval.setBounds(10, 226, 558, 223);
		contentPanel.add(pnlEval);
		pnlEval.setLayout(null);
		
		txtEmp1 = new JTextField();
		txtEmp1.setEditable(false);
		txtEmp1.setBounds(98, 31, 105, 23);
		pnlEval.add(txtEmp1);
		txtEmp1.setColumns(10);
		
		spnEmp1 = new JSpinner();
		JFormattedTextField tf = ((JSpinner.DefaultEditor)spnEmp1.getEditor()).getTextField();
		tf.setEditable(false);
		spnEmp1.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp1.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp1.getModel()).setMinimum(1);
		spnEmp1.setBounds(108, 82, 47, 20);
		pnlEval.add(spnEmp1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 35, 68, 14);
		pnlEval.add(lblNombre);
		
		JLabel lblEvaluacion = new JLabel("Evaluacion:");
		lblEvaluacion.setBounds(10, 85, 78, 14);
		pnlEval.add(lblEvaluacion);
		
		txtEmp2 = new JTextField();
		txtEmp2.setEditable(false);
		txtEmp2.setColumns(10);
		txtEmp2.setBounds(213, 31, 105, 23);
		pnlEval.add(txtEmp2);
		
		txtEmp3 = new JTextField();
		txtEmp3.setEditable(false);
		txtEmp3.setColumns(10);
		txtEmp3.setBounds(328, 31, 105, 23);
		pnlEval.add(txtEmp3);
		
		txtEmp4 = new JTextField();
		txtEmp4.setEditable(false);
		txtEmp4.setColumns(10);
		txtEmp4.setBounds(443, 31, 105, 23);
		pnlEval.add(txtEmp4);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(10, 135, 78, 14);
		pnlEval.add(lblNombre_1);
		
		JLabel lblEvaluacion_1 = new JLabel("Evaluacion:");
		lblEvaluacion_1.setBounds(10, 185, 68, 14);
		pnlEval.add(lblEvaluacion_1);
		
		spnEmp2 = new JSpinner();
		JFormattedTextField tf2 = ((JSpinner.DefaultEditor)spnEmp2.getEditor()).getTextField();
		tf2.setEditable(false);
		spnEmp2.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp2.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp2.getModel()).setMinimum(1);
		spnEmp2.setBounds(224, 82, 47, 20);
		pnlEval.add(spnEmp2);
		
		spnEmp3 = new JSpinner();
		JFormattedTextField tf3 = ((JSpinner.DefaultEditor)spnEmp3.getEditor()).getTextField();
		tf3.setEditable(false);
		spnEmp3.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp3.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp3.getModel()).setMinimum(1);
		spnEmp3.setBounds(338, 82, 47, 20);
		pnlEval.add(spnEmp3);
		
		spnEmp4 = new JSpinner();
		JFormattedTextField tf4 = ((JSpinner.DefaultEditor)spnEmp4.getEditor()).getTextField();
		tf4.setEditable(false);
		spnEmp4.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp4.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp4.getModel()).setMinimum(1);
		spnEmp4.setBounds(453, 82, 47, 20);
		pnlEval.add(spnEmp4);
		
		txtEmp5 = new JTextField();
		txtEmp5.setEditable(false);
		txtEmp5.setColumns(10);
		txtEmp5.setBounds(98, 131, 105, 23);
		pnlEval.add(txtEmp5);
		
		txtEmp6 = new JTextField();
		txtEmp6.setEditable(false);
		txtEmp6.setColumns(10);
		txtEmp6.setBounds(213, 131, 105, 23);
		pnlEval.add(txtEmp6);
		
		txtEmp7 = new JTextField();
		txtEmp7.setEditable(false);
		txtEmp7.setColumns(10);
		txtEmp7.setBounds(328, 131, 105, 23);
		pnlEval.add(txtEmp7);
		
		spnEmp5 = new JSpinner();
		JFormattedTextField tf5 = ((JSpinner.DefaultEditor)spnEmp5.getEditor()).getTextField();
		tf5.setEditable(false);
		spnEmp5.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp5.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp5.getModel()).setMinimum(1);
		spnEmp5.setBounds(108, 182, 47, 20);
		pnlEval.add(spnEmp5);
		
		spnEmp7 = new JSpinner();
		JFormattedTextField tf6 = ((JSpinner.DefaultEditor)spnEmp7.getEditor()).getTextField();
		tf6.setEditable(false);
		spnEmp7.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp7.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp7.getModel()).setMinimum(1);
		spnEmp7.setBounds(338, 182, 47, 20);
		pnlEval.add(spnEmp7);
		
		spnEmp6 = new JSpinner();
		JFormattedTextField tf7 = ((JSpinner.DefaultEditor)spnEmp6.getEditor()).getTextField();
		tf7.setEditable(false);
		spnEmp6.setValue(new Integer(5));
		((SpinnerNumberModel)spnEmp6.getModel()).setMaximum(5);
		((SpinnerNumberModel)spnEmp6.getModel()).setMinimum(1);
		spnEmp6.setBounds(224, 182, 47, 20);
		pnlEval.add(spnEmp6);
		cargatTXT();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Saldar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opcion = JOptionPane.showConfirmDialog(null, "Una vez aceptado se aplicara las evaluaciones, ¿todo correcto?","Confirmación",JOptionPane.INFORMATION_MESSAGE);
						if(opcion == JOptionPane.OK_OPTION){
							aplicarEvaluacion();
						}
						Principal.actualizarGraficas();
						dispose();
					}

					private void aplicarEvaluacion() {
						int i = 0;
						for (Trabajador worker : contr.getProyecto().getJefe().getMisTrabajadores()) {
							for (Trabajador empleados : EmpresaRps.getInstance().getMistrabajadores()) {
								if(worker.getCedula().equalsIgnoreCase(empleados.getCedula())) {
									if(i==0) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp2.getValue())+empleados.getEficiencia())/2);
										i++;
									}
									if(i==1) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp3.getValue())+empleados.getEficiencia())/2);
										i++;
									}
									if(i==2) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp4.getValue())+empleados.getEficiencia())/2);
										i++;
									}
									if(i==3) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp5.getValue())+empleados.getEficiencia())/2);
										i++;
									}
									if(i==4) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp6.getValue())+empleados.getEficiencia())/2);
										i++;
									}
									if(i==5) {
										empleados.setEficiencia((Float.valueOf((Integer) spnEmp7.getValue())+empleados.getEficiencia())/2);
										i++;
									}
								}
							}
							for (Trabajador aux : EmpresaRps.getInstance().getMistrabajadores()) 
								if(contr.getProyecto().getJefe().getCedula().equalsIgnoreCase(aux.getCedula()))
									aux.setEficiencia((aux.getEficiencia()+Float.valueOf((Integer)spnEmp1.getValue()))/2);
							liberarEmpleados();
							contr.setTerminado(true);
							contr.setFechaSaldada(Calendar.getInstance());
							contr.setPrecioSaldo(contr.montoPagar(true));
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


	private void cargatTXT() {
		txtCliente.setText(contr.getCliente().getNombre());
		txtFechaF.setText(String.valueOf((contr.getFechaFinal().get(Calendar.DAY_OF_MONTH)))+"/"+String.valueOf(contr.getFechaFinal().get(Calendar.MONTH))+"/"+String.valueOf(contr.getFechaFinal().get(Calendar.YEAR)));
		txtFechaS.setText(String.valueOf((Calendar.getInstance().get(Calendar.DAY_OF_MONTH)))+"/"+String.valueOf(Calendar.getInstance().get(Calendar.MONTH))+"/"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		txtProyecto.setText(contr.getProyecto().getNombreproyecto());
		txtJefe.setText(contr.getProyecto().getJefe().getNombre());
		txtID.setText(contr.getCodigoContrato());
		txtPrecioF.setText(String.valueOf(contr.getPreciofinal()));
		txtPrecioS.setText(String.valueOf(contr.montoPagar(true)));
		cargarEmpleados();
	}


	private void cargarEmpleados() {
		txtEmp1.setText(contr.getProyecto().getJefe().getNombre());
		cont = 1;
		for (Trabajador work : contr.getProyecto().getJefe().getMisTrabajadores()) {
			if(cont==1) 
				txtEmp2.setText(work.getNombre());
			if(cont==2) 
				txtEmp3.setText(work.getNombre());
			if(cont==3) 
				txtEmp4.setText(work.getNombre());
			if(cont==4) 
				txtEmp5.setText(work.getNombre());
			if(cont==5) 
				txtEmp6.setText(work.getNombre());
			if(cont==6) 
				txtEmp7.setText(work.getNombre());
			cont++;
		}
		if(cont==4) {
			txtEmp5.setText("No requerido");
			spnEmp5.setEnabled(false);
			txtEmp6.setText("No requerido");
			spnEmp6.setEnabled(false);
			txtEmp7.setText("No requerido");
			spnEmp7.setEnabled(false);
		}
		if(cont==5) {
			txtEmp6.setText("No requerido");
			spnEmp6.setEnabled(false);
			txtEmp7.setText("No requerido");
			spnEmp7.setEnabled(false);
		}
		if(cont==6) {
			txtEmp7.setText("No requerido");
			spnEmp7.setEnabled(false);
		}
	}
	private void liberarEmpleados() {
		for (Trabajador worker : contr.getProyecto().getJefe().getMisTrabajadores()) 
			for (Trabajador trabaja : EmpresaRps.getInstance().getMistrabajadores()) 
				if(worker.getCedula().equalsIgnoreCase(trabaja.getCedula()))
					trabaja.setDisponible(true);
		for(Trabajador trabaja : EmpresaRps.getInstance().getMistrabajadores())
			if(trabaja.getCedula().equalsIgnoreCase(contr.getProyecto().getJefe().getCedula()))
					trabaja.setDisponible(true);
	}
}
