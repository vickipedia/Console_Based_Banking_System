import java.util.Scanner;

public class Menu {
	public static void start(){
		int choice;
		Scanner in=new Scanner(System.in);
		System.out.print("\nPlease select your option, \n1. Bank Manager\n2. Customer\nEnter: ");
		choice=in.nextInt();
		if(choice==1)
			Bank.menu();
		else if(choice==2){
			Customer.login();
		}
		else
			start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
	}

}
