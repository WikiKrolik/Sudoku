# Component Programing labs
## _Group WW_FRI_1000_04_

## Exercise 2 - An algorithm for solving a sudoku game
 - _How to test values that are private?_ -- By testing the values obtained from a getter method
 - _What happens when you return a reference to a mutable private instance variable?_ -- This reference can be altered without checking, which can lead to errors
 - _Who can and who should modify a private instance variable?_ -- The class which contains this private instance variable
 - _Should a getter return a reference to the board instance variable? If you see a problem, solve it._ -- Getter should return a copy of the board variable
