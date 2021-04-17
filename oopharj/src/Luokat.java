abstract class Kortti{                               // KORTTI LUOKKA, SISÄLTÄÄ VAIN STR STATIN

    protected Tyyppi tyyppi;
    protected int str;                      // TARVITSEE MAHDOLLISESTI TOSTRING METODIN, ETTÄ KORTIT JA NIIDEN ARVOT SAADAAN NÄKYVIIN
    protected int id;
    
    public Kortti(int str, int id){  
        this.str = str;
        this.id = id;
    }
    
    protected int annaStr(){                            // ANNA NIMI JA ANNA STR VOISI LAITTAA SAMAAN METODIIN, ETTÄ NE ON HELPOMPI SAADA "LAUDALLE"
        return str;
    }
    protected int annaId(){
        return id;
    }
    protected Tyyppi annaTyyppi(){
        return tyyppi;
    }

}

class Karhu extends Kortti{

    static Tyyppi tyyppi = Tyyppi.KARHU;


    public Karhu(int str, int id){
        super(str, id);
    }

    protected Tyyppi annaTyyppi(){
        return tyyppi;
    }

}

class Janis extends Kortti{

    static Tyyppi tyyppi = Tyyppi.JANIS;

    public Janis(int str, int id){
        super(str, id);
    }

    protected Tyyppi annaTyyppi(){
        return tyyppi;
    }
}

class Feromoni extends Kortti{

    static Tyyppi tyyppi = Tyyppi.FEROMONI;

    public Feromoni(int str, int id){
        super(str, id);
    }
    protected Tyyppi annaTyyppi(){
        return tyyppi;
    }
}

class Metsastaja extends Kortti{

    static Tyyppi tyyppi = Tyyppi.METSASTAJA;

    public Metsastaja(int str, int id){
        super(str, id);
    }
    protected Tyyppi annaTyyppi(){
        return tyyppi;
    }
}


enum Tyyppi{
    KARHU, JANIS, FEROMONI, METSASTAJA;
}
