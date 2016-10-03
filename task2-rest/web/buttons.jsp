<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Buttons page</title>
    <script>
        var token;

        function getRequest(callback) {
            var req;
            try {
                req = new XMLHttpRequest();
                /* e.g. Firefox */
            } catch (err1) {
                try {
                    req = new ActiveXObject('Msxml2.XMLHTTP');
                    /* some versions IE */
                } catch (err2) {
                    try {
                        req = new ActiveXObject("Microsoft.XMLHTTP");
                        /* some versions IE */
                    } catch (err3) {
                        req = false;
                    }
                }
            }

            req.onreadystatechange = function () {
                if (req.readyState == 4) {
                    callback(req.status, req.responseText);
                }
            };
            return req;
        }

        function getToken() {
            if ( typeof token == 'undefined') {

                var data = {
                    code: "<%=request.getSession().getAttribute("accessToken")%>",
                    grant_type: "authorization_code",
                    client_id: "s1ut43hkud0w8xn",
                    client_secret: "fk5gv37lcwsec9m",
                    redirect_uri: "http://localhost:8080/index"
                };
                var dataString = "";
                for (var param in data) {
                    if (!data.hasOwnProperty(param)) {
                        continue;
                    }
                    dataString = dataString + "&" + param + "=" + data[param];
                }
                dataString = dataString.substring(1);

                var req = getRequest(function (status, rep) {
                    if (status == 200) {
                        var json = JSON.parse(rep);
                        token = json.access_token;
                        document.getElementById("messageWindow").textContent = "Token: " + token;
                    } else {
                        //Display error
                        document.getElementById("messageWindow").textContent = "ERROR : No Token";
                        console.log(rep);
                    }
                });

                req.open("POST", "https://api.dropboxapi.com/1/oauth2/token", true);
                req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                req.send(dataString);
            }
        }
        function getAccountInfo() {

            var req = getRequest(function (status, rep) {
                if (status == 200) {
                    var json = JSON.parse(rep);
                    console.log(json);

                    var createLine = function (name, value) {
                        var line = document.createElement("tr");

                        var column1 = document.createElement("th");
                        column1.textContent = name;
                        line.appendChild(column1);

                        var column2 = document.createElement("th");
                        column2.textContent = value;
                        line.appendChild(column2);
                        return line;
                    };
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
                    document.getElementById("messageWindow").textContent = "ERROR!";
                    console.log(json);
                }

            });

            req.open("GET", "https://api.dropboxapi.com/1/account/info");
            req.setRequestHeader('Authorization', 'Bearer ' + token);
            req.send();
        }
        function getFolderMetadata() {

            var req = getRequest(function (status, rep) {
                if (status == 200) {
                    var json = JSON.parse(rep);
                    console.log(json);

                    var createLine = function (name, value) {
                        var line = document.createElement("tr");

                        var column1 = document.createElement("th");
                        column1.textContent = name;
                        line.appendChild(column1);

                        var column2 = document.createElement("th");
                        column2.textContent = value;
                        line.appendChild(column2);
                        return line;
                    };
                    var cont = document.getElementById("accountInfoContainer");
                    cont.innerHTML = "";
                    for (var i in json.contents) {
                        cont.appendChild(createLine('Path', json.contents[i].path));
                        cont.appendChild(createLine('Size', json.contents[i].size));
                        cont.appendChild(createLine('Type', json.contents[i].mime_type));
                        cont.appendChild(createLine('_____', '_____'));
                    }
                    cont.style.visibility = 'visible';

                } else {
                    //Display error
                    document.getElementById("messageWindow").textContent = "ERROR!";
                    var json = JSON.parse(rep);
                    console.log(json);
                }

            });

            req.open("GET", "https://api.dropboxapi.com/1/metadata/auto/");
            req.setRequestHeader('Authorization', 'Bearer ' + token);
            req.send();
        }
        function chooseFile() {
            document.getElementById('file').click();
        }
        function uploadFile() {
            var fileInput = document.getElementById('file');

            var req = getRequest(function (status, rep) {
                if (status == 200) {
                    var json = JSON.parse(rep);
                    console.log(json);
                    document.getElementById("messageWindow").textContent = "File with name " + json.path +
                            " (" + json.size + ") successfully uploaded!";
                } else {
                    //Display error
                    document.getElementById("messageWindow").textContent = "ERROR!";
                    var json = JSON.parse(rep);
                    console.log(json);
                }
            });

            req.open("PUT", "https://content.dropboxapi.com/1/files_put/auto/" + fileInput.value, true);
            req.setRequestHeader('Content-type', '"multipart/form-data');
            req.setRequestHeader('Authorization', 'Bearer ' + token);
            req.send(fileInput.files[0]);

        }
        function search() {
            var query = document.getElementById('searchQuery').value;

            var req = getRequest(function (status, rep) {
                if (status == 200) {
                    var json = JSON.parse(rep);
                    console.log(json);

                    var createLine = function (name, value) {
                        var line = document.createElement("tr");

                        var column1 = document.createElement("th");
                        column1.textContent = name;
                        line.appendChild(column1);

                        var column2 = document.createElement("th");
                        column2.textContent = value;
                        line.appendChild(column2);
                        return line;
                    };
                    var cont = document.getElementById("accountInfoContainer");
                    cont.innerHTML = "";
                    for (var i in json) {
                        cont.appendChild(createLine('Path', json[i].path));
                        cont.appendChild(createLine('Size', json[i].size));
                        cont.appendChild(createLine('Type', json[i].mime_type));
                        cont.appendChild(createLine('Modified', json[i].modified));
                        cont.appendChild(createLine('_____', '_____'));
                    }
                    cont.style.visibility = 'visible';

                } else {
                    //Display error
                    document.getElementById("messageWindow").textContent = "ERROR!";
                    var json = JSON.parse(rep);
                    console.log(json);
                }

            });

            req.open("GET", "https://api.dropboxapi.com/1/search/auto/?query=" + query);
            req.setRequestHeader('Authorization', 'Bearer ' + token);
            req.send();
        }

    </script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <span id="messageWindow" class="messageBox">Click on "Get token" to retrieve the token</span>
    <table class="buttons">
        <tr><th>
            <button onclick="getToken()">Get Token</button>
        </th></tr>

        <tr><th>
            <button onclick="getAccountInfo()">Get Account Info</button>
        </th></tr>

        <tr><th>
            <form enctype="multipart/form-data" method="post" action="javascript:uploadFile();">
                <input type="button" id="get_file" value="Choose file" onclick="chooseFile()">
                <input type="file" name="file" id="file">
                <input type="submit" value="Upload">
            </form>
        </th></tr>

        <tr><th>
            <button onclick="getFolderMetadata()">Get root folder metadata</button>
        </th></tr>

        <tr><th>
            <input type="text" id="searchQuery">
            <button onclick="search()">Search files</button>
        </th></tr>
    </table>
<table style="visibility:hidden" id="accountInfoContainer">
</table>
</body>
</html>