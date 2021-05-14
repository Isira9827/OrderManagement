package com;

import java.sql.*; 
public class Order 
{ //Connection to the DB

private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //DB access denied 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order", "root", "isira123"); 
 } 
    catch (Exception e) 
    {e.printStackTrace();} 
 
    return con; 
 } 


public String insertOrder (String orderName, String orderCategory, String paymentMethod, String orderPayment) 
{ 
  String output = ""; 

try
{ 
	 
   Connection con = connect(); 
   
   if (con == null) 
   {return "Error while connecting to the database for inserting."; } 

   // create a prepared statement
   String query = " INSERT INTO order VALUES (?, ?, ?, ?, ?)"; 

   PreparedStatement preparedStmt = con.prepareStatement(query); 

   // binding values
 
   preparedStmt.setInt(1, 0); 
   preparedStmt.setString(2, orderName); 
   preparedStmt.setString(3, orderCategory);
   preparedStmt.setString(4, paymentMethod);
   preparedStmt.setString(5, orderPayment);

   // execute the statement
   preparedStmt.execute(); 
   con.close(); 
    String newOrder = readOrder();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrder + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

public String readOrder() 
{ 
   String output = ""; 
   
   try
  { 
     Connection con = connect(); 
 
     if (con == null) 
     {return "Error while connecting to the database for reading."; } 
 
     // Prepare the HTML table to be displayed
     output = "<table border='1'><tr><th>Order Name</th>" +
              "<th>Order Category</th>" +
              "<th>Payment Method</th>" +
              "<th>Order Payment</th>" +
              "<th>Update</th><th>Remove</th></tr>"; 
 
   String query = "SELECT * FROM order"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
 
   // iterate through the rows in the result set
  while (rs.next()) 
 { 
      String orderID = Integer.toString(rs.getInt("orderID")); 
      String orderName = rs.getString("orderName"); 
      String orderCategory = rs.getString("orderCategory"); 
      String paymentMethod = rs.getString("paymentMethod");
      String orderPayment = rs.getString("orderPayment");
 
   // Add a row into the HTML table
		 output += "<tr><td><input id='hiduIdUpdate' name='hiduIdUpdate' type='hidden' value='" + orderID + "'>"
				 + orderName + "</td>";
		 output += "<td>" + orderCategory + "</td>"; 
		 output += "<td>" + paymentMethod + "</td>"; 
		 output += "<td>" + orderPayment + "</td>";
		
   // buttons
		 output += "<td><input name='btnUpdate' " 
		 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-uid='" + orderID + "'></td>"
		 + "<td><form method='post' action='orders.jsp'>"
		 + "<input name='btnRemove' " 
		 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-uid='" + orderID + "'>"
		 + "<input name='hiduIdDelete' type='hidden' " 
		 + " value='" + orderID + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the HTML table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the orders."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

public String updateOrder(String orderID, String orderName, String orderCategory, String paymentMethod, String orderPayment)
 { 
   
	String output = ""; 
 
	try
   { 
      Connection con = connect(); 
 
      if (con == null) 
      {return "Error while connecting to the database for updating."; } 
 
     // create a prepared statement
     String query = "UPDATE order SET orderName=?,orderCategory=?,paymentMethod=?,orderPayment=? WHERE orderID=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 
     // binding values
    preparedStmt.setString(1, orderName); 
    preparedStmt.setString(2, orderCategory);
    preparedStmt.setString(3, paymentMethod);
    preparedStmt.setString(4, orderPayment);
    preparedStmt.setInt(5, Integer.parseInt(orderID)); 
 
    // execute the statement
    preparedStmt.execute(); 
    con.close();
	String newOrder = readOrder();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrder + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the order.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


public String deleteOrder(String orderID) 
 { 
 String output = ""; 
 
 try
 { 
    Connection con = connect(); 
    if (con == null) 
    {return "Error while connecting to the database for deleting."; } 
 
    // create a prepared statement
    String query = "DELETE FROM order WHERE orderID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
 
    // binding values
    preparedStmt.setInt(1, Integer.parseInt(orderID)); 
 
 // execute the statement
 	 preparedStmt.execute(); 
 	 con.close(); 
 	 String newOrder = readOrder();
 	 output =  "{\"status\":\"success\", \"data\": \"" + 
 			 newOrder + "\"}"; 
 	 } 

 	catch (Exception e) 
 	 { 
 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the order.\"}";  
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
 		}








} 