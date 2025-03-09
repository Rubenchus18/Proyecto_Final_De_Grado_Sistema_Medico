package Controlador;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import persistencias.Empleados;
import persistencias.Pacientes;
import persistencias.Medicos;
import persistencias.Recepcionistas;

public class ControladorHibernet {

    private SessionFactory sessionFactory;

    public ControladorHibernet() throws Exception {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Método para obtener el rol de un usuario
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
            } else {
                rol = null;
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

    // Método para obtener todos los usuarios (Empleados)
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

    // Método para crear un nuevo usuario (Empleado)
    public int crearUsuario(String nombreusuario, String contraseña, String rol) {
        Session sesion = null;
        Integer id = null;
        try {
            sesion = sessionFactory.getCurrentSession();
            sesion.beginTransaction();

            Empleados empleado = new Empleados();
            empleado.setUsername(nombreusuario);
            empleado.setPassword(contraseña);
            empleado.setRol(rol);

            sesion.save(empleado);
            sesion.getTransaction().commit();
            id = empleado.getId(); 
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
        return id;
    }

    // Método para eliminar un usuario (Empleado)
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

    // Método para editar un usuario (Empleado)
    public void EditarUsuario(String nuevoNombreUsuario, String nuevaContraseña, String nuevoRol) {
        Session sesion = null;
        try {
            sesion = sessionFactory.getCurrentSession();
            sesion.beginTransaction();

            Query<Empleados> query = sesion.createQuery("FROM Empleados WHERE username = :nombreUsuario", Empleados.class);
            query.setParameter("nombreUsuario", nuevoNombreUsuario);
            Empleados empleado = query.uniqueResult();

            if (empleado != null) {
                empleado.setUsername(nuevoNombreUsuario);
                empleado.setPassword(nuevaContraseña);
                empleado.setRol(nuevoRol);
                
                sesion.saveOrUpdate(empleado);
                sesion.getTransaction().commit();
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("Usuario no encontrado: " + nuevoNombreUsuario);
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

    
    public void crearPaciente(String nombre, int empleadoId) {
        Session session = null;
        Empleados emple=new Empleados();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query q=session.createQuery("FROM Empleados WHERE username=: nombrea");
            q.setParameter("nombrea", nombre);
            emple=(Empleados) q.getSingleResult();
            
            Pacientes paciente = new Pacientes();
            paciente.setNombre(nombre);
            paciente.setEmpleados(emple);

            session.save(paciente);
            session.getTransaction().commit();
            System.out.println("Paciente creado correctamente.");
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void crearMedico(String nombre, int empleadoId) {
        Session session = null;
        Empleados emple=new Empleados();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query q=session.createQuery("FROM Empleados WHERE username=: nombrea");
            q.setParameter("nombrea", nombre);
            emple=(Empleados) q.getSingleResult();
            
            Medicos medico = new Medicos();
            medico.setNombre(nombre);
            medico.setEmpleados(emple); 

            session.save(medico);
            session.getTransaction().commit();
            System.out.println("Médico creado correctamente.");
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void crearRecepcionista(String nombre) {
        Session session = null;
        Empleados emple=new Empleados();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

         
            Query q=session.createQuery("FROM Empleados WHERE username=: nombrea");
            q.setParameter("nombrea", nombre);
            emple=(Empleados) q.getSingleResult();
            
            Recepcionistas recepcionista = new Recepcionistas();
            recepcionista.setNombre(nombre);
            recepcionista.setEmpleados(emple);;  

            session.save(recepcionista);
            session.getTransaction().commit();
            System.out.println("Recepcionista creado correctamente.");
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


}