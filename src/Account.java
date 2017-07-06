import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Account {
	
	public static int custID;
	
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

	public static void menu(int acctID,int cID){
		System.out.print("\nEnter your option, \n1.Withdraw\n2.Deposit\n3.Transfer\n4.See Transactions\n5.Go to main\nEnter: ");
		int choice;
		custID=cID;
		Scanner in=new Scanner(System.in);
		choice=in.nextInt();
		if(choice==1)
			withdraw(acctID);
		else if(choice==2)
			deposit(acctID);
		else if(choice==3)
			transfer(acctID);
		else if(choice==4)
			transactions(acctID);
		else
			Customer.menu(custID);
	}
	
	public static void doCashCheck(int acctID,float cash){
		String query;
		float bal=0;
		if(cash>5000.00){
			try{
				dbInit();
				query="select balance from account where acct_id="+acctID;
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					bal=rs.getFloat(1);
					bal-=10;
					query="update account set balance="+bal+" where acct_id="+acctID;
					stmt.executeUpdate(query);
					System.out.println("Your transaction exceeds 5000 rupees! Service fee of 10 rupees charged.. New balance - "+bal);
					query="insert into transaction(trans_type,amt,balance,acct_no,oflag) values('OperationalFee',10,"+bal+","+acctID+",1)";
					stmt.executeUpdate(query);
				}
				conn.close();
			}
			catch(Exception e){
				System.out.println(e);
				Account.menu(acctID,custID);
			}
		}
	}
	
	public static void withdraw(int acctID){
		forcePassChange(acctID);
		Scanner in=new Scanner(System.in);
		String query;
		float cash,bal;
		bal=0;
		System.out.print("\nEnter amount to withdraw: ");
		cash=in.nextFloat();
		if(cash<=0){
			System.out.println("\nPlease enter a valid amount!");
			Account.menu(acctID,custID);
		}
		else{
			try{
				dbInit();
				query="select balance from account where acct_id="+acctID;
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					bal=rs.getFloat(1);
				}
				if(bal<=1000){
					System.out.println("\nYou have reached the minimum balance of 1000 rupees!");
				}
				else if((bal-cash)<1000){
					System.out.println("\nCannot withdraw money, minimum balance of 1000 rupees is required as per norms!");
				}
				else if(bal<cash){
					System.out.println("\nInsufficient balance. Available - "+bal);
				}
				else{
					bal-=cash;
					query="update account set balance="+bal+" where acct_id="+acctID;
					stmt.executeUpdate(query);
					System.out.println("Cash withdrawn successfully! New balance - "+bal);
					query="insert into transaction(trans_type,amt,balance,acct_no) values('ATMWithdrawal',"+cash+","+bal+","+acctID+")";
					stmt.executeUpdate(query);
					doMaintenanceCheck(acctID);
				}
				conn.close();
				Account.menu(acctID,custID);
			}
			catch(Exception e){
				System.out.println(e);
				Account.menu(acctID,custID);
			}
		}
	}
	
	public static void deposit(int acctID){
		forcePassChange(acctID);
		Scanner in=new Scanner(System.in);
		String query;
		float cash,bal=0;
		System.out.print("\nEnter amount to deposit: ");
		cash=in.nextFloat();
		if(cash<=0){
			System.out.println("\nPlease enter a valid amount!");
			Account.menu(acctID,custID);
		}
		else{
			try{
				dbInit();
				query="select balance from account where acct_id="+acctID;
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					bal=rs.getFloat(1);
				}
				bal+=cash;
				query="update account set balance="+bal+" where acct_id="+acctID;
				stmt.executeUpdate(query);
				System.out.println("Cash deposited successfully! New balance - "+bal);
				query="insert into transaction(trans_type,amt,balance,acct_no) values('CashDeposit',"+cash+","+bal+","+acctID+")";
				stmt.executeUpdate(query);
				conn.close();
				doMaintenanceCheck(acctID);
				Account.menu(acctID,custID);
			}
			catch(Exception e){
				System.out.println(e);
				Account.menu(acctID,custID);
			}
		}
	}
	
	public static void forcePassChange(int acctID){
		String query;
		int count=0;
		float bal=0;
		try{
			dbInit();
			query="select acct_no,oflag from transaction where sflag=0 order by trans_id desc limit 5;";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()){
				if(rs.getInt(2)==1){
					break;
				}
				if(rs.getInt(1)==acctID){
					count++;
				}
			}
			if(count==5){
				System.out.println("\nYou have performed 5 transactions.. Please change your password!");
				query="select balance from account where acct_id="+acctID;
				rs=stmt.executeQuery(query);
				if(rs.next()){
					bal=rs.getFloat(1);		
				}
				query="insert into transaction(trans_type,amt,balance,acct_no,oflag) values ('ForcePassChange',0,"+bal+","+acctID+",1)";
				stmt.executeUpdate(query);
				Customer.changePassword(custID);
			}
		}
		catch(Exception e){
			System.out.println(e);
			Account.menu(acctID,custID);
		}
	}
	
	public static void transfer(int acctID){
		forcePassChange(acctID);
		Scanner in=new Scanner(System.in);
		String query;
		float cash,bal=0,bal1=0;
		int acctID2;
		System.out.print("\nEnter amount to transfer: ");
		cash=in.nextFloat();
		if(cash<=0){
			System.out.println("\nPlease enter a valid amount!");
			Account.menu(acctID,custID);
		}
		else{
			System.out.print("\nEnter the account number to transfer to: ");
			acctID2=in.nextInt();
			try{
				dbInit();
				query="select balance from account where acct_id="+acctID;
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					bal=rs.getFloat(1);
				}
				if(bal<=1000){
					System.out.println("\nYou have reached the minimum balance of 1000 rupees!");
				}
				else if((bal-cash)<1000){
					System.out.println("\nCannot transfer money, minimum balance of 1000 rupees is required as per norms!");
				}
				else if(bal<cash){
					System.out.println("\nInsufficient balance. Available - "+bal);
				}
				else{
					bal-=cash;
					query="update account set balance="+bal+" where acct_id="+acctID;
					stmt.executeUpdate(query);
					query="update account set balance=balance+"+cash+" where acct_id="+acctID2;
					stmt.executeUpdate(query);
					System.out.println("Cash transferred successfully! New balance - "+bal);
					query="select balance from account where acct_id="+acctID2;
					rs=stmt.executeQuery(query);
					if(rs.next()){
						bal1=rs.getFloat(1);
					}
					query="insert into transaction(trans_type,amt,balance,acct_no) values('TransferTo"+acctID2+"',"+cash+","+bal+","+acctID+")";
					stmt.executeUpdate(query);
					query="insert into transaction(trans_type,amt,balance,acct_no,sflag) values('TransferFrom"+acctID+"',"+cash+","+bal1+","+acctID2+",1)";
					stmt.executeUpdate(query);
					doCashCheck(acctID,cash);
					doMaintenanceCheck(acctID);
				}
				conn.close();
				Account.menu(acctID,custID);
			}
			catch(Exception e){
				System.out.println(e);
				Account.menu(acctID,custID);
			}
		}
	}
	
	public static void transactions(int acctID){
		String query;
		try{
			dbInit();
			System.out.println("\nAccount Statement: \n");
			query="select c.name from customer c,account a where a.cust_id=c.cust_id and a.acct_id="+acctID;
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
				System.out.println("\nName: "+rs.getString(1)+"\nAccount no - "+acctID+"\nCustomer ID - "+custID+"\n");    
			query="select trans_id,trans_type,amt,balance from transaction where acct_no="+acctID;
			rs=stmt.executeQuery(query);
			System.out.println("\nTransactions: \n\n----------------------------------------------------------------------------");
			System.out.println("Trans ID\tTrans Type\t\t\tAmount\t\t  Balance");
			System.out.println("----------------------------------------------------------------------------");
			while(rs.next()){
				System.out.println("\n"+rs.getInt(1)+"\t\t"+String.format("%-30s %-12.2f       %-12.2f" ,rs.getString(2),rs.getFloat(3),rs.getFloat(4)));
			}
			System.out.println("\n----------------------------------------------------------------------------");
			Account.menu(acctID,custID);
		}
		catch(Exception e){
			System.out.println(e);
			Account.menu(acctID,custID);
		}
	}
	
	public static void doMaintenanceCheck(int acctID){
		String query;
		int count=0;
		float bal=0;
		if(checkTop(acctID)){
			try{
				dbInit();
				query="select count(acct_no) from transaction where acct_no="+acctID+" and oflag=0 and sflag=0";
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					count=rs.getInt(1);
				}
				if(count%10 == 0 && count>0){
					query="select balance from account where acct_id="+acctID;
					rs=stmt.executeQuery(query);
					if(rs.next()){
						bal=rs.getFloat(1);
						bal-=100;
						query="update account set balance="+bal+" where acct_id="+acctID;
						stmt.executeUpdate(query);
						System.out.println("Your have completed 10 transactions! Service fee of 100 rupees charged.. New balance - "+bal);
						query="insert into transaction(trans_type,amt,balance,acct_no,oflag) values('MaintenanceFee',100,"+bal+","+acctID+",1)";
						stmt.executeUpdate(query);
					}
					Account.menu(acctID,custID);
				}
			}
			catch(Exception e){
				System.out.println(e);
				Account.menu(acctID,custID);
			}
		}
	}
	
	public static boolean checkTop(int acctNo){
		String query;
		int count=3,i=0;
		try{
			dbInit();
			query="select a.acct_id from customer c,account a where a.cust_id=c.cust_id order by a.balance desc";
			ResultSet rs=stmt.executeQuery(query);
			while(i<count && rs.next()){
				if(rs.getInt(1)==acctNo)
					return false;
				i++;
			}
			conn.close();
		}
		catch(Exception e){
			System.out.println(e);
			Account.menu(acctNo,custID);
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}