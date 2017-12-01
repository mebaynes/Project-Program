/**
*@author Mitch Baynes
*
* This program was made for Assignment 1 of PennX: SD1 - Software Development Fundamentals
*
* Purpose:
* This class can be used to simulate a game of whack a mole. Includes instance variables for the number of moles,
* the number of attempts the user has left, the user's score, and the actual grid that the moles appear on,
* which is a 2 dimensional array of chars. The character 'm' has been used to indicate a mole is at the location.
*
* The class includes a constructor with the size of the grid and the gridDimensions, as well as methods to
* place new moles (place()), attempt to remove moles from the board (whack()), print the grid to the user without 
* displaying the location of the moles (printGridtoUser()), and print the entire grid including the location
* of moles (printGrid())
*
* Instructions were to instantiate the game with 50 attempts to hit all the moles, on a 10 by 10 grid with 10 moles.
* Coordinates start from the top left corner at (0,0)
*/

import java.util.*;

public class WhackAMole{
 
    private int molesLeft = 0;
    private int attemptsLeft;
    private int score;
 	private char[][] moleGrid;
 	private static int gridDimension;

 	 
     public WhackAMole(int numAttempts, int dimensions){
	    attemptsLeft = numAttempts;
      	gridDimension = dimensions;
    	moleGrid = new char[gridDimension][gridDimension];
    }

 
 
    public boolean place(int x, int y){
    	if(x <= gridDimension && y <= gridDimension){
    		moleGrid[x][y] = 'M';
        	molesLeft += 1;
        	return true;
        }
     return false;
    }

 
 
 
    public void whack(int x, int y){
		if(moleGrid[x][y] == 'M'){
        	molesLeft -= 1;
         	score += 1;
         	System.out.println("You hit a mole!");
         	moleGrid[x][y] = 'W';
        }
     	else{
        	System.out.println("Miss");
         	moleGrid[x][y] = 'X';
        }
     	
     	attemptsLeft -= 1;
     	printGridToUser();
    }
 
 
 

    public void printGridToUser(){
     //Top line
    	for(int i = 0; i < gridDimension; i++){
        	System.out.print("_____");
        }
     	System.out.println("");
     //Boxes
     //go through array initializing grid row by row
     //If the box is uninitialized or has a mole in it, display as blank,
     //If there is a value in it, display the box with the value in it.
        for(int i = 0; i < gridDimension; i++){
	    	for(int j = 0; j < gridDimension ; j++){
            	if(moleGrid[j][i] == '\u0000' || moleGrid[j][i] == 'M' ){
					System.out.print("| * ");
            	}else{
                	System.out.print("| " + moleGrid[j][i] + " ");
                }
            }
        	System.out.println("");
        }
     //Bottom line
     	for(int i = 0; i < gridDimension; i++){
        	System.out.print("_____");
        }
     	System.out.println("");
    }
                   
                   
                   

    public void printGrid(){
		//Top line
    	for(int i = 0; i < gridDimension; i++){
        	System.out.print("_____");
        }
     	System.out.println("");
     //Boxes
     //go through array initializing grid row by row
     //If the box is uninitialized, display as blank,
     //If there is a value in it, display the box with the value in it.
        for(int i = 0; i < gridDimension; i++){
	    	for(int j = 0; j < gridDimension; j++){
            	if(moleGrid[j][i] == '\u0000' || moleGrid[j][i] == 'X'){
					System.out.print("| * ");
            	}else{
                	System.out.print("| " + moleGrid[j][i] + " ");
                }
            }
        	System.out.println("");
        }
     //Bottom line
     	for(int i = 0; i < gridDimension; i++){
        	System.out.print("_____");
        }
     	System.out.println("");
     	
     
    }
                   
 /*
 
 
 Additional methods independently added
 
 
 */
                   
 
 //Additional method, which randomly places the number of moles stated within the grid
 	public boolean placeMoles(int numOfMoles, int gridSize){
     
     	if(numOfMoles > gridSize*gridSize){
        	return false;
        }
    	//Randomly place the number of moles
     	for(int i = 0; i < numOfMoles; i++){
         //We do not add 1, because we want a number between 0 and 9, which is what our indices correspond to 
        	int x =(int)(Math.random() * gridSize);
         	int y = (int)(Math.random() * gridSize);
        	place(x,y);	
        }
     	
     	return true;
    }
 
 
 
 
 	public int getAttemptsLeft(){
    	return attemptsLeft;
    }
 
 	
 
 	public int getScore(){
    	return score;
    }
 
 
 
 	public int getMolesLeft(){
    	return molesLeft;
    }

 
 
 
    public static void main(String[] args){
     
    	Scanner in = new Scanner(System.in);
     	//50 attempts, 10x10 grid size
     	int attempts = 50;
     	int boardSize = 10;
     	int numOfMoles = 10;
     	WhackAMole game = new WhackAMole(attempts, boardSize);
     	game.placeMoles(numOfMoles, boardSize);
            
     	System.out.println("You have " + attempts + " attempts to get " + numOfMoles + " moles");
     	System.out.println("Moles were successfully placed randomly on the board");
        System.out.println("Enter (-1,-1) to end the game at any time");
     	game.printGridToUser();
     
     	while(game.getAttemptsLeft() > 0){
         
     		System.out.println("Enter an x coordinate");
         	int x = in.nextInt();
         	if(x > boardSize || x < -1){
            	System.out.print("The x value entered is out of bounds, enter a valid coordinate");
             	continue;
            }
         
         	System.out.println("Enter a y coordinate");
         	int y = in.nextInt();
         	if(y > boardSize || y < -1){
            	System.out.print("The y value entered is out of bounds, enter a valid coordinate");
             	continue;
            }
         
         	if(x == -1 && y == -1){
             	break;
            }
         
         	game.whack(x,y);
         
         	if(game.getMolesLeft() == 0){
             	break;
            }
         
        }
     
     	if(game.molesLeft == 0){
            System.out.println("You hit all the moles!");
            System.out.println("Score: " + game.getScore() );
         
         //Will print the full board to user if they reach the end of the game, or if they type (-1,-1) to quit
         }else{
         	System.out.println("You didn't hit all the moles.");
         	System.out.println("Your score was:" + game.getScore() );
         	System.out.println("Here were the locations of the ones you missed:");
         	game.printGrid();
        }
    }



}