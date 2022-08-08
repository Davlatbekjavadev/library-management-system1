package uz.pdp.librarymanagementsystem.books;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uz.pdp.librarymanagementsystem.authors.Author;
import uz.pdp.librarymanagementsystem.category.Category;
import uz.pdp.librarymanagementsystem.db.DbConnection;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookDao {



    public static List<Book> getAllBooks(int size, int page) {
        try {
            ArrayList<Book> bookList = new ArrayList<>();

//          1. CONNECTION OCHAMIZ
            Connection connection = DbConnection.getConnection();

//        2. GET PREPARED STATEMENT

            String sql = "select b.id,\n" +
                    "       b.title,\n" +
                    "       b.\"img_url\",\n" +
                    "       json_agg(\n" +
                    "               json_build_object(\n" +
                    "                       'id', a.id,\n" +
                    "                       'fullName', a.full_name)) as authors,\n" +
                    "    json_build_object('id', c.id, 'name', c.name) as category\n" +
                    "--        c.id                                     as categoryId,\n" +
                    "--        c.name                                   as categoryName\n" +
                    "from books b\n" +
                    "         join books_authors ba on b.id = ba.book_id\n" +
                    "         join authors a on a.id = ba.author_id\n" +
                    "         join category c on c.id = b.category_id\n" +
                    "group by b.id, c.id, c.name, b.title\n" +
                    "limit ? offset ? * (? - 1)";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, page);
            preparedStatement.setInt(3, page);


//            3. GET RESULTSET

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long bookId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Array array = resultSet.getArray("authors");
                Object categoryObj = resultSet.getObject("category");
                String imgUrl = resultSet.getString("img_url");
                Type listType = new TypeToken<Set<Author>>() {
                }.getType();
                Set<Author> list = new Gson().fromJson(array.toString(), listType);

                Category category = new Gson().fromJson(categoryObj.toString(), Category.class);


                Book book = Book.builder()
                        .id(bookId)
                        .title(title)
                        .authors(list)
                        .category(category)
                        .imgUrl(imgUrl)
                        .build();

                bookList.add(book);


            }
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean addNewBook(Book book) {

        try {

            Connection connection = DbConnection.getConnection();

            String insertBook = "insert into books (title, description, category_id, \"img_url\") VALUES " +
                    "(?, ?, ?, ?)";
            // TODO: 03/08/22 add isbn, year

            PreparedStatement preparedStatement = connection.prepareStatement(insertBook);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setLong(3, book.getCategoryId());
            preparedStatement.setString(4, book.getImgUrl());


            String insertBooksAuthors = "insert into books_authors VALUES ((select currval('books_id_seq')), ?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertBooksAuthors);

            int executeUpdate1 = preparedStatement.executeUpdate();

            int executeUpdate2 = 0;
            for (Long authorId : book.getAuthorsIds()) {
                preparedStatement2.setLong(1, authorId);
                executeUpdate2 = preparedStatement2.executeUpdate();
            }

            return executeUpdate1 == 1 && executeUpdate2 == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public static Boolean deleteBook(int id) {
        int excute1 = 0;
        int excute2 = 0;
        PreparedStatement ps = null;
        try (Connection connection = DbConnection.getConnection()){

            ps = connection.prepareStatement("delete from books_authors where book_id = ?");
            ps.setInt(1,id);
            excute1 =  ps.executeUpdate();
            ps.close();

            ps = connection.prepareStatement("delete from books where id = ?");
            ps.setInt(1,id);
            excute2=ps.executeUpdate();
            ps.close();
            return excute1 == 1 && excute2 == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Boolean updateBook(Book book) {

        try {

            Connection connection = DbConnection.getConnection();

            String updateBook = "update books set " +
                    "description=?, " +
                    "\"img_url\"=?," +
                    "isbn=?," +
                    "title=?, " +
                    "year=?," +
                    "category_id=? where id= ?";
            // TODO: 03/08/22 add isbn, year

            PreparedStatement preparedStatement = connection.prepareStatement(updateBook);

            preparedStatement.setString(1, book.getDescription());
            preparedStatement.setString(2, book.getImgUrl());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setInt(5, book.getYear());
            preparedStatement.setLong(6, book.getCategoryId());
            preparedStatement.setLong(7, book.getId());

            int executeUpdate = preparedStatement.executeUpdate();


            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from books_authors where book_id = ?");
            preparedStatement1.setLong(1,book.getId());
            int executeUpdate1 = preparedStatement1.executeUpdate();


            String insertBooksAuthors = "insert into books_authors VALUES (?, ?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertBooksAuthors);


            int executeUpdate2 = 0;
            for (Long authorId : book.getAuthorsIds()) {
                preparedStatement2.setLong(1, book.getId());
                preparedStatement2.setLong(2, authorId);
                executeUpdate2 = preparedStatement2.executeUpdate();
            }

            return executeUpdate == 1 && executeUpdate2 == 1 && executeUpdate1==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public static Book getBookInfo(int id) {
        try {
            Connection connection = DbConnection.getConnection();


            String sql = "select b.id,\n" +
                    "       b.title,\n" +
                    "       b.\"img_url\",\n" +
                    "       b.year,\n" +
                    "       b.description,\n" +
                    "       b.isbn,\n" +
                    "       json_agg(\n" +
                    "               json_build_object(\n" +
                    "                       'id', a.id,\n" +
                    "                       'fullName', a.full_name)) as authors,\n" +
                    "    json_build_object('id', c.id, 'name', c.name) as category\n" +
                    "from books b" +
                    "         join books_authors ba on b.id = ba.book_id\n" +
                    "         join authors a on a.id = ba.author_id\n" +
                    "         join category c on c.id = b.category_id where b.id = ?" +
                    "group by b.id, c.id, c.name, b.title, b.\"img_url\", b.year, b.description, b.isbn\n";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);



//            3. GET RESULTSET

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long bookId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Array array = resultSet.getArray("authors");
                Object categoryObj = resultSet.getObject("category");
                String imgUrl = resultSet.getString("img_url");//imgUrl
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("year");
                String description = resultSet.getString("description");

                Type listType = new TypeToken<Set<Author>>() {
                }.getType();
                Set<Author> list = new Gson().fromJson(array.toString(), listType);

                Category category = new Gson().fromJson(categoryObj.toString(), Category.class);


                Book book = Book.builder()
                        .id(bookId)
                        .title(title)
                        .authors(list)
                        .category(category)
                        .imgUrl(imgUrl)
                        .isbn(isbn)
                        .year(year)
                        .description(description)
                        .build();

                return book;
            }
        } catch (SQLException e) {throw new RuntimeException(e);
        }
        return null;
    }


}
