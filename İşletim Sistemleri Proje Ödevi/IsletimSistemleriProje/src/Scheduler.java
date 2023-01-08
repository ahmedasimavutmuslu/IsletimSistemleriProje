import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

public class Scheduler //scheduler prosesler arasýndan secim yapacak
{
   private Proses eklenecekProses;
   public Dispatcher dispatcher;
   public static List<Proses> gercekZamanliProses;
   public static List<Proses> kullaniciProses;



   public Scheduler(Dispatcher dispatcher) // Dispatcher nesnesini parametre alarak initiialize ediyoruz.
   {                                       // Bunun nedeni prosesleri parametre olarak Dispatchera gönderecek olmamýz.
      this.dispatcher=dispatcher;
      this.eklenecekProses=null;
      gercekZamanliProses = new ArrayList<>();
      kullaniciProses = new ArrayList<>();

   }

   public void dispatcheraYolla(Proses proses) // Prosesler gerekli kuyruklara eklenmek üzere dispatchera yollanýyor.
   {
      dispatcher.DispatcherControl(proses);

   }

   public List<Proses> gercekZamanliProsesDondur()
   {
      return gercekZamanliProses;
   }

    public List<Proses> kullaniciProsesDondur()
    {
       return kullaniciProses;
    }

}
