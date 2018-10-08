# Game Of Life
Simulation of cellular life using a 2D grid and four simple rules.
This application is created in Eclipse using Java language.

<img src="https://upload.wikimedia.org/wikipedia/commons/e/e5/Gospers_glider_gun.gif">

# Rules: Conway’s Game of Life
1 Any live cell with fewer than two live neighbours dies, as if caused by under-population.

2 Any live cell with two or three live neighbours lives on to the next generation.

3 Any live cell with more than three live neighbours dies, as if by overcrowding.

4 Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

# Concepts

The number of neighbours determines the state of a cell ON or OFF

Calculating the next state of a cell is the main driver of the Game of Life

Need to calculate the new state for each cell based on the current state

Cells can be neighbours if they are at the edge of the array (because of the wraparound property)

Gotcha is to mix up the previous and next states in your grid as the algorithm runs



