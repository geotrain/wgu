# Compare 3 Numbers Using If Else Statement
def biggest(a, b, c):
    if a > b and a > c:
        return a
    if b > a and b > c:
        return b
    else:
        return c
print biggest(3, 6, 9)
print biggest(6, 9, 3)
print biggest(9, 3, 6)
