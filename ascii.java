import java.util.Scanner;
public class ascii {
    public static void main(String[] args){
        Scanner ch = new Scanner(System.in);
        System.out.print("Enter the charaters: ");
        char c = ch.next().charAt(0);

        int ascii = (int) c;
        System.out.print("the value is: "+ ascii);

           ch.close();


    }
}