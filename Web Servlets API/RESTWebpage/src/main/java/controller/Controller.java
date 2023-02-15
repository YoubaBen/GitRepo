package controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Film;
import models.FilmDAO;

@Path("/films")
public class Controller {
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Film[] getAllFilms() {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		listFilm = filmDAO.getAllFilms();
		Film[] arrayFilm = new Film[listFilm.size()];

		listFilm.toArray(arrayFilm);

		System.out.println(listFilm.size() + " films have been found in the database");
		return arrayFilm;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllFilmsString() {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		String stringFilms = "";
		listFilm = filmDAO.getAllFilms();

		for (Film film : listFilm) {
			stringFilms += film.toString() + "# + #";
		}

		System.out.println(listFilm.size() + " films have been found in the database");
		return stringFilms;
	}

	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Film[] getFilmByName(@PathParam("name") String name) {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();
		listFilm = filmDAO.getFilmByName(name);

		Film[] arrayFilm = new Film[listFilm.size()];

		listFilm.toArray(arrayFilm);

		System.out.println(listFilm.size() + " films have been found with the name: " + name);

		return arrayFilm;
	}

	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmByNameString(@PathParam("name") String name) {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();
		String stringFilms = "";

		listFilm = filmDAO.getFilmByName(name);

		for (Film film : listFilm) {
			stringFilms += film.toString() + "# + #";
		}

		System.out.println(listFilm.size() + " films have been found with the name: " + name);

		return stringFilms;
	}

	@GET
	@Path("film/{id: [0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Film[] getFilmByID(@PathParam("id") int id) {
		FilmDAO filmDAO = new FilmDAO();
		Film film = filmDAO.getFilmByID(id);

		ArrayList<Film> listFilm = new ArrayList<Film>();
		listFilm.add(film);

		Film[] arrayFilm = new Film[listFilm.size()];

		listFilm.toArray(arrayFilm);

		if (film == null) {
			System.out.println("Film with ID: " + id + " not found in the database in the database");
		} else {
			System.out.println("Film with ID: " + id + " found in the database");
		}

		return arrayFilm;
	}

	@GET
	@Path("film/{id: [0-9]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmByIDString(@PathParam("id") int id) {
		FilmDAO filmDAO = new FilmDAO();
		Film film = filmDAO.getFilmByID(id);

		if (film == null) {
			System.out.println("Film with ID: " + id + " not found  in the database");
		} else {
			System.out.println("Film with ID: " + id + "  found  in the database");
		}

		return film.toString();
	}

	@GET
	@Path("year/{year: [0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Film[] getFilmByYear(@PathParam("year") int year) {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();
		listFilm = filmDAO.getFilmByYear(year);

		Film[] arrayFilm = new Film[listFilm.size()];

		listFilm.toArray(arrayFilm);

		System.out.println(listFilm.size() + " films have been found in the year: " + year);

		return arrayFilm;
	}

	@GET
	@Path("year/{year: [0-9]*}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFilmByYearString(@PathParam("year") int year) {
		FilmDAO filmDAO = new FilmDAO();
		ArrayList<Film> listFilm = new ArrayList<Film>();
		String stringFilms = "";

		listFilm = filmDAO.getFilmByYear(year);

		for (Film film : listFilm) {
			stringFilms += film.toString() + "# + #";
		}

		System.out.println(listFilm.size() + " films have been found in  the year: " + year);

		return stringFilms;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNextID() {
		FilmDAO filmDAO = new FilmDAO();
		int nextID = filmDAO.getNextID();
		String text = "" + nextID;

		return text;
	}

	@POST
	@Path("film")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void insertFilm(Film film) {
		FilmDAO filmDAO = new FilmDAO();

		int success = filmDAO.insertFilm(film);

		if (success == 1) {
			System.out.println("Successfully added the film: " + film.getTitle());
		} else {
			System.out.println("Unable to add the film: " + film.getTitle());
		}
	}

	@PUT
	@Path("film")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateFilm(Film film) {
		FilmDAO filmDAO = new FilmDAO();

		int success = filmDAO.updateFilm(film);

		if (success == 1) {
			System.out.println("Successfully updated the film: " + film.getTitle());
		} else {
			System.out.println("Unable to update the film: " + film.getTitle());
		}
	}

	@DELETE
	@Path("film")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteFilm(String id) {
		FilmDAO filmDAO = new FilmDAO();

		int success = filmDAO.deleteFilm(Integer.parseInt(id));

		if (success == 1) {
			System.out.println("Film: " + id + "has been successfully deleted");
		} else {
			System.out.println("Film: " + id + " has failed to delete");
		}
	}

}