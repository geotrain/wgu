# Three Stooges Value Of Strings
stooges = ['Moe', 'Larry', 'Curly']
print stooges

# Replace Stooges Input Value For Curly To Shemp
stooges[2] = 'Shemp'
print stooges

# Add Curly back to list with append() function
stooges.append('Curly')
print stooges

# Len() method to use with the list stooges to see how many values are in there
print len(stooges)

# Create 2 new lists then concatenate them using the plus opperand to show one string
stoogeBrothers = ['Moe', 'Shemp', 'Curly']
nonStoogeBrothers = ['Shemp']
allStooges = stoogeBrothers + nonStoogeBrothers
print allStooges

