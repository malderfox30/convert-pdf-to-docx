package model.BO;

import model.Bean.Account;
import model.DAO.CheckLoginDAO;

public class CheckLoginBO {

	public static Account checkAccount(String username, String password) {
		return CheckLoginDAO.checkAccount(username, password);
	}

}
