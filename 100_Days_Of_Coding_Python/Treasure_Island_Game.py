# In this Treasure Island Game, The User is given choices on how they are to walk through the game.
# Based on the decisions made the game will be over or will continue on until they find the gold.
while True:
    print("Welcome to Treasure Island. \nYour mission is to find the treasure.")
    direction = input("You're at a cross road. Where do you want to go?\n Type 'Left' or 'Right'\n")
    if direction.lower() == "left":
        print("You've come to a lake. There is an island in the middle of the lake.")
        direction = input("Type 'wait' to wait for a boat. Type 'swim' to swim across\n")
        if direction.lower() == "wait":
            print("You've arrived at the island unharmed. There is a house with 3 doors.")
            door = input("One red, one yellow, and one blue. Which color do you choose?\n")
            if door.lower() == "yellow":
                print("You found the treasure! You Win the Game!!!")
            elif door.lower() == "red":
                print("It's a room full of fire. Game Over!!!")
            else:
                print("You entered a room full of monsters. Game Over!!!")
        else:
            print("You get attacked by an angry trout. Game Over!!!")
    else:
        print("You've fell into a hole and broke your ankle. Game Over!!!")

    play_again = input("Do you want to play again? Type 'yes' to restart or 'no' to exit: ").lower()
    if play_again != "yes":
        print("Thanks for playing! Goodbye!")
        break
