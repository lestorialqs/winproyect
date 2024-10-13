package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.review.dto.ReviewDtoCreateRequest;
import com.dbp.winproyect.review.dto.ReviewDtoEditRequest;
import com.dbp.winproyect.review.infrastructure.ReviewRepository;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReview() {
        // Preparando datos de prueba
        ReviewDtoCreateRequest reviewDto = new ReviewDtoCreateRequest();
        reviewDto.setRating(5);
        reviewDto.setComment("Great service!");
        reviewDto.setUserId(1L);

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);

        Client client = new Client();
        client.setId(1L);

        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.of(serviceEntity));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(reviewRepository.save(any(Review.class))).thenAnswer(i -> i.getArgument(0));

        // Ejecutando el método a probar
        Review savedReview = reviewService.saveReview(reviewDto, 1L);

        // Verificaciones
        assertEquals(5, savedReview.getRating());
        assertEquals("Great service!", savedReview.getComment());
        assertEquals(client, savedReview.getClient());
        assertEquals(serviceEntity, savedReview.getServiceEntity());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testSaveReview_ServiceNotFound() {
        ReviewDtoCreateRequest reviewDto = new ReviewDtoCreateRequest();
        reviewDto.setUserId(1L);

        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reviewService.saveReview(reviewDto, 1L));
    }
    @Test
    void testEditReview() {
        ReviewDtoEditRequest reviewDto = new ReviewDtoEditRequest();
        reviewDto.setRating(4);
        reviewDto.setComment("Good, but could be better!");

        Review existingReview = new Review();
        existingReview.setId(1L);
        existingReview.setRating(5);
        existingReview.setComment("Great service!");

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));

        // Ejecutando el método
        reviewService.editReview(reviewDto, 1L, 1L);

        // Verificaciones
        assertEquals(4, existingReview.getRating());
        assertEquals("Good, but could be better!", existingReview.getComment());
        verify(reviewRepository, times(1)).save(existingReview);
    }
    @Test
    void testDeleteReview() {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);

        Review review = new Review();
        review.setId(1L);
        review.setServiceEntity(serviceEntity);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.of(serviceEntity));

        // Ejecutando el método
        reviewService.deleteReview(1L, 1L);

        // Verificación
        verify(reviewRepository, times(1)).delete(review);
    }
    @Test
    void testSaveReview_ClientNotFound() {
        ReviewDtoCreateRequest reviewDto = new ReviewDtoCreateRequest();
        reviewDto.setRating(5);
        reviewDto.setComment("Amazing service!");
        reviewDto.setUserId(1L);

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);

        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.of(serviceEntity));
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(ResourceNotFoundException.class, () -> reviewService.saveReview(reviewDto, 1L));

        // Verificación de que no se llamó al método save de reviewRepository
        verify(reviewRepository, never()).save(any(Review.class));
    }
    @Test
    void testEditReview_ReviewNotFound() {
        ReviewDtoEditRequest reviewDto = new ReviewDtoEditRequest();
        reviewDto.setRating(4);
        reviewDto.setComment("Service improved!");

        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(ResourceNotFoundException.class, () -> reviewService.editReview(reviewDto, 1L, 1L));

        // Verificación de que no se llamó al método save de reviewRepository
        verify(reviewRepository, never()).save(any(Review.class));
    }

    @Test
    void testDeleteReview_ReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1L, 1L));

        // Verificación de que no se llamó al método delete de reviewRepository
        verify(reviewRepository, never()).delete(any(Review.class));
    }
    @Test
    void testDeleteReview_ServiceNotFound() {
        Review review = new Review();
        review.setId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(serviceEntityRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificación de excepción
        assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1L, 1L));

        // Verificación de que no se llamó al método delete de reviewRepository
        verify(reviewRepository, never()).delete(any(Review.class));
    }


}
