import hashlib
import math
import tkinter as tk


class MerkleTree:
    def __init__(self):
        self.leaves = []
        self.root = None

    def add_data(self, data):
        leaf = hashlib.sha256(data.encode()).hexdigest()
        self.leaves.append(leaf)

    def build_tree(self):
        nodes = self.leaves
        while len(nodes) > 1:
            new_nodes = []
            for i in range(0, len(nodes), 2):
                node1 = nodes[i]
                if i + 1 < len(nodes):
                    node2 = nodes[i + 1]
                else:
                    node2 = node1
                parent = hashlib.sha256((node1 + node2).encode()).hexdigest()
                new_nodes.append(parent)
            nodes = new_nodes
        self.root = nodes[0]

    def get_root(self):
        return self.root

    def print_tree(self):
        print("Merkle Tree:")
        for i, node in enumerate(self.leaves):
            print(f"Level 0, Leaf {i + 1}: {node}")
        level = 1
        nodes = self.leaves
        while len(nodes) > 1:
            new_nodes = []
            for i in range(0, len(nodes), 2):
                node1 = nodes[i]
                if i + 1 < len(nodes):
                    node2 = nodes[i + 1]
                else:
                    node2 = node1
                parent = hashlib.sha256((node1 + node2).encode()).hexdigest()
                new_nodes.append(parent)
                print(f"Level {level}, Parent {len(new_nodes)}: {parent}")
            nodes = new_nodes
            level += 1


class BloomFilter:
    def __init__(self, capacity, error_rate):
        self.capacity = capacity
        self.error_rate = error_rate
        self.bit_array_size = self.calculate_bit_array_size()
        self.num_hash_functions = self.calculate_num_hash_functions()
        self.bit_array = [False] * self.bit_array_size

    def calculate_bit_array_size(self):
        m = -1 * (self.capacity * math.log(self.error_rate)) / (math.log(2) ** 2)
        return int(m)

    def calculate_num_hash_functions(self):
        k = (self.bit_array_size / self.capacity) * math.log(2)
        return int(k)

    def add(self, data):
        for i in range(self.num_hash_functions):
            hash_value = hashlib.sha256((data + str(i)).encode()).hexdigest()
            index = int(hash_value, 16) % self.bit_array_size
            self.bit_array[index] = True

    def __contains__(self, data):
        for i in range(self.num_hash_functions):
            hash_value = hashlib.sha256((data + str(i)).encode()).hexdigest()
            index = int(hash_value, 16) % self.bit_array_size
            if not self.bit_array[index]:
                return False
        return True


class HealthCareSystem:
    def __init__(self):
        self.merkle_tree = MerkleTree()
        self.bloom_filter = BloomFilter(capacity=1000, error_rate=0.001)
        self.records = {}

    def add_health_data(self, data):
        medical_record = data['medical_record']
        self.merkle_tree.add_data(medical_record)
        self.bloom_filter.add(medical_record)
        self.records[medical_record] = data
        print(f"Encrypted value of '{medical_record}': {hashlib.sha256(medical_record.encode()).hexdigest()}")
        self.merkle_tree.print_tree()

    def search_health_data(self, data):
        if data in self.bloom_filter:
            if self.verify_data_in_merkle_tree(data):
                return self.records[data]
            else:
                return "Data not found in the healthcare system."
        else:
            return "Data not found in the healthcare system."

    def verify_data_in_merkle_tree(self, data):
        leaf = hashlib.sha256(data.encode()).hexdigest()
        return leaf in self.merkle_tree.leaves


