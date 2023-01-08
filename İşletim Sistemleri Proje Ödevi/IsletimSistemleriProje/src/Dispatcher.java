import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dispatcher //Scheduler'ýn seçtiði process icin cpuyu ayarlar
{
    public int zaman=0;
    public RealTimeQueue realTimeQueue;
    public UserJobQueue userJobQueue;


    public Dispatcher( ) // Kullanilan kuyruk yapilari initialize ediliyor.
    {
        this.realTimeQueue=new RealTimeQueue();
        this.userJobQueue=new UserJobQueue();
    }

    public void DispatcherControl(Proses proses) // Bu fonksiyonun amacý Scheduler tarafýndan
    {                                            // gönderilen proseslerin ayrýmýnýn yapýlmasý.
        if(proses.getOncelik()==0)
        {
            //realtime a ekle
            realTimeQueue.RealTimeQueueAdd(proses);

        }
        else
        {
            //kullanýcý proserse ekle
            userJobQueue.UserJobQueueAdd(proses);
        }
    }

    public void DispatcherRun() throws InterruptedException {
        //ilk once realtime queue da proses var mý kontrol et
        if(!realTimeQueue.isEmpty())
        {
            // eger var ise bir sanþiye calýstýr
            realTimeQueue.RealTimeQueueRun();
            ProcessBuilder processBuilder = new ProcessBuilder();
            Dosya.sonCalisan="real"; // Son calisan prosesin turunu kaydet

        }
        else
        {
            //yoksa kullanýcý kuyruðunu bir saniye calistir
            userJobQueue.UserJobQueueRun();
            ProcessBuilder processBuilder = new ProcessBuilder();
            Dosya.sonCalisan="user"; // Son calisan prosesin turunu kaydet

        }

    }


}
