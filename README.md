# Puzzle-Project
Data structure search algorithm written in Java to solve a puzzle in the form of a grid of numbers.

Choices of puzzle size ranging from 3x3 to 5x5 are made available.  Based on choice, one of three different corresponding files is chosen, and its contents, in the form of numbers, are put into a 2-dimensional array as strings.

The puzzle itself is presented as a grid.  Each element of the 2-dimensional array takes up a grid spot, with the exception of the last grid spot which remains empty.
Numbers in the grid can be moved to an empty spot only.

The puzzle is solved by orienting the numbers from least to greatest.
Because the actual puzzle takes the form of a 2-dimensional array, checking if it's solved can be done through comparing it with another 2-dimensional array that does have its elements sorted from least to greatest.
Method that prints unsolved puzzles to the console as well as a menu function are both implemented as well.
