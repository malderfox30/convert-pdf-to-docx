package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.BO.InsertHistoryBO;
import model.BO.UpdateHistoryBO;
import model.Bean.Account;
import model.Bean.History;
import model.DAO.GetHistoryDAO;
import com.spire.pdf.*;

@WebServlet("/UploadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 50, // 50MB
    maxFileSize = 1024 * 1024 * 50, // 50MB
    maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public UploadFileServlet() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  doPost(request, response);
	}

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	  Account user = (Account)request.getSession().getAttribute("account");
	  request.getSession().setAttribute("message", "");
	  
	  @SuppressWarnings("unchecked")
	  List<History> history = (List<History>) request.getSession().getAttribute("history");
	  
	  Collection<Part> parts = request.getParts();
	  System.out.print(parts.size());
	  if (parts.iterator().next().getSize() > 0) {
		  for (Part part : request.getParts()) {
			  System.out.print(part.getContentType());
			  if (!part.getContentType().equals("application/pdf")) {
				  request.getSession().setAttribute("message", "File format not supported!");
			  } 
			  else {
				String fileName = extractFileName(part);
				fileName = new File(fileName).getName();
				String fileId = UUID.randomUUID().toString(); // generate random file ID
				InputStream inputStream = part.getInputStream();
				InsertHistoryBO.insertHistory(fileId, user.getId(), fileName, "running");
				
				Thread handle = new Thread(new FileConverter(inputStream, fileId));
				handle.start();
				
				request.getSession().setAttribute("message", "Upload File Success!");
			  }
		  }
	  } 
	  else {
		  request.getSession().setAttribute("message", "There's no file here, please upload a file to continue!");
	  }
		request.getSession().setAttribute("account", user);
		history = GetHistoryDAO.getHistory(user.getId());
		request.getSession().setAttribute("history", history);
		response.sendRedirect("MyHome.jsp");
  }

  private String extractFileName(Part part) {
	  String contentDisp = part.getHeader("content-disposition");
	  String[] items = contentDisp.split(";");
	  for (String s : items) {
		  if (s.trim().startsWith("filename")) {
			  return s.substring(s.indexOf("=") + 2, s.length() - 1);
		  }
	  }
	  return "";
  }
  
  public static File getFolderUpload() {
	  File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
	  if (!folderUpload.exists()) {
		  folderUpload.mkdirs();
	  }
	  return folderUpload;
  }
}

class FileConverter implements Runnable {
	static int numThread = 1;
    static int threadAllowedToRun = 1;
    int myThreadID;
    private static Object myLock = new Object();
	
	PdfDocument pdfFile = new PdfDocument(); 
	String fileId; 
	
	public FileConverter(InputStream stream, String fileId) {
		this.myThreadID = numThread++;
		this.pdfFile.loadFromStream(stream);
		this.fileId = fileId;
		System.out.println("Thread ID:" + myThreadID);
	}
	
	@Override
	public void run() {
		synchronized (myLock) {
            while (myThreadID != threadAllowedToRun) {
                try {
                    myLock.wait();
                } catch (InterruptedException e) {
                	System.out.println(e.getMessage());
                } catch (Exception e) {
                	System.out.println(e.getMessage());
                }
            }
            System.out.println("myThreadID is running: " + myThreadID);
    		String path = UploadFileServlet.getFolderUpload().getAbsolutePath() + File.separator + this.fileId + ".docx";
    		this.pdfFile.saveToFile(path, FileFormat.DOCX);
    		UpdateHistoryBO.updateHistory(fileId, "success");
    		this.pdfFile.close();
            myLock.notifyAll();
            threadAllowedToRun++;
        }
	}
}
