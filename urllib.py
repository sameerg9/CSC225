
import urllib2

wiki_link = "http://javahungry.blogspot.com/2013/04/quadratic-probing-and-linear-probing_26.html"


page = urllib2.urlopen(wiki)

from bs4 import BeautifulSoup 

soup = BeautifulSoup(page)

print(page)

print(soup) 



print( soup.prettify() )
