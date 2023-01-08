import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dispatcher //Scheduler'�n se�ti�i process icin cpuyu ayarlar
{
    public int zaman=0;
    public RealTimeQueue realTimeQueue;
    public UserJobQueue userJobQueue;


    public Dispatcher( ) // Kullanilan kuyruk yapilari initialize ediliyor.
    {
        this.realTimeQueue=new RealTimeQueue();
        this.userJobQueue=new UserJobQueue();
    }

    public void DispatcherControl(Proses proses) // Bu fonksiyonun amac� Scheduler taraf�ndan
    {                                            // g�nderilen proseslerin ayr�m�n�n yap�lmas�.
        if(proses.getOncelik()==0)
        {
            //realtime a ekle
            realTimeQueue.RealTimeQueueAdd(proses);

        }
        else
        {
            //kullan�c� proserse ekle
            userJobQueue.UserJobQueueAdd(proses);
        }
    }

    public void DispatcherRun() throws InterruptedException {
        //ilk once realtime queue da proses var m� kontrol et
        if(!realTimeQueue.isEmpty())
        {
            // eger var ise bir san�iye cal�st�r
            realTimeQueue.RealTimeQueueRun();
            ProcessBuilder processBuilder = new ProcessBuilder();
            Dosya.sonCalisan="real"; // Son calisan prosesin turunu kaydet

        }
        else
        {
            //yoksa kullan�c� kuyru�unu bir saniye calistir
            userJobQueue.UserJobQueueRun();
            ProcessBuilder processBuilder = new ProcessBuilder();
            Dosya.sonCalisan="user"; // Son calisan prosesin turunu kaydet

        }

    }


}
