package javaMySQL;

import java.sql.*;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {		
		App main = new App();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) {
	    	System.out.println("Where is your MySQL JDBC Driver?");
	    	e.printStackTrace();
	    	return;
	    }
        try {
            main.run();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	private void run() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	    Scanner scan=new Scanner(System.in);
	    String query,cmnd;
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost:3306/test?" +
		                                   "user=root&password=root"+
		    		                       "&autoReconnect=true&useSSL=false");// last key to turn of WARN
		} catch (SQLException ex) {
			// handle error in connections
		    System.out.println("SQLException: " + ex.getMessage());
		}
		try {
		    stmt = conn.createStatement();
    		System.out.println("Start lisening,\r\n"+
		                       "Please specify command");
    		
		    while(true) {		    	
		    	cmnd = scan.next();
		    	if (cmnd.equals("exit")) {
		    		System.out.println("lisening finished");
		    		break;
		    	}
		    	else if (cmnd.equals("first")) {
		    		query = "select e.firstname,e.lastname,e.contact_info\r\n" + 
		    				"from projectemployee prjemp\r\n" + 
		    				"join employee e on e.id = prjemp.employee_id\r\n" + 
		    				"where project_id = (\r\n" + 
		    				"  select project_id from\r\n" + 
		    				"  (select count(*) c,pe.project_id\r\n" + 
		    				"     from projectemployee pe\r\n" + 
		    				"     join position p on p.id = pe.position_id\r\n" + 
		    				"    where p.title = 'JavaDev'\r\n" + 
		    				"    group by pe.project_id\r\n" + 
		    				"    order by c desc limit 1) tmp)\r\n" + 
		    				"  and prjemp.position_id = (select id from position where title = 'PM')\r\n" + 
		    				"order by prjemp.start desc limit 1";
				    rs = stmt.executeQuery(query);
				    while (rs.next()) {
						String fname = rs.getString("firstname");
						String lname = rs.getString("lastname");
						String cinfo = rs.getString("contact_info");

						System.out.println("PM : " + fname + " " + lname);
						System.out.println("contacts : " + cinfo);
					}
		    		
		    	}
		    	else if (cmnd.equals("second")) {
		    		query = "select e.firstname,e.lastname -- , count(pe.project_id)\r\n" + 
		    				"  from employee e\r\n" + 
		    				"  join projectemployee pe on pe.employee_id = e.id\r\n" + 
		    				"  join position p on pe.position_id = p.id\r\n" + 
		    				"  join project pr on pr.id = pe.project_id\r\n" + 
		    				" where p.title = 'QASpecialist'\r\n" + 
		    				" group by e.firstname,e.lastname\r\n" + 
		    				" order by count(pe.project_id) desc;";
				    rs = stmt.executeQuery(query);
				    while (rs.next()) {
						String fname = rs.getString("firstname");
						String lname = rs.getString("lastname");

						System.out.println("QA : " + fname + " " + lname);
					}
		    		
		    	}
	    		System.out.println("Please specify command");
		    } // end of while loop
		}
		catch (SQLException ex){
			// handle error in query section
	        System.out.println("SQLException: " + ex.getMessage());
		}
		finally {    
			// it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed
        	scan.close();
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException sqlEx) { } // ignore	
	            rs = null;
	        }	
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException sqlEx) { } // ignore	
	            stmt = null;
	        }
	     }
	}
}
