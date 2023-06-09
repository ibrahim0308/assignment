import java.util.Iterator;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

class EquipmentLibrary {
    private LinkedList<Equipment> equipmentList;
    private Equipment lastUpdatedEquipment;
    private Equipment lastConnectedEquipment;
    private LinkedList<Equipment> updatedEquipmentList;

    // Constructor
    public EquipmentLibrary() {
        equipmentList = new LinkedList<>();
        updatedEquipmentList = new LinkedList<>();
    }

    // Add Equipment
    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
        System.out.println("Equipment added successfully.");
    }

    // Delete Equipment
    public void deleteEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the serial number of the equipment to delete:");
        String serialNumber = scanner.nextLine();

        boolean found = false;
        Iterator<Equipment> iterator = equipmentList.iterator();
        while (iterator.hasNext()) {
            Equipment equipment = iterator.next();
            if (equipment.getSerialNumber().equals(serialNumber)) {
                iterator.remove();
                found = true;
                System.out.println("Equipment deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Equipment with the specified serial number not found.");
        }
    }

    // Update Equipment
    public void updateEquipment(String serialNumber, Equipment updatedEquipment) {
        boolean found = false;
        for (int i = 0; i < equipmentList.size(); i++) {
            Equipment equipment = equipmentList.get(i);
            if (equipment.getSerialNumber().equals(serialNumber)) {
                equipmentList.set(i, updatedEquipment);
                found = true;
                lastUpdatedEquipment = updatedEquipment;
                updatedEquipmentList.add(updatedEquipment);
                System.out.println("Equipment updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Equipment with the specified serial number not found.");
        }
    }

    // Find Equipment
    public void findEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the serial number of the equipment to find:");
        String serialNumber = scanner.nextLine();

        boolean found = false;
        for (Equipment equipment : equipmentList) {
            if (equipment.getSerialNumber().equals(serialNumber)) {
                System.out.println("Equipment found:");
                System.out.println(equipment);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Equipment with this serial number not found.");
        }
    }

    // Display Equipment
    public void displayEquipment() {
        System.out.println("Equipment List:");
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }
    // Update Equipment
    public void updateEquipment(String serialNumber, Scanner scanner) {
        boolean found = false;
        Equipment updatedEquipment = null; // Initialize to null

        for (int i = 0; i < equipmentList.size(); i++) {
            Equipment equipment = equipmentList.get(i);
            if (equipment.getSerialNumber().equals(serialNumber)) {
                System.out.println("Enter the new name:");
                String newName = scanner.nextLine();

                System.out.println("Enter the new manufacturer:");
                String newManufacturer = scanner.nextLine();

                System.out.println("Enter the new model number:");
                String newModelNumber = scanner.nextLine();

                System.out.println("Enter the new serial number:");
                String newSerialNumber = scanner.nextLine();

                System.out.println("Enter the new MAC address:");
                String newMacAddress = scanner.nextLine();

                System.out.println("Enter the new IP address:");
                String newIPAddress = scanner.nextLine();

                updatedEquipment = new Equipment(newName, newManufacturer, newModelNumber, newSerialNumber,
                        newMacAddress, newIPAddress);
                equipmentList.set(i, updatedEquipment);
                found = true;
                System.out.println("Equipment updated successfully.");
                break;
            }
        }

        if (found) {
            lastUpdatedEquipment = updatedEquipment; // Update lastUpdatedEquipment
        } else {
            System.out.println("Equipment with the specified serial number not found.");
        }
    }



    // Load Equipment from File
    public void loadEquipmentFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("equipment.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    String manufacturer = parts[1].trim();
                    String modelNumber = parts[2].trim();
                    String serialNumber = parts[3].trim();
                    String macAddress = parts[4].trim();
                    String ipAddress = parts[5].trim();

                    Equipment equipment = new Equipment(name, manufacturer, modelNumber, serialNumber, macAddress, ipAddress);
                    equipmentList.add(equipment);
                }
            }
            System.out.println("Equipment data loaded from file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Equipment data file not found. Starting with an empty equipment list.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading equipment data from file.");
        }
    }

    // Connect Equipment
    public boolean connectEquipment(String serialNumber1, String serialNumber2) {
        Equipment equipment1 = findEquipmentBySerialNumber(serialNumber1);
        Equipment equipment2 = findEquipmentBySerialNumber(serialNumber2);

        if (equipment1 == null || equipment2 == null) {
            System.out.println("One or both equipment not found.");
            return false;
        }

        equipment1.setConnected(true);
        equipment2.setConnected(true);

        lastConnectedEquipment = equipment2;

        System.out.println("Equipment successfully connected.");
        return true;
    }

    // Disconnect Equipment
    public void disconnectEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the serial number of the equipment to disconnect:");
        String serialNumber = scanner.nextLine();

        Equipment equipment = findEquipmentBySerialNumber(serialNumber);

        if (equipment == null) {
            System.out.println("Equipment not found.");
            return;
        }

        equipment.setConnected(false);
        System.out.println("Equipment successfully disconnected.");
    }

    // Get Last Updated Equipment
    public void getLastUpdatedEquipment() {
        if (lastUpdatedEquipment != null) {
            System.out.println("Last Updated Equipment:");
            System.out.println(lastUpdatedEquipment);
        } else {
            System.out.println("No equipment has been updated yet.");
        }
    }

    // Get Last Connected Equipment
    public void getLastConnectedEquipment() {
        if (lastConnectedEquipment != null) {
            System.out.println("Last Connected Equipment:");
            System.out.println(lastConnectedEquipment);
        } else {
            System.out.println("No equipment has been connected yet.");
        }
    }

    // Get Last N Updated Equipment
    public void getLastNUpdatedEquipment(int n) {
        if (n <= 0) {
            System.out.println("Invalid number of equipment.");
            return;
        }

        if (n > updatedEquipmentList.size()) {
            System.out.println("Requested number exceeds number of updated equipment. Returning all updated equipment.");
            n = updatedEquipmentList.size();
        }

        System.out.println("Last " + n + " Updated Equipment:");
        for (int i = updatedEquipmentList.size() - 1; i >= updatedEquipmentList.size() - n; i--) {
            System.out.println(updatedEquipmentList.get(i));
        }
    }

    public Equipment findEquipmentBySerialNumber(String serialNumber) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getSerialNumber().equals(serialNumber)) {
                return equipment;
            }
        }
        return null;
    }
}
