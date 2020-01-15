#Factorail of 200
def factorial(n):
    if n == 0: # This is the base case
        return 1
    else: 
        return n * factorial(n - 1) # This is the recursive case
        # Print 3 * 2 * 1

print factorial(0) # Recursive procedure is being asked to run here 
print factorial(3) # Recursive procedure is being asked to run here 
print factorial(5) # Recursive procedure is being asked to run here 