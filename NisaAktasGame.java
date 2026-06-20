/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsproje;

/**
 *
 * @author nisaaktas
 */
public class NisaAktasGame {

    public NisaAktasNode<Integer> head = null;
    Integer nextValue;
    //senaryoda bulunan dusecek tas değerleri 
    int[] senaryo = {2, 2, 4, 2, 4, 2, 4, 8, 8, 32, 2, 64, 16, 64, 32, 16, 16, 32, 64, 8, 4, 2, 2, 2, 64, 32, 16, 8, 8, 4, 8};
    int senaryoIndex = 0;

    public NisaAktasGame() {
        //oyun basladıgında ılk tası degerını belırler 
        this.nextValue = generateNextValue();
    }

    //taşu ılgılı sutuna dusurmeye calısıyor next value varsa gelen sutunun en altına tası yolluyor dolu olmayan en alt satırı bulup tası olusturuyor lıstenı  headı yapıyır komsularını baglayacak metodu cagırıyor 
    //yenı taşın belırlenen sutuna gelmesini saglayan metod 
    public void dropTile(int col) {
        //dusecek tas yoksa ıslem yapma 
        if (nextValue == null) {  //düşecek tas yok 
            return;
        }

        //yenı tasın eklenecegısatır bılgısı 
        int row = 0;
        //ilgili sutunda en alttakı tası bulacak 
        NisaAktasNode<Integer> bottom = findBottom(col);

        //en alttakı tas doluysa ustune onun ustune oradan row bul 
        if (bottom != null) {
            row = bottom.row + 1;
        }

        if (row > 6) { //sutun dolduysa
            return;
        }

        //yenı tas olusturup head yap. 
        NisaAktasNode<Integer> newNode = new NisaAktasNode<>(nextValue, row, col);
        if (head != null) { //head bos değilse yenı node head. 
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;

        connectNeighbors(newNode); //yenı nodu komsularına bagla  bagla 

    }

    //yenı olusan tası 
    //Ynei eklenen tasın ızgara uzerındekı komsularını bulan ve baglayan metod 
    private void connectNeighbors(NisaAktasNode<Integer> node) {

        node.up = node.down = null; //butun baglantılrı sıfırla mergede falan ıslem olunca hata olmasın dıye 

        NisaAktasNode<Integer> temp = head; //butun node ları gezıyor 
        while (temp != null) {  //temp bos ya da kendısı mı dıye kontrol 
            if (temp != node) { //kendısı ıse temp=temp.next yapıyor 

                if (temp.col == node.col) { //aynı sutunda ıse yapılan kontroller 
                    if (temp.row == node.row - 1) { //temp node 1  ustune ise baglansınlar 
                        node.up = temp; //node up ını temp yap 
                        temp.down = node;
                      
                    }

                }

            }
            temp = temp.next;
        }
    }

    //alt akta aynı degerde olan tasları bırlestıren metod 
    protected boolean merge(int col) {

        //temp değişkenını sutundakı e ust degere atar 
        NisaAktasNode<Integer> temp = findTop(col);

        while (temp != null && temp.down != null) {
            if (temp.value.equals(temp.down.value)) {
                temp.value *= 2;
                NisaAktasNode<Integer> del = temp.down;

//komsuluk ıcın guncelleme 
                temp.down = del.down;
                if (del.down != null) {
                    del.down.up = temp;
                }

//global zıncırı guncellıyor 
                if (del.prev != null) {
                    del.prev.next = del.next;
                }
                if (del.next != null) {
                    del.next.prev = del.prev;
                }

                if (del == head) {
                    head = del.next;
                }

                return true;

            }
            temp = temp.down;
        }
        return false;

    }

    //yenı gelecek sayıyı belırler 
    protected Integer generateNextValue() {
        if (senaryoIndex < senaryo.length) {
            return senaryo[senaryoIndex++];
        }
        return null;
    }

    //en buyuk row degerıne sahıp olanı bottom yapar 
    private NisaAktasNode<Integer> findBottom(int col) {
        NisaAktasNode<Integer> temp = head;
        NisaAktasNode<Integer> last = null;
        while (temp != null) {
            if (temp.col == col) {
                if (last == null || temp.row > last.row) {
                    last = temp;
                }

            }
            temp = temp.next;
        }
        return last;
    }
//en kucuk row degerıne sahıp olanı top yapar 

    private NisaAktasNode<Integer> findTop(int col) {
        NisaAktasNode<Integer> temp = head;
        NisaAktasNode<Integer> top = null;

        while (temp != null) {
            if (temp.col == col) {
                if (top == null || temp.row < top.row) {
                    top = temp;
                }

            }
            temp = temp.next;
        }
        return top;
    }

    public boolean isGameOver() {
        return nextValue == null; // || checkGameOverRecursive(0);

    }

    public void printGrid() {
        System.out.println("\n     C0      C1      C2      C3      C4");

        for (int i = 6; i >= 0; i--) {
            // Satır numarasını başa ekleyelim (R6, R5...)
            System.out.print("R" + i + " | ");
            for (int j = 0; j <= 4; j++) {
                NisaAktasNode<Integer> found = null;
                NisaAktasNode<Integer> temp = head;

                while (temp != null) {
                    if (temp.row == i && temp.col == j) {
                        found = temp;
                        break;
                    }
                    temp = temp.next;
                }
                if (found != null) {
                    System.out.printf("%-8d", found.value);
                } else {
                    System.out.printf("%-8s", ".");
                }
            }
            System.out.println();
        }

        System.out.println("------------------------------");

    }
}
