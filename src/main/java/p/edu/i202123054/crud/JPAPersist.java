package p.edu.i202123054.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import p.edu.i202123054.Entidades.City;
import p.edu.i202123054.Entidades.Country;
import p.edu.i202123054.Entidades.Language;

import java.util.Arrays;

public class JPAPersist {

    public static void main(String[] args) {
        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Crear el país imaginario
            Country newCountry = new Country();
            newCountry.setCode("IMAG");  // Código único para el país
            newCountry.setName("Imaginario");
            newCountry.setContinent("Asia");
            newCountry.setRegion("Fantasy Region");
            newCountry.setPopulation(1000000);

            // Crear las ciudades
            City city1 = new City();
            city1.setName("Fantasia");
            city1.setPopulation(500000);
            city1.setCountry(newCountry);  // Relacionar con el país

            City city2 = new City();
            city2.setName("Dreamland");
            city2.setPopulation(300000);
            city2.setCountry(newCountry);

            City city3 = new City();
            city3.setName("Wonderland");
            city3.setPopulation(200000);
            city3.setCountry(newCountry);

            // Crear los lenguajes
            Language lang1 = new Language();
            lang1.setLanguage("Imaginario");
            lang1.setOfficial(true);
            lang1.setCountry(newCountry);  // Relacionar con el país

            Language lang2 = new Language();
            lang2.setLanguage("Sueños");
            lang2.setOfficial(false);
            lang2.setCountry(newCountry);

            // Asociar las ciudades y lenguajes al país
            newCountry.setCities(Arrays.asList(city1, city2, city3));
            newCountry.setLanguages(Arrays.asList(lang1, lang2));

            // Persistir el país (las ciudades y lenguajes se persistirán automáticamente)
            em.persist(newCountry);

            // Confirmar la transacción
            em.getTransaction().commit();
            System.out.println("¡País imaginario registrado exitosamente!");

        } catch (Exception e) {
            // Manejo de errores y revertir la transacción si es necesario
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            // Cerrar el EntityManager
            em.close();
            emf.close();
        }
    }
}
