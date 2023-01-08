import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//  Kullanýcý Prosesleri için Gerçek Zamanlý Kuyrukta olduðu gibi yine LinkedList veri yapýsý kullanýyoruz.
// Ancak farklý olarak öncekliðe göre üç farklý seviye bulunuyor. Bunlar için ayrý ayrý kuyruklar kullanýldý.
public class UserJobQueue
{
    private LinkedList<Proses> Priority1;
    private LinkedList<Proses> Priority2;
    private LinkedList<Proses> Priority3;
    private int sonKalinanIndex=0;
    public Proses sonIslenenUserProses;


    public UserJobQueue() // Farklý öncelik seviyelerindeki kuyruklar initialize ediliyor.
    {
        this.Priority1=new LinkedList<>();
        this.Priority2=new LinkedList<>();
        this.Priority3=new LinkedList<>();
    }
    public Proses sonProsesDondur(Proses proses) // Son iþlenen prosesi dondurur;
    {
        return proses;
    }

    public void UserJobQueueAdd(Proses proses) //oncelige gore kuyruklara alýnýyor
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
    public boolean isEmpty(LinkedList<Proses> oncelik) //Kuyruklarýn boþ olup olmamasýnýn kontrol edildiði fonksiyon
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

    public void UserJobQueueRun() throws InterruptedException { // Gerekli kuyruklara önceliklerine göre atanýrlar


        if(!isEmpty(Priority1))
        {
            if((Dosya.zaman-(Priority1.get(0).getVarisZamani()))>20) // Zaman aþýmýna ugramasý gereken proseslerin kontrolu
            {
                Priority1.get(0).setDurum("zaman aþýmý");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Priority1.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }

            else if(Priority1.get(0).getProsesZamani()==1) //Alt kuyruða geçmeden sonlanacak proseslerin yürütülmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.print(Priority1.get(0).getYaziRengi());
                Priority1.get(0).setDurum("sonlandýrýlýyor");
                Priority1.get(0).setProsesZamani(Priority1.get(0).getProsesZamani()-1);
                System.out.println(Priority1.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Dosya.kesilenProses=Priority1.get(0);
                Priority1.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }
            else  // Alt kuyruða geçecek proseslerin yürütülmesi
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
        else if(!isEmpty(Priority2)) // Boþ kontrolu
        {
            if((Dosya.zaman-(Priority2.get(0).getVarisZamani()))>20) // Zaman aþýmýna ugramasý gereken proseslerin kontrolu
            {
                Priority2.get(0).setDurum("zaman aþýmý");
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                Priority2.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }

            else if(Priority2.get(0).getProsesZamani()==1) // Bir alt kuyruða geçmeyecek proseslerin yürütülmesi
            {
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("Zaman:"+Dosya.zaman+"  ");
                System.out.print(Priority2.get(0).getYaziRengi());
                Priority2.get(0).setDurum("sonlandýrýlýyor");
                Priority2.get(0).setProsesZamani(Priority2.get(0).getProsesZamani()-1);
                System.out.println(Priority2.get(0).prosesBilgileri());
                System.out.print(Color.RESET);
                TimeUnit.SECONDS.sleep(1);
                Dosya.kesilenProses=Priority2.get(0);
                Priority2.removeFirst();
                Dosya.prosesSonlandiMi=true;

            }
            else // Bir alt kuyryða geçecek proseslerin iþlenmesi
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
            for(int i=sonKalinanIndex;i<Priority3.size();i++) // round robin modunda çalýþmasý için for döngüsü kullanýldý
            {
                sonKalinanIndex=i;
                if((Dosya.zaman-(Priority3.get(i).getVarisZamani()))>20) // ZAman aþýmý kontrolü
                {
                    Dosya.zaman++;
                    Priority3.get(i).setDurum("zaman aþýmý");
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
                else if(Priority3.get(i).getProsesZamani()==1) // Sonlandýrýlacak proseslerin iþlenmesi
                {
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    System.out.print(Priority3.get(i).getYaziRengi());
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print("Zaman:"+Dosya.zaman+"  ");
                    System.out.print(Priority3.get(i).getYaziRengi());
                    Priority3.get(i).setDurum("sonlandýrýlýyor");
                    Priority3.get(i).setProsesZamani(Priority3.get(i).getProsesZamani()-1);
                    System.out.println(Priority3.get(i).prosesBilgileri());
                    System.out.print(Color.RESET);
                    TimeUnit.SECONDS.sleep(1);
                    Dosya.zaman++;
                    Priority3.remove(i);
                    Dosya.prosesSonlandiMi=true;
                }
                else // Normal þekilde  proseslerin iþlenmesi
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
