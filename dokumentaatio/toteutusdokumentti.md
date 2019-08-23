## Toteutusdokumentti

Ohjelma on toteutettu Maven- projektina. Projektissa on seuraavat paketit: chessboard, engine, io, structures ja utils.

- Chessboard pitää sisällään luokat Chessboard ja State. Chessboard on shakkipelin tämämnhetkinen tilanne, ja state on hakualgoritmissä käytetty hypoteettinen pelin tila.

- Engine sisältää pelilogiikan. Check tarkistaa onko pelitilanne shakki, MoveChecker tarkistaa pelaajan tekemän siirron oikeellisuuden, MoveGenerator generoi kaikki mahdolliset lailliset siirrot ja Minimax etsii parhaan mahdollisen siirron käymällä läpi peliä annettuun syvyyteen asti.

- Io sisältää luokat GameIO, joka vastaa pelin näyttämisestä sekä käyttäjän syötteistä, sekä Main- luokan joka käynnistää pelin.

- Structures sisältää itsetoteutetut tietorakenteet. CustomList on lista, jonka toiminnallisuus vastaa ArrayListia.

- Utils sisältää apuluokkia. Func sisältää Matemaattisia funktioita, kuten itseisarvon ja etumerkin palauttamisen. Move sisältää pelaajan siirron tiedot. MoveType kertoo pelaajan siirron tyypin. Position kertoo sijainnin pelilaudalla.

Aikavaativuuksista.
