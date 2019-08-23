## Käyttöohje

Lataa uusin release ja suorita se komentorivillä komennolla `java -jar luotu_jar.jar`

Peli toimii muuten kuten shakki, mutta seuraavia sääntöjä ei ole:

- Linnoittautuminen
- En passant
- Toisteiset siirrot
- Tasapelit mistään syystä

Jos pelaajalla ei ole laillista siirtoa, vaikka kuningas ei olisi uhattuna, vastapuoli voittaa.

Tämän jälkeen ohjelma käynnistyy komentorivillä. Pelimoodin voi valita 1-3; 1 pelaaja konetta vastaan, 2 kone itseään vastaan, 3 kaksi pelaajaa toisiaan vastaan. Jos valitset 1 tai 2, niin kysytään vielä koneen "vaikeusaste", eli kuinka monta siirtoa eteenpäin kone etsii siirtoja. Taso 5 on jo aika hidas, varsinkin jos haluaa katsoa koneita vastakkain.

Pelaajien siirrot luetaan komentoriviltä muodossa `a2,a4`, eli laudan paikalla a2 ollut nappula siiryy paikkaan a4. Jos siirto ei ole mahdollinen tai menee laudan ulkopuolelle, niin siitä ilmoitetaan, ja pelaajalta kysytään uudestaan.

Peli päättyy joko shakkimattiin tai siihen, että nappuloita on kaksi jäljellä. Siirtojen lukumäärää ei rajoiteta, eikä muitakaan tasapeliin johtavia sääntöjä ole.
