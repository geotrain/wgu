#studentBecomesTeacher.py

lloyd = {
    "name": "Lloyd",
    "homework": [90.0, 97.0, 75.0, 92.0],
    "quizzes": [88.0, 40.0, 94.0],
    "tests": [75.0, 90.0]
}
alice = {
    "name": "Alice",
    "homework": [100.0, 92.0, 98.0, 100.0],
    "quizzes": [82.0, 83.0, 91.0],
    "tests": [89.0, 97.0]
}
tyler = {
    "name": "Tyler",
    "homework": [0.0, 87.0, 75.0, 22.0],
    "quizzes": [0.0, 75.0, 78.0],
    "tests": [100.0, 100.0]
}

# Add your function below!
def average(numbers):
    total = sum(numbers)
    total = float(total)
    return total / len(numbers)\

def get_average(student):
    h = average(student["homework"])
    q = average(student["quizzes"])
    t = average(student["tests"])
    aver = (h * 0.1) + (q * 0.3) + (t * 0.6)
    return aver
students = [lloyd, alice, tyler]

def get_class_average(classlist):
    results=[]
    for student in classlist:
        result = get_average(student)
        results.append(result)
    return average(results)

def get_letter_grade(score):
    if score >= 90:
        return "A"
    elif score >= 80 and score < 90:
        return "B"
    elif score >= 70 and score < 80:
        return "C"
    elif score >= 60 and score < 70:
        return "D"
    else:
        return "F"


print get_class_average(students)
print get_letter_grade(get_class_average(students))