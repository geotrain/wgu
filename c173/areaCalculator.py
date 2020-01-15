"""
This program is an area calculator. It will calculate the area of a 
given shape once the dimensions are inputed by the user for either a Circle of a Triangle
"""
# Import python code  
from math import pi # This import the value of pi 3.14
from time import sleep # This import simulates a thinking calculator
from datetime import datetime # This imports the current date and time

now = datetime.now() # Stores datetime() into variable now

print "Welcome, loading the calculator program.........."
print "Current Time: %s/%s/%s %s:%s" % (now.month, now.day, now.year, now.hour, now.minute) # This prings the current MM/DD/YYYY HH:MM

sleep(1) # Make the calculator sleep for 1 second

hint = "Dont forget to include the correct unit! \nExiting..." # This is a reminder to use the correct units when entering a value

option = raw_input('Enter C for Circle or T for Triangle:') # Prompts User To Enter A C for Circle or T for Triangle 
option = option.upper() # Make all lower case entries upper case

# If statement to determine what letter the user entered
if option == 'C':
  print "You Entered A Circle"
  radius =  float(raw_input("Enter radius: "))
  area = pi * radius ** 2
  print "The pie is baking..."
  sleep(1)
  print ("Area: %.2f. \n%s" % (area, hint))
elif option == 'T':
  print "You Enered A Triangle"
  base =  float(raw_input("Enter base: "))
  height = float(raw_input("Enter height: "))
  area = 0.5 * base * height
  print "Uni Bi Tri..."
  sleep(1)
  print ("Area: %.2f. \n%s" % (area, hint))
else: 
  print "Error! Invalid shape selector specified. Exit..."
  exit()

