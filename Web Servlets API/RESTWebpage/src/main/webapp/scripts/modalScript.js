// Get the modal
//var modal = document.getElementById("insertModal");

// Get the button that opens the modal
var btn = document.getElementById("insertButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span2 = document.getElementsByClassName("click")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
	getNextID();
	insertModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	insertModal.style.display = "none";
}
span2.onclick = function() {
	updateModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == insertModal) {
		insertModal.style.display = "none";
	}
	else if (event.target == updateModal) {
		updateModal.style.display = "none";
	}
}

cancelUpdate.onclick = function() {
	updateModal.style.display = "none";
}
cancelInsert.onclick = function() {
	insertModal.style.display = "none";
}

function showUpdateModal() {
	updateModal.style.display = "block";
}