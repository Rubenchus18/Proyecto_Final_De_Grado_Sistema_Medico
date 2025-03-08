package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Controlador.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.border.LineBorder;

import Controlador.Controlador;

import javax.swing.JComboBox;

import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import javax.swing.JTextField;
import javax.swing.JSpinner;


import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;


public class Vista extends JFrame {

	public static final long serialVersionUID = 1L;
	public  JPanel contentPane;
	public JPanel panelInicio;
	public JLabel lblLogo;
	public JLabel lblFondo;
	public Object lblSaliraddMouseLis;
	public JLabel lblSalirMenu;
	public JLabel lblMinutos;
	public JTextField textFieldNombreUsuario;
	public JTextField textFieldContraseña;
	public JLabel lblNewLabelContraseña;
	public JButton btnNewButtonInicioSesion;
	public JLabel lblNewLabelNombreUsuario;
	public JPanel panelMedico;
	public JPanel panelPacientes;
	public JPanel panelRececipnista;
	public JPanel panelAdmin;
	public JLabel lblNewLabelError;
	public JLabel lblMedico;
	public JLabel lblPaciente;
	public JLabel lblRecepcionista;
	public JLabel lblAdministrador;
	public JLabel labelHora;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista vista = new Vista();
					Controlador controlador=new Controlador(vista);

					vista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1337, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		  ImageIcon imagenApp = (ajustarTamañoImg("imagenes/logo.png", 64, 64));
		  setTitle("VitalMed");
	       setIconImage(imagenApp.getImage()); 
	       setLocationRelativeTo(null);
	       setResizable(false); 
	        
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		 
		panelInicio = new JPanel();
		panelInicio.setBounds(0, 0, 1329, 742);
		
		contentPane.add(panelInicio);
		panelInicio.setLayout(null);
		 
		 
		 labelHora = new JLabel("");
		 labelHora.setHorizontalAlignment(SwingConstants.CENTER);
		 labelHora.setForeground(new Color(255, 128, 0));
		 labelHora.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 25));
		 labelHora.setBackground(new Color(255, 128, 64));
		 labelHora.setBounds(943, 11, 376, 83);
		 panelInicio.add(labelHora);
		
		 lblNewLabelError = new JLabel("");
		 lblNewLabelError.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabelError.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
		 lblNewLabelError.setBackground(new Color(255, 0, 0));
		 lblNewLabelError.setBounds(437, 648, 376, 83);
		 panelInicio.add(lblNewLabelError);
		 
		  lblNewLabelContraseña = new JLabel("Contraseña");
		  lblNewLabelContraseña.setForeground(new Color(255, 128, 0));
		  lblNewLabelContraseña.setBackground(new Color(255, 128, 0));
		  lblNewLabelContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		  lblNewLabelContraseña.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		  lblNewLabelContraseña.setBounds(437, 396, 376, 83);
		  panelInicio.add(lblNewLabelContraseña);
		   
		    btnNewButtonInicioSesion = new JButton("");
		    btnNewButtonInicioSesion.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 11));
		    btnNewButtonInicioSesion.setBounds(538, 547, 173, 155);
		    btnNewButtonInicioSesion.setContentAreaFilled(false); 
		    btnNewButtonInicioSesion.setBorderPainted(false);
		    panelInicio.add(btnNewButtonInicioSesion);
		    
		     lblNewLabelNombreUsuario = new JLabel("Nombre Usuario");
		     lblNewLabelNombreUsuario.setForeground(new Color(255, 128, 0));
		     lblNewLabelNombreUsuario.setBackground(new Color(255, 128, 64));
		     lblNewLabelNombreUsuario.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		     lblNewLabelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		     lblNewLabelNombreUsuario.setBounds(437, 236, 376, 83);
		     
		     panelInicio.add(lblNewLabelNombreUsuario);
		     
		     textFieldContraseña = new JTextField();
		     textFieldContraseña.setColumns(10);
		     textFieldContraseña.setBounds(437, 479, 376, 57);
		     panelInicio.add(textFieldContraseña);
		     
		     textFieldNombreUsuario = new JTextField();
		     textFieldNombreUsuario.setBounds(437, 330, 376, 57);
		     panelInicio.add(textFieldNombreUsuario);
		     textFieldNombreUsuario.setColumns(10);
		     
		lblLogo = new JLabel("");
		lblLogo.setBounds(478, 0, 287, 263);
		panelInicio.add(lblLogo);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1329, 825);
		panelInicio.add(lblFondo);
		
		 panelAdmin = new JPanel();
		 panelAdmin.setBounds(0, 0, 1329, 734);
		 panelAdmin.setVisible(false);
		 contentPane.add(panelAdmin);
		 panelAdmin.setLayout(null);
		 
		 lblAdministrador = new JLabel("Administrador");
		 lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		 lblAdministrador.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		 lblAdministrador.setBackground(new Color(255, 128, 0));
		 lblAdministrador.setBounds(0, 0, 376, 83);
		 panelAdmin.add(lblAdministrador);
		
		 panelMedico = new JPanel();
		panelMedico.setBounds(0, 0, 1329, 734);
		panelMedico.setVisible(false);
		contentPane.add(panelMedico);
		panelMedico.setLayout(null);
		
		lblMedico = new JLabel("MEDICO");
		lblMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedico.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		lblMedico.setBackground(new Color(255, 128, 0));
		lblMedico.setBounds(0, 0, 376, 83);
		panelMedico.add(lblMedico);
		
		 panelPacientes = new JPanel();
		panelPacientes.setBounds(0, 0, 1329, 734);
		panelPacientes.setVisible(false);
		contentPane.add(panelPacientes);
		panelPacientes.setLayout(null);
		
		lblPaciente = new JLabel("Paciente");
		lblPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaciente.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		lblPaciente.setBackground(new Color(255, 128, 0));
		lblPaciente.setBounds(0, 0, 376, 83);
		panelPacientes.add(lblPaciente);
		
		 panelRececipnista = new JPanel();
		panelRececipnista.setBounds(0, 0, 1329, 734);
		panelRececipnista.setVisible(false);
		contentPane.add(panelRececipnista);
		panelRececipnista.setLayout(null);
		
		lblRecepcionista = new JLabel("Recepcionista");
		lblRecepcionista.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecepcionista.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 33));
		lblRecepcionista.setBackground(new Color(255, 128, 0));
		lblRecepcionista.setBounds(0, 0, 376, 83);
		panelRececipnista.add(lblRecepcionista);


	}
	
	public static ImageIcon ajustarTamañoImg(String ruta, int ancho, int alto) {
        ImageIcon imagen = new ImageIcon((ruta));
        Image imagenOriginal = imagen.getImage();
        Image imagenAjustada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenAjustada);
    }//FIN AJUSTAR TAMAÑO IMG
}