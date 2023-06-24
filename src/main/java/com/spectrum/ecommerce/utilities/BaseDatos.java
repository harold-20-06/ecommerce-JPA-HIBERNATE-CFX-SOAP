package com.spectrum.ecommerce.utilities;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BaseDatos {

    private static final String PERSISTENCE_UNIT_NAME = "ecommercePU";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory==null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            System.out.println("Conexión exitosa a la base de datos");

        }
        return factory;
    }
    public static void shutdown() {
        if (factory!=null) {
            factory.close();
        }
    }
}
