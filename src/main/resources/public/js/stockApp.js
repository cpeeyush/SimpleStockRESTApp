$(document).ready(function() {
    var settings = {
      "async": true,
      "crossDomain": true,
      "url": "http://127.0.0.1:8080/api/stocks",
      "method": "GET"
    }

    $.ajax(settings).done(function (response) {
      drawTable(response);
    });

    function drawTable(data) {
        for (var i = 0; i < data.length; i++) {
            drawRow(data[i]);
        }
    }


    function drawRow(rowData) {
        var row = $("<tr />")
        $("#stockDataTable").append(row);
        row.append($("<td>" + rowData.id + "</td>"));
        row.append($("<td>" + rowData.name + "</td>"));
        row.append($("<td>" + rowData.currentPrice + "</td>"));
        row.append($("<td>" + rowData.lastUpdate + "</td>"));
    }
});
