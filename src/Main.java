import java.util.ArrayList;
import java.io.*;

//When inserting items you should follow
// item_code item_qty

//if the bill is over ---> insert 0

// if you want to pause the bill ----> insert w


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


class POS implements Serializable{
    private String cashier_name;
    private String branch;
    private String customer_name;
    private ArrayList<GloceryItem> item_list;
    private ArrayList<Integer> item_qty = new ArrayList<>();
    private double total_discount;
    private double total_price;
    private String date;
    private String time;
    private String item_code;
    public static int pending = 0;


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
    public void addItem(GloceryItem item){
        item_list.add(item);
    }
    public void addQty(Integer qty){
        item_qty.add(qty);
    }
    public void removeItem(GloceryItem item){
        item_list.remove(item);
    }
    public void printBill(){
        System.out.println("-----------------------------------------------");
        System.out.println("Cashier Name: " + cashier_name+"              "+"Customer Name: " + customer_name);
        System.out.println("Branch: " + branch);
        System.out.println("Date: " + date+"              "+"Time: " + time);
        System.out.println("-----------------------------------------------");
        System.out.println();

        System.out.println("Code | Item Name | Weight | Qunatity | Manufacture Date| Expiary Date   | Manufactue Name | Discount | Price |");
        System.out.println("-----------------------------------------------");
        int i = 0;
        for(GloceryItem item : item_list){
            double discount = item_qty.get(i)*item.getPrice()*item.getDiscount()/100;
            System.out.println(item.getItemCode()+"     "+ item.getItemName()+"     "+ item.getWeight()+"     " + item_qty.get(i)+"     "+  item.getDateOfManufacturing() +"     "+ item.getDateOfExpiry()+"     " + item.getManufacturerName() +"     "+ item.getDiscount()+"     " + item_qty.get(i)*item.getPrice());
            total_price += item_qty.get(i)*item.getPrice();
            total_discount += discount;
            i += 1;
        }
        System.out.println("Total Discount: " + total_discount);
        double total_payement = total_price - total_discount;
        System.out.println("Total Price: " + total_payement);
        
    }
    
    public GloceryItem getItemDetails() throws PendException {
        GloceryItem item = null;
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter item code and qunatity:");
            String itemCode = br.readLine();
            if (itemCode.equals("0")) {
                return null; // Return null if the user wants to exit
            }else if (itemCode.equals("w")){
                throw new PendException("Next Customer!");
            }
            try{
                int qty = Integer.parseInt(itemCode.substring(2, 3));
                itemCode = itemCode.substring(0, 1);
                item_qty.add(qty);
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.println("Follow the format");
            }
            // Fetch item details from the database
            // If item code is not found, throw ItemCodeNotFound exception
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("item.ser"));
            GloceryItem[] items = new GloceryItem[10]; // Assuming there are 10 items in the file
            for (int i = 0; i < 10; i++) {
                items[i] = (GloceryItem) in.readObject();
            }
            in.close();
            
            
            
            int index = Integer.parseInt(itemCode) - 1; // Assuming item codes start from 1
            if (index >= 0 && index < items.length) {
                item = items[index];
            } else {
                throw new ItemCodeNotFound("Item code not found. Please re-enter.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input. Please try again.");
        } catch (ItemCodeNotFound e) {
            System.out.println(e.getMessage()); // Print the exception message
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return item;
    }
    
}