class HealthCareSystemGUI:
    def __init__(self, healthcare_system):
        self.healthcare_system = healthcare_system
        self.root = tk.Tk()
        self.root.title("Healthcare System")
        self.medical_record_entry = None
        self.search_entry = None
        self.search_result_text = None

        self.create_widgets()

    def create_widgets(self):

        tk.Label(self.root, text="Medical Record:").grid(row=0, column=0, sticky="e")
        self.medical_record_entry = tk.Entry(self.root)
        self.medical_record_entry.grid(row=0, column=1)


        tk.Label(self.root, text="Name:").grid(row=1, column=0, sticky="e")
        self.name_entry = tk.Entry(self.root)
        self.name_entry.grid(row=1, column=1)

        tk.Label(self.root, text="Age:").grid(row=2, column=0, sticky="e")
        self.age_entry = tk.Entry(self.root)
        self.age_entry.grid(row=2, column=1)

        tk.Label(self.root, text="Sugar Level:").grid(row=3, column=0, sticky="e")
        self.sugar_level_entry = tk.Entry(self.root)
        self.sugar_level_entry.grid(row=3, column=1)

        tk.Label(self.root, text="Blood Pressure:").grid(row=4, column=0, sticky="e")
        self.blood_pressure_entry = tk.Entry(self.root)
        self.blood_pressure_entry.grid(row=4, column=1)

        add_button = tk.Button(self.root, text="Add Health Data", command=self.add_health_data)
        add_button.grid(row=5, column=0, columnspan=2, pady=10)


        tk.Label(self.root, text="Search Medical Record:").grid(row=6, column=0, sticky="e")
        self.search_entry = tk.Entry(self.root)
        self.search_entry.grid(row=6, column=1)

        search_button = tk.Button(self.root, text="Search", command=self.search_health_data)
        search_button.grid(row=7, column=0, columnspan=2, pady=10)

        self.merkle_tree_label = tk.Label(self.root, text="Merkle Tree:")
        self.merkle_tree_label.grid(row=8, column=0, columnspan=2, pady=10)

        self.search_result_text = tk.Text(self.root, height=5, width=40)
        self.search_result_text.grid(row=9, column=0, columnspan=2, pady=10)

    def add_health_data(self):
        medical_record = self.medical_record_entry.get()
        name = self.name_entry.get()
        age = int(self.age_entry.get())
        sugar_level = float(self.sugar_level_entry.get())
        blood_pressure = self.blood_pressure_entry.get()
        data = {
            'medical_record': medical_record,
            'name': name,
            'age': age,
            'sugar_level': sugar_level,
            'blood_pressure': blood_pressure
        }

        self.healthcare_system.add_health_data(data)
        print(f"Encrypted value of '{medical_record}': {hashlib.sha256(medical_record.encode()).hexdigest()}")
        self.update_merkle_tree()
        self.clear_entry_fields()

    def search_health_data(self):
        search_record = self.search_entry.get()
        result = self.healthcare_system.search_health_data(search_record)
        if isinstance(result, dict):
            self.update_search_result(f"Medical Record Found:\n"
                                      f"Medical Record: {result['medical_record']}\n"
                                      f"Name: {result['name']}\n"
                                      f"Age: {result['age']}\n"
                                      f"Sugar Level: {result['sugar_level']}\n"
                                      f"Blood Pressure: {result['blood_pressure']}")
        else:
            self.update_search_result(result)

    def update_search_result(self, result):
        self.search_result_text.delete('1.0', tk.END)
        self.search_result_text.insert(tk.END, result)

    def clear_entry_fields(self):
        self.medical_record_entry.delete(0, tk.END)
        self.name_entry.delete(0, tk.END)
        self.age_entry.delete(0, tk.END)
        self.sugar_level_entry.delete(0, tk.END)
        self.blood_pressure_entry.delete(0, tk.END)

    def update_merkle_tree(self):
        self.healthcare_system.merkle_tree.build_tree()
        self.merkle_tree_label.config(text="Merkle Tree:\n" + self.get_merkle_tree_string())

    def get_merkle_tree_string(self):
        tree_string = ""
        for i, node in enumerate(self.healthcare_system.merkle_tree.leaves):
            tree_string += f"Level 0, Leaf {i + 1}: {node}\n"
        level = 1
        nodes = self.healthcare_system.merkle_tree.leaves
        while len(nodes) > 1:
            new_nodes = []
            for i in range(0, len(nodes), 2):
                node1 = nodes[i]
                if i + 1 < len(nodes):
                    node2 = nodes[i + 1]
                else:
                    node2 = node1
                parent = hashlib.sha256((node1 + node2).encode()).hexdigest()
                new_nodes.append(parent)
                tree_string += f"Level {level}, Parent {len(new_nodes)}: {parent}\n"
            nodes = new_nodes
            level += 1
        return tree_string

    def run(self):
        self.root.mainloop()


healthcare_system = HealthCareSystem()

gui = HealthCareSystemGUI(healthcare_system)

gui.run()
