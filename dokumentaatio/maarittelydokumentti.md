## Määrittelydokumentti

Työ tulee käyttämään minimax- algoritmia parhaiden peliliikkeiden löytämiseen ja alpha-pruning- algoritmia vaihtoehtojen karsimiseen. Minimax arvioi pelitilanteita ja antaa niille arvosanan niiden edun mukaan. Alpha-pruning leikkaa pois huonoja vaihtoehtoja, jonka seurauksena arvioitavia liikkeitä on vähemmän.

Pelin kulku ajatellaan suurena puuna, jossa jokainen mahdollinen pelitilanne on kuvattuna. Tätä puuta kuljetaan haluttuun syvyyteen ja etsitään paras vaihtoehto minimaxia käyttäen.

Valitsin algoritmin, koska olin kuullut siitä aiemmin ja se vaikuttaa mielenkiintoiselta. Tekoälyn kehittäminen peliin on ollut pitkään mielenkiinnon aihe. Pelin kulku on kaikista luontevinta esittää puuna, koska pelin kulku on lineaarinen. Muut algoritmit ja lähestymistavat ovat ajan puitteissa mahdollisia.

Tavoitteena olisi saada ohjelma toimimaan valmiin shakkiohjelman kanssa, mutta tämän toteuttamisesta ei ole yhtään tietoa tai kokemusta. Minimivaatimus on, että ohjelma pelaa shakkia tekstipohjaisesti, eli ilmoittaa aina siirtonsa tyyliin a2 -> b4. Syötteinä ohjelma saa joko käyttäjän peliliikkeet, tai sitten se pelaa itseään vastaan.

**Aikavaativuuksista**

"shakki"- tilanteen tarkastava algoritmi toimii O(n) ajassa, jossa n on kaikkien palojen yhteenlaskettu siirtojen lukumäärä yhdestä ruudusta (kuningattaren siirtoja ei lasketa uudestaan, koska ne on jo otettu huomioon tornin ja lähettilään siirroissa).
Jokaisesta mahdollisesta paikasta tarkistetaan, onko siinä oikeanlainen vastustajan pala. Haku pysähtyy ensimmäiseen osumaan, joten n on välillä 1-45 (kuninkaan ja sotilaan tapaukset lasketaan erillä muista. Kuningattarella max 27 siirtoa, ratsulla 8, kuninkaalla 8 joista 2 sisältää sotilaan. Sotilaan kolmas siirto ei uhkaa, joten sitä ei huomioida tässä. N).

Laillisten siirtojen listaus toimii hyvin samalla tavalla, mutta etsii kaikki mahdolliset siirrot. Tätä varten käydään taulukko läpi ja etsitään sieltä oikean puolen palat ja lisätään jokaisen palan siirrot yhteen listaan. Paloja on 16 puolella, joilla on max 3, 8, 8, 13, 14, 27 siirtoa. Yhteensä enintään 129 siirtoa, vaikka niin monta ei ole mahdollista kerralla. Keskiarvo on luultavasti 30 - 50 mahdollista siirtoa.

Lopuksi laillisuuden testaamiseksi jokaiselle luodulle siirrolle täytyy testata, onko kyseessä shakki, ja nämä vaihtoehdot karsitaan pois. Eli tämän aikavaativuudeksi tulee O(n\*m), jossa n kuningasta uhkaavien siirtojen määrä ja m kaikkien siirtojen määrä.

Shakki-matti tarkastetaan samalla tavalla. Jos laillisten siirtojen lukumäärä on 0, kyseessä on shakki-matti.

Kentän 'hyvyyden' arvioimisen aikavaativuus yksinkertaisella Minimax-algoritmilla on O(n^d), jossa n mahdollisten siirtojen lukumäärä ja d tutkittava syvyys. Aikavaativuus kasvaa hyvin nopeasti, joten tutkittava syvyys on hyvin rajoitettu tällä lähestymistavalla. Tästä olisi tarkoitus parantaa huomattavasti karsimalla pois 'tiedettävästi huonoja' siirtoja, joiden jälkeläisiä ei enää tarvitse tutkia.

Lähteet:

[wikipedia](https://en.wikipedia.org/wiki/Computer_chess)
