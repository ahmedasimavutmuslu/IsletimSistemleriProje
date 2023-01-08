import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

// Gerçek zamanlý Proses kuyruðüumuz. LinkedList veri yapýsý kullanýldý.
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

    public boolean isEmpty() // LinkedList'in boþ olup olmadýðýnýn kontrolunu yapan bir fonksiyon.
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

    // Bu sýnýfýn en önemli fonksiyonu. Bu kuyruðunun yürütülmesinden sorumlu.

    public void RealTimeQueueRun() throws InterruptedException
    {
        if(Dosya.prosesSonlandiMi==false&&Dosya.sonCalisan=="user")
        {
            System.out.print("Zaman:"+Dosya.zaman+"  ");
            Dosya.kesilenProses.setDurum("askýda");
            System.out.print(Dosya.kesilenProses.getYaziRengi());
            System.out.println(Dosya.kesilenProses.prosesBilgileri());
            System.out.print(Color.RESET);
        }
        if(GercekZamanliListe.get(0).getProsesZamani()!=0&&GercekZamanliListe.get(0).getProsesZamani()>0) // Proses zamanýnýn kontrolu saðlanmýyor ki bitmiþ veyahut hatalý proses zamanýna sahip prosesler iþlenmesin.
        {
            if(GercekZamanliListe.get(0).getProsesZamani()==1) // Sonlandýrýlmak üzeree olan proseslerin iþlenmesi ve sonlandýrýlmasý için bir kontrol saðlanýyor.
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                System.out.print(GercekZamanliListe.get(0).getYaziRengi());
                System.out.println(GercekZamanliListe.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                GercekZamanliListe.get(0).setProsesZamani(GercekZamanliListe.get(0).getProsesZamani()-1);
                GercekZamanliListe.get(0).setDurum("sonlandýrýlýyor");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                System.out.print(GercekZamanliListe.get(0).getYaziRengi());
                System.out.println(GercekZamanliListe.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Dosya.prosesSonlandiMi=true;
            }
            else // Sonlandýrmaya yakýn olmayan prosesler bu koþula göre hareket ediyor.
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
