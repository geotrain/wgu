#Define a faster fibonacci procedure that will enable us to computer
#fibonacci(36).

def fibonacci(n):
    current = 0
    after = 1
    for i in range (0, n):
        current, after = after, current + after

    return current
print fibonacci(36)

mass_of_earth = 5.9722 * 10**24 #Kilograms
mass_of_rabbit = 2 # 2 kilograms per rabbit
n = 1
while fibonacci(n) * mass_of_rabbit < mass_of_earth:
    n = n + 1
print n, fibonacci(n)
