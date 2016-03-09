function query(id) {
	var c = $('#' + id).text().trim();
	if(c === "Query") {
		// start the query for id thru websocket
	    var jsonstr = JSON.stringify({ 'item_id': id});
        stompClient.send("/app/query", {}, jsonstr);
	}
	else {
		// stop the query for id websocket
		var jsonstr = JSON.stringify({ 'item_id': id});
	    stompClient.send("/app/stop", {}, jsonstr);
	}
	if(c === "Query") {
		$('#' + id).text("Stop");
	}
	else {
		$('#' + id).text("Query");
	}
}
