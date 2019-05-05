Edith Flores
Fly Away Home Game

Files Included:
html_doc
Bird.java 
bird
Creature.java
Fly.java
fly
FlyWorld.java
FlyWorldGUI.java
Frog.java
frog
GridLocation.java
Predator.java
Spider.java
spider
world0.txt
world1.txt

#Compile: javac FlyWorldGUI.java
#Run: java FlyWorldGUI <Filename of the World you wanna play in>

Fly Away Game is a simple Java game were the objective is for the user (fly) to use key arrows to get to the safe zone
(Red Square) without getting eaten by a predator. Each creature moves in different ways. 
-Fly can only vertically or horizontally.
-Frogs randomly move vertically or horizontally.
-Birds can "fly" to any unoccupied (No predator) location, it can eat fly if it lands on the same location as the fly.
-Spiders can move vertically or horizontally towards the fly if there is not predator occupying that spot in which case 
the spider randomly chooses which direction to move towards the fly (not occupied by a predator).
Spiders can only eat the fly if they are in the same location as fly.
