package Controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import Vista.Vista;
import net.bytebuddy.asm.Advice.This;
import persistencias.Empleados;

public class Controlador implements ActionListener,MouseListener{
	private Vista vista;
	public ControladorHibernet hibernate;
	String rol=null;
	public Controlador(Vista vista) throws Exception {
		   this.vista = vista;
		   this.vista.btnNewButtonInicioSesion.addActionListener(this);
		   this.vista.lblNewLabelSalida.addMouseListener(this);
		   this.vista.btnNewButtonCrear.addActionListener(this);
		   this.vista.lblNewLabelVolverEditar.addMouseListener(this);
		   this.vista.lblNewLabelVolverCrear.addMouseListener(this);
		   this.vista.btnNewButtonEditar.addActionListener(this);
		   this.vista.comboBoxRoles.addActionListener(this);
		   this.vista.btnCrearTotal.addActionListener(this);
		   this.vista.btnNewButtonEliminar.addActionListener(this);
		   this.vista.btnEditarTotal.addActionListener(this);
		   this.hibernate=new ControladorHibernet();
		   imagenes();
		   iniciarReloj(this.vista.labelHora);
		    añadidoRolesComboBox();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==this.vista.lblNewLabelSalida) {
			this.vista.panelInicio.setVisible(true);
			this.vista.panelAdmin.setVisible(false);
		}
		if(e.getSource()==this.vista.lblNewLabelVolverCrear) {
			this.vista.panelCrearAdmin.setVisible(false);
			this.vista.btnNewButtonEditar.setEnabled(true);
			this.vista.btnNewButtonEliminar.setEnabled(true);
			this.vista.lblNewLabelSalida.setEnabled(true);
			this.vista.btnNewButtonCrear.setEnabled(true);
			this.vista.tablaUsuarios.setEnabled(true);
			
		}
		if(e.getSource()==this.vista.lblNewLabelVolverEditar) {
			this.vista.panelEditarUsuario.setVisible(false);
			this.vista.btnNewButtonEditar.setEnabled(true);
			this.vista.btnNewButtonEliminar.setEnabled(true);
			this.vista.lblNewLabelSalida.setEnabled(true);
			this.vista.btnNewButtonCrear.setEnabled(true);
			this.vista.tablaUsuarios.setEnabled(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.vista.btnNewButtonInicioSesion) {
			String nombre=this.vista.textFieldNombreUsuario.getText();
			String contraseña=this.vista.textFieldContraseña.getText();
			rol=hibernate.sacarRoles(nombre,contraseña);
			if(rol!=null) {
				if(rol.equalsIgnoreCase("admin")) {
					this.vista.panelInicio.setVisible(false);
					this.vista.panelAdmin.setVisible(true);
					this.vista.lblNewLabelNombreUsuarioMostrar.setText(nombre);
					mostrarUsuariosEnJTable();
				}else if(rol.equalsIgnoreCase("medico")) {
					this.vista.panelInicio.setVisible(false);
					this.vista.panelMedico.setVisible(true);
				}else if(rol.equalsIgnoreCase("recepcionista")) {
					this.vista.panelInicio.setVisible(false);
					this.vista.panelRececipnista.setVisible(true);
				}else if(rol.equalsIgnoreCase("paciente")) {
					this.vista.panelInicio.setVisible(false);
					this.vista.panelPacientes.setVisible(true);
				}
			}else {
				this.vista.lblNewLabelError.setText("Usuario o Contraseña no existe");
				this.vista.lblNewLabelError.setForeground(new Color(139, 0, 0));
			}
			
		}
		if(e.getSource()==this.vista.btnNewButtonCrear) {
			this.vista.btnNewButtonEditar.setEnabled(false);
			this.vista.btnNewButtonCrear.setEnabled(false);
			this.vista.btnNewButtonEliminar.setEnabled(false);
			this.vista.lblNewLabelSalida.setEnabled(false);
			this.vista.tablaUsuarios.setEnabled(false);
			this.vista.panelCrearAdmin.setVisible(true);
		}
		if(e.getSource()==this.vista.btnNewButtonEditar) {
			this.vista.btnNewButtonEditar.setEnabled(false);
			this.vista.btnNewButtonCrear.setEnabled(false);
			this.vista.btnNewButtonEliminar.setEnabled(false);
			this.vista.lblNewLabelSalida.setEnabled(false);
			this.vista.tablaUsuarios.setEnabled(false);
			this.vista.panelEditarUsuario.setVisible(true);
		}
		if (e.getSource() == this.vista.btnCrearTotal) {

		    String nombre = this.vista.textFieldNombreUsuarioCrear.getText().trim();
		    String contraseña = this.vista.textFieldContraseñaCrear.getText().trim();
		    String rol = (String) this.vista.comboBoxRoles.getSelectedItem(); 

		    if (nombre.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
		        this.vista.lblErrorCrear.setText("Campos obligatorios");
		    } else {
		
		        hibernate.crearUsuario(nombre, contraseña, rol);
		        mostrarUsuariosEnJTable(); 

		        this.vista.panelCrearAdmin.setVisible(false);
		        this.vista.btnNewButtonEditar.setEnabled(true);
		        this.vista.btnNewButtonEliminar.setEnabled(true);
		        this.vista.lblNewLabelSalida.setEnabled(true);
		        this.vista.btnNewButtonCrear.setEnabled(true);
		        this.vista.tablaUsuarios.setEnabled(true);

		       
		        this.vista.lblErrorCrear.setText("");
		        this.vista.textFieldNombreUsuarioCrear.setText("");
		        this.vista.textFieldContraseñaCrear.setText("");
		        this.vista.comboBoxRoles.setSelectedIndex(0); 
		    }
		}
		if(e.getSource()==this.vista.btnNewButtonEliminar) {
			int seleccionfila=this.vista.tablaUsuarios.getSelectedRow();
			if(seleccionfila>=0) {
				  String nombreUsuario = (String) this.vista.tablaUsuarios.getValueAt(seleccionfila, 0);
				  hibernate.eliminarUsuario(nombreUsuario);
				  mostrarUsuariosEnJTable();  
			}
		}
		if(e.getSource()==this.vista.btnEditarTotal) {
				  String nombreUsuario =this.vista.textFieldNombreUsuarioEditar.getText();
				  String contraseña=this.vista.textFieldContraseñaEditar.getText();
				  String rol = (String) this.vista.comboBoxRolesEditar.getSelectedItem(); 
				  if(nombreUsuario.isEmpty()||contraseña.isEmpty()||rol.isEmpty()) {
					  this.vista.lblErrorEditar.setText("Campos obligatorios");
				  }else {
					  hibernate.EditarUsuario(nombreUsuario, contraseña, rol);
					  mostrarUsuariosEnJTable();
					  this.vista.panelEditarUsuario.setVisible(false);
					     this.vista.btnNewButtonEditar.setEnabled(true);
					        this.vista.btnNewButtonCrear.setEnabled(true);
					        this.vista.btnNewButtonEliminar.setEnabled(true);
					        this.vista.lblNewLabelSalida.setEnabled(true);
					        this.vista.tablaUsuarios.setEnabled(true);
				  }
				 ; 
			
		}
		if (e.getSource() == this.vista.btnNewButtonEditar) {

		    int seleccionfila = this.vista.tablaUsuarios.getSelectedRow();
		    if (seleccionfila >= 0) { 
		        String nombreUsuario = (String) this.vista.tablaUsuarios.getValueAt(seleccionfila, 0); 
		        String contraseña = (String) this.vista.tablaUsuarios.getValueAt(seleccionfila, 1);    
		        String rol = (String) this.vista.tablaUsuarios.getValueAt(seleccionfila, 2);    

		        this.vista.textFieldNombreUsuarioEditar.setText(nombreUsuario);
		        this.vista.textFieldContraseñaEditar.setText(contraseña);
		        this.vista.comboBoxRolesEditar.setSelectedItem(rol);
		        this.vista.panelEditarUsuario.setVisible(true);

		        this.vista.btnNewButtonEditar.setEnabled(false);
		        this.vista.btnNewButtonCrear.setEnabled(false);
		        this.vista.btnNewButtonEliminar.setEnabled(false);
		        this.vista.lblNewLabelSalida.setEnabled(false);
		        this.vista.tablaUsuarios.setEnabled(false);
		    } 
		}
	}
		
