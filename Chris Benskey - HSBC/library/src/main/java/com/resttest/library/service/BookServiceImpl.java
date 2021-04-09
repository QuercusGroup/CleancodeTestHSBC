package com.resttest.library.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.resttest.library.model.entity.Author;
import com.resttest.library.model.entity.Book;
import com.resttest.library.model.entity.Borrowing;
import com.resttest.library.model.googleapi.VolumeInfo;
import com.resttest.library.model.request.BorrowingRequest;
import com.resttest.library.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> fetchAllBooks() {
        final List<Book> books = bookRepository.findAll();
        books.stream().forEach(b -> {
            b.setAvailable(b.getQuantity() - b.getBorrowed());
        });
        return books;
    }

    @Override
    public Book addVolumeToLibrary(final String ISBN, final VolumeInfo volumeInfo, final Integer quantity) {
        final Optional<Book> book = bookRepository.findByISBNForUpdate(ISBN);
        book.ifPresent(b -> {
            LOGGER.error("Volume already present in library {} {}", ISBN, volumeInfo);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already in library");
        });

        final Book bookToPersist = new Book();
        bookToPersist.setISBN(ISBN);
        bookToPersist.setAuthors(volumeInfo.getAuthors().stream().map(a -> {
            final Author author = new Author();
            author.setName(a);
            return author;
        }).collect(Collectors.toSet()));
        bookToPersist.setTitle(volumeInfo.getTitle());
        bookToPersist.setSubtitle(volumeInfo.getSubtitle());
        bookToPersist.setQuantity(quantity);
        bookToPersist.setBorrowed(0);
        return bookRepository.save(bookToPersist);
    }

    @Override
    public Borrowing saveBorrowing(final String ISBN, final BorrowingRequest borrowingRequest) {
        final Optional<Book> book = bookRepository.findByISBNForUpdate(ISBN);
        if (book.isPresent()) {
            final Book bookToUpdate = book.get();

            if ((bookToUpdate.getBorrowed() + 1) > bookToUpdate.getQuantity()){
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            bookToUpdate.setBorrowed(bookToUpdate.getBorrowed() + 1);
            bookToUpdate.setAvailable(bookToUpdate.getQuantity() - bookToUpdate.getBorrowed());
            final Borrowing borrowingToPersist = new Borrowing();
            borrowingToPersist.setBook(bookToUpdate);
            borrowingToPersist.setBorrowerName(borrowingRequest.getBorrowerName());
            borrowingToPersist.setStatus(borrowingRequest.getStatus());
            bookToUpdate.getBorrowings().add(borrowingToPersist);
            return borrowingToPersist;
        };
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Borrowing updateBorrowing(final String ISBN, final Integer borrowingId, final BorrowingRequest borrowingRequest) {
        final Optional<Book> book = bookRepository.findByISBNForUpdate(ISBN);
        final Optional<Borrowing> borrowing;

        if (book.isPresent()) {
            borrowing = book.get().getBorrowings().stream()
                    .filter(borrowingToCheckId -> borrowingToCheckId.getId().equals(borrowingId)).findAny();
        } else{
            LOGGER.error("No book in library found for updateBorrowing: {} {} {}", ISBN, borrowingId, borrowingRequest);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (borrowing.isPresent()) {
            final Borrowing borrowingToUpdate = borrowing.get();
            final Book bookToUpdate = borrowingToUpdate.getBook();
            borrowingToUpdate.setStatus(borrowingRequest.getStatus());
            bookToUpdate.setBorrowed(bookToUpdate.getBorrowed() -1);
            return borrowingToUpdate;
        } else{
            LOGGER.error("No borrowing found for updateBorrowing: {} {} {}", ISBN, borrowingId, borrowingRequest);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
