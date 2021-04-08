package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.test.dto.Book;
import org.junit.Before;
import org.junit.Ignore;
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
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    RentController rentController;

    @Autowired
    BookController bookController;

    @Before
    public void setup()  {
        mockMvc = MockMvcBuilders
                .standaloneSetup(rentController, bookController)
                .build();
    }

    @Test
    public void rentBook_OK() throws Exception {
        //Given
      Book book = new Book(null, "BookTitle", "BookAuthor", Boolean.TRUE);

      Book aBook = this.createBook(book);

        //When
        MvcResult result = mockMvc.perform(patch("/book/rent/"+aBook.getUuid().toString()))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(result, is(notNullValue()));
        assertEquals(result.getResponse().getStatus(), MockHttpServletResponse.SC_OK);
    }

  @Test
  public void returnBook_OK() throws Exception {
    //Given
    Book book = new Book(null, "BookTitle", "BookAuthor", Boolean.FALSE);
    Book aBook = this.createBook(book);

    //When
    MvcResult result = mockMvc.perform(patch("/book/return/"+aBook.getUuid()))
        .andExpect(status().isOk())
        .andReturn();

    //then
    assertThat(result, is(notNullValue()));
    assertEquals(result.getResponse().getStatus(), MockHttpServletResponse.SC_OK);
  }

  @Test
  public void returnBook_NotAvialable() throws Exception {
    //Given
    Book book = new Book(null, "BookTitle", "BookAuthor", Boolean.TRUE);
    Book aBook = this.createBook(book);

    //When
    MvcResult result = mockMvc.perform(patch("/book/return/"+aBook.getUuid()))
      .andExpect(status().isNotFound())
      .andReturn();

  }

  private Book createBook(Book book) throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/book")
        .content(asJsonString(book))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
    String uuid = this.getValueFromResponse(mvcResult, "uuid");
    book.setUuid(UUID.fromString(uuid));
    return book;
  }

  private String getValueFromResponse(MvcResult resultSaveBook, String valueToRetrieve)
        throws UnsupportedEncodingException {
      String response = resultSaveBook.getResponse().getContentAsString();
      return JsonPath.parse(response).read(valueToRetrieve);
    }

    private static String asJsonString(final Object obj) {
      try {
        return new ObjectMapper().writeValueAsString(obj);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
}
