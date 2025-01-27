# This game takes a range of 1 to 100 and then checks to see if it is divisible by 3, if so it prints
# the word fizz. If it is divisible by 5 it prints buzz, if it is divisible by 3 and 5, e.g, 15, it prints
# FizzBuzz. If neither of these 3 conditions it just prints the number
for number in range(1, 101):
    if number % 3 == 0 and number % 5 == 0:
        print("FizzBuzz")
    elif number % 3 == 0:
        print("Fizz")
    elif number % 5 == 0:
        print("Buzz")
    else:
        print(number)