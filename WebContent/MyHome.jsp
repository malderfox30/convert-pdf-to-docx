<%@page import="model.BO.GetHistoryBO"%>
<%@page import="java.util.List"%>
<%@page import="model.Bean.Account"%>
<%@page import="model.Bean.History"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Convert PDF to DOCX</title>
  <style>
    .container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin: 30px auto;
      background-color: rgb(189, 186, 186);
      background: #83a4d4;  
	  background: -webkit-linear-gradient(to right, #b6fbff, #83a4d4);  
      background: linear-gradient(to right, #b6fbff, #83a4d4);
    }
    .welcome {
    	display: flex;
    	align-items: center;
    	justify-content: space-between;
    }
    label {
      font-size: 18px;
    }
    h1 {
      font-weight: 700;
    }
    h3 {
      padding-right: 30px;
    }
    button {
      display: block;
      border-radius: 20px;
      padding: 10px 20px;
      margin: 20px auto;
      border: none;
      cursor: pointer;
    }
    button:hover{
      background-color: rgb(224, 221, 221);
    }
    .history{
      overflow: auto;
      max-height: 300px;
      padding: 0px 20px
    }
    table {
    	width: 100%;
    	border: 1px solid black;
	}
	table td {
		text-align: center;
	}
  </style>
</head>
<body>
	<% Account a = (Account)request.getSession().getAttribute("account");
		int index = 0;
		if (a!=null) { %>
	 <div class="container">
	    <div class="welcome">
	      <h3>Welcome, <%= a.getFullName() %></h3>
	      <a href="LogoutServlet">
	      	<button>Logout</button>
	      </a>
	    </div>
	
	    <h1>CONVERT PDF TO DOCX</h1>
	    <form action='UploadFileServlet' method="post" enctype="multipart/form-data">
	    	<label>Select file to upload:</label>
		    <input type="file" name="file" accept="application/pdf"/><br />
		    <button type="submit">Upload</button>
	    </form>
	    
	<% 
		String message = (String)request.getSession().getAttribute("message");
		System.out.println(message);
		if (message!=null) {
	%>
		<p><%= message %></p>
	<% } %>
	    <h2>History</h2>
	    <table class="history">
	    	<tr>
	    		<th>#</th>
	    		<th>File Name</th>
	    		<th>Status</th>
	    		<th>Download Link</th>
	    		<th>Time</th>
	    	</tr>
			<%
				List<History> history = GetHistoryBO.getHistory(a.getId());
				for (History item : history) {
					index++;
			%>
			<tr>
	   			<td> <%= index %> </td>
	   			<td> <%= item.getFileName() %> </td>
	   			<td> <%= item.getStatus() %> </td>
      			<% if (item.getStatus().equals("running")) { %>
	      			<td> Unavailable </td>
	   			<% } else { %>
					<td> <a href="DownloadFileServlet?fileId=<%= item.getId() %>">Download</a> </td> 
	      		<% } %>
	   			<td> <%= item.getTime() %> </td>
	   		</tr>
			<% } %>
	  	</table>
	  </div>
	<% } else { response.sendRedirect("Login.jsp"); } %>
</body>
</html>

