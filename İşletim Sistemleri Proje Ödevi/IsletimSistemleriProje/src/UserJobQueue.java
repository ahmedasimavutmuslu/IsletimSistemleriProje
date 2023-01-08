import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//  Kullan�c� Prosesleri i�in Ger�ek Zamanl� Kuyrukta oldu�u gibi yine LinkedList veri yap�s� kullan�yoruz.
// Ancak farkl� olarak �ncekli�e g�re �� farkl� seviye bulunuyor. Bunlar i�in ayr� ayr� kuyruklar kullan�ld�.
public class UserJobQueue
{
    private LinkedList<Proses> Priority1;
    private LinkedList<Proses> Priority2;
    private LinkedList<Proses> Priority3;
    private int sonKalinanIndex=0;
    public Proses sonIslenenUserProses;


    public UserJobQueue() // Farkl� �ncelik seviyelerindeki kuyruklar initialize ediliyor.
    {
        this.Priority1=new LinkedList<>();
        this.Priority2=new LinkedList<>();
        this.Priority3=new LinkedList<>();
    }
    public Proses sonProsesDondur(Proses proses) // Son i�lenen prosesi dondurur;
    {
        return proses;
    }

    public void UserJobQueueAdd(Proses proses) //oncelige gore kuyruklara al�n�yor
    {
        if(proses.getOncelik()==1)
        {
            Priority1.add(proses);

        }
        else if(proses.getOncelik()==2)
        {
            Priority2.add(proses);

        }
        else
        {
            Priority3.add(proses);
        }
    }
    public boolean isEmpty(LinkedList<Proses> oncelik) //Kuyruklar�n bo� olup olmamas�n�n kontrol edildi�i fonksiyon
    {
        if(oncelik.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void UserJobQueueRun() throws InterruptedException { // Gerekli kuyruklara �nceliklerine g�re atan�rlar


        if(!isEmpty(Priority1))
        {
            if((Dosya.zaman-(Priority1.get(0).getVarisZamani()))>20) // Zaman a��m�na ugramas� gereken proseslerin kontrolu
            {
                Priority1.get(0).setDurum("zaman a��m�");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Priority1.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }

            else if(Priority1.get(0).getProsesZamani()==1) //Alt kuyru�a ge�meden sonlanacak proseslerin y�r�t�lmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.print(Priority1.get(0).getYaziRengi());
                Priority1.get(0).setDurum("sonland�r�l�yor");
                Priority1.get(0).setProsesZamani(Priority1.get(0).getProsesZamani()-1);
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Dosya.kesilenProses=Priority1.get(0);
                Priority1.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }
            else  // Alt kuyru�a ge�ecek proseslerin y�r�t�lmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                Priority1.get(0).setProsesZamani(Priority1.get(0).getProsesZamani());
                Priority1.get(0).setOncelik(Priority1.get(0).getOncelik()+1);
                System.out.print(Priority1.get(0).getYaziRengi());
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Priority2.add(Priority1.get(0));
                Dosya.kesilenProses=Priority1.get(0);
                Priority1.removeFirst();
                Dosya.prosesSonlandiMi=false;


            }



        }
        else if(!isEmpty(Priority2)) // Bo� kontrolu
        {
            if((Dosya.zaman-(Priority2.get(0).getVarisZamani()))>20) // Zaman a��m�na ugramas� gereken proseslerin kontrolu
            {
                Priority2.get(0).setDurum("zaman a��m�");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Priority2.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }

            else if(Priority2.get(0).getProsesZamani()==1) // Bir alt kuyru�a ge�meyecek proseslerin y�r�t�lmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.print(Priority2.get(0).getYaziRengi());
                Priority2.get(0).setDurum("sonland�r�l�yor");
                Priority2.get(0).setProsesZamani(Priority2.get(0).getProsesZamani()-1);
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Dosya.kesilenProses=Priority2.get(0);
                Priority2.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }
            else // Bir alt kuyry�a ge�ecek proseslerin i�lenmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                Dosya.zaman++;
                Priority2.get(0).setProsesZamani(Priority2.get(0).getProsesZamani()-1);
                Priority2.get(0).setOncelik(Priority2.get(0).getOncelik()+1);
                System.out.print(Priority2.get(0).getYaziRengi());
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Priority3.add(Priority2.get(0));
                Dosya.kesilenProses=Priority2.get(0);
                Priority2.removeFirst();
                Dosya.prosesSonlandiMi=false; // alt seviyeye indigi icin



            }


        }
        else if(!isEmpty(Priority3))
        {
            for(int i=sonKalinanIndex;i<Priority3.size();i++) // round robin modunda �al��mas� i�in for d�ng�s� kullan�ld�
            {
                sonKalinanIndex=i;
                if((Dosya.zaman-(Priority3.get(i).getVarisZamani()))>20) // ZAman a��m� kontrol�
                {
                    Dosya.zaman++;
                    Priority3.get(i).setDurum("zaman a��m�");
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    System.out.print(Priority3.get(i).getYaziRengi());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    Priority3.remove(i);
                    Dosya.prosesSonlandiMi=true;
                    if(Priority3.size()==0)
                    {
                        Dosya.userBos=true;
                    }

                }
                else if(Priority3.get(i).getProsesZamani()==1) // Sonland�r�lacak proseslerin i�lenmesi
                {
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    System.out.print(Priority3.get(i).getYaziRengi());
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    System.out.print(Priority3.get(i).getYaziRengi());
                    Priority3.get(i).setDurum("sonland�r�l�yor");
                    Priority3.get(i).setProsesZamani(Priority3.get(i).getProsesZamani()-1);
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    TimeUnit.SECONDS.sleep(1);
                    Dosya.zaman++;
                    Priority3.remove(i);
                    Dosya.prosesSonlandiMi=true;
                }
                else // Normal �ekilde  proseslerin i�lenmesi
                {
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    Dosya.zaman++;
                    Priority3.get(i).prosesBilgileri();
                    Priority3.get(i).setProsesZamani(Priority3.get(i).getProsesZamani()-1);
                    System.out.print(Priority3.get(i).getYaziRengi());
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    TimeUnit.SECONDS.sleep(1);
                    Dosya.prosesSonlandiMi=false;
                    Dosya.kesilenProses=Priority3.get(i);
                }


            }
            sonKalinanIndex=0;
        }
        else
        {
            Dosya.userBos=true;
        }
    }
}
