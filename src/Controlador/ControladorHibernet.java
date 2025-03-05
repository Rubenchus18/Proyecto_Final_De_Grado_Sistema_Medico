package Controlador;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ControladorHibernet {
	public static void main(String[]args) {
		ControladorHibernet c=new ControladorHibernet();
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration=new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory=configuration.buildSessionFactory();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=sessionFactory) {
				sessionFactory.close();
			}
		}
}
}