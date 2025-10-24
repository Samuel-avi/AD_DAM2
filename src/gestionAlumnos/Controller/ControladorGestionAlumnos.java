package gestionAlumnos.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gestionAlumnos.Model.Alumno;
import gestionAlumnos.Model.IModeloAlumnos;
import gestionAlumnos.UI.VentanaAlumnos;

public class ControladorGestionAlumnos implements ActionListener, ListSelectionListener {

	private IModeloAlumnos model;
	private VentanaAlumnos view;

	public ControladorGestionAlumnos(IModeloAlumnos model, VentanaAlumnos view) {
		this.model = model;
		this.view = view;
		anadirListeners(this);

		this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.view.pack();
		this.view.setLocationRelativeTo(null);
		this.view.setVisible(true);
	}

	private void anadirListeners(ControladorGestionAlumnos controladorGestionAlumnos) {
		view.btnCargarTodos.addActionListener(controladorGestionAlumnos);
		view.btnCrear.addActionListener(controladorGestionAlumnos);
		view.btnModificar.addActionListener(controladorGestionAlumnos);
		view.btnEliminar.addActionListener(controladorGestionAlumnos);

		view.jListaAlumnos.addListSelectionListener(controladorGestionAlumnos);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();

		System.out.println("estoy en actionPerformed con la opcion " + actionCommand);

		// Creo un objeto alumno y le paso los valores desde la ventana
		Alumno a = new Alumno();
		a.setDNI(view.textFieldDNI.getText());
		a.setNombre(view.textFieldNombre.getText());
		a.setApellidos(view.textFieldApellidos.getText());
		a.setCP(view.textFieldCP.getText());

		switch (actionCommand) {
			case "Cargar Todos":
				DefaultListModel<String> modelo = new DefaultListModel<String>();
			
				List<String> alumnos = model.getAll();
				for (String alumno : alumnos) {
					modelo.addElement(alumno);
				}
				view.jListaAlumnos.setModel(modelo);
				limpiarCampos();
				break;
			case "Crear Nuevo":
	
				model.crear(a);
	
				DefaultListModel<String> modelo2 = new DefaultListModel<String>();
				modelo2.addElement("Creado correctamente");
				view.jListaAlumnos.setModel(modelo2);
	
				limpiarCampos();
	
				break;
	
			case "Modificar":
	
				model.modificarAlumno(a);
	
				DefaultListModel<String> modelo3 = new DefaultListModel<String>();
				modelo3.addElement("Modificado correctamente");
				view.jListaAlumnos.setModel(modelo3);
	
				limpiarCampos();
	
				break;
			case "Eliminar":
				model.eliminarAlumno(view.textFieldDNI.getText());
	
				DefaultListModel<String> modelo4 = new DefaultListModel<String>();
				modelo4.addElement("Eliminado correctamente");
				view.jListaAlumnos.setModel(modelo4);
	
				limpiarCampos();
	
				break;
				
			case "mostrar por DNI":
					
				//este caso no se como se ejecuta, en "actionCommand" no lo encuentro
				model.getAlumnoPorDNI(view.textFieldDNI.getText()); 
					
				break;

	
			}
		
			
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO implementar el pinchar de una lista
		System.out.println("estoy en valueChanged");
		if (!e.getValueIsAdjusting()) {// This line prevents double events

			// TODO
			// view.jListaAlumnos

		}

	}

	private void limpiarCampos() {
		view.textFieldDNI.setText("");
		view.textFieldNombre.setText("");
		view.textFieldApellidos.setText("");
		view.textFieldCP.setText("");

	}

	// no se usar esto, lo pongo debajo del objeto alumno?? (no me funciona)
	private void cargarAlumno(Alumno alumno) {
		if (alumno == null) {
			limpiarCampos();
		}

		view.textFieldDNI.setText(alumno.getDNI());
		view.textFieldNombre.setText(alumno.getNombre());
		view.textFieldApellidos.setText(alumno.getApellidos());
		view.textFieldCP.setText(alumno.getCP());
	}

}
