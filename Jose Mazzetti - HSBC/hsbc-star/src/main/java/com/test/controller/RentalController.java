package com.test.controller;

import com.test.dto.Book;
import com.test.exception.BookException;
import com.test.service.RentalService;
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
public class RentalController {

    public final RentalService rentalService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book rented OK."),
        @ApiResponse(responseCode = "204", description = "Book is not available.")
    })
    @PatchMapping("/book/rent/{id}")
    public @ResponseBody ResponseEntity<Book> rentBook(@PathVariable UUID id) {
        try{
            rentalService.rentBook(id);
        }catch (BookException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book returned OK."),
        @ApiResponse(responseCode = "204", description = "Book is not available.")
    })
    @PatchMapping("/book/return/{id}")
    public @ResponseBody ResponseEntity<Book> returnBook(@PathVariable UUID id) {
      try{
        rentalService.returnBook(id);
      }catch (BookException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity(id, HttpStatus.OK);
    }

}
