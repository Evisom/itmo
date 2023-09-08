<?php 
        $start = microtime(true); 
        date_default_timezone_set('Europe/Moscow');
        function tableRow($x,$y,$r,$status, $exec_time) { // Function returns HTML table row
                function cell($value) {
                        return "<td>".$value."</td>";
                }
                return "<tr>".cell(date('H:i:s')).cell($exec_time." ns").cell($x).cell($y).cell($r).cell($status)."</tr>";
        }

        class Point {
                protected int $x;
                protected int $y;
                protected int $r;
                public function __construct(string $x, string $y, string $r) {
                                $this->x = (int) $x;
                                $this->y = (int) $y;
                                $this->r = (int) $r;
                }

                public function inside() {
                        return $this->insideSquare() or $this->insideRectangle() or $this->insideCircle();
                }

                private function insideSquare() {
                        return (
                                ($this->x <= ($this->r)/2) and 
                                ($this->x >= 0) and 
                                ($this->y >= -1*($this->r)) and 
                                ($this->y <= 0)
                        );
                        
                }

                private function insideRectangle() {
                        return (
                                ($this->x + ($this->r)/2 >= $this->y) and 
                                ($this->y>=0) and 
                                ($this->x >= -1*($this->r)/2) and 
                                ($this->x <= 0)
                        );
                }

                private function insideCircle() {
                        return (
                                (pow($this->x, 2) + pow($this->y, 2) <= $this->r) and 
                                ($this->y <= 0) and 
                                ($this->x <= 0)
                        );
                } 
        }

        $input = json_decode(file_get_contents("php://input"), true); // parse json 
        $x = $input['x'];
        $y = $input['y'];
        $r = $input['r'];

        if (!(is_numeric($x) and is_numeric($y) and is_numeric($r))) { // Check request parameters
                http_response_code(400);       
        }

        if ($r < 0 or $r > 5 or $x < -5 or $x > 5 or $y < -5 or $y > 5) {
            http_response_code(400);
        }
    
        $point = new Point($x, $y, $r);

        if ($point->inside()) {
                $status = 'Inside';
        } else {
                $status = 'Outside';
        }

        $end = microtime(true); // execution time
        $exec_time = round(($end-$start) * 1000000, 2); // execution time in nanoseconds

        $response = tableRow($x, $y, $r, $status, $exec_time);
        echo $response
?>