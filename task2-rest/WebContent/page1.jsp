<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	var token;
	function getRequest(callback) {

		var req;

		try {
			req = new XMLHttpRequest(); /* e.g. Firefox */
		} catch (err1) {
			try {
				req = new ActiveXObject('Msxml2.XMLHTTP'); /* some versions IE */
			} catch (err2) {
				try {
					req = new ActiveXObject("Microsoft.XMLHTTP"); /* some versions IE */
				} catch (err3) {
					req = false;
				}
			}
		}

		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				callback(req.status, req.responseText);
			}
		}
		return req;
	}
	
	function getToken() {

		var data = {
				code:"<%=request.getAttribute("code")%>",
				grant_type:"authorization_code",
				client_id:"end2i90jbm33s3r",
				client_secret:"kjsz4j5lu9o4zpv",
				redirect_uri:"http://localhost:8080/task2-rest/index"
		};
		var dataString = "";
		for (var param in data) {
			if (!data.hasOwnProperty(param)) { continue; }
			dataString = dataString + "&" + param + "=" + data[param];
		}
		dataString = dataString.substring(1);
		
		var req = getRequest(function(status, rep) {
			if (status == 200) {
				var json = JSON.parse(rep);
				token = json.access_token;
				document.getElementById("token_display").textContent = token;
			} else {
				//Display error
				document.getElementById("token_display").textContent = "ERROR : No Token";
				console.log(rep);
			}
		});
		
		req.open("POST", "https://api.dropboxapi.com/1/oauth2/token", true);
		req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		req.send(dataString);
	};
	
	function getAccountInfo() {
		
		var req = getRequest(function(status, rep) {
			if (status == 200) {
				var json = JSON.parse(rep);
				console.log(json);
				
				var createLine = function(name,value) {
					var line = document.createElement("tr");
					
					var column1 = document.createElement("th");
					column1.textContent = name;
					line.appendChild(column1);
					
					var column2 = document.createElement("th");
					column2.textContent = value;
					line.appendChild(column2);
					return line;
				}
				var cont = document.getElementById("accountInfoContainer");
				cont.innerHTML = "";
				
				cont.appendChild(createLine('Name', json.display_name));
				cont.appendChild(createLine('Email', json.email));
				cont.appendChild(createLine('Country', json.country));
				cont.appendChild(createLine('UID', json.uid));
				cont.appendChild(createLine('Quota', parseInt(json.quota_info.quota) / 1000000000 + ' Gb'));
				
				cont.style.visibility = 'visible';
				
			} else {
				//Display error
				var json = JSON.parse(rep);
				console.log(json);
			}
			
		});

		req.open("GET", "https://api.dropboxapi.com/1/account/info");
		req.setRequestHeader('Authorization', 'Bearer ' + token);
		req.send();
	};
</script>
</head>
<body>
	<span><button onclick="getToken()">Get Token</button><span>Token:</span><span id="token_display">Click on "Get token" to retrieve the token</span></span>
	<br/>
	<button onclick="getAccountInfo()">Get Account Info</button>
	<table style="visibility:hidden" id="accountInfoContainer">
	</table>
</body>
</html>