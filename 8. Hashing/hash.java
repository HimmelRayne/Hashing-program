import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class hashTable {

    // Class Hash Table
    static class HashTable {
        private int size;
        private LinkedList<Integer>[] table;

        // Constructor
        public HashTable(int size) {
            this.size = size;
            table = new LinkedList[size];

            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        // Fungsi hash
        public int hashFunction(int key) {
            return key % size;
        }

        // Input data
        public void insert(int key) {
            int index = hashFunction(key);

            // Hindari data duplikat
            if (!table[index].contains(key)) {
                table[index].add(key);
                System.out.println("Data " + key + " berhasil ditambahkan ke index " + index);
            } else {
                System.out.println("Data sudah ada!");
            }
        }

        // Hapus data
        public void delete(int key) {
            int index = hashFunction(key);

            if (table[index].remove((Integer) key)) {
                System.out.println("Data " + key + " berhasil dihapus");
            } else {
                System.out.println("Data tidak ditemukan");
            }
        }

        // Cari data
        public void search(int key) {
            int index = hashFunction(key);

            if (table[index].contains(key)) {
                System.out.println("Data " + key + " ditemukan pada index " + index);
            } else {
                System.out.println("Data tidak ditemukan");
            }
        }

        // Tampilkan hash table
        public void display() {
            System.out.println("\n=== HASH TABLE ===");

            for (int i = 0; i < size; i++) {
                System.out.print("Index " + i + " : ");

                for (Integer data : table[i]) {
                    System.out.print(data + " -> ");
                }

                System.out.println("null");
            }
        }
    }

    // Program utama
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Ukuran hash table
        int size = 20;

        // Membuat objek hash table
        HashTable ht = new HashTable(size);

        // Generate 100 angka random unik
        Random rand = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<>();

        while (randomNumbers.size() < 100) {
            int number = rand.nextInt(1000) + 1;

            if (!randomNumbers.contains(number)) {
                randomNumbers.add(number);
                ht.insert(number);
            }
        }

        System.out.println("\n100 data random berhasil dimasukkan.");

        // Menu program
        int pilihan;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. INPUT DATA");
            System.out.println("2. HAPUS DATA");
            System.out.println("3. CARI DATA");
            System.out.println("4. TAMPILKAN HASH TABLE");
            System.out.println("5. EXIT");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();

            switch (pilihan) {

                case 1:
                    System.out.print("Masukkan data: ");
                    int dataInput = input.nextInt();
                    ht.insert(dataInput);
                    break;

                case 2:
                    System.out.print("Masukkan data yang akan dihapus: ");
                    int dataHapus = input.nextInt();
                    ht.delete(dataHapus);
                    break;

                case 3:
                    System.out.print("Masukkan data yang dicari: ");
                    int dataCari = input.nextInt();
                    ht.search(dataCari);
                    break;

                case 4:
                    ht.display();
                    break;

                case 5:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 5);

        input.close();
    }
}
