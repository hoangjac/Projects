var player = true;
var input = 0;
var hasWinner = false;
var cell = ["", "", "", "", "", "", "", ""];
var x;
window.onload = init;

function init() {

    document.getElementById("reset").onclick = reset;

    document.getElementById("11").onclick = function () {
        cell_1Click("11");
    };
    document.getElementById("12").onclick = function () {
        cell_1Click("12");
    };
    document.getElementById("13").onclick = function () {
        cell_1Click("13");
    };
    document.getElementById("21").onclick = function () {
        cell_1Click("21");
    };
    document.getElementById("22").onclick = function () {
        cell_1Click("22");
    };
    document.getElementById("23").onclick = function () {
        cell_1Click("23");
    };
    document.getElementById("31").onclick = function () {
        cell_1Click("31");
    };
    document.getElementById("32").onclick = function () {
        cell_1Click("32");
    };
    document.getElementById("33").onclick = function () {
        cell_1Click("33");
    };


}
function cell_1Click(id) {
    validatePosition(id);
}

function validatePosition(id) {
    if (hasWinner) {
        input === 9;

    } else {
        if (input < 9) {
            var a = document.getElementById(id).innerHTML;
            if (a !== "")
            {
                document.getElementById("d2").innerHTML = "Cell is not available";
                return;
            }
            if (player) {
                document.getElementById(id).innerHTML = "X";
                player = false;
                document.getElementById("d2").innerHTML = "Player O Go!";
            } else {
                document.getElementById(id).innerHTML = "O";
                player = true;
                document.getElementById("d2").innerHTML = "Player X Go!";
            }

            input++;
            checkWinner();
            if (player) {
                x = "0"
            } else
            {
                x = "X"
            }
            if (hasWinner) {
                document.getElementById("d2").innerHTML = "Congrats, Player " + x
                        + " Won!";
                input = 9;
            } else if (input === 9 && hasWinner === false)
                document.getElementById("d2").innerHTML = "Tie!";
        }
    }
}

function checkWinner() {
    checkRows();
    checkColumns();
    checkDiagonals();
}

function reset() {
    player = true;
    input = 0;
    hasWinner = false;
    cell = ["", "", "", "", "", "", "", ""];
    var elements = document.getElementsByTagName("td");
    document.getElementById("d2").innerHTML = "Player X Go!"
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.color = "black";
        elements[i].innerHTML = "";
    }
}

function updateValues() {
    cell[0] = document.getElementById("11").innerHTML;
    cell[1] = document.getElementById("12").innerHTML;
    cell[2] = document.getElementById("13").innerHTML;
    cell[3] = document.getElementById("21").innerHTML;
    cell[4] = document.getElementById("22").innerHTML;
    cell[5] = document.getElementById("23").innerHTML;
    cell[6] = document.getElementById("31").innerHTML;
    cell[7] = document.getElementById("32").innerHTML;
    cell[8] = document.getElementById("33").innerHTML;
}

function checkColumns() {
    updateValues();

    if (cell[0] === cell[3] && cell[3] === cell[6] && cell[6] !== "") {
        hasWinner = true;
        document.getElementById("11").style.color = "red";
        document.getElementById("21").style.color = "red";
        document.getElementById("31").style.color = "red";
    } else if (cell[1] === cell[4] && cell[4] === cell[7] && cell[7] !== "") {
        hasWinner = true;
        document.getElementById("12").style.color = "red";
        document.getElementById("22").style.color = "red";
        document.getElementById("32").style.color = "red";
    } else if (cell[2] === cell[5] && cell[5] === cell[8] && cell[8] !== "") {
        hasWinner = true;
        document.getElementById("13").style.color = "red";
        document.getElementById("23").style.color = "red";
        document.getElementById("33").style.color = "red";
    }
}

function checkRows() {
    updateValues();
    if (cell[0] === cell[1] && cell[1] === cell[2] && cell[2] !== "") {
        hasWinner = true;
        document.getElementById("11").style.color = "red";
        document.getElementById("12").style.color = "red";
        document.getElementById("13").style.color = "red";
    } else if (cell[3] === cell[4] && cell[4] === cell[5] && cell[5] !== "") {
        hasWinner = true;
        document.getElementById("21").style.color = "red";
        document.getElementById("22").style.color = "red";
        document.getElementById("23").style.color = "red";
    } else if (cell[6] === cell[7] && cell[7] === cell[8] && cell[8] !== "") {
        hasWinner = true;
        document.getElementById("31").style.color = "red";
        document.getElementById("32").style.color = "red";
        document.getElementById("33").style.color = "red";
    }
}

function checkDiagonals() {
    updateValues();
    if (cell[0] === cell[4] && cell[4] === cell[8] && cell[8] !== "") {
        hasWinner = true;
        document.getElementById("11").style.color = "red";
        document.getElementById("22").style.color = "red";
        document.getElementById("33").style.color = "red";
    } else if (cell[2] === cell[4] && cell[4] === cell[6] && cell[6] !== "") {
        hasWinner = true;
        document.getElementById("13").style.color = "red";
        document.getElementById("22").style.color = "red";
        document.getElementById("31").style.color = "red";
    }
}