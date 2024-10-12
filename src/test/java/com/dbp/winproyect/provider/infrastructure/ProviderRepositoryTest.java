package com.dbp.winproyect.provider.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import com.dbp.winproyect.provider.domain.Provider;
import org.junit.jupiter.api.Test;

class ProviderTest {

    @Test
    void testProviderCreation() {
        Provider provider = new Provider();
        provider.setRuc(987654321L);
        provider.setEstate(true);
        provider.setRating(4.5f);
        provider.setComission(0.2);

        // Atributos heredados de AppUser
        provider.setEmail("provider@example.com");
        provider.setPhoneNumber("1122334455");

        assertEquals(987654321L, provider.getRuc());
        assertTrue(provider.getEstate());
        assertEquals(4.5f, provider.getRating());
        assertEquals(0.2, provider.getComission());
        assertEquals("provider@example.com", provider.getEmail());
        assertEquals("1122334455", provider.getPhoneNumber());
    }
}

