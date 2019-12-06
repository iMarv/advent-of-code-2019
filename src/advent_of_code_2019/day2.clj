(def vals [1 0 0 3 1 1 2 3 1 3 4 3 1 5 0 3 2 1 13 19 1 9 19 23 1 6 23 27 2 27 9 31 2 6 31 35 1 5 35 39 1 10 39 43 1 43 13 47 1 47 9 51 1 51 9 55 1 55 9 59 2 9 59 63 2 9 63 67 1 5 67 71 2 13 71 75 1 6 75 79 1 10 79 83 2 6 83 87 1 87 5 91 1 91 9 95 1 95 10 99 2 9 99 103 1 5 103 107 1 5 107 111 2 111 10 115 1 6 115 119 2 10 119 123 1 6 123 127 1 127 5 131 2 9 131 135 1 5 135 139 1 139 10 143 1 143 2 147 1 147 5 0 99 2 0 14 0])

(defn code-1 [c1 c2 pos vec]
  (assoc vec pos (+ c1 c2)))

(assert (= (code-1 1 2 1 [0 0]) [0 3]))

(defn code-2 [c1 c2 pos vec]
  (assoc vec pos (* c1 c2)))

(assert (= (code-2 1 2 1 [0 0]) [0 2]))

(defn val-at [vec pos]
  (get vec (get vec pos)))

(defn code-at [pos vec]
  (let
   [opcode (get vec pos)
    c1 (val-at vec (+ pos 1))
    c2 (val-at vec (+ pos 2))
    set-pos (get vec (+ pos 3))]
    (case opcode
      1 (code-at (+ pos 4) (code-1 c1 c2 set-pos vec))
      2 (code-at (+ pos 4) (code-2 c1 c2 set-pos vec))
      99 vec)))

(defn compute [opcodes]
  (code-at 0 opcodes))

(assert (= (compute [1 0 0 0 99])           [2 0 0 0 99]))
(assert (= (compute [2 3 0 3 99])           [2 3 0 6 99]))
(assert (= (compute [2 4 4 5 99 0])         [2 4 4 5 99 9801]))
(assert (= (compute [1 1 1 4 99 5 6 0 99])  [30 1 1 4 2 5 6 0 99]))

(defn day2-prep [vec]
  (assoc (assoc vec 1 12) 2 2))

(defn day2 []
  (get (compute (day2-prep vals)) 0))
