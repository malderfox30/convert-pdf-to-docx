package model.BO;

import model.DAO.InsertHistoryDAO;

public class InsertHistoryBO {
	public static void insertHistory(String id, String userId, String fileName, String status) {
		InsertHistoryDAO.insertHistory(id, userId, fileName, status);
	}
}
