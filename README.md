# chess-tg

This project solves the problem of calculating possible moves for some chess pieces, given a piece and its current square.

### Building Blocks
* The basic things are files and ranks. Files are columns, and range from \A to \H. Ranks are rows and range from 1 to 8.
* There are 4 basic operations with files and ranks. With files you can go right or left. With ranks you can go top and bottom.
* Next, we have 8 basic movements for each directions: top, right, bottom, left, top-right, bottom-right, bottom-left, top-left.
* Next, we have 8 infinite movements for each directions (example, keep going to top or right).
* With these building blocks all moves can be represented.

The docs are here ./docs/uberdoc.html (out of sync, though).
