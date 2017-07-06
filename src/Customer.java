import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
	
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

	public static boolean validate(int custID,String pass){
		try{
			dbInit();
			pass=encryptPass(pass);
			String query="select epass from password where cust_id="+custID+" order by pass_id desc";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()){
				if(rs.getString(1).equals(pass)){
					return true;
				}
				else{
					return false;
				}
			}
		}
		catch(Exception e){
			return false;
		}
		return false;
	}

	
	
	public static void login(){
		int custID;
		String pass,pass1;
		Scanner in=new Scanner(System.in);
		Scanner in1=new Scanner(System.in).useDelimiter("\\n");
		try{
			dbInit();
			System.out.print("\n\nEnter your ID: ");
			custID=in.nextInt();
			System.out.print("\nEnter your password: ");
			pass=in1.nextLine();
			System.out.print("\nRe-enter your password: ");
			pass1=in1.nextLine();
			if(pass.equals(pass1)){
				pass=encryptPass(pass);
				String query="select epass from password where cust_id="+custID+" order by pass_id desc";
				ResultSet rs=stmt.executeQuery(query);
				if(rs.next()){
					if(rs.getString(1).equals(pass)){
						System.out.println("\nWelcome user!");
						menu(custID);
					}
					else{
						System.out.println("\nYou entered wrong password!");
						Customer.login();
					}
				}
				else{
					System.out.println("\nInvalid password!");
					Customer.login();
				}
			}
			else{
				System.out.println("\nPasswords do not match");
				Customer.login();
			}
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static void createPass(int custID){
		String pass,pass1,query;
		Scanner in1=new Scanner(System.in).useDelimiter("\\n");
		try{
			dbInit();
			System.out.print("\n\nEnter your new password: ");
			pass=in1.nextLine();
			System.out.print("\nRe-enter your password: ");
			pass1=in1.nextLine();
			if(pass.equals(pass1)){
				if(encryptPass(pass).equals("false")){
					System.out.println("\nPassword cannot contain special characters!");
					createPass(custID);
				}
				else{
					pass=encryptPass(pass);
					query="insert into password(cust_id,epass) values(?,?)";
					PreparedStatement ps=conn.prepareStatement(query);
					ps.setInt(1,custID);
					ps.setString(2,pass);
					ps.executeUpdate();
					query="update customer set chk=1 where cust_id="+custID;
					stmt.executeUpdate(query);
					menu(custID);
				}
			}
			else{
				System.out.println("\nPasswords do not match");
				createPass(custID);
			}
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static void addAccount(int custID){
		float balance;
		String query;
		int acctNo=0;
		try{
			dbInit();
			query="insert into account(cust_id) values(?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1,custID);
			ps.executeUpdate();
			query="select acct_id from account where cust_id="+custID+" order by acct_id desc";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()){
				System.out.println("\nYour new account number is "+rs.getInt(1));
				acctNo=rs.getInt(1);
			}
			query="insert into transaction(trans_type,amt,balance,acct_no) values('Opening       ',10000,10000,"+acctNo+")";
			stmt.executeUpdate(query);
			conn.close();
			menu(custID);
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static String encryptPass(String pass){
	    StringBuilder sb = new StringBuilder();
        for(char ch:pass.toCharArray()){
        	if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z') || (ch>='0' && ch<='9')){
		        if(ch=='z' || ch=='Z'){
		        	sb.append((char)((int)ch-25));
		        }
		        else if(ch=='9'){
		        	sb.append((char)((int)ch-9));
		        }
		        else{
	            	sb.append((char)((int)ch+1));
		        }
        	}
        	else
        		return "false";
        }
        pass=sb.toString();
        return pass;
	}
	
	public static void removeAccount(int custID){
		int acctNo;
		Scanner in=new Scanner(System.in);
		System.out.print("\nEnter your account no: ");
		acctNo=in.nextInt();
		String query;
		try{
			dbInit();
			query="select acct_id from account where acct_id="+acctNo;
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next()){
				query="delete from account where acct_id="+acctNo;
				stmt.executeUpdate(query);
				System.out.println("Account successfully deleted!");
			}
			else{
				System.out.println("Account not found!");
			}
			conn.close();
			menu(custID);
		}
		catch(Exception e){
			System.out.println("\nAccount not found..");
			Menu.start();
		}
	}
	
	
 	public static boolean getLastThreePasswords(int custID,String pass){
		String query,epass;
		epass=encryptPass(pass);
		int count=0,limit=3,i=0;
		try{
			dbInit();
			Statement stmt1=conn.createStatement();
			query="select count(epass) from password where cust_id="+custID;
			ResultSet rs=stmt.executeQuery(query);
			ResultSet rs1;
			if(rs.next()){
				count=rs.getInt(1);
				if(count<3){
					query="select epass from password where cust_id="+custID+" order by pass_id desc";
					rs1=stmt1.executeQuery(query);
					while(rs1.next()){
						if((rs1.getString(1)).equals(epass)){
							return true;
						}
					}	
				}
				else{
					query="select epass from password where cust_id="+custID+" order by pass_id desc";
					rs1=stmt1.executeQuery(query);
					while(i<limit){
						rs1.next();
						if((rs1.getString(1)).equals(epass))
							return true;
						i++;
					}
				}
				conn.close();
				return false;
			}	
			return false;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	} 
	
	public static boolean checkPassword(String pass){
		int lCase=0,uCase=0,num=0;
		if(pass.length()<6){
			System.out.println("\nPassword should be minimum 6 characters in length");
			return false;
		}
		for(char ch:pass.toCharArray()){
        	if(Character.isDigit(ch)){
		        num++;
        	}
        	else if(Character.isUpperCase(ch)){
        		uCase++;
        	}
        	else if(Character.isLowerCase(ch)){
        		lCase++;
        	}
        }
		if(num<2 || uCase<2 || lCase<2){
			System.out.println("\nThere must be atleast 2 lower case, 2 upper case and 2 digits in your password!");
			return false;
		}
		return true;	
	}
	
	public static void changePassword(int custID){
		String pass,pass1,curpass;
		Scanner in=new Scanner(System.in).useDelimiter("\\n");
		System.out.print("\nEnter your current password: ");
		curpass=in.nextLine();
		if(validate(custID,curpass)){
			System.out.print("\nEnter your new password: ");
			pass=in.nextLine();
			System.out.print("\nRe-enter your new password: ");
			pass1=in.nextLine();
			if(pass.equals(pass1)){
				if(encryptPass(pass).equals("false")){
					System.out.println("\nSpecial characters not allowed!");
					Customer.changePassword(custID);
				}
				else if(checkPassword(pass)==false){
					Customer.changePassword(custID);
				}
				else if(getLastThreePasswords(custID,pass)){
					System.out.println("\nYou have entered a password that was already in use recently.. Try another!");
					Customer.changePassword(custID);
				}
				else{
					pass=encryptPass(pass);
					String query;
					try{
						Class.forName("com.mysql.jdbc.Driver");
						dbInit();
						query="insert into password(cust_id,epass) values(?,?)";
						PreparedStatement ps=conn.prepareStatement(query);
						ps.setInt(1,custID);
						ps.setString(2,pass);
						ps.executeUpdate();
						System.out.println("Password successfully changed!");
						conn.close();
						menu(custID);
					}
					catch(Exception e){
						System.out.println(e);
						Menu.start();
					}
				}
			}
			else{
				System.out.println("\nGiven passwords do not match..");
				Customer.changePassword(custID);
			}
		}
		else{
			System.out.println("\nGiven password is wrong!");
			Customer.changePassword(custID);
		}
	}
	
	
	public static void gotoAccount(int custID){
		int acctID;
		Scanner in=new Scanner(System.in);
		String query="select distinct(a.acct_id) from account a,customer c where a.cust_id=c.cust_id and c.cust_id="+custID+" order by a.acct_id";
		try{
			dbInit();
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("\nList of available accounts: ");
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
			System.out.println("\nEnter your desired account ID: ");
			acctID=in.nextInt();
			query="select distinct(a.acct_id) from account a,customer c where a.cust_id=c.cust_id and c.cust_id="+custID+" order by a.acct_id";
			rs=stmt.executeQuery(query);
			while(rs.next()){
				if(rs.getInt(1)==acctID){
					Account.menu(acctID,custID);
					System.out.println("Password successfully changed!");
					break;
				}
			}
			System.out.println("Please select your own account!");
			conn.close();
			menu(custID);
		}
		catch(Exception e){
			System.out.println(e);
			Menu.start();
		}
	}
	
	public static void menu(int custID){
		System.out.print("\nEnter your option, \n1.Add new account\n2.Remove existing account\n3.Change password\n4.Go to account\n5.Logout\nEnter: ");
		int choice;
		Scanner in=new Scanner(System.in);
		choice=in.nextInt();
		if(choice==1)
			addAccount(custID);
		else if(choice==2)
			removeAccount(custID);
		else if(choice==3)
			changePassword(custID);
		else if(choice==4)
			gotoAccount(custID);
		else
			Menu.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
