package model.BO;

import java.util.List;

import model.Bean.History;
import model.DAO.GetHistoryDAO;

public class GetHistoryBO {
	public static List<History> getHistory(String userId) {
		return GetHistoryDAO.getHistory(userId);
	}
}
