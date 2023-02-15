function search() {
	var selectedGet = $("input:radio[name ='searchType']:checked").val();
	var searchParams = $("form#searchForm").serializeArray();
	var selectFormat = $("#format").val();
	var contentType;
	
	fileSearch = searchParams[0].value

	console.log(searchParams[0]);

	if (selectFormat == "text") {
		contentType = "text/plain";
	}
	else {
		contentType = "application/" + selectFormat;
	}
	$("#response").empty();
	
	if (selectedGet == "getAll") {
		$.ajax({
			type: 'GET',
			url: 'rest/films',
			dataType: selectFormat,
			contentType: contentType,
			success: function(response) {
				createTable(response, "#response", selectFormat);
			}
		});
	}
	else if (selectedGet == "byName") {
		$.ajax({
			type: 'GET',
			url: 'rest/films/' + fileSearch,
			dataType: selectFormat,
			contentType: contentType,
			success: function(response) {
				createTable(response, '#response', selectFormat);
			}
		});
	}
	else if (selectedGet == "byID") {
		$.ajax({
			type: 'GET',
			url: 'rest/films/film/' + fileSearch,
			dataType: selectFormat,
			contentType: contentType,
			success: function(response) {
				createTable(response, "#response", selectFormat);
			}
		});
	}
	else if (selectedGet == "byYear") {
		$.ajax({
			type: 'GET',
			url: 'rest/films/year/' + fileSearch,
			dataType: selectFormat,
			contentType: contentType,
			success: function(response) {
				createTable(response, "#response", selectFormat);
			}
		});
	}
}

function insertData() {
	var selectFormat = $("input:radio[name ='selectFormat']:checked").val()
	var dataFormat;
	var arrayData = $("form#insertForm").serializeArray();

	console.log(selectFormat);
	console.log(arrayData);

	if (selectFormat == 'json') {
		dataFormat = jsonFormat(arrayData);
	}

	else if (selectFormat == 'xml') {
		dataFormat = xmlFormat(arrayData);
	}

	$.ajax({
		type: 'POST',
		url: 'rest/films/film',
		data: dataFormat,
		dataType: selectFormat,
		contentType: "application/" + selectFormat
	});
}

function getUpdateData($rowId) {
	var $row = $(element).closest("tr");
	var $rowData = $row.find("th");

	var searchParams = "fileSearch =" + $rowData.text();

	console.log($row);
	console.log($rowData);
	console.log(searchParams);

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
	var arrayData = $("form#updateForm").serializeArray();
	var jsonData = {};

	console.log(arrayData);

	$.each(arrayData, function(index, data) {
		jsonData[data.name] = data.value;
	});

	$.post("update_film", JSON.stringify(jsonData), null, "json");
}

function jsonFormat(arrayData) {
	var jsonData = {};
	$.each(arrayData, function(index, data) {
		jsonData[data.name] = data.value;
	});

	return JSON.stringify(jsonData);
}

function xmlFormat(arrayData) {
	var xmlData = '<film>';
	$.each(arrayData, function(index, data) {
		xmlData = xmlData + '<' + data.name + '>' + data.value + '</' + data.name + '>';
	});

	xmlData = xmlData + '</film>';

	return xmlData;
}

function deleteFilm(element) {
	var $row = $(element).closest("tr");
	var $id = $row.find("th").text();

	console.log($row);
	console.log($id);

	$.ajax({
		type: 'DELETE',
		url: 'rest/films/film',
		data: $id,
		contentType: "application/json"
	});

	$row.remove();
}

function createTable(response, div, selectFormat) {
	var tableStart = '<table class="table"><thead><tr><th scope="col">ID</th><th scope="col">Title</th><th scope="col">Year</th><th scope="col">Director</th><th scope="col">Stars</th><th scope="col">Review</th><th scope="col">Update/Delete</th></tr></thead><tbody>';
	var $table = $(tableStart).appendTo($(div));

	console.log($table);

	if (selectFormat == 'json') {
		var formatResponse = Object.values(response);

		if (formatResponse[0].length === undefined) {
			tableJson(formatResponse, $table);
		}
		else {
			tableJson(response[0], $table);
		}
	}
	else if (selectFormat == 'xml') {
		tableXml(formatResponse, $table);
	}
	else {
		var formatResponse = [];

		response = response.split('# + #');

		while (response[0]) {
			formatResponse.push(response.splice(0, 6));
		}

		tableString(formatResponse, $table);
	}
}

function tableJson(formatResponse, $table) {
	$.each(formatResponse, function(index, film) {
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

function tableXml(xml, $table) {
	$(xml).find('film').each(function(index, element) {
		var film = $(element);
		$("<tr>").appendTo($table)
			.append($('<th scope="row">').text(film.find('id').text()))
			.append($("<td>").text(film.find('title').text()))
			.append($("<td>").text(film.find('year').text()))
			.append($("<td>").text(film.find('director').text()))
			.append($("<td>").text(film.find('stars').text()))
			.append($("<td>").text(film.find('review').text()))
			.append($("<td>").append('<input type="button" value="Update" onclick="getUpdateData(this)">').append('<input class="fa fa-trash" type="button" value="Delete" onclick="deleteFilm(this)">'));
	});
}

function tableString(array, $table) {
	$.each(array, function(index, film) {
		$("<tr>").appendTo($table)
			.append($('<th scope="row">').text(film[0]))
			.append($("<td>").text(film[1]))
			.append($("<td>").text(film[2]))
			.append($("<td>").text(film[3]))
			.append($("<td>").text(film[4]))
			.append($("<td>").text(film[5]))
			.append($("<td>").append('<input type="button" value="Update"  onclick="getUpdateData(this)">').append('<input class="fa fa-trash" type="button" value="Delete" onclick="deleteFilm(this)">'));
	});
}

function getNextID() {
	$.ajax({
		type: 'GET',
		url: 'rest/films/count',
		dataType: 'text',
		contentType: 'text/plain',
		success: function(response) {
			$("#filmID").val(response);
		}
	});
}