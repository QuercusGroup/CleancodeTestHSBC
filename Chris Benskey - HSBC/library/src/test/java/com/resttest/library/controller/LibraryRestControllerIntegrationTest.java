
package com.resttest.library.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttest.library.model.entity.Borrowing;
import com.resttest.library.model.entity.BorrowingStatus;
import com.resttest.library.model.request.BorrowingRequest;
import com.resttest.library.model.request.StoreBookRequest;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryRestControllerIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkAllEndpointsIntegrationTest() throws Exception {

        // fetch empty books list
        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));

        // store some books on the shelves

        final StoreBookRequest storeBookRequest = new StoreBookRequest();
        storeBookRequest.setISBN("9781735113012");
        storeBookRequest.setQuantity(1);
        this.mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(storeBookRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("The Best Way to Make a Friend, Is to Be a Friend")));


        // fetch empty books list
        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().string(containsString("The Best Way to Make a Friend, Is to Be a Friend")));

        this.mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(storeBookRequest)))
                .andExpect(status().isConflict());


        // borrow a book

        final BorrowingRequest borrowingRequest = new BorrowingRequest();
        borrowingRequest.setBorrowerName("Boris Johnson");
        borrowingRequest.setStatus(BorrowingStatus.LOAN);
        MvcResult result = this.mockMvc.perform(post("/book/{ISBN}/borrowing", "9781735113012")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andExpect(status().isOk()).andReturn();
        final Borrowing successfulBorrowing = objectMapper.readValue(result.getResponse().getContentAsString(), Borrowing.class);
        // make sure list reflects checkout

        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"available\":0")));

        // check that a second borrowing throws an error
        this.mockMvc.perform(post("/book/{ISBN}/borrowing", "9781735113012")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(borrowingRequest)))
                .andExpect(status().isUnprocessableEntity());

        // return book
        final BorrowingRequest returnRequest = new BorrowingRequest();
        borrowingRequest.setStatus(BorrowingStatus.RETURNED);
        this.mockMvc.perform(put("/book/{ISBN}/borrowing/{borrowingId}", "9781735113012",
                successfulBorrowing.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(returnRequest)))
                .andExpect(status().isOk());

        // make sure list reflects return
        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"available\":1")));
    }
}