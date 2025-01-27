# This script looks at a list of student scores and then prints out the max score after looping
# through each one.
student_scores = [180, 124, 165, 173, 189, 199, 146, 201, 122]
max_score = 0
for score in student_scores:
    if score > max_score:
        max_score = score
print(max_score)
