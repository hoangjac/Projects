window.onload = init;

var name;
var font;
var holiday;
var colour;
var img = new Image();



function init() {
    document.getElementById("generate").onclick = drawCard;
}
;

function checkOptions() {
    if (document.getElementById("font1").checked) {
        font = document.getElementById("font1").value;
    } else {
        font = document.getElementById("font2").value;
    }

    var index = document.getElementById("holiday").selectedIndex;
    holiday = document.getElementsByTagName("option")[index].value;

    name = document.getElementById("name").value;

    colour = document.getElementById("colour").value;
}

function drawCard() {
    checkOptions();
    displayCard();
    requestAnimationFrame(displayCard);

}

function displayCard() {
    document.getElementById("wrapper").innerHTML = "";
    var c = document.getElementById("container");
    var ctx = c.getContext("2d");

    c.width = 320;
    c.height = 480;
    ctx.globalAlpha = 0;

    function fillText() {
        if (holiday === "Happy Thanksgiving") {
            ctx.fillStyle = colour;
            ctx.textAlign = "center";
            ctx.fillText(holiday, 160, 400);
            ctx.fillText(name + "!", 160, 430);
        } else if (holiday === "Merry Christmas") {
            ctx.fillStyle = colour;
            ctx.textAlign = "center";
            ctx.fillText(holiday, 203, 100);
            ctx.fillText(name + "!", 205, 130);
        } else if (holiday === "Happy New Year") {
            ctx.fillStyle = colour;
            ctx.textAlign = "center";
            ctx.fillText(holiday, 160, 30);
            ctx.fillText(name + "!", 160, 65);
        } else {
            ctx.fillStyle = colour;
            ctx.textAlign = "center";
            ctx.fillText(holiday, 160, 50);
            ctx.fillText(name + "!", 160, 85);
        }
    }

    img.onload = function () {

        (function transition() {
            ctx.globalAlpha += 0.003;
            ctx.clearRect(0, 0, c.width, c.height);
            ctx.drawImage(img, 0, 0);
            ctx.font = "30px " + font;
            fillText();


            if (ctx.globalAlpha < 1.0) {
                requestAnimationFrame(transition);
            }

        })();
    };
    checkImage();
}
;

function checkImage() {
    if (holiday === "Happy Birthday") {
        img.src = "images/birthday.jpg";
    } else if (holiday === "Merry Christmas") {
        img.src = "images/christmas.jpg";
    } else if (holiday === "Happy Easter") {
        img.src = "images/easter.jpg";
    } else if (holiday === "Happy Thanksgiving") {
        img.src = "images/thanksgiving.jpg";
    } else if (holiday === "Happy New Year") {
        img.src = "images/newyear.jpg";
    }

}
;

