# Heads or Tails coin flipping game based on random flip between 0 and 1
import random
coin_flip = random.randint(0, 1)
print(coin_flip)
if coin_flip == 0:
    print("You got heads!")
else:
    print("You got tails!")