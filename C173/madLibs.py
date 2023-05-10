# This Is A Mad Lib
# Program Just For Fun
print "The Mad Libs Have Started" # Alert User Program has started
name = raw_input("Please enter a proper name: ") # Getting the name for the user
adjective1 = raw_input("Please enter an adjective: ") # Ask user for an adjective
adjective2 = raw_input("Please enter an adjective: ") # Ask user for an adjective
adjective3 = raw_input("Please enter an adjective: ") # Ask user for an adjective
verb1 = raw_input("Please enter a verb: ") # Ask for a verb
verb2 = raw_input("Please enter a verb: ") # Ask for a verb
verb3 = raw_input("Please enter a verb: ") # Ask for a verb
noun1 = raw_input("Please enter a noun: ") # Ask user for a noun
noun2 = raw_input("Please enter a noun: ") # Ask user for a noun
noun3 = raw_input("Please enter a noun: ") # Ask user for a noun
noun4 = raw_input("Please enter a noun: ") # Ask user for a noun
animal =  raw_input("Please enter an animal: ") # Ask user for a animal
food =  raw_input("Please enter a food: ") # Ask user for a food
fruit =  raw_input("Please enter a fruit: ") # Ask user for a fruit
number =  raw_input("Please enter a number: ") # Ask user for a number
superHero =  raw_input("Please enter a super hero: ") # Ask user for a super hero
country =  raw_input("Please enter a country: ") # Ask user for a country
dessert =  raw_input("Please enter a dessert: ") # Ask user for a dessert
year =  raw_input("Please enter a year: ") # Ask user for a year

#The template for the story
STORY = "This morning I woke up and felt %s because %s was going to finally %s over the big %s %s. On the other side of the %s were many %ss protesting to keep %s in stores. The crowd began to %s to the rhythm of the %s, which made all of the %ss very %s. %s tried to %s into the sewers and found %s rats. Needing help, %s quickly called %s. %s appeared and saved %s by flying to %s and dropping %s into a puddle of %s. %s then fell asleep and woke up in the year %s, in a world where %ss ruled the world."

print STORY % (name, adjective1, adjective2, adjective3, verb1, verb2, verb3, noun1, noun2, noun3, noun4, animal, food, fruit, number, superHero, country, dessert, year)