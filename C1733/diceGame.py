"""This is a random dice game"""
from random import randint #import random int function
from time import sleep # import sleep function

# Get input from user
def get_user_guess():
	user_guess = int(raw_input("Guess a number:"))
	return user_guess

# Roll Dice 
def roll_dice(number_of_sides):
  first_roll=randint(1,number_of_sides)
  second_roll=randint(1,number_of_sides)
  max_val = number_of_sides * 2
  print "The maximum Value is: " + str(max_val)
  sleep(1)
  user_guess = get_user_guess()
  if user_guess > max_val:
    print"This is an invalid guess! Exiting..."
    return
  else:
    print "Rolling Dice Now..."
    sleep(2)
    print "The first value is: %d" %(first_roll)
    sleep(1)
    print "The second value is: %d" %(second_roll)
    sleep(1)
    total_roll =first_roll + second_roll
    print "The total is: %d" %(total_roll)
    print "Computing Result..."
    sleep(1)
    if user_guess > total_roll:
      print "You Won!"
      return
    else:
      print "You Loose. Try Again Please."
      return
    
roll_dice(6)