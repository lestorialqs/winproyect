package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.review.dto.ReviewDtoCreateRequest;
import com.dbp.winproyect.review.dto.ReviewDtoEditRequest;
import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReviewServiceTests {

    @Mock
    private ReviewRepository reviewRepository;


    @Mock
    private ServiceEntityRepository serviceEntityRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private ServiceEntity serviceEntity;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear un objeto ServiceEntity de prueba
        serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);

        // Crear un objeto Client de prueba
        client = new Client();
        client.setId(1L);

        // Crear un objeto Review de prueba
        review = new Review();
        review.setId(1L);
        review.setRating(5);
        review.setComment("Great service!");
        review.setDate(ZonedDateTime.now());
        review.setServiceEntity(serviceEntity);
        review.setClient(client);
    }

    @Test
    void testSaveReview_Success() {
        // Crear un DTO para la creación de la review
        ReviewDtoCreateRequest reviewDtoCreateRequest = new ReviewDtoCreateRequest();
        reviewDtoCreateRequest.setRating(5);
        reviewDtoCreateRequest.setComment("Great service!");
        reviewDtoCreateRequest.setUserId(1L);

        // Simular la búsqueda del ServiceEntity y Client
        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.of(serviceEntity));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // Ejecutar el método a probar
        Review result = reviewService.saveReview(reviewDtoCreateRequest, 1L);

        // Verificar que el resultado no es nulo y que tiene los valores esperados
        assertNotNull(result);
        assertEquals(5, result.getRating());
        assertEquals("Great service!", result.getComment());
        assertEquals(serviceEntity, result.getServiceEntity());
        assertEquals(client, result.getClient());

        // Verificar que se llamaron los repositorios
        verify(serviceEntityRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testSaveReview_ServiceNotFound() {
        // Crear un DTO para la creación de la review
        ReviewDtoCreateRequest reviewDtoCreateRequest = new ReviewDtoCreateRequest();
        reviewDtoCreateRequest.setRating(5);
        reviewDtoCreateRequest.setComment("Great service!");
        reviewDtoCreateRequest.setUserId(1L);

        // Simular que el ServiceEntity no existe
        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reviewService.saveReview(reviewDtoCreateRequest, 1L));

        // Verificar que el repositorio de clientes no fue llamado
        verify(clientRepository, never()).findById(1L);
    }

    @Test
    void testSaveReview_ClientNotFound() {
        // Crear un DTO para la creación de la review
        ReviewDtoCreateRequest reviewDtoCreateRequest = new ReviewDtoCreateRequest();
        reviewDtoCreateRequest.setRating(5);
        reviewDtoCreateRequest.setComment("Great service!");
        reviewDtoCreateRequest.setUserId(1L);

        // Simular la búsqueda del ServiceEntity
        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.of(serviceEntity));
        // Simular que el Client no existe
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reviewService.saveReview(reviewDtoCreateRequest, 1L));

        // Verificar que se llamaron los repositorios
        verify(serviceEntityRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllReviewsByServiceId_Success() {
        // Simular la búsqueda de reviews
        Page<Review> reviewsPage = mock(Page.class);
        when(reviewRepository.findByServiceEntityId(1L, Pageable.unpaged())).thenReturn(reviewsPage);

        // Ejecutar el método a probar
        Page<Review> result = reviewService.getAllReviewsByServiceId(1L, Pageable.unpaged());

        // Verificar que el resultado no es nulo
        assertNotNull(result);

        // Verificar que se llamó al repositorio de reviews
        verify(reviewRepository, times(1)).findByServiceEntityId(1L, Pageable.unpaged());
    }

    @Test
    void testEditReview_Success() {
        // Crear un DTO para editar la review
        ReviewDtoEditRequest reviewDtoEditRequest = new ReviewDtoEditRequest();
        reviewDtoEditRequest.setRating(4);
        reviewDtoEditRequest.setComment("Good service!");

        // Simular la búsqueda de la review a editar
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        // Ejecutar el método a probar
        reviewService.editReview(reviewDtoEditRequest, 1L, 1L);

        // Verificar que los valores fueron actualizados
        assertEquals(4, review.getRating());
        assertEquals("Good service!", review.getComment());

        // Verificar que se guardó la review editada
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void testEditReview_NotFound() {
        // Crear un DTO para editar la review
        ReviewDtoEditRequest reviewDtoEditRequest = new ReviewDtoEditRequest();
        reviewDtoEditRequest.setRating(4);
        reviewDtoEditRequest.setComment("Good service!");

        // Simular que la review no existe
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reviewService.editReview(reviewDtoEditRequest, 1L, 1L));

        // Verificar que el repositorio de reviews fue llamado
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteReview_Success() {
        // Simular que el servicio y la review existen
        when(serviceEntityRepository.existsById(1L)).thenReturn(true);
        when(reviewRepository.existsById(1L)).thenReturn(true);

        // Ejecutar el método a probar
        reviewService.deleteReview(1L, 1L);

        // Verificar que se llamó a deleteById
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReview_ServiceNotFound() {
        // Simular que el servicio no existe
        when(serviceEntityRepository.existsById(1L)).thenReturn(false);

        // Verificar que se lanza una excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1L, 1L));
    }

    @Test
    void testDeleteReview_ReviewNotFound() {
        // Simular que el servicio existe y la review no
        when(serviceEntityRepository.existsById(1L)).thenReturn(true);
        when(reviewRepository.existsById(1L)).thenReturn(false);

        // Verificar que se lanza una excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1L, 1L));
    }
}
