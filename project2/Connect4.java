package project2;

import java.util.Scanner;

public class Connect4 {
	static final int X =1, O=2;
	static final int NUM_COLUMNS = 4;
	static final int NUM_IN_ROW = 4;
	static Scanner input = new Scanner(System.in);
	static int firstplayer;
	static int p1=0,p2=0,ties=0;
	static long cnt=0;
	
	public static void main(String[] args) {

		for (int i=0; i<NUM_IN_ROW; i++) {
			int[ ][ ] list = new int[NUM_COLUMNS][NUM_COLUMNS];
			firstplayer = X;
			p1=0;p2=0;cnt=0;ties=0;
		    list[i][NUM_COLUMNS-1] = X;
			Play(list , O);
			System.out.println ("COLUMN " + (i + 1));
			System.out.println ("P1 Net Wins:" + (p1-p2));
			System.out.println("BLUE Wins:  "+ p1 +" RED Wins:" + p2);
			System.out.println ("total Recursion Calls: " + cnt);
		}
	}
	
	public static int Play(int[][] inlist, int clr) {
		cnt++;
		int res=checkBoard(inlist,clr);
		// 0 - board full, 1- X wins  2 = O wins   3-keep playing
		if (res < 3) { 
			if (res == 0) {ties++;return 0;
			} else {
				if (res == firstplayer) {p1++; return 1;} else {p2++; return -1;}
			}
		}	
		res = 0;

		// for each space that can be the next move
		//    make a copy of board (next lines)	

		//   update the board for this move
		for (int row = 0; row<NUM_COLUMNS; row++  ){
			for (int col = NUM_COLUMNS-1; col>=0; col--  ){
				if (inlist[row][col] == 0) {
					int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
					for (int x = 0;x <NUM_COLUMNS;x++  ){
						for (int y = 0; y<NUM_COLUMNS;y++  ){
							clonelist[x][y] = inlist[x][y] ;
						}
					}
					clonelist[row][col] = clr;
					Play(clonelist, 3- clr);
					break;
				}
			}
		}
		//  recursively call Play
		return res;
	}
	
	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	
	public static int checkBoard(int[][] inlist ,int clr){
		int chkclr = 3-clr;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
