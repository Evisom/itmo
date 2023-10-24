window.onload = function () {

    const error = (msg) => {
        $("#error").css("display", "block")
        $("#error-msg").html(msg)
    }

    let point = {
        x: null,
        y: null,
        r: null
    }
    point.r = $("#r option:selected").val()
    $('#x').on('input propertychange', ()=>{
        $(".field-row.x").removeClass("error-outline")
    })
    $("#r").change(function () {
        drawPlane()
        point.r = $("#r option:selected").val()
        $(".field-row.r").removeClass("error-outline")
    });

    $("#info").on("click", () => {
        $("#author").css("display", "block")
    })

    $("#error-close").on("click", () => {
        $("#error").css("display", "none")
    })
    $("#close").on("click", () => {
        $("#author").css("display", "none")
    })

    $("#closeall").on("click", () => {
        $("body").css("display", "none")
    })

    $(document).on('click', '.y-btn', function (e) {
        console.log($(this).text())
        point.y = $(this).text()
        $(".field-row.y").removeClass("error-outline")
    });
    $("#check").on('click', () => {
        point.x = $("#x").val()
        point.r = $("#r option:selected").val()
        console.log(point)
        if (point.x != null && point.y != null && point.r != null && -3 <= point.x && 3>= point.x && point.x !== "" && $.isNumeric(point.x)) {
            req(point)
        }else if (point.x === "" || point.x === null){
            error("X is not set!")
            $(".field-row.x").addClass("error-outline")
        } else if (point.y === null) {
            error("Y is not set!")
            $(".field-row.y").addClass("error-outline")
        } else if (point.r === null) {
            error("R is not set!")
            $(".field-row.r").addClass("error-outline")
        } else if (!$.isNumeric(point.x)){
            error("X is not valid!")
            $(".field-row.x").addClass("error-outline")
        } else if (!(-3 <= point.x && point.x <= 3 )){
            error("X is not valid!")
            $(".field-row.x").addClass("error-outline")
        } else{
            console.log("undefined error!")
            $("#error").css("display", "block")
        }
    })
    const req = (data) => {

            $.ajax({
                type: "POST",
                url: "./", // Replace with the actual server endpoint URL
                data: data,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function (response) {
                    // Handle the response from the server
                    console.log(response);
                    $("#results").html(response)
                },
                error: function (xhr, status, error) {
                    // Handle errors
                    console.error("Error: " + error);
                    error("Server error!")
                }
            });

    }


    const canvas = document.getElementById("plane");
    const ctx = canvas.getContext("2d");

    const colors = [
        "#F4CCCC",
        "#FFE1CC",
        "#FFD9B3",
        "#FFF1CC",
        "#FFFFCC",
        "#E1FFCC",
        "#D9FFC4",
        "#CCFFE1",
        "#B3FFE1",
        "#CCFFF1",
        "#CCE1FF",
        "#D9CCFF",
        "#E1CCFF",
        "#FFE1FF",
        "#FFCCE1",
        "#FFD9CC",
        "#F9CCB3",
        "#FFF5CC",
        "#F0FFCC",
        "#CCFFD9",
        "#CCFFF9",
        "#CCF9FF",
        "#D9F9FF",
        "#F5CCFF",
        "#FFCCF9",
        "#FFD9F9",
        "#FFCCF0",
        "#FFF9CC",
        "#E1FFCC",
        "#CCFFCC",
        "#CCFFD4",
        "#CCFFE9",
        "#CCF4FF",
        "#CCD4FF",
        "#D1CCFF",
        "#E9CCFF",
        "#F4CCFF",
        "#FFCCE9",
        "#FFE9CC",
        "#FFCCC1",
        "#FFF3CC",
        "#FFF9E5",
        "#FFE5F9",
        "#F9CCE5",
        "#FFCCE1",
        "#E5F9FF",
        "#F9E5FF",
        "#E9E5CC",
        "#E1CCB9",
        "#FFF5E5",
        "#FFE9D1",
        "#FFD1E9",
        "#D1E5FF",
        "#F5E5FF",
        "#F5FFE5",
        "#E5FFF5",
        "#E9FFE5",
        "#FFF5FF",
        "#F5FFFB",
        "#FFE5D1",
        "#E5D1B3",
        "#FFE5D4",
        "#FFE5CC",
        "#FFE5E1",
        "#E1FFE5",
        "#D4FFE5",
        "#E5E1FF",
        "#E5D4FF",
        "#FFE1F5",
        "#FFE1E5",
        "#FFD4E5",
        "#E5CCB3",
        "#FFE5E9",
        "#E5E9FF",
        "#E9E5FF",
        "#F5E5E1",
        "#FFE1CC",
        "#FFD4C4",
        "#FFE1D9",
        "#FFE1F5",
        "#FFE1E9",
        "#FFD9E1",
        "#F5E1E9",
        "#FFE9E1",
        "#E9E1D9",
        "#E9D9E1",
        "#D9E1E9",
        "#E1E9D9",
        "#E1D9E1",
        "#E9D9CC",
        "#E9E1CC",
        "#E9CCB3",
        "#E9D9B3",
        "#E9CCD4",
        "#E9CCF9",
        "#D4F9CC",
        "#CCE9B3",
        "#CCD9B3",
        "#CCB3B3",
        "#CCD4CC"
    ];

    const rect = (r) => {

        ctx.beginPath()
        ctx.fillRect(canvas.width / 2, canvas.height / 2, -r, -r);
    }

    const arc = (r) => {

        ctx.beginPath();
        ctx.moveTo(canvas.width / 2, canvas.height / 2); // Starting point at the center of the canvas
        ctx.arc(canvas.width / 2, canvas.height / 2, r, 1.5 * Math.PI, 0, false); // Draw the quarter circle
        ctx.fill();
    }

    const triangle = (r) => {
        ctx.beginPath()
        ctx.beginPath();
        ctx.moveTo(canvas.width / 2, canvas.height / 2); // Starting point
        ctx.lineTo(canvas.width / 2 - (r / 2), canvas.height / 2); // Second point
        ctx.lineTo(canvas.width / 2, canvas.height / 2 - (-r / 2)); // Third point
        ctx.closePath(); // Close the path to complete the triangle
        ctx.fill();
    }

    const axis = (r) => {
        ctx.lineWidth = 2;
        // Draw x-axis
        ctx.beginPath();
        ctx.moveTo(0, canvas.height / 2);
        ctx.lineTo(canvas.width, canvas.height / 2);
        ctx.stroke();

        // Draw y-axis
        ctx.beginPath();
        ctx.moveTo(canvas.width / 2, 0);
        ctx.lineTo(canvas.width / 2, canvas.height);
        ctx.stroke();

    }

    function drawPlane() {

        ctx.clearRect(0, 0, canvas.width, canvas.height);

        const r = canvas.width / 3
        ctx.fillStyle = "#818181";

        rect(r)
        arc(r)
        triangle(r)
        axis()


    }


    var click = 0

// Function to get coordinates on click
    function getCoordinates(event) {
            if (click >= colors.length) {
                click = 0
            }

            const rect = canvas.getBoundingClientRect();
            const x = event.clientX - rect.left - canvas.width / 2;
            const y = canvas.height / 2 - (event.clientY - rect.top);

            console.log(x, y)
            ctx.beginPath()
            ctx.arc(event.clientX-rect.left, event.clientY-rect.top, 2, 0, 2 * Math.PI)
            ctx.fillStyle = colors[click]
            ctx.fill()
            click++

            let ratio = point.r / (canvas.width / 3)
            console.log(point.r, canvas.width / 3, ratio)
            req({
                x: (x*ratio).toFixed(2),
                y: (y*ratio).toFixed(2),
                r: point.r
            })

    }

    canvas.addEventListener("click", getCoordinates);
    drawPlane();
    req()
}