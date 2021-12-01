<%@page import="model.Bean.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    .container {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 50vw;
      height: 50vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      border-radius: 20px;
      background-color: rgb(189, 186, 186);
    }
    input {
      border-radius: 20px;
      width: 300px;
      height: 30px;
      border: none;
      padding: 5px 15px;
      margin: 15px 10px;
    }
    label {
      font-size: 20px;
    }
    h1{
      font-weight: 700;
    }
    button {
      border-radius: 20px;
      padding: 5px 15px;
      border: none;
      cursor: pointer;
    }
    button:hover{
      background-color: rgb(224, 221, 221);
    }
  </style>
</head>
<body>
<%
	Account a = (Account)request.getSession().getAttribute("account");
	if (a==null){
%>
  <form class="container" action="GotoMyHomeServlet"  method="POST">
    <h1>LOGIN</h1>
    <label for="username">User name:</label>
    <input name="username" type="text" id="username" placeholder="Enter user name"/>
    <label for="password">Password:</label>
    <input name="password" type="password" id="password" placeholder="Enter password" />
    <button type="submit">LOGIN</button> 
  </form>
<% } else { response.sendRedirect("MyHome.jsp"); } %>
</body>
</html>