public class Main {
    public static void main(String[] args) {
        while(true){
            ArrayList<GloceryItem> item_list = new ArrayList<GloceryItem>();
            //getting the name of the customer
            InputStreamReader cusNameR = new InputStreamReader(System.in);
            BufferedReader cusNameBR = new BufferedReader(cusNameR);
            System.out.println("Enter the name of the customer:");
            String customer_name = "";
            try {
                customer_name = cusNameBR.readLine();
            } catch (IOException e) {
                System.out.println("Error reading customer name. Please try again.");
            }
            //creating a way to get the time and date of now by system
            java.util.Date date = new java.util.Date();
            //java.util.Date time = new java.util.Date();
            String fullDate = date.toString();
            String Date = fullDate.substring(8, 10) + "-" + fullDate.substring(4, 7) + "-" + fullDate.substring(24, 28);
            String Time = fullDate.substring(11, 19);

            POS pos = new POS("Pramod Bandara", "Moratuwa", customer_name, item_list,Date, Time);
            
            
            //serialize the above objects inside a file to take back in getItemDetails method
            try {
                //Following are the list of items that are in the shop
            
                GloceryItem item1 = new GloceryItem("1", "Flour", 300, 1, "01-05-2024", "01-01-2026", "Rayin", 0);
                GloceryItem item2 = new GloceryItem("2", "Rice", 200, 1, "10-07-2024", "10-01-2026", "Rathna", 10);
                GloceryItem item3 = new GloceryItem("3", "Milk", 100, 1, "21-03-2024", "21-01-2026", "Nestle", 5);
                GloceryItem item4 = new GloceryItem("4", "Sugar", 150, 1, "01-01-2024", "01-01-2026", "Cargills", 0);
                GloceryItem item5 = new GloceryItem("5", "Tea", 250, 1, "01-01-2024", "01-01-2025", "Dilmah", 0);
                GloceryItem item6 = new GloceryItem("6", "Coffee", 300, 1, "01-01-2023", "01-01-2026", "Nescafe", 0);
                GloceryItem item7 = new GloceryItem("7", "Bread", 50, 1, "02-05-2024", "03-05-2024", "Cargills", 25);
                GloceryItem item8 = new GloceryItem("8", "Butter", 200, 1, "01-01-2024", "01-01-2025", "Cargills", 0);
                GloceryItem item9 = new GloceryItem("9", "Cheese", 300, 1, "01-01-2024", "01-01-2025", "Cargills", 0);
                GloceryItem item10 = new GloceryItem("10", "Eggs", 100, 1, "01-01-2024", "01-01-2025", "Cargills", 0);
                
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("item.ser"));
                out.writeObject(item1);
                out.writeObject(item2);
                out.writeObject(item3);
                out.writeObject(item4);
                out.writeObject(item5);
                out.writeObject(item6);
                out.writeObject(item7);
                out.writeObject(item8);
                out.writeObject(item9);
                out.writeObject(item10);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //ask the cashier to enter the customer bought items one by one and then if it is over said enter 0
            while (true) {
                try {
                    GloceryItem item = pos.getItemDetails();
                    if (item == null) {
                        pos.printBill();
                        break;
                    }
                    pos.addItem(item);
                }
                catch (PendException e){
                    System.out.println("Next Customer Please!!!");
                    try {
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PendingBills.ser"));
                        out.writeObject(pos);
                        out.close();
                        pos.pending += 1;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;

                }
                
            }
            
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("1)Are there any customers:");
                System.out.println("2)Are there any pended Billed customers:");
                System.out.println("Enter the number for your preference? ");
                String prefer = br.readLine();
                int preferNum = Integer.parseInt(prefer);
                if(preferNum == 2){
                    try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream("PendingBills.ser"));
                    POS pendPos  = (POS) in.readObject();
                    in.close();
                    POS.pending -= 1;
                    GloceryItem item = null;
                    while (true) {
                        try {
                            item = pendPos.getItemDetails();
                            if (item == null) {
                                pendPos.printBill();
                                break;
                            }
                            //System.out.println(item);
                            pendPos.addItem(item);
                        }
                        
                        catch (PendException e){
                            System.out.println("Next Customer Please!!!");
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PendingBills.ser"));
                            out.writeObject(pendPos);
                            pendPos.pending += 1;
                            break;
                        }
                        
                    }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else if (preferNum == 1){

                }
                else{
                    break;
                }
            
        } catch (IOException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer value.");
        }
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1)Are there any customers:");
            System.out.println("2)No");
            System.out.println("Enter the number for your preference? ");
            String prefer = br.readLine();
            int preferNum = Integer.parseInt(prefer);
            if(preferNum == 1){
                
            }else{
                break;
            }
        }catch(IOException e){
            System.out.println(e.getMessage());

        }
    }
    }
}

