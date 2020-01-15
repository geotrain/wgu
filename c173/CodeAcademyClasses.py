
# Creating A Subclass Called Car Inheriting It's Attributes And Behaviors From The Object Superclass
class Car(object): 
    # Passing A String Value Of "New" to Variable Called Condition
    condition = "new"
    # Define The __Init__ Function
    condition = "new"
    # Using the __init__ Function To Pass Values Using The Self Keyword
    def __init__(self, model, color, mpg):
        self.model = model
        self.color = color
        self.mpg   = mpg

# This Is Create A Variable Called My_car That Is Defined As An Instance Of The Car Object And Passing Parameters
my_car = Car("DeLorean", "silver", 88)

# Print The Value That Is Passed To My_Car Object And Variables Associated With Dot Notation 
print my_car.condition
print my_car.model
print my_car.color
print my_car.mpg