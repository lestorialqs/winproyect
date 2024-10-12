/*package com.dbp.winproyect.provider.infrastructure;

import org.junit.Test;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.mockito.Mockito.when;

@Test
public void testCreateClient() throws Exception {
    // Simular el comportamiento del servicio
    ClientService clientService;
    when(clientService.createClient(any(Client.class))).thenReturn(mockClient);

    // Realizar la solicitud POST
    mockMvc.perform(post("/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"dni\":12345678,\"showAds\":true}"))
            .andExpect(status().isCreated());

    // Verificar que el servicio fue llamado
    verify(clientService, times(1)).createClient(any(Client.class));
}
*/