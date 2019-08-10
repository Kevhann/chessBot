## Viikkoraportti 3

Tällä viikolla refaktoroin sovellusta huomattavasti. Luovuin eri shakkinappuloita kuvaavista luokista ja siirryin kuvaamaan shakkipöytää yhtenä 64:n pituisena taulukkona, jossa shakkinappulan määrittää luku ja puolen määrittää luvun etumerkki.

Lisäksi sain sovellettua funktion, joka palauttaa kaikki mahdolliset siirrot. Shakistani puuttuu vielä toistaiseksi säännöt kuten castling, promotion ja en passant. Tällä hetkellä vastaan pelaa erittäin huono shakki-algo, mutta pelaa kuitenkin.

Tällä viikolla opin välicommittien tärkeydestä ja ns. pala palalta koodaamisesta.

Vaikeuksia tuotti projektin rakenteesta päättäminen sekä shakki-matin tarkastaminen. Yleisesti 'parhaan' tavan päättäminen toi vaikeuksia. Lisäksi paljon hankaluuksia tuotti liian monen asian samanaikainen tekeminen sekä jälkijunassa tullut testaus.

Seuraavaksi täydennän tämänhetkistä testausta ja alan rakentamaan toimivaa minimax-algoritmia.
