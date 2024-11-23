package p.edu.i202123054.crud;

import jakarta.persistence.*;
import p.edu.i202123054.Entidades.Country;

public class JPAFind {

    public static void main(String[] args) {
        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Buscar el país con el código "PER"
            Country peru = em.find(Country.class, "PER");

            if (peru != null) {
                System.out.println("Ciudades peruanas con población > 700,000:");

                // Usar lambda para filtrar las ciudades con población > 700,000
                peru.getCities().stream()
                        .filter(city -> city.getPopulation() > 700000)
                        .forEach(city -> System.out.println(city.getName()));

            } else {
                System.out.println("El país con el código 'PER' no fue encontrado.");
            }

            // Confirmar la transacción (aunque no estamos modificando nada)
            em.getTransaction().commit();

        } catch (Exception e) {
            // Manejo de errores
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
