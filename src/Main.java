import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<Product> products;
        Storage storage = null;
        try {
            storage = new Storage("sample.csv");
        }catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(-1);
        }catch (ParseException e){
            System.err.println("Invalid file contents!");
            System.exit(-1);
        }
        while(true) {
            System.out.print("Enter quantity or expiration date: ");
            try {
                if (in.hasNext()) {
                    if (in.hasNextInt()){
                        int quantity = Integer.parseInt(in.nextLine());
                        products = storage.filterByQuantity(quantity);
                    }
                    else {
                        String inStr = in.nextLine();
                        if (inStr.equals("quit")){
                            break;
                        }
                        products = storage.filterByExpiration(Product.dateFormat.parse(inStr));
                    }
                    printProducts(products);
                }
            } catch (ParseException e) {
                System.out.println("Invalid argument: expected Integer or Date with format yyyy-MM-dd.\n");
            }
        }
    }
    public static void printProducts(List<Product> products){
        for(Product p : products){
            System.out.println(p);
        }
    }
}
