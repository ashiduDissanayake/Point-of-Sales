import java.util.ArrayList;
import java.io.*;



class ItemCodeNotFound extends Exception {
    public ItemCodeNotFound(String message) {
        super(message);
    }
}

class PendException extends Exception{
    public PendException(String messege){
        super(messege);
    }
}

class GloceryItem implements Serializable{
    private String item_code;
    private String item_name;
    private double price;
    private double weight;
    private String date_of_manufacturing;
    private String date_of_expiry;
    private String manufacturer_name;
    private double discount;

    
    public GloceryItem(String item_code, String item_name, double price, double weight, String date_of_manufacturing, String date_of_expiry, String manufacturer_name, double discount){
        this.item_code = item_code;
        this.item_name = item_name;
        this.price = price;
        this.weight = weight;
        this.date_of_manufacturing = date_of_manufacturing;
        this.date_of_expiry = date_of_expiry;
        this.manufacturer_name = manufacturer_name;
        this.discount = discount;
    }
    public String getItemCode(){
        return item_code;
    }
    public String getItemName(){
        return item_name;
    }
    public double getPrice(){
        return price;
    }
    public double getWeight(){
        return weight;
    }
    public String getDateOfManufacturing(){
        return date_of_manufacturing;
    }
    public String getDateOfExpiry(){
        return date_of_expiry;
    }
    public String getManufacturerName(){
        return manufacturer_name;
    }
    public double getDiscount(){
        return discount;
    }
}

class PendingBill implements Serializable {
    private String cashier_name;
    private String branch;
    private String customer_name;
    private ArrayList<GloceryItem> item_list;
    private double total_discount;
    private double total_price;
    private String date;
    private String time;

    public PendingBill(String cashier_name, String branch, String customer_name, ArrayList<GloceryItem> item_list, double total_discount, double total_price, String date, String time){
        this.cashier_name = cashier_name;
        this.branch = branch;
        this.customer_name = customer_name;
        this.item_list = item_list;
        this.total_discount = total_discount;
        this.total_price = total_price;
        this.date = date;
        this.time = time;
    }
    
