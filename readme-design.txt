Class

- Map

    It is the initialize of the game that will be the interface that tell where the available location.

- Player

    It is a class of player. No need to be extends

- MonsterFactory

    to only store the location of Monsters in map and generate the monsters

- Monster

    It is a class for particular monster that might be extends to each type of monster later

- ItemFactory

    to only store the location of items in map and generate the items

- Items

    It is the parent of Cloth/Weapon/Potion since these all class has the same method take/drop/iteminfo


- Weapon

    This one is for attackable item inherit from Items

- Potion

    This one is a potion item inherit from Items



