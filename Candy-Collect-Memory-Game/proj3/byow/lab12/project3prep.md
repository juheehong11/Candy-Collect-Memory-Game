# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer: My implementation was split into the middle, top , and bottom, while the staff solution just did a top half and mirrored it. Their solution is a lot more 
efficient, and I think I could definitely simplify my code by implementing a similar technique in the future. 

-----

**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer: A hexagon could be a corridor or a room, and the tesselation would be connecting these modules together. 

-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer: I think I would start working on methods to make hallways and rooms based on certain dimensions, then methods that connect these. 

-----
**What distinguishes a hallway from a room? How are they similar?**

Answer: Hallways are longer than rooms and can be connected to make turns. They are both composed of an indoor section enclosed by walls (except where connections exist). 
