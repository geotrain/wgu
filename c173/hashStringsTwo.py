def hash_string(keywords, buckets):
    h = 0
    for c in keyword:
        h = (h + ord(c)) % buckets

    return h

print hash_string('b', 12)
print hash_string('dictionary', 1000)