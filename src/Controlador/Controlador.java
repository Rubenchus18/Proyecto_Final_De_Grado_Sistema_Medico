package Controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Vista.Vista;

public class Controlador implements ActionListener,MouseListener{
	private Vista vista;
	public ControladorHibernet hibernate;
	String rol=null;
	public Controlador(Vista vista) throws Exception {
		   this.vista = vista;
		   this.vista.btnNewButtonInicioSesion.addActionListener(this);;
		   this.hibernate=new ControladorHibernet();
		   imagenes();
		   iniciarReloj(this.vista.labelHora);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
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
