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
	    System.out.println("Application starts");
	    if (args[0].equals("prepare"))dataPreparation.createStructure() ;
	    if (args[1].equals("fill")) dataPreparation.fillRandomly();

	    main.run();
	}
	
	private void run() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	    Scanner scan=new Scanner(System.in);
	    String query;
		try {
		    conn = DriverManager.getConnection(dataPreparation.DB_URL, dataPreparation.USER, dataPreparation.PASS);
		    stmt = conn.createStatement();
		} catch (SQLException ex) {
			// handle error in connections
		    System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			query = "select e.firstname,e.lastname,e.contact_info " + 
					"from projectemployee prjemp " + 
					"join employee e on e.id = prjemp.employee_id " + 
					"where project_id = ( " + 
					"  select project_id from " + 
					"  (select count(*) c,pe.project_id " + 
					"     from projectemployee pe " + 
					"     join position p on p.id = pe.position_id " + 
					"    where p.title = '"+dataPreparation.JAVADEVELOPER+"' " + 
					"    group by pe.project_id " + 
					"    order by c desc limit 1) tmp) " + 
					"  and prjemp.position_id = (select id from position where title = '"+dataPreparation.PROJECTMANAGER+"') " +
					"  and e.isActive = true "+
					"order by prjemp.start desc limit 1";
			rs = stmt.executeQuery(query);
		    while (rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				String cinfo = rs.getString("contact_info");
				System.out.println("PM : " + fname + " " + lname);
				System.out.println("contacts : " + cinfo);
			}		
            rs.close();    		
		    query = "select e.firstname,e.lastname " + 
    				"  from employee e" + 
    				"  join projectemployee pe on pe.employee_id = e.id " + 
    				"  join position p on pe.position_id = p.id " + 
    				"  join project pr on pr.id = pe.project_id " + 
    				" where p.title = '"+dataPreparation.QASPECIALIST+"' " + 
    				"   and e.isActive = true "+
    				"   and pr.isActive = true "+
    				" group by e.firstname,e.lastname " + 
    				" order by count(pe.project_id) desc;";
		    rs = stmt.executeQuery(query);
		    while (rs.next()) {
				String fname = rs.getString("firstname");
				String lname = rs.getString("lastname");
				System.out.println("QA : " + fname + " " + lname);
			}
		}catch (SQLException ex){
			// handle error in query section
	        System.out.println("SQLException: " + ex.getMessage());
		}finally {    
			// it is a good idea to release resources in a finally{} block in reverse-order of their creation
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
