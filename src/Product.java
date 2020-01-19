import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Comparable<Product>{
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String name;
    private String code;
    private int quantity;
    private Date expiration;

    public Product(String name, String code, int quantity, Date expiration){
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.expiration = expiration;
    }

    public boolean groupWith(Product product){
        if( product.getName().equals(name) &&
            product.getCode().equals(code) &&
            product.getExpiration().equals(expiration)){
            quantity += product.getQuantity();
            return true;
        }
        return false;
    }

    public String getName(){return name;}
    public String getCode(){return code;}
    public int getQuantity(){return quantity;}
    public Date getExpiration(){return expiration;}
    public String toString(){
        return String.format("%-15s | %10s | %10d | %s", name, code, quantity, dateFormat.format(expiration));
    }

    @Override
    public int compareTo(Product product) {
        return name.compareTo(product.getName());
    }
}