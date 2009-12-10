#!/usr/bin/python

import urllib,sys,time,os

def findIP(adapter):
    ifconfig = os.popen("/sbin/ifconfig").readlines()
    i = 0
    for line in ifconfig:
        line = line.strip()
        if line.find(adapter)>-1:
            ipLine = ifconfig[i+1].strip()
            colon = ipLine.index(':')+1
            space = ipLine.index(' ',colon)
            ip = ipLine[colon:space]
            return ip
        i = i+1

if len(sys.argv) < 3:
    print "Usage: dnsupdate.py <nic> userName password host"
    sys.exit()

nic = sys.argv[1]    
username = sys.argv[2]
password = sys.argv[3]
apiKey = ''
ipAddress = findIP(nic)

for host in sys.argv[4:len(sys.argv)]:
    params = {"l_username":username,
              "l_password":password,
              "host":host,
              "api_key":apiKey,
              "type":"A",
              "action":"SET",
              "value":ipAddress
              }

    params = urllib.urlencode(params)

    f = urllib.urlopen("https://rimuhosting.com/dns/dyndns.jsp",params)
    
    result = f.read().rstrip()

    print result
