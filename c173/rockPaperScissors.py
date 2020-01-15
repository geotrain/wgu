"""This is the rock paper scissors game"""

from random import randint # Import random module
from time import sleep # Import time module

# Create contant variables for game in a list
options = ['R', 'P', 'S']
# Winner / Looser varialbe messages
lost = "You Lost!"
win = "You Won..Congratulations!"
# Function To Decide Winner
def decide_winner(user_choice, computer_choice):
  print "You selected %s" % user_choice
  print "Computer selecting..."
  sleep(1)
  print "The computer selected %s" % computer_choice
  user_choice_index = options.index(user_choice)
  computer_choice_index = options.index(computer_choice)
  # Dtermine who is the winner
  if user_choice_index == computer_choice_index:
    print "You Tied With The Computer"
    return
  elif user_choice_index == 0 and computer_choice == 2:
    print win
    return
  elif user_choice_index ==  1 and computer_choice == 0:
    print win
    return
  elif user_choice_index == 2 and computer_choice == 1:
    print win
    return
  elif user_choice_index > 2:
    print "You selected a letter other than R, P, or S"
    return
  else:
    print lost
    return
# Define a function to start computer game
def play_RPS():
  print "Rock, Paper, or Scissors?"
  print "Decide Carefully If You Dare..."
  user_choice = raw_input("Select R for Rock, Select P for Paper, or S for Scissors: ")
  user_choice = user_choice.upper()
  sleep(1)
  computer_choice = options[randint(0, len(options) - 1)]
  decide_winner(user_choice, computer_choice) # Determine winner

play_RPS() # Call Game Function
  
  