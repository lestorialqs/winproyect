package com.dbp.winproyect.provider.infrastructure;
import com.dbp.winproyect.provider.infrastructure.ProviderRepository;
import com.dbp.winproyect.AbstractContainerBaseTest;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProviderRepository providerRepository;  // No estás llamando save estáticamente


    @Test
    void testCreateReviewWithClientAndServiceEntity() {
        // Crear instancia de Provider y guardarla
        Provider provider = new Provider();
        provider.setRuc(912321321213L);
        provider.setEmail("hola@utec.edu.pe");

        provider = providerRepository.save(provider);  // Guardar el Provider antes de asociarlo a ServiceEntity

        // Crear instancia de ServiceEntity y asignar el Provider guardado
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName("Food Delivery Service");
        serviceEntity.setDescription("Fast and reliable food delivery.");
        serviceEntity.setProvider(provider);

        serviceEntity = serviceEntityRepository.save(serviceEntity);  // Guardar ServiceEntity

        // Crear instancia de Client y guardarla
        Client client = new Client();
        client.setFirstName("Alice");
        client.setLastName("Smith");
        client.setEmail("alice.smith@example.com");
        client = clientRepository.save(client);  // Guardar Client

        // Crear instancia de Review y asociarla con el ServiceEntity y el Client
        Review review = new Review();
        review.setRating(5);
        review.setComment("Great service, fast delivery!");
        review.setDate(ZonedDateTime.now());
        review.setServiceEntity(serviceEntity);
        review.setClient(client);

        // Guardar la Review
        Review savedReview = reviewRepository.save(review);

        // Verificar que la Review y sus relaciones se guardaron correctamente
        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isGreaterThan(0);
        assertThat(savedReview.getServiceEntity()).isEqualTo(serviceEntity);
        assertThat(savedReview.getClient()).isEqualTo(client);
    }


    @Test
    void testDeleteReviewById() {
        // Crear una instancia de Provider y guardarla primero
        Provider provider = new Provider();
        provider = providerRepository.save(provider);  // Guardar el Provider primero

        // Crear una instancia de ServiceEntity y asignar el Provider guardado
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setProvider(provider);  // Asignar el Provider guardado
        serviceEntity.setName("Electrician Service");
        serviceEntity.setDescription("Electrical repairs and installations.");
        serviceEntity = serviceEntityRepository.save(serviceEntity);  // Guardar ServiceEntity

        // Crear una instancia de Client
        Client client = new Client();
        client.setFirstName("Bob");
        client.setLastName("Johnson");
        client.setEmail("bob.johnson@example.com");
        client = clientRepository.save(client);  // Guardar Client

        // Crear una Review
        Review review = new Review();
        review.setRating(4);
        review.setComment("Good service, but could be faster.");
        review.setDate(ZonedDateTime.now());
        review.setServiceEntity(serviceEntity);  // Asignar ServiceEntity a la Review
        review.setClient(client);  // Asignar Client a la Review
        review = reviewRepository.save(review);  // Guardar Review

        // Verificar que la Review se ha guardado
        Optional<Review> foundReview = reviewRepository.findById(review.getId());
        assertThat(foundReview).isPresent();

        // Eliminar la Review por ID
        reviewRepository.deleteById(review.getId());

        // Verificar que la Review ha sido eliminada
        Optional<Review> deletedReview = reviewRepository.findById(review.getId());
        assertThat(deletedReview).isNotPresent();
    }

    @Test
    void testUpdateReview() {
        // Crear y guardar un Provider
        Provider provider = new Provider();
        provider.setRuc(123345787L);
        provider = providerRepository.save(provider);  // Guardar Provider

        // Crear y guardar una ServiceEntity
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName("Car Repair Service");
        serviceEntity.setDescription("Provides car repairs and maintenance.");
        serviceEntity.setProvider(provider); // Asignar el Provider
        serviceEntity = serviceEntityRepository.save(serviceEntity);  // Guardar ServiceEntity

        // Crear y guardar un Client
        Client client = new Client();
        client.setFirstName("Charlie");
        client.setLastName("Brown");
        client.setEmail("charlie.brown@example.com");
        client = clientRepository.save(client);  // Guardar Client

        // Crear y guardar una Review
        Review review = new Review();
        review.setRating(3);
        review.setComment("Average service, nothing special.");
        review.setDate(ZonedDateTime.now());
        review.setServiceEntity(serviceEntity);
        review.setClient(client);
        review = reviewRepository.save(review);  // Guardar Review

        // Actualizar la Review
        review.setRating(4);
        review.setComment("Improved service after a second try.");
        review.setEdited(true);  // Indicar que ha sido editada
        Review updatedReview = reviewRepository.save(review);  // Guardar cambios

        // Verificar los cambios
        assertThat(updatedReview.getRating()).isEqualTo(4);
        assertThat(updatedReview.getComment()).isEqualTo("Improved service after a second try.");
        assertThat(updatedReview.getEdited()).isTrue();
    }

    @Test

    void testFindAllReviewsByClient() {
        // Crear un Provider
        Provider provider = new Provider();
        provider.setRuc(123456789L);
        provider = providerRepository.save(provider); // Guardar Provider

        // Crear un Client
        Client client = new Client();
        client.setFirstName("David");
        client.setLastName("Miller");
        client.setEmail("david.miller@example.com");
        client = clientRepository.save(client);

        // Crear múltiples Reviews asociadas al mismo Client
        ServiceEntity service1 = new ServiceEntity();
        service1.setName("Cleaning Service");
        service1.setDescription("Professional home cleaning.");
        service1.setProvider(provider); // Asignar el Provider
        service1 = serviceEntityRepository.save(service1); // Guardar ServiceEntity

        Review review1 = new Review();
        review1.setRating(5);
        review1.setComment("Fantastic cleaning service!");
        review1.setDate(ZonedDateTime.now());
        review1.setServiceEntity(service1);
        review1.setClient(client);
        reviewRepository.save(review1);

        ServiceEntity service2 = new ServiceEntity();
        service2.setName("Gardening Service");
        service2.setDescription("Garden maintenance and landscaping.");
        service2.setProvider(provider); // Asignar el Provider
        service2 = serviceEntityRepository.save(service2); // Guardar ServiceEntity

        Review review2 = new Review();
        review2.setRating(4);
        review2.setComment("Good work, but could be more punctual."); // Con punto final
        review2.setDate(ZonedDateTime.now());
        review2.setServiceEntity(service2);
        review2.setClient(client);
        reviewRepository.save(review2);

        // Recuperar todas las Reviews asociadas a este Client
        List<Review> clientReviews = reviewRepository.findAllByClient(client);

        // Verificar que se han encontrado todas las Reviews
        assertThat(clientReviews).hasSize(2);
        assertThat(clientReviews).extracting(Review::getComment)
                .contains("Fantastic cleaning service!", "Good work, but could be more punctual."); // Con punto final
    }
}
