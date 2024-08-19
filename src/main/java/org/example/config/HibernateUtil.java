package org.example.config;


import org.example.model.Planet;
import org.flywaydb.core.Flyway;
import org.example.model.Client;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static HibernateUtil INSTANCE;
    @Getter
    private SessionFactory sessionFactory;

    private HibernateUtil() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        PropertyReader.getProperty("hibernate.connection.url"),
                        PropertyReader.getProperty("hibernate.connection.username"),
                        PropertyReader.getProperty("hibernate.connection.password")
                )
                .locations("db/migrations")
                .load();
        flyway.migrate();
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new HibernateUtil();
        }
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
