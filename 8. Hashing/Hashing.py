import random

class HashTable:
    def __init__(self):
        self.size = 211
        self.table = [None] * self.size
        self.prime = 199
        self.count = 0

    def primaryHash(self, key):
        return key % self.size

    def secondaryHash(self, key):
        return self.prime - (key % self.prime)

    def insert(self, key, verbose=True):
        if self.count >= self.size:
            if verbose: print(f"Gagal: Tabel penuh. Tidak dapat memasukkan {key}.")
            return False

        index = self.primaryHash(key)
        step = self.secondaryHash(key)
        i = 0

        # Logika Double Hashing
        # Formula: (hash1 + i * hash2) % size
        # i adalah jumlah percobaan (probe) yang dilakukan
        while i < self.size:
            probe_index = (index + i * step) % self.size
            
            if self.table[probe_index] is None or self.table[probe_index] == "DELETED":
                self.table[probe_index] = key
                self.count += 1
                if verbose and i > 0:
                    print(f"[{key}] Berhasil masuk ke indeks {probe_index} setelah {i} kali collision.")
                return True
                
            elif self.table[probe_index] == key:
                if verbose: print(f"Gagal: Angka {key} sudah ada di tabel.")
                return False
                
            if verbose and i == 0:
                print(f"[{key}] Terjadi collision di indeks {probe_index}. Memulai probing...")
                
            i += 1
        return False

    def search(self, key):
        index = self.primaryHash(key)
        step = self.secondaryHash(key)
        i = 0

        while i < self.size:
            probe_index = (index + i * step) % self.size
            
            if self.table[probe_index] is None:
                return -1
            elif self.table[probe_index] == key:
                return probe_index
                
            print(f"Mencari [{key}]... melewati indeks {probe_index} (isi: {self.table[probe_index]}).")
            i += 1
        return -1

    def delete(self, key):
        index = self.search(key)
        if index != -1:
            self.table[index] = "DELETED"
            self.count -= 1
            print(f"Sukses: Angka {key} dihapus dari indeks {index}.")
            return True
        else:
            print(f"Gagal: Angka {key} tidak ditemukan.")
            return False

    def display(self):
        print("\n--- Seluruh Isi Hash Table ---")
        for i in range(self.size):
            if self.table[i] is not None and self.table[i] != "DELETED":
                print(f"Indeks {i:03d}: {self.table[i]}")
        print(f"Total data saat ini: {self.count} item")
        print("------------------------------\n")

def main():
    ht = HashTable()
    
    print("Menginisialisasi Hash Table dengan 100 angka acak unik...")
    random_numbers = set()
    while len(random_numbers) < 100:
        random_numbers.add(random.randint(1000, 9999))
        
    for num in random_numbers:
        ht.insert(num, verbose=False)
    
    print("Inisialisasi selesai.\n")

    while True:
        print("=== MENU HASH TABLE ===")
        print("1. Tambah Data (Insert)")
        print("2. Hapus Data (Delete)")
        print("3. Cari Data (Search)")
        print("4. Tampilkan Seluruh Data (Display)")
        print("5. Keluar")
        
        choice = input("Pilih menu (1-5): ")
        
        try:
            if choice == '1':
                val = int(input("Masukkan angka untuk ditambahkan: "))
                if ht.insert(val, verbose=True):
                    print("Operasi insert selesai.")
            elif choice == '2':
                val = int(input("Masukkan angka untuk dihapus: "))
                ht.delete(val)
            elif choice == '3':
                val = int(input("Masukkan angka yang dicari: "))
                idx = ht.search(val)
                if idx != -1:
                    print(f"Ditemukan: Angka {val} berada di indeks {idx}.")
                else:
                    print(f"Gagal: Angka {val} tidak ditemukan.")
            elif choice == '4':
                ht.display()
            elif choice == '5':
                print("Keluar dari program.")
                break
            else:
                print("Pilihan tidak valid.")
        except ValueError:
            print("Error: Masukkan angka yang valid.")

if __name__ == "__main__":
    main()