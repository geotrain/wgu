# This allows the user to order a small, medium, or large pizza. Then asks them if they want to add pepporoni and
# extra cheese. Based on the decisions the output calculation will be at the end.
print("Welcome to Python Pizza Deliveries!")
size = input("What size pizza do you want? S, M, or L: ")
pepperoni = input("Do you want pepperoni on your pizza? Y or N: ")
extra_cheese = input("Do you want extra cheese? Y or N: ")

if size == "S":
    price = 15
elif size == "M":
    price = 20
else:
    price = 25

if pepperoni == "Y" or pepperoni == "y":
    if size == "S":
        price += 2
    else:
        price += 3

if extra_cheese == "Y" or extra_cheese == "y":
    price += 1

print(f"Your final bill is: ${price}.")