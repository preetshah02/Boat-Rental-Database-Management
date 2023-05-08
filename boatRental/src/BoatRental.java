import java.util.Arrays;
import java.util.Scanner;

public class BoatRental {
    public static void main(String[] args){
        MySQLFunctions mysql = new MySQLFunctions();
        Scanner in = new Scanner(System.in);
        User user = null;
        String response = "";
        while (!response.equals("quit")){
            if (user != null){

            } else {
                System.out.print("Command: ");
                response = in.nextLine();
                /* String boatType, String make, String model */
                String[] parseResponse = response.split(" ");
                String command = parseResponse[0];

                if (command.equals("login")){
                    if(parseResponse.length != 3){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    String password = parseResponse[2];
                    mysql.login(username, password);
                }

                if (command.equals("addBoat")){
                    if (parseResponse.length != 4) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatType = parseResponse[1];
                    String make = parseResponse[2];
                    String model = parseResponse[3];
                    mysql.addBoat(boatType,make, model);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("deleteBoat")){
                    if(parseResponse.length != 2){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatIDString = parseResponse[1];
                    int boatID = Integer.parseInt(boatIDString);
                    mysql.deleteBoat(boatID);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("updatePrice")){
                    if(parseResponse.length != 3){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatType = parseResponse[1];
                    String priceString = parseResponse[2];
                    int price = Integer.parseInt(priceString);
                    mysql.updatePrice(boatType, price);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("deleteAccount")){
                    if(parseResponse.length != 2){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    mysql.deleteAccount(username);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("returnBoat")){
                    if(parseResponse.length != 3){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    String boatID = parseResponse[2];
                    int id = Integer.parseInt(boatID);
                    mysql.returnBoat(username, id);
                    System.out.println("Return Date: " + Arrays.toString(parseResponse));
                }

                if (command.equals("getRate")){
                    if(parseResponse.length != 2){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatType = parseResponse[1];
                    mysql.getRate(boatType);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("rentBoat")){
                    if(parseResponse.length != 3){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    String boatId = parseResponse[2];
                    int id = Integer.parseInt(boatId);
                    System.out.print("Enter number of days needed to rent the boat: ");
                    int day = in.nextInt();
                    in.nextLine();
                    mysql.rentBoat(username, id, day);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("createAccount")) {
                    if (parseResponse.length != 7) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String fn = parseResponse[1];
                    String ln = parseResponse[2];
                    String dl = parseResponse[3];
                    String username = parseResponse[4];
                    String password = parseResponse[5];
                    String admin = parseResponse[6];
                    mysql.createAccount(fn, ln, dl, username, password, Boolean.valueOf(admin));
                }
                if (command.equals("getMostExpensiveBoats")) {
                    if (parseResponse.length != 1) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    mysql.getMostExpensiveBoats();
                }
                if (command.equals("getBoatsRentedAtStore")) {
                    if (parseResponse.length != 2) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String storeId = parseResponse[1];
                    mysql.getBoatsRentedAtStore(storeId);
                }
                if (command.equals("getUserDataAsCol")) {
                    if (parseResponse.length != 2) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    mysql.getUserDataAsCol(username);
                }
                if (command.equals("insertBoat")) {
                    if (parseResponse.length != 3) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatType = parseResponse[1];
                    String price = parseResponse[2];
                    mysql.insertBoat(boatType, Integer.valueOf(price));
                }

                if (command.equals("getReturnInfo")){
                    if(parseResponse.length != 2){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String boatID = parseResponse[1];
                    int vID = Integer.parseInt(boatID);
                    mysql.getReturnInfo(vID);
                    System.out.println(Arrays.toString(parseResponse));
                }

                if (command.equals("changeReturnDate")){
                    if(parseResponse.length != 3){
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String username = parseResponse[1];
                    String boatId = parseResponse[2];
                    int id = Integer.parseInt(boatId);
                    System.out.print("How many days from today would you like to extend your rent? ");
                    int day = in.nextInt();
                    in.nextLine();
                    mysql.changeReturnDate(username, id, day);
                    System.out.println(Arrays.toString(parseResponse));
                }

                //Get ID and type of all boats together with username of users who have rented them at
                // a certain store. The boats that are not rented out will have null
                if (command.equals("getBoats")){
                    if (parseResponse.length != 2) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String storeID = parseResponse[1];
                    mysql.getBoat(storeID);
                    System.out.println(Arrays.toString(parseResponse));
                }

                // Add store
                if (command.equals("addStore")){
                    if (parseResponse.length != 3) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    String storeID = parseResponse[1];
                    String boatID = parseResponse[2];
                    int id = Integer.parseInt(boatID);
                    mysql.addStore(storeID, id);
                    System.out.println(Arrays.toString(parseResponse));
                }
                if (command.equals("archive")){
                    if (parseResponse.length != 1) {
                        System.out.println("Information not correct.");
                        continue;
                    }
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter a date in format (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    mysql.archive(date);
                }
            }
        }
    }
}
