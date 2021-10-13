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
        Book mumu = new Book();
        mumu.setName("Муму");
        mumu.setNumberOfPages(120);
        Book bear = new Book();
        bear.setName("Медведь");
        bear.setNumberOfPages(100);
        Book cleanCode = new Book();
        cleanCode.setName("Clean code");
        cleanCode.setNumberOfPages(480);
        Book cookbook = new Book();
        cookbook.setName("Кулинарная книга");
        cookbook.setNumberOfPages(800);
        Book[] books = new Book[4];
        books[0] = mumu;
        books[1] = bear;
        books[2] = cleanCode;
        books[3] = cookbook;
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
