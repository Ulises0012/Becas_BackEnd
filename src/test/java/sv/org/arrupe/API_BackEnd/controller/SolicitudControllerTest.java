package sv.org.arrupe.API_BackEnd.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import sv.org.arrupe.API_BackEnd.security.SecurityTestConfig;
import sv.org.arrupe.API_BackEnd.service.EstudioSocioeconomicoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SolicitudController.class)
@Import(SecurityTestConfig.class)
public class SolicitudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudioSocioeconomicoService estudioSocioeconomicoService;

    @Test
    @WithMockUser(roles = "USER")
    void testGetSolicitudes() throws Exception {
        mockMvc.perform(get("/api/solicitudes"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSolicitudesUnauthorized() throws Exception {
        mockMvc.perform(get("/api/solicitudes"))
                .andExpect(status().isUnauthorized());
    }
}