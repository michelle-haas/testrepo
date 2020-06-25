package at.technikumwien.carwebapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Tag("integration-test")
public class CarResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void testRetrieveAll() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/resources/cars")
                .accept(MediaType.APPLICATION_JSON);

        // andere Variante als oben
        var response = mockMvc.perform(requestBuilder)
                .andReturn().getResponse();

        // bei dieser Variante brauchen wir einen Objectmapper
        var cars = om.readValue(
                response.getContentAsString(),
                // was wollen wir zurück:
                new TypeReference<List<Car>>(){}
        );

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(response.getContentType()).containsIgnoringCase(MediaType.APPLICATION_JSON_VALUE);
        assertEquals(2, cars.size());
    }
}
