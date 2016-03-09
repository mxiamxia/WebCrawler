var socket = new SockJS("/WebCrawler/ws");
var stompClient = Stomp.over(socket);

// Render price data from server into HTML, registered as callback
// when subscribing to price topic
function renderPrice(frame) {
	var prices = JSON.parse(frame.body);
	var item_id;
	if(prices.recommendation) {
		item_id = $('<td>').html(prices.item_id).css("color", "red");
	}
	else {
		item_id = $('<td>').html(prices.item_id);
	}
	$('#price').append(
			$('<tr>').append(
					$('<td>').html(item_id),
					$('<td>').html(prices.color),
					$('<td>').html(prices.original_price),
					$('<td>').html(prices.sale_price),
					$('<td>').html(timeConverter(prices.dtime))
			)
	);
}

// Callback function to be called when stomp client is connected to server
var connectCallback = function() {
	stompClient.subscribe('/topic/price', renderPrice);
}; 

// Callback function to be called when stomp client could not connect to server
var errorCallback = function(error) {
	alert(error.headers.message);
};

// Connect to server via websocket
stompClient.connect("guest", "guest", connectCallback, errorCallback);

function timeConverter(UNIX_timestamp){
	  var a = new Date(UNIX_timestamp);
	  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
	  var year = a.getFullYear();
	  var month = months[a.getMonth()];
	  var date = a.getDate();
	  var hour = a.getHours();
	  var min = a.getMinutes();
	  var sec = a.getSeconds();
	  var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
	  return time;
	}