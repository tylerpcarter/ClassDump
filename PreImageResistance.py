import sys
import hashlib
import random

shaHash = (hashlib.sha256("The quick brown fox jumps over the lazy dog".encode('utf_8')).hexdigest())[0:6]

library = "AaBb0CcDd1EeFf2GgHh3IiJj4KkLl5MmNn6OoPp7QqRr8SsTt9UuVvWwXxYyZz "

stringChar = 0
stringLength = 0
i = 0
tempString = ""
tempHash = ""

# Infinite loop, select a random string length between 1 and 50. Create an empty string which will have random characters
# concatenated to the tempString up to the length of the string. For every character added to the string, run sha256 on the new string
# and compare the first six characters of the tempHash to the first six characters of the shaHash we are given. If they match,
# then we have found a tempString which, when encoded with sha256, produces a hex with the same first six values.

while(True):
    stringLength = random.randint(1,50)
    i=0
    tempString = ""
    while(i<stringLength):
        stringChar = random.randint(0,62)
        tempString += library[stringChar]
        i+=1
        print(tempHash + " " + shaHash)
        tempHash = (hashlib.sha256(tempString.encode('utf_8')).hexdigest())[0:6]
        if(tempHash==shaHash):
            print(tempString)
            sys.exit()


#     Alternative way commented out, iterate through every integer 
#     up to 9223372036854775807 and hash that way.
# integer = 1
# hexString = ""
# checkHash = ""
# while(True):
#     hexString = hexString+hex(integer)
#     integer+=1
#     checkHash = hashlib.sha256(hexString.encode('utf_8')).hexdigest()
#     print(checkHash[0:6] + " " + shaHash[0:6] + " | " + str(integer))
#     if (checkHash[0:4] == shaHash[0:4]):
#         print("Found it. The integer is " + str(integer))
#         sys.exit()
      
    
    
    
    