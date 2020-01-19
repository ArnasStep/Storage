import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

public class Storage {
    private List<Product> products;

    public Storage (List<Product> products){
        this.products = products;
    }

    public Storage(String file) throws FileNotFoundException, ParseException {
        products = new ArrayList<>();
        Scanner sc = new Scanner(new File(file));
        sc.useDelimiter(",|\n");
        sc.nextLine(); // Ignore title line
        while(sc.hasNext()) {
            Product product = new Product(sc.next(), sc.next(), sc.nextInt(), Product.dateFormat.parse(sc.next()));
            this.addProduct(product);
        }
        Collections.sort(products);
    }

    public void addProduct(Product product){
        for(Product p : products){
            if(p.groupWith(product)) return;
        }
        products.add(product);
    }

    public List<Product> filterByQuantity(int quantity){
        List<Product> filtered = new ArrayList<>();
        for(Product p : products){
            if(p.getQuantity() < quantity)
                filtered.add(p);
        }
        return filtered;
    }

    public List<Product> filterByExpiration(Date expiration){
        List<Product> filtered = new ArrayList<>();
        for(Product p : products){
            if(p.getExpiration().before(expiration))
                filtered.add(p);
        }
        return filtered;
    }
}
