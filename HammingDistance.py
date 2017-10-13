import sys

hash1 = sys.argv[1]
hash2 = sys.argv[2]

#Given a hex value, return a 16 digit binary number. [2:] skips 0x created by bin() function.
def binify(hexvalue):
    return bin(int(hexvalue,16))[2:]

bin1 = binify(hash1)
bin2 = binify(hash2)

ham  = 0
counter = 0;

# for every character, check the same character position in the other string. Compare. If not equal, ham++
for char in bin1:
    if char != bin2[counter]:
        ham+=1
    counter+=1

# print final binary difference
print(ham)

sys.exit()