    public String getCashierName(){
        return cashier_name;
    }
    public String getBranch(){
        return branch;
    }
    public String getCustomerName(){
        return customer_name;
    }
    public ArrayList<GloceryItem> getItemList(){
        return item_list;
    }
    public double getTotalDiscount(){
        return total_discount;
    }
    public double getTotalPrice(){
        return total_price;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public void setCashierName(String cashier_name){
        this.cashier_name = cashier_name;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }
    public void setCustomerName(String customer_name){
        this.customer_name = customer_name;
    }
    public void setItemList(ArrayList<GloceryItem> item_list){
        this.item_list = item_list;
    }
    public void setTotalDiscount(double total_discount){
        this.total_discount = total_discount;
    }
    public void setTotalPrice(double total_price){
        this.total_price = total_price;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void printPendingBill(){
        System.out.println("Cashier Name: " + cashier_name);
        System.out.println("Branch: " + branch);
        System.out.println("Customer Name: " + customer_name);
        System.out.println("Item List: ");
        for(GloceryItem item : item_list){
            System.out.println("Item Code: " + item.getItemCode());
            System.out.println("Item Name:" + item.getItemName());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Weight: " + item.getWeight());
            System.out.println("Date of Manufacturing: " + item.getDateOfManufacturing());
            System.out.println("Date of Expiry: " + item.getDateOfExpiry());
            System.out.println("Manufacturer Name: " + item.getManufacturerName());
            System.out.println("Discount: " + item.getDiscount());
        }
        System.out.println("Total Discount: " + total_discount);
        System.out.println("Total Price: " + total_price);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
    }
}


class POS implements Serializable{
    private String cashier_name;
    private String branch;
    private String customer_name;
    private ArrayList<GloceryItem> item_list;
    private double total_discount;
    private double total_price;
    private String date;
    private String time;
    private String item_code;
    public static int pending = 0;
    private ArrayList<PendingBill> pending_bills;
    public POS(String cashier_name, String branch, String customer_name, ArrayList<GloceryItem> item_list, double total_discount, double total_price, String date, String time, ArrayList<PendingBill> pending_bills){
        this.cashier_name = cashier_name;
        this.branch = branch;
        this.customer_name = customer_name;
        this.item_list = item_list;
        this.total_discount = total_discount;
        this.total_price = total_price;
        this.date = date;
        this.time = time;
        this.pending_bills = pending_bills;
    }
    public POS(String cashier_name, String branch, String customer_name, ArrayList<GloceryItem> item_list, String date, String time){
        this.cashier_name = cashier_name;
        this.branch = branch;
        this.customer_name = customer_name;
        this.item_list = item_list;
        this.total_discount = 0.0;
        this.total_price = 0.0; 
        this.date = date;
        this.time = time;
    }
    public String getCashierName(){
        return cashier_name;
    }
    public String getBranch(){
        return branch;
    }
    public String getCustomerName(){
        return customer_name;
    }
    public ArrayList<GloceryItem> getItemList(){
        return item_list;
    }
    public double getTotalDiscount(){
        return total_discount;
    }
    public double getTotalPrice(){
        return total_price;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    public ArrayList<PendingBill> getPendingBills(){
        return pending_bills;
    }
    public void setCashierName(String cashier_name){
        this.cashier_name = cashier_name;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }
    public void setCustomerName(String customer_name){
        this.customer_name = customer_name;
    }
    public void setItemList(ArrayList<GloceryItem> item_list){
        this.item_list = item_list;
    }
    public void setTotalDiscount(double total_discount){
        this.total_discount = total_discount;
    }
    public void setTotalPrice(double total_price){
        this.total_price = total_price;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setPendingBills(ArrayList<PendingBill> pending_bills){
        this.pending_bills = pending_bills;
    }
    public void addItem(GloceryItem item){
        item_list.add(item);
    }
    public void removeItem(GloceryItem item){
        item_list.remove(item);
    }
    public void addPendingBill(PendingBill pending_bill){
        pending_bills.add(pending_bill);
    }
    public void removePendingBill(PendingBill pending_bill){
        pending_bills.remove(pending_bill);
    }
    public void printBill(){
        System.out.println("-----------------------------------------------");
        System.out.println("Cashier Name: " + cashier_name+"              "+"Customer Name: " + customer_name);
        System.out.println("Branch: " + branch);
        System.out.println("Date: " + date+"              "+"Time: " + time);
        System.out.println("-----------------------------------------------");
        System.out.println();

        System.out.println("Code | Item Name | Weight | Manufacture Date| Expiary Date   | Manufactue Name | Discount | Price |");
        System.out.println("-----------------------------------------------");
        for(GloceryItem item : item_list){
            double discount = item.getPrice()*item.getDiscount()/100;
            System.out.println(item.getItemCode()+"     "+ item.getItemName()+"     "+ item.getWeight()+"     " + item.getDateOfManufacturing() +"     "+ item.getDateOfExpiry()+"     " + item.getManufacturerName() +"     "+ item.getDiscount()+"     " + item.getPrice());
            total_price += item.getPrice();
            total_discount += discount;
        }
        System.out.println("Total Discount: " + total_discount);
        double total_payement = total_price - total_discount;
        System.out.println("Total Price: " + total_payement);
        
    }
    public void printPendingBills(){
        for(PendingBill pending_bill : pending_bills){
            pending_bill.printPendingBill();
        }
    }
    
    public GloceryItem getItemDetails() throws PendException {
        GloceryItem item = null;
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter item code:");
            String itemCode = br.readLine();
            
            // Fetch item details from the database
            // If item code is not found, throw ItemCodeNotFound exception
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("item.ser"));
            GloceryItem[] items = new GloceryItem[10]; // Assuming there are 10 items in the file
            for (int i = 0; i < 10; i++) {
                items[i] = (GloceryItem) in.readObject();
            }
            in.close();
            
            if (itemCode.equals("0")) {
                return null; // Return null if the user wants to exit
            }else if (itemCode.equals("w")){
                throw new PendException("Next Customer!");
            }
                        
                        // Parse the item code to an integer and subtract 1 to get the index (assuming item codes start from 1)
                        int index = Integer.parseInt(itemCode) - 1; 
                        // Check if the index is within the bounds of the items array
                        if (index >= 0 && index < items.length) {
                            // If it is, assign the item at that index to the item variable
                            item = items[index];
                        } else {
                            // If it's not, throw an exception
                            throw new ItemCodeNotFound("Item code not found. Please re-enter.");
                        }
                    } catch (IOException e) {
                        // Handle any IOExceptions that might occur
                        System.out.println("Error reading input. Please try again.");
                    } catch (ItemCodeNotFound e) {
                        // Handle the custom ItemCodeNotFound exception
                        System.out.println(e.getMessage()); // Print the exception message
                    } catch (ClassNotFoundException e) {
                        // Handle any ClassNotFoundExceptions that might occur
                        e.printStackTrace();
                    }
                    
                    // Return the item
                    return item;
                }
                
            }


            public class Main {
                public static void main(String[] args) {
                    // Start an infinite loop
                    while(true){
                        // Initialize lists for grocery items and pending bills
                        ArrayList<GloceryItem> item_list = new ArrayList<GloceryItem>();
                        ArrayList<PendingBill> pending_bills = new ArrayList<PendingBill>();
                        // Set up a BufferedReader to read the customer's name from the console
                        InputStreamReader cusNameR = new InputStreamReader(System.in);
                        BufferedReader cusNameBR = new BufferedReader(cusNameR);
                        System.out.println("Enter the name of the customer:");
                        String customer_name = "";
                        try {
                            // Read the customer's name
                            customer_name = cusNameBR.readLine();
                        } catch (IOException e) {
                            // Handle any IOExceptions that might occur
                            System.out.println("Error reading customer name. Please try again.");
                        }
                        // Get the current date and time
                        java.util.Date date = new java.util.Date();
                        String fullDate = date.toString();
                        String Date = fullDate.substring(8, 10) + "-" + fullDate.substring(4, 7) + "-" + fullDate.substring(24, 28);
                        String Time = fullDate.substring(11, 19);

                        // Create a new POS object with the cashier's name, branch, customer's name, item list, date, and time
                        POS pos = new POS("Pramod Bandara", "Moratuwa", customer_name, item_list,Date, Time);
                        
                        
                        // Serialize the above objects inside a file to take back in getItemDetails method
                        try {
                            // Create some grocery items
                            GloceryItem item1 = new GloceryItem("1", "Flour", 300, 1, "01-05-2024", "01-01-2026", "Rayin", 0);
                            // ... more items ...

                            // Write the items to a file
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("item.ser"));
                            out.writeObject(item1);
                            // ... write more items ...
                            out.close();
                        } catch (IOException e) {
                            // Handle any IOExceptions that might occur
                            e.printStackTrace();
                        }


                        // Ask the cashier to enter the customer bought items one by one and then if it is over said enter 0
                        while (true) {
                            try {
                                // Get the details of an item
                                GloceryItem item = pos.getItemDetails();
                                if (item == null) {
                                    // If there are no more items, print the bill and break the loop
                                    pos.printBill();
                                    break;
                                }
                                // Add the item to the POS
                                pos.addItem(item);
                            }
                            catch (PendException e){
                                // Handle the custom PendException
                                System.out.println("Next Customer Please!!!");
                                try {
                                    // Write the pending bills to a file
                                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PendingBills.ser"));
                                    out.writeObject(pos);
                                    out.close();
                                    // Increment the number of pending bills
                                    pos.pending += 1;
                                } catch (IOException ex) {
                                    // Handle any IOExceptions that might occur
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            
                        }
                        
                        try{
                            // Ask the cashier if there are any more customers or any pending bills
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("1)Are there any customers:");
                            System.out.println("2)Are there any pended Billed customers:");
                            System.out.println("Enter the number for your preference? ");
                            String prefer = br.readLine();
                            int preferNum = Integer.parseInt(prefer);
                            if(preferNum == 2){
                                // If there are pending bills, read them from the file
                                try {
                                ObjectInputStream in = new ObjectInputStream(new FileInputStream("PendingBills.ser"));
                                POS pendPos  = (POS) in.readObject();
                                in.close();
                                // Decrement the number of pending bills
                                POS.pending -= 1;
                                GloceryItem item = null;
                                while (true) {
                                    try {
                                        // Get the details of an item
                                        item = pendPos.getItemDetails();
                                        if (item == null) {
                                            // If there are no more items, print the bill and break the loop
                                            pendPos.printBill();
                                            break;
                                        }
                                        // Add the item to the POS
                                        pendPos.addItem(item);
                                    }
                                    
                                    catch (PendException e){
                                        // Handle the custom PendException
                                        System.out.println("Next Customer Please!!!");
                                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PendingBills.ser"));
                                        out.writeObject(pendPos);
                                        pendPos.pending += 1;
                                        break;
                                    }
                                    
                                }
                                } catch (ClassNotFoundException e) {
                                    // Handle any ClassNotFoundExceptions that might occur
                                    e.printStackTrace();
                                }
                            }else if (preferNum == 1){

                            }
                            else{
                                // If there are no more customers or pending bills, break the loop
                                break;
                            }
                        
                    } catch (IOException e) {
                        // Handle any IOExceptions that might occur
                        System.out.println(e);
                    } catch (NumberFormatException e) {
                        // Handle any NumberFormatExceptions that might occur
                        System.out.println("Invalid input. Please enter a valid integer value.");
                    }
                    try{
                        // Ask the cashier if there are any more customers
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("1)Are there any customers:");
                        System.out.println("2)No");
                        System.out.println("Enter the number for your preference? ");
                        String prefer = br.readLine();
                        int preferNum = Integer.parseInt(prefer);
                        if(preferNum == 1){
                            
                        }else{
                            // If there are no more customers, break the loop
                            break;
                        }
                            
                    
                    
                    }catch(IOException e){
                        // Handle any IOExceptions that might occur
                        System.out.println(e.getMessage());

                    }
                }
                    
                }
            }