	//Metodo
	public ImageIcon fotoEscalarLabel(JLabel label, String url) {
        ImageIcon imagenDefecto = new ImageIcon(url);
        ImageIcon icono = new ImageIcon(imagenDefecto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        return icono;
    }
	 public ImageIcon fotoEscalarButton(JButton label, String url) {
	        ImageIcon imagenDefecto = new ImageIcon(url);
	        ImageIcon icono = new ImageIcon(imagenDefecto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
	        return icono;
	    }
	 public void imagenes() {
		 this.vista.lblFondo.setIcon(fotoEscalarLabel(this.vista.lblFondo, "imagenes/fondo_aplicacion.jpg"));
		 this.vista.lblLogo.setIcon(fotoEscalarLabel(this.vista.lblLogo, "imagenes/logo.png"));
		 this.vista.btnNewButtonInicioSesion.setIcon(fotoEscalarButton(this.vista.btnNewButtonInicioSesion, "imagenes/botonInicoSesion.png"));
		 this.vista.lblNewLabelSalida.setIcon(fotoEscalarLabel(this.vista.lblNewLabelSalida, "imagenes/botonVolver.png"));
		 this.vista.lblNewLabelLogo.setIcon(fotoEscalarLabel(this.vista.lblNewLabelLogo, "imagenes/logo.png"));
		 this.vista.lblNewLabelCara.setIcon(fotoEscalarLabel(this.vista.lblNewLabelCara, "imagenes/foto_perfil.png"));
		 this.vista.lblNewLabelFondoAdmin.setIcon(fotoEscalarLabel(this.vista.lblNewLabelFondoAdmin, "imagenes/fondo_aplicacion.jpg"));
		 this.vista.btnNewButtonCrear.setIcon(fotoEscalarButton(this.vista.btnNewButtonCrear, "imagenes/boton_crear.png"));
		 this.vista.btnNewButtonEditar.setIcon(fotoEscalarButton(this.vista.btnNewButtonEditar, "imagenes/editar.png"));
		 this.vista.btnNewButtonEliminar.setIcon(fotoEscalarButton(this.vista.btnNewButtonEliminar, "imagenes/BtonEliminar.png"));
		 this.vista.btnCrearTotal.setIcon(fotoEscalarButton(this.vista.btnCrearTotal, "imagenes/boton_crear.png"));
		 this.vista.btnEditarTotal.setIcon(fotoEscalarButton(this.vista.btnEditarTotal, "imagenes/editar.png"));
		 this.vista.lblNewLabelVolverCrear.setIcon(fotoEscalarLabel(this.vista.lblNewLabelVolverCrear, "imagenes/botonVolver.png"));
		 this.vista.lblNewLabelVolverEditar.setIcon(fotoEscalarLabel(this.vista.lblNewLabelVolverEditar, "imagenes/botonVolver.png"));
		 this.vista.lblNewLabelFondoCrear.setIcon(fotoEscalarLabel(this.vista.lblNewLabelFondoCrear, "imagenes/fondo_admin_panel.jpg"));
		 this.vista.lblNewLabelFondeEditar.setIcon(fotoEscalarLabel(this.vista.lblNewLabelFondeEditar, "imagenes/fondo_admin_panel.jpg"));
	 }
	 public void añadidoRolesComboBox() {
		  this.vista.comboBoxRoles.addItem("admin");
		    this.vista.comboBoxRoles.addItem("medico");
		    this.vista.comboBoxRoles.addItem("recepcionista");
		    this.vista.comboBoxRoles.addItem("paciente");
		    
		    this.vista.comboBoxRoles.setSelectedItem(null);
		    this.vista.comboBoxRolesEditar.addItem("admin");
		    this.vista.comboBoxRolesEditar.addItem("medico");
		    this.vista.comboBoxRolesEditar.addItem("recepcionista");
		    this.vista.comboBoxRolesEditar.addItem("paciente");
		    
		    this.vista.comboBoxRoles.setSelectedItem(null);
	 }
	 public void mostrarUsuariosEnJTable() {
		 
		    List<Empleados> usuarios = hibernate.obtenerTodosLosUsuarios();

		  
		    DefaultTableModel modelo = new DefaultTableModel();

		    
		    modelo.addColumn("Usuario");
		    modelo.addColumn("Contraseña");
		    modelo.addColumn("Rol");

		    for (Empleados empleado : usuarios) {
		        Object[] fila = {
		            empleado.getUsername(), 
		            empleado.getPassword(), 
		            empleado.getRol()      
		        };
		        modelo.addRow(fila); 
		    }

		    this.vista.tablaUsuarios.setModel(modelo);
		}
	 //Hilo
	 public void iniciarReloj(JLabel label) {	    
		    Thread hiloReloj = new Thread(() -> {		      
		        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

		        while (true) {
		            try {
		                String horaActual = formato.format(new Date());

		                SwingUtilities.invokeLater(() -> {
		                    label.setText(horaActual);
		                });
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    });

		    hiloReloj.start();
		}
	 
}
