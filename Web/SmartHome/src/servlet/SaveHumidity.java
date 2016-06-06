package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;


import bean.Humidity;
import dao.HumidityDao;
import support.TimeController;

/**
 * Servlet implementation class SaveTemperature
 */
@WebServlet("/SaveTemperature")
public class SaveHumidity extends HttpServlet {
       
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		Humidity humidity = new Humidity();
		try {
			String hum =request.getParameter("humidity");
			String oTime = request.getParameter("time");
			humidity.setHumidity(Double.parseDouble(hum));
			String time = oTime.substring(1, 5) + "-" + oTime.substring(5, 7) + "-" + oTime.substring(7, 9) + " " + oTime.substring(9, 11) + ":" + oTime.substring(11, 13) + ":" + oTime.substring(13, 15); 
			System.out.println(time);
			humidity.setDatetime(Timestamp.valueOf(time));
			humidity.setPeriod(TimeController.getPeriod(oTime));
			System.out.println(humidity.getPeriod());
			HumidityDao hDao = new HumidityDao();
			hDao.saveHumidity(humidity);

			response.sendRedirect("test.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

}
