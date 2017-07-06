import java.util.Scanner;
import java.sql.*;

public class Bank {
	
	public static Connection conn;
	public static Statement stmt;
	
	public static void dbInit(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/bank?autoReconnect=true&useSSL=false","root","root");
			stmt=conn.createStatement();
		}
		catch(Exception e){
			System.out.println("Error in database connection!");
		}
	}
	
	public static void menu(){
		System.out.print("\nEnter your option, \n1.Add new Customer\n2.Remove existing Customer\n3.See top n customers\n4.Customer details\n5.Exit to main menu\nEnter: ");
		int choice;
		Scanner in=new Scanner(System.in);
		choice=in.nextInt();
		if(choice==1)
			addCustomer();
		else if(choice==2)
			removeCustomer();
		else if(choice==3)
			getTopCustomers();
		else if(choice==4)
			getCustomerDetails();
		else
			Menu.start();
	}
	
	public static void addCustomer(){
		String custName;
		int custID;
		Scanner in=new Scanner(System.in);
		Scanner in1=new Scanner(System.in).useDelimiter("\\n");
		try{
			dbInit();
			System.out.print("\nEnter the name of the customer: ");
			custName=in1.nextLine();
			String query="insert into customer(name) values(?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1,custName);
			ps.executeUpdate();
			query="select cust_id from customer order by cust_id desc";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()){
				System.out.println("\nCustomer added successfully! Customer ID - "+rs.getInt(1));
				custID=rs.getInt(1);
				Customer.createPass(custID);
			}
			conn.close();
			menu();
		}
		catch(Exception e){
			System.out.println(e);
			menu();
		}
	}
	
	public static void removeCustomer(){
		int custID;
		String query;
		Scanner in=new Scanner(System.in);
		try{
			dbInit();
			System.out.print("\n\nEnter the ID of the customer: ");
			custID=in.nextInt();
			query="select cust_id from customer where cust_id="+custID;
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()){
				query="delete from customer where cust_id="+custID;
				stmt.executeUpdate(query);
				System.out.println("Customer successfully removed!");
			}
			else{
				System.out.println("Customer not found!");
			}
			conn.close();
			menu();
		}
		catch(Exception e){
			System.out.println("Customer not found..");
			menu();
		}
	}
	
	public static void getTopCustomers(){
		String query;
		int count=0,i=0;
		Scanner in=new Scanner(System.in);
		try{
			dbInit();
			System.out.println("\nHow many entries do you want? ");
			count=in.nextInt();
			if(count<1){
				System.out.println("\nPlease specify a valid number..");
			}
			else{
				query="select c.name,a.balance,a.acct_id from customer c,account a where a.cust_id=c.cust_id order by a.balance desc";
				ResultSet rs=stmt.executeQuery(query);
				System.out.println("Top "+count+" customers: ");
				while(i<count && rs.next()){
					System.out.println("\n"+rs.getString(1)+" has a balance of "+rs.getFloat(2)+" in the account "+rs.getInt(3));
					i++;
				}
			}
			conn.close();
			menu();
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static void getCustomerDetails(){
		String query;
		try{
			dbInit();
			query="select c.cust_id,a.acct_id,c.name,a.balance,p.epass from customer c,account a,password p where a.cust_id=c.cust_id and c.cust_id=p.cust_id order by c.cust_id";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("\nCustomer Details: \n\n------------------------------------------------------------------------------------------------");
			System.out.println("Cust ID\t\tAcct No\t\tCust Name\t\t      Balance\t\tPassword");
			System.out.println("------------------------------------------------------------------------------------------------");
			while(rs.next()){
				System.out.println("\n"+rs.getInt(1)+"\t\t"+rs.getInt(2)+"\t\t"+String.format("%-30s%.2f" ,rs.getString(3),rs.getFloat(4))+"\t\t"+rs.getString(5));
			}
			System.out.println("\n------------------------------------------------------------------------------------------------");
			conn.close();
			menu();
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
