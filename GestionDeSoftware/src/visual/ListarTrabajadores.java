package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class ListarTrabajadores extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarTrabajadores dialog = new ListarTrabajadores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarTrabajadores() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarTrabajadores.class.getResource("/img/listar32.png")));
		setTitle("Lista de Trabajadores");
		setResizable(false);
		setBounds(100, 100,  1050, 597);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel ListaTrabajadores = new JPanel();
		ListaTrabajadores.setBorder(new TitledBorder(null, "Lista de trabajadores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		ListaTrabajadores.setBounds(12, 104, 691, 430);
		contentPanel.add(ListaTrabajadores);
		
		JPanel proyectosActivos = new JPanel();
		proyectosActivos.setBorder(new TitledBorder(null, "Proyectos activos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		proyectosActivos.setBounds(715, 36, 319, 498);
		contentPanel.add(proyectosActivos);
		proyectosActivos.setLayout(null);
		
		JPanel FiltroTipo = new JPanel();
		FiltroTipo.setBorder(new TitledBorder(null, "Filtro por Tipo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		FiltroTipo.setBounds(12, 36, 329, 57);
		contentPanel.add(FiltroTipo);
		FiltroTipo.setLayout(null);
		
		JPanel Busquedad = new JPanel();
		Busquedad.setBorder(new TitledBorder(null, "Busquedad", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		Busquedad.setBounds(390, 36, 313, 57);
		contentPanel.add(Busquedad);
		Busquedad.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
