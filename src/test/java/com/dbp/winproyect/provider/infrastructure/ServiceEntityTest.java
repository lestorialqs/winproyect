package com.dbp.winproyect.provider.infrastructure;

import static com.dbp.winproyect.tag.domain.ServiceTag.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.tag.domain.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

public class ServiceEntityTest {

    @Mock
    private Provider provider;

    @Mock
    private Tag tag;

    @InjectMocks
    private ServiceEntity serviceEntity;

    @BeforeEach
    public void setUp() {
        serviceEntity = new ServiceEntity();
        serviceEntity.setName("Plumbing Service");
        serviceEntity.setDescription("Provides high-quality plumbing services.");
        serviceEntity.setAddress("123 Main St");
        serviceEntity.setSuggestedPrice(100.0);
        serviceEntity.setAvg_rating(4.5f);
        serviceEntity.setProvider(provider);
    }

    @Test
    void testServiceEntityCreation() {
        assertEquals("Plumbing Service", serviceEntity.getName());
        assertEquals("Provides high-quality plumbing services.", serviceEntity.getDescription());
        assertEquals("123 Main St", serviceEntity.getAddress());
        assertEquals(100.0, serviceEntity.getSuggestedPrice());
        assertEquals(4.5f, serviceEntity.getAvg_rating());
        assertNotNull(serviceEntity.getProvider());
    }

    @Test
    void testAddTag() {
        Tag newTag = new Tag();
        newTag.setName(FOTOGRAFIA);

        serviceEntity.getTags().add(newTag);

        assertTrue(serviceEntity.getTags().contains(newTag));
    }

    @Test
    void testRemoveTag() {
        Tag newTag = new Tag();
        newTag.setName(FOTOGRAFIA);

        serviceEntity.getTags().add(newTag);
        serviceEntity.getTags().remove(newTag);

        assertFalse(serviceEntity.getTags().contains(newTag));
    }

    @Test
    void testUpdateProvider() {
        Provider newProvider = mock(Provider.class);
        serviceEntity.setProvider(newProvider);

        assertEquals(newProvider, serviceEntity.getProvider());
    }

    @Test
    void testAverageRatingCalculation() {
        // Simular varios ratings
        Set<Float> ratings = new HashSet<>();
        ratings.add(4.0f);
        ratings.add(5.0f);
        ratings.add(3.5f);

        // Calcular el promedio
        float average = (float) ratings.stream().mapToDouble(Float::doubleValue).average().orElse(0.0);

        serviceEntity.setAvg_rating(average);

        assertEquals(4.17f, serviceEntity.getAvg_rating(), 0.01f); // Verifica que el promedio es correcto
    }

    @Test
    void testServiceEntityWithTags() {
        // Agregar múltiples tags al ServiceEntity
        Tag tag1 = new Tag();
        tag1.setName(ELECTRICIDAD);

        Tag tag2 = new Tag();
        tag2.setName(MANTENIMIENTO);

        serviceEntity.getTags().add(tag1);
        serviceEntity.getTags().add(tag2);

        assertEquals(2, serviceEntity.getTags().size());
        assertTrue(serviceEntity.getTags().contains(tag1));
        assertTrue(serviceEntity.getTags().contains(tag2));
    }
}
