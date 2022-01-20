## 1. Classes and Data Structures
####(1) Dot
Instance Variables: 
* Position "location" — the (x,y) coordinate of the dot's location
* int "order" — the order of the dot; the order in which 
  the dot should be visited by the player
* Color "color" — colour of the dot 

####(2) Engine
Static Final Variables:
* static final File CWD — current working directory
* static final File SAVE — a txt file that saves the latest game 
* static final int WIDTH
* static final int HEIGHT

Instance Variables: 
* Player player
* TERenderer ter
* Render render
* int "od" — keeps track of the order of the dot to be visited next

####(3) Hallway
Instance Variables: 
* Position "location" — the (x,y) coordinate of the halllway's location, 
  keeps track of the bottom-left corner as starting point
* int "width" — width of hallway (horizontal length)
* int "height" — height of hallway (vertical length) 
* String "dir" — indicates the direction of the path created by the hallway; 
comes in "v" for vertical and "h" for horizontal
  
####(4) KeyboardSource
Instance Variables:
* TETile[][] "world"
* TERenderer "ter"
* TETile "avatar"

####(5) Partition
Instance Variables:
* boolean "hasRoom" — starts out false; depending on "rand", may be set to true. 
  Indicates if a partition would have a room in it.
* Position "p" — the starting point of the partition, gives coordinate (x,y) at the bottom left.
* int "rand" — a random integer that helps determine "hasRoom".

####(6) Player
Instance Variables:
* Position "location" — location of the player; constantly updated
* TERenderer "ter"
* TETile "avatar" — representation of player on the game map 
* TETile[][] worldFrame

####(7) Position
* int "x" — horizontal coordinate
* int "y" — vertical coordinate

####(8) Render
No instance variables

####(9) Room
Instance Variables:
* Position location — gives the bottom left coordinate as the room's starting point
* int width
* int height
* Position dotLocation — gives the coordinate of the dot placed randomly inside the room

####(10) StringSource


####(11) World 
Instance Variables:
* TETile[][] myWorld — TETile representation of world
* String seed — the seed for world
* ArrayList<Dot> dots — a list that keeps track of all dots created for the world, in order
* int order — used to indicate the order of the next dot that needs to be found 

## 2. Algorithms
#### World
Divides the world into 8 PARTITIONs of equal sizes.

Randomly iterates through all the PARTITIONs created, and createRoom() if it's determined that
this specific partition should have a room. createRoom() also creates a DOT 
(object to be picked up in the game) in a random position within the ROOM. 

Connects a ROOM created to one of the previous ROOMs already created (chosen randomly) by
creating a HALLWAY that connects them. 

#### Room
Creates ROOM objects, and also places the FLOOR and WALL tiles for the ROOM. 

Executes getRandomDotLocation() to place a randomly coloured dot (out of 8 choices)
into a random position within its FLOOR spaces.

Connects given self (a ROOM object) with another one of existing ROOMs via connect(), 
which creates HALLWAYs and gets rid of any tiles that mess up the pathway in WORLD map.
Divides the cases into when the two rooms are poisitoned in 2nd and 4th quadrants 
(in a Cartesian coordinate), or when they are positioned in 1st and 3rd quadrants. 
Sufficient cases are considered so that the order of the ROOMs being compared doesn't matter.

## 3. Persistence
**_What is Stored_**

During the game (in gameInProgress() from Engine.java), we keep track of all keys pressed 
and keep adding them onto the seed string given by "N", "S", and the numbers in between. 
Thus the keys pressed throughout a game is stored in a String "saveString".

**_How they are stored_**

Then when saveGame() (from Engine.java) is called, we store that string in the file SAVE 
using writeFile(). 

**_How they are retrieved_**

When the user presses "L" to load previous game, we use readFile()
to read from SAVE and use that string to replicate all the previous keys and movements to 
get to the latest state of the game when last quit (this is done in continueGame() from Engine.java).
