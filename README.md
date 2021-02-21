## Overview
A Minecraft plugin that adds nuclear missiles to your Spigot server.

## Usage
RealRockets adds a collection of new mechanics to Minecraft. After collecting enough **Enriched Ore**, you can refine it into **Ore Chunks**, which can be crafted into deadly missile warheads. **Enriched Ore** is dropped when mining common stone blocks in the Overworld.

### Key Ingredients
**Biofuel**  
Craft Biofuel in a Biodiesel Reactor by combining **Sugar Cane, Potatoes, and Bone Meal**.

**Chunks**  
Create various ore Chunks in an **Ore Refinery** by combining **Enriched Ore** and **Biofuel**.

**Warheads**  
Missile warheads are made in a **Warhead Forge** by supplying 9 of any one kind of **Chunk**.

**Missiles**  
Missiles are made in the **Rocket Foundry**. You need a **Warhead**, a **Rocket Hull**, and **Biofuel** to fly. However, your missile won't know where to go without a **Targeting Computer**, so be sure to include one when you craft your missile! Targeting Computers enable you to change the missile's target before it has been primed. To do this, simply write down the **coordinates** in a **Book & Quill**, and use the **Rocket Foundry** to set the target.

### Crafting Recipes
**Ore Refinery**  
C = Cobblestone  
F = Furnace  
L = Lava Bucket  
C|F|C
---|---|---
C|L|C
 | | 

**Biodiesel Reactor**  
C = Cobblestone  
O = Composter  
L = Lava Bucket  
C|O|C
---|---|---
C|L|C
 | | 

**Warhead Forge**  
C = Cobblestone  
A = Anvil  
L = Lava Bucket  
C|A|C
---|---|---
C|L|C
 | | 

**Rocket Foundry**  
I = Iron Block  
D = Dispenser  
I|I|I
---|---|---
I|D|I
I|I|I

**Rocket Hull**  
I = Iron Block  
I|I|I
---|---|---
I||I
I|I|I

**Targeting Computer**  
R = Redstone Dust  
T = Redstone Torch  
D = Daylight Detector  
G = Gold Ingot  
R|R|R
---|---|---
T|D|T
G|G|G

### Commands
/rr  
/realrockets

### Permissions
realrockets.use - Use the command and get one of each RealRockets block  
realrockets.dropore - Allow the player to drop ore when mining

## Installation (Server)
To use the plugin, simply download a `.jar` binary from the [Releases](https://github.com/hwdotexe/RealRockets/releases) page for your server's version, and restart.

## Installation (Development)
If you'd like to install the codebase and make changes, simply follow these instructions:

### Step 1: Clone
In a terminal, use `git clone https://github.com/hwdotexe/RealRockets.git`

### Step 2: Import as a Maven Project
This plugin uses Maven as its build system. As such, you'll need to import it using Maven as well. This process varies by IDE, so please seek out their instructions if needed.

### Step 3: Building
When you're ready to compile the plugin locally, you can use `mvn clean package` to generate your `.jar` file. 

## Contributing
If you like this plugin and want to contribute, please do! I welcome any and all Pull Requests from eager developers who want to help out. 
