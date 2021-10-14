package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Library library = new Library();
        Book[] books = library.fillLibrary();
        System.out.println("--- Все книги библиотеки:");
        library.printBooks(books);
        library.swap(books, 0, 3);
        System.out.println("--- Первую и последнюю книги поменяли местами:");
        library.printBooks(books);
        System.out.println("--- Книги с названием 'Clean code':");
        library.printBooks(books, "Clean code");
    }

    private Book[] fillLibrary() {
        Book[] books = new Book[4];
        books[0] = new Book("Муму", 120);
        books[1] = new Book("Медведь", 100);
        books[2] = new Book("Clean code", 480);
        books[3] = new Book("Кулинарная книга", 800);
        return books;
    }

    private void printBooks(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            printBookInfo(books[i]);
        }
    }

    private void printBooks(Book[] books, String name) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(name)) {
                printBookInfo(books[i]);
            }
        }
    }

    private void printBookInfo(Book book) {
        String bookInfo = "\"" + book.getName() + "\", кол-во страниц: "
                + book.getNumberOfPages();
        System.out.println(bookInfo);
    }

    private Book[] swap(Book[] books, int src, int dest) {
        Book temp = books[dest];
        books[dest] = books[src];
        books[src] = temp;
        return books;
    }
}
