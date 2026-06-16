import random

class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [[] for _ in range(size)]  # Separate Chaining

    # Fungsi hash
    def hash_function(self, key):
        return key % self.size

    # Input data
    def insert(self, key):
        index = self.hash_function(key)

        # Hindari data duplikat
        if key not in self.table[index]:
            self.table[index].append(key)
            print(f"Data {key} berhasil ditambahkan ke index {index}")
        else:
            print(f"Data {key} sudah ada")

    # Hapus data
    def delete(self, key):
        index = self.hash_function(key)

        if key in self.table[index]:
            self.table[index].remove(key)
            print(f"Data {key} berhasil dihapus")
        else:
            print(f"Data {key} tidak ditemukan")

    # Cari data
    def search(self, key):
        index = self.hash_function(key)

        if key in self.table[index]:
            print(f"Data {key} ditemukan pada index {index}")
        else:
            print(f"Data {key} tidak ditemukan")

    # Tampilkan hash table
    def display(self):
        print("\n=== HASH TABLE ===")
        for i in range(self.size):
            print(f"Index {i}: {self.table[i]}")


# =========================
# PROGRAM UTAMA
# =========================

# Ukuran hash table
size = 20

# Membuat objek hash table
ht = HashTable(size)

# Generate 100 angka random unik
random_numbers = random.sample(range(1, 1000), 100)

# Input otomatis ke hash table
for number in random_numbers:
    ht.insert(number)

print("\n100 data random berhasil dimasukkan ke hash table.")

# Menu program
while True:
    print("\n===== MENU =====")
    print("1. INPUT DATA")
    print("2. HAPUS DATA")
    print("3. CARI DATA")
    print("4. TAMPILKAN HASH TABLE")
    print("5. EXIT")

    pilihan = input("Pilih menu: ")

    if pilihan == "1":
        data = int(input("Masukkan data: "))
        ht.insert(data)

    elif pilihan == "2":
        data = int(input("Masukkan data yang akan dihapus: "))
        ht.delete(data)

    elif pilihan == "3":
        data = int(input("Masukkan data yang dicari: "))
        ht.search(data)

    elif pilihan == "4":
        ht.display()

    elif pilihan == "5":
        print("Program selesai.")
        break

    else:
        print("Pilihan tidak valid!")