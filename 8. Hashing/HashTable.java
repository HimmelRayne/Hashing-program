import java.util.Random;
import java.util.Scanner;

public class HashTable {
    private Integer[] table;
    private int size;
    private int prime;
    private int count;
    private final Integer DELETED = -1;

    public HashTable() {
        this.size = 211;
        this.table = new Integer[this.size];
        this.prime = 199;
        this.count = 0;
    }

    private int primaryHash(int key) {
        return key % size;
    }

    private int secondaryHash(int key) {
        return prime - (key % prime);
    }

    public boolean insert(int key, boolean verbose) {
        if (count >= size) {
            if (verbose) System.out.println("Gagal: Tabel penuh. Tidak dapat memasukkan " + key);
            return false;
        }

        int index = primaryHash(key);
        int step = secondaryHash(key);
        int i = 0;

        while (i < size) {
            int probeIndex = (index + i * step) % size;

            if (table[probeIndex] == null || table[probeIndex].equals(DELETED)) {
                table[probeIndex] = key;
                count++;
                if (verbose && i > 0) {
                    System.out.println("[" + key + "] Berhasil dimasukkan ke indeks " + probeIndex + " setelah " + i + " kali collision.");
                }
                return true;
            } else if (table[probeIndex] == key) {
                if (verbose) System.out.println("Gagal: Angka " + key + " sudah ada di dalam tabel.");
                return false;
            }

            if (verbose && i == 0) {
                System.out.println("[" + key + "] Terjadi collision di indeks " + probeIndex + ". Memulai proses probing...");
            }

            i++;
        }

        return false;
    }

    public int search(int key) {
        int index = primaryHash(key);
        int step = secondaryHash(key);
        int i = 0;

        while (i < size) {
            int probeIndex = (index + i * step) % size;

            if (table[probeIndex] == null) {
                return -1;
            } else if (table[probeIndex] == key) {
                return probeIndex;
            }

            System.out.println("Mencari [" + key + "]... melewati indeks " + probeIndex + " (berisi " + table[probeIndex] + ").");
            i++;
        }

        return -1;
    }

    public boolean delete(int key) {
        int index = search(key);
        if (index != -1) {
            table[index] = DELETED;
            count--;
            System.out.println("Sukses: Angka " + key + " dihapus dari indeks " + index + ".");
            return true;
        } else {
            System.out.println("Gagal: Angka " + key + " tidak ditemukan.");
            return false;
        }
    }

    public void display() {
        System.out.println("\n--- Seluruh Isi Hash Table ---");
        for (int i = 0; i < size; i++) {
            if (table[i] != null && !table[i].equals(DELETED)) {
                System.out.printf("Indeks %03d: %d\n", i, table[i]);
            }
        }
        System.out.println("Total data saat ini: " + count + " item");
        System.out.println("------------------------------\n");
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menginisialisasi Hash Table dengan 100 angka acak unik...");
        
        while (ht.count < 100) {
            int randomNum = 1000 + rand.nextInt(9000); // Menghasilkan angka 1000 - 9999
            ht.insert(randomNum, false);
        }

        System.out.println("Inisialisasi selesai.\n");

        while (true) {
            System.out.println("=== MENU HASH TABLE ===");
            System.out.println("1. Tambah Data (Insert)");
            System.out.println("2. Hapus Data (Delete)");
            System.out.println("3. Cari Data (Search)");
            System.out.println("4. Tampilkan Seluruh Data (Display)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Masukkan angka untuk ditambahkan: ");
                        int valToInsert = Integer.parseInt(scanner.nextLine());
                        if (ht.insert(valToInsert, true)) {
                            System.out.println("Sukses menambahkan data baru.");
                        }
                        break;
                    case "2":
                        System.out.print("Masukkan angka untuk dihapus: ");
                        int valToDelete = Integer.parseInt(scanner.nextLine());
                        ht.delete(valToDelete);
                        break;
                    case "3":
                        System.out.print("Masukkan angka yang dicari: ");
                        int valToSearch = Integer.parseInt(scanner.nextLine());
                        int idx = ht.search(valToSearch);
                        if (idx != -1) {
                            System.out.println("Ditemukan: Angka " + valToSearch + " berada di indeks " + idx + ".");
                        } else {
                            System.out.println("Pencarian Gagal: Angka " + valToSearch + " tidak ada di tabel.");
                        }
                        break;
                    case "4":
                        ht.display();
                        break;
                    case "5":
                        System.out.println("Terima kasih telah menggunakan program ini.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1 hingga 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error: Harap masukkan nilai numerik yang valid.");
            }
        }
    }
}