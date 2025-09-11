import java.util.*;

public class HashFunction {  
    
    public static int hashing(String key) {
        int hashtableSize = 10;
        int hash = 0;
        
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            hash += (int) ch; 
        }
        
        return hash % hashtableSize; 
    }

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter characters: ");
        String inp = sc.nextLine();

        int hashValue = hashing(inp);
        System.out.println("After hashing: " + hashValue);

        sc.close();
    }
}
