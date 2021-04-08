The REST service was tested running under IntelliJ as a SpringBoot application.  Examples:
  http://localhost:8080/library/search?s=Potter
  http://localhost:8080/library/author?name=Fleming
  http://localhost:8080/library/check/JKR0001
  http://localhost:8080/library/withdraw/JKR0002
  http://localhost:8080/library/return/JKR0002

Assumptions:
1. All users of the API are already registered and authorised to withdraw books from the library.
2. The API will return lists of books in a search that could get rendered on a Front End.  Book rentals would be based
        on the ids returned from these searches. ie. search for book, then click to rent.
3. The user who withdraws/returns a book would be obtained from the session and recorded along with the book withdrawal details.
4. No book with the same id is loaded twice.  The library might have multiple copies of a book but the ID is unique.
5. The user may rent a book and the book is due back in 1 week.
6. The user does not try to check out or return books that do not exist
7. The current implementation has a collection loaded into memory to hold the catalogue.
     A 'real' version would use a database.

