import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

// Ger�ek zamanl� Proses kuyru��umuz. LinkedList veri yap�s� kullan�ld�.
public class RealTimeQueue {

    private LinkedList<Proses> GercekZamanliListe;

    public RealTimeQueue() // Kurucu fonksiyonumuz ile LinkedList'i initialize ediyoruz.
    {
        this.GercekZamanliListe=new LinkedList<>();
    }

    public void RealTimeQueueAdd(Proses proses) // Parametre olarak gelen prosesi LinkedList'e ekliyoruz.
    {
        GercekZamanliListe.add(proses);
    }

    public boolean isEmpty() // LinkedList'in bo� olup olmad���n�n kontrolunu yapan bir fonksiyon.
    {
        if(GercekZamanliListe.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Bu s�n�f�n en �nemli fonksiyonu. Bu kuyru�unun y�r�t�lmesinden sorumlu.

    public void RealTimeQueueRun() throws InterruptedException
    {
        if(Dosya.prosesSonlandiMi==false&&Dosya.sonCalisan=="user")
        {
            System.out.print("Zaman:"+Dosya.zaman+"  ");
            Dosya.kesilenProses.setDurum("ask�da");
            System.out.print(Dosya.kesilenProses.getYaziRengi());
            System.out.println(Dosya.kesilenProses.prosesBilgileri());
            System.out.print(Color.RESET);
        }
        if(GercekZamanliListe.get(0).getProsesZamani()!=0&&GercekZamanliListe.get(0).getProsesZamani()>0) // Proses zaman�n�n kontrolu sa�lanm�yor ki bitmi� veyahut hatal� proses zaman�na sahip prosesler i�lenmesin.
        {
            if(GercekZamanliListe.get(0).getProsesZamani()==1) // Sonland�r�lmak �zeree olan proseslerin i�lenmesi ve sonland�r�lmas� i�in bir kontrol sa�lan�yor.
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                System.out.print(GercekZamanliListe.get(0).getYaziRengi());
                System.out.println(GercekZamanliListe.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                GercekZamanliListe.get(0).setProsesZamani(GercekZamanliListe.get(0).getProsesZamani()-1);
                GercekZamanliListe.get(0).setDurum("sonland�r�l�yor");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                System.out.print(GercekZamanliListe.get(0).getYaziRengi());
                System.out.println(GercekZamanliListe.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Dosya.prosesSonlandiMi=true;
            }
            else // Sonland�rmaya yak�n olmayan prosesler bu ko�ula g�re hareket ediyor.
            {
                TimeUnit.SECONDS.sleep(1);
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                System.out.print(GercekZamanliListe.get(0).getYaziRengi());
                System.out.println(GercekZamanliListe.get(0).prosesBilgileri());
                System.out.print(Color.RESET);

                GercekZamanliListe.get(0).setProsesZamani(GercekZamanliListe.get(0).getProsesZamani()-1);

                Dosya.prosesSonlandiMi=false;
            }


        }
        else
        {
            GercekZamanliListe.remove(0);
            Dosya.realBos=true;
        }


    }
}
