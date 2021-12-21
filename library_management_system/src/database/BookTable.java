package database;

import shop.Book;
import shop.Author;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookTable extends PostgresSQLJDBC{

    @Override
    public int generateNextIdAvailable(){
        int id;
        int maxId=0;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT book_id FROM BOOK;" );
            while ( rs.next() ) {
                id = rs.getInt("book_id");
                if(id>maxId)
                    maxId=id;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return maxId+1;
    }

    public boolean searchBook(String title){
        int id=-1;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT book_id FROM BOOK WHERE title = '" + title + "';" );
            while ( rs.next() ) {
                id = rs.getInt("book_id");
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        if(id==-1)
            return false;
        return true;
    }

    @Override
    public void insertIntoTable(Object obj){
        try {
            if(!searchBook(((Book)obj).getTitle())){
                PublisherTable publisher=new PublisherTable();
                int id=generateNextIdAvailable();
                int publisherId= publisher.getPublisherId(((Book)obj).getPublisher());
                if(publisherId==-1){
                    publisherId=publisher.generateNextIdAvailable();
                    publisher.insertIntoTable(((Book)obj).getPublisher());
                }

                LanguageTable language=new LanguageTable();
                int languageId= language.getLanguageId(((Book)obj).getLanguage());
                if(languageId==-1){
                    languageId=language.generateNextIdAvailable();
                    language.insertIntoTable(((Book)obj).getLanguage());
                }
                Statement stmt = getConnection().createStatement();
                String sql = "INSERT INTO BOOK (book_id, title, pages, available_quantity, price, publisher_id, language_id, publication_year)  "
                        + "VALUES (" + id + ",'" +  ((Book)obj).getTitle() + "'," + ((Book) obj).getNumberOfPages() + "," + ((Book)obj).getAvailableQuantity() + "," + ((Book)obj).getPrice() + "," + publisherId + "," + languageId + "," + ((Book)obj).getPublicationYear() + ");";
                stmt.executeUpdate(sql);

                stmt.close();

                ArrayList<Author> authors=((Book)obj).getAuthors();

                System.out.println("Records created successfully");
            }else{
                System.out.println("Records already exist");
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public void selectFromTable(){
        try {
            System.out.println("---BOOK TABLE---");
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM BOOK;" );
            while ( rs.next() ) {
                int id = rs.getInt("book_id");
                String  title = rs.getString("title");
                int nrOfPages  = rs.getInt("pages");
                int availableQuantity  = rs.getInt("available_quantity");
                float price = rs.getFloat("price");
                int publisherId  = rs.getInt("publisher_id");
                int languageId  = rs.getInt("language_id");
                int publicationYear  = rs.getInt("publication_year");
                System.out.println( "ID = " + id );
                System.out.println( "TITLE = " + title );
                System.out.println( "NUMBER OF PAGES = " + nrOfPages );
                System.out.println( "AVAILABLE QUANTITY = " + availableQuantity );
                System.out.println( "PRICE = " + price );
                System.out.println( "PUBLISHER ID = " + publisherId );
                System.out.println( "LANGUAGE ID = " + languageId );
                System.out.println( "PUBLICATION YEAR = " + publicationYear );

                System.out.println();
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    @Override
    public void updateTable(){

    }

    @Override
    public void deleteFromTable(){

    }
}
