package p.edu.i202123054.crud;

import jakarta.persistence.*;
import p.edu.i202123054.Entidades.Country;

public class JPARemove {

    public static void main(String[] args) {
        // Crear el EntityManagerFactory y EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cl1-jpa-pu");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Buscar el país con código "IMAG"
            Country country = em.find(Country.class, "IMAG");

            if (country != null) {
                // Eliminar el país (esto eliminará las ciudades y lenguajes debido a la cascada)
                em.remove(country);
                System.out.println("El país IMAG y sus ciudades y lenguajes fueron eliminados.");
            } else {
                System.out.println("El país con el código 'IMAG' no fue encontrado.");
            }

            // Confirmar la transacción
            em.getTransaction().commit();

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
