package servlets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.opencsv.CSVReader;

import beans.UserBean;
import utils.UrlUtil;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/csvManager")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UrlUtil urlUtil;

	private boolean isMultipart;
	private File file;

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("alert", "Please log in to upload your CSV file!");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/fileUpload.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("alert", "Please log in to upload your CSV file!");
			getServletContext().getRequestDispatcher("/").forward(request, response);
		} else {
			Integer userId = (user != null)?user.getUserId():null;
			isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				request.setAttribute("alert", "There is no file to upload!");
				getServletContext().getRequestDispatcher("/").forward(request, response);
			} else {
				String appPath = request.getServletContext().getRealPath("");
				// constructs path of the directory to save uploaded file
				String savePath = appPath + "tmp";

				// creates the save directory if it does not exists
				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}
				for (Part part : request.getParts()) {
					String fileName = extractFileName(part);
					file = new File(savePath + File.separator + fileName);
					System.out.println(savePath);
					part.write(file.getAbsolutePath());
				}
				CSVReader reader = null;
				String message="";
				try {
					reader = new CSVReader(new FileReader(file));
					String[] it;
					int lineCompt=0;
					while ((it = reader.readNext()) != null) {
						String[] res = null;
						lineCompt++;
//						Format pour le fichier CSV
//						personalUrlString, longUrlString, startDateString, expireDateString, urlPasswordString,	 captcha, nbClickString
						res = urlUtil.createUrl(it[0], it[1], it[2], it[3], it[4], it[5], it[6], userId);
						message = message + "Line - " + lineCompt + " : " + res[1] + "</br>";
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				request.setAttribute("alert", message);
				getServletContext().getRequestDispatcher("/").forward(request, response);
			}
		}
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

}
