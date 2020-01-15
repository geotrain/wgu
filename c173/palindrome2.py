def iter_palindrome(s):
    for i in range(0, len(s) / 2):
        if s[i] != s[-s(i + 1)]:
            return False
    return True
