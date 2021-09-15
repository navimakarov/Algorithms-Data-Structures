import os


file1 = input("File1: ")
file2 = input("File2: ")

algo1 = input("Algo1: ")
algo2 = input("Algo2: ")

file1 = os.getcwd()+"\\"+file1
file2 = os.getcwd()+"\\"+file2

data1 = []
data2 = []
with open(file1, "r") as f1:
    data1 = f1.read().split("\n")
with open(file2, "r") as f2:
    data2 = f2.read().split("\n")

if(len(data1) > len(data2)):
    for i in range(len(data1)-len(data2)):
        data2.append("")
else:
    for i in range(len(data2)-len(data1)):
        data1.append("")

max_length1 = 0
for d in data1:
    if(len(d) > max_length1):
        max_length1 = len(d)

max_length2 = 0
for d in data2:
    if(len(d) > max_length2):
        max_length2 = len(d)

for i in range(len(data2)):
    data2[i] = data2[i] + " " * (max_length2 - len(data2[i]))
    data2[i] += " #"

for i in range(len(data1)):
    data1[i] = data1[i] + " " * (max_length1 - len(data1[i]))
    data1[i] += " # "
    data1[i] += data2[i]
    data1[i] = "# " + data1[i]

data1.insert(0, "+"*(7+max_length1+max_length2))
data1.insert(0, "+ @" + algo1 + " " * (max_length1 - len(algo1)) + "+ @" + algo2 + " " * (max_length2 - len(algo2)) + "+")
data1.insert(0, "+"*(7+max_length1+max_length2))
data1.append("+"*(7+max_length1+max_length2))
data1.append("")
with open("joined.java", "w") as f:
    f.write("\n".join(data1))
    
