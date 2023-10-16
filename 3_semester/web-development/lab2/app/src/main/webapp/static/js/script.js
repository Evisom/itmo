window.onload = function () {

    $.ajax({
        type:'post',
        url: './',
        success: (res) => {
            $("#results").html(res)
        }
    })

    let point = {
        x: null,
        y: null,
        r: null
    }

    $("#info").on("click", () =>{
        $("#author").css("display", "block")
    })

    $("#error-close").on("click", () =>{
        $("#error").css("display", "none")
    })
    $("#close").on("click", () =>{
        $("#author").css("display", "none")
    })

    $("#closeall").on("click", ()=>{
        $("body").css("display", "none")
    })

    $(document).on('click', '.y-btn',  function (e)  {
        console.log($(this).text())
        point.y = $(this).text()
    });
    $("#check").on('click', () => {
        point.x = $("#x").val()
        point.r = $("#r option:selected").val()
        console.log(point)
        if (point.x  != null & point.y != null & point.r != null) {
            $.ajax({
                type: "POST",
                url: "./", // Replace with the actual server endpoint URL
                data: point,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function (response) {
                    // Handle the response from the server
                    console.log(response);
                    $("#results").html(response)
                },
                error: function (xhr, status, error) {
                    // Handle errors
                    console.error("Error: " + error);
                    $("#error").css("display", "block")
                }
            });
        } else {
            $("#error").css("display", "block")
        }
    })
}