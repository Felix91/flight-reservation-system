#!/usr/bin/env python3

import urllib.request as url
import sys
#POSTDATA=emailAddr=fda&password=asdf&firstName=asdf&lastName=asf

def users(inputFile):
    print("inputFile" + inputFile);
    f = open(inputFile)
    count=1
    tmp=f.readline()
    print("tmp="+tmp)
    while(True):
        line=f.readline()
        print("line="+line)
        Fname = line.split(',')[0]
        Lname = line.split(',')[1]
        Email = line.split(',')[2]
        Passw = line.split(',')[3]
        if not Passw:
            break
        c="{0:0=1d}".format(count)
        count+=1
        yield {"emailAddr": Email, "firstName": Fname, "lastName": Lname, "password": Passw, "c":c}


def main( argv):
    if (len(argv)<2):
        print("Usage: " +argv[0]+" csvFile.csv")
        return;

    gen=users(argv[1])
    while (1):
        customers = next(gen)
	    #URL=customers["c"]+"&emailAddr="+customers["emailAddr"]+"&firstName="+customers["firstName"]+"&lastName="+customers["lastName"]+"&password="+customers["password"]
        URL="http://localhost:8888/entityTest?test=Customer"+"&emailAddr="+customers["emailAddr"]+"&firstName="+customers["firstName"]+"&lastName="+customers["lastName"]+"&password="+customers["password"]
        print(URL)
        url.urlopen(URL)

main(sys.argv);

