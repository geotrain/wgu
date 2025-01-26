# This script takes input from the user to pick rock paper or scissors and then outputs
# their choice to the screen and then the script randomly selects one, the script then weighs
# who is the winner (user or computer) and outputs that to the screen.
import random
while True:
    # Get user selection and set to typecast int
    user_selection = int(input("What do you choose? Type 0 for Rock, 1 for Paper, or 2 for Scissors: \n"))
    if user_selection != 0 or user_selection != 1 or user_selection != 2:
        print("Next time select 0, 1, or 2. Play again soon!")
        exit(0)
    if user_selection == 0:
        user_choice = "Rock"
    elif user_selection == 1:
        user_choice = "Paper"
    else:
        user_choice = "Scissors"
    print(f"You selected \"{user_choice}\" as your choice.")
    # Set list of options of the game and randomize pick an item from the list
    computer_selection = ["Rock", "Paper", "Scissors"]
    computer_choice = random.choice(computer_selection)
    print(f"The computer selected \"{computer_choice}\" as its choice.")
    # Go through the nine possibilities outcome of the choices selected
    if user_choice == "Rock" and computer_choice == "Rock":
        print("It is a draw!")
    elif user_choice == "Paper" and computer_choice == "Paper":
        print("It is a draw!")
    elif user_choice == "Scissors" and computer_choice == "Scissors":
        print("It is a draw!")
    elif user_choice == "Rock" and computer_choice == "Scissors":
        print("You won the game!")
    elif user_choice == "Paper" and computer_choice == "Rock":
        print("You won the game!")
    elif user_choice == "Scissors" and computer_choice == "Paper":
        print("It is a draw!")
    elif user_choice == "Rock" and computer_choice == "Paper":
        print("The computer won the game!")
    elif user_choice == "Paper" and computer_choice == "Scissors":
        print("The computer won the game!")
    elif user_choice == "Scissors" and computer_choice == "Rock":
        print("The computer won the game!")
    # Offer user to play again or exit
    play_again = input("Do you want to play again? Type 'yes' to restart or 'no' to exit: ").lower()
    if play_again != "yes":
        print("Thanks for playing! Goodbye!")
        break
