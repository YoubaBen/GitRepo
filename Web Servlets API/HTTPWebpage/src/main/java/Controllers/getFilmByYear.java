package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Film;
import models.FilmDAO;

// Servlet Implementation

@WebServlet("/get_film_by_year")

public class getFilmByYear extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getFilmByYear() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDAO filmDAO = new FilmDAO();

		ArrayList<Film> listFilm = new ArrayList<Film>();

		int searchYear = Integer.parseInt(request.getParameter("fileSearch"));

		listFilm = filmDAO.getFilmByYear(searchYear);

		String json = new Gson().toJson(listFilm);

		if (listFilm.isEmpty()) {
			System.out.println("Can not find a film in the year " + searchYear);
		} else {
			System.out.println(listFilm.size() + " films found with the year " + searchYear);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	// @see HttpServlet #doPost(HttpServletRequest request, HttpServletResponse
	// response)

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}