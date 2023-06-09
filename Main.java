
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EquipmentLibrary library = new EquipmentLibrary();
        library.loadEquipmentFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\nMenu");
            System.out.println("----------------------");
            System.out.println("1. Add Equipment");
            System.out.println("2. Delete Equipment");
            System.out.println("3. Update Equipment");
            System.out.println("4. Find Equipment");
            System.out.println("5. Display Equipment");
            System.out.println("6. Connect Equipment");
            System.out.println("7. Disconnect Equipment");
            System.out.println("8. Get Last Updated Equipment");
            System.out.println("9. Get Last Connected Equipment");
            System.out.println("10. Get Last N Updated Equipment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("\nEnter the number of equipment to add: ");
                        int numEquipment = Integer.parseInt(scanner.nextLine());

                        for (int i = 0; i < numEquipment; i++) {
                            System.out.println("\nEnter details for Equipment " + (i + 1) + ":");

                            System.out.print("Name: ");
                            String name = scanner.nextLine();

                            System.out.print("Manufacturer: ");
                            String manufacturer = scanner.nextLine();

                            System.out.print("Model Number: ");
                            String modelNumber = scanner.nextLine();

                            System.out.print("Serial Number: ");
                            String serialNumber = scanner.nextLine();

                            System.out.print("MAC Address: ");
                            String macAddress = scanner.nextLine();

                            System.out.print("IP Address: ");
                            String ipAddress = scanner.nextLine();

                            Equipment equipment = new Equipment(name, manufacturer, modelNumber,
                                    serialNumber, macAddress, ipAddress);
                            library.addEquipment(equipment);
                        }
                        break;

                    case 2:
                        library.deleteEquipment();
                        break;

                    case 3:
                        System.out.println("Enter the serial number of the equipment to update:");
                        String serialNumber = scanner.nextLine();
                        library.updateEquipment(serialNumber, scanner);
                        break;

                    case 4:
                        library.findEquipment();
                        break;

                    case 5:
                        library.displayEquipment();
                        break;

                    case 6:
                        System.out.println("Enter the serial number of equipment 1:");
                        String serialNumber1 = scanner.nextLine();
                        System.out.println("Enter the serial number of equipment 2:");
                        String serialNumber2 = scanner.nextLine();
                        boolean success = library.connectEquipment(serialNumber1, serialNumber2);
                        break;

                    case 7:
                        library.disconnectEquipment();
                        break;

                    case 8:
                        library.getLastUpdatedEquipment();
                        break;

                    case 9:
                        library.getLastConnectedEquipment();
                        break;

                    case 10:
                        System.out.print("Enter the number of equipment to retrieve: ");
                        int n = Integer.parseInt(scanner.nextLine());
                        library.getLastNUpdatedEquipment(n);
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
