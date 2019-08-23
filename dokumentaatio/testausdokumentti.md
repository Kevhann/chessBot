## Testausdokumentti

Ohjelman kannalta oleellisimmat luokat ja toiminnallisuudet on testattu käyttäen javan JUnit- testejä.

Toiminnallisuudet kuten shakki ja siirron toteaminen lailliseksi/laittomaksi on testattu luomalla haluttu shakkitilanne, ja testaamalla metodeja sitä vastaan.

Liikkeiden generoimisen testaus on toteutettu laskemalla tietyssä tilanteessa shakkinappulan laillisten liikkeiden määrä ja vertaamalla sitä generaattorin antamaan tulokseen.

Minimax hakualgoritmi on testattu antamalla tilanne, jonka voi aina voittaa pelaamalla oikein tietty määrä siirtoja (mate in n turns), ja katsomalla että algoritmi palauttaa alkuperäisen tilanteen 'arvoksi' voitto/häviö. Toisin sanoen algoritmi tietää n siirtoa etukäteen, että se tulee varmasti voittamaan/häviämään.
