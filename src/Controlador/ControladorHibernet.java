package Controlador;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import persistencias.Empleados;


public class ControladorHibernet {

	private   SessionFactory  sessionFactory;
	public ControladorHibernet() throws Exception{
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
	}
	public String sacarRoles(String nombreusuario, String contraseña) {
	    Session session = null;
	    String rol = null;
	    try {
	        session = sessionFactory.getCurrentSession();
	        session.beginTransaction();

	        Query<Empleados> q = session.createQuery("FROM Empleados WHERE username = :nombre AND password = :contraseña", Empleados.class);
	        q.setParameter("nombre", nombreusuario);
	        q.setParameter("contraseña", contraseña);

	        List<Empleados> empleados = q.getResultList();

	        if (!empleados.isEmpty()) {
	            rol = empleados.get(0).getRol();
	        }else {
	        	rol=null;
	        }
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session != null && session.getTransaction() != null) {
	            session.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return rol;
	}
	public List<Empleados> obtenerTodosLosUsuarios() {
	    Session session = null;
	    List<Empleados> usuarios = null;
	    try {
	        session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        
	        Query<Empleados> q = session.createQuery("FROM Empleados", Empleados.class);
	        usuarios = q.getResultList();

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session != null && session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return usuarios;
	}
	public void crearUsuario(String nombreusuario, String contraseña,String rol) {
		Session sesion=null;
		
		try{
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			Empleados empleados=new Empleados();
			empleados.setUsername(nombreusuario);
			empleados.setPassword(contraseña);
			empleados.setRol(rol);
			
			sesion.saveOrUpdate(empleados);
			
			sesion.getTransaction().commit();
			System.out.println("Añadido");
		}catch(Exception e) {
			if (sesion != null && sesion.getTransaction() != null) {
				sesion.getTransaction().rollback();
	        }
			e.printStackTrace();
		}finally {
			if(sesion!=null) {
				sesion.close();
			}
		}
		
	}
	public void eliminarUsuario(String nombreUsuario) {
	    Session sesion = null;
	    try {
	        sesion = sessionFactory.getCurrentSession();
	        sesion.beginTransaction();

	        Query<Empleados> query = sesion.createQuery("FROM Empleados WHERE username = :nombre", Empleados.class);
	        query.setParameter("nombre", nombreUsuario);
	        Empleados empleado = query.uniqueResult();
	        if (empleado != null) {
	            sesion.delete(empleado);
	            System.out.println("Usuario eliminado: " + nombreUsuario);
	        } else {
	            System.out.println("Usuario no encontrado: " + nombreUsuario);
	        }

	        sesion.getTransaction().commit();
	    } catch (Exception e) {
	        if (sesion != null && sesion.getTransaction() != null) {
	            sesion.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (sesion != null) {
	            sesion.close();
	        }
	    }
	}
	public void EditarUsuario(String nombreUsuarioActual, String nuevaContraseña, String nuevoRol) {
	    Session sesion = null;
	    try {
	        sesion = sessionFactory.getCurrentSession();
	        sesion.beginTransaction();

	        Query<Empleados> query = sesion.createQuery("FROM Empleados WHERE username = :nombreUsuario", Empleados.class);
	        query.setParameter("nombreUsuario", nombreUsuarioActual);
	        Empleados empleado = query.uniqueResult();

	        if (empleado != null) {
	        
	            empleado.setUsername(nombreUsuarioActual); 
	            empleado.setPassword(nuevaContraseña);   
	            empleado.setRol(nuevoRol);

	            sesion.saveOrUpdate(empleado);
	            sesion.getTransaction().commit();
	            System.out.println("Usuario actualizado correctamente.");
	        } else {
	            System.out.println("Usuario no encontrado: " + nombreUsuarioActual);
	        }
	    } catch (Exception e) {
	        if (sesion != null && sesion.getTransaction() != null) {
	            sesion.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (sesion != null) {
	            sesion.close();
	        }
	    }
	}
}
