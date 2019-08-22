$(function () {
    loadDist();
    loadStreet();
    loadType();
})

function loadType() {
    $.getJSON("../houseController/type", function (data) {
        for (var i = 0; i < data.length; i++) {
            var cal = "";
            var typeid = $("#typeid").val();
            ope = "<OPTION value='" + data[i].id + "' " + cal + ">";
            if (data[i].id == typeid) {
                var ope = "<OPTION value='" + data[i].id + "' selected>";
            }
            ope += data[i].name;
            ope += "</OPTION>";
            $("#type").append(ope);
        }
    })
}

function loadDist() {
    $.getJSON("../houseController/dist", function (data) {
        for (var i = 0; i < data.length; i++) {
            var ope = "<OPTION value='" + data[i].id + "'>";
            ope += data[i].name;
            ope += "</OPTION>";
            $("#dist").append(ope);
        }
    })
}

function findStreet() {
    $("#street option").remove();
    var id = $("#dist").val();
    $.getJSON("../houseController/street", "id=" + id, function (data) {
        for (var i = 0; i < data.length; i++) {
            var ope = "";
            if (id == 0) {
                ope = "<OPTION value='0'>请选择</OPTION>";
            } else {
                ope = "<OPTION value='" + data[i].id + "'>";
                ope += data[i].name;
                ope += "</OPTION>";
            }
            $("#street").append(ope);
        }
    })
}

function loadStreet() {
    $.getJSON("../houseController/street", "id=1", function (data) {
        for (var i = 0; i < data.length; i++) {
            var ope = "<OPTION value='" + data[i].id + "'>";
            ope += data[i].name;
            ope += "</OPTION>";
            $("#street").append(ope);
        }
    })
}