import java.util.Scanner;

public class Main
{
    public static void main(String[] Args)
    {
        // Main fonksiyonu�nu oldugunca basit tutmaya �al��t�m.
        // �ki adet se�enek sunup buna g�re program�n �al��mas�n�n devam� sa�land�.
        Scanner scanner=new Scanner(System.in);
        String cevap;
        System.out.println("//////////////////////////");
        System.out.println("//  Giri�.txt Dosyan�z  //");
        System.out.println("//     Bulunuyor Mu?    //");
        System.out.println("//         E/H          //");
        System.out.println("//////////////////////////");
        cevap=scanner.nextLine();
        if(cevap.equals("e")||cevap.equals("E"))
        {
            System.out.println("//////////////////////////");
            System.out.println("// Giri�.txt Dosyan�z�n //");
            System.out.println("//    Dizinini Giriniz  //");
            System.out.println("//////////////////////////");
            cevap=scanner.nextLine();

            System.out.println("//////////////////////////");
            System.out.println("//     Girilen Dizin    //");
            System.out.println("//     ile Program      //");
            System.out.println("//     �al��t�r�lacak   //");
            System.out.println("//////////////////////////");
            Dosya.Oku(cevap);


        }
        else
        {
            System.out.println("//////////////////////////");
            System.out.println("//  Varsay�lan Ayarlar  //");
            System.out.println("//     ile Program      //");
            System.out.println("//     �al��t�r�lacak   //");
            System.out.println("//////////////////////////");
            Dosya.Oku();

        }

    }
}
