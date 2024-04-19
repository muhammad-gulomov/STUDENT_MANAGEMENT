package uz.muhammadtrying.run_out_of_names.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        ServletContextListener.super.contextInitialized(sce);
    }
}
