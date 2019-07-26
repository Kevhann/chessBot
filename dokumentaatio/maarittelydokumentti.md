## Määrittelydokumentti

Työ tulee  käyttämään minimax- algoritmia parhaiden peliliikkeiden löytämiseen ja alpha-pruning- algoritmia vaihtoehtojen karsimiseen. Minimax arvioi pelitilanteita ja antaa niille arvosanan niiden edun mukaan. Alpha-pruning leikkaa pois huonoja vaihtoehtoja, jonka seurauksena arvioitavia liikkeitä on vähemmän.

Pelin kulku ajatellaan suurena puuna, jossa jokainen mahdollinen pelitilanne on kuvattuna. Tätä puuta kuljetaan haluttuun syvyyteen ja etsitään paras vaihtoehto minimaxia käyttäen. 

Valitsin algoritmin, koska olin kuullut siitä aiemmin ja se vaikuttaa mielenkiintoiselta. Tekoälyn kehittäminen peliin on ollut pitkään mielenkiinnon aihe. Pelin kulku on kaikista luontevinta esittää puuna, koska pelin kulku on lineaarinen. Muut algoritmit ja lähestymistavat ovat ajan puitteissa mahdollisia. 

Tavoitteena olisi saada ohjelma toimimaan valmiin shakkiohjelman kanssa, mutta tämän toteuttamisesta ei ole yhtään tietoa tai kokemusta. Minimivaatimus on, että ohjelma pelaa shakkia tekstipohjaisesti, eli ilmoittaa aina siirtonsa tyyliin a2 -> b4. Syötteinä ohjelma saa joko käyttäjän peliliikkeet, tai sitten se pelaa itseään vastaan.

tavoitteena ei ole mitään varsinaista aikavaatimusta, ohjelman tulisi ainoastaan valita siirtonsa nopeasti (alle sekunti).

Lähteet:

[wikipedia](https://en.wikipedia.org/wiki/Computer_chess)
