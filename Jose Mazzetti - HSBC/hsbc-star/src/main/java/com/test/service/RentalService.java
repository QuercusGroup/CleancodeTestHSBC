package com.test.service;

import com.test.dto.Book;
import com.test.exception.BookException;
import com.test.model.BookEntity;
import com.test.respository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalService {

    private ModelMapper modelMapper;
    public final BookRepository bookRepository;

    /*
        Rent and return can be later on refactored in a single method.
     */
    public Book rentBook(UUID id) throws BookException {
        Book bookAvailable = this.isBookAvailable(id);
        if (bookAvailable.getAvailable()){
            return modelMapper.map(this.persistBook(bookAvailable, Boolean.FALSE), Book.class);
        }else
            throw new BookException("Book not available for rent"+id);
    }

    public Book returnBook(UUID id) throws BookException {
        Book bookRented = this.isBookRented(id);
        if (bookRented.getAvailable().equals(Boolean.FALSE)){
            return modelMapper.map(this.persistBook(bookRented, Boolean.TRUE), Book.class);
        } else
            throw new BookException("Book not available for return"+id);
    }

    private Book isBookRented(UUID id) throws BookException {
        try{
            return modelMapper.map(bookRepository.findBookEntityByUuidAndAvailableFalse(id), Book.class);
        }catch (Exception e){
            throw new BookException("book not available for return."+id);
        }
    }

    private Book isBookAvailable(UUID id) throws BookException {
        try{
            return modelMapper.map(bookRepository.findBookEntityByUuidAndAvailableTrue(id), Book.class);
        }catch (Exception e){
            throw new BookException("book not available for rent."+id);
        }
    }

    private BookEntity persistBook(Book book, Boolean status){
        book.setAvailable(status);
        return bookRepository.save(modelMapper.map(book, BookEntity.class));
    }

}
