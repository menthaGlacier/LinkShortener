package com.menthaglacier.linkshortener.api.controller;

import com.jayway.jsonpath.JsonPath;
import com.menthaglacier.linkshortener.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class LinkControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsLinkAndRedirects() throws Exception {
        String shortCode = createLinkAndReturnShortCode();

        mockMvc.perform(get("/" + shortCode))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "https://example.com"));
    }

    @Test
    void deletesLinkAndReturnsNoContent() throws Exception {
        String shortCode = createLinkAndReturnShortCode();

        mockMvc.perform(delete("/api/v1/links" + "/" + shortCode))
                .andExpect(status().isNoContent());
    }

    private String createLinkAndReturnShortCode() throws Exception {
        String body = mockMvc.perform(post("/api/v1/links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"originalUrl": "%s"}
                                """.formatted("https://example.com")))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(body, "$.shortCode");
    }
}
