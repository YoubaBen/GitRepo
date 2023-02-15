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

import models.Film;
import models.FilmDAO;

@WebServlet("/update_film")
public class updateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateFilm() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestData = request.getReader().lines().collect(Collectors.joining());

		FilmDAO filmDAO = new FilmDAO();

		JsonObject jsonData = new Gson().fromJson(requestData, JsonObject.class);

		int id = jsonData.get("id").getAsInt();
		String title = jsonData.get("title").getAsString();
		int year = jsonData.get("year").getAsInt();
		String director = jsonData.get("director").getAsString();
		String stars = jsonData.get("stars").getAsString();
		String review = jsonData.get("review").getAsString();

		Film film = new Film(id, title, year, director, stars, review);

		int yes = filmDAO.updateFilm(film);

		if (yes == 1) {
			System.out.println("The  movie  " + title + " has been updated ");
		} else {
			System.out.println("The  movie  " + title + " has failed to update ");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}