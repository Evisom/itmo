<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://unpkg.com/98.css" />
    <link rel="stylesheet" href="static/css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="static/js/script.js"></script>
</head>
<body>
    <jsp:include page="jsp/author.jsp"/>
    <jsp:include page="jsp/error.jsp"/>

    <div class="window">
        <div class="title-bar">
            <div class="title-bar-text">Лабораторная №2</div>
            <div class="title-bar-controls">
                <button id="info" aria-label="Help"></button>
                <button id="closeall" aria-label="Close"></button>
            </div>
        </div>
        <div class="window-body">
            <div class="controls">
                <canvas class="plane" id="plane" width="256" height="256">

                </canvas>
                <div class="form">
                    <p style="margin-bottom: 8px">Sample text</p>
                    <div class="field-row x">
                        <label for="x">X:</label>
                        <input maxlength="8" id="x" type="text" placeholder="from -3 to 3" />
                    </div>
                    <div class="field-row y">
                        <label for="x">Y:</label>
                        <button class="y-btn">-3</button>
                        <button class="y-btn">-2</button>
                        <button class="y-btn">-1</button>
                        <button class="y-btn">0</button>
                        <button class="y-btn">1</button>
                        <button class="y-btn">2</button>
                        <button class="y-btn">3</button>
                        <button class="y-btn">4</button>
                        <button class="y-btn">5</button>
                    </div>
                    <div class="field-row r">
                        <label for="x">R:</label>
                        <select id="r">
                            <option value="1">1</option>
                            <option value="1.5">1.5</option>
                            <option value="2">2</option>
                            <option value="2.5">2.5</option>
                            <option value="3">3</option>
                        </select>
                    </div>
                    <div class="field-row r">
                        <button type="submit" id="check">Check!</button>
                    </div>
                </div>
            </div>
            <div class="table">
                <table class="interactive">
                    <thead>
                    <tr>
                        <th>Time</th>
                        <th>Execution time</th>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody id="results">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
