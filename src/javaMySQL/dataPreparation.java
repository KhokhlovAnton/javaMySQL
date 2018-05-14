package javaMySQL;

import java.util.*;
import java.sql.*;

public class dataPreparation {
	
	static final String USER = "root";
	static final String PASS = "root";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
	
	public static final String PROJECTMANAGER = "PM";
	public static final String JAVADEVELOPER = "JavaDev";
	public static final String QASPECIALIST = "QASpecialist";
	
	private static Connection connection;
	private static Statement statement;
	
	private static String[] tableNames = {
			"project",
			"employee",
			"position",
			"projectemployee"
	};
	
	public static boolean createStructure() {

	    System.out.println("Build schema");
		
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("id", "int");
		fields.put("title", "varchar(200)");
		fields.put("description", "varchar(200)");
		fields.put("start", "date");
		fields.put("end", "date");
		fields.put("firstname", "varchar(200)");
		fields.put("lastname", "varchar(200)");
		fields.put("position_id", "int");
		fields.put("contact_info", "varchar(200)");
		fields.put("project_id", "int");
		fields.put("employee_id", "int");
		fields.put("isActive", "boolean");
		
		String sql = "";
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) {
	    	System.out.println("Where is your MySQL JDBC Driver?");
	    	e.printStackTrace();
	    	return false;
	    }
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();
			 for (int i = 0; i < tableNames.length; i++) {
					sql = "CREATE TABLE IF NOT EXISTS `"+tableNames[i]+
							"`(`id` "+fields.get("id")+" NOT NULL AUTO_INCREMENT,"+
							" PRIMARY KEY (id));";
					statement.execute(sql);
					if((i == 0) || (i == 2)) {
						sql = "ALTER TABLE `"+tableNames[i]+
						        "` ADD COLUMN title "+fields.get("title") + " not null";
						if(i == 0) sql += ", ADD COLUMN description "+fields.get("description") + " not null"+
								          ", ADD COLUMN start "+fields.get("start") + " not null"+
								          ", ADD COLUMN end "+fields.get("start") + " not null"+
								          ", ADD COLUMN isActive "+fields.get("isActive") + " not null;";
						else sql += ";";
					}
					else if(i == 1) {
						sql = "ALTER TABLE `"+tableNames[i]+
								"` ADD COLUMN firstname "+fields.get("firstname") + " not null,"+
								" ADD COLUMN lastname "+fields.get("lastname") + " not null,"+
								" ADD COLUMN position_id "+fields.get("position_id") + " not null,"+
								" ADD COLUMN contact_info "+fields.get("contact_info") + " not null,"+
								" ADD COLUMN isActive "+fields.get("isActive") + " not null;";			
					}
					else if(i == 3) {
						sql = "ALTER TABLE `"+tableNames[i]+
								"` ADD COLUMN project_id "+fields.get("project_id") + " not null,"+
								" ADD COLUMN employee_id "+fields.get("employee_id") + " not null,"+
								" ADD COLUMN position_id "+fields.get("position_id") + " not null,"+
								" ADD COLUMN start "+fields.get("start") + " not null,"+
								" ADD COLUMN end "+fields.get("end") + " not null;";							
					}
					statement.execute(sql);
			 }
			 
		} catch (SQLException ex) {
			// handle error in connections
		    System.out.println("SQLException: " + ex.getMessage());
	    	return false;
		}		
	    System.out.println("Schema builds successfully");
		return true;		
	}
	public static boolean fillRandomly() {
		String sql = "";
		String[] projects = {
				"test1",
				"test2",
				"test3",
				"test4"
		};
		String[] positions = {
				"PM",
				"JavaDev",
				"SQLDev",
				"QASpecialist"
		};
		String[] fnames = {
				"John",
				"Jane",
				"Rob",
				"Patrik",
				"Sam",
				"Samanta",
				"Will",
				"Cris",
				"Mel",
				"Nil"
		};
		String[] lnames = {
				"Dow",
				"Wilson",
				"Chuky",
				"Palansky",
				"Flatman",
				"Donnel",
				"Goodspeed",
				"Onail",
				"Hattman",
				"Johnson"
		};
		try {
			for(int i = 0; i < projects.length; i++) {
				sql += "INSERT INTO `project` ( `title`, `description`, `start`, `end`, `isActive`) VALUES"+
						"('"+projects[i]+"','Lorem ipsum dolor sit amet',";
				if(i == 0) sql += "'2018-05-01','2018-05-31',true);";
				else if(i == 1) sql += "'2018-01-01','2018-03-31',false);";
				else if(i == 2) sql += "'2017-05-01','2019-05-31',true);";
				else if(i == 3) sql += "'2018-04-01','2018-07-31',true);";
				statement.execute(sql);
				sql = "";
			}
			for(int i = 0; i < positions.length; i++) {
				sql += "INSERT INTO `position`(`title`) VALUES"+
					"('"+positions[i]+"');";
				statement.execute(sql);
				sql = "";			
			}
			for(int i = 0; i < (fnames.length+lnames.length); i++) {
				int randIndex = (int)(Math.random() * 10);
				sql += "INSERT INTO `employee` (`firstname`, `lastname`, `position_id`, `contact_info`, `isActive`) VALUES"+
						"('"+fnames[randIndex]+"','"+lnames[randIndex]+"',"+((randIndex % 4)+1)+",'"+fnames[randIndex]+lnames[randIndex]+"',true);";
					statement.execute(sql);
					sql = "";		
			}
			for(int i = 0; i < 29;i++) {
				//TODO: somehow insert randomly rows to projectemployee table
			}
		} catch (SQLException ex) {
			// handle error in inserting
		    System.out.println("SQLException: " + ex.getMessage());
	    	return false;
		}
		System.out.println("Data successfully loaded");
		return true;
	}

}
