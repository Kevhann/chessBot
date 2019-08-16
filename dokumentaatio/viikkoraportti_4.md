## Viikkoraportti 4

Tällä viikolla sain vihdoin tehtyä pelin siihen vaiheeseen, että pystyn soveltamaan siihen minimax-algoritmia alpha-pruningilla. Peli on edelleen oikeasta shakista pelkinnetty, nimellisesti tornitus, tasapelit, loppupelissä toisteiset liikkeet sekä en passant ovat tekemättä.

Ohjelmasta löytyi vielä muutama harmittava bugi, joiden pitäisi olla korjattu. Testaus ei edennyt tällä viikolla paljoa.

Opin minimaxista lisää, sekä alpha-pruningista.

Vaikeuksia tuotti ohjelmassa piilevien bugien löytäminen. Myös itse algoritmin toiminta tuotti hieman vaikeuksia. En ole aivan varma, miten tulisi testata algoritmin oikeellisuutta. Luultavasti käy manuaalisesti läpi joitain yksinkertaisia pelilautoja ja etsii niistä parhaat siirrot x liikkeen jälkeen.

Seuraavaksi laajennan pelilaudan arviointiin käytettävää algoritmia paremmaksi. Tavoitteena on myös karsia aggressiivisemmin vaihtoehtoja pois, jotta hakusyvyyttä saadaan suurennettua.

Kiitos rakentavasta palautteesta!
