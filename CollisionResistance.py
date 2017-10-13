import sys
import random
import hashlib

library = "AaBb0CcDd1EeFf2GgHh3IiJj4KkLl5MmNn6OoPp7QqRr8SsTt9UuVvWwXxYyZz"

tempHash=""
tempString=""
i = 0
stringChar=0
stringLength=0
hashDict = {}

while(True):
    stringLength = random.randint(10,20)
    tempString=""
    i=0
    while(i<stringLength):
        i+=1
        stringChar = library[random.randint(0,61)]
        tempString += "".join(stringChar)
        
    tempHash = (hashlib.sha256(tempString.encode('utf_8')).hexdigest())[0:6]
    if(tempHash in hashDict):
        print(tempString + " " + hashDict[tempHash])
        sys.exit()
    else:
        hashDict[tempHash] = tempString
            
        