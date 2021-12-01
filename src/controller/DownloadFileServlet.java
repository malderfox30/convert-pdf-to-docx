package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 50, // 50MB
    maxFileSize = 1024 * 1024 * 50, // 50MB
    maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DownloadFileServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  doPost(request, response);
	}

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
	  String fileId = request.getParameter("fileId");
	  
	  response.setContentType("application/msword");
      response.setHeader("Content-Disposition","attachment;filename=" + fileId + ".docx");
	  
	  String path = UploadFileServlet.getFolderUpload().getAbsolutePath() + File.separator + fileId + ".docx";
	  File file = new File(path);
	  FileInputStream fileIn = new FileInputStream(file);
	  ServletOutputStream out = response.getOutputStream(); 

	  byte[] outputByte = new byte[(int)file.length()];
	  
	  while(fileIn.read(outputByte, 0, (int)file.length()) != -1) {
	  	out.write(outputByte, 0, (int)file.length());
	  }
	  fileIn.close();
	  out.flush();
	  out.close();
  	}
  }
  


