package Controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import models.FilmDAO;

// Implementing servlets 
@WebServlet("/delete_film")

public class deleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteFilm() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestData = request.getReader().lines().collect(Collectors.joining());
		FilmDAO filmDAO = new FilmDAO();
		JsonObject jsonData = new Gson().fromJson(requestData, JsonObject.class);
		
		int id = jsonData.get("id").getAsInt();
		int yes = filmDAO.deleteFilm(id);
		
		if(yes == 1)
		{
			System.out.println("The film " + id +  " has been deleted successfully from the database");
		}
		else
		{
			System.out.println("The Film " + id + " has not been deleted");
		}
	}
}