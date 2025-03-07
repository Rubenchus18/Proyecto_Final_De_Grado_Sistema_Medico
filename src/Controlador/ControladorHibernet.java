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
}
