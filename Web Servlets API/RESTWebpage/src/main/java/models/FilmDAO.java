package models;

import java.util.ArrayList;
import java.sql.*;

public class FilmDAO {

	Film film = null;

	Connection conn = null;
	Statement state = null;
	
	// endpoint creds , removed the endpoint acc because it wasnt working anymore for some reason

	private String endpoint = "";
	private String port = "6306";
	private String dbName = "cnx5";
	private String user = "benhadjj";
	private String password = "shorntEl6";
	
	private String url = "jdbc:mysql://" + endpoint + ":" + port + "/" + dbName;

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}

		catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			state = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film film = null;
		try {
			film = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException se) {
			System.out.println(se);
		}
		return film;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> listFilm = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs = state.executeQuery(selectSQL);

			// Retrieve the results

			while (rs.next()) {
				film = getNextFilm(rs);
				listFilm.add(film);
			}

			state.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return listFilm;
	}

	public Film getFilmByID(int id) {

		openConnection();
		film = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id = " + id;
			ResultSet rs = state.executeQuery(selectSQL);

			// Retrieve the results
			while (rs.next()) {
				film = getNextFilm(rs);
			}
			state.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return film;
	}

	public ArrayList<Film> getFilmByName(String name) {
		openConnection();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		try {
			String selectSQL = "select * from films where title like '%" + name + "%'";
			ResultSet rs = state.executeQuery(selectSQL);

			while (rs.next()) {
				film = getNextFilm(rs);
				listFilm.add(film);
			}

			state.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return listFilm;
	}

	public ArrayList<Film> getFilmByYear(int year) {
		openConnection();
		ArrayList<Film> listFilm = new ArrayList<Film>();

		try {
			String selectSQL = "select * from films where year = " + year;
			ResultSet rs = state.executeQuery(selectSQL);

			while (rs.next()) {
				film = getNextFilm(rs);
				listFilm.add(film);
			}

			state.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return listFilm;
	}

	public int insertFilm(Film film) {
		openConnection();

		try {
			String insertSQL = "insert into films values(?,?,?,?,?,?)";

			PreparedStatement prepState = conn.prepareStatement(insertSQL);

			prepState.setInt(1, film.getId());

			prepState.setString(2, film.getTitle());

			prepState.setInt(3, film.getYear());

			prepState.setString(4, film.getDirector());

			prepState.setString(5, film.getStars());

			prepState.setString(6, film.getReview());

			prepState.executeUpdate();

			prepState.close();

			closeConnection();

			return 1;
		} catch (SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

	public int deleteFilm(int id) {
		openConnection();

		try {
			String deleteSQL = "Delete from films where id = ?";

			PreparedStatement prepState = conn.prepareStatement(deleteSQL);

			prepState.setInt(1, id);

			prepState.executeUpdate();

			prepState.close();

			closeConnection();

			return 1;
		} catch (SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

	public int updateFilm(Film film) {
		openConnection();

		try {
			String updateSQL = "update films set title = ?, year = ?, director = ?, stars = ?, review = ? where id = ?";

			PreparedStatement prepState = conn.prepareStatement(updateSQL);

			prepState.setString(1, film.getTitle());
			prepState.setInt(2, film.getYear());
			prepState.setString(3, film.getDirector());
			prepState.setString(4, film.getStars());
			prepState.setString(5, film.getReview());
			prepState.setInt(6, film.getId());

			prepState.executeUpdate();

			prepState.close();
			closeConnection();
			return 1;
		} catch (SQLException se) {
			System.out.println(se);
			return 0;
		}
	}

	public int getNextID() {
		openConnection();

		try {
			String selectSQL = "select max(id) as maxID from films";
			PreparedStatement prepState;
			ResultSet rs;
			int nextID = -1;
			prepState = conn.prepareStatement(selectSQL);
			rs = prepState.executeQuery();
			if (rs.next()) {
				nextID = rs.getInt("maxID") + 1;
			}

			state.close();
			closeConnection();

			return nextID;
		} catch (SQLException se) {
			System.out.println(se);
			return -1;
		}
	}

	public FilmDAO() {
	}
}
