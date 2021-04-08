package com.test.service;

import com.test.dto.Book;
import com.test.exception.LibraryException;
import com.test.model.BookEntity;
import com.test.respository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentService {

    private ModelMapper modelMapper;
    public final BookRepository bookRepository;


    public Book rentBook(UUID id) throws LibraryException {
        Book bookAvailable = this.isBookAvailable(id);
        return modelMapper.map(this.changeBookAvailability(bookAvailable, Boolean.FALSE), Book.class);
    }

    public Book returnBook(UUID id) throws LibraryException {
        Book bookRented = this.isBookRented(id);
        return modelMapper.map(this.changeBookAvailability(bookRented, Boolean.TRUE), Book.class);
    }

    private Book isBookRented(UUID id) throws LibraryException {
        try{
            return modelMapper.map(bookRepository.findBookEntityByUuidAndAvailableFalse(id), Book.class);
        }catch (Exception e){
            throw new LibraryException("book not available for return."+id);
        }
    }

    private Book isBookAvailable(UUID id) throws LibraryException {
        try{
            return modelMapper.map(bookRepository.findBookEntityByUuidAndAvailableTrue(id), Book.class);
        }catch (Exception e){
            throw new LibraryException("book not available for rent."+id);
        }
    }

    private BookEntity changeBookAvailability(Book book, Boolean status){
        book.setAvailable(status);
        return bookRepository.save(modelMapper.map(book, BookEntity.class));
    }

}
