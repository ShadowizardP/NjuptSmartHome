package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;


import bean.Temperature;
import dao.TemperatureDao;
import support.TimeController;

/**
 * Servlet implementation class SaveTemperature
 */
@WebServlet("/SaveTemperature")
public class SaveTemperature extends HttpServlet {
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("here");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//System.out.println("here?");
		request.setCharacterEncoding("UTF-8");
		
		Temperature temperature = new Temperature();
		try {
			String tem =request.getParameter("temperature");
			String oTime = request.getParameter("time");
			temperature.setTemperature(Double.parseDouble(tem));
			String time = oTime.substring(1, 5) + "-" + oTime.substring(5, 7) + "-" + oTime.substring(7, 9) + " " + oTime.substring(9, 11) + ":" + oTime.substring(11, 13) + ":" + oTime.substring(13, 15); 
			System.out.println(time);
			temperature.setDatetime(Timestamp.valueOf(time));
			temperature.setPeriod(TimeController.getPeriod(oTime));
			System.out.println(temperature.getPeriod());
			TemperatureDao tDao = new TemperatureDao();
			tDao.saveTemperature(temperature);

			response.sendRedirect("test.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

}
