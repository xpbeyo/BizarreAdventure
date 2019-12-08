Instructions:
Phase 2 of the CSC207 Term Project. No plugins and tools used, will work just open the project.
Our uml package which contains the uml diagrams of the project is under com.example.phase2 package.

UML: All UML diagrams are stored in the uml package.

Setup:
- Install Android Studio.
- Run, then check out project with version control using
https://markus.teach.cs.toronto.edu/git/csc207-2019-09/group_0537
- Set up the project through Gradle, using the default Gradle wrapper。
- Let the project build.
- Create an Android Virtual Device that uses Pixel3, with the device OS as API 28
- Run the file "LoginActivity.java", which will initiate the application and game menus.


Game information:
Information about the games and how they are played are listed below. This is a RPG game and have
three stages. The player should get through all three stages to win the game. The first two stages
is to find weapons and treasure box to help player add properties, in order to help to fight with
the monster in stage 3.

Stage1: Maze
Hero moves around to find the key hidden in treasure. Treasures can 
Increase hero's attack, luckiness, defence, flexibility and life. Treasures
Can also give hero the key and weapon. Hero need to avoid the monsters.
Each touch of strong monster will damage hero 5 lives and for weak monsters,
Damage hero 1 life per time.

Press the up/ down/ left/ right of the screen to move the hero 
up/ down/ left/ right. Monsters will move randomly.


Stage2: Treasure Hunt
A game that is inspired by the classic minesweeper. Clicking any boxes would allow you to expand it.
Empty squares would tell you the number of its neighbouring traps. A treasure would reward you with
some juicy stats. A trap however, would immobilize you and send you to the next stage.


Stage3: Battle
Player will need to choose different movement to fight with the monster. Monster will give messages
of its movement in order to give player hints.

Player need to click 'check' button to check monster's current message, and choose different
movement(attack, defence, and evade) to fight with the monster. Different movement will cause
different effect.

Attack: Make damage to the monster.
(Has possibility to evade from the damage of boss, or make a critical attack(double damage).)
Defence: Greatly reduce the damage received from the boss this round but cannot make damage.
(No chance of evade)
Evade: Increase the rate of evade this round but cannot make damage to the boss.




Functionality:
- The first page will be login page. Uses need to create their own account.
- For each account, the user can create different players with different careers and different
  initial weapons.
- Different careers and different weapons have different properties.
- The are 4 parameters for the property:
  1. Attack: The higher attack the player has, the more harm the player can create to the boss.
  2. Defence: Decrease the harm the boss create to the player.
  3. Flexibility: Increase the possibility to evade from the attack from the boss.
  4. Luckiness: Increase the possibility to make an critical attack(double damage) to boss.
- An user account can contain lots of players.
- After login, user can change the color theme in setting section.
- If the player quit game at a stage, after the user came back, it will continue the stage from the
  start. For example, if a player quit in stage 2, the player can resume the game from the beginning
  of stage 2.
- When the user choose to use which player to resume or start the game, the current stage and property
  of the player will show to user.
- After the player beat the boss, system will ask users if they want to keep the record on the
  scoreboard. The maximum number the scoreboard can show is 5.
- Scoreboard will contain the first five players among all uses.
- There are 2 ways to sort player in the scoreboard which the user can choose.
  1. Sorted by the life remaining.(Win the game with life left.)
  2. Sorted by the summation of properties.