package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.test.dto.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    BookController bookController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .build();
    }

    @Test
    public void saveBook_OK() throws Exception {
        //Given
        Book aBook = new Book(null, "bookname", Boolean.TRUE);
        //When
        MvcResult result = mockMvc.perform(post("/book")
            .content(asJsonString(aBook))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        //then
        assertThat(result, is(notNullValue()));
        assertEquals(result.getResponse().getStatus(), MockHttpServletResponse.SC_CREATED);
    }

    @Test
    public void getBook_OK() throws Exception {
        //Given
        Book aBook = new Book(null, "bookname", Boolean.TRUE);
        mockMvc.perform(post("/book")
            .content(asJsonString(aBook))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        //When
        MvcResult result = mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(result, is(notNullValue()));
        assertEquals(result.getResponse().getStatus(), MockHttpServletResponse.SC_OK);
    }

    @Test
    public void deleteBook_OK() throws Exception {
        //Given
        Book aBook = new Book(null, "bookname", Boolean.TRUE);
        MvcResult resultSaveBook = mockMvc.perform(post("/book")
            .content(asJsonString(aBook))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

        String uuid = this.getValueFromResponse(resultSaveBook, "uuid");

        //when
        MvcResult resultDelete = mockMvc.perform(delete("/book/"+UUID.fromString(uuid))
            .content(asJsonString(aBook))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn();

        //then
        assertThat(resultDelete, is(notNullValue()));
        assertEquals(resultDelete.getResponse().getStatus(), MockHttpServletResponse.SC_ACCEPTED);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getValueFromResponse(MvcResult resultSaveBook, String valueToRetrieve)
        throws UnsupportedEncodingException {
        String response = resultSaveBook.getResponse().getContentAsString();
        return JsonPath.parse(response).read(valueToRetrieve);
    }

}
