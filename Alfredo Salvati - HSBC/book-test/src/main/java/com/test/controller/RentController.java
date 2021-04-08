package com.test.controller;

import com.test.dto.Book;
import com.test.exception.LibraryException;
import com.test.service.RentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
  Controller that rentals for rent/return.
 */

@RestController
@AllArgsConstructor
public class RentController {

    public final RentService rentService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book rented OK."),
        @ApiResponse(responseCode = "404", description = "Book is not available.")
    })
    @PatchMapping("/book/rent/{id}")
    public @ResponseBody ResponseEntity<Book> rentBook(@PathVariable String id) {
        try{
            rentService.rentBook(UUID.fromString(id));
        }catch (LibraryException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book returned OK."),
        @ApiResponse(responseCode = "404", description = "Book is not available.")
    })
    @PatchMapping("/book/return/{id}")
    public @ResponseBody ResponseEntity<Book> returnBook(@PathVariable UUID id) {
      try{
        rentService.returnBook(id);
      }catch (LibraryException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity(id, HttpStatus.OK);
    }

}
