# This script asks the user how long of a password they want, how many letters, symbols, and numbers
# It then spits out a password chosen randomly and gives them a usable password.
import random
letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
           's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
           'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
numbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
symbols = ['!', '#', '$', '@', '%', '&', '(', ')', '-', '_', '*', '=', '^', '.', ',', ':', ';', '?',
           ';', '<', '>']
print("Welcome to the Password Generator!")
password_letters = int(input("How many letters would you like in your password? \n"))
if password_letters > 52:
    print("Please restart the script and next time when choosing the amount of letters in your password, please enter 52 or less.")
    exit(0)
password_numbers = int(input("How many numbers would you like in your password?\n"))
if password_numbers > 10:
    print("Please restart the script and next time when choosing the amount of numbers in your password, please enter 10 or less.")
    exit(0)
password_symbols = int(input("How many symbols would you like in your password? \n"))
if password_symbols > 21:
    print("Please restart the script and next time when choosing the amount of symbols in your password, please enter 21 or less.")
    exit(0)
random_letters = random.sample(letters, password_letters)
random_numbers = random.sample(numbers, password_numbers)
random_symbols = random.sample(symbols, password_symbols)
password = random_letters + random_numbers + random_symbols
random.shuffle(password)
formatted_password = "".join(password)
print(f"Your password is: {formatted_password}")