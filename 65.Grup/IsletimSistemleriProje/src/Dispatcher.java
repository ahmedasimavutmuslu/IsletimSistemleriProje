import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void DispatcherRun() throws InterruptedException, IOException {
        //ilk once realtime queue da proses var mý kontrol et
    	if(!realTimeQueue.isEmpty())
        {
            // eger var ise bir sanþiye calýstýr
            Process process = new Process() {
                @Override
                public OutputStream getOutputStream() {
                    return null;
                }

                @Override
                public InputStream getInputStream() {
                    return null;
                }

                @Override
                public InputStream getErrorStream() {
                    return null;
                }

                @Override
                public int waitFor() throws InterruptedException {
                    return 0;
                }

                @Override
                public int exitValue() {
                    return 0;
                }

                @Override
                public void destroy() {

                }
            };

            realTimeQueue.RealTimeQueueRun();
            Dosya.sonCalisan="real"; // Son calisan prosesin turunu kaydet
            process.destroy();

        }
        else
        {
            //yoksa kullanýcý kuyruðunu bir saniye calistir
            Process process=new Process() {
                @Override
                public OutputStream getOutputStream() {
                    return null;
                }

                @Override
                public InputStream getInputStream() {
                    return null;
                }

                @Override
                public InputStream getErrorStream() {
                    return null;
                }

                @Override
                public int waitFor() throws InterruptedException {
                    return 0;
                }

                @Override
                public int exitValue() {
                    return 0;
                }

                @Override
                public void destroy() {

                }
            };
            userJobQueue.UserJobQueueRun();
            process.destroy();

            Dosya.sonCalisan="user"; // Son calisan prosesin turunu kaydet

        }
    }


}
