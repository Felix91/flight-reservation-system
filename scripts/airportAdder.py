#!/usr/bin/env python3

import urllib.request as url

def airports():
    cities=url.urlopen("http://www.census.gov/popest/data/cities/totals/2011/tables/SUB-EST2011-03-40.csv")


    count=1
    tmp=cities.readline()
    print(tmp)
    while(tmp.decode()[0] != '.'):
        tmp=cities.readline();

    while(True):
        city= cities.readline()[1:].decode().split(',')[0];
        if not city:
            break
        callsign="{0:0=3d}".format(count)
        count+=1
        yield {"city": city, "callsign":callsign}

gen=airports()
while (1):
    airport= next(gen)
    URL="http://localhost:8888/entityTest?test=Airport&callSign="+airport["callsign"]+"&city="+airport["city"].replace(" ","+")
    print(URL)
    url.urlopen(URL)
