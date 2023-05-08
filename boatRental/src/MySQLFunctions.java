import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySQLFunctions {
    private String databaseURL = "jdbc:mysql://localhost:3306/BoatRental";
    private String user = "root";
    private String pass = "Preet123";
    private Connection conn = null;
    private static PreparedStatement preparedStatement = null;

    public void login(String username, String password){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //STEP 2: Register JDBC driver (automatically done since JDBC 4.0)
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(databaseURL, user, pass);

            //STEP 4: Execute a query
            String statement = "select * from user where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            rs = preparedStatement.executeQuery();

            //STEP 5: Process the results
            while(rs.next()){
                System.out.println("Username: "+rs.getString("username")+", First name: "+rs.getString("firstname") + ", Last name: "+rs.getString("lastname"));
                System.out.println("Welcome! Login Successful");
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    //Return type when account already exists?
    public void createAccount(String fn, String ln, String dl, String username, String password, boolean admin){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into user (firstName, lastName, driversLicense, username, password, admin) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,fn);
            preparedStatement.setString(2, ln);
            preparedStatement.setString(3, dl);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setBoolean(6, admin);


            int result = preparedStatement.executeUpdate();

            //STEP 5: Process the results
            if(result != 0)
                System.out.println("Account Created!");
            else
                System.out.println("Account already exists");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void returnBoat(String username, int boatID){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rental set boatReturned = 1 where username = ? and boatID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2, boatID);
            int result = preparedStatement.executeUpdate();

            //STEP 5: Process the results
            if (result != 0){
                System.out.println("Boat returned!" );
            }
            else {
                System.out.println("Boat already returned.");
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void deleteBoat(int boatID){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "delete from boat where boatID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,boatID);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Boat deleted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void addBoat(String boatType, String make, String model){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into boat (boatType, make, model) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,boatType);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, model);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Boat inserted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void deleteAccount(String username){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "delete from user where username = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1,username);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("User deleted!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void updatePrice(String boatType, int price){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rate set price = ? where boatType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1,price);
            preparedStatement.setString(2, boatType);

            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Price updated!" );


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void getRate(String boatType){
        Statement stmt = null;
        ResultSet rs = null;
        double boatPrice = 0;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "select price from rate where boatType = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, boatType);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                boatPrice = rs.getDouble("price");
            }

            //STEP 5: Process the results
            System.out.println("Price of the boat: " + boatPrice);


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void rentBoat(String username, int boatID, int days){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into rental (userName, boatID, returnDate) values (?, ?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, boatID);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);
            java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(3, returnDate);

            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Boat rented!");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void getMostExpensiveBoats(){
        Statement stmt = null;
        ResultSet rs = null;
        int storeId;
        int maxPrice;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);
            // Execute a query
            String statement = "select store.storeID, MAX(price) \n"
                    + "from store join boat inner join rate on boat.boatType = rate.boatType \n"
                    + "where boat.boatID in\n"
                    + "		(select boatID \n"
                    + "		from store as s\n"
                    + "where boatID in (select s.boatID where s.storeID = store.storeID)) group by store.storeID;\n"
                    + "";
            preparedStatement = conn.prepareStatement(statement);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                storeId = rs.getInt("storeID");
                maxPrice = rs.getInt("MAX(price)");
                System.out.println("Max priced boat is " + maxPrice + " at storeID " + storeId);
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    // Where and when to return a boat
    public void getReturnInfo(int boatID) {
        Statement stmt = null;
        ResultSet rs = null;
        String storeID = "";
        Calendar cal = Calendar.getInstance();
        java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());

        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "Select storeID, returnDate from rental, store where rental.boatID = store.boatID and rental.boatID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setInt(1, boatID);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                storeID = rs.getString("storeID");
                returnDate = rs.getDate("returnDate");
                System.out.println("Boat " + boatID + " should be returned to store " + storeID +
                        " by " + returnDate.toString());
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void getBoatsRentedAtStore(String storeId){
        Statement stmt = null;
        ResultSet rs = null;
        String boatType;
        int count;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "SELECT boatType, count(*) as count\n"
                    + "  	FROM boat AS c, rental AS r, store AS s\n"
                    + "  	WHERE c.boatID = r.boatID and s.boatID =  c.boatID and s.storeID = ? \n"
                    + "and r.boatReturned = false\n"
                    + " 	GROUP BY c.boatType\n"
                    + "HAVING count(*) > 0;\n"
                    + "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeId);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                boatType = rs.getString("boatType");
                count = rs.getInt("count");
                System.out.println("Boat Type: " + boatType + " and count is: " + count);
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void changeReturnDate(String username, int boatID, int days) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "update rental set returnDate = ? where username = ? and boatID = ?";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(2, username);
            preparedStatement.setInt(3, boatID);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);
            java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
            System.out.println(returnDate);
            preparedStatement.setDate(1, returnDate);

            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Boat return date is updated!");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void getUserDataAsCol(String username){
        Statement stmt = null;
        ResultSet rs = null;
        List<String> userDetails = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "select username as UserDataInCol \n"
                    + "from user\n"
                    + "where username = ?\n"
                    + "union\n"
                    + "select driversLicense \n"
                    + "from user\n"
                    + "where username = ?\n"
                    + "union\n"
                    + "select firstName\n"
                    + "from user\n"
                    + "where username = ?\n"
                    + "union\n"
                    + "select lastName\n"
                    + "from user\n"
                    + "where username = ?;\n"
                    + "";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, username);

            rs = preparedStatement.executeQuery();
            while (rs.next()){
                userDetails.add(rs.getString("UserDataInCol"));
            }

            System.out.println("Username: " + userDetails.get(0));
            System.out.println("Driver's License: " + userDetails.get(1));
            System.out.println("First Name: " + userDetails.get(2));
            System.out.println("Last Name: " + userDetails.get(3));


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    //Get ID and type of all boats together with username of users who have rented them at
    // a certain store. The boats that are not rented out will have null
    public void getBoat(String storeID) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);
            System.out.print("Boats at store " + storeID + ": ");
            String statement = "SELECT c.boatID, c.boatType, r.username " +
                    "FROM boat AS c " +
                    "LEFT JOIN rental AS r " +
                    "ON c.boatID = r.boatID and boatReturned = false " +
                    "INNER JOIN " +
                    "store as s " +
                    "ON c.boatID = s.boatID " +
                    "Where s.storeID = ? " +
                    "Order by c.boatID";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeID);

            rs = preparedStatement.executeQuery("SELECT c.boatID, c.boatType, r.username "+
                    "FROM boat AS c " +
                    "LEFT JOIN rental AS r " +
                    "ON c.boatID = r.boatID and boatReturned = false " +
                    "INNER JOIN " +
                    "store as s " +
                    "ON c.boatID = s.boatID " +
                    "Where s.storeID =  " + storeID + " " +
                    "Order by c.boatID");

            // preparedStatement.execute();

            while (rs.next()) {
                System.out.println("\nBoat ID = " + rs.getString("boatID") + " Boat Type = " + rs.getString("boatType")
                        + " Username = " + rs.getString("username"));
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void insertBoat(String boatType, int price){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into rate (boatType, price) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, boatType);
            preparedStatement.setInt(2, price);
            preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Boat inserted!");


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void addStore(String storeID, int boatID) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "insert into store (storeID, boatID) values (?, ?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, storeID);
            preparedStatement.setInt(2, boatID);
            boolean result = preparedStatement.execute();

            //STEP 5: Process the results
            System.out.println("Store added");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void archive(String date) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(databaseURL, user, pass);

            // Execute a query
            String statement = "CALL archive(?)";
            preparedStatement = conn.prepareStatement(statement);
            preparedStatement.setString(1, date);
            boolean result = preparedStatement.execute();

            if(!result)
                System.out.println("Archive Successful!");


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}