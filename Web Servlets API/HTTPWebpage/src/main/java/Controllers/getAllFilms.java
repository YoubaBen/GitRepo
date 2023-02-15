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

@WebServlet("/get_all_films")

public class getAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// @see HttpServlet #HttpServlet()

	public getAllFilms() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		listFilm = filmDAO.getAllFilms();

		System.out.println(listFilm.size() + " Films found in the database");

		String json = new Gson().toJson(listFilm);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// response)

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
