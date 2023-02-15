package Controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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

@WebServlet("/get_film_by_id")

public class getFilmByID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getFilmByID() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDAO filmDAO = new FilmDAO();
		
		Film film = null;
		
		ArrayList<Film> listFilm = new ArrayList<Film>();
		
		int idSearch = Integer.parseInt(request.getParameter("fileSearch"));
		
		film = filmDAO.getFilmByID(idSearch);
		listFilm.add(film);
		
		String json = new Gson().toJson(listFilm);
		
		if(film == null)
		{
			System.out.println(idSearch + " film ID not found or not in the database");
			 String address = "/WEB-INF/results/unknown-customer.jsp";
		}
		else
		{
			System.out.println(idSearch + " ID  found in the database");
			
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}