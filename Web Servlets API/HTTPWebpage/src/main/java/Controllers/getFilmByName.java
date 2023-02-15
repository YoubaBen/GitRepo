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

@WebServlet("/get_film_by_name")

public class getFilmByName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// @see HttpServlet

	public getFilmByName() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		String searchName = request.getParameter("fileSearch");

		listFilm = filmDAO.getFilmByName(searchName);

		String json = new Gson().toJson(listFilm);

		System.out.println("Searching for films including the name: " + searchName);
		System.out.println(listFilm.size() + " films have been found");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}