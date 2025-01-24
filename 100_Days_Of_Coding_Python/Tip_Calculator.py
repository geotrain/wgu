# This is a tip calculator that asks the user the total of a bill, how much you plan on tipping and then
# asks how many people are splitting the bill. Once that is calculated an output is given for each user.
print("Welcome to the tip calculator!")
total_bill = float(input("What is the total bill? \n"))
tip_amount = float(input("How much tip would you like to give? 10, 12, or 15? \n"))
tip_percent = tip_amount / 100
final_tip = total_bill * tip_percent
people_split = int(input("How many people to split the bill? \n"))
calculation = total_bill + final_tip
split_total = round(calculation / people_split, 2)
print(f"Each person should pay: ${split_total:.2f}")