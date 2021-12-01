package controller;

import java.io.IOException;
import java.util.List;

// import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.CheckLoginBO;
import model.BO.GetHistoryBO;
import model.Bean.Account;
import model.Bean.History;


@WebServlet("/GotoMyHomeServlet")
public class GotoMyHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GotoMyHomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account a = CheckLoginBO.checkAccount(username, password);
		if (a==null) {
			response.sendRedirect("Login.jsp");
		} else {
			request.getSession().setAttribute("account", a);
			List<History> history = GetHistoryBO.getHistory(a.getId());
			request.getSession().setAttribute("history", history);
			response.sendRedirect("MyHome.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
