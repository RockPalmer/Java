// Assignment #: 8
// Name: Rock Palmer
// StudentID: 1214784662
// Lecture: T Th 1:30-2:45
// Section: CSE 205
// Description: This class takes a single integer <N> as a parameter value and 
//		tries to find if N number of queens can be fit into an N-by-N chess board
//		without any of them being in the same row, column or diagonal. It does
//		so by placing possible placements into a stack of valid solutions and 
//		checking if this solution breaks the row, column, diagonal rule for any
//		of the other solutions. If so, it will try the next column over (provided
//		that that does not put it out of bounds) until it finds the correct 
//		placement. It does this row by row until it finds a solution.

import java.util.Stack;

public class NQueenSolver 
{  
	private Answer answer1;  
	private int boardSize; 
	
	//This is N in the assignment description.  
	
	//Constructor to initialize the boardSize, which is also the number of  
	//queens to be placed. Also create an answer object.  
	public NQueenSolver(int queenNum)   
	{     
		answer1 = new Answer(queenNum);     
		boardSize = queenNum; 
		
		//This is N in the assignment description.   
	}  
	
	//Return an answer -- accessor of the answer1  
	public Answer returnAnswer()   
	{     
		return answer1;   
	}  
	
	//The findSolution will return true if a solution is found,  
	//false otherwise. Please see the pseudo-code of the assignment 11 statement  
	public boolean findSolution()   
	{    
		Stack<Pair> stackSoln = new Stack<Pair>();    
		//back-up stack   
		Stack<Pair> stackSoln2 = new Stack<Pair>();    
		boolean success = false;    
		boolean conflict = false; 

		//Push information onto the stack indicating the first choice    
		//is a queen in row 0 and column 0.   
		stackSoln.push(new Pair(0,0)); 
		
		//Continue loop until either a solution is found or there are not possible solutions
		while (!success && !stackSoln.isEmpty())     
		{        
			System.out.println("Trying to place a queen in row " + stackSoln.peek().getRow() + " in column " + stackSoln.peek().getColumn());    
			
			//Set conflict to false every time so the program can check if this is so
			conflict = false;

			//Place choice to be checked into backup stack and store value of choice in variable temp
			Pair temp = stackSoln.pop();
			stackSoln2.push(temp);
			
			//Check whether the most recent choice (on top of the stack) is in the same row, same column,        
			//or same diagonal as any other choices in the stack.        
			//If so, there is a conflict; otherwise, there is no conflict.
			while (!stackSoln.isEmpty() && !conflict)
			{
				//Check if they are in the same row
				if (stackSoln.peek().getRow() == temp.getRow())
					conflict = true;
				//Check if they are in the same column
				else if (stackSoln.peek().getColumn() == temp.getColumn())
					conflict = true;
				//Check if they are in the same diagonal
				else if (stackSoln.peek().getRow() - stackSoln.peek().getColumn() == temp.getRow() - temp.getColumn()) 
					conflict = true;
				else if (stackSoln.peek().getColumn() + stackSoln.peek().getRow() == temp.getRow() + temp.getColumn())
					conflict = true;
				//Uncover the next solution in the stack to compare and check it
				else 
					stackSoln2.push(stackSoln.pop());
			} 
			
			// Refill the stack when done
			while(!stackSoln2.isEmpty()) 
				stackSoln.push(stackSoln2.pop());
			
			//If the proposed solution breaks the row, column, diagonal rule, increment the column
			//of the top solution (as long as that does not put it out of bounds)
			if (conflict == true)        
			{     
				//Pop items off the stack until the stack becomes empty or            
				//the top of the stack is a choice whose column is not N-1.           
				while (!stackSoln.isEmpty() && stackSoln.peek().getColumn() == boardSize - 1) 
					stackSoln.pop();
				
				//If the stack is now not empty after popping items,            
				//then increase the column number of the top choice by 1.            
				//That is, pop the top choice, and increase its column, then push it back.  
				if (!stackSoln.isEmpty()) 
				{
					Pair temp2 = stackSoln.pop();
					stackSoln.push(new Pair(temp2.getRow(), temp2.getColumn() + 1));
				}
			} 
			//If a solution is found and all Queens have been fit on the board, end the loop and
			//and print out the solution
			else if (conflict == false && stackSoln.size() == boardSize)         
			{    
				success = true; 
				
				//Since a solution is found, copy the stack info into the answer           
				//so that it can print them.           
				answer1.copySolution(stackSoln);   
			}        
			//no conflict, and it has not finished finding a solution yet    
			else      
			{        
				//The next choice is to place a queen at row number stackSoln.size()            
				//(one more than the current choice's row) and column number 0.            
				//So push (stackSoln.size(), 0) onto the stack    
				stackSoln.push(new Pair(stackSoln.size(), 0));
			} 
		} //end of while loop    
		return success;   
	}//end of findSolution method
}//end of the NQueenSolver class