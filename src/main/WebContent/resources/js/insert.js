$(document).ready(function () {
    $("#onclick").click(function () {
        $("#screen").css("display", "block");
    });
    $("#send").click(function () {
        $("#screen").css("display", "none");
        $("#screen2").css("display", "block");
    });
    $("#send2").click(function () {
        var datetime = $("#datetime").val();
        if (datetime === ""){
            window.alert("Informe data e hora.");
        }
            else{
                $("#screen2").css("display", "none");
                $("#screen3").css("display", "block");
            }
    });
    $("#send3").click(function () {
        var number = $("#number").val();
        if (number === ""){
            window.alert("Informe um valor.");
        }
            else{
                $("#screen3").css("display", "none");
                $("#register").submit();
            }
    });
});