import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class MemoryBlock {
    int startAddress;
    int size;
    int processId;

    MemoryBlock(int startAddress, int size, int processId) {
        this.startAddress = startAddress;
        this.size = size;
        this.processId = processId;
    }
}

public class MemoryManagementSimulation {

    static ArrayList<MemoryBlock> memory = new ArrayList<>();
    static int availableMemory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("1-设置内存大小\nEnter initial memory size (in KB): ");
        availableMemory = scanner.nextInt();
        memory.add(new MemoryBlock(0, availableMemory, -1));

        while (true) {
            System.out.println("\nMemory Management Simulation:");
            System.out.println("1 - set memory size");
            System.out.println("2 - select memory allocation algorithm");
            System.out.println("3 - New process");
            System.out.println("4 - Terminate a process");
            System.out.println("5 - display memory usage");
            System.out.println("0 - Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                {
                    System.out.print("Enter new memory size (in KB): ");
                    availableMemory = scanner.nextInt();
                    memory.clear();
                    memory.add(new MemoryBlock(0, availableMemory, -1));
                    break;
                }
                case 2: chooseAllocationAlgorithm(scanner);break;
                case 3: createNewProcess(scanner);break;
                case 4: killProcess(scanner);break;
                case 5: printMemoryUsage();break;
                case 0:
                {
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;
                }
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void chooseAllocationAlgorithm(Scanner scanner) {
        System.out.println("Select memory allocation algorithm:");
        System.out.println("1 - 首次适应算法");
        System.out.println("2 - 最佳适应算法");
        System.out.println("3 - 最坏适应算法");

        int algorithmChoice = scanner.nextInt();

        switch (algorithmChoice) {
            case 1: {
                memory.sort(Comparator.comparingInt(m -> m.startAddress));
                System.out.println("Memory allocation algorithm set to 首次适应算法.");
                break;
            }
            case 2: {
                memory.sort(Comparator.comparingInt(m -> m.size));
                System.out.println("Memory allocation algorithm set to 最佳适应算法.");
                break;
            }
            case 3: {
                memory.sort(Comparator.comparingInt((MemoryBlock m) -> m.size).reversed());
                System.out.println("Memory allocation algorithm set to 最坏适应算法.");
                break;
            }
            default: System.out.println("Invalid choice. Using default algorithm (首次适应算法).");break;
        }
    }

    private static void createNewProcess(Scanner scanner) {
        System.out.print("Enter processID: ");
        int processID = scanner.nextInt();
        System.out.print("Enter process size (in KB): ");
        int processSize = scanner.nextInt();

        MemoryBlock allocatedBlock = allocateMemory(processSize);

        if (allocatedBlock != null) {
            allocatedBlock.processId = processID;
            System.out.println("Process allocated. Memory block: " + allocatedBlock.startAddress +
                    " - " + (allocatedBlock.startAddress + allocatedBlock.size - 1) +
                    " (Size: " + allocatedBlock.size + " KB, Process ID: " + allocatedBlock.processId + ")");
        } else {
            System.out.println("Unable to allocate memory for the process.");
        }
    }

    private static MemoryBlock allocateMemory(int processSize) {
        for (MemoryBlock block : memory) {
            if (block.processId == -1 && block.size >= processSize) {
                MemoryBlock allocatedBlock = new MemoryBlock(block.startAddress, processSize, 1);
                block.startAddress += processSize;
                block.size -= processSize;
                memory.add(allocatedBlock);
                return allocatedBlock;
            }
        }
        return null;
    }

    private static void killProcess(Scanner scanner) {
        System.out.print("Enter process ID to kill: ");
        int processId = scanner.nextInt();

        for (MemoryBlock block : memory) {
            if (block.processId == processId) {
                block.processId = -1;
                System.out.println("Process killed. Memory block released: " + block.startAddress +
                        " - " + (block.startAddress + block.size - 1) +
                        " (Size: " + block.size + " KB)");
                mergeFreeBlocks();
                return;
            }
        }

        System.out.println("Process ID not found.");
    }

    private static void mergeFreeBlocks() {
        memory.sort(Comparator.comparingInt(m -> m.startAddress));

        for (int i = 0; i < memory.size() - 1; i++) {
            MemoryBlock currentBlock = memory.get(i);
            MemoryBlock nextBlock = memory.get(i + 1);

            if (currentBlock.processId == -1 && nextBlock.processId == -1) {
                currentBlock.size += nextBlock.size;
                memory.remove(i + 1);
                i--;
            }
        }
    }

    private static void printMemoryUsage() {
        System.out.println("Memory Usage:");
        for (MemoryBlock block : memory) {
            if (block.processId == -1) {
                System.out.println("Free Block: \t" + block.startAddress +
                        " - " + (block.startAddress + block.size - 1) +
                        " (Size: " + block.size + " KB)");
            } else {
                System.out.println("Occupied Block: \t" + block.startAddress +
                        " - " + (block.startAddress + block.size - 1) +
                        " (Size: " + block.size + " KB, Process ID: " + block.processId + ")");
            }
        }
    }
}
