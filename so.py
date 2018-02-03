from bs4 import BeautifulSoup
from urllib2 import urlopen 

BASE_URL = 'http://www.chicagoreader.com'

def get_category_links():
	html = urlopen(section_url)
	soup = BeatifulSoup(hmtl , "lxml")
	print(soup)
	
	boccat = soup.find("dl", "boccat" )
	print(BASE_URL)
	clinks = [BASE_URL + dd.a["href"] for dd in boccat.findAll("dd") ]
	print(boccat)
	print(clinks)
	





print("print sometihng please ")
