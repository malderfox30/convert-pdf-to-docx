package model.BO;

import model.DAO.UpdateHistoryDAO;

public class UpdateHistoryBO {
	public static void updateHistory(String id, String status) {
		UpdateHistoryDAO.updateHistory(id, status);
	}
}
