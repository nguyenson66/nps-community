function reply() {
	document.getElementById("commentInput").style.display = "flex";
}

$(document).ready(function () {
	$(".paging").click(function () {
		idx_page = $(this).text();
		url = "/api/questions?page=" + (idx_page - 1);
		$.ajax({ url, method: 'GET' })
			.done(function (responseBody) {
				$("tbody").empty();
				$.each(responseBody.content, function (index, question) {
					score = question.vote_up - question.vote_down
					var htmlrow = "<tr>"
						+ "<th scope='row'>" + (responseBody.number * 8 + index + 1) + "</th>"
						+ "<th><a class='nav-link ps-0' href='/questions/" + question.id + "'>" + question.title + "</a></th>"
						+ "<th>" + question.viewed + "</th>"
						+ "<th>" + score + "</th></tr>";
					$("#ques_table").append(htmlrow);
				});
				if (idx_page > 1)
					$("#f_page").removeClass("disabled");
				else
					$("#f_page").addClass("disabled");
				for (let i = 1; i <= responseBody.totalPages; i++) {
					if (i == idx_page)
						$("#page_" + i).addClass("active");
					else
						$("#page_" + i).removeClass("active")

				}
				if (idx_page < responseBody.totalPages)
					$("#l_page").removeClass("disabled");
				else
					$("#l_page").addClass("disabled");

			})
	});
});

function quesVoteUp() {
	questionID = $("#qvoteup").val()
	url = "/api/questions/" + questionID + "/upvote",
		$.ajax({ url, method: 'POST' })
			.done(function (responseBody) {
				score = responseBody.vote_up - responseBody.vote_down
				$("#qscore").text(score)
			})
}

function quesVoteDown() {
	questionID = $("#qvotedown").val()
	url = "/api/questions/" + questionID + "/downvote",
		$.ajax({ url, method: 'POST' })
			.done(function (responseBody) {
				score = responseBody.vote_up - responseBody.vote_down
				$("#qscore").text(score)
			})
}

function ansVoteUp(answerID) {
	url = "/api/answers/" + answerID + "/upvote",
		$.ajax({ url, method: 'POST' })
			.done(function (responseBody) {
				score = responseBody.vote_up - responseBody.vote_down
				$("#ascore" + answerID).text(score)
			})
}

function ansVoteDown(answerID) {
	url = "/api/answers/" + answerID + "/downvote",
		$.ajax({ url, method: 'POST' })
			.done(function (responseBody) {
				score = responseBody.vote_up - responseBody.vote_down
				$("#ascore" + answerID).text(score)
			})
}

