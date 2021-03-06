# Game Chat Introduction
The game chat system allows a player to send a message to different game locations or specific players. Originally, the game included the ability for a player to SAY a message to all players in the same area. Chat functionality has been expended to WHISPER a message to a player, which behaves similar to a private message. A player’s REPLY to a WHISPER has been streamlined to avoid typing the recipient’s name. Players can also SHOUT, sending a message to every player in the game.

Also added is the ability to IGNORE all messages from a specific player, regardless of how the message was sent. Individual player ignore lists can be displayed to screen. If desired, the player that owns the ignore list can remove a specific player. 
To assist players in location, or searching for the correct spelling for a player name, players have the ability to view all players in the game world with the aptly named LISTPLAYERS command. 


# Whisper

# Reply

# List Players in Game

# Ignore
## Usage
There are two ways to ignore messages from a player:
1. **IGNORE -A <player_name>**
2. **/I -A <player_name>**

## Command Implementation
Ignored and ignoring players are tracked on separate lists residing in the Player object. If p1 wants to ignore p2, p2 would execute command “IGNORE –A p2”. This would add p2’s name to p1’s ignoreList, and would add p1’s name to p2’s ignoredByList. 

To provide the ignore functionality these classes were edited: Player, GameCore, GameClient, GameCoreInterface, GameObject, GameObjectInterface. Of these, the bulk of the logic can be found in classes Player and GameCore

### Class: Player, File:Player.java
The following variables were added to track ignored and ignoring players:
```
private ArrayList<String> ignoreList;
private ArrayList<String> ignoredByList;
```

Adding names to the ignore lists is done through the following methods:
```
public void ignorePlayer(String name)
public void addIgnoredBy( String name)
```

The following methods are used to search the lists. If a player is found in a specific list, the corresponding function returns a true, otherwise false.
```
public boolean searchIgnoredBy(String name)
public boolean searchIgnoreList(String name)
```

### Class: GameCore, File:Player.java
The following changes were made to this class:
1. Function added
```
public  String  ignore (String  name , String  ignoreName )                                  
```
Updates ignore lists, after performing the following checks:  
a. Can't add self to ignore list 
b. Verifies that player being ignored exists 
c. Verifies player is not already in ignore list |

1. Function Changed
```
public  void  broadcast (Player player , String message  )
```
Does not broadcast to ignoring players  

3. Function Changed
```
public  void  broadcast (Player  sendingPlayer , Player  receivingPlayer , String  message )
```
Does not broadcast to ignoring players




# List Ignore Users

# Unignore

# Shout
