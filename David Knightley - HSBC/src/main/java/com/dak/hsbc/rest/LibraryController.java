package com.dak.hsbc.rest;

import com.dak.hsbc.library.Book;
import com.dak.hsbc.library.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("library")
public class LibraryController {


    private Library library;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Initialize library
        library = new Library();
        library.loadCatalogue();
    }

    @GetMapping(value = "/search")
    @ResponseBody
    public List<Book> searchTitle(@RequestParam(name = "s") String keyWord) {
        return library.searchByTitle(keyWord);
    }

    @GetMapping(value = "/author")
    @ResponseBody
    public List<Book> getBooksForAuthor(@RequestParam String name) {
        return library.getBooksByAuthor(name);
    }

    @GetMapping(value = "/check/{isbn}")
    @ResponseBody
    public boolean checkAvailability(@PathVariable String isbn) {
        return library.isAvailable(isbn);
    }

    @GetMapping(value = "/withdraw/{isbn}")
    @ResponseBody
    public String withdrawBook(@PathVariable String isbn) {
        return library.withdrawBook(isbn);
    }

    @GetMapping(value = "/return/{isbn}")
    @ResponseBody
    public boolean returnBook(@PathVariable String isbn) {
        return library.returnBook(isbn);
    }

}
