function search() {
	var selectedGet = $("input:radio[name ='searchType']:checked").val();
	var searchParams = $("form#searchForm").serialize();

	console.log(searchParams);

	$("#response").empty();
	if (selectedGet == "getAll") {
		$.get("get_all_films", function(responseJson) {
			console.log(responseJson)
			createTable(responseJson, "#response")
		});
	}
	else if (selectedGet == "byName") {
		$.get("get_film_by_name", searchParams, function(responseJson) {
			console.log(responseJson)
			createTable(responseJson, "#response")
		});
	}
	else if (selectedGet == "byID") {
		$.get("get_film_by_id", searchParams, function(responseJson) {
			console.log(responseJson)
			createTable(responseJson, "#response")
		});
	}
	else if (selectedGet == "byYear") {
		$.get("get_film_by_year", searchParams, function(responseJson) {
			console.log(responseJson)
			createTable(responseJson, "#response")
		});
	}
}


function insertData() {
	var dataArray = $("form#insertForm").serializeArray();
	var dataJson = {};
	$.each(dataArray, function(index, data) {
		dataJson[data.name] = data.value;
	});

	$.post("insert_film", JSON.stringify(dataJson), null, "json");
}

function createTable(responseJson, div) {
	var tableStart = '<table class="table"><thead><tr><th scope="col">ID</th><th scope="col">Title</th><th scope="col">Year</th><th scope="col">Director</th><th scope="col">Stars</th><th scope="col">Review</th><th scope="col">Update/Delete</th></tr></thead><tbody>';
	var $table = $(tableStart).appendTo($(div));

	$.each(responseJson, function(index, film) {
		$("<tr>").appendTo($table)
			.append($('<th scope="row">').text(film.id))
			.append($("<td>").text(film.title))
			.append($("<td>").text(film.year))
			.append($("<td>").text(film.director))
			.append($("<td>").text(film.stars))
			.append($("<td>").text(film.review))
			.append($("<td>").append('<input type="button" value="Update" onclick="getUpdateData(this)">').append('<input class="fa fa-trash" type="button" value="Delete" onclick="deleteFilm(this)">'));
	});
}

function getUpdateData(element) {
	var $row = $(element).closest("tr");
	var $rowData = $row.find("th");

	var searchParams = "fileSearch=" + $rowData.text();

	console.log($row);
	console.log($rowData);
	console.log(searchParams)

	showUpdateModal();

	$.get("get_film_by_id", searchParams, function(responseJson) {
		$("#newFilmID").val(responseJson[0].id);
		$("#newFilmTitle").val(responseJson[0].title);
		$("#newFilmYear").val(responseJson[0].year);
		$("#newFilmDirector").val(responseJson[0].director);
		$("#newFilmStars").val(responseJson[0].stars);
		$("#newFilmReview").val(responseJson[0].review);
	});
}

function sendUpdateData() {
	var dataArray = $("form#updateForm").serializeArray();
	var dataJson = {};
	$.each(dataArray, function(index, data) {
		dataJson[data.name] = data.value;
	});

	$.post("update_film", JSON.stringify(dataJson), null, "json");
}

function deleteFilm(element) {
	var $row = $(element).closest("tr");
	var $id = $row.find("th").text();

	var dataJson = {};
	dataJson["id"] = $id;

	console.log($id);
	console.log(dataJson)

	$.post("delete_film", JSON.stringify(dataJson), null, "json");

	$row.remove();
}

function getNextID() {
	$.get("get_id", function(responseJson) {
		console.log(responseJson)
		$("#filmID").val(responseJson);
	})

}


