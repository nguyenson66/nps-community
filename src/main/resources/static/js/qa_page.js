function reply() {
	document.getElementById("commentInput").style.display = "flex";
}

$(document).ready(function() {
	
});

function quesVoteUp() {
	questionID = $("#qvoteup").val()
	url = "/api/questions/" + questionID + "/upvote",
	$.ajax({url, method: 'POST'})
	.done(function(responseBody) {
		score = responseBody.vote_up - responseBody.vote_down
		$("#qscore").text(score)
	})
}

function quesVoteDown(){
	questionID = $("#qvotedown").val()
	url = "/api/questions/" + questionID + "/downvote",
	$.ajax({url, method: 'POST'})
	.done(function(responseBody) {
		score = responseBody.vote_up - responseBody.vote_down
		$("#qscore").text(score)
	})
}

function ansVoteUp(answerID) {
	url = "/api/answers/" + answerID + "/upvote",
	$.ajax({url, method: 'POST'})
	.done(function(responseBody) {
		score = responseBody.vote_up - responseBody.vote_down
		$("#ascore" + answerID).text(score)
	})
}

function ansVoteDown(answerID){
	url = "/api/answers/" + answerID + "/downvote",
	$.ajax({url, method: 'POST'})
	.done(function(responseBody) {
		score = responseBody.vote_up - responseBody.vote_down
		$("#ascore" + answerID).text(score)
	})
}

