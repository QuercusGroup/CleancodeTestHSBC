package com.test.service;

import com.test.dto.Book;
import com.test.exception.BookException;
import com.test.model.BookEntity;
import com.test.respository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService{

    private ModelMapper modelMapper;
    public final BookRepository bookRepository;

    public List<Book> getBooks(){
        return modelMapper.map(bookRepository.findAll(), List.class);
    }

    public void saveBook(Book book){
        bookRepository.save(modelMapper.map(book, BookEntity.class));
    }

    public void deleteBook(UUID id) throws BookException {
        try{
            bookRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new BookException("Book can't be deleted,"+id);
        }
    }
}
