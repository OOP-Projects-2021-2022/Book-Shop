package database;

import java.sql.ResultSet;
import java.sql.Statement;

public class PublisherTable extends PostgresSQLJDBC{

    @Override
    public int generateNextIdAvailable(){
        int id;
        int maxId=0;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT publisher_id FROM PUBLISHER;" );
            while ( rs.next() ) {
                id = rs.getInt("publisher_id");
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

    public boolean searchPublisher(String publisherName){
        int id=-1;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT publisher_id FROM PUBLISHER WHERE name = '" + publisherName + "';" );
            while ( rs.next() ) {
                id = rs.getInt("publisher_id");
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

    public int getPublisherId(String publisherName){
        int id=-1;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT publisher_id FROM PUBLISHER WHERE name = '" + publisherName + "';" );
            while ( rs.next() ) {
                id = rs.getInt("publisher_id");
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return id;
    }

    @Override
    public void insertIntoTable(Object publisherName){
        try {
            if(!searchPublisher((String)publisherName)){
                int id=generateNextIdAvailable();
                Statement stmt = getConnection().createStatement();
                String sql = "INSERT INTO PUBLISHER (publisher_id, name)  "
                        + "VALUES (" + id + ",'" +  publisherName + "');";
                stmt.executeUpdate(sql);

                stmt.close();

                System.out.println("Records created successfully");
            }
            else{
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
            System.out.println("---PUBLISHER TABLE---");
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM PUBLISHER;" );
            while ( rs.next() ) {
                int id = rs.getInt("publisher_id");
                String  publisherName = rs.getString("name");
                System.out.println( "ID = " + id );
                System.out.println( "PUBLISHER NAME = " + publisherName );

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
