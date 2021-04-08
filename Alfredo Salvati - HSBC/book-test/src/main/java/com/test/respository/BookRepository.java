package com.test.respository;

import com.test.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

  BookEntity findBookEntityByUuidAndAvailableTrue(UUID id);
  BookEntity findBookEntityByUuidAndAvailableFalse(UUID id);

  BookEntity findByTitle(String title);
}
