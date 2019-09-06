## Testausdokumentti

Ohjelman kannalta oleellisimmat luokat ja toiminnallisuudet on testattu käyttäen javan JUnit- testejä.

Toiminnallisuudet kuten shakki ja siirron toteaminen lailliseksi/laittomaksi on testattu luomalla haluttu shakkitilanne, ja testaamalla metodeja sitä vastaan.

Liikkeiden generoimisen testaus on toteutettu laskemalla tietyssä tilanteessa shakkinappulan laillisten liikkeiden määrä ja vertaamalla sitä generaattorin antamaan tulokseen.

Minimax hakualgoritmi on testattu antamalla tilanne, jonka voi aina voittaa pelaamalla oikein tietty määrä siirtoja (mate in n turns), ja katsomalla että algoritmi palauttaa alkuperäisen tilanteen 'arvoksi' voitto/häviö. Toisin sanoen algoritmi tietää n siirtoa etukäteen, että se tulee varmasti voittamaan/häviämään.

### Suorituskykytestaus
Ohjelmassa suorituskykyä on testattu seuraavilta osilta:

* Siirron laillisuuden tarkistaminen

  Annetusta lähtötilanteesta generoidaan 10,000,000 siirtoa ja tarkistetaan niiden laillisuus. 

* Kaikkien mahdollisten siirtojen generoiminen

  Annetusta tilasta luodaan kaikki mahdolliset lailliset siirrot 100,000 kertaa.

* Parhaan siirron generoiminen Minimaxilla

  Samasta tilasta valitaan paras siirto 10,000 kertaa syvyydellä 1, eli algoritmi katsoo kahden siirron päähän.
