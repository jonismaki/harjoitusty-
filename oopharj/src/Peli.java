import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Peli{

///////////////////////////////////////////////////////////////////// METODIT ///////////////////////////////////////////////////////////////////////////////////////

    private static ArrayList<Kortti> taytaPakat(){       //PITÄISI LUODA PELAAJILLE PAKAT (TEKEE NYT VAIN YHDEN PAKAN)

        ArrayList<Kortti> Pakka = new ArrayList<Kortti>();

        for(int i = 0; i <= 14; i++){
            Random kortinTyyppi = new Random();        //Satunnainen luku 0-8 väliltä määrittelemään kumpi menee pakkaan
            int korttityyppi = kortinTyyppi.nextInt(11);
            Karhu k = new Karhu(2, i);
            Janis j = new Janis(1,i);
            Metsastaja m = new Metsastaja(0,i);
            Feromoni f = new Feromoni(0, 1);
            if(korttityyppi > 8){                      //Jos satunnainen luku on isompi kuin 9 niin lisää karhu
                Pakka.add(k);
            }
            if(korttityyppi == 8){                      // Jos 8 niin lisää feromoni kortti
                Pakka.add(f);
            }
            if(korttityyppi == 7){                      // Jos 7 lisää metsastaja
                Pakka.add(m);
            }
            if(korttityyppi < 7){                       // Alle 7 niin lisää jänis
                Pakka.add(j);
            }
        }

        return Pakka;
    }

///////////////////////////////////////////////////////////////////////// LOGIIKKA //////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args){

        System.out.println("Pelin tarkoitus on kerätä pisteitä. Peliä pelataan kymmenen kierrosta");
        System.out.println("Joka kierroksella pelaaja valitsee kahdesta kortista toisen pelattavaksi");
        System.out.println("Jänis kortti on yhden pisteen arvoinen ja kun pelaat sen, jänikset lisääntyvät puolella laudalla olevista jäniksistä (pyöristetään alaspäin)");
        System.out.println("Karhu kortin arvo on kaksi pistettä. Kun pelaat karhun, se syö toiselta yhden jäniksen pois");
        System.out.println("Metsästäjä poistaa vastustajalta neljä jänistä, mutta ei ole itsessään loppupisteytyksessä minkään arvoinen");
        System.out.println("Feromonit tuplaa pöydässä olevien jänisten määrän, mutta ei myöskään tuota itsessään pisteitä");
        System.out.println("On mahdollista pelata kortti ns. turhaan. Esimerkiksi pelata karhu kun toisella ei ole yhtään jänistä");
        System.out.println("Viimeisen kierroksen jälkeen, peli kruunaa voittajan");
        System.out.println("");

        ArrayList<Kortti> p1p = taytaPakat(); /////////////////////////////////////////////////////     PELAAJIEN PAKAT   /////////////////////////////////////////////////////////////////////////
        ArrayList<Kortti> p2p = taytaPakat();

        ArrayList<Kortti> p1l = new ArrayList<Kortti>();   ///////////////////////////////////////////// PELAAJIEN LAUTA ////////////////////////////////////////////////////////////////
        ArrayList<Kortti> p2l = new ArrayList<Kortti>();       

        ArrayList<Kortti> p1k = new ArrayList<Kortti>();   /////////////////////////////////////////////// PELAAJIEN KÄSI //////////////////////////////////////////////////////
        ArrayList<Kortti> p2k = new ArrayList<Kortti>();


        /*for(int i = 0; i < p1p.size(); i++){           ////PAKKATESTI////
            System.out.println(p1p.get(i).annaTyyppi());
        }*/


        for(int kesto = 0; kesto < 10; kesto++){

            if(kesto == 0){                                         /// ALKU KORTIT 
                for(int i = 0; i < 2; i++){
                    p1k.add(p1p.get(0));
                    p1p.remove(0);
                    p2k.add(p2p.get(0));
                    p2p.remove(0);
                }
            }

            System.out.println("Kierros " + (kesto + 1));
            System.out.println(" ");

            System.out.println("Pelaajalla 1 on pöydällä");

            if(p1l.size() > 0){                                                                         ///// TULOSTAA LAUDAT 
                for(int i = 0; i < p1l.size(); i++){
                    System.out.print(p1l.get(i).annaTyyppi() + " ");
                }
            }

            System.out.println(" ");
            System.out.println("Pelaajalla 2 on pöydällä");

            if(p2l.size() > 0){
                for(int i = 0; i < p2l.size(); i++){
                    System.out.print(p2l.get(i).annaTyyppi() + " ");
                }
            }
            System.out.println(" ");
            System.out.println("---------------------------------------------");


            System.out.println("P1 Valitse kortti jonka haluat pelata? P1");

            for(int i = 0; i < p1k.size(); i++){                                                        //////////// ANTAA P1 KÄDEN JA VAIHTOEHDOT /////////////
                System.out.println((i + 1)+ "." + " " + p1k.get(i).annaTyyppi());
            }

            Tyyppi pelattu1 = Tyyppi.JANIS;
            
            Scanner scan = new Scanner(System.in);
            int mikaKortti = scan.nextInt();

            if(mikaKortti == 1){
                p1l.add(p1k.get(0));
                pelattu1 = p1k.get(0).annaTyyppi();
                p1k.remove(0);
            }
            if(mikaKortti == 2){
                p1l.add(p1k.get(1));
                pelattu1 = p1k.get(1).annaTyyppi();
                p1k.remove(1);
            }

            if(pelattu1 == Tyyppi.KARHU){                      /////////////////////// KORTTIEN ERIKOIS TOIMINNOT KUN P1 PELAA ////////////////////////
                for(int i = 0; i < p2l.size(); i++){
                    if(p2l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        p2l.remove(i);
                        break;
                    }
                }
            }
            
            if(pelattu1 == Tyyppi.JANIS){
                int janikset = 0;
                for(int i = 0; i < p1l.size(); i++){
                    if(p1l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        janikset++;
                    }
                }
                int lisaantyy = janikset / 2;
                for(int i = 0; i < lisaantyy; i++){
                    Janis j = new Janis(1, 1);
                    p1l.add(j);
                }
            }

            if(pelattu1 == Tyyppi.METSASTAJA){
                int metsastetyt = 0;
                for(int i = 0; i < p2l.size(); i++){
                    if(p2l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        p2l.remove(i);
                        metsastetyt++;
                        if(metsastetyt == 4){
                            break;
                        }
                    }
                }
            }
            
            if(pelattu1 == Tyyppi.FEROMONI){
                int janikset = 0;
                for(int i = 0; i < p1l.size(); i++){
                    if(p1l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        janikset++;
                    }
                }
                for(int i = 0; i < janikset; i++){
                    Janis j = new Janis(1, 1);
                    p1l.add(j);
                }
            }




            System.out.println("P2 Valitse kortti jonka haluat pelata? P2");

            for(int i = 0; i < p2k.size(); i++){                                                        //////////// ANTAA P2 KÄDEN JA VAIHTOEHDOT /////////////
                System.out.println((i + 1)+ "." + " " + p2k.get(i).annaTyyppi());
            }

            Tyyppi pelattu2 = Tyyppi.JANIS;

            mikaKortti = scan.nextInt();
    
            if(mikaKortti == 1){
                p2l.add(p2k.get(0));
                pelattu2 = p2k.get(1).annaTyyppi();
                p2k.remove(0);
            }
            if(mikaKortti == 2){
                p2l.add(p2k.get(1));
                pelattu2 = p2k.get(1).annaTyyppi();
                p2k.remove(1);
            }

            if(pelattu2 == Tyyppi.KARHU){                           /////////////////////// KORTTIEN ERIKOIS TOIMINNOT KUN P2 PELAA ////////////////////////
                for(int i = 0; i < p1l.size(); i++){
                    if(p1l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        p1l.remove(i);
                        break;
                    }
                }
            }
            if(pelattu2 == Tyyppi.JANIS){
                int janikset = 0;
                for(int i = 0; i < p2l.size(); i++){
                    if(p2l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        janikset++;
                    }
                }
                int lisaantyy = janikset / 2;
                for(int i = 0; i < lisaantyy; i++){
                    Janis j = new Janis(1, 1);
                    p2l.add(j);
                }
            }

            if(pelattu2 == Tyyppi.METSASTAJA){
                int metsastetyt = 0;
                for(int i = 0; i < p1l.size(); i++){
                    if(p1l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        p1l.remove(i);
                        metsastetyt++;
                        if(metsastetyt == 4){
                            break;
                        }
                    }
                }
            }

            if(pelattu2 == Tyyppi.FEROMONI){
                int janikset = 0;
                for(int i = 0; i < p2l.size(); i++){
                    if(p2l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        janikset++;
                    }
                }
                for(int i = 0; i < janikset; i++){
                    Janis j = new Janis(1, 1);
                    p2l.add(j);
                }
            }




            p1k.add(p1p.get(0));                    //////////// LOPPU KIERROKSEN KORTTI NOSTO ////////////////////
            p1p.remove(0);
            p2k.add(p2p.get(0));
            p2p.remove(0);


        
            if(kesto == 9 ){                        /////////// LOPPUPISTEYTYS ////////////////////////////////////
                int pisteet1 = 0;
                int pisteet2 = 0;
                for(int i = 0 ; i < p1l.size(); i++){
                    if(p1l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        pisteet1 = pisteet1 + p1l.get(i).annaStr();
                    }
                    if(p1l.get(i).annaTyyppi() == Tyyppi.KARHU){
                        pisteet1 = pisteet1 + p1l.get(i).annaStr();
                    }
                    
                }
                for(int i = 0 ; i < p2l.size(); i++){
                    if(p2l.get(i).annaTyyppi() == Tyyppi.JANIS){
                        pisteet2 = pisteet2 + p2l.get(i).annaStr();
                    }
                    if(p2l.get(i).annaTyyppi() == Tyyppi.KARHU){
                        pisteet2 = pisteet2 + p2l.get(i).annaStr();
                    }
                    
                }
            if(pisteet1 > pisteet2){                                    //// VOITTAJAN JULISTUS //////////
                System.out.println("Pelaaja 1 on metsän KUNINGAS!");
            }
            else{
                System.out.println("Pelaaja 2 on metsän Kuningas!");
            }
            }
        }
    }

}